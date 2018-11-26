package de.stwgmbh.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A TndSiemens.
 */
@Entity
@Table(name = "tnd_siemens")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TndSiemens implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "tnd_obj_nr")
    private String tndObjNr;

    @Column(name = "tnd_teil_obj_nr")
    private String tndTeilObjNr;

    @Column(name = "tnd_warten")
    private LocalDate tndWarten;

    @Column(name = "tnd_leitungs_nr_alt")
    private String tndLeitungsNrAlt;

    @Column(name = "tnd_leitungs_nr_neu")
    private String tndLeitungsNrNeu;

    @Column(name = "tnd_gsm_nr")
    private String tndGSMNr;

    @Column(name = "tnd_gsm_nr_2")
    private String tndGSMNr2;

    @Column(name = "tnd_wartung_bma")
    private String tndWartungBMA;

    @Column(name = "tnd_si_se")
    private String tndSiSe;

    @Column(name = "tnd_login")
    private String tndLogin;

    @Column(name = "tnd_passwort")
    private String tndPasswort;

    @Column(name = "tnd_ip")
    private String tndIP;

    @Column(name = "tnd_telekom_nr")
    private Integer tndTelekomNr;

    @Column(name = "tnd_leitungs_nr_pseudo")
    private Long tndLeitungsNrPseudo;

    @Column(name = "tnd_dsl_frist")
    private LocalDate tndDslFrist;

    @OneToMany(mappedBy = "siemens")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TblTermine> termins = new HashSet<>();
    @OneToMany(mappedBy = "siemens")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TblAsp> kontakts = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTndObjNr() {
        return tndObjNr;
    }

    public TndSiemens tndObjNr(String tndObjNr) {
        this.tndObjNr = tndObjNr;
        return this;
    }

    public void setTndObjNr(String tndObjNr) {
        this.tndObjNr = tndObjNr;
    }

    public String getTndTeilObjNr() {
        return tndTeilObjNr;
    }

    public TndSiemens tndTeilObjNr(String tndTeilObjNr) {
        this.tndTeilObjNr = tndTeilObjNr;
        return this;
    }

    public void setTndTeilObjNr(String tndTeilObjNr) {
        this.tndTeilObjNr = tndTeilObjNr;
    }

    public LocalDate getTndWarten() {
        return tndWarten;
    }

    public TndSiemens tndWarten(LocalDate tndWarten) {
        this.tndWarten = tndWarten;
        return this;
    }

    public void setTndWarten(LocalDate tndWarten) {
        this.tndWarten = tndWarten;
    }

    public String getTndLeitungsNrAlt() {
        return tndLeitungsNrAlt;
    }

    public TndSiemens tndLeitungsNrAlt(String tndLeitungsNrAlt) {
        this.tndLeitungsNrAlt = tndLeitungsNrAlt;
        return this;
    }

    public void setTndLeitungsNrAlt(String tndLeitungsNrAlt) {
        this.tndLeitungsNrAlt = tndLeitungsNrAlt;
    }

    public String getTndLeitungsNrNeu() {
        return tndLeitungsNrNeu;
    }

    public TndSiemens tndLeitungsNrNeu(String tndLeitungsNrNeu) {
        this.tndLeitungsNrNeu = tndLeitungsNrNeu;
        return this;
    }

    public void setTndLeitungsNrNeu(String tndLeitungsNrNeu) {
        this.tndLeitungsNrNeu = tndLeitungsNrNeu;
    }

    public String getTndGSMNr() {
        return tndGSMNr;
    }

    public TndSiemens tndGSMNr(String tndGSMNr) {
        this.tndGSMNr = tndGSMNr;
        return this;
    }

    public void setTndGSMNr(String tndGSMNr) {
        this.tndGSMNr = tndGSMNr;
    }

    public String getTndGSMNr2() {
        return tndGSMNr2;
    }

    public TndSiemens tndGSMNr2(String tndGSMNr2) {
        this.tndGSMNr2 = tndGSMNr2;
        return this;
    }

    public void setTndGSMNr2(String tndGSMNr2) {
        this.tndGSMNr2 = tndGSMNr2;
    }

    public String getTndWartungBMA() {
        return tndWartungBMA;
    }

    public TndSiemens tndWartungBMA(String tndWartungBMA) {
        this.tndWartungBMA = tndWartungBMA;
        return this;
    }

    public void setTndWartungBMA(String tndWartungBMA) {
        this.tndWartungBMA = tndWartungBMA;
    }

    public String getTndSiSe() {
        return tndSiSe;
    }

    public TndSiemens tndSiSe(String tndSiSe) {
        this.tndSiSe = tndSiSe;
        return this;
    }

    public void setTndSiSe(String tndSiSe) {
        this.tndSiSe = tndSiSe;
    }

    public String getTndLogin() {
        return tndLogin;
    }

    public TndSiemens tndLogin(String tndLogin) {
        this.tndLogin = tndLogin;
        return this;
    }

    public void setTndLogin(String tndLogin) {
        this.tndLogin = tndLogin;
    }

    public String getTndPasswort() {
        return tndPasswort;
    }

    public TndSiemens tndPasswort(String tndPasswort) {
        this.tndPasswort = tndPasswort;
        return this;
    }

    public void setTndPasswort(String tndPasswort) {
        this.tndPasswort = tndPasswort;
    }

    public String getTndIP() {
        return tndIP;
    }

    public TndSiemens tndIP(String tndIP) {
        this.tndIP = tndIP;
        return this;
    }

    public void setTndIP(String tndIP) {
        this.tndIP = tndIP;
    }

    public Integer getTndTelekomNr() {
        return tndTelekomNr;
    }

    public TndSiemens tndTelekomNr(Integer tndTelekomNr) {
        this.tndTelekomNr = tndTelekomNr;
        return this;
    }

    public void setTndTelekomNr(Integer tndTelekomNr) {
        this.tndTelekomNr = tndTelekomNr;
    }

    public Long getTndLeitungsNrPseudo() {
        return tndLeitungsNrPseudo;
    }

    public TndSiemens tndLeitungsNrPseudo(Long tndLeitungsNrPseudo) {
        this.tndLeitungsNrPseudo = tndLeitungsNrPseudo;
        return this;
    }

    public void setTndLeitungsNrPseudo(Long tndLeitungsNrPseudo) {
        this.tndLeitungsNrPseudo = tndLeitungsNrPseudo;
    }

    public LocalDate getTndDslFrist() {
        return tndDslFrist;
    }

    public TndSiemens tndDslFrist(LocalDate tndDslFrist) {
        this.tndDslFrist = tndDslFrist;
        return this;
    }

    public void setTndDslFrist(LocalDate tndDslFrist) {
        this.tndDslFrist = tndDslFrist;
    }

    public Set<TblTermine> getTermins() {
        return termins;
    }

    public TndSiemens termins(Set<TblTermine> tblTermines) {
        this.termins = tblTermines;
        return this;
    }

    public TndSiemens addTermin(TblTermine tblTermine) {
        this.termins.add(tblTermine);
        tblTermine.setSiemens(this);
        return this;
    }

    public TndSiemens removeTermin(TblTermine tblTermine) {
        this.termins.remove(tblTermine);
        tblTermine.setSiemens(null);
        return this;
    }

    public void setTermins(Set<TblTermine> tblTermines) {
        this.termins = tblTermines;
    }

    public Set<TblAsp> getKontakts() {
        return kontakts;
    }

    public TndSiemens kontakts(Set<TblAsp> tblAsps) {
        this.kontakts = tblAsps;
        return this;
    }

    public TndSiemens addKontakt(TblAsp tblAsp) {
        this.kontakts.add(tblAsp);
        tblAsp.setSiemens(this);
        return this;
    }

    public TndSiemens removeKontakt(TblAsp tblAsp) {
        this.kontakts.remove(tblAsp);
        tblAsp.setSiemens(null);
        return this;
    }

    public void setKontakts(Set<TblAsp> tblAsps) {
        this.kontakts = tblAsps;
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
        TndSiemens tndSiemens = (TndSiemens) o;
        if (tndSiemens.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tndSiemens.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TndSiemens{" +
            "id=" + getId() +
            ", tndObjNr='" + getTndObjNr() + "'" +
            ", tndTeilObjNr='" + getTndTeilObjNr() + "'" +
            ", tndWarten='" + getTndWarten() + "'" +
            ", tndLeitungsNrAlt='" + getTndLeitungsNrAlt() + "'" +
            ", tndLeitungsNrNeu='" + getTndLeitungsNrNeu() + "'" +
            ", tndGSMNr='" + getTndGSMNr() + "'" +
            ", tndGSMNr2='" + getTndGSMNr2() + "'" +
            ", tndWartungBMA='" + getTndWartungBMA() + "'" +
            ", tndSiSe='" + getTndSiSe() + "'" +
            ", tndLogin='" + getTndLogin() + "'" +
            ", tndPasswort='" + getTndPasswort() + "'" +
            ", tndIP='" + getTndIP() + "'" +
            ", tndTelekomNr=" + getTndTelekomNr() +
            ", tndLeitungsNrPseudo=" + getTndLeitungsNrPseudo() +
            ", tndDslFrist='" + getTndDslFrist() + "'" +
            "}";
    }
}
