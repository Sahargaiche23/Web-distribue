import { Component, OnInit } from "@angular/core";
import { TrainService } from "src/environments/train.service";

@Component({
  selector: "app-horraire",
  templateUrl: "./horraire.component.html",
})
export class HorraireComponent implements OnInit {
  annonces: any[] = [];
  selectedAnnonce: any = null;
  reservationData: { nombrePlaces: number } = { nombrePlaces: 1 };
  totalPrice: number = 0;

  constructor(private trainService: TrainService) {}

  ngOnInit(): void {
    this.getAllAnnonces();
  }

  getAllAnnonces(): void {
    this.trainService.getAllAnnonces().subscribe(
      (data) => {
        this.annonces = data;
      },
      (error) => {
        console.error("Erreur lors de la récupération des annonces :", error);
      }
    );
  }

  openReservationModal(annonce: any): void {
    console.log("Modal ouvert pour :", annonce);
    this.selectedAnnonce = annonce;
    this.reservationData = { nombrePlaces: annonce.nombrePlaces || 1 };
    this.calculateTotalPrice();
  }

  closeReservationModal(): void {
    this.selectedAnnonce = null;
  }

  calculateTotalPrice(): void {
    this.totalPrice = this.selectedAnnonce?.prixTicket * this.reservationData.nombrePlaces;
  }

  downloadTicket(): void {
    const ticketInfo = `Annonce - Votre ticket\nPrix: ${this.totalPrice}\nAnnonce: ${this.selectedAnnonce.titreAnnonce}\nDate: ${new Date().toLocaleString()}`;
    const element = document.createElement('a');
    element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(ticketInfo));
    element.setAttribute('download', 'ticket.txt');
    document.body.appendChild(element);
    element.click();
    document.body.removeChild(element);
  }

  reserve(): void {
    console.log('Réservation effectuée pour', this.reservationData.nombrePlaces, 'places');
    this.downloadTicket();
    this.closeReservationModal();
  }
}
