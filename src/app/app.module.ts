import { PlotOwnerConnectService } from './shared/service/plot-owner-connect.service';
import { PlotOwnerConnectModule } from './plot-owner-connect/plot-owner-connect.module';
import { NgModule } from "@angular/core";
import { HttpModule } from '@angular/http';
import { BrowserModule } from "@angular/platform-browser";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { AuthServiceConfig, FacebookLoginProvider, GoogleLoginProvider, SocialLoginModule } from 'angularx-social-login';
import { StompService } from 'ng2-stomp-service';
import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { DashboardModule } from "./dashboard/dashboard.module";
import { MaterialModule } from "./material/material.module";
import { UserService } from './shared/service/user.service';
import { VentureService } from "./venturedetails/service/ventureservice.service";
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import { RegisterModule } from './register/register.module';
import { HttpClientModule } from '@angular/common/http';


const config = new AuthServiceConfig([
  {
    id: GoogleLoginProvider.PROVIDER_ID,
    provider: new GoogleLoginProvider('823898676621-427jhcjug96ijmbt9il0h9chd8norbdk.apps.googleusercontent.com')
  },
  {
    id: FacebookLoginProvider.PROVIDER_ID,
    provider: new FacebookLoginProvider('131571760819154')
  }
]);

export function socialConfig() {
  return config;
}
@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    AppRoutingModule,
    DashboardModule,
    HttpModule,
    SocialLoginModule,
    RegisterModule,
    HttpClientModule
  ],
  providers: [VentureService, UserService, StompService, SocialLoginModule, PlotOwnerConnectService
    , {
      provide: AuthServiceConfig,
      useFactory: socialConfig
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
