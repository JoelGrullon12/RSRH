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
import model.NivelIdioma;

public class NivelIdiomaRepository {

    private final Connection connection;

    public NivelIdiomaRepository(Connection connection) {
        this.connection = connection;
    }

    public NivelIdioma findById(int id) {
        String sql = "SELECT * FROM nivel_idioma WHERE id_nivel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando nivel", e);
        }
    }

    public List<NivelIdioma> findAll() {
        List<NivelIdioma> list = new ArrayList<>();
        String sql = "SELECT * FROM nivel_idioma";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando niveles de idioma", e);
        }
        return list;
    }

    private NivelIdioma mapRow(ResultSet rs) throws SQLException {
        NivelIdioma n = new NivelIdioma();
        n.setIdNivel(rs.getInt("id_nivel"));
        n.setNombreNivel(rs.getString("nombre_nivel"));
        return n;
    }
}
