package de.stwgmbh.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Abnahmeprotokoll.
 */
@Entity
@Table(name = "abnahmeprotokoll")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Abnahmeprotokoll implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "objekt_nummer")
    private String objektNummer;

    @Column(name = "datum_leistung")
    private LocalDate datumLeistung;

    @Column(name = "firma")
    private String firma;

    @Column(name = "kunde_asp")
    private String kundeAsp;

    @Column(name = "firma_asp")
    private String firmaAsp;

    @Column(name = "sise")
    private Boolean sise;

    @Column(name = "zusatzleistungen")
    private String zusatzleistungen;

    @Column(name = "antenne")
    private String antenne;

    @Column(name = "gprs_signal")
    private String gprsSignal;

    @Column(name = "alarm_test")
    private Boolean alarmTest;

    @Column(name = "alarm_bemerkung")
    private String alarmBemerkung;

    @Column(name = "ort")
    private String ort;

    @Column(name = "datum")
    private LocalDate datum;

    @Lob
    @Column(name = "abn_protokoll")
    private byte[] abnProtokoll;

    @Column(name = "abn_protokoll_content_type")
    private String abnProtokollContentType;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("")
    private TblTermine abnahmeprotokoll;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjektNummer() {
        return objektNummer;
    }

    public Abnahmeprotokoll objektNummer(String objektNummer) {
        this.objektNummer = objektNummer;
        return this;
    }

    public void setObjektNummer(String objektNummer) {
        this.objektNummer = objektNummer;
    }

    public LocalDate getDatumLeistung() {
        return datumLeistung;
    }

    public Abnahmeprotokoll datumLeistung(LocalDate datumLeistung) {
        this.datumLeistung = datumLeistung;
        return this;
    }

    public void setDatumLeistung(LocalDate datumLeistung) {
        this.datumLeistung = datumLeistung;
    }

    public String getFirma() {
        return firma;
    }

    public Abnahmeprotokoll firma(String firma) {
        this.firma = firma;
        return this;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getKundeAsp() {
        return kundeAsp;
    }

    public Abnahmeprotokoll kundeAsp(String kundeAsp) {
        this.kundeAsp = kundeAsp;
        return this;
    }

    public void setKundeAsp(String kundeAsp) {
        this.kundeAsp = kundeAsp;
    }

    public String getFirmaAsp() {
        return firmaAsp;
    }

    public Abnahmeprotokoll firmaAsp(String firmaAsp) {
        this.firmaAsp = firmaAsp;
        return this;
    }

    public void setFirmaAsp(String firmaAsp) {
        this.firmaAsp = firmaAsp;
    }

    public Boolean isSise() {
        return sise;
    }

    public Abnahmeprotokoll sise(Boolean sise) {
        this.sise = sise;
        return this;
    }

    public void setSise(Boolean sise) {
        this.sise = sise;
    }

    public String getZusatzleistungen() {
        return zusatzleistungen;
    }

    public Abnahmeprotokoll zusatzleistungen(String zusatzleistungen) {
        this.zusatzleistungen = zusatzleistungen;
        return this;
    }

    public void setZusatzleistungen(String zusatzleistungen) {
        this.zusatzleistungen = zusatzleistungen;
    }

    public String getAntenne() {
        return antenne;
    }

    public Abnahmeprotokoll antenne(String antenne) {
        this.antenne = antenne;
        return this;
    }

    public void setAntenne(String antenne) {
        this.antenne = antenne;
    }

    public String getGprsSignal() {
        return gprsSignal;
    }

    public Abnahmeprotokoll gprsSignal(String gprsSignal) {
        this.gprsSignal = gprsSignal;
        return this;
    }

    public void setGprsSignal(String gprsSignal) {
        this.gprsSignal = gprsSignal;
    }

    public Boolean isAlarmTest() {
        return alarmTest;
    }

    public Abnahmeprotokoll alarmTest(Boolean alarmTest) {
        this.alarmTest = alarmTest;
        return this;
    }

    public void setAlarmTest(Boolean alarmTest) {
        this.alarmTest = alarmTest;
    }

    public String getAlarmBemerkung() {
        return alarmBemerkung;
    }

    public Abnahmeprotokoll alarmBemerkung(String alarmBemerkung) {
        this.alarmBemerkung = alarmBemerkung;
        return this;
    }

    public void setAlarmBemerkung(String alarmBemerkung) {
        this.alarmBemerkung = alarmBemerkung;
    }

    public String getOrt() {
        return ort;
    }

    public Abnahmeprotokoll ort(String ort) {
        this.ort = ort;
        return this;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public Abnahmeprotokoll datum(LocalDate datum) {
        this.datum = datum;
        return this;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public byte[] getAbnProtokoll() {
        return abnProtokoll;
    }

    public Abnahmeprotokoll abnProtokoll(byte[] abnProtokoll) {
        this.abnProtokoll = abnProtokoll;
        return this;
    }

    public void setAbnProtokoll(byte[] abnProtokoll) {
        this.abnProtokoll = abnProtokoll;
    }

    public String getAbnProtokollContentType() {
        return abnProtokollContentType;
    }

    public Abnahmeprotokoll abnProtokollContentType(String abnProtokollContentType) {
        this.abnProtokollContentType = abnProtokollContentType;
        return this;
    }

    public void setAbnProtokollContentType(String abnProtokollContentType) {
        this.abnProtokollContentType = abnProtokollContentType;
    }

    public User getUser() {
        return user;
    }

    public Abnahmeprotokoll user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TblTermine getAbnahmeprotokoll() {
        return abnahmeprotokoll;
    }

    public Abnahmeprotokoll abnahmeprotokoll(TblTermine tblTermine) {
        this.abnahmeprotokoll = tblTermine;
        return this;
    }

    public void setAbnahmeprotokoll(TblTermine tblTermine) {
        this.abnahmeprotokoll = tblTermine;
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
        Abnahmeprotokoll abnahmeprotokoll = (Abnahmeprotokoll) o;
        if (abnahmeprotokoll.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), abnahmeprotokoll.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Abnahmeprotokoll{" +
            "id=" + getId() +
            ", objektNummer='" + getObjektNummer() + "'" +
            ", datumLeistung='" + getDatumLeistung() + "'" +
            ", firma='" + getFirma() + "'" +
            ", kundeAsp='" + getKundeAsp() + "'" +
            ", firmaAsp='" + getFirmaAsp() + "'" +
            ", sise='" + isSise() + "'" +
            ", zusatzleistungen='" + getZusatzleistungen() + "'" +
            ", antenne='" + getAntenne() + "'" +
            ", gprsSignal='" + getGprsSignal() + "'" +
            ", alarmTest='" + isAlarmTest() + "'" +
            ", alarmBemerkung='" + getAlarmBemerkung() + "'" +
            ", ort='" + getOrt() + "'" +
            ", datum='" + getDatum() + "'" +
            ", abnProtokoll='" + getAbnProtokoll() + "'" +
            ", abnProtokollContentType='" + getAbnProtokollContentType() + "'" +
            "}";
    }
}
