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
import model.Capacitacion;

public class CapacitacionRepository {

    private final Connection connection;

    public CapacitacionRepository(Connection connection) {
        this.connection = connection;
    }

    public Capacitacion findById(int id) {
        String sql = "SELECT * FROM capacitaciones WHERE id_capacitacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando capacitación", e);
        }
    }

    public List<Capacitacion> findAll() {
        List<Capacitacion> list = new ArrayList<>();
        String sql = "SELECT * FROM capacitaciones";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando capacitaciones", e);
        }
        return list;
    }

    public void save(Capacitacion c) {
        String sql = "INSERT INTO capacitaciones(nombre_capacitacion, descripcion, nivel_id, fecha_desde, fecha_hasta, institucion, candidato_id, eliminado) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, c.getNombreCapacitacion());
            stmt.setString(2, c.getDescripcion());
            stmt.setInt(3, c.getNivelId());
            if (c.getFechaDesde() != null)
                stmt.setObject(4, c.getFechaDesde());
            else
                stmt.setNull(4, Types.DATE);

            if (c.getFechaHasta() != null)
                stmt.setObject(5, c.getFechaHasta());
            else
                stmt.setNull(5, Types.DATE);

            stmt.setString(6, c.getInstitucion());
            stmt.setInt(7, c.getCandidatoId());
            stmt.setObject(8, c.getEliminado());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    c.setIdCapacitacion(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando capacitación", e);
        }
    }

    public void update(Capacitacion c) {
        String sql = "UPDATE capacitaciones SET nombre_capacitacion = ?, descripcion = ?, nivel_id = ?, fecha_desde = ?, fecha_hasta = ?, institucion = ?, candidato_id = ?, eliminado = ? " +
                     "WHERE id_capacitacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, c.getNombreCapacitacion());
            stmt.setString(2, c.getDescripcion());
            stmt.setInt(3, c.getNivelId());
            if (c.getFechaDesde() != null)
                stmt.setObject(4, c.getFechaDesde());
            else
                stmt.setNull(4, Types.DATE);

            if (c.getFechaHasta() != null)
                stmt.setObject(5, c.getFechaHasta());
            else
                stmt.setNull(5, Types.DATE);

            stmt.setString(6, c.getInstitucion());
            stmt.setInt(7, c.getCandidatoId());
            stmt.setObject(8, c.getEliminado());
            stmt.setInt(9, c.getIdCapacitacion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando capacitación", e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM capacitaciones WHERE id_capacitacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando capacitación", e);
        }
    }

    private Capacitacion mapRow(ResultSet rs) throws SQLException {
        Capacitacion c = new Capacitacion();
        c.setIdCapacitacion(rs.getInt("id_capacitacion"));
        c.setNombreCapacitacion(rs.getString("nombre_capacitacion"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setNivelId(rs.getInt("nivel_id"));

        LocalDate desde = rs.getObject("fecha_desde", LocalDate.class);
        if (desde != null) c.setFechaDesde(desde);

        LocalDate hasta = rs.getObject("fecha_hasta", LocalDate.class);
        if (hasta != null) c.setFechaHasta(hasta);

        c.setInstitucion(rs.getString("institucion"));
        c.setCandidatoId(rs.getInt("candidato_id"));
        c.setEliminado(rs.getObject("eliminado") != null ? rs.getBoolean("eliminado") : null);
        return c;
    }
}
