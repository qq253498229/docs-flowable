import {NgModule} from '@angular/core';
import {ListComponent} from './list/list.component';
import {RouterModule, Routes} from '@angular/router';
import {ShareModule} from '../../common/share.module';
import {ApplyComponent} from './apply/apply.component';

const routes: Routes = [
  {path: '', component: ListComponent},
  {path: ':id', component: ApplyComponent}
];

@NgModule({
  declarations: [ListComponent, ApplyComponent],
  imports: [
    ShareModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class TaskModule {
}
