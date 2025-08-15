/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import data.UnitOfWork;
import data.repositories.GenericRepository;
import java.sql.SQLException;
import java.util.List;
import model.BaseEntity;
import model.Candidato;
import model.Idioma;
import model.IdiomaCandidato;

/**
 *
 * @author Joel Grullon
 */
import java.sql.SQLException;
import java.util.List;

public class CandidatoService implements IService<Candidato> {

    @Override
    public List<Candidato> getAll() {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.candidatos().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<Candidato> getAllByPuesto(int idPuesto) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.candidatos().findAllByPuesto(idPuesto);
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Candidato findById(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.candidatos().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Candidato findByIdWithRelatioships(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            Candidato cand= uow.candidatos().findById(id);

            cand.setCapacitaciones(uow.capacitaciones().findAllByCandidatoId(id));
            cand.setCompetencias(uow.competencias().findAllByCandidatoId(id));
            cand.setExperienciasLaborales(uow.experienciasLaborales().findAllByCandidatoId(id));
            cand.setRecomendaciones(uow.recomendaciones().findAllByCandidatoId(id));
            cand.setIdiomas(uow.idiomasCandidatos().findAllByCandidatoId(id));

            for (IdiomaCandidato idi : cand.getIdiomas()) {
                idi.setIdioma(uow.idiomas().findById(idi.getIdiomaId()));
                idi.setNivelIdioma(uow.nivelesIdioma().findById(idi.getNivelId()));
            }

            return cand;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(Candidato c) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.candidatos().save(c);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Candidato insert(Candidato c, boolean returnEntity) {
        try (UnitOfWork uow = new UnitOfWork()) {
            Candidato nuevoCandidato=uow.candidatos().save(c);
            uow.save();
            return nuevoCandidato;
        } catch (SQLException e) {
            e.printStackTrace();
            return new Candidato();
        }
    }

    @Override
    public boolean update(Candidato c) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.candidatos().update(c);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Candidato c) {
        return delete(c.getIdCandidato());
    }

    @Override
    public boolean delete(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.candidatos().delete(id);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
