import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NgForm} from '@angular/forms';
import {services} from '../service/service';

@Component({
  selector: 'app-sample',
  templateUrl: './sample.component.html',
  styleUrls: ['./sample.component.css']
})
export class SampleComponent {
  pingResp: string;
  pingError: any;
  greetingResp: string;
  greetingError: any;
  name: '';
  sampleUrl = '';

  constructor(private http: HttpClient) {
    this.sampleUrl = services.sample.url;
  }

  ping() {
    this.http.get(this.sampleUrl + '/ping')
    .subscribe((data: Pong) => {
      this.pingResp = 'Pong ' + data.pong + ' times';
      this.pingError = null;
    },
      error => {
        this.pingError = error;
        this.pingResp = null;
      }
    );
  }

  greet(fGreet: NgForm) {
    const person = {
      name: fGreet.value.name
    };
    this.http.post<any>(this.sampleUrl + '/greeting', person)
    .subscribe((data: Greet) => {
      this.greetingResp = data.message;
      this.greetingError = null;
    },
      error => {
        this.greetingError = error;
        this.greetingResp = null;
      }
    );
  }
}

class Pong {
  pong: number;
}

class Greet {
  message: string;
}
