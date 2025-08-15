/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view.verCandidatoForm;

import view.nuevoCandidato.*;
import common.DateLabelFormatter;
import common.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.Candidato;
import model.Capacitacion;
import model.Competencia;

import model.ExperienciaLaboral;
import model.Idioma;
import model.IdiomaCandidato;
import model.NivelIdioma;
import model.Puesto;
import model.Recomendacion;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import service.CandidatoService;
import service.CapacitacionService;
import service.CompetenciaService;
import service.ExperienciaLaboralService;
import service.IdiomaCandidatoService;
import service.IdiomaService;
import service.NivelIdiomaService;
import service.PuestoService;
import service.RecomendacionService;

/**
 *
 * @author Joel Grullon
 */
public class VerCandidatoForm extends javax.swing.JInternalFrame {

    private CandidatoService candidatoService;
    private PuestoService puestoService;
    private ExperienciaLaboralService experienciaService;
    private CapacitacionService capacitacionService;
    private CompetenciaService competenciaService;
    private IdiomaService idiomaService;
    private IdiomaCandidatoService idiomaCandidatoService;
    private NivelIdiomaService nivelIdiomaService;
    private RecomendacionService recomendacionService;

    private JDesktopPane mainDesktop;

    private Candidato candidato;
    private int candidatoId;
    private List<ExperienciaLaboral> experienciasLaborales;
    private List<Competencia> competencias;
    private List<Recomendacion> recomendaciones;
    private List<Capacitacion> capacitaciones;
    private List<Idioma> idiomas;
    private List<IdiomaCandidato> idiomaCandidatos;

    /**
     * Creates new form NuevoCandidatoForm
     */
    public VerCandidatoForm(JDesktopPane desktopPanel, int candId) {

        candidatoId=candId;
        candidatoService = new CandidatoService();
        puestoService = new PuestoService();
        experienciaService = new ExperienciaLaboralService();
        capacitacionService = new CapacitacionService();
        competenciaService = new CompetenciaService();
        idiomaService = new IdiomaService();
        idiomaCandidatoService = new IdiomaCandidatoService();
        nivelIdiomaService = new NivelIdiomaService();
        recomendacionService = new RecomendacionService();

        experienciasLaborales = new ArrayList<>();
        competencias = new ArrayList<>();
        recomendaciones = new ArrayList<>();
        capacitaciones = new ArrayList<>();
        idiomaCandidatos = new ArrayList<>();
        idiomas = new ArrayList<>();

        mainDesktop = desktopPanel;

        initComponents();

        cargarDatosBasicos();
        recargarExperiencias();
        recargarCapacitaciones();
        recargarCompetencias();
        recargarIdiomas();
        recargarRecomendaciones();
    }

    private void cargarDatosBasicos(){
        candidato=candidatoService.findByIdWithRelatioships(candidatoId);

        txtNombre.setText(candidato.getNombre());
        txtApellido.setText(candidato.getApellido());
        txtCedula.setText(candidato.getCedula());
        txtEmail.setText(candidato.getEmail());
        txtSalario.setText(candidato.getSalario().toString());

        

        experienciasLaborales=candidato.getExperienciaLaborales();

        competencias=candidato.getCompetencias();
        recomendaciones=candidato.getRecomendaciones();
        capacitaciones=candidato.getCapacitaciones();
        idiomaCandidatos=candidato.getIdiomas();
    }

    public void agregarExperienciaLaboral(ExperienciaLaboral exp) {
        experienciasLaborales.add(exp);
        recargarExperiencias();       
    }

    public void recargarExperiencias(){
        DefaultTableModel modelo = (DefaultTableModel) tblExp.getModel();
        modelo.setRowCount(0);

        for (ExperienciaLaboral exp:experienciasLaborales) {
            LocalDate desde = exp.getFechaDesde();
            LocalDate hasta = exp.getFechaHasta();

            String tiempo = DateUtil.calcularPeriodo(desde, hasta);

            modelo.addRow(new Object[]{exp.getEmpresa(), exp.getPuesto(), tiempo});
        }
    }

    public void agregarCompetencia(Competencia comp) {
        competencias.add(comp);
        recargarCompetencias();       
    }

    public void recargarCompetencias(){
        DefaultTableModel modelo = (DefaultTableModel) tblCompetencias.getModel();
        modelo.setRowCount(0);

        for (Competencia comp:competencias) {
            modelo.addRow(new Object[]{comp.getNombreCompetencia(), comp.getDescripcion()});
        }
    }

    public void agregarRecomendacion(Recomendacion rec) {
        recomendaciones.add(rec);
        recargarRecomendaciones();       
    }

    public void recargarRecomendaciones(){
        DefaultTableModel modelo = (DefaultTableModel) tblRecomendacion.getModel();
        modelo.setRowCount(0);

        for (Recomendacion reco:recomendaciones) {
            modelo.addRow(new Object[]{reco.getNombreRecomendador(), reco.getContacto()});
        }
    }

