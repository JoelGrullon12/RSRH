/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import data.UnitOfWork;
import model.RiesgoPuesto;

/**
 *
 * @author Joel Grullon
 */
import java.sql.SQLException;
import java.util.List;

public class RiesgoPuestoService implements IReadOnlyService<RiesgoPuesto> {

    @Override
    public List<RiesgoPuesto> getAll() {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.riesgosPuesto().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public RiesgoPuesto findById(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.riesgosPuesto().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
