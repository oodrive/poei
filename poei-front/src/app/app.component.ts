import {Component, OnDestroy} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, Subscription} from 'rxjs/Rx';
import {retryWhen, shareReplay, tap} from 'rxjs/operators';
import {services} from './service/service';

const healthCheck = '/actuator/health';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnDestroy {
  // LISTENERS
  pollingData: any[] = [];
  // SERVICES
  services: any;

  constructor(private http: HttpClient) {
    this.services = services;
    let i = 0;
    for (const key in this.services) {
      if (this.services.hasOwnProperty(key)) {
        const service = this.services[key];
        if (service.enabled) {
          this.pollingData[i] = this.pollingHealthCheck(service.url + healthCheck, (flag) => service.available = flag);
        }
        i++;
      }
    }
  }

  pollingHealthCheck(serviceUrl: string, fn: (flag: boolean) => void): Subscription {
    return Observable.interval(2000)
        .switchMap(() => this.http.get(serviceUrl))
        .pipe(
          tap(() => {
            // console.log('HTTP request executed');
          }),
          shareReplay(),
          retryWhen(errors => {
              return errors
                      .pipe(
                          tap(() => {
                            fn(false);
                            // console.log('Could not connect to', serviceUrl, '. Retrying...');
                          }
                      ));
          })
        )
        .subscribe(
          res => {
            fn(true);
            // console.log('HTTP response', res);
          },
          err => console.log('HTTP Error', err),
          () => console.log('HTTP request completed.')
        );
  }

  ngOnDestroy() {
    this.pollingData.forEach(polling => {
      polling.unsubscribe();
    });
  }
}
