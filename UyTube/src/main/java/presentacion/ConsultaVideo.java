package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import datatypes.DtComentario;
import datatypes.DtValoracion;
import datatypes.DtVideo;
import interfaces.ICategoria;
import interfaces.IUsuario;
import interfaces.IVideo;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.JCheckBox;
import javax.swing.tree.DefaultTreeModel;

public class ConsultaVideo extends JInternalFrame {
	private JList listaUsr = new JList();
	private JTextField nomVid = new JTextField();
	private JTextField duracion = new JTextField();
	private JTextField url = new JTextField();
	private JButton btnSeleccionarUsuario = new JButton("Seleccionar");
	private JTextArea descripcion = new JTextArea();
	private JComboBox<Integer> fDia = new JComboBox<Integer>();
	private JComboBox<Integer> fMes = new JComboBox<Integer>();
	private JComboBox<Integer> fAnio = new JComboBox<Integer>();
	private JComboBox categoria = new JComboBox();
	private JList listaVid = new JList();
	private JButton btnSelecVid = new JButton("Seleccionar");
	private JList listaLeGusta = new JList();
	private JList listaNoGusta = new JList();
	private JTree comentarios = new JTree();
	private JCheckBox publico = new JCheckBox("");
	private DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Comentarios");
	private IVideo iV;
	private IUsuario iU;
	private ICategoria iC;


