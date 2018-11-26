package de.stwgmbh.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.stwgmbh.app.domain.Abnahmeprotokoll;
import de.stwgmbh.app.repository.AbnahmeprotokollRepository;
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
 * REST controller for managing Abnahmeprotokoll.
 */
@RestController
@RequestMapping("/api")
public class AbnahmeprotokollResource {

    private final Logger log = LoggerFactory.getLogger(AbnahmeprotokollResource.class);

    private static final String ENTITY_NAME = "abnahmeprotokoll";

    private final AbnahmeprotokollRepository abnahmeprotokollRepository;

    public AbnahmeprotokollResource(AbnahmeprotokollRepository abnahmeprotokollRepository) {
        this.abnahmeprotokollRepository = abnahmeprotokollRepository;
    }

    /**
     * POST  /abnahmeprotokolls : Create a new abnahmeprotokoll.
     *
     * @param abnahmeprotokoll the abnahmeprotokoll to create
     * @return the ResponseEntity with status 201 (Created) and with body the new abnahmeprotokoll, or with status 400 (Bad Request) if the abnahmeprotokoll has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/abnahmeprotokolls")
    @Timed
    public ResponseEntity<Abnahmeprotokoll> createAbnahmeprotokoll(@RequestBody Abnahmeprotokoll abnahmeprotokoll) throws URISyntaxException {
        log.debug("REST request to save Abnahmeprotokoll : {}", abnahmeprotokoll);
        if (abnahmeprotokoll.getId() != null) {
            throw new BadRequestAlertException("A new abnahmeprotokoll cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Abnahmeprotokoll result = abnahmeprotokollRepository.save(abnahmeprotokoll);
        return ResponseEntity.created(new URI("/api/abnahmeprotokolls/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /abnahmeprotokolls : Updates an existing abnahmeprotokoll.
     *
     * @param abnahmeprotokoll the abnahmeprotokoll to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated abnahmeprotokoll,
     * or with status 400 (Bad Request) if the abnahmeprotokoll is not valid,
     * or with status 500 (Internal Server Error) if the abnahmeprotokoll couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/abnahmeprotokolls")
    @Timed
    public ResponseEntity<Abnahmeprotokoll> updateAbnahmeprotokoll(@RequestBody Abnahmeprotokoll abnahmeprotokoll) throws URISyntaxException {
        log.debug("REST request to update Abnahmeprotokoll : {}", abnahmeprotokoll);
        if (abnahmeprotokoll.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Abnahmeprotokoll result = abnahmeprotokollRepository.save(abnahmeprotokoll);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, abnahmeprotokoll.getId().toString()))
            .body(result);
    }

    /**
     * GET  /abnahmeprotokolls : get all the abnahmeprotokolls.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of abnahmeprotokolls in body
     */
    @GetMapping("/abnahmeprotokolls")
    @Timed
    public List<Abnahmeprotokoll> getAllAbnahmeprotokolls() {
        log.debug("REST request to get all Abnahmeprotokolls");
        return abnahmeprotokollRepository.findAll();
    }

    /**
     * GET  /abnahmeprotokolls/:id : get the "id" abnahmeprotokoll.
     *
     * @param id the id of the abnahmeprotokoll to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the abnahmeprotokoll, or with status 404 (Not Found)
     */
    @GetMapping("/abnahmeprotokolls/{id}")
    @Timed
    public ResponseEntity<Abnahmeprotokoll> getAbnahmeprotokoll(@PathVariable Long id) {
        log.debug("REST request to get Abnahmeprotokoll : {}", id);
        Optional<Abnahmeprotokoll> abnahmeprotokoll = abnahmeprotokollRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(abnahmeprotokoll);
    }

    /**
     * DELETE  /abnahmeprotokolls/:id : delete the "id" abnahmeprotokoll.
     *
     * @param id the id of the abnahmeprotokoll to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/abnahmeprotokolls/{id}")
    @Timed
    public ResponseEntity<Void> deleteAbnahmeprotokoll(@PathVariable Long id) {
        log.debug("REST request to delete Abnahmeprotokoll : {}", id);

        abnahmeprotokollRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
