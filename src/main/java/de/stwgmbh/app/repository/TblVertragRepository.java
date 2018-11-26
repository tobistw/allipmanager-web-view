package de.stwgmbh.app.repository;

import de.stwgmbh.app.domain.TblVertrag;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TblVertrag entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TblVertragRepository extends JpaRepository<TblVertrag, Long> {

}
