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
import model.Competencia;

public class CompetenciaRepository {

    private final Connection connection;

    public CompetenciaRepository(Connection connection) {
        this.connection = connection;
    }

    public Competencia findById(int id) {
        String sql = "SELECT * FROM competencias WHERE id_competencia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando competencia", e);
        }
    }

    public List<Competencia> findAll() {
        List<Competencia> list = new ArrayList<>();
        String sql = "SELECT * FROM competencias";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando competencias", e);
        }
        return list;
    }

    public void save(Competencia c) {
        String sql = "INSERT INTO competencias(nombre_competencia, descripcion, candidato_id, eliminado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, c.getNombreCompetencia());
            stmt.setString(2, c.getDescripcion());
            stmt.setInt(3, c.getCandidatoId());
            stmt.setObject(4, c.getEliminado());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    c.setIdCompetencia(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando competencia", e);
        }
    }

    public void update(Competencia c) {
        String sql = "UPDATE competencias SET nombre_competencia = ?, descripcion = ?, candidato_id = ?, eliminado = ? WHERE id_competencia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, c.getNombreCompetencia());
            stmt.setString(2, c.getDescripcion());
            stmt.setInt(3, c.getCandidatoId());
            stmt.setObject(4, c.getEliminado());
            stmt.setInt(5, c.getIdCompetencia());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando competencia", e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM competencias WHERE id_competencia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando competencia", e);
        }
    }

    private Competencia mapRow(ResultSet rs) throws SQLException {
        Competencia c = new Competencia();
        c.setIdCompetencia(rs.getInt("id_competencia"));
        c.setNombreCompetencia(rs.getString("nombre_competencia"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setCandidatoId(rs.getInt("candidato_id"));
        c.setEliminado(rs.getObject("eliminado") != null ? rs.getBoolean("eliminado") : null);
        return c;
    }
}
