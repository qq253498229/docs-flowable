import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {CommonService} from '../../../common/common.service';

@Component({
  selector: 'app-apply',
  templateUrl: './apply.component.html',
  styleUrls: ['./apply.component.scss']
})
export class ApplyComponent implements OnInit {
  result = {
    id: '',
    form: {}
  };

  param = {};

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router,
    private common: CommonService
  ) {
  }

  ngOnInit() {
    this.result.id = this.route.snapshot.paramMap.get('id');
    this.http.get('/api/form/getFormByProcessDefId/' + this.result.id).subscribe(res => {
      this.common.log(res);
      this.result.form = res;
    });
  }

  back() {
    window.history.go(-1);
  }

  submit() {
    this.http.post('/api/process/' + this.result.id, this.param).subscribe(res => {
      this.common.log(res);
      alert('申请成功');
      this.router.navigate(['/process']);
    });
  }
}
