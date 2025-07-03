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
import model.RiesgoPuesto;

public class RiesgoPuestoRepository {

    private final Connection connection;

    public RiesgoPuestoRepository(Connection connection) {
        this.connection = connection;
    }

    public RiesgoPuesto findById(int id) {
        String sql = "SELECT * FROM riesgos_puesto WHERE id_riesgo = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando riesgo", e);
        }
    }

    public List<RiesgoPuesto> findAll() {
        List<RiesgoPuesto> list = new ArrayList<>();
        String sql = "SELECT * FROM riesgos_puesto";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando riesgos", e);
        }
        return list;
    }

    private RiesgoPuesto mapRow(ResultSet rs) throws SQLException {
        RiesgoPuesto r = new RiesgoPuesto();
        r.setIdRiesgo(rs.getInt("id_riesgo"));
        r.setNombreRiesgo(rs.getString("nombre_riesgo"));
        return r;
    }
}
