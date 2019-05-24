import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient, HttpParams } from '@angular/common/http';
import { services } from '../service/service';

@Component({
  selector: 'app-password',
  templateUrl: './password.component.html',
  styleUrls: ['./password.component.css']
})
export class PasswordComponent {
  passwordUrl = '';
  // Random password
  randomPassword: string;
  randomPasswordError: any;
  passwordLength = 8;
  useChars = true;
  useDigits = true;
  // Score
  password = {
    value: ''
  };
  score = {
    score: ''
  };
  scoreError: any;
  scoreClass = '';

  constructor(private http: HttpClient) {
    this.passwordUrl = services.password.url;
  }

  generateRandomPassword(fRandom: NgForm) {
    const options = {
      params: new HttpParams()
        .set('length', this.passwordLength + '')
        .set('useChars', this.useChars + '')
        .set('useDigits', this.useDigits + '')
    };
    this.http.get(this.passwordUrl + '/random', options)
    .subscribe((data: Password) => {
      this.randomPassword = data.value;
      this.randomPasswordError = null;
    },
      error => {
        console.error(error);
        this.randomPasswordError = error.error;
        this.randomPassword = null;
      }
    );
  }

  computeScore(fScore: NgForm) {
    this.http.post<any>(this.passwordUrl + '/score', this.password)
    .subscribe((data: Score) => {
      this.score = data;
      if (data.score === 'HIGH') {
        this.scoreClass = 'text-success';
      } else if (data.score === 'MEDIUM') {
        this.scoreClass = 'text-warning';
      } else {
        this.scoreClass = 'text-danger';
      }
    },
      error => {
        console.error(error);
        this.scoreError = error.error;
        this.score = {
          score: ''
        };
      }
    );
  }

  isFRandomDisabled(fRandom: NgForm): boolean {
    return fRandom.value.passwordLength <= 0 || !fRandom.value.useChars && !fRandom.value.useDigits;
  }
}

class Password {
  value: string;
}

class Score {
  score: string;
}
