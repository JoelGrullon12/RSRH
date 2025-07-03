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
import model.NivelCapacitacion;

/**
 *
 * @author Joel Grullon
 */
import java.sql.SQLException;
import java.util.List;

public class NivelCapacitacionService implements IReadOnlyService<NivelCapacitacion> {

    @Override
    public List<NivelCapacitacion> getAll() {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.nivelesCapacitacion().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public NivelCapacitacion findById(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.nivelesCapacitacion().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
