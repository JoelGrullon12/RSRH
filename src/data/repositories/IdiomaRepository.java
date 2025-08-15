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
import model.Idioma;

public class IdiomaRepository {

    private final Connection connection;

    public IdiomaRepository(Connection connection) {
        this.connection = connection;
    }

    public Idioma findById(int id) {
        String sql = "SELECT * FROM idiomas WHERE id_idioma = ? and (eliminado is null or eliminado = 0)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando idioma", e);
        }
    }

    public List<Idioma> findAll() {
        List<Idioma> list = new ArrayList<>();
        String sql = "SELECT * FROM idiomas where (eliminado is null or eliminado = 0)";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando idiomas", e);
        }
        return list;
    }
    
    public void save(Idioma e) {
        String sql = "INSERT INTO idiomas(nombre_idioma, eliminado) " +
                     "VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, e.getNombreIdioma());
            stmt.setObject(2, e.getEliminado());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    e.setIdIdioma(rs.getInt(1));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error insertando idioma", ex);
        }
    }

    public void update(Idioma e) {
        String sql = "UPDATE idiomas SET nombre_idioma = ?, eliminado = ? " +
                     "WHERE id_idioma = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, e.getNombreIdioma());
            stmt.setObject(2, e.getEliminado());
            stmt.setInt(3, e.getIdIdioma());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error actualizando idioma", ex);
        }
    }

    public void delete(int id) {
        String sql = "UPDATE idiomas SET eliminado = 1 WHERE id_idioma = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error eliminando idioma", ex);
        }
    }

    private Idioma mapRow(ResultSet rs) throws SQLException {
        Idioma i = new Idioma();
        i.setIdIdioma(rs.getInt("id_idioma"));
        i.setNombreIdioma(rs.getString("nombre_idioma"));
        i.setEliminado(rs.getObject("eliminado") != null ? rs.getBoolean("eliminado") : null);
        return i;
    }
}
