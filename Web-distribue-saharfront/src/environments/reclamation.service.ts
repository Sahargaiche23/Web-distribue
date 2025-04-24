import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReclamationService {
  private apiUrl = 'http://localhost:8099/reclamation';

  constructor(private http: HttpClient) {}

  // ✅ Récupérer toutes les réclamations
  getAllReclamations(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/reclamations`);
  }

  // ✅ Ajouter une réclamation
  createReclamation(reclamation: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/addReclamation`, reclamation, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  // ✅ Supprimer une réclamation par ID
  deleteReclamation(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/reclamation/${id}`);
  }

  // ✅ Récupérer une réclamation par ID
  getReclamationById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/reclamation/${id}`);
  }

  // ✅ Mettre à jour une réclamation
  uupdateReclamation(id: number, data: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/reclamations/${id}`, data); // Correcting the method signature to accept id and data
  }

  // ✅ Rechercher par statut
  searchByStatut(statut: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/searchByStatut/${statut}`);
  }
}
