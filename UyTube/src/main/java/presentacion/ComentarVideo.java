package presentacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import datatypes.DtComentario;
import interfaces.IUsuario;
import interfaces.IVideo;
import javax.swing.JTextArea;
import javax.swing.tree.DefaultTreeModel;
import java.awt.Color;
import java.awt.Font;

public class ComentarVideo extends JInternalFrame {
	private JList listaUsrV;
	private JList listaVid = new JList();
	private JButton btnSelecVid = new JButton("Seleccionar");
	private JTree comentarios = new JTree();
	private DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Comentarios");
	private JComboBox<Integer> fDia = new JComboBox<Integer>();
	private JComboBox<Integer> fMes = new JComboBox<Integer>();
	private JComboBox<Integer> fAnio = new JComboBox<Integer>();
	private JButton btnNuevoComentario = new JButton("Nuevo Comentario");
	private JButton btnResponderComentario = new JButton("Responder Comentario");
	private Boolean nuevoComentario = false;
	private JButton btnConfirmar = new JButton("Confirmar");
	private JList listaUsrC = new JList();
	private JTextArea comentario = new JTextArea();
	private JButton btnSeleccionarUC = new JButton("Seleccionar");
	private final JLabel lblMsgError = new JLabel("Faltan completar campos");
	private final JLabel lblMsgExito = new JLabel("Se agrego el comentario");
	private final JLabel lblMsgErrorSelec = new JLabel("Debe seleccionar un comentario");
	JButton btnSeleccionarUsuario = new JButton("Seleccionar");
	
	
	public ComentarVideo(IUsuario iU, IVideo iV) {
		setTitle("Comentar video");
		setBounds(100, 100, 800, 542);
		getContentPane().setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComentarVideo.this.setVisible(false);
				
			}
		});
		btnSalir.setBounds(606, 477, 168, 25);
		getContentPane().add(btnSalir);
		
		JScrollPane scrollListaUsr = new JScrollPane();
		scrollListaUsr.setBounds(23, 26, 168, 190);
		getContentPane().add(scrollListaUsr);
		
		listaUsrV  = new JList();
		DefaultListModel<String> listaU = new DefaultListModel<String>();
		listaUsrV.setModel(listaU);
		scrollListaUsr.setViewportView(listaUsrV);
		
		
		btnSeleccionarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = listaUsrV.getSelectedIndex();
				String usr = listaUsrV.getModel().getElementAt(i).toString();
				((DefaultListModel) listaVid.getModel()).clear();
				List<String> videos = iV.listarVideosDeUsuario(usr);
				if (!videos.isEmpty()) {
					for (String v: videos) {
						((DefaultListModel) listaVid.getModel()).addElement(v);
					}
				}
				listaUsrV.setEnabled(false);
				btnSeleccionarUsuario.setEnabled(false);
				
				listaVid.setEnabled(true);
				btnSelecVid.setEnabled(true);
			}
		});
		
		btnSeleccionarUsuario.setBounds(23, 229, 168, 25);
		getContentPane().add(btnSeleccionarUsuario);
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(76, 12, 58, 14);
		getContentPane().add(lblUsuarios);
		
		JScrollPane scrollListaVid = new JScrollPane();
		scrollListaVid.setBounds(201, 26, 168, 190);
		getContentPane().add(scrollListaVid);
		
		
		listaVid.setEnabled(false);
		listaVid.setModel(new DefaultListModel<String>());
		scrollListaVid.setViewportView(listaVid);
		
		JLabel lblVideos = new JLabel("Videos");
		lblVideos.setBounds(263, 12, 46, 14);
		getContentPane().add(lblVideos);
		btnSelecVid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaVid.setEnabled(false);
				btnSelecVid.setEnabled(false);
				int i = listaVid.getSelectedIndex();
				String vid = listaVid.getModel().getElementAt(i).toString();
				cargarComentarios(iV, vid);
				comentarios.setEnabled(true);
				btnNuevoComentario.setEnabled(true);
				btnResponderComentario.setEnabled(true);
			}
		});
		
		
		btnSelecVid.setEnabled(false);
		btnSelecVid.setBounds(201, 230, 168, 23);
		getContentPane().add(btnSelecVid);
		
		fDia.setEnabled(false);
		
		
		fDia.setBounds(405, 304, 49, 24);
		fDia.addItem(null);
		for (Integer i=1; i<=31; i++) {
			fDia.addItem(i);
		}
		getContentPane().add(fDia);
		fMes.setEnabled(false);
		
		
		fMes.setBounds(466, 304, 52, 24);
		fMes.addItem(null);
		for (Integer i=1; i<=12; i++) {
			fMes.addItem(i);
		}
		getContentPane().add(fMes);
		fAnio.setEnabled(false);
		
		
		fAnio.setBounds(528, 304, 77, 24);
		fAnio.addItem(null);
		for (Integer i=2019; i>=2000; i--) {
			fAnio.addItem(i);
		}
		getContentPane().add(fAnio);

		JScrollPane scrollComentarios = new JScrollPane();
		scrollComentarios.setBounds(405, 26, 349, 190);
		getContentPane().add(scrollComentarios);
		comentarios.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("JTree") {
				{
				}
			}
		));
		comentarios.setEnabled(false);
		scrollComentarios.setViewportView(comentarios);
		btnNuevoComentario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNuevoComentario.setEnabled(false);
				btnResponderComentario.setEnabled(false);
				nuevoComentario = true;
				btnSeleccionarUC.setEnabled(true);
				listaUsrC.setEnabled(true);
						
				
			}
		});
		
		btnNuevoComentario.setEnabled(false);
		btnNuevoComentario.setBounds(442, 230, 121, 23);
		getContentPane().add(btnNuevoComentario);
		btnResponderComentario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetearMensajes();
				if (comentarios.getLastSelectedPathComponent() == null) {
					lblMsgErrorSelec.setVisible(true);
				} else {
					btnNuevoComentario.setEnabled(false);
					btnResponderComentario.setEnabled(false);
					nuevoComentario = false;
					btnNuevoComentario.setEnabled(false);
					btnResponderComentario.setEnabled(false);
					btnSeleccionarUC.setEnabled(true);
					listaUsrC.setEnabled(true);					
				}
				
			}
		});
		

		btnResponderComentario.setEnabled(false);
		btnResponderComentario.setBounds(577, 230, 150, 23);
		getContentPane().add(btnResponderComentario);
		
		JScrollPane scrollUsrC = new JScrollPane();
		scrollUsrC.setBounds(36, 292, 143, 170);
		getContentPane().add(scrollUsrC);
		
		listaUsrC.setEnabled(false);
		listaUsrC.setModel(new DefaultListModel<String>());
		scrollUsrC.setViewportView(listaUsrC);
		
		JLabel lblUsuarioARealizar = new JLabel("Usuario a comentar");
		lblUsuarioARealizar.setBounds(37, 278, 150, 14);
		getContentPane().add(lblUsuarioARealizar);
		btnSeleccionarUC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fDia.setEnabled(true);
				fMes.setEnabled(true);
				fAnio.setEnabled(true);
				comentario.setEnabled(true);
				btnConfirmar.setEnabled(true);
			}
		});
		
		
		btnSeleccionarUC.setEnabled(false);
		btnSeleccionarUC.setBounds(60, 471, 89, 23);
		getContentPane().add(btnSeleccionarUC);
		
		JLabel lblNewLabel = new JLabel("Fecha");
		lblNewLabel.setBounds(311, 309, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblComentario = new JLabel("Comentario");
		lblComentario.setBounds(311, 340, 105, 14);
		getContentPane().add(lblComentario);
		
		comentario.setEnabled(false);
		comentario.setBounds(405, 339, 349, 94);
		getContentPane().add(comentario);
		
		
		btnConfirmar.setEnabled(false);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetearMensajes();
				if (comentario.getText().isEmpty() || fDia.getSelectedIndex() == 0 || fMes.getSelectedIndex() == 0 || fAnio.getSelectedIndex() == 0) {
					lblMsgError.setVisible(true);
				} else {
					int i = listaUsrC.getSelectedIndex();
					String usr = listaUsrC.getModel().getElementAt(i).toString();
					Calendar fPub = Calendar.getInstance();
					fPub.set((Integer) fAnio.getSelectedItem(), (Integer) fMes.getSelectedItem(), (Integer) fDia.getSelectedItem());
					if (nuevoComentario) {
						iV.realizarComentario(usr, fPub, comentario.getText());
					} else {
						DefaultMutableTreeNode com = (DefaultMutableTreeNode) comentarios.getLastSelectedPathComponent();
						int idCom = ((DtComentario) com.getUserObject()).getId();
						iV.responderComentario(idCom, usr, fPub, comentario.getText());
					}
					inicializar(iU);
					lblMsgExito.setVisible(true);
					
				}
			}
		});
		btnConfirmar.setBounds(428, 477, 168, 25);
		getContentPane().add(btnConfirmar);
		
		comentarios.setModel(new DefaultTreeModel(raiz));
		lblMsgError.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMsgError.setForeground(Color.RED);
		lblMsgError.setBounds(528, 452, 178, 14);
		
		getContentPane().add(lblMsgError);
		lblMsgExito.setForeground(new Color(127, 255, 0));
		lblMsgExito.setBounds(528, 452, 178, 14);
		
		getContentPane().add(lblMsgExito);
		lblMsgErrorSelec.setForeground(Color.RED);
		lblMsgErrorSelec.setBounds(473, 264, 254, 14);
		
		getContentPane().add(lblMsgErrorSelec);
		
	}
	
	public void cargarComentarios(IVideo iV, String nomVid) {
		List<DtComentario> listaCom = iV.obtenerComentariosVideo(nomVid);
		for (DtComentario c: listaCom) {
			DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(c);
			cargarRespuestas(nodo, c.getRespuestas());
			raiz.add(nodo);
		}
	}
	
	public void cargarRespuestas(DefaultMutableTreeNode padre, List<DtComentario> com) {
		for (DtComentario c: com) {
			DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(c);
				cargarRespuestas(nodo, c.getRespuestas());	
			padre.add(nodo);
		}

	}
	
	public void cargarElementos(IUsuario iU) {
		List<String> usuarios = iU.listarUsuarios();
		DefaultListModel<String> listaU = new DefaultListModel<String>();
		int i = 0;
		for (String u: usuarios) {
			listaU.add(i++, u);
		}
		listaUsrV.setModel(listaU);
		listaUsrC.setModel(listaU);
	}
	
	public void limpiarLista() {
		((DefaultListModel) listaUsrV.getModel()).clear();
		listaUsrV.setEnabled(true);
		((DefaultListModel) listaVid.getModel()).clear();
		listaVid.setEnabled(false);
		((DefaultListModel) listaUsrC.getModel()).clear();
		listaUsrC.setEnabled(false);
		raiz.removeAllChildren();
		((DefaultTreeModel) comentarios.getModel()).reload();
		comentarios.setEnabled(false);
	}
	
	public void limpiarForm() {
		btnSeleccionarUsuario.setEnabled(true);
		btnSelecVid.setEnabled(false);
		fDia.setSelectedIndex(0);
		fMes.setSelectedIndex(0);
		fAnio.setSelectedIndex(0);
		btnNuevoComentario.setEnabled(false);
		btnResponderComentario.setEnabled(false);
		nuevoComentario = false;
		btnConfirmar.setEnabled(false);
		comentario.setText("");
		btnSeleccionarUC.setEnabled(false);
	}
	
	public void resetearMensajes() {
		lblMsgError.setVisible(false);
		lblMsgExito.setVisible(false);
		lblMsgErrorSelec.setVisible(false);
	}
	
	public void inicializar(IUsuario iU) {
		limpiarLista();
		limpiarForm();
		cargarElementos(iU);
		resetearMensajes();
	}
	
}
