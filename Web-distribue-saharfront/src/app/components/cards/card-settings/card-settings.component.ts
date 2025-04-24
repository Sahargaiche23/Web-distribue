import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { User } from "src/app/user";
import { UserService } from "src/environments/user.service";

@Component({
  selector: "app-card-settings",
  templateUrl: "./card-settings.component.html",
})
export class CardSettingsComponent implements OnInit {
  userId: number;
  userInfo: any;
  user: User;

  storedUsername: string;
  dateNaissance: string;

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit() {
    this.storedUsername = sessionStorage.getItem('username');
    this.dateNaissance = sessionStorage.getItem('dateNaissance');
    
    this.userId = Number(localStorage.getItem('userId'));
  
    // Vérifier si le nom d'utilisateur est disponible dans le sessionStorage
    if (this.storedUsername) {
      this.userService.getUserByUsername(this.storedUsername).subscribe(
        (user) => {
          this.userInfo = user;
          // Si la réponse est un tableau, on prend le premier utilisateur
          this.user = user[0] || user;
          console.log('User data received:', this.user);  // Affiche les données récupérées de l'utilisateur
        },
        (error) => {
          console.error('Error fetching user data:', error);
        }
      );
    } else {
      console.warn('No username stored in sessionStorage');
    }
  }
}
