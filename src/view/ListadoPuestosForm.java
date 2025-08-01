package view;

import javax.swing.*;
import model.Puesto;
import service.PuestoService;

import java.awt.*;
import java.util.List;

public class ListadoPuestosForm extends JInternalFrame {

    private JPanel cardsPanel;
    private JScrollPane scrollPane;

    public ListadoPuestosForm() {
        super("Listado de Puestos", true, true, true, true);
        initComponents();
        cargarPuestos();
    }

    private void initComponents() {
        cardsPanel = new JPanel();
        cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
        cardsPanel.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(cardsPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);

        this.setSize(600, 400); // Ajusta el tamaño a tu preferencia
    }

    private void cargarPuestos() {
        // Aquí debes reemplazar con tu acceso real a la BD
        PuestoService dao = new PuestoService(); // Asegúrate de tener esta clase
        List<Puesto> puestos = dao.getAll();

        cardsPanel.removeAll();

        for (Puesto p : puestos) {
            cardsPanel.add(crearCardPuesto(p));
            cardsPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre cards
        }

        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    private JPanel crearCardPuesto(Puesto puesto) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setBackground(new Color(245, 245, 245)); // Gris claro

        JLabel lblNombre = new JLabel("Puesto: " + puesto.getNombrePuesto());
        JLabel lblDescripcion = new JLabel("Descripción: " + puesto.getDescripcion());
        JLabel lblSalario = new JLabel("Salario: " + puesto.getSalarioMinimo() + " - " + puesto.getSalarioMaximo());
        JLabel lblVacantes = new JLabel("Vacantes: " + puesto.getVacantes());

        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblDescripcion.setFont(new Font("Arial", Font.PLAIN, 13));
        lblSalario.setFont(new Font("Arial", Font.PLAIN, 13));
        lblVacantes.setFont(new Font("Arial", Font.PLAIN, 13));

        card.add(lblNombre);
        card.add(lblDescripcion);
        card.add(lblSalario);
        card.add(lblVacantes);

        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        return card;
    }
}
