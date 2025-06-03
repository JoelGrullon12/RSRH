/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author Joel Grullon
 */
import data.repositories.GenericRepository;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import model.Idioma;

public class UnitOfWork implements AutoCloseable {
    private final Connection connection;
    private final Map<Class<?>, GenericRepository<?>> repositories = new HashMap<>();

    private GenericRepository<Idioma> idiomaRepository;

     public UnitOfWork() throws SQLException {
        this.connection = MySQL.getConnection();
        this.connection.setAutoCommit(false);
    }
     
     // Método genérico para obtener un repositorio
    public <T> GenericRepository<T> getRepository(Class<T> entityClass) {
        if (!repositories.containsKey(entityClass)) {
            repositories.put(entityClass, new GenericRepository<>(entityClass, connection));
        }

        @SuppressWarnings("unchecked")
        GenericRepository<T> repo = (GenericRepository<T>) repositories.get(entityClass);
        return repo;
    }

    public GenericRepository<Idioma> idiomas() {
        if (idiomaRepository == null) {
            idiomaRepository = new GenericRepository<Idioma>(Idioma.class, connection);
        }
        return idiomaRepository;
    }

//    public GenericRepository<Usuario> usuarios() {
//        if (usuarioRepository == null) {
//            usuarioRepository = new GenericRepository<>(Usuario.class, connection);
//        }
//        return usuarioRepository;
//    }

    public void save() throws SQLException {
        connection.commit();
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}