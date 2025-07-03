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
import model.Capacitacion;
import model.Idioma;

/**
 *
 * @author Joel Grullon
 */
import java.sql.SQLException;
import java.util.List;

public class CapacitacionService implements IService<Capacitacion> {

    @Override
    public List<Capacitacion> getAll() {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.capacitaciones().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Capacitacion findById(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.capacitaciones().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(Capacitacion c) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.capacitaciones().save(c);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Capacitacion c) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.capacitaciones().update(c);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Capacitacion c) {
        return delete(c.getIdCapacitacion());
    }

    @Override
    public boolean delete(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.capacitaciones().delete(id);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
