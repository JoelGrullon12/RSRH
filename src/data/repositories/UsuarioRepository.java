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
import model.Usuario;

public class UsuarioRepository {

    private final Connection connection;

    public UsuarioRepository(Connection connection) {
        this.connection = connection;
    }

    public Usuario findById(int id) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ? and (eliminado is null or eliminado = 0)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? mapRow(rs) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error buscando usuario", e);
        }
    }

    public List<Usuario> findAll() {
        List<Usuario> list = new ArrayList<>();
        String sql = "SELECT * FROM usuarios where (eliminado is null or eliminado = 0)";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error listando usuarios", e);
        }
        return list;
    }

    public void save(Usuario u) {
        String sql = "INSERT INTO usuarios(nombre_usuario, contrasenia, tipo_usuario, empleado_id, candidato_id, eliminado) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, u.getNombreUsuario());
            stmt.setString(2, u.getContrasenia());
            stmt.setString(3, u.getTipoUsuario());
            stmt.setInt(4, u.getEmpleadoId());
            stmt.setInt(5, u.getCandidatoId());
            stmt.setObject(6, u.getEliminado());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    u.setIdUsuario(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error insertando usuario", e);
        }
    }

    public void update(Usuario u) {
        String sql = "UPDATE usuarios SET nombre_usuario = ?, contrasenia = ?, tipo_usuario = ?, empleado_id = ?, candidato_id = ?, eliminado = ? " +
                     "WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, u.getNombreUsuario());
            stmt.setString(2, u.getContrasenia());
            stmt.setString(3, u.getTipoUsuario());
            stmt.setInt(4, u.getEmpleadoId());
            stmt.setInt(5, u.getCandidatoId());
            stmt.setObject(6, u.getEliminado());
            stmt.setInt(7, u.getIdUsuario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error actualizando usuario", e);
        }
    }

    public void delete(int id) {
        String sql = "UPDATE usuarios SET eliminado = 1 usuarios WHERE id_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error eliminando usuario", e);
        }
    }
    
    public Usuario findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        }
        return null;
    }
    
    public Usuario findByUsernameAndPassword(String username, String passwordHash) throws SQLException {
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ? AND contrasenia = ? AND (eliminado IS NULL or eliminado = 0)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, passwordHash);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        }
        return null;
    }

    private Usuario mapRow(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("id_usuario"));
        u.setNombreUsuario(rs.getString("nombre_usuario"));
        u.setContrasenia(rs.getString("contrasenia"));
        u.setTipoUsuario(rs.getString("tipo_usuario"));
        u.setEmpleadoId(rs.getInt("empleado_id"));
        u.setCandidatoId(rs.getInt("candidato_id"));
        u.setEliminado(rs.getObject("eliminado") != null ? rs.getBoolean("eliminado") : null);
        return u;
    }
}
