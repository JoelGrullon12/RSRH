/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import data.UnitOfWork;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import model.RiesgoPuesto;

/**
 *
 * @author Joel Grullon
 */
import java.sql.SQLException;
import java.util.List;
import model.Usuario;

public class UsuarioService implements IService<Usuario> {

    @Override
    public List<Usuario> getAll() {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.usuarios().findAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Usuario findById(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.usuarios().findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(Usuario u) {
        String passwordHash=sha256(u.getContrasenia());
        u.setContrasenia(passwordHash);
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.usuarios().save(u);
            uow.save();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Usuario u) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.usuarios().update(u);
            uow.save();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Usuario u) {
        return delete(u.getIdUsuario());
    }

    @Override
    public boolean delete(int id) {
        try (UnitOfWork uow = new UnitOfWork()) {
            uow.usuarios().delete(id);
            uow.save();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public Usuario findByUsername(String username) {
        try (UnitOfWork uow = new UnitOfWork()) {
            return uow.usuarios().findByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Usuario findByUsernameAndPassword(String username, String password) {
        try (UnitOfWork uow = new UnitOfWork()) {
            String passwordHash=sha256(password);
            return uow.usuarios().findByUsernameAndPassword(username, passwordHash);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder();
            for (byte b : hash) hex.append(String.format("%02x", b));
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
