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
import model.Empleado;

public class EmpleadoService implements IService<Empleado> {

    @Override
    public List<Empleado> getAll() {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.empleados().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<Empleado> getAllWithRelationships() {
        try (UnitOfWork uow = new UnitOfWork()) {
            List<Empleado> listaEmpleados= uow.empleados().findAll();

            for (Empleado e : listaEmpleados) {
                e.setPuesto(uow.puestos().findById(e.getPuestoId()));
            }

            return listaEmpleados;
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Empleado findById(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.empleados().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean insert(Empleado e) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.empleados().save(e);
            uow.save();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean convertirCandidatoEnEmpleado(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.empleados().saveCandidatoAsEmpleado(id);
            uow.save();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Empleado e) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.empleados().update(e);
            uow.save();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Empleado e) {
        return delete(e.getIdEmpleado());
    }

    @Override
    public boolean delete(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.empleados().delete(id);
            uow.save();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public Empleado findByCedula(String cedula) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.empleados().findByCedula(cedula);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
