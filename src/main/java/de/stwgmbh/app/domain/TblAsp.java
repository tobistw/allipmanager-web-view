package de.stwgmbh.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A TblAsp.
 */
@Entity
@Table(name = "tbl_asp")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TblAsp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "asp_id")
    private Integer aspId;

    @Column(name = "asp_name")
    private String aspName;

    @Column(name = "asp_rolle")
    private String aspRolle;

    @Column(name = "asp_tel_1")
    private String aspTel1;

    @Column(name = "asp_mobil_1")
    private String aspMobil1;

    @Column(name = "asp_email")
    private String aspEmail;

    @Column(name = "asp_objekt_nr_ref")
    private String aspObjektNrRef;

    @Column(name = "asp_bemerkung")
    private String aspBemerkung;

    @ManyToOne
    @JsonIgnoreProperties("kontakts")
    private TblVertrag kunde;

    @ManyToOne
    @JsonIgnoreProperties("kontakts")
    private TndSiemens siemens;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAspId() {
        return aspId;
    }

    public TblAsp aspId(Integer aspId) {
        this.aspId = aspId;
        return this;
    }

    public void setAspId(Integer aspId) {
        this.aspId = aspId;
    }

    public String getAspName() {
        return aspName;
    }

    public TblAsp aspName(String aspName) {
        this.aspName = aspName;
        return this;
    }

    public void setAspName(String aspName) {
        this.aspName = aspName;
    }

    public String getAspRolle() {
        return aspRolle;
    }

    public TblAsp aspRolle(String aspRolle) {
        this.aspRolle = aspRolle;
        return this;
    }

    public void setAspRolle(String aspRolle) {
        this.aspRolle = aspRolle;
    }

    public String getAspTel1() {
        return aspTel1;
    }

    public TblAsp aspTel1(String aspTel1) {
        this.aspTel1 = aspTel1;
        return this;
    }

    public void setAspTel1(String aspTel1) {
        this.aspTel1 = aspTel1;
    }

    public String getAspMobil1() {
        return aspMobil1;
    }

    public TblAsp aspMobil1(String aspMobil1) {
        this.aspMobil1 = aspMobil1;
        return this;
    }

    public void setAspMobil1(String aspMobil1) {
        this.aspMobil1 = aspMobil1;
    }

    public String getAspEmail() {
        return aspEmail;
    }

    public TblAsp aspEmail(String aspEmail) {
        this.aspEmail = aspEmail;
        return this;
    }

    public void setAspEmail(String aspEmail) {
        this.aspEmail = aspEmail;
    }

    public String getAspObjektNrRef() {
        return aspObjektNrRef;
    }

    public TblAsp aspObjektNrRef(String aspObjektNrRef) {
        this.aspObjektNrRef = aspObjektNrRef;
        return this;
    }

    public void setAspObjektNrRef(String aspObjektNrRef) {
        this.aspObjektNrRef = aspObjektNrRef;
    }

    public String getAspBemerkung() {
        return aspBemerkung;
    }

    public TblAsp aspBemerkung(String aspBemerkung) {
        this.aspBemerkung = aspBemerkung;
        return this;
    }

    public void setAspBemerkung(String aspBemerkung) {
        this.aspBemerkung = aspBemerkung;
    }

    public TblVertrag getKunde() {
        return kunde;
    }

    public TblAsp kunde(TblVertrag tblVertrag) {
        this.kunde = tblVertrag;
        return this;
    }

    public void setKunde(TblVertrag tblVertrag) {
        this.kunde = tblVertrag;
    }

    public TndSiemens getSiemens() {
        return siemens;
    }

    public TblAsp siemens(TndSiemens tndSiemens) {
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
        TblAsp tblAsp = (TblAsp) o;
        if (tblAsp.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tblAsp.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TblAsp{" +
            "id=" + getId() +
            ", aspId=" + getAspId() +
            ", aspName='" + getAspName() + "'" +
            ", aspRolle='" + getAspRolle() + "'" +
            ", aspTel1='" + getAspTel1() + "'" +
            ", aspMobil1='" + getAspMobil1() + "'" +
            ", aspEmail='" + getAspEmail() + "'" +
            ", aspObjektNrRef='" + getAspObjektNrRef() + "'" +
            ", aspBemerkung='" + getAspBemerkung() + "'" +
            "}";
    }
}
