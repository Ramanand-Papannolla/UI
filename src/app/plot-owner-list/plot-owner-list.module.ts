import { MaterialModule } from './../material/material.module';
import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { PlotOwnerListRouteModule } from "./plot-owner-list-route.module";
import { PlotOwnerListComponent } from "./plot-owner-list.component";

@NgModule({
  declarations: [PlotOwnerListComponent],
  imports: [CommonModule, PlotOwnerListRouteModule, MaterialModule],
  exports: [PlotOwnerListComponent]
})
export class PlotOwnerListModule {}
