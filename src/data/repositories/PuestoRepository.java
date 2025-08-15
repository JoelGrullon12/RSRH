/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.repositories;

import model.Puesto;

/**
 *
 * @author Joel Grullon
 */
import java.sql.*;
import java.util.*;
public class PuestoRepository {

    private final Connection connection;

    public PuestoRepository(Connection connection) {
        this.connection = connection;
    }

    public Puesto findById(int id) {
        String sql = "SELECT * FROM puestos WHERE id_puesto = ? and (eliminado is null or eliminado = 0)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando puesto", e);
        }
    }

    public List<Puesto> findAll() {
        List<Puesto> list = new ArrayList<>();
        String sql = "SELECT * FROM puestos where (eliminado is null or eliminado = 0)";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando puestos", e);
        }
        return list;
    }

    public void save(Puesto p) {
        String sql = "INSERT INTO puestos(nombre_puesto, descripcion, salario_minimo, salario_maximo, riesgo_id, departamento_id, vacantes, eliminado) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, p.getNombrePuesto());
            stmt.setString(2, p.getDescripcion());
            stmt.setBigDecimal(3, p.getSalarioMinimo());
            stmt.setBigDecimal(4, p.getSalarioMaximo());
            stmt.setInt(5, p.getRiesgoId());
            stmt.setInt(6, p.getDepartamentoId());
            stmt.setInt(7, p.getVacantes());
            stmt.setObject(8, p.getEliminado());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    p.setIdPuesto(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando puesto", e);
        }
    }

    public void update(Puesto p) {
        String sql = "UPDATE puestos SET nombre_puesto = ?, descripcion = ?, salario_minimo = ?, salario_maximo = ?, riesgo_id = ?, departamento_id = ?, vacantes = ?, eliminado = ? " +
                     "WHERE id_puesto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, p.getNombrePuesto());
            stmt.setString(2, p.getDescripcion());
            stmt.setBigDecimal(3, p.getSalarioMinimo());
            stmt.setBigDecimal(4, p.getSalarioMaximo());
            stmt.setInt(5, p.getRiesgoId());
            stmt.setInt(6, p.getDepartamentoId());
            stmt.setInt(7, p.getVacantes());
            stmt.setObject(8, p.getEliminado());
            stmt.setInt(9, p.getIdPuesto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando puesto", e);
        }
    }

    public void delete(int id) {
        String sql = "UPDATE puestos SET eliminado = 1 WHERE id_puesto = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando puesto", e);
        }
    }

    private Puesto mapRow(ResultSet rs) throws SQLException {
        Puesto p = new Puesto();
        p.setIdPuesto(rs.getInt("id_puesto"));
        p.setNombrePuesto(rs.getString("nombre_puesto"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setSalarioMinimo(rs.getBigDecimal("salario_minimo"));
        p.setSalarioMaximo(rs.getBigDecimal("salario_maximo"));
        p.setRiesgoId(rs.getInt("riesgo_id"));
        p.setDepartamentoId(rs.getInt("departamento_id"));
        p.setVacantes(rs.getInt("vacantes"));
        p.setEliminado(rs.getObject("eliminado") != null ? rs.getBoolean("eliminado") : null);
        return p;
    }
}
