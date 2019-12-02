package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.*;
import datatypes.DtListaRep;
import datatypes.DtVideoUsuario;
import interfaces.IListaReproduccion;
import interfaces.IUsuario;
import javax.swing.JTable;

public class ConsultaListaRep extends JInternalFrame {

	private JList listaUsr = new JList();
	private JTextField nomVid = new JTextField();
	private JButton btnSeleccionarUsuario = new JButton("Seleccionar");
	private JList listaLis = new JList();
	private JButton btnSelecLis = new JButton("Seleccionar");
	private JCheckBox publico = new JCheckBox("");
	private JCheckBox chkboxPorDefecto = new JCheckBox("");
	private IUsuario iU;
	private IListaReproduccion iL;
	private JTable tablaVid;
	private Object[] columnas = {"Video", "Propietario"};
	private Object[][] datosTabla = {};

	public ConsultaListaRep(IUsuario iU, IListaReproduccion iL, ConsultaVideo cV) {
		this.iU = iU;
		this.iL = iL;
		setTitle("Consultar una lista de reproduccion");
		setBounds(100, 100, 800, 542);
		getContentPane().setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicializar();	
				
				ConsultaListaRep.this.setVisible(false);
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
		
		
		btnSeleccionarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = listaUsr.getSelectedIndex();
				String usr = listaUsr.getModel().getElementAt(i).toString();
				((DefaultListModel) listaLis.getModel()).clear();
				List<String> listas = iL.listarListasDeUsuario(usr);
				if (!listas.isEmpty()) {
					for (String l: listas) {
						((DefaultListModel) listaLis.getModel()).addElement(l);
					}
				}

				listaUsr.setEnabled(false);
				btnSeleccionarUsuario.setEnabled(false);
				
				listaLis.setEnabled(true);
				btnSelecLis.setEnabled(true);
			}
		});
		
		btnSeleccionarUsuario.setBounds(23, 229, 168, 25);
		getContentPane().add(btnSeleccionarUsuario);
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(76, 12, 58, 14);
		getContentPane().add(lblUsuarios);
		
		JScrollPane scrollListaLis = new JScrollPane();
		scrollListaLis.setBounds(201, 26, 168, 190);
		getContentPane().add(scrollListaLis);
		
		
		listaLis.setEnabled(false);
		listaLis.setModel(new DefaultListModel<String>());
		scrollListaLis.setViewportView(listaLis);
		
		JLabel lblVideos = new JLabel("Listas de reproduccion");
		lblVideos.setBounds(229, 12, 140, 14);
		getContentPane().add(lblVideos);
		btnSelecLis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaLis.setEnabled(false);
				btnSelecLis.setEnabled(false);
				int i = listaLis.getSelectedIndex();
				String lis = listaLis.getModel().getElementAt(i).toString();
				DtListaRep infoL = iL.obtenerListaDeUsuario(lis);
				nomVid.setText(infoL.getNombre());
				publico.setSelected(infoL.getPublico());
				chkboxPorDefecto.setSelected(!infoL.getEsParticular());
				List<DtVideoUsuario> videos = iL.listarVideosdeLista(lis);
				if (!videos.isEmpty()) {
					TableModel modelo = (DefaultTableModel) tablaVid.getModel();
					for (DtVideoUsuario v: videos) {
						((DefaultTableModel) modelo).addRow(new Object[]{v.getNombreE(), v.getNickname()});
					}
				}
			}
		});
		
		
		btnSelecLis.setEnabled(false);
		btnSelecLis.setBounds(201, 230, 168, 23);
		getContentPane().add(btnSelecLis);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(420, 58, 70, 15);
		getContentPane().add(lblNombre);
		
		nomVid = new JTextField();
		nomVid.setEnabled(false);
		nomVid.setBounds(555, 55, 219, 19);
		getContentPane().add(nomVid);
		nomVid.setColumns(10);
		
		JLabel lblVideoPublico = new JLabel("Lista publica");
		lblVideoPublico.setBounds(420, 96, 98, 14);
		getContentPane().add(lblVideoPublico);
		publico.setEnabled(false);
		
		
		publico.setBounds(556, 91, 27, 23);
		getContentPane().add(publico);
		
		JScrollPane scrollListaVid = new JScrollPane();
		scrollListaVid.setBounds(23, 300, 333, 190);
		getContentPane().add(scrollListaVid);
		
		tablaVid = new JTable();
		TableModel modelo =  new DefaultTableModel(datosTabla, columnas);
		tablaVid.setModel(modelo);
		scrollListaVid.setViewportView(tablaVid);
		
		
		JLabel lblVideos_1 = new JLabel("Videos");
		lblVideos_1.setBounds(44, 275, 46, 14);
		getContentPane().add(lblVideos_1);
		
		JLabel lblListaPorDefecto = new JLabel("Lista por defecto");
		lblListaPorDefecto.setBounds(420, 137, 127, 14);
		getContentPane().add(lblListaPorDefecto);
		
		
		chkboxPorDefecto.setEnabled(false);
		chkboxPorDefecto.setBounds(555, 133, 27, 23);
		getContentPane().add(chkboxPorDefecto);
		
		JButton btnSelecVid = new JButton("Seleccionar");
		btnSelecVid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = tablaVid.getSelectedRow();
				String vid = tablaVid.getValueAt(i, 0).toString();
				String usr = tablaVid.getValueAt(i, 1).toString();
				cV.inicializar();
				cV.cargarVideo(usr, vid);
				ConsultaListaRep.this.setVisible(false);
				cV.setVisible(true);
				
			}
		});
		btnSelecVid.setBounds(366, 312, 89, 23);
		getContentPane().add(btnSelecVid);
		
		
	}
	public void cargarElementos() {
		List<String> usuarios = iU.listarUsuarios();
		DefaultListModel<String> listaU = new DefaultListModel<String>();
		int i = 0;
		for (String u: usuarios) {
			listaU.add(i++, u);
		}
		listaUsr.setModel(listaU);
	}
		
	
	
	
	public void limpiarLista() {
		listaUsr.setEnabled(false);
		((DefaultListModel) listaUsr.getModel()).clear();
		listaLis.setEnabled(false);
		((DefaultListModel) listaLis.getModel()).clear();
		TableModel modelo = (DefaultTableModel) tablaVid.getModel();
		while (modelo.getRowCount() > 0) {
		    ((DefaultTableModel) modelo).removeRow(0);
		}
		
	}
	
	public void limpiarForm() {
		btnSeleccionarUsuario.setEnabled(true);
		((DefaultListModel) listaUsr.getModel()).clear();
		listaUsr.setEnabled(true);
		btnSelecLis.setEnabled(false);
		nomVid.setText("");
	}
	
	
	public void inicializar() {
		limpiarLista();
		limpiarForm();
		cargarElementos();
	}
	
	public void cargarLista(String nick, String lis) {
		listaUsr.setEnabled(false);
		btnSeleccionarUsuario.setEnabled(false);
		listaLis.setEnabled(false);
		btnSelecLis.setEnabled(false);
		iL.setuList(nick);
		DtListaRep infoL = iL.obtenerListaDeUsuario(lis);
		nomVid.setText(infoL.getNombre());
		publico.setSelected(infoL.getPublico());
		chkboxPorDefecto.setSelected(!infoL.getEsParticular());
		List<DtVideoUsuario> videos = iL.listarVideosdeLista(lis);
		if (!videos.isEmpty()) {
			TableModel modelo = (DefaultTableModel) tablaVid.getModel();
			for (DtVideoUsuario v: videos) {
				((DefaultTableModel) modelo).addRow(new Object[]{v.getNombreE(), v.getNickname()});
			}
		}
	}
}
