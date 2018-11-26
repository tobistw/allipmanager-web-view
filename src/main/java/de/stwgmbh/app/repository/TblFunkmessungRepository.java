package de.stwgmbh.app.repository;

import de.stwgmbh.app.domain.TblFunkmessung;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TblFunkmessung entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TblFunkmessungRepository extends JpaRepository<TblFunkmessung, Long> {

}
