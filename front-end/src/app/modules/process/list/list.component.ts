import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  constructor(
    private http: HttpClient
  ) {
  }

  ngOnInit() {
    this.http.get('/api/process').subscribe(res => {
      console.log(res);
    });
  }

}
