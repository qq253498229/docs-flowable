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
    list: [],
    users: []
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
    this.http.get<any[]>('/api/user').subscribe(res => {
      console.log(res);
      this.result.users = res;
    });
  }

  getDate() {
    this.http.get<any[]>('/api/task/' + this.param.userId).subscribe(res => {
      console.log(res);
      this.result.list = res;
    });
  }

  operate(id: string) {
    this.router.navigate(['/task', id]);
  }

  claim(id: any) {
    console.log(id);
    this.http.post('/api/task/claim/' + this.param.userId + '/' + id, null).subscribe(res => {
      console.log(res);
      alert('领取成功');
    });
  }
}
