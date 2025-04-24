import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { User } from "src/app/user";
import { UserService } from "src/environments/user.service";

@Component({
  selector: "app-profile",
  templateUrl: "./profile.component.html",
})
export class ProfileComponent implements OnInit {
  userId: number;
  username: string = '';
  userInfo: any;
  user:User;
userData: any;

  constructor(private userService: UserService, private router: Router) {}

 

  ngOnInit(): void {
    // Récupérer l'utilisateur par son nom d'utilisateur
    this.userService.getUserByUsername(this.username).subscribe(
      (user: User) => {
        this.userInfo = user;  // Assigner les données de l'utilisateur récupéré
      },
      (error) => {
        console.error('Error fetching user by username:', error);
      }
    );
  }
  updateUser(user: any) {
   
    this.router.navigate(['/pages/update'], { state: { user } });
  }
}  
