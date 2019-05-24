import { Component, Input } from '@angular/core';
import { Service } from './service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-service',
  templateUrl: './service.component.html',
  styleUrls: ['./service.component.css']
})
export class ServiceComponent {
  @Input() service: Service;
  editNames = false;

  changeNames(f: NgForm) {
    this.service.owners = f.value.owners;
    this.editNames = false;
  }
}
