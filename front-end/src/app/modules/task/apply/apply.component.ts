import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
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

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private common: CommonService
  ) {
  }

  ngOnInit() {
    this.result.id = this.route.snapshot.paramMap.get('id');
    this.http.get('/api/form/getFormByTaskId/' + this.result.id).subscribe(res => {
      this.common.log(res);
      this.result.form = res;
    });
  }

}
