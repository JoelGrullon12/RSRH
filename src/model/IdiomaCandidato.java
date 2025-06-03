/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Joel Grullon
 */
@Entity
@Table(name = "idiomas_candidatos")
@NamedQueries({
    @NamedQuery(name = "IdiomasCandidatos.findAll", query = "SELECT i FROM IdiomasCandidatos i"),
    @NamedQuery(name = "IdiomasCandidatos.findByIdIdiomaCandidato", query = "SELECT i FROM IdiomasCandidatos i WHERE i.idIdiomaCandidato = :idIdiomaCandidato")})
public class IdiomaCandidato extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_idioma_candidato")
    private Integer idIdiomaCandidato;
    @JoinColumn(name = "candidato_id", referencedColumnName = "id_candidato")
    @ManyToOne(optional = false)
    private Candidato candidatoId;
    @JoinColumn(name = "idioma_id", referencedColumnName = "id_idioma")
    @ManyToOne(optional = false)
    private Idioma idiomaId;

    public IdiomaCandidato() {
    }

    public IdiomaCandidato(Integer idIdiomaCandidato) {
        this.idIdiomaCandidato = idIdiomaCandidato;
    }

    public Integer getIdIdiomaCandidato() {
        return idIdiomaCandidato;
    }

    public void setIdIdiomaCandidato(Integer idIdiomaCandidato) {
        this.idIdiomaCandidato = idIdiomaCandidato;
    }

    public Candidato getCandidatoId() {
        return candidatoId;
    }

    public void setCandidatoId(Candidato candidatoId) {
        this.candidatoId = candidatoId;
    }

    public Idioma getIdiomaId() {
        return idiomaId;
    }

    public void setIdiomaId(Idioma idiomaId) {
        this.idiomaId = idiomaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIdiomaCandidato != null ? idIdiomaCandidato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IdiomaCandidato)) {
            return false;
        }
        IdiomaCandidato other = (IdiomaCandidato) object;
        if ((this.idIdiomaCandidato == null && other.idIdiomaCandidato != null) || (this.idIdiomaCandidato != null && !this.idIdiomaCandidato.equals(other.idIdiomaCandidato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.IdiomasCandidatos[ idIdiomaCandidato=" + idIdiomaCandidato + " ]";
    }
    
}
