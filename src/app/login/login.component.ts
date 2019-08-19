import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { AuthService, FacebookLoginProvider, GoogleLoginProvider, SocialUser } from "angularx-social-login";

import { UserService } from "../shared/service/user.service";

@Component({
  selector: "app-login",
  templateUrl:
    "./login.component.html",
  styleUrls: ["./login.component.scss"]
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  message: string;
  private user: SocialUser;
  private loggedIn: boolean;

  constructor(
    private router: Router,
    private userService: UserService,
    private authService: AuthService,
    private formBuilder: FormBuilder
  ) { }



  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ["", Validators.required],
      password: ["", Validators.required]
    });
  }

  onSubmit() {
    this.submitted = true;
    this.connect(this.form.username.value, this.form.password.value);
  }

  connect(username: string, password: string) {
    if ((this.user == null || this.user == undefined) && this.loginForm.invalid) {
      return;
    }
    this.userService.login(username, password).subscribe(
      res => {
        this.user=res;
        this.userService.updateUserLoginSubject(res);
        sessionStorage.setItem("user", JSON.stringify(this.user));
          // this.subscriptionService.userSubScription(this.user);
          this.router.navigate(["venture",this.user.id]);
      },
      error => {
        this.message = error.error.message;
      }
    );
  }

  get form() {
    return this.loginForm.controls;
  }

  signInWithGoogle(): void {
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID).then(userData => {
      // sessionStorage.setItem("user", JSON.stringify(userData));
      this.user = userData;
      // this.router.navigate(["venture",this.user.id]);
    });
  }
  signInWithFB():
    void { this.authService.signIn(FacebookLoginProvider.PROVIDER_ID); }

  clearData() {
    sessionStorage.removeItem("name");
    this.message = null;
  }
}
