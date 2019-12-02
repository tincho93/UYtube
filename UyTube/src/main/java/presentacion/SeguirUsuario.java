package presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import interfaces.IUsuario;

public class SeguirUsuario extends JInternalFrame {
	private JList listaSeguidores;
	private JList listaSeguidos = new JList();
	private JButton btnConfirmar = new JButton("Seguir");
	private final JLabel lblMsgExito = new JLabel("Se siguio al usuario");
	private JButton btnSelecSeguidor = new JButton("Seleccionar");
	private String seguidor = "";
	private JLabel lblMsgError1 = new JLabel("Debe seleccionar un usuario");
	private JLabel lblMsgError2 = new JLabel("Debe seleccionar un usuario");
	
	public SeguirUsuario(IUsuario iU) {
		setTitle("Seguir un usuario");
		setBounds(100, 100, 800, 542);
		getContentPane().setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeguirUsuario.this.setVisible(false);
				
			}
		});
		btnSalir.setBounds(606, 477, 168, 25);
		getContentPane().add(btnSalir);
		
		JScrollPane scrollListaSeguidores = new JScrollPane();
		scrollListaSeguidores.setBounds(184, 106, 168, 190);
		getContentPane().add(scrollListaSeguidores);
		
		listaSeguidores  = new JList();
		DefaultListModel<String> listaU = new DefaultListModel<String>();
		listaSeguidores.setModel(listaU);
		scrollListaSeguidores.setViewportView(listaSeguidores);
		
		
		btnSelecSeguidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = listaSeguidores.getSelectedIndex();
				if (i < 0) {
					lblMsgError1.setVisible(true);
				} else {
					seguidor = listaSeguidores.getModel().getElementAt(i).toString();
					listaSeguidores.setEnabled(false);
					btnSelecSeguidor.setEnabled(false);
					listaSeguidos.setEnabled(true);
					btnConfirmar.setEnabled(true);
				}
			}
		});
		
		btnSelecSeguidor.setBounds(184, 309, 168, 25);
		getContentPane().add(btnSelecSeguidor);
		
		JLabel lblseguidor = new JLabel("Seguidor");
		lblseguidor.setBounds(237, 92, 58, 14);
		getContentPane().add(lblseguidor);
		
		JScrollPane scrollSeguidos = new JScrollPane();
		scrollSeguidos.setBounds(387, 106, 168, 190);
		getContentPane().add(scrollSeguidos);
		
		listaSeguidos.setEnabled(false);
		listaSeguidos.setModel(new DefaultListModel<String>());
		scrollSeguidos.setViewportView(listaSeguidos);
		
		JLabel lblSeguido = new JLabel("Seguido");
		lblSeguido.setBounds(437, 92, 67, 14);
		getContentPane().add(lblSeguido);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = listaSeguidos.getSelectedIndex();
				if (i < 0) {
					lblMsgError2.setVisible(true);
				} else {
					String seguido = listaSeguidores.getModel().getElementAt(i).toString();
					iU.seguirUsuario(seguidor, seguido);
					iU.limpiarControlador();
					inicializar(iU);
					lblMsgExito.setVisible(true);
				}
			}
		});
		
		
		btnConfirmar.setEnabled(false);
		btnConfirmar.setBounds(387, 309, 168, 25);
		getContentPane().add(btnConfirmar);
		lblMsgExito.setForeground(new Color(127, 255, 0));
		lblMsgExito.setBounds(405, 344, 178, 14);
		
		getContentPane().add(lblMsgExito);
		
		
		lblMsgError1.setForeground(Color.RED);
		lblMsgError1.setBounds(173, 345, 235, 14);
		getContentPane().add(lblMsgError1);
		
		lblMsgError2.setForeground(Color.RED);
		lblMsgError2.setBounds(377, 345, 202, 14);
		getContentPane().add(lblMsgError2);
		
	}
	
	public void cargarElementos(IUsuario iU) {
		List<String> usuarios = iU.listarUsuarios();
		DefaultListModel<String> listaU = new DefaultListModel<String>();
		int i = 0;
		for (String u: usuarios) {
			listaU.add(i++, u);
		}
		listaSeguidores.setModel(listaU);
		listaSeguidos.setModel(listaU);
	}
	
	public void limpiarLista() {
		((DefaultListModel) listaSeguidores.getModel()).clear();
		listaSeguidores.setEnabled(true);
		((DefaultListModel) listaSeguidos.getModel()).clear();
		listaSeguidos.setEnabled(false);
		
	}
	
	public void limpiarForm() {
		btnSelecSeguidor.setEnabled(true);
		btnConfirmar.setEnabled(false);
	}
	
	public void resetearMensajes() {
		lblMsgExito.setVisible(false);
		lblMsgError1.setVisible(false);
		lblMsgError2.setVisible(false);
	}
	
	public void inicializar(IUsuario iU) {
		limpiarLista();
		limpiarForm();
		cargarElementos(iU);
		resetearMensajes();
	}
	
}