import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {IndexComponent} from './common/index/index.component';

const routes: Routes = [
  {
    path: '', component: IndexComponent,
    children: [
      {path: '', redirectTo: '/process', pathMatch: 'full'},
      {path: 'form', loadChildren: './modules/form/form.module#FormModule'},
      {path: 'process', loadChildren: './modules/process/process.module#ProcessModule'},
      {path: 'task', loadChildren: './modules/task/task.module#TaskModule'}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
