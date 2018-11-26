package de.stwgmbh.app.web.rest;

import de.stwgmbh.app.AllipmanagerwebviewApp;

import de.stwgmbh.app.domain.TblAsp;
import de.stwgmbh.app.repository.TblAspRepository;
import de.stwgmbh.app.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


import static de.stwgmbh.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TblAspResource REST controller.
 *
 * @see TblAspResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AllipmanagerwebviewApp.class)
public class TblAspResourceIntTest {

    private static final Integer DEFAULT_ASP_ID = 1;
    private static final Integer UPDATED_ASP_ID = 2;

    private static final String DEFAULT_ASP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ASP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ASP_ROLLE = "AAAAAAAAAA";
    private static final String UPDATED_ASP_ROLLE = "BBBBBBBBBB";

    private static final String DEFAULT_ASP_TEL_1 = "AAAAAAAAAA";
    private static final String UPDATED_ASP_TEL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ASP_MOBIL_1 = "AAAAAAAAAA";
    private static final String UPDATED_ASP_MOBIL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ASP_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_ASP_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ASP_OBJEKT_NR_REF = "AAAAAAAAAA";
    private static final String UPDATED_ASP_OBJEKT_NR_REF = "BBBBBBBBBB";

    private static final String DEFAULT_ASP_BEMERKUNG = "AAAAAAAAAA";
    private static final String UPDATED_ASP_BEMERKUNG = "BBBBBBBBBB";

    @Autowired
    private TblAspRepository tblAspRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTblAspMockMvc;

