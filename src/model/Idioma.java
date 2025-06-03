/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Joel Grullon
 */
@Entity
@Table(name = "idiomas")
@NamedQueries({
    @NamedQuery(name = "Idiomas.findAll", query = "SELECT i FROM Idiomas i"),
    @NamedQuery(name = "Idiomas.findByIdIdioma", query = "SELECT i FROM Idiomas i WHERE i.idIdioma = :idIdioma"),
    @NamedQuery(name = "Idiomas.findByNombreIdioma", query = "SELECT i FROM Idiomas i WHERE i.nombreIdioma = :nombreIdioma"),
    @NamedQuery(name = "Idiomas.findByEstado", query = "SELECT i FROM Idiomas i WHERE i.estado = :estado")})
public class Idioma extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_idioma")
    private Integer idIdioma;
    @Basic(optional = false)
    @Column(name = "nombre_idioma")
    private String nombreIdioma;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idiomaId")
    private Collection<IdiomaCandidato> idiomasCandidatosCollection;

    public Idioma() {
    }

    public Idioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public Idioma(Integer idIdioma, String nombreIdioma) {
        this.idIdioma = idIdioma;
        this.nombreIdioma = nombreIdioma;
    }

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

    public String getNombreIdioma() {
        return nombreIdioma;
    }

    public void setNombreIdioma(String nombreIdioma) {
        this.nombreIdioma = nombreIdioma;
    }

    public Collection<IdiomaCandidato> getIdiomasCandidatosCollection() {
        return idiomasCandidatosCollection;
    }

    public void setIdiomasCandidatosCollection(Collection<IdiomaCandidato> idiomasCandidatosCollection) {
        this.idiomasCandidatosCollection = idiomasCandidatosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIdioma != null ? idIdioma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Idioma)) {
            return false;
        }
        Idioma other = (Idioma) object;
        if ((this.idIdioma == null && other.idIdioma != null) || (this.idIdioma != null && !this.idIdioma.equals(other.idIdioma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Idiomas[ idIdioma=" + idIdioma + " ]";
    }
    
}