    public void agregarCapacitacion(Capacitacion cap) {
        capacitaciones.add(cap);
        recargarCapacitaciones();       
    }

    public void recargarCapacitaciones(){
        DefaultTableModel modelo = (DefaultTableModel) tblCapacitaciones.getModel();
        modelo.setRowCount(0);

        for (Capacitacion cap:capacitaciones) {
            modelo.addRow(new Object[]{cap.getNombreCapacitacion(), cap.getInstitucion()});
        }
    }

    public void agregarIdioma(IdiomaCandidato idioma) {
        idiomaCandidatos.add(idioma);
        recargarIdiomas();       
    }

    public void recargarIdiomas(){
        DefaultTableModel modelo = (DefaultTableModel) tblIdiomas.getModel();
        modelo.setRowCount(0);

        for (IdiomaCandidato idiomaCandidato:idiomaCandidatos) {
            modelo.addRow(new Object[]{idiomaCandidato.getIdioma().getNombreIdioma(), idiomaCandidato.getNivelIdioma().getNombreNivel()});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblExp = new javax.swing.JTable();
        btnInfoExp = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCapacitaciones = new javax.swing.JTable();
        btnInfoCap = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCompetencias = new javax.swing.JTable();
        btnInfoComp = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblRecomendacion = new javax.swing.JTable();
        btnInfoReco = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblIdiomas = new javax.swing.JTable();
        btnInfoIdioma = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtSalario = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JLabel();
        txtApellido = new javax.swing.JLabel();
        txtCedula = new javax.swing.JLabel();
        txtEmail = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();

        btnInfoCap.setVisible(false);
        btnInfoComp.setVisible(false);
        btnInfoExp.setVisible(false);
        btnInfoIdioma.setVisible(false);
        btnInfoReco.setVisible(false);

        setClosable(true);
        setTitle("Ver Candidato");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Datos de Candidato");

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Experiencia Laboral"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblExp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre Empresa", "Puesto Ejercido", "Tiempo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblExp);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 57, 680, 180));

        btnInfoExp.setText("Detalles");
        btnInfoExp.setEnabled(false);
        jPanel2.add(btnInfoExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jPanel6.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 700, 250));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Capacitaciones"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCapacitaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Carrera", "Institucion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblCapacitaciones);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 57, 328, 184));

        btnInfoCap.setText("Detalles");
        btnInfoCap.setEnabled(false);
        jPanel3.add(btnInfoCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jPanel6.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 700, 350, 250));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Competencias"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCompetencias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Competencia", "Descripcion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblCompetencias);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 57, 328, 184));

        btnInfoComp.setText("Detalles");
        btnInfoComp.setEnabled(false);
        jPanel4.add(btnInfoComp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jPanel6.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 350, 250));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Recomendaciones"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblRecomendacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Contacto"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblRecomendacion);

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 57, 328, 184));

        btnInfoReco.setText("Detalles");
        btnInfoReco.setEnabled(false);
        jPanel5.add(btnInfoReco, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jPanel6.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 450, 350, 250));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Idiomas"));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblIdiomas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Idioma", "Nivel"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblIdiomas);

        jPanel7.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 57, 328, 184));

        btnInfoIdioma.setText("Detalles");
        btnInfoIdioma.setEnabled(false);
        jPanel7.add(btnInfoIdioma, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 700, 350, 250));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos basicos"));
        jPanel1.setToolTipText("");
        jPanel1.setName(""); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSalario.setText("salario");
        jPanel1.add(txtSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, 600, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Apellido");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Cedula");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Email");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Salario");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Nombre");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        txtNombre.setText("nombre");
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 600, -1));

        txtApellido.setText("apellido");
        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 600, -1));

        txtCedula.setText("cedula");
        jPanel1.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 600, -1));

        txtEmail.setText("email");
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 600, -1));

        jPanel6.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 700, 180));
        jPanel6.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 1000, 70, 20));

        jScrollPane5.setViewportView(jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(640, 640, 640)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInfoCap;
    private javax.swing.JButton btnInfoComp;
    private javax.swing.JButton btnInfoExp;
    private javax.swing.JButton btnInfoIdioma;
    private javax.swing.JButton btnInfoReco;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable tblCapacitaciones;
    private javax.swing.JTable tblCompetencias;
    private javax.swing.JTable tblExp;
    private javax.swing.JTable tblIdiomas;
    private javax.swing.JTable tblRecomendacion;
    private javax.swing.JLabel txtApellido;
    private javax.swing.JLabel txtCedula;
    private javax.swing.JLabel txtEmail;
    private javax.swing.JLabel txtNombre;
    private javax.swing.JLabel txtSalario;
    // End of variables declaration//GEN-END:variables
}
