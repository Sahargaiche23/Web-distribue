import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'src/app/Subscription';
import { AbonementService } from 'src/environments/abonemet.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-abonement',
  templateUrl: './abonement.component.html'
})
export class AbonementComponent implements OnInit {
  abonnementForm: FormGroup;
  abonnementId: number | null = null;
  abonnements: Subscription[] = [];

  constructor(
    private fb: FormBuilder,
    private abonementService: AbonementService
  ) {
    this.abonnementForm = this.fb.group({
      userEmail: ['', [Validators.required, Validators.email]],
      plan: ['', Validators.required],
      active: [false],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this. getAllAbonnement();
  }

  // Call the correct service method to get all annonces
  getAllAbonnement(): void {
    this.abonementService.getAllSubscriptions().subscribe(  // Now correctly calling getAllAnnonces from TrainService
      (data) => {
        this. abonnements = data;  // Populate the annonces array with the response data
        console.log(this.abonnements); // Log the abonnements to the console
      },
      (error) => {
        console.error("Error fetching annonces:", error);
      }
    );
  }
  createAbonnement(): void {
    if (this.abonnementForm.valid) {
      const newSub: Subscription = this.abonnementForm.value;
      this.abonementService.createSubscription(newSub).subscribe(() => {
        Swal.fire('Succès', 'Abonnement créé avec succès.', 'success');
        this.abonnementForm.reset();
        this. getAllAbonnement();
      });
    }
  }

  updateAbonnement(): void {
    if (this.abonnementForm.valid && this.abonnementId) {
      const updatedSub: Subscription = this.abonnementForm.value;
      this.abonementService.updateSubscription(this.abonnementId, updatedSub).subscribe(() => {
        Swal.fire('Succès', 'Abonnement mis à jour.', 'success');
        this.abonnementForm.reset();
        this.abonnementId = null;
        this. getAllAbonnement();
      });
    }
  }

  editAbonnement(abonnement: Subscription): void {
    this.abonnementId = abonnement.id;
    this.abonnementForm.patchValue(abonnement);
  }

  deleteAbonnement(id: number): void {
    Swal.fire({
      title: 'Êtes-vous sûr ?',
      text: 'Cette action est irréversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Oui, supprimer'
    }).then((result) => {
      if (result.isConfirmed) {
        this.abonementService.deleteSubscription(id).subscribe(() => {
          Swal.fire('Supprimé', 'Abonnement supprimé.', 'success');
          this. getAllAbonnement();
        });
      }
    });
  }
}
