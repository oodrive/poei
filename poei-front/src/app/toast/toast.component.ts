import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.css']
})
export class ToastComponent implements OnInit {
  @Input() input: any;
  @Input() title: string;
  @Input() isError: boolean;
  error: any;

  constructor() { }

  ngOnInit() {
    if (this.isError) {
      if (typeof this.input === 'string' || this.input instanceof String) {
        this.error = JSON.parse(this.input + '');
      } else {
        this.error = this.input;
      }
    }
  }

  close() {
    this.input = null;
  }
}
