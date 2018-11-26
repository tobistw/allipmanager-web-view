package de.stwgmbh.app.repository;

import de.stwgmbh.app.domain.Abnahmeprotokoll;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Abnahmeprotokoll entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AbnahmeprotokollRepository extends JpaRepository<Abnahmeprotokoll, Long> {

    @Query("select abnahmeprotokoll from Abnahmeprotokoll abnahmeprotokoll where abnahmeprotokoll.user.login = ?#{principal.username}")
    List<Abnahmeprotokoll> findByUserIsCurrentUser();

}
