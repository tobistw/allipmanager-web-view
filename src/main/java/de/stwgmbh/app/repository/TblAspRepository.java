package de.stwgmbh.app.repository;

import de.stwgmbh.app.domain.TblAsp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TblAsp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TblAspRepository extends JpaRepository<TblAsp, Long> {

}
