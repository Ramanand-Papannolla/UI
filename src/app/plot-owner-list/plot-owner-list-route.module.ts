import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PlotOwnerListComponent } from './plot-owner-list.component';

const routes: Routes = [
  {
    path: "",
    component: PlotOwnerListComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PlotOwnerListRouteModule { }
