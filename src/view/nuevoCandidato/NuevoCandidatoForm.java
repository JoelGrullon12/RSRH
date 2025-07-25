/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view.nuevoCandidato;

import common.DateLabelFormatter;
import common.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

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
public class NuevoCandidatoForm extends javax.swing.JInternalFrame {

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

    private List<ExperienciaLaboral> experienciasLaborales;
    private List<Competencia> competencias;
    private List<Recomendacion> recomendaciones;
    private List<Capacitacion> capacitaciones;
    private List<Idioma> idiomas;
    private List<IdiomaCandidato> idiomaCandidatos;

    /**
     * Creates new form NuevoCandidatoForm
     */
    public NuevoCandidatoForm(JDesktopPane desktopPanel) {

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
        btnAgregarExp = new javax.swing.JButton();
        btnEditarExp = new javax.swing.JButton();
        btnEliminarExp = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCapacitaciones = new javax.swing.JTable();
        btnAgregarCap = new javax.swing.JButton();
        btnEditarCap = new javax.swing.JButton();
        btnEliminarCap = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCompetencias = new javax.swing.JTable();
        btnAgregarComp = new javax.swing.JButton();
        btnEditarComp = new javax.swing.JButton();
        btnEliminarComp = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblRecomendacion = new javax.swing.JTable();
        btnAgregarReco = new javax.swing.JButton();
        btnEditarReco = new javax.swing.JButton();
        btnEliminarReco = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblIdiomas = new javax.swing.JTable();
        btnAgregarIdioma = new javax.swing.JButton();
        btnEditarIdioma = new javax.swing.JButton();
        btnEliminarIdioma = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JFormattedTextField();
        cmbPuesto = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btnVerPuestos = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtSalario = new javax.swing.JFormattedTextField();
        txtEmail = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        btnEnviarSolicitud = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();

        setClosable(true);
        setIconifiable(true);
        setTitle("Nuevo Candidato");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Nuevo Candidato");

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Experiencia Laboral"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblExp.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Nombre Empresa", "Puesto Ejercido", "Tiempo"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        tblExp.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Evitar procesar cuando aún se están ajustando los valores
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tblExp.getSelectedRow();
                    boolean buttonsEnabled = selectedRow != -1;
                    btnEditarExp.setEnabled(buttonsEnabled);
                    btnEliminarExp.setEnabled(buttonsEnabled);
                }
            }
        });

        jScrollPane1.setViewportView(tblExp);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 57, 680, 180));

        btnAgregarExp.setText("Agregar");
        btnAgregarExp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarExpActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregarExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 24, -1, -1));

        btnEditarExp.setText("Editar");
        btnEditarExp.setEnabled(false);
        btnEditarExp.addActionListener(this::btnEditarExpActionPerformed);
        jPanel2.add(btnEditarExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 24, -1, -1));

        btnEliminarExp.setText("Eliminar");
        btnEliminarExp.setEnabled(false);
        btnEliminarExp.addActionListener(this::btnEliminarExpActionPerformed);
        jPanel2.add(btnEliminarExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 24, -1, -1));

        jPanel6.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 700, 250));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Capacitaciones"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCapacitaciones.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Carrera", "Institucion"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        tblCapacitaciones.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Evitar procesar cuando aún se están ajustando los valores
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tblCapacitaciones.getSelectedRow();
                    boolean buttonsEnabled = selectedRow != -1;
                    btnEditarCap.setEnabled(buttonsEnabled);
                    btnEliminarCap.setEnabled(buttonsEnabled);
                }
            }
        });

        jScrollPane2.setViewportView(tblCapacitaciones);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 57, 328, 184));

        btnAgregarCap.setText("Agregar");
        btnAgregarCap.addActionListener(this::btnAgregarCapacitacionClick);
        jPanel3.add(btnAgregarCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 24, -1, -1));

        btnEditarCap.setText("Editar");
        btnEditarCap.setEnabled(false);
        btnEditarCap.addActionListener(this::btnEditarCapacitacionClick);
        jPanel3.add(btnEditarCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 24, -1, -1));

        btnEliminarCap.setText("Eliminar");
        btnEliminarCap.setEnabled(false);
        btnEliminarCap.addActionListener(this::btnEliminarCapacitacionClick);
        jPanel3.add(btnEliminarCap, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 24, -1, -1));

        jPanel6.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 720, 350, 250));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Competencias"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCompetencias.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Competencia", "Descripcion"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        tblCompetencias.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Evitar procesar cuando aún se están ajustando los valores
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tblCompetencias.getSelectedRow();
                    boolean buttonsEnabled = selectedRow != -1;
                    btnEditarComp.setEnabled(buttonsEnabled);
                    btnEliminarComp.setEnabled(buttonsEnabled);
                }
            }
        });

        jScrollPane3.setViewportView(tblCompetencias);

        jPanel4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 57, 328, 184));

        btnAgregarComp.setText("Agregar");
        jPanel4.add(btnAgregarComp, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 24, -1, -1));
        btnAgregarComp.addActionListener(this::btnAgregarCompClick);

        btnEditarComp.setText("Editar");
        btnEditarComp.setEnabled(false);
        btnEditarComp.addActionListener(this::btnEditarCompClick);
        jPanel4.add(btnEditarComp, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 24, -1, -1));

        btnEliminarComp.setText("Eliminar");
        btnEliminarComp.setEnabled(false);
        btnEliminarComp.addActionListener(this::btnEliminarCompClick);
        jPanel4.add(btnEliminarComp, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 24, -1, -1));

        jPanel6.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 350, 250));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Recomendaciones"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblRecomendacion.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Nombre", "Contacto"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        tblRecomendacion.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Evitar procesar cuando aún se están ajustando los valores
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tblRecomendacion.getSelectedRow();
                    boolean buttonsEnabled = selectedRow != -1;
                    btnEditarReco.setEnabled(buttonsEnabled);
                    btnEliminarReco.setEnabled(buttonsEnabled);
                }
            }
        });

        jScrollPane4.setViewportView(tblRecomendacion);

        jPanel5.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 57, 328, 184));

        btnAgregarReco.setText("Agregar");
        btnAgregarReco.addActionListener(this::btnAgregarRecomendacionClick);
        jPanel5.add(btnAgregarReco, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 24, -1, -1));

        btnEditarReco.setText("Editar");
        btnEditarReco.setEnabled(false);
        btnEditarReco.addActionListener(this::btnEditarRecomendacionClick);
        jPanel5.add(btnEditarReco, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 24, -1, -1));

        btnEliminarReco.setText("Eliminar");
        btnEliminarReco.setEnabled(false);
        btnEliminarReco.addActionListener(this::btnEliminarRecomendacionClick);
        jPanel5.add(btnEliminarReco, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 24, -1, -1));

        jPanel6.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 470, 350, 250));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Idiomas"));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblIdiomas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Idioma", "Nivel"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        tblIdiomas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Evitar procesar cuando aún se están ajustando los valores
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tblIdiomas.getSelectedRow();
                    boolean buttonsEnabled = selectedRow != -1;
                    btnEditarIdioma.setEnabled(buttonsEnabled);
                    btnEliminarIdioma.setEnabled(buttonsEnabled);
                }
            }
        });

        jScrollPane6.setViewportView(tblIdiomas);

        jPanel7.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 57, 328, 184));

        btnAgregarIdioma.setText("Agregar");
        btnAgregarIdioma.addActionListener(this::btnAgregarIdiomaClick);
        jPanel7.add(btnAgregarIdioma, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 24, -1, -1));

        btnEditarIdioma.setText("Editar");
        btnEditarIdioma.setEnabled(false);
        btnEditarIdioma.addActionListener(this::btnEditarIdiomaClick);
        jPanel7.add(btnEditarIdioma, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 24, -1, -1));

        btnEliminarIdioma.setText("Eliminar");
        btnEliminarIdioma.setEnabled(false);
        btnEliminarIdioma.addActionListener(this::btnEliminarIdiomaClick);
        jPanel7.add(btnEliminarIdioma, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 24, -1, -1));

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 720, 350, 250));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos basicos"));
        jPanel1.setToolTipText("");
        jPanel1.setName(""); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Nombre");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 26, -1, -1));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 620, -1));

        jPanel1.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 620, -1));

        jLabel3.setText("Apellido");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 56, -1, -1));

        jLabel4.setText("Cedula");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 86, -1, -1));

        txtCedula.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jPanel1.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 620, -1));

        cmbPuesto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        jPanel1.add(cmbPuesto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 530, -1));

        jLabel5.setText("Email");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 116, -1, -1));

        btnVerPuestos.setText("Mas Info.");
        jPanel1.add(btnVerPuestos, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 140, -1, -1));

        jLabel6.setText("Salario");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 176, -1, -1));

        txtSalario.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jPanel1.add(txtSalario, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 620, -1));

        txtEmail.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 620, -1));

        jLabel7.setText("Puesto");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 146, -1, -1));

        jPanel6.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 700, 210));

        btnEnviarSolicitud.setText("Enviar Solicitud");
        btnEnviarSolicitud.addActionListener(this::btnEnviarSolicitudClick);

        jPanel6.add(btnEnviarSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 970, -1, -1));
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

    private void btnAgregarExpActionPerformed(java.awt.event.ActionEvent evt) {
        AgregarExperienciaForm agExp = new AgregarExperienciaForm(this);
        mainDesktop.add(agExp);
        agExp.setVisible(true);
    }

    private void btnEditarExpActionPerformed(java.awt.event.ActionEvent evt) {
        int expSeleccionada=tblExp.getSelectedRow();

        AgregarExperienciaForm agExp = new AgregarExperienciaForm(this, experienciasLaborales.get(expSeleccionada));
        mainDesktop.add(agExp);
        agExp.setVisible(true);
    }

    private void btnEliminarExpActionPerformed(java.awt.event.ActionEvent evt) {
        int filaSeleccionada=tblExp.getSelectedRow();
        experienciasLaborales.remove(filaSeleccionada);
        recargarExperiencias();
    }

    private void btnAgregarCompClick(java.awt.event.ActionEvent evt) {
        AgregarCompetenciaForm agCompetencia=new AgregarCompetenciaForm(this);
        mainDesktop.add(agCompetencia);
        agCompetencia.setVisible(true);
    }

    private void btnEditarCompClick(java.awt.event.ActionEvent evt) {
        int filaSeleccionada=tblCompetencias.getSelectedRow();

        AgregarCompetenciaForm agComp = new AgregarCompetenciaForm(this, competencias.get(filaSeleccionada));
        mainDesktop.add(agComp);
        agComp.setVisible(true);
    }

    private void btnEliminarCompClick(java.awt.event.ActionEvent evt) { 
        int filaSeleccionada=tblCompetencias.getSelectedRow();
        competencias.remove(filaSeleccionada);
        recargarCompetencias();
    }

    private void btnAgregarRecomendacionClick(java.awt.event.ActionEvent evt) {
        AgregarRecomendacionForm agRecomendacion=new AgregarRecomendacionForm(this);
        mainDesktop.add(agRecomendacion);
        agRecomendacion.setVisible(true);
    }

    private void btnEditarRecomendacionClick(java.awt.event.ActionEvent evt) {
        int filaSeleccionada=tblRecomendacion.getSelectedRow();

        AgregarRecomendacionForm form = new AgregarRecomendacionForm(this, recomendaciones.get(filaSeleccionada));
        mainDesktop.add(form);
        form.setVisible(true);
    }

    private void btnEliminarRecomendacionClick(java.awt.event.ActionEvent evt) {
        int filaSeleccionada=tblRecomendacion.getSelectedRow();
        recomendaciones.remove(filaSeleccionada);
        recargarRecomendaciones();
    }

    private void btnAgregarCapacitacionClick(java.awt.event.ActionEvent evt) {
        AgregarCapacitacionForm agCapacitacion=new AgregarCapacitacionForm(this);
        mainDesktop.add(agCapacitacion);
        agCapacitacion.setVisible(true);
    }

    private void btnEditarCapacitacionClick(java.awt.event.ActionEvent evt) {
        int filaSeleccionada=tblCapacitaciones.getSelectedRow();

        AgregarCapacitacionForm form = new AgregarCapacitacionForm(this, capacitaciones.get(filaSeleccionada));
        mainDesktop.add(form);
        form.setVisible(true);
    }

    private void btnEliminarCapacitacionClick(java.awt.event.ActionEvent evt) {
        int filaSeleccionada=tblCapacitaciones.getSelectedRow();
        capacitaciones.remove(filaSeleccionada);
        recargarCapacitaciones();
    }

    private void btnAgregarIdiomaClick(java.awt.event.ActionEvent evt) {
        AgregarIdiomaCandidatoForm form=new AgregarIdiomaCandidatoForm(this);
        mainDesktop.add(form);
        form.setVisible(true);
    }

    private void btnEditarIdiomaClick(java.awt.event.ActionEvent evt) {
        int filaSeleccionada=tblIdiomas.getSelectedRow();

        AgregarIdiomaCandidatoForm form = new AgregarIdiomaCandidatoForm(this, idiomaCandidatos.get(filaSeleccionada));
        mainDesktop.add(form);
        form.setVisible(true);
    }

    private void btnEliminarIdiomaClick(java.awt.event.ActionEvent evt) {
        int filaSeleccionada=tblIdiomas.getSelectedRow();
        idiomaCandidatos.remove(filaSeleccionada);
        recargarIdiomas();
    }

    private void btnEnviarSolicitudClick(java.awt.event.ActionEvent evt){

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarCap;
    private javax.swing.JButton btnAgregarComp;
    private javax.swing.JButton btnAgregarExp;
    private javax.swing.JButton btnEnviarSolicitud;
    private javax.swing.JButton btnAgregarIdioma;
    private javax.swing.JButton btnAgregarReco;
    private javax.swing.JButton btnEditarCap;
    private javax.swing.JButton btnEditarComp;
    private javax.swing.JButton btnEditarExp;
    private javax.swing.JButton btnEditarIdioma;
    private javax.swing.JButton btnEditarReco;
    private javax.swing.JButton btnEliminarCap;
    private javax.swing.JButton btnEliminarComp;
    private javax.swing.JButton btnEliminarExp;
    private javax.swing.JButton btnEliminarIdioma;
    private javax.swing.JButton btnEliminarReco;
    private javax.swing.JButton btnVerPuestos;
    private javax.swing.JComboBox<String> cmbPuesto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JTextField txtApellido;
    private javax.swing.JFormattedTextField txtCedula;
    private javax.swing.JFormattedTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JFormattedTextField txtSalario;
    // End of variables declaration//GEN-END:variables
}
