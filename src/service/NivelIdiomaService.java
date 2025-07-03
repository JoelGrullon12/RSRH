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
import model.NivelIdioma;

public class NivelIdiomaService implements IReadOnlyService<NivelIdioma> {

    @Override
    public List<NivelIdioma> getAll() {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.nivelesIdioma().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public NivelIdioma findById(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.nivelesIdioma().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
