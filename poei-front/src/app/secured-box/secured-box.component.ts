import { Component } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { services } from '../service/service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-secured-box',
  templateUrl: './secured-box.component.html',
  styleUrls: ['./secured-box.component.css']
})
export class SecuredBoxComponent {
  securedBoxUrl = '';
  // CREATE
  createSecret: Secret;
  createSuccessMessage = null;
  createError: any;
  // GET
  secretValue = '';
  getError: any;

  constructor(private http: HttpClient) {
    this.securedBoxUrl = services.securedBox.url;
    this.initSecret();
  }

  create(fCreate: NgForm) {
    this.http.post<any>(this.securedBoxUrl + '/secret', this.createSecret).subscribe(
      resp => {
        this.createSuccessMessage = 'Secret "' + this.createSecret.key + '" successfully created';
        this.createError = null;
      },
      error => {
        console.error(error);
        this.createError = error.error;
        this.createSuccessMessage = null;
      }
    );
  }

  get(fGet: NgForm) {
    this.http.post<any>(this.securedBoxUrl + '/secret/' + fGet.value.key, {password: fGet.value.password}).subscribe(
      resp => {
        this.secretValue = resp.value;
        this.getError = null;
      },
      error => {
        console.log(error);
        this.getError = error.error;
        this.secretValue = '';
      }
    );
  }

  initSecret() {
    this.createSecret = {
      key: '',
      value: '',
      password: ''
    };
  }
}

class Secret {
  key: string;
  value: string;
  password: string;
}
