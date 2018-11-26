package de.stwgmbh.app.web.rest;

import de.stwgmbh.app.AllipmanagerwebviewApp;

import de.stwgmbh.app.domain.Abnahmeprotokoll;
import de.stwgmbh.app.repository.AbnahmeprotokollRepository;
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
import org.springframework.util.Base64Utils;

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
 * Test class for the AbnahmeprotokollResource REST controller.
 *
 * @see AbnahmeprotokollResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AllipmanagerwebviewApp.class)
public class AbnahmeprotokollResourceIntTest {

    private static final String DEFAULT_OBJEKT_NUMMER = "AAAAAAAAAA";
    private static final String UPDATED_OBJEKT_NUMMER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATUM_LEISTUNG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATUM_LEISTUNG = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_FIRMA = "AAAAAAAAAA";
    private static final String UPDATED_FIRMA = "BBBBBBBBBB";

    private static final String DEFAULT_KUNDE_ASP = "AAAAAAAAAA";
    private static final String UPDATED_KUNDE_ASP = "BBBBBBBBBB";

    private static final String DEFAULT_FIRMA_ASP = "AAAAAAAAAA";
    private static final String UPDATED_FIRMA_ASP = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SISE = false;
    private static final Boolean UPDATED_SISE = true;

    private static final String DEFAULT_ZUSATZLEISTUNGEN = "AAAAAAAAAA";
    private static final String UPDATED_ZUSATZLEISTUNGEN = "BBBBBBBBBB";

    private static final String DEFAULT_ANTENNE = "AAAAAAAAAA";
    private static final String UPDATED_ANTENNE = "BBBBBBBBBB";

    private static final String DEFAULT_GPRS_SIGNAL = "AAAAAAAAAA";
    private static final String UPDATED_GPRS_SIGNAL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ALARM_TEST = false;
    private static final Boolean UPDATED_ALARM_TEST = true;

    private static final String DEFAULT_ALARM_BEMERKUNG = "AAAAAAAAAA";
    private static final String UPDATED_ALARM_BEMERKUNG = "BBBBBBBBBB";

    private static final String DEFAULT_ORT = "AAAAAAAAAA";
    private static final String UPDATED_ORT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATUM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATUM = LocalDate.now(ZoneId.systemDefault());

    private static final byte[] DEFAULT_ABN_PROTOKOLL = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ABN_PROTOKOLL = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_ABN_PROTOKOLL_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ABN_PROTOKOLL_CONTENT_TYPE = "image/png";

    @Autowired
    private AbnahmeprotokollRepository abnahmeprotokollRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAbnahmeprotokollMockMvc;

    private Abnahmeprotokoll abnahmeprotokoll;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AbnahmeprotokollResource abnahmeprotokollResource = new AbnahmeprotokollResource(abnahmeprotokollRepository);
        this.restAbnahmeprotokollMockMvc = MockMvcBuilders.standaloneSetup(abnahmeprotokollResource)
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
    public static Abnahmeprotokoll createEntity(EntityManager em) {
        Abnahmeprotokoll abnahmeprotokoll = new Abnahmeprotokoll()
            .objektNummer(DEFAULT_OBJEKT_NUMMER)
            .datumLeistung(DEFAULT_DATUM_LEISTUNG)
            .firma(DEFAULT_FIRMA)
            .kundeAsp(DEFAULT_KUNDE_ASP)
            .firmaAsp(DEFAULT_FIRMA_ASP)
            .sise(DEFAULT_SISE)
            .zusatzleistungen(DEFAULT_ZUSATZLEISTUNGEN)
            .antenne(DEFAULT_ANTENNE)
            .gprsSignal(DEFAULT_GPRS_SIGNAL)
            .alarmTest(DEFAULT_ALARM_TEST)
            .alarmBemerkung(DEFAULT_ALARM_BEMERKUNG)
            .ort(DEFAULT_ORT)
            .datum(DEFAULT_DATUM)
            .abnProtokoll(DEFAULT_ABN_PROTOKOLL)
            .abnProtokollContentType(DEFAULT_ABN_PROTOKOLL_CONTENT_TYPE);
        return abnahmeprotokoll;
    }

    @Before
    public void initTest() {
        abnahmeprotokoll = createEntity(em);
    }

