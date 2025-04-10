package tn.esprit.universite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.universite.entities.Annonce;
import tn.esprit.universite.repositories.AnnonceRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnnonceService implements IAnnonceService {

    private final AnnonceRepository annonceRepository;
    private final FileStorageService fileStorageService;

    @Autowired
    public AnnonceService(AnnonceRepository annonceRepository,
                              FileStorageService fileStorageService) {
        this.annonceRepository = annonceRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public Annonce addAnnonce(Annonce annonce, MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            String image = fileStorageService.storeFile(imageFile);
            annonce.setImage(image);
        }
        annonce.setDatePublication(LocalDateTime.now());
        return annonceRepository.save(annonce);
    }

    @Override
    public Annonce getAnnonce(Long id) {
        return annonceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Annonce> getAllAnnonces() {
        return annonceRepository.findAll();
    }

    @Override
    public void deleteAnnonce(Long idAnnonce) {
        annonceRepository.deleteById(idAnnonce);
    }

    @Override
    public Annonce updateAnnonce(Annonce annonce, MultipartFile imageFile) {
        if (imageFile != null && !imageFile.isEmpty()) {
            String image = fileStorageService.storeFile(imageFile);
            annonce.setImage(image);
        }
        return annonceRepository.save(annonce);
    }

    @Override
    public List<Annonce> rechercherParTitre(String titreAnnonce) {
        return annonceRepository.findByTitreAnnonceContainingIgnoreCase(titreAnnonce);
    }
}
