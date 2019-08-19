import { Component, HostListener, OnInit, ViewChild } from "@angular/core";
import { Router } from "@angular/router";
import { AuthService } from "angularx-social-login";
import { StompService } from "ng2-stomp-service";
import { UserService } from "../shared/service/user.service";
import { settings } from "./../shared/util/settings";
import { MatSidenavContainer } from "@angular/material";
import { User } from "../shared/model/user";

@Component({
  selector: "dashboard",
  templateUrl: "./dashboard.component.html",
  styleUrls: ["./dashboard.component.css"]
})
export class DashboardComponent implements OnInit {
  private receiver: string;
  @ViewChild("sidenav") sidenav: MatSidenavContainer;
  private user: User;
  constructor(
    private router: Router,
    private userService: UserService,
    private stompService: StompService,
    private authService: AuthService
  ) {
    stompService.configure({
      host: settings.baseUrl + "/chat",
      queue: { init: false }
    });
  }

  ngOnInit() {
    if (this.user == null || this.user === undefined) {
      if (
        undefined != sessionStorage.getItem("user") &&
        null !== sessionStorage.getItem("user")
      ) {
        this.user = JSON.parse(sessionStorage.getItem("user"));
      }
      this.userService.getloggedInUsero().subscribe(res => {
        this.user = res;
      });
    }
  }

  @HostListener("window:unload", ["$event"])
  onUnload() {
    // this.logout();
  }

  onReceiverChange(event) {
    this.receiver = event;
  }

  logout() {
    this.clearSession();
  }

  clearSession() {
    sessionStorage.removeItem("user");
    this.user = null;
    if ("CONNECTED" == this.stompService.status) {
      this.stompService.disconnect();
    }
    console.log(this.stompService.status);
    this.router.navigate(["/"]);
  }
  showNavbarImgs(): boolean {
    return null !== this.user || undefined != this.user;
  }

  getNameFirstChar(): any {
    return JSON.parse(sessionStorage.getItem("user")).name[0];
  }
}
