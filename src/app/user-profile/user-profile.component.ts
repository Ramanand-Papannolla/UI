import { Component, OnInit, Input } from "@angular/core";
import { Userdtls } from "./model/userdtls";

@Component({
  selector: "user-profile",
  templateUrl: "./user-profile.component.html",
  styleUrls: ["./user-profile.component.css"]
})
export class UserProfileComponent implements OnInit {
  constructor() {}
  @Input() userDtls: Userdtls;
  ngOnInit() {
    this.userDtls = {
      userName: "Ramanand",
      mobile: 8983290578,
      userId: "1234A",
      emailId: "abc.ram@gmai.com",
      image:""
        // "https://www.brecorder.com/wp-content/uploads/2018/10/shahrukh-khan-1.jpg"
    };
  }
}
