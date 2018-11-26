package de.stwgmbh.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A TblVertrag.
 */
@Entity
@Table(name = "tbl_vertrag")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TblVertrag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ver_hv")
    private String verHv;

    @Column(name = "ver_equipment_nr")
    private String verEquipmentNr;

    @Column(name = "ver_objekt_nr")
    private String verObjektNr;

    @Column(name = "ver_teilmelder")
    private String verTeilmelder;

    @Column(name = "ver_vp_name")
    private String verVpName;

    @Column(name = "ver_vp_name_2")
    private String verVpName2;

    @Column(name = "ver_vp_strasse")
    private String verVpStrasse;

    @Column(name = "ver_vp_plz")
    private String verVpPlz;

    @Column(name = "ver_vp_ort")
    private String verVpOrt;

    @Column(name = "ver_obj_name")
    private String verObjName;

    @Column(name = "ver_obj_strasse")
    private String verObjStrasse;

    @Column(name = "ver_obj_plz")
    private String verObjPlz;

    @Column(name = "ver_obj_ort")
    private String verObjOrt;

    @Column(name = "ver_sise_vertrag")
    private String verSiseVertrag;

    @Column(name = "ver_sise_typ")
    private String verSiseTyp;

    @Column(name = "ver_konz_id")
    private String verKonzId;

    @Column(name = "ver_wartung")
    private String verWartung;

    @Column(name = "ver_markieren")
    private Boolean verMarkieren;

    @Column(name = "ver_dummy")
    private Boolean verDummy;

    @OneToMany(mappedBy = "vertrag")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TblTermine> termines = new HashSet<>();
    @OneToMany(mappedBy = "kunde")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TblAsp> kontakts = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVerHv() {
        return verHv;
    }

    public TblVertrag verHv(String verHv) {
        this.verHv = verHv;
        return this;
    }

    public void setVerHv(String verHv) {
        this.verHv = verHv;
    }

    public String getVerEquipmentNr() {
        return verEquipmentNr;
    }

    public TblVertrag verEquipmentNr(String verEquipmentNr) {
        this.verEquipmentNr = verEquipmentNr;
        return this;
    }

    public void setVerEquipmentNr(String verEquipmentNr) {
        this.verEquipmentNr = verEquipmentNr;
    }

    public String getVerObjektNr() {
        return verObjektNr;
    }

    public TblVertrag verObjektNr(String verObjektNr) {
        this.verObjektNr = verObjektNr;
        return this;
    }

    public void setVerObjektNr(String verObjektNr) {
        this.verObjektNr = verObjektNr;
    }

    public String getVerTeilmelder() {
        return verTeilmelder;
    }

    public TblVertrag verTeilmelder(String verTeilmelder) {
        this.verTeilmelder = verTeilmelder;
        return this;
    }

    public void setVerTeilmelder(String verTeilmelder) {
        this.verTeilmelder = verTeilmelder;
    }

    public String getVerVpName() {
        return verVpName;
    }

    public TblVertrag verVpName(String verVpName) {
        this.verVpName = verVpName;
        return this;
    }

    public void setVerVpName(String verVpName) {
        this.verVpName = verVpName;
    }

    public String getVerVpName2() {
        return verVpName2;
    }

    public TblVertrag verVpName2(String verVpName2) {
        this.verVpName2 = verVpName2;
        return this;
    }

    public void setVerVpName2(String verVpName2) {
        this.verVpName2 = verVpName2;
    }

    public String getVerVpStrasse() {
        return verVpStrasse;
    }

    public TblVertrag verVpStrasse(String verVpStrasse) {
        this.verVpStrasse = verVpStrasse;
        return this;
    }

    public void setVerVpStrasse(String verVpStrasse) {
        this.verVpStrasse = verVpStrasse;
    }

    public String getVerVpPlz() {
        return verVpPlz;
    }

    public TblVertrag verVpPlz(String verVpPlz) {
        this.verVpPlz = verVpPlz;
        return this;
    }

    public void setVerVpPlz(String verVpPlz) {
        this.verVpPlz = verVpPlz;
    }

    public String getVerVpOrt() {
        return verVpOrt;
    }

    public TblVertrag verVpOrt(String verVpOrt) {
        this.verVpOrt = verVpOrt;
        return this;
    }

    public void setVerVpOrt(String verVpOrt) {
        this.verVpOrt = verVpOrt;
    }

    public String getVerObjName() {
        return verObjName;
    }

    public TblVertrag verObjName(String verObjName) {
        this.verObjName = verObjName;
        return this;
    }

    public void setVerObjName(String verObjName) {
        this.verObjName = verObjName;
    }

    public String getVerObjStrasse() {
        return verObjStrasse;
    }

    public TblVertrag verObjStrasse(String verObjStrasse) {
        this.verObjStrasse = verObjStrasse;
        return this;
    }

    public void setVerObjStrasse(String verObjStrasse) {
        this.verObjStrasse = verObjStrasse;
    }

    public String getVerObjPlz() {
        return verObjPlz;
    }

    public TblVertrag verObjPlz(String verObjPlz) {
        this.verObjPlz = verObjPlz;
        return this;
    }

    public void setVerObjPlz(String verObjPlz) {
        this.verObjPlz = verObjPlz;
    }

    public String getVerObjOrt() {
        return verObjOrt;
    }

    public TblVertrag verObjOrt(String verObjOrt) {
        this.verObjOrt = verObjOrt;
        return this;
    }

    public void setVerObjOrt(String verObjOrt) {
        this.verObjOrt = verObjOrt;
    }

    public String getVerSiseVertrag() {
        return verSiseVertrag;
    }

    public TblVertrag verSiseVertrag(String verSiseVertrag) {
        this.verSiseVertrag = verSiseVertrag;
        return this;
    }

    public void setVerSiseVertrag(String verSiseVertrag) {
        this.verSiseVertrag = verSiseVertrag;
    }

    public String getVerSiseTyp() {
        return verSiseTyp;
    }

    public TblVertrag verSiseTyp(String verSiseTyp) {
        this.verSiseTyp = verSiseTyp;
        return this;
    }

    public void setVerSiseTyp(String verSiseTyp) {
        this.verSiseTyp = verSiseTyp;
    }

    public String getVerKonzId() {
        return verKonzId;
    }

    public TblVertrag verKonzId(String verKonzId) {
        this.verKonzId = verKonzId;
        return this;
    }

    public void setVerKonzId(String verKonzId) {
        this.verKonzId = verKonzId;
    }

    public String getVerWartung() {
        return verWartung;
    }

    public TblVertrag verWartung(String verWartung) {
        this.verWartung = verWartung;
        return this;
    }

    public void setVerWartung(String verWartung) {
        this.verWartung = verWartung;
    }

    public Boolean isVerMarkieren() {
        return verMarkieren;
    }

    public TblVertrag verMarkieren(Boolean verMarkieren) {
        this.verMarkieren = verMarkieren;
        return this;
    }

    public void setVerMarkieren(Boolean verMarkieren) {
        this.verMarkieren = verMarkieren;
    }

    public Boolean isVerDummy() {
        return verDummy;
    }

    public TblVertrag verDummy(Boolean verDummy) {
        this.verDummy = verDummy;
        return this;
    }

    public void setVerDummy(Boolean verDummy) {
        this.verDummy = verDummy;
    }

    public Set<TblTermine> getTermines() {
        return termines;
    }

    public TblVertrag termines(Set<TblTermine> tblTermines) {
        this.termines = tblTermines;
        return this;
    }

    public TblVertrag addTermine(TblTermine tblTermine) {
        this.termines.add(tblTermine);
        tblTermine.setVertrag(this);
        return this;
    }

    public TblVertrag removeTermine(TblTermine tblTermine) {
        this.termines.remove(tblTermine);
        tblTermine.setVertrag(null);
        return this;
    }

    public void setTermines(Set<TblTermine> tblTermines) {
        this.termines = tblTermines;
    }

    public Set<TblAsp> getKontakts() {
        return kontakts;
    }

    public TblVertrag kontakts(Set<TblAsp> tblAsps) {
        this.kontakts = tblAsps;
        return this;
    }

    public TblVertrag addKontakt(TblAsp tblAsp) {
        this.kontakts.add(tblAsp);
        tblAsp.setKunde(this);
        return this;
    }

    public TblVertrag removeKontakt(TblAsp tblAsp) {
        this.kontakts.remove(tblAsp);
        tblAsp.setKunde(null);
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
        TblVertrag tblVertrag = (TblVertrag) o;
        if (tblVertrag.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tblVertrag.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TblVertrag{" +
            "id=" + getId() +
            ", verHv='" + getVerHv() + "'" +
            ", verEquipmentNr='" + getVerEquipmentNr() + "'" +
            ", verObjektNr='" + getVerObjektNr() + "'" +
            ", verTeilmelder='" + getVerTeilmelder() + "'" +
            ", verVpName='" + getVerVpName() + "'" +
            ", verVpName2='" + getVerVpName2() + "'" +
            ", verVpStrasse='" + getVerVpStrasse() + "'" +
            ", verVpPlz='" + getVerVpPlz() + "'" +
            ", verVpOrt='" + getVerVpOrt() + "'" +
            ", verObjName='" + getVerObjName() + "'" +
            ", verObjStrasse='" + getVerObjStrasse() + "'" +
            ", verObjPlz='" + getVerObjPlz() + "'" +
            ", verObjOrt='" + getVerObjOrt() + "'" +
            ", verSiseVertrag='" + getVerSiseVertrag() + "'" +
            ", verSiseTyp='" + getVerSiseTyp() + "'" +
            ", verKonzId='" + getVerKonzId() + "'" +
            ", verWartung='" + getVerWartung() + "'" +
            ", verMarkieren='" + isVerMarkieren() + "'" +
            ", verDummy='" + isVerDummy() + "'" +
            "}";
    }
}
