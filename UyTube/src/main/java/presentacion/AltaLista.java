package presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import interfaces.ICategoria;
import interfaces.IListaReproduccion;
import interfaces.IUsuario;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class AltaLista extends JInternalFrame {
	private JList listaUsr;
	private String usr = "";
	private JTextField nomLis;
	private JButton btnSelecUsr = new JButton("Seleccionar Usuario");
	private JScrollPane scrollPane = new JScrollPane();
	private JComboBox categoria = new JComboBox();
	private JButton btnAgregar = new JButton("Agregar");
	private JLabel lblMsgExiste = new JLabel("Ya existe una lista con este nombre en el canal.");
	private JLabel lblMsgError = new JLabel("Error: falta completar algun campo.");
	private JLabel lblMsgExito = new JLabel("La lista fue agregada con exito.");
	private JLabel lblMsgErrorUsr = new JLabel("Debe seleccionar un usuario");
	private final JLabel lblListaDeUsuarios = new JLabel("Lista de Usuarios");
	private JButton btnParticular = new JButton("Particular");
	private JCheckBox chckbxListaPublica = new JCheckBox("Lista Publica");
	private JButton btnPorDefecto = new JButton("Por Defecto");
	private Boolean listaParticular = false;
	private Boolean listaPublica = false;
	
	public AltaLista(IUsuario iU, ICategoria iC, IListaReproduccion iL) {
		
		setTitle("Agregar una Lista de Reproduccion");
		setBounds(0, 0, 800, 542);
		AltaLista.this.setVisible(false);
		getContentPane().setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(629, 473, 117, 25);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaLista.this.setVisible(false);
			}
		});
		getContentPane().add(btnSalir);
		btnSelecUsr.setEnabled(false);
		
		btnSelecUsr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reiniciarMsg();
				if (usr.isEmpty()) {
					lblMsgErrorUsr.setVisible(true);
				} else {
					habilitarFormUsr(false);
					habilitarFormVid(true);
				}
			}
		});
		
		
		btnSelecUsr.setBounds(36, 438, 198, 25);
		getContentPane().add(btnSelecUsr);
		
		
		scrollPane.setBounds(36, 192, 198, 203);
		getContentPane().add(scrollPane);
		
		DefaultListModel<String> listaU = new DefaultListModel<String>();
		listaUsr  = new JList(listaU);
		listaUsr.setEnabled(false);
		scrollPane.setViewportView(listaUsr);
		listaUsr.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			int i = listaUsr.getSelectedIndex();
			if (i > 0) {
				usr = listaUsr.getModel().getElementAt(i).toString();
			}
		}	
		});
		listaUsr.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(282, 50, 89, 15);
		getContentPane().add(lblNombre);
		
		nomLis = new JTextField();
		nomLis.setBounds(449, 48, 313, 19);
		getContentPane().add(nomLis);
		nomLis.setColumns(10);
		
		
		categoria.setBounds(449, 354, 313, 24);
		getContentPane().add(categoria);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(282, 359, 70, 15);
		getContentPane().add(lblCategoria);
		btnAgregar.setEnabled(false);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)  {
				//Hay que ver errores anotados en cuadernola mateo
				reiniciarMsg();
				if (nomLis.getText().isEmpty()) {
					lblMsgError.setVisible(true);
				} else {
					
					if (listaParticular) {
						if (iL.existeListaParticular(usr, nomLis.getText())) {
							lblMsgExiste.setVisible(true);
						} else {
							if (categoria.getSelectedIndex() != 0) {
								iL.agregarListaParticularCategoria(nomLis.getText(), listaPublica, categoria.getSelectedItem().toString());
							} else {
								iL.agregarListaParticular(nomLis.getText(), listaPublica);
							}

						}
					} else {
						if (iL.existeListaDefecto(nomLis.getText())) {
							lblMsgExiste.setVisible(true);
						} else {
							iL.agregarListaDefecto(nomLis.getText());
						}
					}
					
					inicializar(iU, iC);
					lblMsgExito.setVisible(true);
				}
				
			}
		});
		
		
		btnAgregar.setBounds(474, 473, 117, 25);
		getContentPane().add(btnAgregar);
		
		
		lblMsgExiste.setForeground(Color.RED);
		lblMsgExiste.setBounds(459, 70, 303, 15);
		getContentPane().add(lblMsgExiste);
		
		
		lblMsgError.setForeground(Color.RED);
		lblMsgError.setBounds(484, 443, 278, 15);
		getContentPane().add(lblMsgError);
		
		
		lblMsgExito.setForeground(new Color(124, 252, 0));
		lblMsgExito.setBounds(492, 443, 254, 15);
		getContentPane().add(lblMsgExito);
		lblMsgErrorUsr.setForeground(Color.RED);
		lblMsgErrorUsr.setBounds(36, 407, 219, 15);
		
		getContentPane().add(lblMsgErrorUsr);
		lblListaDeUsuarios.setBounds(36, 166, 137, 15);
		
		getContentPane().add(lblListaDeUsuarios);
		chckbxListaPublica.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				listaPublica = !listaPublica;
			}
		});
		
		chckbxListaPublica.setEnabled(false);
		chckbxListaPublica.setBounds(449, 103, 142, 23);
		getContentPane().add(chckbxListaPublica);
		
		
		btnParticular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listaUsr.setEnabled(true);
				btnSelecUsr.setEnabled(true);
				btnParticular.setEnabled(false);
				btnPorDefecto.setEnabled(false);
				listaParticular = true;
			}
		});
		
		btnParticular.setBounds(12, 103, 109, 23);
		getContentPane().add(btnParticular);
		btnPorDefecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nomLis.setEnabled(true);
				btnAgregar.setEnabled(true);
				listaParticular = false;
			}
		});
		
		btnPorDefecto.setBounds(143, 103, 117, 23);
		getContentPane().add(btnPorDefecto);

	}
	
	public void cargarUsuarios(IUsuario iU) {
		List<String> usuarios = iU.listarUsuarios();
		((DefaultListModel) listaUsr.getModel()).addElement("");
		for (String u: usuarios) {
			((DefaultListModel) listaUsr.getModel()).addElement(u);
		}
	}
	
	public void cargarCategorias(ICategoria iC) {
		List<String> categorias = iC.listarCategorias();
		categoria.addItem("<Sin categoria>");
		for (String c: categorias) {
			categoria.addItem(c);
		}
	}
	
	public void limpiarListas() {
		((DefaultListModel) listaUsr.getModel()).clear();
		categoria.removeAllItems();
	}
	
	public void habilitarFormVid(Boolean flag) {
		nomLis.setEnabled(flag);
		chckbxListaPublica.setEnabled(flag);
		categoria.setEnabled(flag);
		btnAgregar.setEnabled(flag);
	}
	
	public void habilitarFormUsr(Boolean flag) {
		listaUsr.setEnabled(flag);
		btnSelecUsr.setEnabled(flag);
	}
	
	public void reiniciarMsg() {
		lblMsgExiste.setVisible(false);
		lblMsgError.setVisible(false);
		lblMsgExito.setVisible(false);
		lblMsgErrorUsr.setVisible(false);
	}
	
	public void reiniciarVal() {
		nomLis.setText("");
		listaPublica = false;
		chckbxListaPublica.setSelected(false);
		categoria.setSelectedIndex(0);
		usr = "";
		btnParticular.setEnabled(true);
		btnPorDefecto.setEnabled(true);
	}
	
	public void inicializar(IUsuario iU, ICategoria iC) {
		iC.limpiarControlador();
		iU.limpiarControlador();
		limpiarListas();
		cargarUsuarios(iU);
		cargarCategorias(iC);
		habilitarFormUsr(false);
		habilitarFormVid(false);
		reiniciarVal();
		reiniciarMsg();
	}
}