    private TblAsp tblAsp;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TblAspResource tblAspResource = new TblAspResource(tblAspRepository);
        this.restTblAspMockMvc = MockMvcBuilders.standaloneSetup(tblAspResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TblAsp createEntity(EntityManager em) {
        TblAsp tblAsp = new TblAsp()
            .aspId(DEFAULT_ASP_ID)
            .aspName(DEFAULT_ASP_NAME)
            .aspRolle(DEFAULT_ASP_ROLLE)
            .aspTel1(DEFAULT_ASP_TEL_1)
            .aspMobil1(DEFAULT_ASP_MOBIL_1)
            .aspEmail(DEFAULT_ASP_EMAIL)
            .aspObjektNrRef(DEFAULT_ASP_OBJEKT_NR_REF)
            .aspBemerkung(DEFAULT_ASP_BEMERKUNG);
        return tblAsp;
    }

    @Before
    public void initTest() {
        tblAsp = createEntity(em);
    }

    @Test
    @Transactional
    public void createTblAsp() throws Exception {
        int databaseSizeBeforeCreate = tblAspRepository.findAll().size();

        // Create the TblAsp
        restTblAspMockMvc.perform(post("/api/tbl-asps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tblAsp)))
            .andExpect(status().isCreated());

        // Validate the TblAsp in the database
        List<TblAsp> tblAspList = tblAspRepository.findAll();
        assertThat(tblAspList).hasSize(databaseSizeBeforeCreate + 1);
        TblAsp testTblAsp = tblAspList.get(tblAspList.size() - 1);
        assertThat(testTblAsp.getAspId()).isEqualTo(DEFAULT_ASP_ID);
        assertThat(testTblAsp.getAspName()).isEqualTo(DEFAULT_ASP_NAME);
        assertThat(testTblAsp.getAspRolle()).isEqualTo(DEFAULT_ASP_ROLLE);
        assertThat(testTblAsp.getAspTel1()).isEqualTo(DEFAULT_ASP_TEL_1);
        assertThat(testTblAsp.getAspMobil1()).isEqualTo(DEFAULT_ASP_MOBIL_1);
        assertThat(testTblAsp.getAspEmail()).isEqualTo(DEFAULT_ASP_EMAIL);
        assertThat(testTblAsp.getAspObjektNrRef()).isEqualTo(DEFAULT_ASP_OBJEKT_NR_REF);
        assertThat(testTblAsp.getAspBemerkung()).isEqualTo(DEFAULT_ASP_BEMERKUNG);
    }

    @Test
    @Transactional
    public void createTblAspWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tblAspRepository.findAll().size();

        // Create the TblAsp with an existing ID
        tblAsp.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTblAspMockMvc.perform(post("/api/tbl-asps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tblAsp)))
            .andExpect(status().isBadRequest());

        // Validate the TblAsp in the database
        List<TblAsp> tblAspList = tblAspRepository.findAll();
        assertThat(tblAspList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTblAsps() throws Exception {
        // Initialize the database
        tblAspRepository.saveAndFlush(tblAsp);

        // Get all the tblAspList
        restTblAspMockMvc.perform(get("/api/tbl-asps?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tblAsp.getId().intValue())))
            .andExpect(jsonPath("$.[*].aspId").value(hasItem(DEFAULT_ASP_ID)))
            .andExpect(jsonPath("$.[*].aspName").value(hasItem(DEFAULT_ASP_NAME.toString())))
            .andExpect(jsonPath("$.[*].aspRolle").value(hasItem(DEFAULT_ASP_ROLLE.toString())))
            .andExpect(jsonPath("$.[*].aspTel1").value(hasItem(DEFAULT_ASP_TEL_1.toString())))
            .andExpect(jsonPath("$.[*].aspMobil1").value(hasItem(DEFAULT_ASP_MOBIL_1.toString())))
            .andExpect(jsonPath("$.[*].aspEmail").value(hasItem(DEFAULT_ASP_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].aspObjektNrRef").value(hasItem(DEFAULT_ASP_OBJEKT_NR_REF.toString())))
            .andExpect(jsonPath("$.[*].aspBemerkung").value(hasItem(DEFAULT_ASP_BEMERKUNG.toString())));
    }
    
    @Test
    @Transactional
    public void getTblAsp() throws Exception {
        // Initialize the database
        tblAspRepository.saveAndFlush(tblAsp);

        // Get the tblAsp
        restTblAspMockMvc.perform(get("/api/tbl-asps/{id}", tblAsp.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tblAsp.getId().intValue()))
            .andExpect(jsonPath("$.aspId").value(DEFAULT_ASP_ID))
            .andExpect(jsonPath("$.aspName").value(DEFAULT_ASP_NAME.toString()))
            .andExpect(jsonPath("$.aspRolle").value(DEFAULT_ASP_ROLLE.toString()))
            .andExpect(jsonPath("$.aspTel1").value(DEFAULT_ASP_TEL_1.toString()))
            .andExpect(jsonPath("$.aspMobil1").value(DEFAULT_ASP_MOBIL_1.toString()))
            .andExpect(jsonPath("$.aspEmail").value(DEFAULT_ASP_EMAIL.toString()))
            .andExpect(jsonPath("$.aspObjektNrRef").value(DEFAULT_ASP_OBJEKT_NR_REF.toString()))
            .andExpect(jsonPath("$.aspBemerkung").value(DEFAULT_ASP_BEMERKUNG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTblAsp() throws Exception {
        // Get the tblAsp
        restTblAspMockMvc.perform(get("/api/tbl-asps/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTblAsp() throws Exception {
        // Initialize the database
        tblAspRepository.saveAndFlush(tblAsp);

        int databaseSizeBeforeUpdate = tblAspRepository.findAll().size();

        // Update the tblAsp
        TblAsp updatedTblAsp = tblAspRepository.findById(tblAsp.getId()).get();
        // Disconnect from session so that the updates on updatedTblAsp are not directly saved in db
        em.detach(updatedTblAsp);
        updatedTblAsp
            .aspId(UPDATED_ASP_ID)
            .aspName(UPDATED_ASP_NAME)
            .aspRolle(UPDATED_ASP_ROLLE)
            .aspTel1(UPDATED_ASP_TEL_1)
            .aspMobil1(UPDATED_ASP_MOBIL_1)
            .aspEmail(UPDATED_ASP_EMAIL)
            .aspObjektNrRef(UPDATED_ASP_OBJEKT_NR_REF)
            .aspBemerkung(UPDATED_ASP_BEMERKUNG);

        restTblAspMockMvc.perform(put("/api/tbl-asps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTblAsp)))
            .andExpect(status().isOk());

        // Validate the TblAsp in the database
        List<TblAsp> tblAspList = tblAspRepository.findAll();
        assertThat(tblAspList).hasSize(databaseSizeBeforeUpdate);
        TblAsp testTblAsp = tblAspList.get(tblAspList.size() - 1);
        assertThat(testTblAsp.getAspId()).isEqualTo(UPDATED_ASP_ID);
        assertThat(testTblAsp.getAspName()).isEqualTo(UPDATED_ASP_NAME);
        assertThat(testTblAsp.getAspRolle()).isEqualTo(UPDATED_ASP_ROLLE);
        assertThat(testTblAsp.getAspTel1()).isEqualTo(UPDATED_ASP_TEL_1);
        assertThat(testTblAsp.getAspMobil1()).isEqualTo(UPDATED_ASP_MOBIL_1);
        assertThat(testTblAsp.getAspEmail()).isEqualTo(UPDATED_ASP_EMAIL);
        assertThat(testTblAsp.getAspObjektNrRef()).isEqualTo(UPDATED_ASP_OBJEKT_NR_REF);
        assertThat(testTblAsp.getAspBemerkung()).isEqualTo(UPDATED_ASP_BEMERKUNG);
    }

    @Test
    @Transactional
    public void updateNonExistingTblAsp() throws Exception {
        int databaseSizeBeforeUpdate = tblAspRepository.findAll().size();

        // Create the TblAsp

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblAspMockMvc.perform(put("/api/tbl-asps")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tblAsp)))
            .andExpect(status().isBadRequest());

        // Validate the TblAsp in the database
        List<TblAsp> tblAspList = tblAspRepository.findAll();
        assertThat(tblAspList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTblAsp() throws Exception {
        // Initialize the database
        tblAspRepository.saveAndFlush(tblAsp);

        int databaseSizeBeforeDelete = tblAspRepository.findAll().size();

        // Get the tblAsp
        restTblAspMockMvc.perform(delete("/api/tbl-asps/{id}", tblAsp.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TblAsp> tblAspList = tblAspRepository.findAll();
        assertThat(tblAspList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblAsp.class);
        TblAsp tblAsp1 = new TblAsp();
        tblAsp1.setId(1L);
        TblAsp tblAsp2 = new TblAsp();
        tblAsp2.setId(tblAsp1.getId());
        assertThat(tblAsp1).isEqualTo(tblAsp2);
        tblAsp2.setId(2L);
        assertThat(tblAsp1).isNotEqualTo(tblAsp2);
        tblAsp1.setId(null);
        assertThat(tblAsp1).isNotEqualTo(tblAsp2);
    }
}
