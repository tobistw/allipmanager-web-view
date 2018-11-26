package de.stwgmbh.app.repository;

import de.stwgmbh.app.domain.Arbeitsbeleg;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Arbeitsbeleg entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArbeitsbelegRepository extends JpaRepository<Arbeitsbeleg, Long> {

    @Query("select arbeitsbeleg from Arbeitsbeleg arbeitsbeleg where arbeitsbeleg.user.login = ?#{principal.username}")
    List<Arbeitsbeleg> findByUserIsCurrentUser();

}
