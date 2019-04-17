import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {
  result = {
    list: []
  };

  param = {
    userId: ''
  };

  constructor(
    private http: HttpClient,
    private router: Router
  ) {
  }

  ngOnInit() {

  }

  getData() {
    this.http.get<any[]>('/api/process').subscribe(res => {
      this.result.list = res;
    });
  }

  option(id: any) {
    this.router.navigate(['/process', id]);
  }
}
