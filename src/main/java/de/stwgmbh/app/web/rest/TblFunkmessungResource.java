package de.stwgmbh.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.stwgmbh.app.domain.TblFunkmessung;
import de.stwgmbh.app.repository.TblFunkmessungRepository;
import de.stwgmbh.app.web.rest.errors.BadRequestAlertException;
import de.stwgmbh.app.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TblFunkmessung.
 */
@RestController
@RequestMapping("/api")
public class TblFunkmessungResource {

    private final Logger log = LoggerFactory.getLogger(TblFunkmessungResource.class);

    private static final String ENTITY_NAME = "tblFunkmessung";

    private final TblFunkmessungRepository tblFunkmessungRepository;

    public TblFunkmessungResource(TblFunkmessungRepository tblFunkmessungRepository) {
        this.tblFunkmessungRepository = tblFunkmessungRepository;
    }

    /**
     * POST  /tbl-funkmessungs : Create a new tblFunkmessung.
     *
     * @param tblFunkmessung the tblFunkmessung to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tblFunkmessung, or with status 400 (Bad Request) if the tblFunkmessung has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tbl-funkmessungs")
    @Timed
    public ResponseEntity<TblFunkmessung> createTblFunkmessung(@RequestBody TblFunkmessung tblFunkmessung) throws URISyntaxException {
        log.debug("REST request to save TblFunkmessung : {}", tblFunkmessung);
        if (tblFunkmessung.getId() != null) {
            throw new BadRequestAlertException("A new tblFunkmessung cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TblFunkmessung result = tblFunkmessungRepository.save(tblFunkmessung);
        return ResponseEntity.created(new URI("/api/tbl-funkmessungs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tbl-funkmessungs : Updates an existing tblFunkmessung.
     *
     * @param tblFunkmessung the tblFunkmessung to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tblFunkmessung,
     * or with status 400 (Bad Request) if the tblFunkmessung is not valid,
     * or with status 500 (Internal Server Error) if the tblFunkmessung couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tbl-funkmessungs")
    @Timed
    public ResponseEntity<TblFunkmessung> updateTblFunkmessung(@RequestBody TblFunkmessung tblFunkmessung) throws URISyntaxException {
        log.debug("REST request to update TblFunkmessung : {}", tblFunkmessung);
        if (tblFunkmessung.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TblFunkmessung result = tblFunkmessungRepository.save(tblFunkmessung);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tblFunkmessung.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tbl-funkmessungs : get all the tblFunkmessungs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of tblFunkmessungs in body
     */
    @GetMapping("/tbl-funkmessungs")
    @Timed
    public List<TblFunkmessung> getAllTblFunkmessungs() {
        log.debug("REST request to get all TblFunkmessungs");
        return tblFunkmessungRepository.findAll();
    }

    /**
     * GET  /tbl-funkmessungs/:id : get the "id" tblFunkmessung.
     *
     * @param id the id of the tblFunkmessung to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tblFunkmessung, or with status 404 (Not Found)
     */
    @GetMapping("/tbl-funkmessungs/{id}")
    @Timed
    public ResponseEntity<TblFunkmessung> getTblFunkmessung(@PathVariable Long id) {
        log.debug("REST request to get TblFunkmessung : {}", id);
        Optional<TblFunkmessung> tblFunkmessung = tblFunkmessungRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tblFunkmessung);
    }

    /**
     * DELETE  /tbl-funkmessungs/:id : delete the "id" tblFunkmessung.
     *
     * @param id the id of the tblFunkmessung to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tbl-funkmessungs/{id}")
    @Timed
    public ResponseEntity<Void> deleteTblFunkmessung(@PathVariable Long id) {
        log.debug("REST request to delete TblFunkmessung : {}", id);

        tblFunkmessungRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
