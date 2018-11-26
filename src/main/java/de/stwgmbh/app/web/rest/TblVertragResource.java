package de.stwgmbh.app.web.rest;

import com.codahale.metrics.annotation.Timed;
import de.stwgmbh.app.domain.TblVertrag;
import de.stwgmbh.app.repository.TblVertragRepository;
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
 * REST controller for managing TblVertrag.
 */
@RestController
@RequestMapping("/api")
public class TblVertragResource {

    private final Logger log = LoggerFactory.getLogger(TblVertragResource.class);

    private static final String ENTITY_NAME = "tblVertrag";

    private final TblVertragRepository tblVertragRepository;

    public TblVertragResource(TblVertragRepository tblVertragRepository) {
        this.tblVertragRepository = tblVertragRepository;
    }

    /**
     * POST  /tbl-vertrags : Create a new tblVertrag.
     *
     * @param tblVertrag the tblVertrag to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tblVertrag, or with status 400 (Bad Request) if the tblVertrag has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tbl-vertrags")
    @Timed
    public ResponseEntity<TblVertrag> createTblVertrag(@RequestBody TblVertrag tblVertrag) throws URISyntaxException {
        log.debug("REST request to save TblVertrag : {}", tblVertrag);
        if (tblVertrag.getId() != null) {
            throw new BadRequestAlertException("A new tblVertrag cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TblVertrag result = tblVertragRepository.save(tblVertrag);
        return ResponseEntity.created(new URI("/api/tbl-vertrags/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tbl-vertrags : Updates an existing tblVertrag.
     *
     * @param tblVertrag the tblVertrag to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tblVertrag,
     * or with status 400 (Bad Request) if the tblVertrag is not valid,
     * or with status 500 (Internal Server Error) if the tblVertrag couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tbl-vertrags")
    @Timed
    public ResponseEntity<TblVertrag> updateTblVertrag(@RequestBody TblVertrag tblVertrag) throws URISyntaxException {
        log.debug("REST request to update TblVertrag : {}", tblVertrag);
        if (tblVertrag.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TblVertrag result = tblVertragRepository.save(tblVertrag);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tblVertrag.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tbl-vertrags : get all the tblVertrags.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tblVertrags in body
     */
    @GetMapping("/tbl-vertrags")
    @Timed
    public ResponseEntity<List<TblVertrag>> getAllTblVertrags(Pageable pageable) {
        log.debug("REST request to get a page of TblVertrags");
        Page<TblVertrag> page = tblVertragRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tbl-vertrags");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /tbl-vertrags/:id : get the "id" tblVertrag.
     *
     * @param id the id of the tblVertrag to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tblVertrag, or with status 404 (Not Found)
     */
    @GetMapping("/tbl-vertrags/{id}")
    @Timed
    public ResponseEntity<TblVertrag> getTblVertrag(@PathVariable Long id) {
        log.debug("REST request to get TblVertrag : {}", id);
        Optional<TblVertrag> tblVertrag = tblVertragRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tblVertrag);
    }

    /**
     * DELETE  /tbl-vertrags/:id : delete the "id" tblVertrag.
     *
     * @param id the id of the tblVertrag to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tbl-vertrags/{id}")
    @Timed
    public ResponseEntity<Void> deleteTblVertrag(@PathVariable Long id) {
        log.debug("REST request to delete TblVertrag : {}", id);

        tblVertragRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
