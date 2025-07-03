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
import model.Idioma;
import model.Idioma;

/**
 *
 * @author Joel Grullon
 */
import java.util.List;

public class IdiomaService implements IService<Idioma> {

     @Override
    public List<Idioma> getAll() {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.idiomas().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Idioma findById(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.idiomas().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean insert(Idioma ic) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.idiomas().save(ic);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Idioma ic) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.idiomas().update(ic);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Idioma ic) {
        return delete(ic.getIdIdioma());
    }

    @Override
    public boolean delete(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.idiomas().delete(id);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
