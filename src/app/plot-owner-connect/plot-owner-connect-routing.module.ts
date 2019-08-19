import { PlotOwnerConnectComponent } from './plot-owner-connect.component';
import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

const routes: Routes = [
  {
    path: "",
    component: PlotOwnerConnectComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PlotOwnerConnectRoutingModule {}
