package de.stwgmbh.app.repository;

import de.stwgmbh.app.domain.TblTermine;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the TblTermine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TblTermineRepository extends JpaRepository<TblTermine, Long> {

    @Query("select tbl_termine from TblTermine tbl_termine where tbl_termine.user.login = ?#{principal.username}")
    List<TblTermine> findByUserIsCurrentUser();

}
