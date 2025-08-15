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
import model.Puesto;

/**
 *
 * @author Joel Grullon
 */
import java.sql.SQLException;
import java.util.List;

public class PuestoService implements IService<Puesto> {

    @Override
    public List<Puesto> getAll() {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.puestos().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<Puesto> getAllWithRelationships() {
        try (UnitOfWork uow = new UnitOfWork()) {
            List<Puesto> listaPuestos= uow.puestos().findAll();
            
            for (Puesto p  : listaPuestos) {
                p.setRiesgo(uow.riesgosPuesto().findById(p.getRiesgoId()));
                p.setDepartamento(uow.departamentos().findById(p.getDepartamentoId()));
            }

            return listaPuestos;
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Puesto findById(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.puestos().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(Puesto p) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.puestos().save(p);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Puesto p) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.puestos().update(p);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Puesto p) {
        return delete(p.getIdPuesto());
    }

    @Override
    public boolean delete(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.puestos().delete(id);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