    @Test
    @Transactional
    public void createAbnahmeprotokoll() throws Exception {
        int databaseSizeBeforeCreate = abnahmeprotokollRepository.findAll().size();

        // Create the Abnahmeprotokoll
        restAbnahmeprotokollMockMvc.perform(post("/api/abnahmeprotokolls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abnahmeprotokoll)))
            .andExpect(status().isCreated());

        // Validate the Abnahmeprotokoll in the database
        List<Abnahmeprotokoll> abnahmeprotokollList = abnahmeprotokollRepository.findAll();
        assertThat(abnahmeprotokollList).hasSize(databaseSizeBeforeCreate + 1);
        Abnahmeprotokoll testAbnahmeprotokoll = abnahmeprotokollList.get(abnahmeprotokollList.size() - 1);
        assertThat(testAbnahmeprotokoll.getObjektNummer()).isEqualTo(DEFAULT_OBJEKT_NUMMER);
        assertThat(testAbnahmeprotokoll.getDatumLeistung()).isEqualTo(DEFAULT_DATUM_LEISTUNG);
        assertThat(testAbnahmeprotokoll.getFirma()).isEqualTo(DEFAULT_FIRMA);
        assertThat(testAbnahmeprotokoll.getKundeAsp()).isEqualTo(DEFAULT_KUNDE_ASP);
        assertThat(testAbnahmeprotokoll.getFirmaAsp()).isEqualTo(DEFAULT_FIRMA_ASP);
        assertThat(testAbnahmeprotokoll.isSise()).isEqualTo(DEFAULT_SISE);
        assertThat(testAbnahmeprotokoll.getZusatzleistungen()).isEqualTo(DEFAULT_ZUSATZLEISTUNGEN);
        assertThat(testAbnahmeprotokoll.getAntenne()).isEqualTo(DEFAULT_ANTENNE);
        assertThat(testAbnahmeprotokoll.getGprsSignal()).isEqualTo(DEFAULT_GPRS_SIGNAL);
        assertThat(testAbnahmeprotokoll.isAlarmTest()).isEqualTo(DEFAULT_ALARM_TEST);
        assertThat(testAbnahmeprotokoll.getAlarmBemerkung()).isEqualTo(DEFAULT_ALARM_BEMERKUNG);
        assertThat(testAbnahmeprotokoll.getOrt()).isEqualTo(DEFAULT_ORT);
        assertThat(testAbnahmeprotokoll.getDatum()).isEqualTo(DEFAULT_DATUM);
        assertThat(testAbnahmeprotokoll.getAbnProtokoll()).isEqualTo(DEFAULT_ABN_PROTOKOLL);
        assertThat(testAbnahmeprotokoll.getAbnProtokollContentType()).isEqualTo(DEFAULT_ABN_PROTOKOLL_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createAbnahmeprotokollWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = abnahmeprotokollRepository.findAll().size();

        // Create the Abnahmeprotokoll with an existing ID
        abnahmeprotokoll.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAbnahmeprotokollMockMvc.perform(post("/api/abnahmeprotokolls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abnahmeprotokoll)))
            .andExpect(status().isBadRequest());

        // Validate the Abnahmeprotokoll in the database
        List<Abnahmeprotokoll> abnahmeprotokollList = abnahmeprotokollRepository.findAll();
        assertThat(abnahmeprotokollList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAbnahmeprotokolls() throws Exception {
        // Initialize the database
        abnahmeprotokollRepository.saveAndFlush(abnahmeprotokoll);

        // Get all the abnahmeprotokollList
        restAbnahmeprotokollMockMvc.perform(get("/api/abnahmeprotokolls?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(abnahmeprotokoll.getId().intValue())))
            .andExpect(jsonPath("$.[*].objektNummer").value(hasItem(DEFAULT_OBJEKT_NUMMER.toString())))
            .andExpect(jsonPath("$.[*].datumLeistung").value(hasItem(DEFAULT_DATUM_LEISTUNG.toString())))
            .andExpect(jsonPath("$.[*].firma").value(hasItem(DEFAULT_FIRMA.toString())))
            .andExpect(jsonPath("$.[*].kundeAsp").value(hasItem(DEFAULT_KUNDE_ASP.toString())))
            .andExpect(jsonPath("$.[*].firmaAsp").value(hasItem(DEFAULT_FIRMA_ASP.toString())))
            .andExpect(jsonPath("$.[*].sise").value(hasItem(DEFAULT_SISE.booleanValue())))
            .andExpect(jsonPath("$.[*].zusatzleistungen").value(hasItem(DEFAULT_ZUSATZLEISTUNGEN.toString())))
            .andExpect(jsonPath("$.[*].antenne").value(hasItem(DEFAULT_ANTENNE.toString())))
            .andExpect(jsonPath("$.[*].gprsSignal").value(hasItem(DEFAULT_GPRS_SIGNAL.toString())))
            .andExpect(jsonPath("$.[*].alarmTest").value(hasItem(DEFAULT_ALARM_TEST.booleanValue())))
            .andExpect(jsonPath("$.[*].alarmBemerkung").value(hasItem(DEFAULT_ALARM_BEMERKUNG.toString())))
            .andExpect(jsonPath("$.[*].ort").value(hasItem(DEFAULT_ORT.toString())))
            .andExpect(jsonPath("$.[*].datum").value(hasItem(DEFAULT_DATUM.toString())))
            .andExpect(jsonPath("$.[*].abnProtokollContentType").value(hasItem(DEFAULT_ABN_PROTOKOLL_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].abnProtokoll").value(hasItem(Base64Utils.encodeToString(DEFAULT_ABN_PROTOKOLL))));
    }
    
    @Test
    @Transactional
    public void getAbnahmeprotokoll() throws Exception {
        // Initialize the database
        abnahmeprotokollRepository.saveAndFlush(abnahmeprotokoll);

        // Get the abnahmeprotokoll
        restAbnahmeprotokollMockMvc.perform(get("/api/abnahmeprotokolls/{id}", abnahmeprotokoll.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(abnahmeprotokoll.getId().intValue()))
            .andExpect(jsonPath("$.objektNummer").value(DEFAULT_OBJEKT_NUMMER.toString()))
            .andExpect(jsonPath("$.datumLeistung").value(DEFAULT_DATUM_LEISTUNG.toString()))
            .andExpect(jsonPath("$.firma").value(DEFAULT_FIRMA.toString()))
            .andExpect(jsonPath("$.kundeAsp").value(DEFAULT_KUNDE_ASP.toString()))
            .andExpect(jsonPath("$.firmaAsp").value(DEFAULT_FIRMA_ASP.toString()))
            .andExpect(jsonPath("$.sise").value(DEFAULT_SISE.booleanValue()))
            .andExpect(jsonPath("$.zusatzleistungen").value(DEFAULT_ZUSATZLEISTUNGEN.toString()))
            .andExpect(jsonPath("$.antenne").value(DEFAULT_ANTENNE.toString()))
            .andExpect(jsonPath("$.gprsSignal").value(DEFAULT_GPRS_SIGNAL.toString()))
            .andExpect(jsonPath("$.alarmTest").value(DEFAULT_ALARM_TEST.booleanValue()))
            .andExpect(jsonPath("$.alarmBemerkung").value(DEFAULT_ALARM_BEMERKUNG.toString()))
            .andExpect(jsonPath("$.ort").value(DEFAULT_ORT.toString()))
            .andExpect(jsonPath("$.datum").value(DEFAULT_DATUM.toString()))
            .andExpect(jsonPath("$.abnProtokollContentType").value(DEFAULT_ABN_PROTOKOLL_CONTENT_TYPE))
            .andExpect(jsonPath("$.abnProtokoll").value(Base64Utils.encodeToString(DEFAULT_ABN_PROTOKOLL)));
    }

    @Test
    @Transactional
    public void getNonExistingAbnahmeprotokoll() throws Exception {
        // Get the abnahmeprotokoll
        restAbnahmeprotokollMockMvc.perform(get("/api/abnahmeprotokolls/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAbnahmeprotokoll() throws Exception {
        // Initialize the database
        abnahmeprotokollRepository.saveAndFlush(abnahmeprotokoll);

        int databaseSizeBeforeUpdate = abnahmeprotokollRepository.findAll().size();

        // Update the abnahmeprotokoll
        Abnahmeprotokoll updatedAbnahmeprotokoll = abnahmeprotokollRepository.findById(abnahmeprotokoll.getId()).get();
        // Disconnect from session so that the updates on updatedAbnahmeprotokoll are not directly saved in db
        em.detach(updatedAbnahmeprotokoll);
        updatedAbnahmeprotokoll
            .objektNummer(UPDATED_OBJEKT_NUMMER)
            .datumLeistung(UPDATED_DATUM_LEISTUNG)
            .firma(UPDATED_FIRMA)
            .kundeAsp(UPDATED_KUNDE_ASP)
            .firmaAsp(UPDATED_FIRMA_ASP)
            .sise(UPDATED_SISE)
            .zusatzleistungen(UPDATED_ZUSATZLEISTUNGEN)
            .antenne(UPDATED_ANTENNE)
            .gprsSignal(UPDATED_GPRS_SIGNAL)
            .alarmTest(UPDATED_ALARM_TEST)
            .alarmBemerkung(UPDATED_ALARM_BEMERKUNG)
            .ort(UPDATED_ORT)
            .datum(UPDATED_DATUM)
            .abnProtokoll(UPDATED_ABN_PROTOKOLL)
            .abnProtokollContentType(UPDATED_ABN_PROTOKOLL_CONTENT_TYPE);

        restAbnahmeprotokollMockMvc.perform(put("/api/abnahmeprotokolls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAbnahmeprotokoll)))
            .andExpect(status().isOk());

        // Validate the Abnahmeprotokoll in the database
        List<Abnahmeprotokoll> abnahmeprotokollList = abnahmeprotokollRepository.findAll();
        assertThat(abnahmeprotokollList).hasSize(databaseSizeBeforeUpdate);
        Abnahmeprotokoll testAbnahmeprotokoll = abnahmeprotokollList.get(abnahmeprotokollList.size() - 1);
        assertThat(testAbnahmeprotokoll.getObjektNummer()).isEqualTo(UPDATED_OBJEKT_NUMMER);
        assertThat(testAbnahmeprotokoll.getDatumLeistung()).isEqualTo(UPDATED_DATUM_LEISTUNG);
        assertThat(testAbnahmeprotokoll.getFirma()).isEqualTo(UPDATED_FIRMA);
        assertThat(testAbnahmeprotokoll.getKundeAsp()).isEqualTo(UPDATED_KUNDE_ASP);
        assertThat(testAbnahmeprotokoll.getFirmaAsp()).isEqualTo(UPDATED_FIRMA_ASP);
        assertThat(testAbnahmeprotokoll.isSise()).isEqualTo(UPDATED_SISE);
        assertThat(testAbnahmeprotokoll.getZusatzleistungen()).isEqualTo(UPDATED_ZUSATZLEISTUNGEN);
        assertThat(testAbnahmeprotokoll.getAntenne()).isEqualTo(UPDATED_ANTENNE);
        assertThat(testAbnahmeprotokoll.getGprsSignal()).isEqualTo(UPDATED_GPRS_SIGNAL);
        assertThat(testAbnahmeprotokoll.isAlarmTest()).isEqualTo(UPDATED_ALARM_TEST);
        assertThat(testAbnahmeprotokoll.getAlarmBemerkung()).isEqualTo(UPDATED_ALARM_BEMERKUNG);
        assertThat(testAbnahmeprotokoll.getOrt()).isEqualTo(UPDATED_ORT);
        assertThat(testAbnahmeprotokoll.getDatum()).isEqualTo(UPDATED_DATUM);
        assertThat(testAbnahmeprotokoll.getAbnProtokoll()).isEqualTo(UPDATED_ABN_PROTOKOLL);
        assertThat(testAbnahmeprotokoll.getAbnProtokollContentType()).isEqualTo(UPDATED_ABN_PROTOKOLL_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingAbnahmeprotokoll() throws Exception {
        int databaseSizeBeforeUpdate = abnahmeprotokollRepository.findAll().size();

        // Create the Abnahmeprotokoll

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAbnahmeprotokollMockMvc.perform(put("/api/abnahmeprotokolls")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(abnahmeprotokoll)))
            .andExpect(status().isBadRequest());

        // Validate the Abnahmeprotokoll in the database
        List<Abnahmeprotokoll> abnahmeprotokollList = abnahmeprotokollRepository.findAll();
        assertThat(abnahmeprotokollList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAbnahmeprotokoll() throws Exception {
        // Initialize the database
        abnahmeprotokollRepository.saveAndFlush(abnahmeprotokoll);

        int databaseSizeBeforeDelete = abnahmeprotokollRepository.findAll().size();

        // Get the abnahmeprotokoll
        restAbnahmeprotokollMockMvc.perform(delete("/api/abnahmeprotokolls/{id}", abnahmeprotokoll.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Abnahmeprotokoll> abnahmeprotokollList = abnahmeprotokollRepository.findAll();
        assertThat(abnahmeprotokollList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Abnahmeprotokoll.class);
        Abnahmeprotokoll abnahmeprotokoll1 = new Abnahmeprotokoll();
        abnahmeprotokoll1.setId(1L);
        Abnahmeprotokoll abnahmeprotokoll2 = new Abnahmeprotokoll();
        abnahmeprotokoll2.setId(abnahmeprotokoll1.getId());
        assertThat(abnahmeprotokoll1).isEqualTo(abnahmeprotokoll2);
        abnahmeprotokoll2.setId(2L);
        assertThat(abnahmeprotokoll1).isNotEqualTo(abnahmeprotokoll2);
        abnahmeprotokoll1.setId(null);
        assertThat(abnahmeprotokoll1).isNotEqualTo(abnahmeprotokoll2);
    }
}
