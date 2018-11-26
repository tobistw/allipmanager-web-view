package de.stwgmbh.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A TblTermine.
 */
@Entity
@Table(name = "tbl_termine")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TblTermine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ter_termian_id")
    private Long terTermianId;

    @Column(name = "ter_objekt_nr")
    private String terObjektNr;

    @Column(name = "ter_per_nr_ref")
    private String terPerNrRef;

    @Column(name = "ter_ibs_datum")
    private LocalDate terIbsDatum;

    @Column(name = "ter_ibs_uhrzeit")
    private String terIbsUhrzeit;

    @Column(name = "ter_datum_neu")
    private LocalDate terDatumNeu;

    @Column(name = "ter_bemerkung")
    private String terBemerkung;

    @Column(name = "ter_antennen")
    private Boolean terAntennen;

    @Column(name = "ter_montiert")
    private Boolean terMontiert;

    @Column(name = "ter_ibs_provisorium")
    private Boolean terIbsProvisorium;

    @Column(name = "ter_konz_id")
    private String terKonzId;

    @Column(name = "ter_status")
    private String terStatus;

    @Column(name = "ter_status_protokoll")
    private String terStatusProtokoll;

    @Column(name = "ter_kunden_info_typ")
    private String terKundenInfoTyp;

    @Column(name = "ter_kunde_informiert")
    private Boolean terKundeInformiert;

    @Column(name = "ter_kunde_bestaetigt")
    private Boolean terKundeBestaetigt;

    @Column(name = "ter_kunde_antenne_erhalten")
    private Boolean terKundeAntenneErhalten;

    @Column(name = "ter_siemens_informiert")
    private Boolean terSiemensInformiert;

    @Column(name = "ter_dsl_frist")
    private LocalDate terDslFrist;

    @Column(name = "ter_termintyp")
    private String terTermintyp;

    @Column(name = "ter_funkmessung")
    private Boolean terFunkmessung;

    @Column(name = "ter_klaerung")
    private Boolean terKlaerung;

    @Column(name = "ter_abgesagt")
    private Boolean terAbgesagt;

    @Column(name = "ter_hinweis")
    private Boolean terHinweis;

    @Column(name = "ter_sim_im_geraet")
    private Boolean terSimImGeraet;

    @OneToMany(mappedBy = "tblTermine")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TblFunkmessung> funkmessungs = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("termines")
    private TblVertrag vertrag;

    @ManyToOne
    @JsonIgnoreProperties("termins")
    private TndSiemens siemens;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTerTermianId() {
        return terTermianId;
    }

    public TblTermine terTermianId(Long terTermianId) {
        this.terTermianId = terTermianId;
        return this;
    }

    public void setTerTermianId(Long terTermianId) {
        this.terTermianId = terTermianId;
    }

    public String getTerObjektNr() {
        return terObjektNr;
    }

    public TblTermine terObjektNr(String terObjektNr) {
        this.terObjektNr = terObjektNr;
        return this;
    }

    public void setTerObjektNr(String terObjektNr) {
        this.terObjektNr = terObjektNr;
    }

    public String getTerPerNrRef() {
        return terPerNrRef;
    }

    public TblTermine terPerNrRef(String terPerNrRef) {
        this.terPerNrRef = terPerNrRef;
        return this;
    }

    public void setTerPerNrRef(String terPerNrRef) {
        this.terPerNrRef = terPerNrRef;
    }

    public LocalDate getTerIbsDatum() {
        return terIbsDatum;
    }

    public TblTermine terIbsDatum(LocalDate terIbsDatum) {
        this.terIbsDatum = terIbsDatum;
        return this;
    }

    public void setTerIbsDatum(LocalDate terIbsDatum) {
        this.terIbsDatum = terIbsDatum;
    }

    public String getTerIbsUhrzeit() {
        return terIbsUhrzeit;
    }

    public TblTermine terIbsUhrzeit(String terIbsUhrzeit) {
        this.terIbsUhrzeit = terIbsUhrzeit;
        return this;
    }

    public void setTerIbsUhrzeit(String terIbsUhrzeit) {
        this.terIbsUhrzeit = terIbsUhrzeit;
    }

    public LocalDate getTerDatumNeu() {
        return terDatumNeu;
    }

    public TblTermine terDatumNeu(LocalDate terDatumNeu) {
        this.terDatumNeu = terDatumNeu;
        return this;
    }

    public void setTerDatumNeu(LocalDate terDatumNeu) {
        this.terDatumNeu = terDatumNeu;
    }

    public String getTerBemerkung() {
        return terBemerkung;
    }

    public TblTermine terBemerkung(String terBemerkung) {
        this.terBemerkung = terBemerkung;
        return this;
    }

    public void setTerBemerkung(String terBemerkung) {
        this.terBemerkung = terBemerkung;
    }

    public Boolean isTerAntennen() {
        return terAntennen;
    }

    public TblTermine terAntennen(Boolean terAntennen) {
        this.terAntennen = terAntennen;
        return this;
    }

    public void setTerAntennen(Boolean terAntennen) {
        this.terAntennen = terAntennen;
    }

    public Boolean isTerMontiert() {
        return terMontiert;
    }

    public TblTermine terMontiert(Boolean terMontiert) {
        this.terMontiert = terMontiert;
        return this;
    }

    public void setTerMontiert(Boolean terMontiert) {
        this.terMontiert = terMontiert;
    }

    public Boolean isTerIbsProvisorium() {
        return terIbsProvisorium;
    }

    public TblTermine terIbsProvisorium(Boolean terIbsProvisorium) {
        this.terIbsProvisorium = terIbsProvisorium;
        return this;
    }

    public void setTerIbsProvisorium(Boolean terIbsProvisorium) {
        this.terIbsProvisorium = terIbsProvisorium;
    }

    public String getTerKonzId() {
        return terKonzId;
    }

    public TblTermine terKonzId(String terKonzId) {
        this.terKonzId = terKonzId;
        return this;
    }

    public void setTerKonzId(String terKonzId) {
        this.terKonzId = terKonzId;
    }

    public String getTerStatus() {
        return terStatus;
    }

    public TblTermine terStatus(String terStatus) {
        this.terStatus = terStatus;
        return this;
    }

    public void setTerStatus(String terStatus) {
        this.terStatus = terStatus;
    }

    public String getTerStatusProtokoll() {
        return terStatusProtokoll;
    }

    public TblTermine terStatusProtokoll(String terStatusProtokoll) {
        this.terStatusProtokoll = terStatusProtokoll;
        return this;
    }

    public void setTerStatusProtokoll(String terStatusProtokoll) {
        this.terStatusProtokoll = terStatusProtokoll;
    }

    public String getTerKundenInfoTyp() {
        return terKundenInfoTyp;
    }

    public TblTermine terKundenInfoTyp(String terKundenInfoTyp) {
        this.terKundenInfoTyp = terKundenInfoTyp;
        return this;
    }

    public void setTerKundenInfoTyp(String terKundenInfoTyp) {
        this.terKundenInfoTyp = terKundenInfoTyp;
    }

    public Boolean isTerKundeInformiert() {
        return terKundeInformiert;
    }

    public TblTermine terKundeInformiert(Boolean terKundeInformiert) {
        this.terKundeInformiert = terKundeInformiert;
        return this;
    }

    public void setTerKundeInformiert(Boolean terKundeInformiert) {
        this.terKundeInformiert = terKundeInformiert;
    }

    public Boolean isTerKundeBestaetigt() {
        return terKundeBestaetigt;
    }

    public TblTermine terKundeBestaetigt(Boolean terKundeBestaetigt) {
        this.terKundeBestaetigt = terKundeBestaetigt;
        return this;
    }

    public void setTerKundeBestaetigt(Boolean terKundeBestaetigt) {
        this.terKundeBestaetigt = terKundeBestaetigt;
    }

    public Boolean isTerKundeAntenneErhalten() {
        return terKundeAntenneErhalten;
    }

    public TblTermine terKundeAntenneErhalten(Boolean terKundeAntenneErhalten) {
        this.terKundeAntenneErhalten = terKundeAntenneErhalten;
        return this;
    }

    public void setTerKundeAntenneErhalten(Boolean terKundeAntenneErhalten) {
        this.terKundeAntenneErhalten = terKundeAntenneErhalten;
    }

    public Boolean isTerSiemensInformiert() {
        return terSiemensInformiert;
    }

    public TblTermine terSiemensInformiert(Boolean terSiemensInformiert) {
        this.terSiemensInformiert = terSiemensInformiert;
        return this;
    }

    public void setTerSiemensInformiert(Boolean terSiemensInformiert) {
        this.terSiemensInformiert = terSiemensInformiert;
    }

    public LocalDate getTerDslFrist() {
        return terDslFrist;
    }

    public TblTermine terDslFrist(LocalDate terDslFrist) {
        this.terDslFrist = terDslFrist;
        return this;
    }

    public void setTerDslFrist(LocalDate terDslFrist) {
        this.terDslFrist = terDslFrist;
    }

    public String getTerTermintyp() {
        return terTermintyp;
    }

    public TblTermine terTermintyp(String terTermintyp) {
        this.terTermintyp = terTermintyp;
        return this;
    }

    public void setTerTermintyp(String terTermintyp) {
        this.terTermintyp = terTermintyp;
    }

    public Boolean isTerFunkmessung() {
        return terFunkmessung;
    }

    public TblTermine terFunkmessung(Boolean terFunkmessung) {
        this.terFunkmessung = terFunkmessung;
        return this;
    }

    public void setTerFunkmessung(Boolean terFunkmessung) {
        this.terFunkmessung = terFunkmessung;
    }

    public Boolean isTerKlaerung() {
        return terKlaerung;
    }

    public TblTermine terKlaerung(Boolean terKlaerung) {
        this.terKlaerung = terKlaerung;
        return this;
    }

    public void setTerKlaerung(Boolean terKlaerung) {
        this.terKlaerung = terKlaerung;
    }

    public Boolean isTerAbgesagt() {
        return terAbgesagt;
    }

    public TblTermine terAbgesagt(Boolean terAbgesagt) {
        this.terAbgesagt = terAbgesagt;
        return this;
    }

    public void setTerAbgesagt(Boolean terAbgesagt) {
        this.terAbgesagt = terAbgesagt;
    }

    public Boolean isTerHinweis() {
        return terHinweis;
    }

    public TblTermine terHinweis(Boolean terHinweis) {
        this.terHinweis = terHinweis;
        return this;
    }

    public void setTerHinweis(Boolean terHinweis) {
        this.terHinweis = terHinweis;
    }

    public Boolean isTerSimImGeraet() {
        return terSimImGeraet;
    }

    public TblTermine terSimImGeraet(Boolean terSimImGeraet) {
        this.terSimImGeraet = terSimImGeraet;
        return this;
    }

    public void setTerSimImGeraet(Boolean terSimImGeraet) {
        this.terSimImGeraet = terSimImGeraet;
    }

    public Set<TblFunkmessung> getFunkmessungs() {
        return funkmessungs;
    }

    public TblTermine funkmessungs(Set<TblFunkmessung> tblFunkmessungs) {
        this.funkmessungs = tblFunkmessungs;
        return this;
    }

    public TblTermine addFunkmessung(TblFunkmessung tblFunkmessung) {
        this.funkmessungs.add(tblFunkmessung);
        tblFunkmessung.setTblTermine(this);
        return this;
    }

    public TblTermine removeFunkmessung(TblFunkmessung tblFunkmessung) {
        this.funkmessungs.remove(tblFunkmessung);
        tblFunkmessung.setTblTermine(null);
        return this;
    }

    public void setFunkmessungs(Set<TblFunkmessung> tblFunkmessungs) {
        this.funkmessungs = tblFunkmessungs;
    }

    public User getUser() {
        return user;
    }

    public TblTermine user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TblVertrag getVertrag() {
        return vertrag;
    }

    public TblTermine vertrag(TblVertrag tblVertrag) {
        this.vertrag = tblVertrag;
        return this;
    }

    public void setVertrag(TblVertrag tblVertrag) {
        this.vertrag = tblVertrag;
    }

    public TndSiemens getSiemens() {
        return siemens;
    }

    public TblTermine siemens(TndSiemens tndSiemens) {
        this.siemens = tndSiemens;
        return this;
    }

    public void setSiemens(TndSiemens tndSiemens) {
        this.siemens = tndSiemens;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TblTermine tblTermine = (TblTermine) o;
        if (tblTermine.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tblTermine.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TblTermine{" +
            "id=" + getId() +
            ", terTermianId=" + getTerTermianId() +
            ", terObjektNr='" + getTerObjektNr() + "'" +
            ", terPerNrRef='" + getTerPerNrRef() + "'" +
            ", terIbsDatum='" + getTerIbsDatum() + "'" +
            ", terIbsUhrzeit='" + getTerIbsUhrzeit() + "'" +
            ", terDatumNeu='" + getTerDatumNeu() + "'" +
            ", terBemerkung='" + getTerBemerkung() + "'" +
            ", terAntennen='" + isTerAntennen() + "'" +
            ", terMontiert='" + isTerMontiert() + "'" +
            ", terIbsProvisorium='" + isTerIbsProvisorium() + "'" +
            ", terKonzId='" + getTerKonzId() + "'" +
            ", terStatus='" + getTerStatus() + "'" +
            ", terStatusProtokoll='" + getTerStatusProtokoll() + "'" +
            ", terKundenInfoTyp='" + getTerKundenInfoTyp() + "'" +
            ", terKundeInformiert='" + isTerKundeInformiert() + "'" +
            ", terKundeBestaetigt='" + isTerKundeBestaetigt() + "'" +
            ", terKundeAntenneErhalten='" + isTerKundeAntenneErhalten() + "'" +
            ", terSiemensInformiert='" + isTerSiemensInformiert() + "'" +
            ", terDslFrist='" + getTerDslFrist() + "'" +
            ", terTermintyp='" + getTerTermintyp() + "'" +
            ", terFunkmessung='" + isTerFunkmessung() + "'" +
            ", terKlaerung='" + isTerKlaerung() + "'" +
            ", terAbgesagt='" + isTerAbgesagt() + "'" +
            ", terHinweis='" + isTerHinweis() + "'" +
            ", terSimImGeraet='" + isTerSimImGeraet() + "'" +
            "}";
    }
}
