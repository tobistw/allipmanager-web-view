package de.stwgmbh.app.web.rest;

import de.stwgmbh.app.AllipmanagerwebviewApp;

import de.stwgmbh.app.domain.TblVertrag;
import de.stwgmbh.app.repository.TblVertragRepository;
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
 * Test class for the TblVertragResource REST controller.
 *
 * @see TblVertragResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AllipmanagerwebviewApp.class)
public class TblVertragResourceIntTest {

    private static final String DEFAULT_VER_HV = "AAAAAAAAAA";
    private static final String UPDATED_VER_HV = "BBBBBBBBBB";

    private static final String DEFAULT_VER_EQUIPMENT_NR = "AAAAAAAAAA";
    private static final String UPDATED_VER_EQUIPMENT_NR = "BBBBBBBBBB";

    private static final String DEFAULT_VER_OBJEKT_NR = "AAAAAAAAAA";
    private static final String UPDATED_VER_OBJEKT_NR = "BBBBBBBBBB";

    private static final String DEFAULT_VER_TEILMELDER = "AAAAAAAAAA";
    private static final String UPDATED_VER_TEILMELDER = "BBBBBBBBBB";

    private static final String DEFAULT_VER_VP_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VER_VP_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VER_VP_NAME_2 = "AAAAAAAAAA";
    private static final String UPDATED_VER_VP_NAME_2 = "BBBBBBBBBB";

    private static final String DEFAULT_VER_VP_STRASSE = "AAAAAAAAAA";
    private static final String UPDATED_VER_VP_STRASSE = "BBBBBBBBBB";

    private static final String DEFAULT_VER_VP_PLZ = "AAAAAAAAAA";
    private static final String UPDATED_VER_VP_PLZ = "BBBBBBBBBB";

    private static final String DEFAULT_VER_VP_ORT = "AAAAAAAAAA";
    private static final String UPDATED_VER_VP_ORT = "BBBBBBBBBB";

    private static final String DEFAULT_VER_OBJ_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VER_OBJ_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_VER_OBJ_STRASSE = "AAAAAAAAAA";
    private static final String UPDATED_VER_OBJ_STRASSE = "BBBBBBBBBB";

    private static final String DEFAULT_VER_OBJ_PLZ = "AAAAAAAAAA";
    private static final String UPDATED_VER_OBJ_PLZ = "BBBBBBBBBB";

    private static final String DEFAULT_VER_OBJ_ORT = "AAAAAAAAAA";
    private static final String UPDATED_VER_OBJ_ORT = "BBBBBBBBBB";

    private static final String DEFAULT_VER_SISE_VERTRAG = "AAAAAAAAAA";
    private static final String UPDATED_VER_SISE_VERTRAG = "BBBBBBBBBB";

    private static final String DEFAULT_VER_SISE_TYP = "AAAAAAAAAA";
    private static final String UPDATED_VER_SISE_TYP = "BBBBBBBBBB";

    private static final String DEFAULT_VER_KONZ_ID = "AAAAAAAAAA";
    private static final String UPDATED_VER_KONZ_ID = "BBBBBBBBBB";

    private static final String DEFAULT_VER_WARTUNG = "AAAAAAAAAA";
    private static final String UPDATED_VER_WARTUNG = "BBBBBBBBBB";

    private static final Boolean DEFAULT_VER_MARKIEREN = false;
    private static final Boolean UPDATED_VER_MARKIEREN = true;

    private static final Boolean DEFAULT_VER_DUMMY = false;
    private static final Boolean UPDATED_VER_DUMMY = true;

    @Autowired
    private TblVertragRepository tblVertragRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTblVertragMockMvc;

