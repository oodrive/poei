import { Component } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { services } from '../service/service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-hasher',
  templateUrl: './hasher.component.html',
  styleUrls: ['./hasher.component.css']
})
export class HasherComponent {
  hasherUrl = '';
  // HASH
  text = '';
  hashAlgorithm = 'SHA256';
  hash: Hash;
  hashError: any;

  constructor(private http: HttpClient) {
    this.hasherUrl = services.hasher.url;
  }

  generateHash(fHash: NgForm) {
    const options = {
      params: new HttpParams()
        .set('text', this.text)
        .set('hashAlgorithm', this.hashAlgorithm)
    };
    this.http.get(this.hasherUrl + '/hash', options)
    .subscribe((data: Hash) => {
      this.hash = data;
      this.hashError = null;
    },
      error => {
        console.error(error);
        this.hashError = error;
        this.hash = null;
      }
    );

  }
}

class Hash {
  value: string;
  alreadyUsed: boolean;
}
