import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { User } from "src/app/user";
import { UserService } from "src/environments/user.service";

@Component({
  selector: "app-card-profile",
  templateUrl: "./card-profile.component.html",
})
export class CardProfileComponent implements OnInit {
  userId: number;
  userInfo: any;
  user: User;
  storedUsername: string;
  dateNaissance: string;

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit() {
    // Récupérer les valeurs de sessionStorage et localStorage
    this.storedUsername = sessionStorage.getItem('username');
    this.dateNaissance = sessionStorage.getItem('dateNaissance');
    this.userId = Number(localStorage.getItem('userId'));

    console.log('storedUsername:', this.storedUsername);  // Affiche le nom d'utilisateur
    console.log('userId:', this.userId);  // Affiche l'ID utilisateur
    console.log('dateNaissance:', this.dateNaissance);  // Affiche la date de naissance stockée

    // Vérifier si storedUsername est défini avant de faire la requête
    if (this.storedUsername) {
      console.log('Making API call with username:', this.storedUsername);  // Affiche le nom d'utilisateur pour la requête

      this.userService.getUserByUsername(this.storedUsername).subscribe(
        (user) => {
          console.log('User data received:', user);  // Affiche les données de l'utilisateur récupérées
          this.userInfo = user;
          this.user = user[0];  // Si la réponse est un tableau, sélectionne le premier utilisateur
        },
        (error) => {
          console.error('Error fetching user data:', error);  // Affiche l'erreur si la requête échoue
        }
      );
    } else {
      console.warn('No username stored in sessionStorage');  // Avertit si aucune valeur n'est présente
    }
  }

  updateUser(user: any) {
    this.router.navigate(['/pages/update'], { state: { user } });
  }
}
