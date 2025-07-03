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
import java.util.*;
import model.Recomendacion;

public class RecomendacionRepository {

    private final Connection connection;

    public RecomendacionRepository(Connection connection) {
        this.connection = connection;
    }

    public Recomendacion findById(int id) {
        String sql = "SELECT * FROM recomendaciones WHERE id_recomendacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando recomendación", e);
        }
    }

    public List<Recomendacion> findAll() {
        List<Recomendacion> list = new ArrayList<>();
        String sql = "SELECT * FROM recomendaciones";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando recomendaciones", e);
        }
        return list;
    }

    public void save(Recomendacion r) {
        String sql = "INSERT INTO recomendaciones(nombre_recomendador, empresa, puesto, contacto, candidato_id, eliminado) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, r.getNombreRecomendador());
            stmt.setString(2, r.getEmpresa());
            stmt.setString(3, r.getPuesto());
            stmt.setString(4, r.getContacto());
            stmt.setInt(5, r.getCandidatoId());
            stmt.setObject(6, r.getEliminado());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    r.setIdRecomendacion(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando recomendación", e);
        }
    }

    public void update(Recomendacion r) {
        String sql = "UPDATE recomendaciones SET nombre_recomendador = ?, empresa = ?, puesto = ?, contacto = ?, candidato_id = ?, eliminado = ? " +
                     "WHERE id_recomendacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, r.getNombreRecomendador());
            stmt.setString(2, r.getEmpresa());
            stmt.setString(3, r.getPuesto());
            stmt.setString(4, r.getContacto());
            stmt.setInt(5, r.getCandidatoId());
            stmt.setObject(6, r.getEliminado());
            stmt.setInt(7, r.getIdRecomendacion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando recomendación", e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM recomendaciones WHERE id_recomendacion = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando recomendación", e);
        }
    }

    private Recomendacion mapRow(ResultSet rs) throws SQLException {
        Recomendacion r = new Recomendacion();
        r.setIdRecomendacion(rs.getInt("id_recomendacion"));
        r.setNombreRecomendador(rs.getString("nombre_recomendador"));
        r.setEmpresa(rs.getString("empresa"));
        r.setPuesto(rs.getString("puesto"));
        r.setContacto(rs.getString("contacto"));
        r.setCandidatoId(rs.getInt("candidato_id"));
        r.setEliminado(rs.getObject("eliminado") != null ? rs.getBoolean("eliminado") : null);
        return r;
    }
}
