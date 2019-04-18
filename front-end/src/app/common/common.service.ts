import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommonService {

  constructor() {
  }

  log(obj: any) {
    if (!environment.production) {
      console.log(obj);
    }
  }
}
