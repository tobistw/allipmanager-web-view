package de.stwgmbh.app.web.rest;

import de.stwgmbh.app.AllipmanagerwebviewApp;

import de.stwgmbh.app.domain.TblTermine;
import de.stwgmbh.app.repository.TblTermineRepository;
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
 * Test class for the TblTermineResource REST controller.
 *
 * @see TblTermineResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AllipmanagerwebviewApp.class)
public class TblTermineResourceIntTest {

    private static final Long DEFAULT_TER_TERMIAN_ID = 1L;
    private static final Long UPDATED_TER_TERMIAN_ID = 2L;

    private static final String DEFAULT_TER_OBJEKT_NR = "AAAAAAAAAA";
    private static final String UPDATED_TER_OBJEKT_NR = "BBBBBBBBBB";

    private static final String DEFAULT_TER_PER_NR_REF = "AAAAAAAAAA";
    private static final String UPDATED_TER_PER_NR_REF = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TER_IBS_DATUM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TER_IBS_DATUM = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TER_IBS_UHRZEIT = "AAAAAAAAAA";
    private static final String UPDATED_TER_IBS_UHRZEIT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_TER_DATUM_NEU = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TER_DATUM_NEU = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TER_BEMERKUNG = "AAAAAAAAAA";
    private static final String UPDATED_TER_BEMERKUNG = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TER_ANTENNEN = false;
    private static final Boolean UPDATED_TER_ANTENNEN = true;

    private static final Boolean DEFAULT_TER_MONTIERT = false;
    private static final Boolean UPDATED_TER_MONTIERT = true;

    private static final Boolean DEFAULT_TER_IBS_PROVISORIUM = false;
    private static final Boolean UPDATED_TER_IBS_PROVISORIUM = true;

    private static final String DEFAULT_TER_KONZ_ID = "AAAAAAAAAA";
    private static final String UPDATED_TER_KONZ_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TER_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_TER_STATUS_PROTOKOLL = "AAAAAAAAAA";
    private static final String UPDATED_TER_STATUS_PROTOKOLL = "BBBBBBBBBB";

    private static final String DEFAULT_TER_KUNDEN_INFO_TYP = "AAAAAAAAAA";
    private static final String UPDATED_TER_KUNDEN_INFO_TYP = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TER_KUNDE_INFORMIERT = false;
    private static final Boolean UPDATED_TER_KUNDE_INFORMIERT = true;

    private static final Boolean DEFAULT_TER_KUNDE_BESTAETIGT = false;
    private static final Boolean UPDATED_TER_KUNDE_BESTAETIGT = true;

    private static final Boolean DEFAULT_TER_KUNDE_ANTENNE_ERHALTEN = false;
    private static final Boolean UPDATED_TER_KUNDE_ANTENNE_ERHALTEN = true;

    private static final Boolean DEFAULT_TER_SIEMENS_INFORMIERT = false;
    private static final Boolean UPDATED_TER_SIEMENS_INFORMIERT = true;

    private static final LocalDate DEFAULT_TER_DSL_FRIST = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TER_DSL_FRIST = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TER_TERMINTYP = "AAAAAAAAAA";
    private static final String UPDATED_TER_TERMINTYP = "BBBBBBBBBB";

    private static final Boolean DEFAULT_TER_FUNKMESSUNG = false;
    private static final Boolean UPDATED_TER_FUNKMESSUNG = true;

    private static final Boolean DEFAULT_TER_KLAERUNG = false;
    private static final Boolean UPDATED_TER_KLAERUNG = true;

    private static final Boolean DEFAULT_TER_ABGESAGT = false;
    private static final Boolean UPDATED_TER_ABGESAGT = true;

    private static final Boolean DEFAULT_TER_HINWEIS = false;
    private static final Boolean UPDATED_TER_HINWEIS = true;

    private static final Boolean DEFAULT_TER_SIM_IM_GERAET = false;
    private static final Boolean UPDATED_TER_SIM_IM_GERAET = true;

    @Autowired
    private TblTermineRepository tblTermineRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTblTermineMockMvc;

