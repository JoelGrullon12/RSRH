/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

public class NivelCapacitacion {
    private int idNivel;
    private String nombreNivel;

    private List<Capacitacion> capacitaciones;

    public int getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(int idNivel) {
        this.idNivel = idNivel;
    }

    public String getNombreNivel() {
        return nombreNivel;
    }

    public void setNombreNivel(String nombreNivel) {
        this.nombreNivel = nombreNivel;
    }

    public List<Capacitacion> getCapacitaciones() {
        return capacitaciones;
    }

    public void setCapacitaciones(List<Capacitacion> capacitaciones) {
        this.capacitaciones = capacitaciones;
    }

    @Override
    public String toString() {
        return nombreNivel; // Esto es lo que se muestra en el JComboBox
    }

    // equals para poder hacer .setSelectedItem correctamente
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NivelCapacitacion other = (NivelCapacitacion) obj;
        return idNivel == other.idNivel;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(idNivel);
    }
}
