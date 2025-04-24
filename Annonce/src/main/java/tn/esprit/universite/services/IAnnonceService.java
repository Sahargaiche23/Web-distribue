package tn.esprit.universite.services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.universite.entities.Annonce;

import java.util.List;

public interface IAnnonceService {
    Annonce addAnnonce(Annonce annonce, MultipartFile imageFile);
    Annonce getAnnonce(Long id);
    List<Annonce> getAllAnnonces();
    void deleteAnnonce(Long idAnnonce);
    Annonce updateAnnonce(Annonce annonce, MultipartFile imageFile);
    List<Annonce> rechercherParTitre(String titreAnnonce);
}


