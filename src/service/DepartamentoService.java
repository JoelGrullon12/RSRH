/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import data.UnitOfWork;
import model.Departamento;

/**
 *
 * @author Joel Grullon
 */
import java.sql.SQLException;
import java.util.List;

public class DepartamentoService implements IService<Departamento> {

    @Override
    public List<Departamento> getAll() {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.departamentos().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Departamento findById(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.departamentos().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(Departamento d) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.departamentos().save(d);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Departamento d) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.departamentos().update(d);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Departamento d) {
        return delete(d.getIdDepartamento());
    }

    @Override
    public boolean delete(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.departamentos().delete(id);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        
    public Departamento findByNombre(String nombre) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.departamentos().findByNombre(nombre);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
