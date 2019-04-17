import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-apply',
  templateUrl: './apply.component.html',
  styleUrls: ['./apply.component.scss']
})
export class ApplyComponent implements OnInit {
  result = {
    id: ''
  };

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit() {
    this.result.id = this.route.snapshot.paramMap.get('id');

    this.http.get('/api/task/taskInfo/' + this.result.id).subscribe(res => {
      console.log(res);
    });
  }

}
