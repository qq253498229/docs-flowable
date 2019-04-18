import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {CommonService} from '../../../common/common.service';

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
    private router: Router,
    private common: CommonService
  ) {
  }

  ngOnInit() {
    this.http.get<any[]>('/api/user').subscribe(res => {
      this.common.log(res);
      this.result.users = res;
    });
  }

  getData() {
    this.common.log(this.param.userId);
    this.http.get<any[]>('/api/task/' + this.param.userId).subscribe(res => {
      this.common.log(res);
      this.result.list = res;
    });
  }

  operate(id: string) {
    this.router.navigate(['/task', id]);
  }

  claim(id: any) {
    this.common.log(id);
    this.http.post('/api/task/claim/' + this.param.userId + '/' + id, null).subscribe(res => {
      this.common.log(res);
      alert('领取成功');
    });
  }
}
