/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import data.UnitOfWork;
import java.sql.SQLException;
import java.util.List;
import model.ExperienciaLaboral;

public class ExperienciaLaboralService implements IService<ExperienciaLaboral> {

    @Override
    public List<ExperienciaLaboral> getAll() {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.experienciasLaborales().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public ExperienciaLaboral findById(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.experienciasLaborales().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(ExperienciaLaboral c) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.experienciasLaborales().save(c);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(ExperienciaLaboral c) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.experienciasLaborales().update(c);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(ExperienciaLaboral c) {
        return delete(c.getIdExperiencia());
    }

    @Override
    public boolean delete(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.experienciasLaborales().delete(id);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
