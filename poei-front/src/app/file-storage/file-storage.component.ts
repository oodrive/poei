import { Component, OnDestroy } from '@angular/core';
import {
  HttpClient,
  HttpEventType,
  HttpRequest,
  HttpResponse
} from '@angular/common/http';
import { services } from '../service/service';
import { NgForm } from '@angular/forms';
import { Observable } from 'rxjs/Rx';
import { retryWhen, shareReplay, tap } from 'rxjs/operators';

@Component({
  selector: 'app-file-storage',
  templateUrl: './file-storage.component.html',
  styleUrls: ['./file-storage.component.css']
})
export class FileStorageComponent implements OnDestroy {
  fileStorageUrl = '';
  fileName = 'Choose file';
  // UPLOAD
  selectedFiles: FileList;
  currentFileUpload: File;
  progress: { percentage: number } = { percentage: 0 };
  uploadedFile: any;
  successMessage = '';
  uploadError: any;
  // DOWNLOAD
  polling: any;
  files = [];
  downloadError: any;

  constructor(private http: HttpClient) {
    this.fileStorageUrl = services.fileStorage.url;
    this.pollingFiles();
  }

  selectFile(event) {
    this.selectedFiles = event.target.files;
    this.fileName = this.selectedFiles[0].name;
  }

  pollingFiles() {
    const getFilesUrl = this.fileStorageUrl + '/file';
    this.polling = Observable.interval(2000)
        .switchMap(() => this.http.get(getFilesUrl))
        .pipe(
          tap(() => {
            // console.log('HTTP request executed');
          }),
          shareReplay(),
          retryWhen(errors => {
              return errors
                      .pipe(
                          tap(() => {
                            console.log('Could not connect to', getFilesUrl, '. Retrying...');
                          }
                      ));
          })
        )
        .subscribe(
          (data: FileDTO[]) => {
            if (this.files.length !== data.length + 1) {
              this.files = [{
                fileId: -1,
                name: 'non-existent-file.pdf'
              }];
              this.files = this.files.concat(data);
            }
          },
          err => console.log('HTTP Error', err),
          () => console.log('HTTP request completed.')
        );
  }

  upload() {
    this.progress.percentage = 0;
    this.currentFileUpload = this.selectedFiles.item(0);

    const formdata: FormData = new FormData();

    formdata.append('file', this.currentFileUpload);
    this.currentFileUpload = this.selectedFiles.item(0);
    const req = new HttpRequest(
      'POST',
      this.fileStorageUrl + '/file',
      formdata,
      {
        reportProgress: true,
        responseType: 'text'
      }
    );

    this.http.request(req).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress.percentage = Math.round(
            (100 * event.loaded) / event.total
          );
        } else if (event instanceof HttpResponse) {
          this.uploadedFile = event.body;
          const f = JSON.parse(this.uploadedFile);
          this.successMessage = 'File "' + f.name + '" successfully uploaded';
          this.uploadError = null;
        }
      },
      error => {
        console.error(error);
        this.uploadError = error.error;
        this.successMessage = '';
      }
    );

    this.selectedFiles = undefined;
  }

  download(fDownload: NgForm) {
    window.open(this.fileStorageUrl + '/file/' + fDownload.value.fileId);
  }

  ngOnDestroy() {
    this.polling.unsubscribe();
  }
}

class FileDTO {
  fileId: number;
  name: string;
}
