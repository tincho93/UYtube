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
import datatypes.DtVideoUsuario;
import interfaces.IListaReproduccion;
import interfaces.IUsuario;
import interfaces.IVideo;
import javax.swing.SwingConstants;

public class QuitarVideoLista extends JInternalFrame {
	private JList listUsrL = new JList();
	private JList listList = new JList();
	private JList listVid = new JList();
	private JButton btnSelecUsrL = new JButton("Seleccionar");
	private JButton btnSelecLista = new JButton("Seleccionar");
	private JButton btnSelecVid = new JButton("Seleccionar");
	private JButton btnQuitar = new JButton("Quitar Video");
	private JButton btnSalir = new JButton("Salir");
	private JScrollPane scrollListaVid = new JScrollPane();
	private JScrollPane scrollListaUsrL = new JScrollPane();
	private JScrollPane scrollListaLis = new JScrollPane();
	private JLabel lblFaltanSeleccionarDatos = new JLabel("Faltan seleccionar datos");
	private JLabel lblSeHaQuitado = new JLabel("Se ha quitado el video");
	private JLabel lblVideos = new JLabel("Videos");
	private JLabel label = new JLabel("Usuarios");
	private JLabel lblListasDeReproduccion = new JLabel("Listas de Reproduccion");
	private IVideo iV;
	private IUsuario iU;
	private IListaReproduccion iL;
	private Boolean seleccionoVideo = false;
	private Boolean seleccionoLista = false;
	private String nomVid = "";
	private String usuarioLista = "";
	private String nomLista = "";
	
	
	public QuitarVideoLista(IVideo iV, IUsuario iU, IListaReproduccion iL) {
		this.iV = iV;
		this.iU = iU;
		this.iL = iL;
		setTitle("Quitar video de Lista de Reproduccion");
		setBounds(100, 100, 800, 542);
		getContentPane().setLayout(null);
		

		btnSalir.setBounds(606, 476, 168, 25);
		getContentPane().add(btnSalir);
		DefaultListModel<String> listaU = new DefaultListModel<String>();
		

		scrollListaVid.setBounds(379, 103, 168, 297);
		getContentPane().add(scrollListaVid);
		
		
		listVid.setEnabled(false);
		listVid.setModel(new DefaultListModel<String>());
		scrollListaVid.setViewportView(listVid);
		
		lblVideos.setHorizontalAlignment(SwingConstants.CENTER);
		lblVideos.setBounds(379, 74, 168, 14);
		getContentPane().add(lblVideos);		
		
		btnSelecVid.setEnabled(false);
		btnSelecVid.setBounds(379, 420, 168, 23);
		getContentPane().add(btnSelecVid);
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(23, 74, 168, 14);
		getContentPane().add(label);
		
		scrollListaUsrL.setBounds(23, 103, 168, 297);
		getContentPane().add(scrollListaUsrL);
	
		scrollListaUsrL.setViewportView(listUsrL);
		
		scrollListaLis.setBounds(201, 103, 168, 297);
		getContentPane().add(scrollListaLis);

		listList.setModel(new DefaultListModel<String>());
		listList.setEnabled(false);
		scrollListaLis.setViewportView(listList);
	
		
		lblListasDeReproduccion.setHorizontalAlignment(SwingConstants.CENTER);
		lblListasDeReproduccion.setBounds(203, 73, 166, 14);
		getContentPane().add(lblListasDeReproduccion);
		
		
		btnSelecUsrL.setBounds(23, 420, 168, 23);
		getContentPane().add(btnSelecUsrL);
		
		btnSelecLista.setEnabled(false);
		
		btnSelecLista.setBounds(201, 420, 168, 23);
		getContentPane().add(btnSelecLista);
		
		btnQuitar.setEnabled(false);
		btnQuitar.setBounds(606, 439, 168, 25);
		getContentPane().add(btnQuitar);
		lblFaltanSeleccionarDatos.setForeground(new Color(255, 0, 0));
		lblFaltanSeleccionarDatos.setBounds(595, 412, 195, 15);
		getContentPane().add(lblFaltanSeleccionarDatos);
		lblSeHaQuitado.setForeground(new Color(124, 252, 0));
		lblSeHaQuitado.setBounds(606, 412, 155, 15);
		getContentPane().add(lblSeHaQuitado);
		
		//BOTON SELECCIONAR USUARIO/////////////////////////////
		btnSelecUsrL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblFaltanSeleccionarDatos.setVisible(false);
				int i = listUsrL.getSelectedIndex();
				if (i >= 0) {
					listList.setEnabled(true);
					usuarioLista = listUsrL.getModel().getElementAt(i).toString();
					((DefaultListModel) listList.getModel()).clear();
					List<String> listas = iL.listarListasDeUsuario(usuarioLista);
					if (!listas.isEmpty()) {
						btnSelecLista.setEnabled(true);
						for (String l: listas) {
							((DefaultListModel) listList.getModel()).addElement(l);
						}
					}
				} else {
					lblFaltanSeleccionarDatos.setVisible(true);
				}
			}
		});
		
		//BOTON SELECCIONAR LISTA///////////////////////////////
		btnSelecLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblFaltanSeleccionarDatos.setVisible(false);
				int i = listList.getSelectedIndex();
				if (i >= 0) {
					seleccionoLista = true;
					listVid.setEnabled(true);
					nomLista = listList.getModel().getElementAt(i).toString();
					((DefaultListModel) listVid.getModel()).clear();
					List<DtVideoUsuario> videos = iL.listarVideosdeLista(nomLista);
					if (!videos.isEmpty()) {
						btnSelecVid.setEnabled(true);
						for (DtVideoUsuario v: videos) {
							String dataVid = v.getNombreE() + " - " + v.getNickname();
							((DefaultListModel) listVid.getModel()).addElement(dataVid);
						}
					}
				} else {
					lblFaltanSeleccionarDatos.setVisible(true);
				}
			}
		});
		
		//BOTON SELECCIONAR VIDEO///////////////////////////////
		btnSelecVid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				int i = listVid.getSelectedIndex();
				if (i >= 0) {
					seleccionoVideo = true;
					btnQuitar.setEnabled(true);
					nomVid = listVid.getModel().getElementAt(i).toString();
				} else {
					lblFaltanSeleccionarDatos.setVisible(true);
				}
			}
		});
		
		//BOTON QUITAR VIDEO DE LISTA///////////////////////////////
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (seleccionoVideo == true && seleccionoLista == true) {
					String[] tokens = nomVid.split(" - ");
					int i = 0;
					String datos[] = new String[2];
					for (String t : tokens) {
						datos[i] = t;
						i++;
					}
					
					iL.eliminarVideoDeLista(datos[1], datos[0], nomLista);
					lblSeHaQuitado.setVisible(true);
				} else {
					lblFaltanSeleccionarDatos.setVisible(true);
				}
				nomVid = "";
				usuarioLista = "";
				nomLista = "";
				((DefaultListModel) listList.getModel()).clear();
				((DefaultListModel) listVid.getModel()).clear();
				btnSelecUsrL.setEnabled(true);
				btnSelecLista.setEnabled(false);
				btnSelecVid.setEnabled(false);
				btnQuitar.setEnabled(false);
				listUsrL.setEnabled(true);
			}
		});
		
		//BOTON SALIR//////////////////////////////////
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarLista();
				QuitarVideoLista.this.setVisible(false);
			}
		});
	}
	
	
	
	public void cargarElementos() {
		List<String> usuarios = iU.listarUsuarios();
		DefaultListModel<String> listaU = new DefaultListModel<String>();
		int i = 0;
		for (String u: usuarios) {
			listaU.add(i++, u);
		}
		listUsrL.setModel(listaU);
	}
		
	
	public void limpiarLista() {
		((DefaultListModel) listVid.getModel()).clear();
		((DefaultListModel) listList.getModel()).clear();
	}
	
	public void limpiarForm() {
		lblSeHaQuitado.setVisible(false);
		lblFaltanSeleccionarDatos.setVisible(false);
		nomVid = "";
		usuarioLista = "";
		nomLista = "";
		btnSelecLista.setEnabled(false);
		btnSelecVid.setEnabled(false);
		btnQuitar.setEnabled(false);
	}
	
	public void inicializar() {
		limpiarLista();
		limpiarForm();
		cargarElementos();
	}
}
