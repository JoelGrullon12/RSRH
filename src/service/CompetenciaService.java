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
import model.Competencia;
import model.Idioma;

/**
 *
 * @author Joel Grullon
 */
import java.sql.SQLException;
import java.util.List;

public class CompetenciaService implements IService<Competencia> {

    @Override
    public List<Competencia> getAll() {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.competencias().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Competencia findById(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.competencias().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(Competencia c) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.competencias().save(c);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Competencia c) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.competencias().update(c);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Competencia c) {
        return delete(c.getIdCompetencia());
    }

    @Override
    public boolean delete(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.competencias().delete(id);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
