package de.stwgmbh.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.stwgmbh.app.domain.Arbeitsbeleg;
import de.stwgmbh.app.repository.ArbeitsbelegRepository;
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
 * REST controller for managing Arbeitsbeleg.
 */
@RestController
@RequestMapping("/api")
public class ArbeitsbelegResource {

    private final Logger log = LoggerFactory.getLogger(ArbeitsbelegResource.class);

    private static final String ENTITY_NAME = "arbeitsbeleg";

    private final ArbeitsbelegRepository arbeitsbelegRepository;

    public ArbeitsbelegResource(ArbeitsbelegRepository arbeitsbelegRepository) {
        this.arbeitsbelegRepository = arbeitsbelegRepository;
    }

    /**
     * POST  /arbeitsbelegs : Create a new arbeitsbeleg.
     *
     * @param arbeitsbeleg the arbeitsbeleg to create
     * @return the ResponseEntity with status 201 (Created) and with body the new arbeitsbeleg, or with status 400 (Bad Request) if the arbeitsbeleg has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/arbeitsbelegs")
    @Timed
    public ResponseEntity<Arbeitsbeleg> createArbeitsbeleg(@RequestBody Arbeitsbeleg arbeitsbeleg) throws URISyntaxException {
        log.debug("REST request to save Arbeitsbeleg : {}", arbeitsbeleg);
        if (arbeitsbeleg.getId() != null) {
            throw new BadRequestAlertException("A new arbeitsbeleg cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Arbeitsbeleg result = arbeitsbelegRepository.save(arbeitsbeleg);
        return ResponseEntity.created(new URI("/api/arbeitsbelegs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /arbeitsbelegs : Updates an existing arbeitsbeleg.
     *
     * @param arbeitsbeleg the arbeitsbeleg to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated arbeitsbeleg,
     * or with status 400 (Bad Request) if the arbeitsbeleg is not valid,
     * or with status 500 (Internal Server Error) if the arbeitsbeleg couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/arbeitsbelegs")
    @Timed
    public ResponseEntity<Arbeitsbeleg> updateArbeitsbeleg(@RequestBody Arbeitsbeleg arbeitsbeleg) throws URISyntaxException {
        log.debug("REST request to update Arbeitsbeleg : {}", arbeitsbeleg);
        if (arbeitsbeleg.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Arbeitsbeleg result = arbeitsbelegRepository.save(arbeitsbeleg);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, arbeitsbeleg.getId().toString()))
            .body(result);
    }

    /**
     * GET  /arbeitsbelegs : get all the arbeitsbelegs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of arbeitsbelegs in body
     */
    @GetMapping("/arbeitsbelegs")
    @Timed
    public List<Arbeitsbeleg> getAllArbeitsbelegs() {
        log.debug("REST request to get all Arbeitsbelegs");
        return arbeitsbelegRepository.findAll();
    }

    /**
     * GET  /arbeitsbelegs/:id : get the "id" arbeitsbeleg.
     *
     * @param id the id of the arbeitsbeleg to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the arbeitsbeleg, or with status 404 (Not Found)
     */
    @GetMapping("/arbeitsbelegs/{id}")
    @Timed
    public ResponseEntity<Arbeitsbeleg> getArbeitsbeleg(@PathVariable Long id) {
        log.debug("REST request to get Arbeitsbeleg : {}", id);
        Optional<Arbeitsbeleg> arbeitsbeleg = arbeitsbelegRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(arbeitsbeleg);
    }

    /**
     * DELETE  /arbeitsbelegs/:id : delete the "id" arbeitsbeleg.
     *
     * @param id the id of the arbeitsbeleg to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/arbeitsbelegs/{id}")
    @Timed
    public ResponseEntity<Void> deleteArbeitsbeleg(@PathVariable Long id) {
        log.debug("REST request to delete Arbeitsbeleg : {}", id);

        arbeitsbelegRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
