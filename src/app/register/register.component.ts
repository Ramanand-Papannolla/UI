import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../shared/service/user.service';
import { AuthService } from 'angularx-social-login';
import { User } from '../shared/model/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  loading = false;
  submitted = false;
  constructor(private router: Router,
    private userService: UserService,
    private authService: AuthService,
    private formBuilder: FormBuilder
  ) { }
  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      userName: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: [],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });


    this.authService.authState.subscribe(user => {
      if (user !== null && user !== undefined) {
        this.registerForm.reset(user)
      }
    });

  }

  register() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }
    this.loading = true;
    this.userService.register(this.registerForm.value)
      // .pipe(first())
      .subscribe(
        data => {
          // this.alertService.success('Registration successful', true);
          this.router.navigate(['/']);
        },
        error => {
          // this.alertService.error(error);
          this.loading = false;
        });
  }

}
