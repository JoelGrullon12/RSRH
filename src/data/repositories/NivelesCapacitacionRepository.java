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
import model.NivelCapacitacion;

public class NivelesCapacitacionRepository {

    private final Connection connection;

    public NivelesCapacitacionRepository(Connection connection) {
        this.connection = connection;
    }

    public NivelCapacitacion findById(int id) {
        String sql = "SELECT * FROM niveles_capacitacion WHERE id_nivel = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando nivel de capacitación", e);
        }
    }

    public List<NivelCapacitacion> findAll() {
        List<NivelCapacitacion> list = new ArrayList<>();
        String sql = "SELECT * FROM niveles_capacitacion";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando niveles de capacitación", e);
        }
        return list;
    }

    private NivelCapacitacion mapRow(ResultSet rs) throws SQLException {
        NivelCapacitacion n = new NivelCapacitacion();
        n.setIdNivel(rs.getInt("id_nivel"));
        n.setNombreNivel(rs.getString("nombre_nivel"));
        return n;
    }
}
