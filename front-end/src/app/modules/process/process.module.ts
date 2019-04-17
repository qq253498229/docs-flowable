import {NgModule} from '@angular/core';
import {ListComponent} from './list/list.component';
import {RouterModule, Routes} from '@angular/router';
import {ShareModule} from '../../common/share.module';

const routes: Routes = [
  {path: '', component: ListComponent}
];

@NgModule({
  declarations: [ListComponent],
  imports: [
    ShareModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class ProcessModule {
}
