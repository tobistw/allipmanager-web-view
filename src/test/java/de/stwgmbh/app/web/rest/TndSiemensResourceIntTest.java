package de.stwgmbh.app.web.rest;

import de.stwgmbh.app.AllipmanagerwebviewApp;

import de.stwgmbh.app.domain.TndSiemens;
import de.stwgmbh.app.repository.TndSiemensRepository;
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
 * Test class for the TndSiemensResource REST controller.
 *
 * @see TndSiemensResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AllipmanagerwebviewApp.class)
public class TndSiemensResourceIntTest {

    private static final String DEFAULT_TND_OBJ_NR = "AAAAAAAAAA";
    private static final String UPDATED_TND_OBJ_NR = "BBBBBBBBBB";

    private static final String DEFAULT_TND_TEIL_OBJ_NR = "AAAAAAAAAA";
    private static final String UPDATED_TND_TEIL_OBJ_NR = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TND_WARTEN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TND_WARTEN = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TND_LEITUNGS_NR_ALT = "AAAAAAAAAA";
    private static final String UPDATED_TND_LEITUNGS_NR_ALT = "BBBBBBBBBB";

    private static final String DEFAULT_TND_LEITUNGS_NR_NEU = "AAAAAAAAAA";
    private static final String UPDATED_TND_LEITUNGS_NR_NEU = "BBBBBBBBBB";

    private static final String DEFAULT_TND_GSM_NR = "AAAAAAAAAA";
    private static final String UPDATED_TND_GSM_NR = "BBBBBBBBBB";

    private static final String DEFAULT_TND_GSM_NR_2 = "AAAAAAAAAA";
    private static final String UPDATED_TND_GSM_NR_2 = "BBBBBBBBBB";

    private static final String DEFAULT_TND_WARTUNG_BMA = "AAAAAAAAAA";
    private static final String UPDATED_TND_WARTUNG_BMA = "BBBBBBBBBB";

    private static final String DEFAULT_TND_SI_SE = "AAAAAAAAAA";
    private static final String UPDATED_TND_SI_SE = "BBBBBBBBBB";

    private static final String DEFAULT_TND_LOGIN = "AAAAAAAAAA";
    private static final String UPDATED_TND_LOGIN = "BBBBBBBBBB";

    private static final String DEFAULT_TND_PASSWORT = "AAAAAAAAAA";
    private static final String UPDATED_TND_PASSWORT = "BBBBBBBBBB";

    private static final String DEFAULT_TND_IP = "AAAAAAAAAA";
    private static final String UPDATED_TND_IP = "BBBBBBBBBB";

    private static final Integer DEFAULT_TND_TELEKOM_NR = 1;
    private static final Integer UPDATED_TND_TELEKOM_NR = 2;

    private static final Long DEFAULT_TND_LEITUNGS_NR_PSEUDO = 1L;
    private static final Long UPDATED_TND_LEITUNGS_NR_PSEUDO = 2L;

    private static final LocalDate DEFAULT_TND_DSL_FRIST = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TND_DSL_FRIST = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private TndSiemensRepository tndSiemensRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTndSiemensMockMvc;

    private TndSiemens tndSiemens;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TndSiemensResource tndSiemensResource = new TndSiemensResource(tndSiemensRepository);
        this.restTndSiemensMockMvc = MockMvcBuilders.standaloneSetup(tndSiemensResource)
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
    public static TndSiemens createEntity(EntityManager em) {
        TndSiemens tndSiemens = new TndSiemens()
            .tndObjNr(DEFAULT_TND_OBJ_NR)
            .tndTeilObjNr(DEFAULT_TND_TEIL_OBJ_NR)
            .tndWarten(DEFAULT_TND_WARTEN)
            .tndLeitungsNrAlt(DEFAULT_TND_LEITUNGS_NR_ALT)
            .tndLeitungsNrNeu(DEFAULT_TND_LEITUNGS_NR_NEU)
            .tndGSMNr(DEFAULT_TND_GSM_NR)
            .tndGSMNr2(DEFAULT_TND_GSM_NR_2)
            .tndWartungBMA(DEFAULT_TND_WARTUNG_BMA)
            .tndSiSe(DEFAULT_TND_SI_SE)
            .tndLogin(DEFAULT_TND_LOGIN)
            .tndPasswort(DEFAULT_TND_PASSWORT)
            .tndIP(DEFAULT_TND_IP)
            .tndTelekomNr(DEFAULT_TND_TELEKOM_NR)
            .tndLeitungsNrPseudo(DEFAULT_TND_LEITUNGS_NR_PSEUDO)
            .tndDslFrist(DEFAULT_TND_DSL_FRIST);
        return tndSiemens;
    }

