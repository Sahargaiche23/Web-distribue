import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Subscription } from 'src/app/Subscription';

@Injectable({
  providedIn: 'root'
})
export class  AbonementService {
    private apiUrl = 'http://localhost:8090/api/subscriptions';  

    constructor(private http: HttpClient) {}
  
    // Récupérer toutes les souscriptions
    getAllSubscriptions(page: number = 0, size: number = 10): Observable<Subscription[]> {
      const params = new HttpParams()
        .set('page', page.toString())
        .set('size', size.toString());
        return this.http.get<any>(`${this.apiUrl}/list`, { params });
      }
  
    // Récupérer une souscription par ID
    getSubscriptionById(id: number): Observable<Subscription> {
      return this.http.get<Subscription>(`${this.apiUrl}/${id}`);
    }
  
    // Créer une nouvelle souscription
    createSubscription(subscription: Subscription): Observable<Subscription> {
      return this.http.post<Subscription>(this.apiUrl, subscription);
    }
  
    // Mettre à jour une souscription
    updateSubscription(id: number, subscription: Subscription): Observable<Subscription> {
      return this.http.put<Subscription>(`${this.apiUrl}/${id}`, subscription);
    }
  
    // Supprimer une souscription
    deleteSubscription(id: number): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
  
    // Récupérer les souscriptions d'un utilisateur par email
    getSubscriptionsByUserEmail(email: string, page: number = 0, size: number = 10): Observable<Subscription[]> {
      const params = new HttpParams()
        .set('email', email)
        .set('page', page.toString())
        .set('size', size.toString());
      return this.http.get<Subscription[]>(`${this.apiUrl}/user`, { params });
    }
  
    // Récupérer les souscriptions par statut actif ou non
    getSubscriptionsByStatus(active: boolean, page: number = 0, size: number = 10): Observable<Subscription[]> {
      const params = new HttpParams()
        .set('active', active.toString())
        .set('page', page.toString())
        .set('size', size.toString());
      return this.http.get<Subscription[]>(`${this.apiUrl}/status`, { params });
    }
  
    // Récupérer les souscriptions proches de l'expiration
    getExpiringSubscriptions(days: number = 30): Observable<Subscription[]> {
      return this.http.get<Subscription[]>(`${this.apiUrl}/expiring`, {
        params: new HttpParams().set('days', days.toString())
      });
    }
  }