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
import model.IdiomaCandidato;

/**
 *
 * @author Joel Grullon
 */
import java.sql.SQLException;
import java.util.List;

public class IdiomaCandidatoService implements IService<IdiomaCandidato> {

    @Override
    public List<IdiomaCandidato> getAll() {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.idiomasCandidatos().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public IdiomaCandidato findById(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.idiomasCandidatos().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(IdiomaCandidato ic) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.idiomasCandidatos().save(ic);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(IdiomaCandidato ic) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.idiomasCandidatos().update(ic);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(IdiomaCandidato ic) {
        return delete(ic.getIdIdiomaCandidato());
    }

    @Override
    public boolean delete(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.idiomasCandidatos().delete(id);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
