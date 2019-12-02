package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import datatypes.DtListaRep;
import interfaces.ICategoria;
import interfaces.IListaReproduccion;
import interfaces.IUsuario;
import java.awt.Color;

public class ModificarLista extends JInternalFrame {
	private JComboBox categoria = new JComboBox();
	private JList listaUsr = new JList();
	private JList listaList = new JList();
	private JButton btnSalir = new JButton("Salir");
	private JCheckBox publica = new JCheckBox("");
	private boolean esPublica = false;
	private IUsuario iU;
	private IListaReproduccion iL;
	private ICategoria iC;
	private JButton btnSelectList = new JButton("Seleccionar");
	private JButton btnSelectUsr = new JButton("Seleccionar");
	private final JLabel lblListasParticulares = new JLabel("Listas Particulares");
	private String usuarioLista = "";
	private String nomLista = "";
	private Boolean seleccionoLista = false;
	private JButton btnConfirmar = new JButton("Confirmar");
	private final JLabel lblMsgOK = new JLabel("Se han modificado los datos");
	private Boolean esVacia = false;
	

	
	public ModificarLista(IUsuario iU, IListaReproduccion iL, ICategoria iC) {
		getContentPane().setEnabled(false);
		
		this.iU = iU;
		this.iL = iL;
		this.iC = iC;
		setTitle("Modificar Lista de Reproduccion");
		setBounds(100, 100, 800, 542);
		getContentPane().setLayout(null);
		
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicializar();	
				((DefaultListModel) listaList.getModel()).clear();
				listaList.setEnabled(false);
				publica.setEnabled(false);
				ModificarLista.this.setVisible(false);
			}
		});
		btnSalir.setBounds(606, 476, 168, 25);
		getContentPane().add(btnSalir);
		
		JScrollPane scrollListaUsr = new JScrollPane();
		scrollListaUsr.setBounds(23, 26, 168, 190);
		getContentPane().add(scrollListaUsr);
		
		listaUsr  = new JList();
		DefaultListModel<String> listaU = new DefaultListModel<String>();
		listaUsr.setModel(listaU);
		scrollListaUsr.setViewportView(listaUsr);
		
		JScrollPane scrollListaList = new JScrollPane();
		scrollListaList.setBounds(201, 26, 168, 190);
		getContentPane().add(scrollListaList);
		
		
		listaList.setEnabled(false);
		listaList.setModel(new DefaultListModel<String>());
		scrollListaList.setViewportView(listaList);
		
		JLabel lblVideoPublico = new JLabel("Lista publica");
		lblVideoPublico.setBounds(429, 202, 124, 14);
		getContentPane().add(lblVideoPublico);
		publica.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				esPublica = ! esPublica;
			}
		});
		
		
		publica.setBounds(555, 198, 27, 23);
		getContentPane().add(publica);
		
		categoria.setBounds(555, 124, 219, 24);
		getContentPane().add(categoria);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(431, 129, 70, 15);
		getContentPane().add(lblCategoria);
		btnSelectUsr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaList.setEnabled(true);
				btnSelectList.setEnabled(true);
				int i = listaUsr.getSelectedIndex();
				usuarioLista = listaUsr.getModel().getElementAt(i).toString();
				((DefaultListModel) listaList.getModel()).clear();
				List<String> listas = iL.listarListasParticulares(usuarioLista);
				if (!listas.isEmpty()) {
					for (String l: listas) {
						((DefaultListModel) listaList.getModel()).addElement(l);
					}
				}
				iL.setuList(usuarioLista);
				btnSelectUsr.setEnabled(false);
				listaUsr.setEnabled(false);
			}
		});
		
		btnSelectUsr.setEnabled(true);
		btnSelectUsr.setBounds(23, 229, 168, 25);
		getContentPane().add(btnSelectUsr);
		btnSelectList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionoLista = true;
				btnSelectUsr.setEnabled(false);
				btnConfirmar.setEnabled(true);
				int i = listaList.getSelectedIndex();
				nomLista = listaList.getModel().getElementAt(i).toString();
				listaUsr.setEnabled(false);
				btnSelectList.setEnabled(false);
				publica.setEnabled(true);
				DtListaRep infoL = iL.obtenerListaDeUsuario(nomLista);
				if (infoL.getCategoria() == null) {
					esVacia = true;
					categoria.setSelectedIndex(0);
				} else {
					categoria.setSelectedItem(infoL.getCategoria());
					esVacia = false;
				}
				publica.setSelected(infoL.getPublico());
				esPublica =  infoL.getPublico();
				iL.setLista(nomLista);
			}
		});
	
		btnSelectList.setEnabled(false);
		btnSelectList.setBounds(201, 230, 168, 23);
		getContentPane().add(btnSelectList);
		
		JLabel lblUsr = new JLabel("Usuarios");
		lblUsr.setBounds(73, 9, 70, 15);
		getContentPane().add(lblUsr);
		lblListasParticulares.setBounds(218, 10, 149, 15);
		
		getContentPane().add(lblListasParticulares);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (categoria.getSelectedIndex() == 0) {
					if (!esVacia) {
						iL.eliminarCategoria();						
					}
				} else {
					iL.modificarCategoria(categoria.getSelectedItem().toString());					
				}
				iL.modificarInfoLista(nomLista, publica.isSelected());
				inicializar();
				lblMsgOK.setVisible(true);
			}
		});
		
		btnConfirmar.setBounds(606, 432, 168, 25);
		getContentPane().add(btnConfirmar);
		lblMsgOK.setForeground(new Color(124, 252, 0));
		lblMsgOK.setBounds(588, 405, 223, 15);
		listaUsr.setEnabled(true);
		limpiarLista();
		btnSelectList.setEnabled(false);
		getContentPane().add(lblMsgOK);
		

	}
	
	public void limpiarLista() {	
		((DefaultListModel) listaList.getModel()).clear();
		listaList.setEnabled(false);
	    
	}
	
	public void limpiarForm() {
		btnSelectUsr.setEnabled(true);
		((DefaultListModel) listaUsr.getModel()).clear();
		listaUsr.setEnabled(true);
		btnSelectList.setEnabled(false);
		categoria.setSelectedIndex(0);
		lblMsgOK.setVisible(false);
		publica.setEnabled(false);
	}
	
	public void cargarCategorias() {
		categoria.removeAllItems();
		categoria.addItem("<Sin categoria>");
		List<String> categorias = iC.listarCategorias();
		for (String c: categorias) {
			categoria.addItem(c);
		}
	}
	
	public void cargarUsuarios() {
		List<String> usuarios = iU.listarUsuarios();
		DefaultListModel<String> listaU = new DefaultListModel<String>();
		int i = 0;
		for( String u: usuarios) {
			listaU.add(i++, u);
		}
		listaUsr.setModel(listaU);
	}
	
	public void cargarLista(String nick, String lis) {
		listaUsr.setEnabled(false);
		iL.setuList(nick);
		iL.setLista(lis);
		DtListaRep infoL = iL.obtenerListaDeUsuario(lis);
		seleccionoLista = true;
		btnSelectUsr.setEnabled(false);
		btnConfirmar.setEnabled(true);
		listaUsr.setEnabled(false);
		btnSelectList.setEnabled(false);
		publica.setEnabled(true);
		if (infoL.getCategoria().isEmpty()) {
			esVacia = true;
			categoria.setSelectedIndex(0);
		} else {
			categoria.setSelectedItem(infoL.getCategoria());
			esVacia = false;
		}
		publica.setSelected(infoL.getPublico());
		esPublica =  infoL.getPublico();
	}
	
	public void inicializar() {
		cargarCategorias();
		limpiarLista();
		limpiarForm();
		cargarUsuarios();
	}
}
