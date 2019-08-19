import { MaterialModule } from './../material/material.module';
import { UserProfileComponent } from "./user-profile.component";
import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { UserProfileRoutingModule } from "./user-profile-routing.module";

@NgModule({
  declarations: [UserProfileComponent],
  imports: [CommonModule, UserProfileRoutingModule, MaterialModule],
  exports:[UserProfileComponent]
})
export class UserProfileModule {}
