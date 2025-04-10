
package tn.esprit.universite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.universite.entities.Annonce;

import java.util.List;

public interface AnnonceRepository extends JpaRepository<Annonce, Long> {
    List<Annonce> findByTitreAnnonceContainingIgnoreCase(String titreAnnonce);
}
