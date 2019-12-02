package presentacion;

import interfaces.*;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaCategoria extends JInternalFrame {
	
	private JTextField nomCat;
	private JButton btnAgregar = new JButton("Agregar");
	private JButton btnCancelar = new JButton("Cancelar");
	private JLabel lblNombre = new JLabel("Nombre");
	private JLabel lblMsgErrorExiste = new JLabel("Error: La categoria ya existe.");
	private JLabel lblMsgExito = new JLabel("La categoria fue agregada con exito.");
	private final JLabel lblMsgErrorVacio = new JLabel("Error: El campo nombre esta vacio.");
	
	/**
	 * Create the frame.
	 */
	public AltaCategoria(ICategoria iC) {
		setTitle("Agregar una Categoria");
		setBounds(100, 100, 800, 542);
		getContentPane().setLayout(null);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblMsgErrorVacio.setVisible(false);
				lblMsgErrorExiste.setVisible(false);
				lblMsgExito.setVisible(false);
				
				if (nomCat.getText().isEmpty()) {
					lblMsgErrorVacio.setVisible(true);
				} else if (iC.existeCategoria(nomCat.getText())){
					lblMsgErrorExiste.setVisible(true);
				} else {
					iC.altaCategoria(nomCat.getText());
					resetearFormulario();
					lblMsgExito.setVisible(true);
				}
			}
		});
		
		
		btnAgregar.setBounds(82, 214, 117, 25);
		getContentPane().add(btnAgregar);
		
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetearFormulario();
				AltaCategoria.this.setVisible(false);
				
			}
		});
		
		btnCancelar.setBounds(246, 214, 117, 25);
		getContentPane().add(btnCancelar);
		
		nomCat = new JTextField();
		
		nomCat.setBounds(170, 96, 193, 19);
		getContentPane().add(nomCat);
		nomCat.setColumns(10);
		
		
		lblNombre.setBounds(82, 98, 70, 15);
		getContentPane().add(lblNombre);
		
		
		lblMsgErrorExiste.setForeground(Color.RED);
		lblMsgErrorExiste.setBounds(82, 148, 281, 15);
		lblMsgErrorExiste.setVisible(false);
		
		
		lblMsgExito.setForeground(new Color(124, 252, 0));
		lblMsgExito.setBounds(82, 148, 281, 15);
		lblMsgExito.setVisible(false);
		getContentPane().add(lblMsgExito);
		getContentPane().add(lblMsgErrorExiste);
		lblMsgErrorVacio.setForeground(Color.RED);
		lblMsgErrorVacio.setBounds(82, 148, 281, 15);
		lblMsgErrorVacio.setVisible(false);
		
		getContentPane().add(lblMsgErrorVacio);
		this.setVisible(false);
	}
	
	public void resetearFormulario() {
		lblMsgExito.setVisible(false);
		lblMsgErrorExiste.setVisible(false);
		lblMsgErrorVacio.setVisible(false);
		nomCat.setText("");
	}
}
