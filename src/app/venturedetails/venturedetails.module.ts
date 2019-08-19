import { MaterialModule } from "./../material/material.module";
import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { RouterModule } from "@angular/router";
import { VentureService } from "./service/ventureservice.service";
import { VentureDetailsRoutingModule } from "./venture-details-routing.module";
import { VenturedetailsComponent } from "./venturedetails.component";

@NgModule({
  declarations: [VenturedetailsComponent],
  imports: [
    VentureDetailsRoutingModule,
    CommonModule,
    RouterModule,
    MaterialModule
  ],
  providers: [VentureService]
})
export class VenturedetailsModule {}
