/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Joel Grullon
 */
import java.util.List;

public class NivelIdioma {
    private int idNivel;
    private String nombreNivel;

    private List<IdiomaCandidato> idiomaCandidatos;

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

    public List<IdiomaCandidato> getIdiomaCandidatos() {
        return idiomaCandidatos;
    }

    public void setIdiomaCandidatos(List<IdiomaCandidato> idiomaCandidatos) {
        this.idiomaCandidatos = idiomaCandidatos;
    }
}