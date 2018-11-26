package de.stwgmbh.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.stwgmbh.app.domain.TblAsp;
import de.stwgmbh.app.repository.TblAspRepository;
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
 * REST controller for managing TblAsp.
 */
@RestController
@RequestMapping("/api")
public class TblAspResource {

    private final Logger log = LoggerFactory.getLogger(TblAspResource.class);

    private static final String ENTITY_NAME = "tblAsp";

    private final TblAspRepository tblAspRepository;

    public TblAspResource(TblAspRepository tblAspRepository) {
        this.tblAspRepository = tblAspRepository;
    }

    /**
     * POST  /tbl-asps : Create a new tblAsp.
     *
     * @param tblAsp the tblAsp to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tblAsp, or with status 400 (Bad Request) if the tblAsp has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tbl-asps")
    @Timed
    public ResponseEntity<TblAsp> createTblAsp(@RequestBody TblAsp tblAsp) throws URISyntaxException {
        log.debug("REST request to save TblAsp : {}", tblAsp);
        if (tblAsp.getId() != null) {
            throw new BadRequestAlertException("A new tblAsp cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TblAsp result = tblAspRepository.save(tblAsp);
        return ResponseEntity.created(new URI("/api/tbl-asps/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tbl-asps : Updates an existing tblAsp.
     *
     * @param tblAsp the tblAsp to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tblAsp,
     * or with status 400 (Bad Request) if the tblAsp is not valid,
     * or with status 500 (Internal Server Error) if the tblAsp couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tbl-asps")
    @Timed
    public ResponseEntity<TblAsp> updateTblAsp(@RequestBody TblAsp tblAsp) throws URISyntaxException {
        log.debug("REST request to update TblAsp : {}", tblAsp);
        if (tblAsp.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TblAsp result = tblAspRepository.save(tblAsp);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tblAsp.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tbl-asps : get all the tblAsps.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tblAsps in body
     */
    @GetMapping("/tbl-asps")
    @Timed
    public ResponseEntity<List<TblAsp>> getAllTblAsps(Pageable pageable) {
        log.debug("REST request to get a page of TblAsps");
        Page<TblAsp> page = tblAspRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tbl-asps");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tbl-asps/:id : get the "id" tblAsp.
     *
     * @param id the id of the tblAsp to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tblAsp, or with status 404 (Not Found)
     */
    @GetMapping("/tbl-asps/{id}")
    @Timed
    public ResponseEntity<TblAsp> getTblAsp(@PathVariable Long id) {
        log.debug("REST request to get TblAsp : {}", id);
        Optional<TblAsp> tblAsp = tblAspRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tblAsp);
    }

    /**
     * DELETE  /tbl-asps/:id : delete the "id" tblAsp.
     *
     * @param id the id of the tblAsp to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tbl-asps/{id}")
    @Timed
    public ResponseEntity<Void> deleteTblAsp(@PathVariable Long id) {
        log.debug("REST request to delete TblAsp : {}", id);

        tblAspRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
