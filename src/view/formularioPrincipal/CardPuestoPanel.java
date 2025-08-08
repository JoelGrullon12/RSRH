package view.formularioPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class CardPuestoPanel extends JPanel {
    private JLabel lblTitulo;
    private JLabel lblSalario;
    private JButton btnVer;
    private JButton btnEditar;

    public CardPuestoPanel(String nombrePuesto, BigDecimal salarioMinimo, BigDecimal salarioMaximo,
                           ActionListener onVerClick, ActionListener onEditarClick) {

        setPreferredSize(new Dimension(300, 120));
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.GRAY, 1), // Borde visible
            BorderFactory.createEmptyBorder(10, 10, 10, 10) // Padding: top, left, bottom, right
        ));
        setLayout(new BorderLayout(5, 5));
        setBackground(Color.WHITE);

        // Panel superior: título
        lblTitulo = new JLabel(nombrePuesto);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo, BorderLayout.NORTH);

        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        String salarioMinimoFormatted = formatter.format(salarioMinimo);
        String salarioMaximoFormatted = formatter.format(salarioMaximo);

        // Panel centro: salario
        lblSalario = new JLabel("RD%s - RD%s".formatted(salarioMinimoFormatted, salarioMaximoFormatted));
        lblSalario.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblSalario, BorderLayout.CENTER);

        // Panel inferior: botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        btnVer = new JButton("Mas Informacion");
        btnEditar = new JButton("Aplicar");

        btnVer.addActionListener(onVerClick);
        btnEditar.addActionListener(onEditarClick);

        buttonPanel.add(btnVer);
        buttonPanel.add(btnEditar);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
