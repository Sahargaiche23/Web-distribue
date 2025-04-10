import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from "src/app/user"; // Assurez-vous que ce modèle est correct

@Injectable({
  providedIn: "root",
})
export class UserService {
  private apiUrl = 'http://localhost:8085/api/v1/users';

  constructor(private http: HttpClient) {}

  // ✅ Créer un utilisateur (avec Keycloak + DB)
  registerUser(user: User): Observable<string> {
    return this.http.post(`${this.apiUrl}`, user, { responseType: 'text' });
  }

  // ❌ Doublon de "register", donc supprimé ou on peut fusionner
  // create(user: User): Observable<User> {
  //   return this.http.post<User>(`${this.apiUrl}/register`, user);
  // }

  // ✅ Récupérer tous les utilisateurs (si l’endpoint existe côté backend)
  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}`);
  }
  getUserByUsername(username: string): Observable<User> {
    const params = new HttpParams().set('username', username);  // Ajout du paramètre username
    return this.http.get<User>(this.apiUrl, { params });
  }

  // ✅ Mise à jour utilisateur
  updateUser(userId: string, user: User): Observable<string> {
    return this.http.put(`${this.apiUrl}/${userId}`, user, { responseType: 'text' });
  }

  // ✅ Suppression utilisateur
  deleteUser(userId: string): Observable<string> {
    return this.http.delete(`${this.apiUrl}/${userId}`, { responseType: 'text' });
  }

  // ✅ Récupérer un utilisateur par email
  getUserByEmail(useremail: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/user/${useremail}`);
  }

  // ✅ Connexion (obtenir le token Keycloak)
  login(username: string, password: string): Observable<any> {
    const loginData = {
      username: username,
      password: password
    };

    return this.http.post(`${this.apiUrl}/login`, loginData, {
      responseType: 'text' // ou json si le backend retourne un objet
    });
  }
}