    private TblTermine tblTermine;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TblTermineResource tblTermineResource = new TblTermineResource(tblTermineRepository);
        this.restTblTermineMockMvc = MockMvcBuilders.standaloneSetup(tblTermineResource)
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
    public static TblTermine createEntity(EntityManager em) {
        TblTermine tblTermine = new TblTermine()
            .terTermianId(DEFAULT_TER_TERMIAN_ID)
            .terObjektNr(DEFAULT_TER_OBJEKT_NR)
            .terPerNrRef(DEFAULT_TER_PER_NR_REF)
            .terIbsDatum(DEFAULT_TER_IBS_DATUM)
            .terIbsUhrzeit(DEFAULT_TER_IBS_UHRZEIT)
            .terDatumNeu(DEFAULT_TER_DATUM_NEU)
            .terBemerkung(DEFAULT_TER_BEMERKUNG)
            .terAntennen(DEFAULT_TER_ANTENNEN)
            .terMontiert(DEFAULT_TER_MONTIERT)
            .terIbsProvisorium(DEFAULT_TER_IBS_PROVISORIUM)
            .terKonzId(DEFAULT_TER_KONZ_ID)
            .terStatus(DEFAULT_TER_STATUS)
            .terStatusProtokoll(DEFAULT_TER_STATUS_PROTOKOLL)
            .terKundenInfoTyp(DEFAULT_TER_KUNDEN_INFO_TYP)
            .terKundeInformiert(DEFAULT_TER_KUNDE_INFORMIERT)
            .terKundeBestaetigt(DEFAULT_TER_KUNDE_BESTAETIGT)
            .terKundeAntenneErhalten(DEFAULT_TER_KUNDE_ANTENNE_ERHALTEN)
            .terSiemensInformiert(DEFAULT_TER_SIEMENS_INFORMIERT)
            .terDslFrist(DEFAULT_TER_DSL_FRIST)
            .terTermintyp(DEFAULT_TER_TERMINTYP)
            .terFunkmessung(DEFAULT_TER_FUNKMESSUNG)
            .terKlaerung(DEFAULT_TER_KLAERUNG)
            .terAbgesagt(DEFAULT_TER_ABGESAGT)
            .terHinweis(DEFAULT_TER_HINWEIS)
            .terSimImGeraet(DEFAULT_TER_SIM_IM_GERAET);
        return tblTermine;
    }

    @Before
    public void initTest() {
        tblTermine = createEntity(em);
    }

