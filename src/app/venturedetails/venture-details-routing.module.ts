import { NgModule } from "@angular/core";
import { VenturedetailsComponent } from "./venturedetails.component";
import { RouterModule, Routes } from "@angular/router";

const routes: Routes = [
  {
    path: "",
    component: VenturedetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class VentureDetailsRoutingModule {}
