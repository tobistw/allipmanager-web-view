package de.stwgmbh.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Dashboard.
 */
@Entity
@Table(name = "dashboard")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Dashboard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "zeitstempel")
    private ZonedDateTime zeitstempel;

    @Column(name = "titel")
    private String titel;

    @Column(name = "nachricht")
    private String nachricht;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getZeitstempel() {
        return zeitstempel;
    }

    public Dashboard zeitstempel(ZonedDateTime zeitstempel) {
        this.zeitstempel = zeitstempel;
        return this;
    }

    public void setZeitstempel(ZonedDateTime zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    public String getTitel() {
        return titel;
    }

    public Dashboard titel(String titel) {
        this.titel = titel;
        return this;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getNachricht() {
        return nachricht;
    }

    public Dashboard nachricht(String nachricht) {
        this.nachricht = nachricht;
        return this;
    }

    public void setNachricht(String nachricht) {
        this.nachricht = nachricht;
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
        Dashboard dashboard = (Dashboard) o;
        if (dashboard.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dashboard.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Dashboard{" +
            "id=" + getId() +
            ", zeitstempel='" + getZeitstempel() + "'" +
            ", titel='" + getTitel() + "'" +
            ", nachricht='" + getNachricht() + "'" +
            "}";
    }
}
