import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TrainService {
  private apiUrl = 'http://localhost:8070/annonce'; // Correct base URL for "annonce"

  constructor(private http: HttpClient) {}

  // Get all annonces
  getAllAnnonces(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/annonces`);
  }

  // Get an annonce by its ID
  getAnnonce(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/annonce/${id}`);
  }

  // Create a new annonce
  createAnnonce(annonce: any, file: File, userId: number): Observable<any> {
    const formData = new FormData();
    formData.append('annonce', JSON.stringify(annonce)); // Append annonce object
    formData.append('file', file); // Append the file if present
    formData.append('userId', userId.toString()); // Append user ID

    const httpOptions = {
      headers: new HttpHeaders()
    };

    return this.http.post<any>(`${this.apiUrl}/addannonce`, formData, httpOptions);
  }

  // Update an annonce
  updateAnnonce(annonce: any, file: File): Observable<any> {
    const formData = new FormData();
    formData.append('annonce', JSON.stringify(annonce)); // Append annonce object
    formData.append('file', file); // Append the file if present

    return this.http.patch<any>(`${this.apiUrl}/annonce`, formData);
  }

  // Delete an annonce by ID
  deleteAnnonce(id: number): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/annonce/${id}`);
  }

  // Search annonces by title
  searchAnnonceByTitle(titreAnnonce: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/searchAnnonce/${titreAnnonce}`);
  }
}
