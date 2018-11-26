package de.stwgmbh.app.repository;

import de.stwgmbh.app.domain.TndSiemens;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TndSiemens entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TndSiemensRepository extends JpaRepository<TndSiemens, Long> {

}