	public ConsultaVideo(IUsuario iU, IVideo iV, ICategoria iC) {
		this.iV = iV;
		this.iU = iU;
		this.iC = iC;
		setTitle("Consultar video");
		setBounds(100, 100, 800, 542);
		getContentPane().setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inicializar();
				((DefaultListModel) listaLeGusta.getModel()).clear();
				listaLeGusta.setEnabled(false);
				((DefaultListModel) listaNoGusta.getModel()).clear();
				listaNoGusta.setEnabled(false);		
				((DefaultListModel) listaVid.getModel()).clear();
				listaVid.setEnabled(false);
				ConsultaVideo.this.setVisible(false);
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
				((DefaultListModel) listaVid.getModel()).clear();
				List<String> videos = iV.listarVideosDeUsuario(usr);
				if (!videos.isEmpty()) {
					for (String v: videos) {
						((DefaultListModel) listaVid.getModel()).addElement(v);
					}
				}
				listaUsr.setEnabled(false);
				btnSeleccionarUsuario.setEnabled(false);
				
				listaVid.setEnabled(true);
				btnSelecVid.setEnabled(true);
			}
		});
		
		btnSeleccionarUsuario.setBounds(23, 229, 168, 25);
		getContentPane().add(btnSeleccionarUsuario);
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(76, 12, 98, 14);
		getContentPane().add(lblUsuarios);
		
		JScrollPane scrollListaVid = new JScrollPane();
		scrollListaVid.setBounds(201, 26, 168, 190);
		getContentPane().add(scrollListaVid);
		
		
		listaVid.setEnabled(false);
		listaVid.setModel(new DefaultListModel<String>());
		scrollListaVid.setViewportView(listaVid);
		
		JLabel lblVideos = new JLabel("Videos");
		lblVideos.setBounds(263, 12, 106, 14);
		getContentPane().add(lblVideos);
		btnSelecVid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaVid.setEnabled(false);
				btnSelecVid.setEnabled(false);
				int i = listaVid.getSelectedIndex();
				String vid = listaVid.getModel().getElementAt(i).toString();
				DtVideo infoV = iV.obtenerInfoVideo(vid);
				nomVid.setText(infoV.getNombre());
				duracion.setText(infoV.getDuracion().toString());
				url.setText(infoV.getUrl());
				descripcion.setText(infoV.getDescripcion());
				fDia.setSelectedItem(infoV.getfPublicacion().get(Calendar.DAY_OF_MONTH));
				fMes.setSelectedItem(infoV.getfPublicacion().get(Calendar.MONTH)+1);
				fAnio.setSelectedItem(infoV.getfPublicacion().get(Calendar.YEAR));
				categoria.setSelectedItem(infoV.getCategoria());
				publico.setSelected(infoV.getPublico());
				cargarComentarios(vid);
				cargarLeGusta(iV, vid);
				cargarNoGusta(iV, vid);
				listaLeGusta.setEnabled(true);
				listaNoGusta.setEnabled(true);
			}
		});
		
		
		btnSelecVid.setEnabled(false);
		btnSelecVid.setBounds(201, 230, 168, 23);
		getContentPane().add(btnSelecVid);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(404, 12, 70, 15);
		getContentPane().add(lblNombre);
		
		nomVid = new JTextField();
		nomVid.setEnabled(false);
		nomVid.setBounds(555, 11, 219, 19);
		getContentPane().add(nomVid);
		nomVid.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion");
		lblDescripcion.setBounds(404, 43, 107, 15);
		getContentPane().add(lblDescripcion);
		descripcion.setEnabled(false);
		
		
		descripcion.setBounds(555, 43, 219, 63);
		getContentPane().add(descripcion);
		
		JLabel lblDuracion = new JLabel("Duracion (segundos)");
		lblDuracion.setBounds(404, 119, 149, 15);
		getContentPane().add(lblDuracion);
		
		duracion = new JTextField();
		duracion.setEnabled(false);
		duracion.setBounds(555, 117, 219, 19);
		getContentPane().add(duracion);
		duracion.setColumns(10);
		fDia.setEnabled(false);
		
		
		fDia.setBounds(572, 175, 49, 24);
		fDia.addItem(null);
		for (Integer i=1; i<=31; i++) {
			fDia.addItem(i);
		}
		getContentPane().add(fDia);
		fMes.setEnabled(false);
		
		
		fMes.setBounds(633, 175, 52, 24);
		fMes.addItem(null);
		for (Integer i=1; i<=12; i++) {
			fMes.addItem(i);
		}
		getContentPane().add(fMes);
		fAnio.setEnabled(false);
		
		
		fAnio.setBounds(697, 175, 77, 24);
		fAnio.addItem(null);
		for (Integer i=1920; i<=2019; i++) {
			fAnio.addItem(i);
		}
		getContentPane().add(fAnio);
		
		JLabel lblUrl = new JLabel("URL");
		lblUrl.setBounds(404, 148, 70, 15);
		getContentPane().add(lblUrl);
		
		url = new JTextField();
		url.setEnabled(false);
		url.setBounds(555, 146, 219, 19);
		getContentPane().add(url);
		url.setColumns(10);
		
		JLabel lblFechaDePublicacion = new JLabel("Fecha de publicacion");
		lblFechaDePublicacion.setBounds(404, 180, 148, 15);
		getContentPane().add(lblFechaDePublicacion);
		categoria.setEnabled(false);
		
		
		categoria.setBounds(555, 210, 219, 24);
		getContentPane().add(categoria);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(404, 215, 70, 15);
		getContentPane().add(lblCategoria);
		
		JScrollPane scrollLeGusta = new JScrollPane();
		scrollLeGusta.setBounds(420, 301, 157, 158);
		getContentPane().add(scrollLeGusta);
		
		
		scrollLeGusta.setViewportView(listaLeGusta);
		
		JScrollPane scrollNoGusta = new JScrollPane();
		scrollNoGusta.setBounds(617, 301, 157, 158);
		getContentPane().add(scrollNoGusta);
		
		
		scrollNoGusta.setViewportView(listaNoGusta);
		
		JLabel lblLesGusta = new JLabel("les gusta");
		lblLesGusta.setBounds(465, 281, 105, 14);
		getContentPane().add(lblLesGusta);
		
		JLabel lblNoLesGusta = new JLabel("no les gusta");
		lblNoLesGusta.setBounds(653, 283, 122, 14);
		getContentPane().add(lblNoLesGusta);
		
		JScrollPane scrollComentarios = new JScrollPane();
		scrollComentarios.setBounds(23, 278, 349, 181);
		getContentPane().add(scrollComentarios);
		
		comentarios.setModel(new DefaultTreeModel(raiz));

		scrollComentarios.setViewportView(comentarios);
		
		JLabel lblVideoPublico = new JLabel("Video publico");
		lblVideoPublico.setBounds(404, 255, 98, 14);
		getContentPane().add(lblVideoPublico);
		
		
		publico.setBounds(556, 252, 27, 23);
		getContentPane().add(publico);
		
		
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
		
	
	public void cargarComentarios(String nomVid) {
		List<DtComentario> listaCom = iV.obtenerComentariosVideo(nomVid);
		for (DtComentario c: listaCom) {
			DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(c);
			cargarRespuestas(nodo, c.getRespuestas());
			raiz.add(nodo);
		}
	}
	
	public void cargarLeGusta(IVideo iV, String nomVid) {
		List<DtValoracion> listaVal = iV.obtenerValoracionVideo();
		DefaultListModel<String> listaU = new DefaultListModel<String>();
		int i = 0;
		for (DtValoracion v: listaVal) {
			if (!listaVal.isEmpty()) {
				if (v.getGusta()) {
					listaU.add(i++, v.getNickname());
				}
			}
		}	
		listaLeGusta.setModel(listaU);
	}
	
	public void cargarNoGusta(IVideo iV, String nomVid) {
		List<DtValoracion> listaVal = iV.obtenerValoracionVideo();
		DefaultListModel<String> listaU = new DefaultListModel<String>();
		int i = 0;
		for (DtValoracion v: listaVal) {
			if (!listaVal.isEmpty()) {
				if (!v.getGusta()) {
					listaU.add(i++, v.getNickname());
				}
			}
		}
		listaNoGusta.setModel(listaU);
	}
	
	public void cargarRespuestas(DefaultMutableTreeNode padre, List<DtComentario> com) {
		for (DtComentario c: com) {
			DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(c);
				cargarRespuestas(nodo, c.getRespuestas());	
			padre.add(nodo);
		}

	}
	
	public void limpiarLista() {
	    raiz.removeAllChildren();
		((DefaultTreeModel) comentarios.getModel()).reload();
		comentarios.setEnabled(true);
	}
	
	public void limpiarForm() {
		btnSeleccionarUsuario.setEnabled(true);
		((DefaultListModel) listaUsr.getModel()).clear();
		listaUsr.setEnabled(true);
		btnSelecVid.setEnabled(false);
		nomVid.setText("");
		duracion.setText("");
		url.setText("");
		descripcion.setText("");
		fDia.setSelectedIndex(-1);
		fMes.setSelectedIndex(-1);
		fAnio.setSelectedIndex(-1);
		categoria.setSelectedIndex(-1);
	}
	
	public void cargarCategorias() {
		List<String> categorias = iC.listarCategorias();
		categoria.addItem("<Sin categoria>");
		for (String c: categorias) {
			categoria.addItem(c);
		}
	}
	
	public void inicializar() {
		cargarCategorias();
		limpiarLista();
		limpiarForm();
		cargarElementos();
	}
	
	public void cargarVideo(String nick, String vid) {
		iV.setUsr(nick);
		listaUsr.setEnabled(false);
		btnSeleccionarUsuario.setEnabled(false);
		listaVid.setEnabled(false);
		btnSelecVid.setEnabled(false);
		DtVideo infoV = iV.obtenerInfoVideo(vid);
		nomVid.setText(infoV.getNombre());
		duracion.setText(infoV.getDuracion().toString());
		url.setText(infoV.getUrl());
		descripcion.setText(infoV.getDescripcion());
		fDia.setSelectedIndex(infoV.getfPublicacion().get(Calendar.DAY_OF_MONTH));
		fMes.setSelectedIndex(infoV.getfPublicacion().get(Calendar.MONTH));
		fAnio.setSelectedItem(infoV.getfPublicacion().get(Calendar.YEAR));
		if (infoV.getCategoria() == null) {
			categoria.setSelectedIndex(0);
		} else {
			categoria.setSelectedItem(infoV.getCategoria());			
		}
		publico.setSelected(infoV.getPublico());
		cargarComentarios(vid);
	}
}
