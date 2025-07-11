/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.repositories;

/**
 *
 * @author Joel Grullon
 */
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import model.ExperienciaLaboral;

public class ExperienciaLaboralRepository {

    private final Connection connection;

    public ExperienciaLaboralRepository(Connection connection) {
        this.connection = connection;
    }

    public ExperienciaLaboral findById(int id) {
        String sql = "SELECT * FROM experiencias_laborales WHERE id_experiencia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando experiencia laboral", e);
        }
    }

    public List<ExperienciaLaboral> findAll() {
        List<ExperienciaLaboral> list = new ArrayList<>();
        String sql = "SELECT * FROM experiencias_laborales";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando experiencias laborales", e);
        }
        return list;
    }

    public void save(ExperienciaLaboral el) {
        String sql = "INSERT INTO experiencias_laborales(empresa, puesto, descripcion, fecha_desde, fecha_hasta, salario, candidato_id, eliminado) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, el.getEmpresa());
            stmt.setString(2, el.getPuesto());
            stmt.setString(3, el.getDescripcion());
            if (el.getFechaDesde() != null)
                stmt.setObject(4, el.getFechaDesde());
            else
                stmt.setNull(4, Types.DATE);

            if (el.getFechaHasta() != null)
                stmt.setObject(5, el.getFechaHasta());
            else
                stmt.setNull(5, Types.DATE);

            stmt.setBigDecimal(6, el.getSalario());
            stmt.setInt(7, el.getCandidatoId());
            stmt.setObject(8, el.getEliminado());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    el.setIdExperiencia(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando experiencia laboral", e);
        }
    }

    public void update(ExperienciaLaboral el) {
        String sql = "UPDATE experiencias_laborales SET empresa = ?, puesto = ?, descripcion = ?, fecha_desde = ?, fecha_hasta = ?, salario = ?, candidato_id = ?, eliminado = ? " +
                     "WHERE id_experiencia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, el.getEmpresa());
            stmt.setString(2, el.getPuesto());
            stmt.setString(3, el.getDescripcion());
            if (el.getFechaDesde() != null)
                stmt.setObject(4, el.getFechaDesde());
            else
                stmt.setNull(4, Types.DATE);

            if (el.getFechaHasta() != null)
                stmt.setObject(5, el.getFechaHasta());
            else
                stmt.setNull(5, Types.DATE);

            stmt.setBigDecimal(6, el.getSalario());
            stmt.setInt(7, el.getCandidatoId());
            stmt.setObject(8, el.getEliminado());
            stmt.setInt(9, el.getIdExperiencia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando experiencia", e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM experiencias_laborales WHERE id_experiencia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando experiencia", e);
        }
    }

    private ExperienciaLaboral mapRow(ResultSet rs) throws SQLException {
        ExperienciaLaboral c = new ExperienciaLaboral();
        c.setIdExperiencia(rs.getInt("id_experiencia"));
        c.setEmpresa(rs.getString("empresa"));
        c.setPuesto(rs.getString("puesto"));
        c.setDescripcion(rs.getString("descripcion"));

        LocalDate desde = rs.getObject("fecha_desde", LocalDate.class);
        if (desde != null) c.setFechaDesde(desde);

        LocalDate hasta = rs.getObject("fecha_hasta", LocalDate.class);
        if (hasta != null) c.setFechaHasta(hasta);

        c.setSalario(rs.getBigDecimal("salario"));
        c.setCandidatoId(rs.getInt("candidato_id"));
        c.setEliminado(rs.getObject("eliminado") != null ? rs.getBoolean("eliminado") : null);
        return c;
    }
}
