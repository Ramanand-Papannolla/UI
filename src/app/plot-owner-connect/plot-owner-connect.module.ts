import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../material/material.module';
import { SharedModule } from '../shared/module/shared.module';
import { PlotOwnerConnectRoutingModule } from './plot-owner-connect-routing.module';
import { PlotOwnerConnectComponent } from './plot-owner-connect.component';
@NgModule({
  declarations: [PlotOwnerConnectComponent],
  imports: [
    CommonModule,
    FormsModule,
    SharedModule,
    MaterialModule,
    PlotOwnerConnectRoutingModule,
    ReactiveFormsModule,
  ],
  exports: [PlotOwnerConnectComponent]
})
export class PlotOwnerConnectModule { }