    @Test
    @Transactional
    public void createTblTermine() throws Exception {
        int databaseSizeBeforeCreate = tblTermineRepository.findAll().size();

        // Create the TblTermine
        restTblTermineMockMvc.perform(post("/api/tbl-termines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tblTermine)))
            .andExpect(status().isCreated());

        // Validate the TblTermine in the database
        List<TblTermine> tblTermineList = tblTermineRepository.findAll();
        assertThat(tblTermineList).hasSize(databaseSizeBeforeCreate + 1);
        TblTermine testTblTermine = tblTermineList.get(tblTermineList.size() - 1);
        assertThat(testTblTermine.getTerTermianId()).isEqualTo(DEFAULT_TER_TERMIAN_ID);
        assertThat(testTblTermine.getTerObjektNr()).isEqualTo(DEFAULT_TER_OBJEKT_NR);
        assertThat(testTblTermine.getTerPerNrRef()).isEqualTo(DEFAULT_TER_PER_NR_REF);
        assertThat(testTblTermine.getTerIbsDatum()).isEqualTo(DEFAULT_TER_IBS_DATUM);
        assertThat(testTblTermine.getTerIbsUhrzeit()).isEqualTo(DEFAULT_TER_IBS_UHRZEIT);
        assertThat(testTblTermine.getTerDatumNeu()).isEqualTo(DEFAULT_TER_DATUM_NEU);
        assertThat(testTblTermine.getTerBemerkung()).isEqualTo(DEFAULT_TER_BEMERKUNG);
        assertThat(testTblTermine.isTerAntennen()).isEqualTo(DEFAULT_TER_ANTENNEN);
        assertThat(testTblTermine.isTerMontiert()).isEqualTo(DEFAULT_TER_MONTIERT);
        assertThat(testTblTermine.isTerIbsProvisorium()).isEqualTo(DEFAULT_TER_IBS_PROVISORIUM);
        assertThat(testTblTermine.getTerKonzId()).isEqualTo(DEFAULT_TER_KONZ_ID);
        assertThat(testTblTermine.getTerStatus()).isEqualTo(DEFAULT_TER_STATUS);
        assertThat(testTblTermine.getTerStatusProtokoll()).isEqualTo(DEFAULT_TER_STATUS_PROTOKOLL);
        assertThat(testTblTermine.getTerKundenInfoTyp()).isEqualTo(DEFAULT_TER_KUNDEN_INFO_TYP);
        assertThat(testTblTermine.isTerKundeInformiert()).isEqualTo(DEFAULT_TER_KUNDE_INFORMIERT);
        assertThat(testTblTermine.isTerKundeBestaetigt()).isEqualTo(DEFAULT_TER_KUNDE_BESTAETIGT);
        assertThat(testTblTermine.isTerKundeAntenneErhalten()).isEqualTo(DEFAULT_TER_KUNDE_ANTENNE_ERHALTEN);
        assertThat(testTblTermine.isTerSiemensInformiert()).isEqualTo(DEFAULT_TER_SIEMENS_INFORMIERT);
        assertThat(testTblTermine.getTerDslFrist()).isEqualTo(DEFAULT_TER_DSL_FRIST);
        assertThat(testTblTermine.getTerTermintyp()).isEqualTo(DEFAULT_TER_TERMINTYP);
        assertThat(testTblTermine.isTerFunkmessung()).isEqualTo(DEFAULT_TER_FUNKMESSUNG);
        assertThat(testTblTermine.isTerKlaerung()).isEqualTo(DEFAULT_TER_KLAERUNG);
        assertThat(testTblTermine.isTerAbgesagt()).isEqualTo(DEFAULT_TER_ABGESAGT);
        assertThat(testTblTermine.isTerHinweis()).isEqualTo(DEFAULT_TER_HINWEIS);
        assertThat(testTblTermine.isTerSimImGeraet()).isEqualTo(DEFAULT_TER_SIM_IM_GERAET);
    }

    @Test
    @Transactional
    public void createTblTermineWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tblTermineRepository.findAll().size();

        // Create the TblTermine with an existing ID
        tblTermine.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTblTermineMockMvc.perform(post("/api/tbl-termines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tblTermine)))
            .andExpect(status().isBadRequest());

        // Validate the TblTermine in the database
        List<TblTermine> tblTermineList = tblTermineRepository.findAll();
        assertThat(tblTermineList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTblTermines() throws Exception {
        // Initialize the database
        tblTermineRepository.saveAndFlush(tblTermine);

        // Get all the tblTermineList
        restTblTermineMockMvc.perform(get("/api/tbl-termines?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tblTermine.getId().intValue())))
            .andExpect(jsonPath("$.[*].terTermianId").value(hasItem(DEFAULT_TER_TERMIAN_ID.intValue())))
            .andExpect(jsonPath("$.[*].terObjektNr").value(hasItem(DEFAULT_TER_OBJEKT_NR.toString())))
            .andExpect(jsonPath("$.[*].terPerNrRef").value(hasItem(DEFAULT_TER_PER_NR_REF.toString())))
            .andExpect(jsonPath("$.[*].terIbsDatum").value(hasItem(DEFAULT_TER_IBS_DATUM.toString())))
            .andExpect(jsonPath("$.[*].terIbsUhrzeit").value(hasItem(DEFAULT_TER_IBS_UHRZEIT.toString())))
            .andExpect(jsonPath("$.[*].terDatumNeu").value(hasItem(DEFAULT_TER_DATUM_NEU.toString())))
            .andExpect(jsonPath("$.[*].terBemerkung").value(hasItem(DEFAULT_TER_BEMERKUNG.toString())))
            .andExpect(jsonPath("$.[*].terAntennen").value(hasItem(DEFAULT_TER_ANTENNEN.booleanValue())))
            .andExpect(jsonPath("$.[*].terMontiert").value(hasItem(DEFAULT_TER_MONTIERT.booleanValue())))
            .andExpect(jsonPath("$.[*].terIbsProvisorium").value(hasItem(DEFAULT_TER_IBS_PROVISORIUM.booleanValue())))
            .andExpect(jsonPath("$.[*].terKonzId").value(hasItem(DEFAULT_TER_KONZ_ID.toString())))
            .andExpect(jsonPath("$.[*].terStatus").value(hasItem(DEFAULT_TER_STATUS.toString())))
            .andExpect(jsonPath("$.[*].terStatusProtokoll").value(hasItem(DEFAULT_TER_STATUS_PROTOKOLL.toString())))
            .andExpect(jsonPath("$.[*].terKundenInfoTyp").value(hasItem(DEFAULT_TER_KUNDEN_INFO_TYP.toString())))
            .andExpect(jsonPath("$.[*].terKundeInformiert").value(hasItem(DEFAULT_TER_KUNDE_INFORMIERT.booleanValue())))
            .andExpect(jsonPath("$.[*].terKundeBestaetigt").value(hasItem(DEFAULT_TER_KUNDE_BESTAETIGT.booleanValue())))
            .andExpect(jsonPath("$.[*].terKundeAntenneErhalten").value(hasItem(DEFAULT_TER_KUNDE_ANTENNE_ERHALTEN.booleanValue())))
            .andExpect(jsonPath("$.[*].terSiemensInformiert").value(hasItem(DEFAULT_TER_SIEMENS_INFORMIERT.booleanValue())))
            .andExpect(jsonPath("$.[*].terDslFrist").value(hasItem(DEFAULT_TER_DSL_FRIST.toString())))
            .andExpect(jsonPath("$.[*].terTermintyp").value(hasItem(DEFAULT_TER_TERMINTYP.toString())))
            .andExpect(jsonPath("$.[*].terFunkmessung").value(hasItem(DEFAULT_TER_FUNKMESSUNG.booleanValue())))
            .andExpect(jsonPath("$.[*].terKlaerung").value(hasItem(DEFAULT_TER_KLAERUNG.booleanValue())))
            .andExpect(jsonPath("$.[*].terAbgesagt").value(hasItem(DEFAULT_TER_ABGESAGT.booleanValue())))
            .andExpect(jsonPath("$.[*].terHinweis").value(hasItem(DEFAULT_TER_HINWEIS.booleanValue())))
            .andExpect(jsonPath("$.[*].terSimImGeraet").value(hasItem(DEFAULT_TER_SIM_IM_GERAET.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getTblTermine() throws Exception {
        // Initialize the database
        tblTermineRepository.saveAndFlush(tblTermine);

        // Get the tblTermine
        restTblTermineMockMvc.perform(get("/api/tbl-termines/{id}", tblTermine.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tblTermine.getId().intValue()))
            .andExpect(jsonPath("$.terTermianId").value(DEFAULT_TER_TERMIAN_ID.intValue()))
            .andExpect(jsonPath("$.terObjektNr").value(DEFAULT_TER_OBJEKT_NR.toString()))
            .andExpect(jsonPath("$.terPerNrRef").value(DEFAULT_TER_PER_NR_REF.toString()))
            .andExpect(jsonPath("$.terIbsDatum").value(DEFAULT_TER_IBS_DATUM.toString()))
            .andExpect(jsonPath("$.terIbsUhrzeit").value(DEFAULT_TER_IBS_UHRZEIT.toString()))
            .andExpect(jsonPath("$.terDatumNeu").value(DEFAULT_TER_DATUM_NEU.toString()))
            .andExpect(jsonPath("$.terBemerkung").value(DEFAULT_TER_BEMERKUNG.toString()))
            .andExpect(jsonPath("$.terAntennen").value(DEFAULT_TER_ANTENNEN.booleanValue()))
            .andExpect(jsonPath("$.terMontiert").value(DEFAULT_TER_MONTIERT.booleanValue()))
            .andExpect(jsonPath("$.terIbsProvisorium").value(DEFAULT_TER_IBS_PROVISORIUM.booleanValue()))
            .andExpect(jsonPath("$.terKonzId").value(DEFAULT_TER_KONZ_ID.toString()))
            .andExpect(jsonPath("$.terStatus").value(DEFAULT_TER_STATUS.toString()))
            .andExpect(jsonPath("$.terStatusProtokoll").value(DEFAULT_TER_STATUS_PROTOKOLL.toString()))
            .andExpect(jsonPath("$.terKundenInfoTyp").value(DEFAULT_TER_KUNDEN_INFO_TYP.toString()))
            .andExpect(jsonPath("$.terKundeInformiert").value(DEFAULT_TER_KUNDE_INFORMIERT.booleanValue()))
            .andExpect(jsonPath("$.terKundeBestaetigt").value(DEFAULT_TER_KUNDE_BESTAETIGT.booleanValue()))
            .andExpect(jsonPath("$.terKundeAntenneErhalten").value(DEFAULT_TER_KUNDE_ANTENNE_ERHALTEN.booleanValue()))
            .andExpect(jsonPath("$.terSiemensInformiert").value(DEFAULT_TER_SIEMENS_INFORMIERT.booleanValue()))
            .andExpect(jsonPath("$.terDslFrist").value(DEFAULT_TER_DSL_FRIST.toString()))
            .andExpect(jsonPath("$.terTermintyp").value(DEFAULT_TER_TERMINTYP.toString()))
            .andExpect(jsonPath("$.terFunkmessung").value(DEFAULT_TER_FUNKMESSUNG.booleanValue()))
            .andExpect(jsonPath("$.terKlaerung").value(DEFAULT_TER_KLAERUNG.booleanValue()))
            .andExpect(jsonPath("$.terAbgesagt").value(DEFAULT_TER_ABGESAGT.booleanValue()))
            .andExpect(jsonPath("$.terHinweis").value(DEFAULT_TER_HINWEIS.booleanValue()))
            .andExpect(jsonPath("$.terSimImGeraet").value(DEFAULT_TER_SIM_IM_GERAET.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTblTermine() throws Exception {
        // Get the tblTermine
        restTblTermineMockMvc.perform(get("/api/tbl-termines/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTblTermine() throws Exception {
        // Initialize the database
        tblTermineRepository.saveAndFlush(tblTermine);

        int databaseSizeBeforeUpdate = tblTermineRepository.findAll().size();

        // Update the tblTermine
        TblTermine updatedTblTermine = tblTermineRepository.findById(tblTermine.getId()).get();
        // Disconnect from session so that the updates on updatedTblTermine are not directly saved in db
        em.detach(updatedTblTermine);
        updatedTblTermine
            .terTermianId(UPDATED_TER_TERMIAN_ID)
            .terObjektNr(UPDATED_TER_OBJEKT_NR)
            .terPerNrRef(UPDATED_TER_PER_NR_REF)
            .terIbsDatum(UPDATED_TER_IBS_DATUM)
            .terIbsUhrzeit(UPDATED_TER_IBS_UHRZEIT)
            .terDatumNeu(UPDATED_TER_DATUM_NEU)
            .terBemerkung(UPDATED_TER_BEMERKUNG)
            .terAntennen(UPDATED_TER_ANTENNEN)
            .terMontiert(UPDATED_TER_MONTIERT)
            .terIbsProvisorium(UPDATED_TER_IBS_PROVISORIUM)
            .terKonzId(UPDATED_TER_KONZ_ID)
            .terStatus(UPDATED_TER_STATUS)
            .terStatusProtokoll(UPDATED_TER_STATUS_PROTOKOLL)
            .terKundenInfoTyp(UPDATED_TER_KUNDEN_INFO_TYP)
            .terKundeInformiert(UPDATED_TER_KUNDE_INFORMIERT)
            .terKundeBestaetigt(UPDATED_TER_KUNDE_BESTAETIGT)
            .terKundeAntenneErhalten(UPDATED_TER_KUNDE_ANTENNE_ERHALTEN)
            .terSiemensInformiert(UPDATED_TER_SIEMENS_INFORMIERT)
            .terDslFrist(UPDATED_TER_DSL_FRIST)
            .terTermintyp(UPDATED_TER_TERMINTYP)
            .terFunkmessung(UPDATED_TER_FUNKMESSUNG)
            .terKlaerung(UPDATED_TER_KLAERUNG)
            .terAbgesagt(UPDATED_TER_ABGESAGT)
            .terHinweis(UPDATED_TER_HINWEIS)
            .terSimImGeraet(UPDATED_TER_SIM_IM_GERAET);

        restTblTermineMockMvc.perform(put("/api/tbl-termines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTblTermine)))
            .andExpect(status().isOk());

        // Validate the TblTermine in the database
        List<TblTermine> tblTermineList = tblTermineRepository.findAll();
        assertThat(tblTermineList).hasSize(databaseSizeBeforeUpdate);
        TblTermine testTblTermine = tblTermineList.get(tblTermineList.size() - 1);
        assertThat(testTblTermine.getTerTermianId()).isEqualTo(UPDATED_TER_TERMIAN_ID);
        assertThat(testTblTermine.getTerObjektNr()).isEqualTo(UPDATED_TER_OBJEKT_NR);
        assertThat(testTblTermine.getTerPerNrRef()).isEqualTo(UPDATED_TER_PER_NR_REF);
        assertThat(testTblTermine.getTerIbsDatum()).isEqualTo(UPDATED_TER_IBS_DATUM);
        assertThat(testTblTermine.getTerIbsUhrzeit()).isEqualTo(UPDATED_TER_IBS_UHRZEIT);
        assertThat(testTblTermine.getTerDatumNeu()).isEqualTo(UPDATED_TER_DATUM_NEU);
        assertThat(testTblTermine.getTerBemerkung()).isEqualTo(UPDATED_TER_BEMERKUNG);
        assertThat(testTblTermine.isTerAntennen()).isEqualTo(UPDATED_TER_ANTENNEN);
        assertThat(testTblTermine.isTerMontiert()).isEqualTo(UPDATED_TER_MONTIERT);
        assertThat(testTblTermine.isTerIbsProvisorium()).isEqualTo(UPDATED_TER_IBS_PROVISORIUM);
        assertThat(testTblTermine.getTerKonzId()).isEqualTo(UPDATED_TER_KONZ_ID);
        assertThat(testTblTermine.getTerStatus()).isEqualTo(UPDATED_TER_STATUS);
        assertThat(testTblTermine.getTerStatusProtokoll()).isEqualTo(UPDATED_TER_STATUS_PROTOKOLL);
        assertThat(testTblTermine.getTerKundenInfoTyp()).isEqualTo(UPDATED_TER_KUNDEN_INFO_TYP);
        assertThat(testTblTermine.isTerKundeInformiert()).isEqualTo(UPDATED_TER_KUNDE_INFORMIERT);
        assertThat(testTblTermine.isTerKundeBestaetigt()).isEqualTo(UPDATED_TER_KUNDE_BESTAETIGT);
        assertThat(testTblTermine.isTerKundeAntenneErhalten()).isEqualTo(UPDATED_TER_KUNDE_ANTENNE_ERHALTEN);
        assertThat(testTblTermine.isTerSiemensInformiert()).isEqualTo(UPDATED_TER_SIEMENS_INFORMIERT);
        assertThat(testTblTermine.getTerDslFrist()).isEqualTo(UPDATED_TER_DSL_FRIST);
        assertThat(testTblTermine.getTerTermintyp()).isEqualTo(UPDATED_TER_TERMINTYP);
        assertThat(testTblTermine.isTerFunkmessung()).isEqualTo(UPDATED_TER_FUNKMESSUNG);
        assertThat(testTblTermine.isTerKlaerung()).isEqualTo(UPDATED_TER_KLAERUNG);
        assertThat(testTblTermine.isTerAbgesagt()).isEqualTo(UPDATED_TER_ABGESAGT);
        assertThat(testTblTermine.isTerHinweis()).isEqualTo(UPDATED_TER_HINWEIS);
        assertThat(testTblTermine.isTerSimImGeraet()).isEqualTo(UPDATED_TER_SIM_IM_GERAET);
    }

    @Test
    @Transactional
    public void updateNonExistingTblTermine() throws Exception {
        int databaseSizeBeforeUpdate = tblTermineRepository.findAll().size();

        // Create the TblTermine

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblTermineMockMvc.perform(put("/api/tbl-termines")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tblTermine)))
            .andExpect(status().isBadRequest());

        // Validate the TblTermine in the database
        List<TblTermine> tblTermineList = tblTermineRepository.findAll();
        assertThat(tblTermineList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTblTermine() throws Exception {
        // Initialize the database
        tblTermineRepository.saveAndFlush(tblTermine);

        int databaseSizeBeforeDelete = tblTermineRepository.findAll().size();

        // Get the tblTermine
        restTblTermineMockMvc.perform(delete("/api/tbl-termines/{id}", tblTermine.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TblTermine> tblTermineList = tblTermineRepository.findAll();
        assertThat(tblTermineList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblTermine.class);
        TblTermine tblTermine1 = new TblTermine();
        tblTermine1.setId(1L);
        TblTermine tblTermine2 = new TblTermine();
        tblTermine2.setId(tblTermine1.getId());
        assertThat(tblTermine1).isEqualTo(tblTermine2);
        tblTermine2.setId(2L);
        assertThat(tblTermine1).isNotEqualTo(tblTermine2);
        tblTermine1.setId(null);
        assertThat(tblTermine1).isNotEqualTo(tblTermine2);
    }
}
