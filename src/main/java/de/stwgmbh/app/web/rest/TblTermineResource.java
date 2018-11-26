package de.stwgmbh.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.stwgmbh.app.domain.TblTermine;
import de.stwgmbh.app.repository.TblTermineRepository;
import de.stwgmbh.app.web.rest.errors.BadRequestAlertException;
import de.stwgmbh.app.web.rest.util.HeaderUtil;
import de.stwgmbh.app.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TblTermine.
 */
@RestController
@RequestMapping("/api")
public class TblTermineResource {

    private final Logger log = LoggerFactory.getLogger(TblTermineResource.class);

    private static final String ENTITY_NAME = "tblTermine";

    private final TblTermineRepository tblTermineRepository;

    public TblTermineResource(TblTermineRepository tblTermineRepository) {
        this.tblTermineRepository = tblTermineRepository;
    }

    /**
     * POST  /tbl-termines : Create a new tblTermine.
     *
     * @param tblTermine the tblTermine to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tblTermine, or with status 400 (Bad Request) if the tblTermine has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tbl-termines")
    @Timed
    public ResponseEntity<TblTermine> createTblTermine(@RequestBody TblTermine tblTermine) throws URISyntaxException {
        log.debug("REST request to save TblTermine : {}", tblTermine);
        if (tblTermine.getId() != null) {
            throw new BadRequestAlertException("A new tblTermine cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TblTermine result = tblTermineRepository.save(tblTermine);
        return ResponseEntity.created(new URI("/api/tbl-termines/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tbl-termines : Updates an existing tblTermine.
     *
     * @param tblTermine the tblTermine to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tblTermine,
     * or with status 400 (Bad Request) if the tblTermine is not valid,
     * or with status 500 (Internal Server Error) if the tblTermine couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tbl-termines")
    @Timed
    public ResponseEntity<TblTermine> updateTblTermine(@RequestBody TblTermine tblTermine) throws URISyntaxException {
        log.debug("REST request to update TblTermine : {}", tblTermine);
        if (tblTermine.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TblTermine result = tblTermineRepository.save(tblTermine);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tblTermine.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tbl-termines : get all the tblTermines.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tblTermines in body
     */
    @GetMapping("/tbl-termines")
    @Timed
    public ResponseEntity<List<TblTermine>> getAllTblTermines(Pageable pageable) {
        log.debug("REST request to get a page of TblTermines");
        Page<TblTermine> page = tblTermineRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tbl-termines");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tbl-termines/:id : get the "id" tblTermine.
     *
     * @param id the id of the tblTermine to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tblTermine, or with status 404 (Not Found)
     */
    @GetMapping("/tbl-termines/{id}")
    @Timed
    public ResponseEntity<TblTermine> getTblTermine(@PathVariable Long id) {
        log.debug("REST request to get TblTermine : {}", id);
        Optional<TblTermine> tblTermine = tblTermineRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tblTermine);
    }

    /**
     * DELETE  /tbl-termines/:id : delete the "id" tblTermine.
     *
     * @param id the id of the tblTermine to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tbl-termines/{id}")
    @Timed
    public ResponseEntity<Void> deleteTblTermine(@PathVariable Long id) {
        log.debug("REST request to delete TblTermine : {}", id);

        tblTermineRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
