import { Component, OnDestroy } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { services } from '../service/service';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs/Rx';
import {retryWhen, shareReplay, tap} from 'rxjs/operators';

@Component({
  selector: 'app-twitter',
  templateUrl: './twitter.component.html',
  styleUrls: ['./twitter.component.css']
})
export class TwitterComponent implements OnDestroy {
  twitterUrl = '';
  // TWEET
  createTweet: CreateTweet;
  createdTweet: ReadTweet;
  tweetSuccessMessage: string;
  tweetError: any;
  // GET TWEETS
  tweets: any;
  getTweetsError: any;
  accounts = [];
  polling: any;
  page = 0;
  nbTweets = 3;
  selectedAccountId = -1;

  constructor(private http: HttpClient) {
    this.twitterUrl = services.twitter.url;
    this.createTweet = this.initCreateTweet();
    this.pollingAccounts();
  }

  pollingAccounts() {
    const getTweetsUrl = this.twitterUrl + '/account';
    this.polling = Observable.interval(2000)
        .switchMap(() => this.http.get(getTweetsUrl))
        .pipe(
          tap(() => {
            // console.log('HTTP request executed');
          }),
          shareReplay(),
          retryWhen(errors => {
              return errors
                      .pipe(
                          tap(() => {
                            console.log('Could not connect to', getTweetsUrl, '. Retrying...');
                          }
                      ));
          })
        )
        .subscribe(
          (data: ReadAccount[]) => {
            if (this.accounts.length - 1 !== data.length) {
              this.accounts = [];
              this.accounts[0] = {
                accountId: -1,
                login: 'ALL'
              };
              this.accounts = this.accounts.concat(data);
            }
          },
          err => console.log('HTTP Error', err),
          () => console.log('HTTP request completed.')
        );
  }

  tweet(fTweet: NgForm) {
    this.http.post<any>(this.twitterUrl + '/tweet', this.createTweet).subscribe(
      (data: ReadTweet) => {
        this.createdTweet = data;
        this.tweetError = null;
      },
      error => {
        console.error(error);
        this.tweetError = error.error;
      }
    );
  }

  getTweets(fGetTweets: NgForm) {
    this.page = 0;
    this.retrieveTweets();
  }

  nextTweets() {
    this.page += 1;
    this.retrieveTweets();
  }

  previousTweets() {
    this.page -= 1;
    this.retrieveTweets();
  }

  private retrieveTweets() {
    const options = {
      params: new HttpParams()
        .set('page', this.page + '')
        .set('nbTweets', this.nbTweets + '')
        .set('accountId', this.selectedAccountId + '')
    };
    this.http.get(this.twitterUrl + '/tweet', options).subscribe(
      (data: any) => {
        this.tweets = data;
        this.getTweetsError = null;
      },
      error => {
        console.error(error);
        this.getTweetsError = error.error;
        this.tweets = null;
      }
    );
  }

  private initCreateTweet(): CreateTweet {
    return {
      account: {
        login: '',
        password: ''
      },
      message: ''
    };
  }

  ngOnDestroy() {
    this.polling.unsubscribe();
  }
}

class CreateAccount {
  login: string;
  password: string;
}
class CreateTweet {
  account: CreateAccount;
  message: string;
}
class ReadAccount {
  accountId: number;
  login: string;
}
class ReadTweet {
  tweetId: number;
  account: ReadAccount;
  message: string;
}
