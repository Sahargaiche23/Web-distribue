import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { ReclamationService } from "src/environments/reclamation.service";
import Swal from "sweetalert2";

@Component({
  selector: "app-recl",
  templateUrl: "./reclamation.component.html",
})
export class ReclamationComponent implements OnInit {
  form: FormGroup;
  reclamations: any[] = [];
  currentReclamationId: number;

  constructor(
    private router: Router,
    private service: ReclamationService,
    private fb: FormBuilder
  ) {
    this.form = this.fb.group({
      lieuDepart: ['', Validators.required],
      lieuArrivee: ['', Validators.required],
      heureDepart: ['', Validators.required],
      heureArrivee: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.loadReclamations();
  }

  loadReclamations() {
    this.service.getAllReclamations().subscribe((data) => {
      this.reclamations = data;
    });
  }

  onSubmit() {
    if (this.form.valid) {
      const data: any = {
        lieuDepart: this.form.get('lieuDepart').value,
        lieuArrivee: this.form.get('lieuArrivee').value,
        heureDepart: this.form.get('heureDepart').value,
        heureArrivee: this.form.get('heureArrivee').value,
        description: this.form.get('description').value
      };

      if (this.currentReclamationId) {
        // Update reclamation
        this.service.uupdateReclamation(this.currentReclamationId, data).subscribe(
          () => {
            Swal.fire({
              icon: 'success',
              title: 'Succès!',
              text: 'Votre réclamation a été mise à jour!'
            }).then(() => {
              this.loadReclamations();  // Reload the list
            });
          },
          (error) => {
            console.error('Error updating reclamation:', error);
          }
        );
      } else {
        // Add new reclamation
        this.service.createReclamation(data).subscribe(
          () => {
            Swal.fire({
              icon: 'success',
              title: 'Succès!',
              text: 'Votre réclamation a été envoyée avec succès!'
            }).then(() => {
              this.loadReclamations();  // Reload the list
            });
          },
          (error) => {
            console.error('Error creating reclamation:', error);
          }
        );
      }
    }
  }

  editReclamation(id: number) {
    this.currentReclamationId = id;
    const reclamation = this.reclamations.find(r => r.idReclamation === id);
    if (reclamation) {
      this.form.patchValue({
        lieuDepart: reclamation.lieuDepart,
        lieuArrivee: reclamation.lieuArrivee,
        heureDepart: reclamation.heureDepart,
        heureArrivee: reclamation.heureArrivee,
        description: reclamation.description
      });
    }
  }

  deleteReclamation(id: number) {
    Swal.fire({
      title: 'Êtes-vous sûr?',
      text: "Vous ne pourrez pas revenir en arrière!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Oui, supprimer!',
      cancelButtonText: 'Annuler'
    }).then((result) => {
      if (result.isConfirmed) {
        this.service.deleteReclamation(id).subscribe(
          () => {
            Swal.fire('Supprimé!', 'Votre réclamation a été supprimée.', 'success');
            this.loadReclamations();  // Reload the list
          },
          (error) => {
            console.error('Error deleting reclamation:', error);
            Swal.fire('Erreur!', 'Une erreur est survenue.', 'error');
          }
        );
      }
    });
  }
}