    private TblVertrag tblVertrag;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TblVertragResource tblVertragResource = new TblVertragResource(tblVertragRepository);
        this.restTblVertragMockMvc = MockMvcBuilders.standaloneSetup(tblVertragResource)
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
    public static TblVertrag createEntity(EntityManager em) {
        TblVertrag tblVertrag = new TblVertrag()
            .verHv(DEFAULT_VER_HV)
            .verEquipmentNr(DEFAULT_VER_EQUIPMENT_NR)
            .verObjektNr(DEFAULT_VER_OBJEKT_NR)
            .verTeilmelder(DEFAULT_VER_TEILMELDER)
            .verVpName(DEFAULT_VER_VP_NAME)
            .verVpName2(DEFAULT_VER_VP_NAME_2)
            .verVpStrasse(DEFAULT_VER_VP_STRASSE)
            .verVpPlz(DEFAULT_VER_VP_PLZ)
            .verVpOrt(DEFAULT_VER_VP_ORT)
            .verObjName(DEFAULT_VER_OBJ_NAME)
            .verObjStrasse(DEFAULT_VER_OBJ_STRASSE)
            .verObjPlz(DEFAULT_VER_OBJ_PLZ)
            .verObjOrt(DEFAULT_VER_OBJ_ORT)
            .verSiseVertrag(DEFAULT_VER_SISE_VERTRAG)
            .verSiseTyp(DEFAULT_VER_SISE_TYP)
            .verKonzId(DEFAULT_VER_KONZ_ID)
            .verWartung(DEFAULT_VER_WARTUNG)
            .verMarkieren(DEFAULT_VER_MARKIEREN)
            .verDummy(DEFAULT_VER_DUMMY);
        return tblVertrag;
    }

    @Before
    public void initTest() {
        tblVertrag = createEntity(em);
    }

