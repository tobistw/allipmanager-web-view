package de.stwgmbh.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import de.stwgmbh.app.domain.enumeration.Belegstatus;

/**
 * A Arbeitsbeleg.
 */
@Entity
@Table(name = "arbeitsbeleg")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Arbeitsbeleg implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "objekt_nummer")
    private String objektNummer;

    @Column(name = "datum_leistung")
    private LocalDate datumLeistung;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Belegstatus status;

    @Lob
    @Column(name = "arbeitsbeleg")
    private byte[] arbeitsbeleg;

    @Column(name = "arbeitsbeleg_content_type")
    private String arbeitsbelegContentType;

    @ManyToOne
    @JsonIgnoreProperties("")
    private User user;

    @ManyToOne
    @JsonIgnoreProperties("")
    private TblTermine termin;

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

    public Arbeitsbeleg objektNummer(String objektNummer) {
        this.objektNummer = objektNummer;
        return this;
    }

    public void setObjektNummer(String objektNummer) {
        this.objektNummer = objektNummer;
    }

    public LocalDate getDatumLeistung() {
        return datumLeistung;
    }

    public Arbeitsbeleg datumLeistung(LocalDate datumLeistung) {
        this.datumLeistung = datumLeistung;
        return this;
    }

    public void setDatumLeistung(LocalDate datumLeistung) {
        this.datumLeistung = datumLeistung;
    }

    public Belegstatus getStatus() {
        return status;
    }

    public Arbeitsbeleg status(Belegstatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(Belegstatus status) {
        this.status = status;
    }

    public byte[] getArbeitsbeleg() {
        return arbeitsbeleg;
    }

    public Arbeitsbeleg arbeitsbeleg(byte[] arbeitsbeleg) {
        this.arbeitsbeleg = arbeitsbeleg;
        return this;
    }

    public void setArbeitsbeleg(byte[] arbeitsbeleg) {
        this.arbeitsbeleg = arbeitsbeleg;
    }

    public String getArbeitsbelegContentType() {
        return arbeitsbelegContentType;
    }

    public Arbeitsbeleg arbeitsbelegContentType(String arbeitsbelegContentType) {
        this.arbeitsbelegContentType = arbeitsbelegContentType;
        return this;
    }

    public void setArbeitsbelegContentType(String arbeitsbelegContentType) {
        this.arbeitsbelegContentType = arbeitsbelegContentType;
    }

    public User getUser() {
        return user;
    }

    public Arbeitsbeleg user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TblTermine getTermin() {
        return termin;
    }

    public Arbeitsbeleg termin(TblTermine tblTermine) {
        this.termin = tblTermine;
        return this;
    }

    public void setTermin(TblTermine tblTermine) {
        this.termin = tblTermine;
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
        Arbeitsbeleg arbeitsbeleg = (Arbeitsbeleg) o;
        if (arbeitsbeleg.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), arbeitsbeleg.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Arbeitsbeleg{" +
            "id=" + getId() +
            ", objektNummer='" + getObjektNummer() + "'" +
            ", datumLeistung='" + getDatumLeistung() + "'" +
            ", status='" + getStatus() + "'" +
            ", arbeitsbeleg='" + getArbeitsbeleg() + "'" +
            ", arbeitsbelegContentType='" + getArbeitsbelegContentType() + "'" +
            "}";
    }
}
