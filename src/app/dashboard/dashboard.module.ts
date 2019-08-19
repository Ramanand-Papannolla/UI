import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material';
import { RouterModule } from "@angular/router";
import { LoginModule } from "./../login/login.module";
import { MaterialModule } from "./../material/material.module";
import { VenturedetailsModule } from "./../venturedetails/venturedetails.module";
import { DashboardRoutingModule } from "./dashboard-routing.module";
import { DashboardComponent } from "./dashboard.component";

@NgModule({
  declarations: [DashboardComponent],
  imports: [
    CommonModule,
    MaterialModule,
    LoginModule,
    VenturedetailsModule,
    RouterModule,
    DashboardRoutingModule,
    FormsModule,

  ],
  exports: [DashboardComponent]
})
export class DashboardModule {}
