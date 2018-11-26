package de.stwgmbh.app.web.rest;

import de.stwgmbh.app.AllipmanagerwebviewApp;

import de.stwgmbh.app.domain.TblFunkmessung;
import de.stwgmbh.app.repository.TblFunkmessungRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static de.stwgmbh.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TblFunkmessungResource REST controller.
 *
 * @see TblFunkmessungResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AllipmanagerwebviewApp.class)
public class TblFunkmessungResourceIntTest {

    private static final Integer DEFAULT_FUN_ID = 1;
    private static final Integer UPDATED_FUN_ID = 2;

    private static final String DEFAULT_FUN_OBJEKT_NR = "AAAAAAAAAA";
    private static final String UPDATED_FUN_OBJEKT_NR = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_FUN_DATUM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FUN_DATUM = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_FUN_D_1 = "AAAAAAAAAA";
    private static final String UPDATED_FUN_D_1 = "BBBBBBBBBB";

    private static final String DEFAULT_FUN_D_2 = "AAAAAAAAAA";
    private static final String UPDATED_FUN_D_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FUN_O_2 = "AAAAAAAAAA";
    private static final String UPDATED_FUN_O_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FUN_EPLUS = "AAAAAAAAAA";
    private static final String UPDATED_FUN_EPLUS = "BBBBBBBBBB";

    private static final String DEFAULT_FUN_ZUSATZ = "AAAAAAAAAA";
    private static final String UPDATED_FUN_ZUSATZ = "BBBBBBBBBB";

    private static final String DEFAULT_FUN_KABEL = "AAAAAAAAAA";
    private static final String UPDATED_FUN_KABEL = "BBBBBBBBBB";

    private static final String DEFAULT_FUN_BEMERKUNG = "AAAAAAAAAA";
    private static final String UPDATED_FUN_BEMERKUNG = "BBBBBBBBBB";

    private static final String DEFAULT_FUN_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FUN_NAME = "BBBBBBBBBB";

    @Autowired
    private TblFunkmessungRepository tblFunkmessungRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTblFunkmessungMockMvc;

    private TblFunkmessung tblFunkmessung;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TblFunkmessungResource tblFunkmessungResource = new TblFunkmessungResource(tblFunkmessungRepository);
        this.restTblFunkmessungMockMvc = MockMvcBuilders.standaloneSetup(tblFunkmessungResource)
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
    public static TblFunkmessung createEntity(EntityManager em) {
        TblFunkmessung tblFunkmessung = new TblFunkmessung()
            .funId(DEFAULT_FUN_ID)
            .funObjektNr(DEFAULT_FUN_OBJEKT_NR)
            .funDatum(DEFAULT_FUN_DATUM)
            .funD1(DEFAULT_FUN_D_1)
            .funD2(DEFAULT_FUN_D_2)
            .funO2(DEFAULT_FUN_O_2)
            .funEplus(DEFAULT_FUN_EPLUS)
            .funZusatz(DEFAULT_FUN_ZUSATZ)
            .funKabel(DEFAULT_FUN_KABEL)
            .funBemerkung(DEFAULT_FUN_BEMERKUNG)
            .funName(DEFAULT_FUN_NAME);
        return tblFunkmessung;
    }

    @Before
    public void initTest() {
        tblFunkmessung = createEntity(em);
    }

