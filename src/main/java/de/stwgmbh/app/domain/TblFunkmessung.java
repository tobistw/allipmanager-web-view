package de.stwgmbh.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A TblFunkmessung.
 */
@Entity
@Table(name = "tbl_funkmessung")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TblFunkmessung implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "fun_id")
    private Integer funId;

    @Column(name = "fun_objekt_nr")
    private String funObjektNr;

    @Column(name = "fun_datum")
    private LocalDate funDatum;

    @Column(name = "fun_d_1")
    private String funD1;

    @Column(name = "fun_d_2")
    private String funD2;

    @Column(name = "fun_o_2")
    private String funO2;

    @Column(name = "fun_eplus")
    private String funEplus;

    @Column(name = "fun_zusatz")
    private String funZusatz;

    @Column(name = "fun_kabel")
    private String funKabel;

    @Column(name = "fun_bemerkung")
    private String funBemerkung;

    @Column(name = "fun_name")
    private String funName;

    @ManyToOne
    @JsonIgnoreProperties("funkmessungs")
    private TblTermine tblTermine;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFunId() {
        return funId;
    }

    public TblFunkmessung funId(Integer funId) {
        this.funId = funId;
        return this;
    }

    public void setFunId(Integer funId) {
        this.funId = funId;
    }

    public String getFunObjektNr() {
        return funObjektNr;
    }

    public TblFunkmessung funObjektNr(String funObjektNr) {
        this.funObjektNr = funObjektNr;
        return this;
    }

    public void setFunObjektNr(String funObjektNr) {
        this.funObjektNr = funObjektNr;
    }

    public LocalDate getFunDatum() {
        return funDatum;
    }

    public TblFunkmessung funDatum(LocalDate funDatum) {
        this.funDatum = funDatum;
        return this;
    }

    public void setFunDatum(LocalDate funDatum) {
        this.funDatum = funDatum;
    }

    public String getFunD1() {
        return funD1;
    }

    public TblFunkmessung funD1(String funD1) {
        this.funD1 = funD1;
        return this;
    }

    public void setFunD1(String funD1) {
        this.funD1 = funD1;
    }

    public String getFunD2() {
        return funD2;
    }

    public TblFunkmessung funD2(String funD2) {
        this.funD2 = funD2;
        return this;
    }

    public void setFunD2(String funD2) {
        this.funD2 = funD2;
    }

    public String getFunO2() {
        return funO2;
    }

    public TblFunkmessung funO2(String funO2) {
        this.funO2 = funO2;
        return this;
    }

    public void setFunO2(String funO2) {
        this.funO2 = funO2;
    }

    public String getFunEplus() {
        return funEplus;
    }

    public TblFunkmessung funEplus(String funEplus) {
        this.funEplus = funEplus;
        return this;
    }

    public void setFunEplus(String funEplus) {
        this.funEplus = funEplus;
    }

    public String getFunZusatz() {
        return funZusatz;
    }

    public TblFunkmessung funZusatz(String funZusatz) {
        this.funZusatz = funZusatz;
        return this;
    }

    public void setFunZusatz(String funZusatz) {
        this.funZusatz = funZusatz;
    }

    public String getFunKabel() {
        return funKabel;
    }

    public TblFunkmessung funKabel(String funKabel) {
        this.funKabel = funKabel;
        return this;
    }

    public void setFunKabel(String funKabel) {
        this.funKabel = funKabel;
    }

    public String getFunBemerkung() {
        return funBemerkung;
    }

    public TblFunkmessung funBemerkung(String funBemerkung) {
        this.funBemerkung = funBemerkung;
        return this;
    }

    public void setFunBemerkung(String funBemerkung) {
        this.funBemerkung = funBemerkung;
    }

    public String getFunName() {
        return funName;
    }

    public TblFunkmessung funName(String funName) {
        this.funName = funName;
        return this;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public TblTermine getTblTermine() {
        return tblTermine;
    }

    public TblFunkmessung tblTermine(TblTermine tblTermine) {
        this.tblTermine = tblTermine;
        return this;
    }

    public void setTblTermine(TblTermine tblTermine) {
        this.tblTermine = tblTermine;
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
        TblFunkmessung tblFunkmessung = (TblFunkmessung) o;
        if (tblFunkmessung.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tblFunkmessung.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TblFunkmessung{" +
            "id=" + getId() +
            ", funId=" + getFunId() +
            ", funObjektNr='" + getFunObjektNr() + "'" +
            ", funDatum='" + getFunDatum() + "'" +
            ", funD1='" + getFunD1() + "'" +
            ", funD2='" + getFunD2() + "'" +
            ", funO2='" + getFunO2() + "'" +
            ", funEplus='" + getFunEplus() + "'" +
            ", funZusatz='" + getFunZusatz() + "'" +
            ", funKabel='" + getFunKabel() + "'" +
            ", funBemerkung='" + getFunBemerkung() + "'" +
            ", funName='" + getFunName() + "'" +
            "}";
    }
}
