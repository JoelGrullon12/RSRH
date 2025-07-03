/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import data.UnitOfWork;
import model.Recomendacion;

/**
 *
 * @author Joel Grullon
 */
import java.sql.SQLException;
import java.util.List;

public class RecomendacionService implements IService<Recomendacion> {

    @Override
    public List<Recomendacion> getAll() {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.recomendaciones().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Recomendacion findById(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.recomendaciones().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(Recomendacion r) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.recomendaciones().save(r);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Recomendacion r) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.recomendaciones().update(r);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Recomendacion r) {
        return delete(r.getIdRecomendacion());
    }

    @Override
    public boolean delete(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.recomendaciones().delete(id);
            uow.save();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
