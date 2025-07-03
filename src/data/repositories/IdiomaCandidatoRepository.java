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
import model.IdiomaCandidato;

public class IdiomaCandidatoRepository {

    private final Connection connection;

    public IdiomaCandidatoRepository(Connection connection) {
        this.connection = connection;
    }

    public IdiomaCandidato findById(int id) {
        String sql = "SELECT * FROM idiomas_candidatos WHERE id_idioma_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando idioma del candidato", e);
        }
    }

    public List<IdiomaCandidato> findAll() {
        List<IdiomaCandidato> list = new ArrayList<>();
        String sql = "SELECT * FROM idiomas_candidatos";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando idiomas de candidatos", e);
        }
        return list;
    }

    public void save(IdiomaCandidato ic) {
        String sql = "INSERT INTO idiomas_candidatos(candidato_id, idioma_id, nivel_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, ic.getCandidatoId());
            stmt.setInt(2, ic.getIdiomaId());
            stmt.setInt(3, ic.getNivelId());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    ic.setIdIdiomaCandidato(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando idioma del candidato", e);
        }
    }

    public void update(IdiomaCandidato ic) {
        String sql = "UPDATE idiomas_candidatos SET candidato_id = ?, idioma_id = ?, nivel_id = ? WHERE id_idioma_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ic.getCandidatoId());
            stmt.setInt(2, ic.getIdiomaId());
            stmt.setInt(3, ic.getNivelId());
            stmt.setInt(4, ic.getIdIdiomaCandidato());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando idioma del candidato", e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM idiomas_candidatos WHERE id_idioma_candidato = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando idioma del candidato", e);
        }
    }

    private IdiomaCandidato mapRow(ResultSet rs) throws SQLException {
        IdiomaCandidato ic = new IdiomaCandidato();
        ic.setIdIdiomaCandidato(rs.getInt("id_idioma_candidato"));
        ic.setCandidatoId(rs.getInt("candidato_id"));
        ic.setIdiomaId(rs.getInt("idioma_id"));
        ic.setNivelId(rs.getInt("nivel_id"));
        return ic;
    }
}