    @Test
    @Transactional
    public void createTblFunkmessung() throws Exception {
        int databaseSizeBeforeCreate = tblFunkmessungRepository.findAll().size();

        // Create the TblFunkmessung
        restTblFunkmessungMockMvc.perform(post("/api/tbl-funkmessungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tblFunkmessung)))
            .andExpect(status().isCreated());

        // Validate the TblFunkmessung in the database
        List<TblFunkmessung> tblFunkmessungList = tblFunkmessungRepository.findAll();
        assertThat(tblFunkmessungList).hasSize(databaseSizeBeforeCreate + 1);
        TblFunkmessung testTblFunkmessung = tblFunkmessungList.get(tblFunkmessungList.size() - 1);
        assertThat(testTblFunkmessung.getFunId()).isEqualTo(DEFAULT_FUN_ID);
        assertThat(testTblFunkmessung.getFunObjektNr()).isEqualTo(DEFAULT_FUN_OBJEKT_NR);
        assertThat(testTblFunkmessung.getFunDatum()).isEqualTo(DEFAULT_FUN_DATUM);
        assertThat(testTblFunkmessung.getFunD1()).isEqualTo(DEFAULT_FUN_D_1);
        assertThat(testTblFunkmessung.getFunD2()).isEqualTo(DEFAULT_FUN_D_2);
        assertThat(testTblFunkmessung.getFunO2()).isEqualTo(DEFAULT_FUN_O_2);
        assertThat(testTblFunkmessung.getFunEplus()).isEqualTo(DEFAULT_FUN_EPLUS);
        assertThat(testTblFunkmessung.getFunZusatz()).isEqualTo(DEFAULT_FUN_ZUSATZ);
        assertThat(testTblFunkmessung.getFunKabel()).isEqualTo(DEFAULT_FUN_KABEL);
        assertThat(testTblFunkmessung.getFunBemerkung()).isEqualTo(DEFAULT_FUN_BEMERKUNG);
        assertThat(testTblFunkmessung.getFunName()).isEqualTo(DEFAULT_FUN_NAME);
    }

    @Test
    @Transactional
    public void createTblFunkmessungWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tblFunkmessungRepository.findAll().size();

        // Create the TblFunkmessung with an existing ID
        tblFunkmessung.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTblFunkmessungMockMvc.perform(post("/api/tbl-funkmessungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tblFunkmessung)))
            .andExpect(status().isBadRequest());

        // Validate the TblFunkmessung in the database
        List<TblFunkmessung> tblFunkmessungList = tblFunkmessungRepository.findAll();
        assertThat(tblFunkmessungList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTblFunkmessungs() throws Exception {
        // Initialize the database
        tblFunkmessungRepository.saveAndFlush(tblFunkmessung);

        // Get all the tblFunkmessungList
        restTblFunkmessungMockMvc.perform(get("/api/tbl-funkmessungs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tblFunkmessung.getId().intValue())))
            .andExpect(jsonPath("$.[*].funId").value(hasItem(DEFAULT_FUN_ID)))
            .andExpect(jsonPath("$.[*].funObjektNr").value(hasItem(DEFAULT_FUN_OBJEKT_NR.toString())))
            .andExpect(jsonPath("$.[*].funDatum").value(hasItem(DEFAULT_FUN_DATUM.toString())))
            .andExpect(jsonPath("$.[*].funD1").value(hasItem(DEFAULT_FUN_D_1.toString())))
            .andExpect(jsonPath("$.[*].funD2").value(hasItem(DEFAULT_FUN_D_2.toString())))
            .andExpect(jsonPath("$.[*].funO2").value(hasItem(DEFAULT_FUN_O_2.toString())))
            .andExpect(jsonPath("$.[*].funEplus").value(hasItem(DEFAULT_FUN_EPLUS.toString())))
            .andExpect(jsonPath("$.[*].funZusatz").value(hasItem(DEFAULT_FUN_ZUSATZ.toString())))
            .andExpect(jsonPath("$.[*].funKabel").value(hasItem(DEFAULT_FUN_KABEL.toString())))
            .andExpect(jsonPath("$.[*].funBemerkung").value(hasItem(DEFAULT_FUN_BEMERKUNG.toString())))
            .andExpect(jsonPath("$.[*].funName").value(hasItem(DEFAULT_FUN_NAME.toString())));
    }
    
    @Test
    @Transactional
    public void getTblFunkmessung() throws Exception {
        // Initialize the database
        tblFunkmessungRepository.saveAndFlush(tblFunkmessung);

        // Get the tblFunkmessung
        restTblFunkmessungMockMvc.perform(get("/api/tbl-funkmessungs/{id}", tblFunkmessung.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tblFunkmessung.getId().intValue()))
            .andExpect(jsonPath("$.funId").value(DEFAULT_FUN_ID))
            .andExpect(jsonPath("$.funObjektNr").value(DEFAULT_FUN_OBJEKT_NR.toString()))
            .andExpect(jsonPath("$.funDatum").value(DEFAULT_FUN_DATUM.toString()))
            .andExpect(jsonPath("$.funD1").value(DEFAULT_FUN_D_1.toString()))
            .andExpect(jsonPath("$.funD2").value(DEFAULT_FUN_D_2.toString()))
            .andExpect(jsonPath("$.funO2").value(DEFAULT_FUN_O_2.toString()))
            .andExpect(jsonPath("$.funEplus").value(DEFAULT_FUN_EPLUS.toString()))
            .andExpect(jsonPath("$.funZusatz").value(DEFAULT_FUN_ZUSATZ.toString()))
            .andExpect(jsonPath("$.funKabel").value(DEFAULT_FUN_KABEL.toString()))
            .andExpect(jsonPath("$.funBemerkung").value(DEFAULT_FUN_BEMERKUNG.toString()))
            .andExpect(jsonPath("$.funName").value(DEFAULT_FUN_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTblFunkmessung() throws Exception {
        // Get the tblFunkmessung
        restTblFunkmessungMockMvc.perform(get("/api/tbl-funkmessungs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTblFunkmessung() throws Exception {
        // Initialize the database
        tblFunkmessungRepository.saveAndFlush(tblFunkmessung);

        int databaseSizeBeforeUpdate = tblFunkmessungRepository.findAll().size();

        // Update the tblFunkmessung
        TblFunkmessung updatedTblFunkmessung = tblFunkmessungRepository.findById(tblFunkmessung.getId()).get();
        // Disconnect from session so that the updates on updatedTblFunkmessung are not directly saved in db
        em.detach(updatedTblFunkmessung);
        updatedTblFunkmessung
            .funId(UPDATED_FUN_ID)
            .funObjektNr(UPDATED_FUN_OBJEKT_NR)
            .funDatum(UPDATED_FUN_DATUM)
            .funD1(UPDATED_FUN_D_1)
            .funD2(UPDATED_FUN_D_2)
            .funO2(UPDATED_FUN_O_2)
            .funEplus(UPDATED_FUN_EPLUS)
            .funZusatz(UPDATED_FUN_ZUSATZ)
            .funKabel(UPDATED_FUN_KABEL)
            .funBemerkung(UPDATED_FUN_BEMERKUNG)
            .funName(UPDATED_FUN_NAME);

        restTblFunkmessungMockMvc.perform(put("/api/tbl-funkmessungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTblFunkmessung)))
            .andExpect(status().isOk());

        // Validate the TblFunkmessung in the database
        List<TblFunkmessung> tblFunkmessungList = tblFunkmessungRepository.findAll();
        assertThat(tblFunkmessungList).hasSize(databaseSizeBeforeUpdate);
        TblFunkmessung testTblFunkmessung = tblFunkmessungList.get(tblFunkmessungList.size() - 1);
        assertThat(testTblFunkmessung.getFunId()).isEqualTo(UPDATED_FUN_ID);
        assertThat(testTblFunkmessung.getFunObjektNr()).isEqualTo(UPDATED_FUN_OBJEKT_NR);
        assertThat(testTblFunkmessung.getFunDatum()).isEqualTo(UPDATED_FUN_DATUM);
        assertThat(testTblFunkmessung.getFunD1()).isEqualTo(UPDATED_FUN_D_1);
        assertThat(testTblFunkmessung.getFunD2()).isEqualTo(UPDATED_FUN_D_2);
        assertThat(testTblFunkmessung.getFunO2()).isEqualTo(UPDATED_FUN_O_2);
        assertThat(testTblFunkmessung.getFunEplus()).isEqualTo(UPDATED_FUN_EPLUS);
        assertThat(testTblFunkmessung.getFunZusatz()).isEqualTo(UPDATED_FUN_ZUSATZ);
        assertThat(testTblFunkmessung.getFunKabel()).isEqualTo(UPDATED_FUN_KABEL);
        assertThat(testTblFunkmessung.getFunBemerkung()).isEqualTo(UPDATED_FUN_BEMERKUNG);
        assertThat(testTblFunkmessung.getFunName()).isEqualTo(UPDATED_FUN_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingTblFunkmessung() throws Exception {
        int databaseSizeBeforeUpdate = tblFunkmessungRepository.findAll().size();

        // Create the TblFunkmessung

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblFunkmessungMockMvc.perform(put("/api/tbl-funkmessungs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tblFunkmessung)))
            .andExpect(status().isBadRequest());

        // Validate the TblFunkmessung in the database
        List<TblFunkmessung> tblFunkmessungList = tblFunkmessungRepository.findAll();
        assertThat(tblFunkmessungList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTblFunkmessung() throws Exception {
        // Initialize the database
        tblFunkmessungRepository.saveAndFlush(tblFunkmessung);

        int databaseSizeBeforeDelete = tblFunkmessungRepository.findAll().size();

        // Get the tblFunkmessung
        restTblFunkmessungMockMvc.perform(delete("/api/tbl-funkmessungs/{id}", tblFunkmessung.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TblFunkmessung> tblFunkmessungList = tblFunkmessungRepository.findAll();
        assertThat(tblFunkmessungList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblFunkmessung.class);
        TblFunkmessung tblFunkmessung1 = new TblFunkmessung();
        tblFunkmessung1.setId(1L);
        TblFunkmessung tblFunkmessung2 = new TblFunkmessung();
        tblFunkmessung2.setId(tblFunkmessung1.getId());
        assertThat(tblFunkmessung1).isEqualTo(tblFunkmessung2);
        tblFunkmessung2.setId(2L);
        assertThat(tblFunkmessung1).isNotEqualTo(tblFunkmessung2);
        tblFunkmessung1.setId(null);
        assertThat(tblFunkmessung1).isNotEqualTo(tblFunkmessung2);
    }
}
