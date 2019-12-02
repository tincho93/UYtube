package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import interfaces.IListaReproduccion;
import interfaces.IUsuario;
import interfaces.IVideo;
import java.awt.Color;

public class AgregarVideoLista extends JInternalFrame {
	private JList listaUsrV;
	private JButton btnSelecUsrV = new JButton("Seleccionar");
	private JList listUsrL = new JList();
	private JList listList = new JList();
	private JButton btnSelecUsrL = new JButton("Seleccionar");
	private JButton btnSelecLista = new JButton("Seleccionar");
	private JButton btnAgregar = new JButton("Agregar");
	private JList listaVid = new JList();
	private JButton btnSelecVid = new JButton("Seleccionar");
	private IVideo iV;
	private IUsuario iU;
	private IListaReproduccion iL;
	private Boolean seleccionoVideo = false;
	private Boolean seleccionoLista = false;
	private String usuarioVideo = "";
	private String nomVid = "";
	private String usuarioLista = "";
	private String nomLista = "";
	
	
	private JLabel lblFaltanSeleccionarDatos = new JLabel("Faltan seleccionar datos");
	private JLabel lblSeHaAgregado = new JLabel("Se ha agregado video");
	
	public AgregarVideoLista(IVideo iV, IUsuario iU, IListaReproduccion iL) {
		this.iV = iV;
		this.iU = iU;
		this.iL = iL;
		setTitle("Agregar video a Lista de Reproduccion");
		setBounds(100, 100, 800, 542);
		getContentPane().setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarVideoLista.this.setVisible(false);
			}
		});
		btnSalir.setBounds(606, 476, 168, 25);
		getContentPane().add(btnSalir);
		
		JScrollPane scrollListaUsr = new JScrollPane();
		scrollListaUsr.setBounds(23, 26, 168, 190);
		getContentPane().add(scrollListaUsr);
		
		listaUsrV  = new JList();
		DefaultListModel<String> listaU = new DefaultListModel<String>();
		listaUsrV.setModel(listaU);
		scrollListaUsr.setViewportView(listaUsrV);
		
		
		btnSelecUsrV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int u = listaUsrV.getSelectedIndex();
				usuarioVideo = listaUsrV.getModel().getElementAt(u).toString();
				((DefaultListModel) listaVid.getModel()).clear();
				List<String> videos = iV.listarVideosDeUsuario(usuarioVideo);
				if (!videos.isEmpty()) {
					for (String v: videos) {
						((DefaultListModel) listaVid.getModel()).addElement(v);
					}
				}
				listaUsrV.setEnabled(false);
				btnSelecUsrV.setEnabled(false);
				
				listaVid.setEnabled(true);
				btnSelecVid.setEnabled(true);
			}
		});
		
		btnSelecUsrV.setBounds(23, 229, 168, 25);
		getContentPane().add(btnSelecUsrV);
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(76, 12, 87, 14);
		getContentPane().add(lblUsuarios);
		
		JScrollPane scrollListaVid = new JScrollPane();
		scrollListaVid.setBounds(201, 26, 168, 190);
		getContentPane().add(scrollListaVid);
		
		
		listaVid.setEnabled(false);
		listaVid.setModel(new DefaultListModel<String>());
		scrollListaVid.setViewportView(listaVid);
		
		
		JLabel lblVideos = new JLabel("Videos");
		lblVideos.setBounds(263, 12, 74, 14);
		getContentPane().add(lblVideos);
		btnSelecVid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaVid.setEnabled(false);
				btnSelecVid.setEnabled(false);
				int v = listaVid.getSelectedIndex();
				nomVid = listaVid.getModel().getElementAt(v).toString();
				seleccionoVideo = true;
				listaUsrV.setEnabled(false);
				btnSelecUsrL.setEnabled(true);			
			}
		});
		
		
		btnSelecVid.setEnabled(false);
		btnSelecVid.setBounds(201, 230, 168, 23);
		getContentPane().add(btnSelecVid);
		
		JLabel label = new JLabel("Usuarios");
		label.setBounds(75, 266, 88, 14);
		getContentPane().add(label);
		
		JScrollPane scrollListaUsrL = new JScrollPane();
		scrollListaUsrL.setBounds(23, 280, 168, 190);
		getContentPane().add(scrollListaUsrL);
		
		scrollListaUsrL.setViewportView(listUsrL);
		
		JScrollPane scrollListaLis = new JScrollPane();
		scrollListaLis.setBounds(201, 280, 168, 190);
		getContentPane().add(scrollListaLis);

		listList.setModel(new DefaultListModel<String>());
		listList.setEnabled(false);
		scrollListaLis.setViewportView(listList);
	
		JLabel lblListasDeReproduccion = new JLabel("Listas de Reproduccion");
		lblListasDeReproduccion.setBounds(203, 265, 183, 14);
		getContentPane().add(lblListasDeReproduccion);
		btnSelecUsrL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listList.setEnabled(true);
				btnSelecLista.setEnabled(true);
				int i = listUsrL.getSelectedIndex();
				usuarioLista = listUsrL.getModel().getElementAt(i).toString();
				((DefaultListModel) listList.getModel()).clear();
				List<String> listas = iL.listarListasDeUsuario(usuarioLista);
				if (!listas.isEmpty()) {
					for (String l: listas) {
						((DefaultListModel) listList.getModel()).addElement(l);
					}
				}
			}
		});
		
		btnSelecUsrL.setBounds(23, 477, 168, 23);
		getContentPane().add(btnSelecUsrL);
		
		btnSelecLista.setEnabled(false);
		btnSelecLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seleccionoLista = true;
				btnSelecUsrL.setEnabled(false);
				btnAgregar.setEnabled(true);
				int i = listList.getSelectedIndex();
				nomLista = listList.getModel().getElementAt(i).toString();
				listUsrL.setEnabled(false);
				btnSelecLista.setEnabled(false);
			}
		});
		btnSelecLista.setBounds(201, 477, 168, 23);
		getContentPane().add(btnSelecLista);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (seleccionoVideo == true && seleccionoLista == true) {
					if (!iL.existeListaParticular(usuarioLista, nomLista)) {
						iL.agregarVideoListaPorDefecto(usuarioVideo, nomVid, nomLista);
					} else {
						iL.agregarVideoListaParticular(usuarioVideo, nomVid, nomLista);
					}
					lblSeHaAgregado.setVisible(true);
				} else {
					lblFaltanSeleccionarDatos.setVisible(true);
				}
				usuarioVideo = "";
				nomVid = "";
				usuarioLista = "";
				nomLista = "";
				((DefaultListModel) listList.getModel()).clear();
				((DefaultListModel) listaVid.getModel()).clear();
				btnSelecUsrV.setEnabled(true);
				btnSelecUsrL.setEnabled(true);
				btnSelecLista.setEnabled(false);
				btnSelecVid.setEnabled(false);
				btnAgregar.setEnabled(false);
				listaUsrV.setEnabled(true);
				listUsrL.setEnabled(true);
			}
		});
		
		btnAgregar.setEnabled(false);
		btnAgregar.setBounds(606, 439, 168, 25);
		getContentPane().add(btnAgregar);
		lblFaltanSeleccionarDatos.setForeground(new Color(255, 0, 0));
		lblFaltanSeleccionarDatos.setBounds(595, 412, 195, 15);
		getContentPane().add(lblFaltanSeleccionarDatos);
		lblSeHaAgregado.setForeground(new Color(124, 252, 0));
		lblSeHaAgregado.setBounds(606, 412, 155, 15);
		getContentPane().add(lblSeHaAgregado);
	}
	
	public void cargarElementos() {
		List<String> usuarios = iU.listarUsuarios();
		DefaultListModel<String> listaU = new DefaultListModel<String>();
		int i = 0;
		for (String u: usuarios) {
			listaU.add(i++, u);
		}
		listaUsrV.setModel(listaU);
		listUsrL.setModel(listaU);
	}
		
	
	public void limpiarLista() {
		((DefaultListModel) listaVid.getModel()).clear();
		((DefaultListModel) listList.getModel()).clear();
	}
	
	public void limpiarForm() {
		lblSeHaAgregado.setVisible(false);
		lblFaltanSeleccionarDatos.setVisible(false);
		usuarioVideo = "";
		nomVid = "";
		usuarioLista = "";
		nomLista = "";
		btnAgregar.setEnabled(false);
	}
	
	public void inicializar() {
		limpiarLista();
		limpiarForm();
		cargarElementos();
	}
}
