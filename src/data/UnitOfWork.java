/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

/**
 *
 * @author Joel Grullon
 */
import data.repositories.CandidatoRepository;
import data.repositories.CapacitacionRepository;
import data.repositories.CompetenciaRepository;
import data.repositories.DepartamentoRepository;
import data.repositories.EmpleadoRepository;
import data.repositories.ExperienciaLaboralRepository;
import data.repositories.IdiomaCandidatoRepository;
import data.repositories.IdiomaRepository;
import data.repositories.NivelIdiomaRepository;
import data.repositories.NivelesCapacitacionRepository;
import data.repositories.PuestoRepository;
import data.repositories.RecomendacionRepository;
import data.repositories.RiesgoPuestoRepository;
import data.repositories.UsuarioRepository;
import java.sql.Connection;
import java.sql.SQLException;

public class UnitOfWork implements AutoCloseable {

    private final Connection connection;

    // Repositorios (inicializados perezosamente)
    private DepartamentoRepository departamentoRepository;
    private PuestoRepository puestoRepository;
    private RiesgoPuestoRepository riesgoPuestoRepository;
    private CandidatoRepository candidatoRepository;
    private ExperienciaLaboralRepository experienciaRepository;
    private RecomendacionRepository recomendacionRepository;
    private CompetenciaRepository competenciaRepository;
    private IdiomaRepository idiomaRepository;
    private NivelIdiomaRepository nivelIdiomaRepository;
    private IdiomaCandidatoRepository idiomaCandidatoRepository;
    private NivelesCapacitacionRepository nivelesCapacitacionRepository;
    private CapacitacionRepository capacitacionRepository;
    private EmpleadoRepository empleadoRepository;
    private UsuarioRepository usuarioRepository;

    public UnitOfWork() throws SQLException {
        this.connection = MySQL.getConnection();
        this.connection.setAutoCommit(false); // Control transaccional manual
    }

    public DepartamentoRepository departamentos() {
        if (departamentoRepository == null)
            departamentoRepository = new DepartamentoRepository(connection);
        return departamentoRepository;
    }

    public PuestoRepository puestos() {
        if (puestoRepository == null)
            puestoRepository = new PuestoRepository(connection);
        return puestoRepository;
    }

    public RiesgoPuestoRepository riesgosPuesto() {
        if (riesgoPuestoRepository == null)
            riesgoPuestoRepository = new RiesgoPuestoRepository(connection);
        return riesgoPuestoRepository;
    }

    public CandidatoRepository candidatos() {
        if (candidatoRepository == null)
            candidatoRepository = new CandidatoRepository(connection);
        return candidatoRepository;
    }

    public RecomendacionRepository recomendaciones() {
        if (recomendacionRepository == null)
            recomendacionRepository = new RecomendacionRepository(connection);
        return recomendacionRepository;
    }

    public CompetenciaRepository competencias() {
        if (competenciaRepository == null)
            competenciaRepository = new CompetenciaRepository(connection);
        return competenciaRepository;
    }

    public ExperienciaLaboralRepository experienciasLaborales() {
        if (experienciaRepository == null)
            experienciaRepository = new ExperienciaLaboralRepository(connection);
        return experienciaRepository;
    }

    public IdiomaRepository idiomas() {
        if (idiomaRepository == null)
            idiomaRepository = new IdiomaRepository(connection);
        return idiomaRepository;
    }

    public NivelIdiomaRepository nivelesIdioma() {
        if (nivelIdiomaRepository == null)
            nivelIdiomaRepository = new NivelIdiomaRepository(connection);
        return nivelIdiomaRepository;
    }

    public IdiomaCandidatoRepository idiomasCandidatos() {
        if (idiomaCandidatoRepository == null)
            idiomaCandidatoRepository = new IdiomaCandidatoRepository(connection);
        return idiomaCandidatoRepository;
    }

    public NivelesCapacitacionRepository nivelesCapacitacion() {
        if (nivelesCapacitacionRepository == null)
            nivelesCapacitacionRepository = new NivelesCapacitacionRepository(connection);
        return nivelesCapacitacionRepository;
    }

    public CapacitacionRepository capacitaciones() {
        if (capacitacionRepository == null)
            capacitacionRepository = new CapacitacionRepository(connection);
        return capacitacionRepository;
    }

    public EmpleadoRepository empleados() {
        if (empleadoRepository == null)
            empleadoRepository = new EmpleadoRepository(connection);
        return empleadoRepository;
    }

    public UsuarioRepository usuarios() {
        if (usuarioRepository == null)
            usuarioRepository = new UsuarioRepository(connection);
        return usuarioRepository;
    }

    public void save() throws SQLException {
        connection.commit();
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace(); // o loggear
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace(); // o loggear
        }
    }
}