    @Test
    @Transactional
    public void createTblVertrag() throws Exception {
        int databaseSizeBeforeCreate = tblVertragRepository.findAll().size();

        // Create the TblVertrag
        restTblVertragMockMvc.perform(post("/api/tbl-vertrags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tblVertrag)))
            .andExpect(status().isCreated());

        // Validate the TblVertrag in the database
        List<TblVertrag> tblVertragList = tblVertragRepository.findAll();
        assertThat(tblVertragList).hasSize(databaseSizeBeforeCreate + 1);
        TblVertrag testTblVertrag = tblVertragList.get(tblVertragList.size() - 1);
        assertThat(testTblVertrag.getVerHv()).isEqualTo(DEFAULT_VER_HV);
        assertThat(testTblVertrag.getVerEquipmentNr()).isEqualTo(DEFAULT_VER_EQUIPMENT_NR);
        assertThat(testTblVertrag.getVerObjektNr()).isEqualTo(DEFAULT_VER_OBJEKT_NR);
        assertThat(testTblVertrag.getVerTeilmelder()).isEqualTo(DEFAULT_VER_TEILMELDER);
        assertThat(testTblVertrag.getVerVpName()).isEqualTo(DEFAULT_VER_VP_NAME);
        assertThat(testTblVertrag.getVerVpName2()).isEqualTo(DEFAULT_VER_VP_NAME_2);
        assertThat(testTblVertrag.getVerVpStrasse()).isEqualTo(DEFAULT_VER_VP_STRASSE);
        assertThat(testTblVertrag.getVerVpPlz()).isEqualTo(DEFAULT_VER_VP_PLZ);
        assertThat(testTblVertrag.getVerVpOrt()).isEqualTo(DEFAULT_VER_VP_ORT);
        assertThat(testTblVertrag.getVerObjName()).isEqualTo(DEFAULT_VER_OBJ_NAME);
        assertThat(testTblVertrag.getVerObjStrasse()).isEqualTo(DEFAULT_VER_OBJ_STRASSE);
        assertThat(testTblVertrag.getVerObjPlz()).isEqualTo(DEFAULT_VER_OBJ_PLZ);
        assertThat(testTblVertrag.getVerObjOrt()).isEqualTo(DEFAULT_VER_OBJ_ORT);
        assertThat(testTblVertrag.getVerSiseVertrag()).isEqualTo(DEFAULT_VER_SISE_VERTRAG);
        assertThat(testTblVertrag.getVerSiseTyp()).isEqualTo(DEFAULT_VER_SISE_TYP);
        assertThat(testTblVertrag.getVerKonzId()).isEqualTo(DEFAULT_VER_KONZ_ID);
        assertThat(testTblVertrag.getVerWartung()).isEqualTo(DEFAULT_VER_WARTUNG);
        assertThat(testTblVertrag.isVerMarkieren()).isEqualTo(DEFAULT_VER_MARKIEREN);
        assertThat(testTblVertrag.isVerDummy()).isEqualTo(DEFAULT_VER_DUMMY);
    }

    @Test
    @Transactional
    public void createTblVertragWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tblVertragRepository.findAll().size();

        // Create the TblVertrag with an existing ID
        tblVertrag.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTblVertragMockMvc.perform(post("/api/tbl-vertrags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tblVertrag)))
            .andExpect(status().isBadRequest());

        // Validate the TblVertrag in the database
        List<TblVertrag> tblVertragList = tblVertragRepository.findAll();
        assertThat(tblVertragList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTblVertrags() throws Exception {
        // Initialize the database
        tblVertragRepository.saveAndFlush(tblVertrag);

        // Get all the tblVertragList
        restTblVertragMockMvc.perform(get("/api/tbl-vertrags?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tblVertrag.getId().intValue())))
            .andExpect(jsonPath("$.[*].verHv").value(hasItem(DEFAULT_VER_HV.toString())))
            .andExpect(jsonPath("$.[*].verEquipmentNr").value(hasItem(DEFAULT_VER_EQUIPMENT_NR.toString())))
            .andExpect(jsonPath("$.[*].verObjektNr").value(hasItem(DEFAULT_VER_OBJEKT_NR.toString())))
            .andExpect(jsonPath("$.[*].verTeilmelder").value(hasItem(DEFAULT_VER_TEILMELDER.toString())))
            .andExpect(jsonPath("$.[*].verVpName").value(hasItem(DEFAULT_VER_VP_NAME.toString())))
            .andExpect(jsonPath("$.[*].verVpName2").value(hasItem(DEFAULT_VER_VP_NAME_2.toString())))
            .andExpect(jsonPath("$.[*].verVpStrasse").value(hasItem(DEFAULT_VER_VP_STRASSE.toString())))
            .andExpect(jsonPath("$.[*].verVpPlz").value(hasItem(DEFAULT_VER_VP_PLZ.toString())))
            .andExpect(jsonPath("$.[*].verVpOrt").value(hasItem(DEFAULT_VER_VP_ORT.toString())))
            .andExpect(jsonPath("$.[*].verObjName").value(hasItem(DEFAULT_VER_OBJ_NAME.toString())))
            .andExpect(jsonPath("$.[*].verObjStrasse").value(hasItem(DEFAULT_VER_OBJ_STRASSE.toString())))
            .andExpect(jsonPath("$.[*].verObjPlz").value(hasItem(DEFAULT_VER_OBJ_PLZ.toString())))
            .andExpect(jsonPath("$.[*].verObjOrt").value(hasItem(DEFAULT_VER_OBJ_ORT.toString())))
            .andExpect(jsonPath("$.[*].verSiseVertrag").value(hasItem(DEFAULT_VER_SISE_VERTRAG.toString())))
            .andExpect(jsonPath("$.[*].verSiseTyp").value(hasItem(DEFAULT_VER_SISE_TYP.toString())))
            .andExpect(jsonPath("$.[*].verKonzId").value(hasItem(DEFAULT_VER_KONZ_ID.toString())))
            .andExpect(jsonPath("$.[*].verWartung").value(hasItem(DEFAULT_VER_WARTUNG.toString())))
            .andExpect(jsonPath("$.[*].verMarkieren").value(hasItem(DEFAULT_VER_MARKIEREN.booleanValue())))
            .andExpect(jsonPath("$.[*].verDummy").value(hasItem(DEFAULT_VER_DUMMY.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getTblVertrag() throws Exception {
        // Initialize the database
        tblVertragRepository.saveAndFlush(tblVertrag);

        // Get the tblVertrag
        restTblVertragMockMvc.perform(get("/api/tbl-vertrags/{id}", tblVertrag.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tblVertrag.getId().intValue()))
            .andExpect(jsonPath("$.verHv").value(DEFAULT_VER_HV.toString()))
            .andExpect(jsonPath("$.verEquipmentNr").value(DEFAULT_VER_EQUIPMENT_NR.toString()))
            .andExpect(jsonPath("$.verObjektNr").value(DEFAULT_VER_OBJEKT_NR.toString()))
            .andExpect(jsonPath("$.verTeilmelder").value(DEFAULT_VER_TEILMELDER.toString()))
            .andExpect(jsonPath("$.verVpName").value(DEFAULT_VER_VP_NAME.toString()))
            .andExpect(jsonPath("$.verVpName2").value(DEFAULT_VER_VP_NAME_2.toString()))
            .andExpect(jsonPath("$.verVpStrasse").value(DEFAULT_VER_VP_STRASSE.toString()))
            .andExpect(jsonPath("$.verVpPlz").value(DEFAULT_VER_VP_PLZ.toString()))
            .andExpect(jsonPath("$.verVpOrt").value(DEFAULT_VER_VP_ORT.toString()))
            .andExpect(jsonPath("$.verObjName").value(DEFAULT_VER_OBJ_NAME.toString()))
            .andExpect(jsonPath("$.verObjStrasse").value(DEFAULT_VER_OBJ_STRASSE.toString()))
            .andExpect(jsonPath("$.verObjPlz").value(DEFAULT_VER_OBJ_PLZ.toString()))
            .andExpect(jsonPath("$.verObjOrt").value(DEFAULT_VER_OBJ_ORT.toString()))
            .andExpect(jsonPath("$.verSiseVertrag").value(DEFAULT_VER_SISE_VERTRAG.toString()))
            .andExpect(jsonPath("$.verSiseTyp").value(DEFAULT_VER_SISE_TYP.toString()))
            .andExpect(jsonPath("$.verKonzId").value(DEFAULT_VER_KONZ_ID.toString()))
            .andExpect(jsonPath("$.verWartung").value(DEFAULT_VER_WARTUNG.toString()))
            .andExpect(jsonPath("$.verMarkieren").value(DEFAULT_VER_MARKIEREN.booleanValue()))
            .andExpect(jsonPath("$.verDummy").value(DEFAULT_VER_DUMMY.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTblVertrag() throws Exception {
        // Get the tblVertrag
        restTblVertragMockMvc.perform(get("/api/tbl-vertrags/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTblVertrag() throws Exception {
        // Initialize the database
        tblVertragRepository.saveAndFlush(tblVertrag);

        int databaseSizeBeforeUpdate = tblVertragRepository.findAll().size();

        // Update the tblVertrag
        TblVertrag updatedTblVertrag = tblVertragRepository.findById(tblVertrag.getId()).get();
        // Disconnect from session so that the updates on updatedTblVertrag are not directly saved in db
        em.detach(updatedTblVertrag);
        updatedTblVertrag
            .verHv(UPDATED_VER_HV)
            .verEquipmentNr(UPDATED_VER_EQUIPMENT_NR)
            .verObjektNr(UPDATED_VER_OBJEKT_NR)
            .verTeilmelder(UPDATED_VER_TEILMELDER)
            .verVpName(UPDATED_VER_VP_NAME)
            .verVpName2(UPDATED_VER_VP_NAME_2)
            .verVpStrasse(UPDATED_VER_VP_STRASSE)
            .verVpPlz(UPDATED_VER_VP_PLZ)
            .verVpOrt(UPDATED_VER_VP_ORT)
            .verObjName(UPDATED_VER_OBJ_NAME)
            .verObjStrasse(UPDATED_VER_OBJ_STRASSE)
            .verObjPlz(UPDATED_VER_OBJ_PLZ)
            .verObjOrt(UPDATED_VER_OBJ_ORT)
            .verSiseVertrag(UPDATED_VER_SISE_VERTRAG)
            .verSiseTyp(UPDATED_VER_SISE_TYP)
            .verKonzId(UPDATED_VER_KONZ_ID)
            .verWartung(UPDATED_VER_WARTUNG)
            .verMarkieren(UPDATED_VER_MARKIEREN)
            .verDummy(UPDATED_VER_DUMMY);

        restTblVertragMockMvc.perform(put("/api/tbl-vertrags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTblVertrag)))
            .andExpect(status().isOk());

        // Validate the TblVertrag in the database
        List<TblVertrag> tblVertragList = tblVertragRepository.findAll();
        assertThat(tblVertragList).hasSize(databaseSizeBeforeUpdate);
        TblVertrag testTblVertrag = tblVertragList.get(tblVertragList.size() - 1);
        assertThat(testTblVertrag.getVerHv()).isEqualTo(UPDATED_VER_HV);
        assertThat(testTblVertrag.getVerEquipmentNr()).isEqualTo(UPDATED_VER_EQUIPMENT_NR);
        assertThat(testTblVertrag.getVerObjektNr()).isEqualTo(UPDATED_VER_OBJEKT_NR);
        assertThat(testTblVertrag.getVerTeilmelder()).isEqualTo(UPDATED_VER_TEILMELDER);
        assertThat(testTblVertrag.getVerVpName()).isEqualTo(UPDATED_VER_VP_NAME);
        assertThat(testTblVertrag.getVerVpName2()).isEqualTo(UPDATED_VER_VP_NAME_2);
        assertThat(testTblVertrag.getVerVpStrasse()).isEqualTo(UPDATED_VER_VP_STRASSE);
        assertThat(testTblVertrag.getVerVpPlz()).isEqualTo(UPDATED_VER_VP_PLZ);
        assertThat(testTblVertrag.getVerVpOrt()).isEqualTo(UPDATED_VER_VP_ORT);
        assertThat(testTblVertrag.getVerObjName()).isEqualTo(UPDATED_VER_OBJ_NAME);
        assertThat(testTblVertrag.getVerObjStrasse()).isEqualTo(UPDATED_VER_OBJ_STRASSE);
        assertThat(testTblVertrag.getVerObjPlz()).isEqualTo(UPDATED_VER_OBJ_PLZ);
        assertThat(testTblVertrag.getVerObjOrt()).isEqualTo(UPDATED_VER_OBJ_ORT);
        assertThat(testTblVertrag.getVerSiseVertrag()).isEqualTo(UPDATED_VER_SISE_VERTRAG);
        assertThat(testTblVertrag.getVerSiseTyp()).isEqualTo(UPDATED_VER_SISE_TYP);
        assertThat(testTblVertrag.getVerKonzId()).isEqualTo(UPDATED_VER_KONZ_ID);
        assertThat(testTblVertrag.getVerWartung()).isEqualTo(UPDATED_VER_WARTUNG);
        assertThat(testTblVertrag.isVerMarkieren()).isEqualTo(UPDATED_VER_MARKIEREN);
        assertThat(testTblVertrag.isVerDummy()).isEqualTo(UPDATED_VER_DUMMY);
    }

    @Test
    @Transactional
    public void updateNonExistingTblVertrag() throws Exception {
        int databaseSizeBeforeUpdate = tblVertragRepository.findAll().size();

        // Create the TblVertrag

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTblVertragMockMvc.perform(put("/api/tbl-vertrags")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tblVertrag)))
            .andExpect(status().isBadRequest());

        // Validate the TblVertrag in the database
        List<TblVertrag> tblVertragList = tblVertragRepository.findAll();
        assertThat(tblVertragList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTblVertrag() throws Exception {
        // Initialize the database
        tblVertragRepository.saveAndFlush(tblVertrag);

        int databaseSizeBeforeDelete = tblVertragRepository.findAll().size();

        // Get the tblVertrag
        restTblVertragMockMvc.perform(delete("/api/tbl-vertrags/{id}", tblVertrag.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TblVertrag> tblVertragList = tblVertragRepository.findAll();
        assertThat(tblVertragList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TblVertrag.class);
        TblVertrag tblVertrag1 = new TblVertrag();
        tblVertrag1.setId(1L);
        TblVertrag tblVertrag2 = new TblVertrag();
        tblVertrag2.setId(tblVertrag1.getId());
        assertThat(tblVertrag1).isEqualTo(tblVertrag2);
        tblVertrag2.setId(2L);
        assertThat(tblVertrag1).isNotEqualTo(tblVertrag2);
        tblVertrag1.setId(null);
        assertThat(tblVertrag1).isNotEqualTo(tblVertrag2);
    }
}
