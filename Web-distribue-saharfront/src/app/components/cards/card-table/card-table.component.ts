import { Component, OnInit, Input } from '@angular/core';
import { TrainService } from 'src/environments/train.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-card-table',
  templateUrl: './card-table.component.html',
})
export class CardTableComponent implements OnInit {
  @Input() color = 'light';

  trajets: any[] = [];
  selectedAnnonce: any = null;
  showModal: boolean = false;
  modalMode: 'add' | 'edit' = 'add';
  annonceForm: FormGroup;
  selectedFile: File | null = null;

  headers: string[] = [
    'Titre', 'Contenu', 'Type', 'Auteur', 'Date', 'Lieu départ',
    'Heure départ', 'Lieu arrivée', 'Heure arrivée', 'Prix', 'Places', 'Image'
  ];

  constructor(private trainService: TrainService, private fb: FormBuilder) {
    this.annonceForm = this.fb.group({
      titreAnnonce: ['', Validators.required],
      contenuAnnonce: ['', Validators.required],
      typeAnnonce: ['', Validators.required],
      prixTicket: ['', [Validators.required, Validators.min(0)]],
      nombrePlaces: ['', [Validators.required, Validators.min(1)]]
    });
  }

  ngOnInit(): void {
    this.getAllTrajets();
  }

  getAllTrajets(): void {
    this.trainService.getAllAnnonces().subscribe({
      next: (data) => this.trajets = data,
      error: (err) => console.error('Erreur de chargement des trajets:', err)
    });
  }

  openModal(mode: 'add' | 'edit', annonce?: any): void {
    this.modalMode = mode;
    this.selectedAnnonce = mode === 'edit' ? { ...annonce } : null;
    if (mode === 'edit' && annonce) {
      this.annonceForm.patchValue({
        titreAnnonce: annonce.titreAnnonce,
        contenuAnnonce: annonce.contenuAnnonce,
        typeAnnonce: annonce.typeAnnonce,
        prixTicket: annonce.prixTicket,
        nombrePlaces: annonce.nombrePlaces
      });
    } else {
      this.annonceForm.reset();
    }
    this.showModal = true;
  }

  closeModal(refresh: boolean): void {
    this.showModal = false;
    if (refresh) this.getAllTrajets();
  }

  deleteAnnonce(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer cette annonce ?')) {
      this.trainService.deleteAnnonce(id).subscribe({
        next: () => this.getAllTrajets(),
        error: (err) => console.error('Erreur lors de la suppression:', err)
      });
    }
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  submitForm(): void {
    if (this.annonceForm.invalid) {
      return;
    }

    const formData = this.annonceForm.value;
    if (this.modalMode === 'add') {
      // Handle add mode
      if (this.selectedFile) {
        this.trainService.createAnnonce(formData, this.selectedFile, 1) // Assuming '1' is the userId
          .subscribe({
            next: () => {
              this.closeModal(true);
            },
            error: (err) => console.error('Erreur lors de la création:', err)
          });
      } else {
        console.error('File is required for adding an annonce.');
      }
    } else if (this.modalMode === 'edit') {
      // Handle edit mode
      if (this.selectedFile) {
        this.trainService.updateAnnonce(formData, this.selectedFile)
          .subscribe({
            next: () => {
              this.closeModal(true);
            },
            error: (err) => console.error('Erreur lors de la mise à jour:', err)
          });
      } else {
        console.error('File is required for updating an annonce.');
      }
    }
  }
}
