package de.stwgmbh.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.stwgmbh.app.domain.TndSiemens;
import de.stwgmbh.app.repository.TndSiemensRepository;
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
 * REST controller for managing TndSiemens.
 */
@RestController
@RequestMapping("/api")
public class TndSiemensResource {

    private final Logger log = LoggerFactory.getLogger(TndSiemensResource.class);

    private static final String ENTITY_NAME = "tndSiemens";

    private final TndSiemensRepository tndSiemensRepository;

    public TndSiemensResource(TndSiemensRepository tndSiemensRepository) {
        this.tndSiemensRepository = tndSiemensRepository;
    }

    /**
     * POST  /tnd-siemens : Create a new tndSiemens.
     *
     * @param tndSiemens the tndSiemens to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tndSiemens, or with status 400 (Bad Request) if the tndSiemens has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tnd-siemens")
    @Timed
    public ResponseEntity<TndSiemens> createTndSiemens(@RequestBody TndSiemens tndSiemens) throws URISyntaxException {
        log.debug("REST request to save TndSiemens : {}", tndSiemens);
        if (tndSiemens.getId() != null) {
            throw new BadRequestAlertException("A new tndSiemens cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TndSiemens result = tndSiemensRepository.save(tndSiemens);
        return ResponseEntity.created(new URI("/api/tnd-siemens/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tnd-siemens : Updates an existing tndSiemens.
     *
     * @param tndSiemens the tndSiemens to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tndSiemens,
     * or with status 400 (Bad Request) if the tndSiemens is not valid,
     * or with status 500 (Internal Server Error) if the tndSiemens couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tnd-siemens")
    @Timed
    public ResponseEntity<TndSiemens> updateTndSiemens(@RequestBody TndSiemens tndSiemens) throws URISyntaxException {
        log.debug("REST request to update TndSiemens : {}", tndSiemens);
        if (tndSiemens.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TndSiemens result = tndSiemensRepository.save(tndSiemens);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tndSiemens.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tnd-siemens : get all the tndSiemens.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tndSiemens in body
     */
    @GetMapping("/tnd-siemens")
    @Timed
    public ResponseEntity<List<TndSiemens>> getAllTndSiemens(Pageable pageable) {
        log.debug("REST request to get a page of TndSiemens");
        Page<TndSiemens> page = tndSiemensRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tnd-siemens");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tnd-siemens/:id : get the "id" tndSiemens.
     *
     * @param id the id of the tndSiemens to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tndSiemens, or with status 404 (Not Found)
     */
    @GetMapping("/tnd-siemens/{id}")
    @Timed
    public ResponseEntity<TndSiemens> getTndSiemens(@PathVariable Long id) {
        log.debug("REST request to get TndSiemens : {}", id);
        Optional<TndSiemens> tndSiemens = tndSiemensRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tndSiemens);
    }

    /**
     * DELETE  /tnd-siemens/:id : delete the "id" tndSiemens.
     *
     * @param id the id of the tndSiemens to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tnd-siemens/{id}")
    @Timed
    public ResponseEntity<Void> deleteTndSiemens(@PathVariable Long id) {
        log.debug("REST request to delete TndSiemens : {}", id);

        tndSiemensRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