    @Before
    public void initTest() {
        tndSiemens = createEntity(em);
    }

    @Test
    @Transactional
    public void createTndSiemens() throws Exception {
        int databaseSizeBeforeCreate = tndSiemensRepository.findAll().size();

        // Create the TndSiemens
        restTndSiemensMockMvc.perform(post("/api/tnd-siemens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tndSiemens)))
            .andExpect(status().isCreated());

        // Validate the TndSiemens in the database
        List<TndSiemens> tndSiemensList = tndSiemensRepository.findAll();
        assertThat(tndSiemensList).hasSize(databaseSizeBeforeCreate + 1);
        TndSiemens testTndSiemens = tndSiemensList.get(tndSiemensList.size() - 1);
        assertThat(testTndSiemens.getTndObjNr()).isEqualTo(DEFAULT_TND_OBJ_NR);
        assertThat(testTndSiemens.getTndTeilObjNr()).isEqualTo(DEFAULT_TND_TEIL_OBJ_NR);
        assertThat(testTndSiemens.getTndWarten()).isEqualTo(DEFAULT_TND_WARTEN);
        assertThat(testTndSiemens.getTndLeitungsNrAlt()).isEqualTo(DEFAULT_TND_LEITUNGS_NR_ALT);
        assertThat(testTndSiemens.getTndLeitungsNrNeu()).isEqualTo(DEFAULT_TND_LEITUNGS_NR_NEU);
        assertThat(testTndSiemens.getTndGSMNr()).isEqualTo(DEFAULT_TND_GSM_NR);
        assertThat(testTndSiemens.getTndGSMNr2()).isEqualTo(DEFAULT_TND_GSM_NR_2);
        assertThat(testTndSiemens.getTndWartungBMA()).isEqualTo(DEFAULT_TND_WARTUNG_BMA);
        assertThat(testTndSiemens.getTndSiSe()).isEqualTo(DEFAULT_TND_SI_SE);
        assertThat(testTndSiemens.getTndLogin()).isEqualTo(DEFAULT_TND_LOGIN);
        assertThat(testTndSiemens.getTndPasswort()).isEqualTo(DEFAULT_TND_PASSWORT);
        assertThat(testTndSiemens.getTndIP()).isEqualTo(DEFAULT_TND_IP);
        assertThat(testTndSiemens.getTndTelekomNr()).isEqualTo(DEFAULT_TND_TELEKOM_NR);
        assertThat(testTndSiemens.getTndLeitungsNrPseudo()).isEqualTo(DEFAULT_TND_LEITUNGS_NR_PSEUDO);
        assertThat(testTndSiemens.getTndDslFrist()).isEqualTo(DEFAULT_TND_DSL_FRIST);
    }

    @Test
    @Transactional
    public void createTndSiemensWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tndSiemensRepository.findAll().size();

        // Create the TndSiemens with an existing ID
        tndSiemens.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTndSiemensMockMvc.perform(post("/api/tnd-siemens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tndSiemens)))
            .andExpect(status().isBadRequest());

        // Validate the TndSiemens in the database
        List<TndSiemens> tndSiemensList = tndSiemensRepository.findAll();
        assertThat(tndSiemensList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTndSiemens() throws Exception {
        // Initialize the database
        tndSiemensRepository.saveAndFlush(tndSiemens);

        // Get all the tndSiemensList
        restTndSiemensMockMvc.perform(get("/api/tnd-siemens?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tndSiemens.getId().intValue())))
            .andExpect(jsonPath("$.[*].tndObjNr").value(hasItem(DEFAULT_TND_OBJ_NR.toString())))
            .andExpect(jsonPath("$.[*].tndTeilObjNr").value(hasItem(DEFAULT_TND_TEIL_OBJ_NR.toString())))
            .andExpect(jsonPath("$.[*].tndWarten").value(hasItem(DEFAULT_TND_WARTEN.toString())))
            .andExpect(jsonPath("$.[*].tndLeitungsNrAlt").value(hasItem(DEFAULT_TND_LEITUNGS_NR_ALT.toString())))
            .andExpect(jsonPath("$.[*].tndLeitungsNrNeu").value(hasItem(DEFAULT_TND_LEITUNGS_NR_NEU.toString())))
            .andExpect(jsonPath("$.[*].tndGSMNr").value(hasItem(DEFAULT_TND_GSM_NR.toString())))
            .andExpect(jsonPath("$.[*].tndGSMNr2").value(hasItem(DEFAULT_TND_GSM_NR_2.toString())))
            .andExpect(jsonPath("$.[*].tndWartungBMA").value(hasItem(DEFAULT_TND_WARTUNG_BMA.toString())))
            .andExpect(jsonPath("$.[*].tndSiSe").value(hasItem(DEFAULT_TND_SI_SE.toString())))
            .andExpect(jsonPath("$.[*].tndLogin").value(hasItem(DEFAULT_TND_LOGIN.toString())))
            .andExpect(jsonPath("$.[*].tndPasswort").value(hasItem(DEFAULT_TND_PASSWORT.toString())))
            .andExpect(jsonPath("$.[*].tndIP").value(hasItem(DEFAULT_TND_IP.toString())))
            .andExpect(jsonPath("$.[*].tndTelekomNr").value(hasItem(DEFAULT_TND_TELEKOM_NR)))
            .andExpect(jsonPath("$.[*].tndLeitungsNrPseudo").value(hasItem(DEFAULT_TND_LEITUNGS_NR_PSEUDO.intValue())))
            .andExpect(jsonPath("$.[*].tndDslFrist").value(hasItem(DEFAULT_TND_DSL_FRIST.toString())));
    }
    
    @Test
    @Transactional
    public void getTndSiemens() throws Exception {
        // Initialize the database
        tndSiemensRepository.saveAndFlush(tndSiemens);

        // Get the tndSiemens
        restTndSiemensMockMvc.perform(get("/api/tnd-siemens/{id}", tndSiemens.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tndSiemens.getId().intValue()))
            .andExpect(jsonPath("$.tndObjNr").value(DEFAULT_TND_OBJ_NR.toString()))
            .andExpect(jsonPath("$.tndTeilObjNr").value(DEFAULT_TND_TEIL_OBJ_NR.toString()))
            .andExpect(jsonPath("$.tndWarten").value(DEFAULT_TND_WARTEN.toString()))
            .andExpect(jsonPath("$.tndLeitungsNrAlt").value(DEFAULT_TND_LEITUNGS_NR_ALT.toString()))
            .andExpect(jsonPath("$.tndLeitungsNrNeu").value(DEFAULT_TND_LEITUNGS_NR_NEU.toString()))
            .andExpect(jsonPath("$.tndGSMNr").value(DEFAULT_TND_GSM_NR.toString()))
            .andExpect(jsonPath("$.tndGSMNr2").value(DEFAULT_TND_GSM_NR_2.toString()))
            .andExpect(jsonPath("$.tndWartungBMA").value(DEFAULT_TND_WARTUNG_BMA.toString()))
            .andExpect(jsonPath("$.tndSiSe").value(DEFAULT_TND_SI_SE.toString()))
            .andExpect(jsonPath("$.tndLogin").value(DEFAULT_TND_LOGIN.toString()))
            .andExpect(jsonPath("$.tndPasswort").value(DEFAULT_TND_PASSWORT.toString()))
            .andExpect(jsonPath("$.tndIP").value(DEFAULT_TND_IP.toString()))
            .andExpect(jsonPath("$.tndTelekomNr").value(DEFAULT_TND_TELEKOM_NR))
            .andExpect(jsonPath("$.tndLeitungsNrPseudo").value(DEFAULT_TND_LEITUNGS_NR_PSEUDO.intValue()))
            .andExpect(jsonPath("$.tndDslFrist").value(DEFAULT_TND_DSL_FRIST.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTndSiemens() throws Exception {
        // Get the tndSiemens
        restTndSiemensMockMvc.perform(get("/api/tnd-siemens/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTndSiemens() throws Exception {
        // Initialize the database
        tndSiemensRepository.saveAndFlush(tndSiemens);

        int databaseSizeBeforeUpdate = tndSiemensRepository.findAll().size();

        // Update the tndSiemens
        TndSiemens updatedTndSiemens = tndSiemensRepository.findById(tndSiemens.getId()).get();
        // Disconnect from session so that the updates on updatedTndSiemens are not directly saved in db
        em.detach(updatedTndSiemens);
        updatedTndSiemens
            .tndObjNr(UPDATED_TND_OBJ_NR)
            .tndTeilObjNr(UPDATED_TND_TEIL_OBJ_NR)
            .tndWarten(UPDATED_TND_WARTEN)
            .tndLeitungsNrAlt(UPDATED_TND_LEITUNGS_NR_ALT)
            .tndLeitungsNrNeu(UPDATED_TND_LEITUNGS_NR_NEU)
            .tndGSMNr(UPDATED_TND_GSM_NR)
            .tndGSMNr2(UPDATED_TND_GSM_NR_2)
            .tndWartungBMA(UPDATED_TND_WARTUNG_BMA)
            .tndSiSe(UPDATED_TND_SI_SE)
            .tndLogin(UPDATED_TND_LOGIN)
            .tndPasswort(UPDATED_TND_PASSWORT)
            .tndIP(UPDATED_TND_IP)
            .tndTelekomNr(UPDATED_TND_TELEKOM_NR)
            .tndLeitungsNrPseudo(UPDATED_TND_LEITUNGS_NR_PSEUDO)
            .tndDslFrist(UPDATED_TND_DSL_FRIST);

        restTndSiemensMockMvc.perform(put("/api/tnd-siemens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTndSiemens)))
            .andExpect(status().isOk());

        // Validate the TndSiemens in the database
        List<TndSiemens> tndSiemensList = tndSiemensRepository.findAll();
        assertThat(tndSiemensList).hasSize(databaseSizeBeforeUpdate);
        TndSiemens testTndSiemens = tndSiemensList.get(tndSiemensList.size() - 1);
        assertThat(testTndSiemens.getTndObjNr()).isEqualTo(UPDATED_TND_OBJ_NR);
        assertThat(testTndSiemens.getTndTeilObjNr()).isEqualTo(UPDATED_TND_TEIL_OBJ_NR);
        assertThat(testTndSiemens.getTndWarten()).isEqualTo(UPDATED_TND_WARTEN);
        assertThat(testTndSiemens.getTndLeitungsNrAlt()).isEqualTo(UPDATED_TND_LEITUNGS_NR_ALT);
        assertThat(testTndSiemens.getTndLeitungsNrNeu()).isEqualTo(UPDATED_TND_LEITUNGS_NR_NEU);
        assertThat(testTndSiemens.getTndGSMNr()).isEqualTo(UPDATED_TND_GSM_NR);
        assertThat(testTndSiemens.getTndGSMNr2()).isEqualTo(UPDATED_TND_GSM_NR_2);
        assertThat(testTndSiemens.getTndWartungBMA()).isEqualTo(UPDATED_TND_WARTUNG_BMA);
        assertThat(testTndSiemens.getTndSiSe()).isEqualTo(UPDATED_TND_SI_SE);
        assertThat(testTndSiemens.getTndLogin()).isEqualTo(UPDATED_TND_LOGIN);
        assertThat(testTndSiemens.getTndPasswort()).isEqualTo(UPDATED_TND_PASSWORT);
        assertThat(testTndSiemens.getTndIP()).isEqualTo(UPDATED_TND_IP);
        assertThat(testTndSiemens.getTndTelekomNr()).isEqualTo(UPDATED_TND_TELEKOM_NR);
        assertThat(testTndSiemens.getTndLeitungsNrPseudo()).isEqualTo(UPDATED_TND_LEITUNGS_NR_PSEUDO);
        assertThat(testTndSiemens.getTndDslFrist()).isEqualTo(UPDATED_TND_DSL_FRIST);
    }

    @Test
    @Transactional
    public void updateNonExistingTndSiemens() throws Exception {
        int databaseSizeBeforeUpdate = tndSiemensRepository.findAll().size();

        // Create the TndSiemens

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTndSiemensMockMvc.perform(put("/api/tnd-siemens")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tndSiemens)))
            .andExpect(status().isBadRequest());

        // Validate the TndSiemens in the database
        List<TndSiemens> tndSiemensList = tndSiemensRepository.findAll();
        assertThat(tndSiemensList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTndSiemens() throws Exception {
        // Initialize the database
        tndSiemensRepository.saveAndFlush(tndSiemens);

        int databaseSizeBeforeDelete = tndSiemensRepository.findAll().size();

        // Get the tndSiemens
        restTndSiemensMockMvc.perform(delete("/api/tnd-siemens/{id}", tndSiemens.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TndSiemens> tndSiemensList = tndSiemensRepository.findAll();
        assertThat(tndSiemensList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TndSiemens.class);
        TndSiemens tndSiemens1 = new TndSiemens();
        tndSiemens1.setId(1L);
        TndSiemens tndSiemens2 = new TndSiemens();
        tndSiemens2.setId(tndSiemens1.getId());
        assertThat(tndSiemens1).isEqualTo(tndSiemens2);
        tndSiemens2.setId(2L);
        assertThat(tndSiemens1).isNotEqualTo(tndSiemens2);
        tndSiemens1.setId(null);
        assertThat(tndSiemens1).isNotEqualTo(tndSiemens2);
    }
}
