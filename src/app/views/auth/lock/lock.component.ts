// lock.component.ts

import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { UserService } from "src/environments/user.service";

@Component({
  selector: "app-lock",
  templateUrl: "./lock.component.html",
})
export class LockComponent implements OnInit {
  username: string = '';
  otp: string = '';
  enteredOtp: string = '';
user: any;

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {
   
  }

  

}