import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

const ownerlist: Routes = [
  {
    path: "",
    loadChildren: "./dashboard/dashboard.module#DashboardModule"
  },
  {
    path: "ownerlist/:ventureId",
    loadChildren: "./plot-owner-list/plot-owner-list.module#PlotOwnerListModule"
  },
  {
    path: "venture/:userId",
    loadChildren: "./venturedetails/venturedetails.module#VenturedetailsModule"
  },
  {
    path: "ownerconnect",
    loadChildren: "./plot-owner-connect/plot-owner-connect.module#PlotOwnerConnectModule"
  },
  {
    path: "register",
    loadChildren: "./register/register.module#RegisterModule"
  }
];
const userprofile: Routes = [
  {
    path: "userprofileinfo",
    loadChildren: "./user-profile/user-profile.module#UserProfileModule"
  }
];
const routes: Routes = [...ownerlist, ...userprofile];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
