import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ServiceComponent } from './service/service.component';
import { SampleComponent } from './sample/sample.component';
import { PasswordComponent } from './password/password.component';
import { HasherComponent } from './hasher/hasher.component';
import { TwitterComponent } from './twitter/twitter.component';
import { SecuredBoxComponent } from './secured-box/secured-box.component';
import { FileStorageComponent } from './file-storage/file-storage.component';
import { ToastComponent } from './toast/toast.component';

@NgModule({
  declarations: [
    AppComponent,
    ServiceComponent,
    SampleComponent,
    PasswordComponent,
    HasherComponent,
    TwitterComponent,
    SecuredBoxComponent,
    FileStorageComponent,
    ToastComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
