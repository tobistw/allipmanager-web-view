package de.stwgmbh.app.web.rest;

import de.stwgmbh.app.AllipmanagerwebviewApp;

import de.stwgmbh.app.domain.Arbeitsbeleg;
import de.stwgmbh.app.repository.ArbeitsbelegRepository;
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

import de.stwgmbh.app.domain.enumeration.Belegstatus;
/**
 * Test class for the ArbeitsbelegResource REST controller.
 *
 * @see ArbeitsbelegResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AllipmanagerwebviewApp.class)
public class ArbeitsbelegResourceIntTest {

    private static final String DEFAULT_OBJEKT_NUMMER = "AAAAAAAAAA";
    private static final String UPDATED_OBJEKT_NUMMER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATUM_LEISTUNG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATUM_LEISTUNG = LocalDate.now(ZoneId.systemDefault());

    private static final Belegstatus DEFAULT_STATUS = Belegstatus.OFFEN;
    private static final Belegstatus UPDATED_STATUS = Belegstatus.FERTIG;

    private static final byte[] DEFAULT_ARBEITSBELEG = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ARBEITSBELEG = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_ARBEITSBELEG_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ARBEITSBELEG_CONTENT_TYPE = "image/png";

    @Autowired
    private ArbeitsbelegRepository arbeitsbelegRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restArbeitsbelegMockMvc;

    private Arbeitsbeleg arbeitsbeleg;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ArbeitsbelegResource arbeitsbelegResource = new ArbeitsbelegResource(arbeitsbelegRepository);
        this.restArbeitsbelegMockMvc = MockMvcBuilders.standaloneSetup(arbeitsbelegResource)
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
    public static Arbeitsbeleg createEntity(EntityManager em) {
        Arbeitsbeleg arbeitsbeleg = new Arbeitsbeleg()
            .objektNummer(DEFAULT_OBJEKT_NUMMER)
            .datumLeistung(DEFAULT_DATUM_LEISTUNG)
            .status(DEFAULT_STATUS)
            .arbeitsbeleg(DEFAULT_ARBEITSBELEG)
            .arbeitsbelegContentType(DEFAULT_ARBEITSBELEG_CONTENT_TYPE);
        return arbeitsbeleg;
    }

    @Before
    public void initTest() {
        arbeitsbeleg = createEntity(em);
    }

    @Test
    @Transactional
    public void createArbeitsbeleg() throws Exception {
        int databaseSizeBeforeCreate = arbeitsbelegRepository.findAll().size();

        // Create the Arbeitsbeleg
        restArbeitsbelegMockMvc.perform(post("/api/arbeitsbelegs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(arbeitsbeleg)))
            .andExpect(status().isCreated());

        // Validate the Arbeitsbeleg in the database
        List<Arbeitsbeleg> arbeitsbelegList = arbeitsbelegRepository.findAll();
        assertThat(arbeitsbelegList).hasSize(databaseSizeBeforeCreate + 1);
        Arbeitsbeleg testArbeitsbeleg = arbeitsbelegList.get(arbeitsbelegList.size() - 1);
        assertThat(testArbeitsbeleg.getObjektNummer()).isEqualTo(DEFAULT_OBJEKT_NUMMER);
        assertThat(testArbeitsbeleg.getDatumLeistung()).isEqualTo(DEFAULT_DATUM_LEISTUNG);
        assertThat(testArbeitsbeleg.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testArbeitsbeleg.getArbeitsbeleg()).isEqualTo(DEFAULT_ARBEITSBELEG);
        assertThat(testArbeitsbeleg.getArbeitsbelegContentType()).isEqualTo(DEFAULT_ARBEITSBELEG_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createArbeitsbelegWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = arbeitsbelegRepository.findAll().size();

        // Create the Arbeitsbeleg with an existing ID
        arbeitsbeleg.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restArbeitsbelegMockMvc.perform(post("/api/arbeitsbelegs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(arbeitsbeleg)))
            .andExpect(status().isBadRequest());

        // Validate the Arbeitsbeleg in the database
        List<Arbeitsbeleg> arbeitsbelegList = arbeitsbelegRepository.findAll();
        assertThat(arbeitsbelegList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllArbeitsbelegs() throws Exception {
        // Initialize the database
        arbeitsbelegRepository.saveAndFlush(arbeitsbeleg);

        // Get all the arbeitsbelegList
        restArbeitsbelegMockMvc.perform(get("/api/arbeitsbelegs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(arbeitsbeleg.getId().intValue())))
            .andExpect(jsonPath("$.[*].objektNummer").value(hasItem(DEFAULT_OBJEKT_NUMMER.toString())))
            .andExpect(jsonPath("$.[*].datumLeistung").value(hasItem(DEFAULT_DATUM_LEISTUNG.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].arbeitsbelegContentType").value(hasItem(DEFAULT_ARBEITSBELEG_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].arbeitsbeleg").value(hasItem(Base64Utils.encodeToString(DEFAULT_ARBEITSBELEG))));
    }
    
    @Test
    @Transactional
    public void getArbeitsbeleg() throws Exception {
        // Initialize the database
        arbeitsbelegRepository.saveAndFlush(arbeitsbeleg);

        // Get the arbeitsbeleg
        restArbeitsbelegMockMvc.perform(get("/api/arbeitsbelegs/{id}", arbeitsbeleg.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(arbeitsbeleg.getId().intValue()))
            .andExpect(jsonPath("$.objektNummer").value(DEFAULT_OBJEKT_NUMMER.toString()))
            .andExpect(jsonPath("$.datumLeistung").value(DEFAULT_DATUM_LEISTUNG.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.arbeitsbelegContentType").value(DEFAULT_ARBEITSBELEG_CONTENT_TYPE))
            .andExpect(jsonPath("$.arbeitsbeleg").value(Base64Utils.encodeToString(DEFAULT_ARBEITSBELEG)));
    }

    @Test
    @Transactional
    public void getNonExistingArbeitsbeleg() throws Exception {
        // Get the arbeitsbeleg
        restArbeitsbelegMockMvc.perform(get("/api/arbeitsbelegs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateArbeitsbeleg() throws Exception {
        // Initialize the database
        arbeitsbelegRepository.saveAndFlush(arbeitsbeleg);

        int databaseSizeBeforeUpdate = arbeitsbelegRepository.findAll().size();

        // Update the arbeitsbeleg
        Arbeitsbeleg updatedArbeitsbeleg = arbeitsbelegRepository.findById(arbeitsbeleg.getId()).get();
        // Disconnect from session so that the updates on updatedArbeitsbeleg are not directly saved in db
        em.detach(updatedArbeitsbeleg);
        updatedArbeitsbeleg
            .objektNummer(UPDATED_OBJEKT_NUMMER)
            .datumLeistung(UPDATED_DATUM_LEISTUNG)
            .status(UPDATED_STATUS)
            .arbeitsbeleg(UPDATED_ARBEITSBELEG)
            .arbeitsbelegContentType(UPDATED_ARBEITSBELEG_CONTENT_TYPE);

        restArbeitsbelegMockMvc.perform(put("/api/arbeitsbelegs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedArbeitsbeleg)))
            .andExpect(status().isOk());

        // Validate the Arbeitsbeleg in the database
        List<Arbeitsbeleg> arbeitsbelegList = arbeitsbelegRepository.findAll();
        assertThat(arbeitsbelegList).hasSize(databaseSizeBeforeUpdate);
        Arbeitsbeleg testArbeitsbeleg = arbeitsbelegList.get(arbeitsbelegList.size() - 1);
        assertThat(testArbeitsbeleg.getObjektNummer()).isEqualTo(UPDATED_OBJEKT_NUMMER);
        assertThat(testArbeitsbeleg.getDatumLeistung()).isEqualTo(UPDATED_DATUM_LEISTUNG);
        assertThat(testArbeitsbeleg.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testArbeitsbeleg.getArbeitsbeleg()).isEqualTo(UPDATED_ARBEITSBELEG);
        assertThat(testArbeitsbeleg.getArbeitsbelegContentType()).isEqualTo(UPDATED_ARBEITSBELEG_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingArbeitsbeleg() throws Exception {
        int databaseSizeBeforeUpdate = arbeitsbelegRepository.findAll().size();

        // Create the Arbeitsbeleg

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restArbeitsbelegMockMvc.perform(put("/api/arbeitsbelegs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(arbeitsbeleg)))
            .andExpect(status().isBadRequest());

        // Validate the Arbeitsbeleg in the database
        List<Arbeitsbeleg> arbeitsbelegList = arbeitsbelegRepository.findAll();
        assertThat(arbeitsbelegList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteArbeitsbeleg() throws Exception {
        // Initialize the database
        arbeitsbelegRepository.saveAndFlush(arbeitsbeleg);

        int databaseSizeBeforeDelete = arbeitsbelegRepository.findAll().size();

        // Get the arbeitsbeleg
        restArbeitsbelegMockMvc.perform(delete("/api/arbeitsbelegs/{id}", arbeitsbeleg.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Arbeitsbeleg> arbeitsbelegList = arbeitsbelegRepository.findAll();
        assertThat(arbeitsbelegList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Arbeitsbeleg.class);
        Arbeitsbeleg arbeitsbeleg1 = new Arbeitsbeleg();
        arbeitsbeleg1.setId(1L);
        Arbeitsbeleg arbeitsbeleg2 = new Arbeitsbeleg();
        arbeitsbeleg2.setId(arbeitsbeleg1.getId());
        assertThat(arbeitsbeleg1).isEqualTo(arbeitsbeleg2);
        arbeitsbeleg2.setId(2L);
        assertThat(arbeitsbeleg1).isNotEqualTo(arbeitsbeleg2);
        arbeitsbeleg1.setId(null);
        assertThat(arbeitsbeleg1).isNotEqualTo(arbeitsbeleg2);
    }
}
