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
import interfaces.IVideo;

public class ValorarVideo extends JInternalFrame {
	private JList listaUsrVid;
	private JList listaVid = new JList();
	private JButton btnSelecVid = new JButton("Seleccionar");
	private JList listaUsrVal = new JList();
	private JButton btnSeleccionarUVal = new JButton("Seleccionar");
	private final JLabel lblMsgExito = new JLabel("Se valoro el video");
	private JButton btnSelecUVid = new JButton("Seleccionar");
	private JButton btnMeGusta = new JButton("Me gusta");
	private JButton btnNoMeGusta = new JButton("No me gusta");
	
	
	public ValorarVideo(IVideo iV, IUsuario iU) {
			
		setTitle("Comentar video");
		setBounds(100, 100, 800, 542);
		getContentPane().setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ValorarVideo.this.setVisible(false);
			}
		});
		btnSalir.setBounds(606, 477, 168, 25);
		getContentPane().add(btnSalir);
		
		JScrollPane scrollUsrVid = new JScrollPane();
		scrollUsrVid.setBounds(23, 26, 168, 190);
		getContentPane().add(scrollUsrVid);
		
		listaUsrVid  = new JList();
		DefaultListModel<String> listaU = new DefaultListModel<String>();
		listaUsrVid.setModel(listaU);
		scrollUsrVid.setViewportView(listaUsrVid);
		
		
		btnSelecUVid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = listaUsrVid.getSelectedIndex();
				String usr = listaUsrVid.getModel().getElementAt(i).toString();
				((DefaultListModel) listaVid.getModel()).clear();
				List<String> videos = iV.listarVideosDeUsuario(usr);
				if (!videos.isEmpty()) {
					for (String v: videos) {
						((DefaultListModel) listaVid.getModel()).addElement(v);
					}
				}
				listaUsrVid.setEnabled(false);
				btnSelecUVid.setEnabled(false);
				
				listaVid.setEnabled(true);
				btnSelecVid.setEnabled(true);
			}
		});
		
		btnSelecUVid.setBounds(23, 229, 168, 25);
		getContentPane().add(btnSelecUVid);
		
		JLabel lblUsuarios = new JLabel("Usuarios");
		lblUsuarios.setBounds(76, 12, 85, 14);
		getContentPane().add(lblUsuarios);
		
		JScrollPane scrollListaVid = new JScrollPane();
		scrollListaVid.setBounds(201, 26, 168, 190);
		getContentPane().add(scrollListaVid);
		
		
		listaVid.setEnabled(false);
		listaVid.setModel(new DefaultListModel<String>());
		scrollListaVid.setViewportView(listaVid);
		
		JLabel lblVideos = new JLabel("Videos");
		lblVideos.setBounds(263, 12, 85, 14);
		getContentPane().add(lblVideos);
		btnSelecVid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaVid.setEnabled(false);
				btnSelecVid.setEnabled(false);

				listaUsrVal.setEnabled(true);
				btnSeleccionarUVal.setEnabled(true);
			}
		});
		
		
		btnSelecVid.setEnabled(false);
		btnSelecVid.setBounds(201, 230, 168, 23);
		getContentPane().add(btnSelecVid);
		
		
		JScrollPane scrollUsrVal = new JScrollPane();
		scrollUsrVal.setBounds(379, 26, 168, 190);
		getContentPane().add(scrollUsrVal);
		
		listaUsrVal.setEnabled(false);
		listaUsrVal.setModel(new DefaultListModel<String>());
		scrollUsrVal.setViewportView(listaUsrVal);
		
		JLabel lblUsuarioARealizar = new JLabel("Valorador");
		lblUsuarioARealizar.setBounds(428, 12, 85, 14);
		getContentPane().add(lblUsuarioARealizar);
		btnSeleccionarUVal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaUsrVal.setEnabled(false);
				btnSeleccionarUVal.setEnabled(false);
				btnMeGusta.setEnabled(true);
				btnNoMeGusta.setEnabled(true);
			}
		});
		
		
		btnSeleccionarUVal.setEnabled(false);
		btnSeleccionarUVal.setBounds(379, 230, 168, 23);
		getContentPane().add(btnSeleccionarUVal);
		lblMsgExito.setForeground(new Color(127, 255, 0));
		lblMsgExito.setBounds(624, 451, 132, 14);
		
		getContentPane().add(lblMsgExito);
		
		
		btnMeGusta.setEnabled(false);
		btnMeGusta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i = listaVid.getSelectedIndex();
				String vid = listaVid.getModel().getElementAt(i).toString();
				iV.setVid(vid);
				i = listaUsrVal.getSelectedIndex();
				String usrVal = listaUsrVal.getModel().getElementAt(i).toString();
				iV.valorarVideo(usrVal, true);
				inicializar(iU);
				lblMsgExito.setVisible(true);
				
			}
		});
		btnMeGusta.setBounds(585, 98, 168, 23);
		getContentPane().add(btnMeGusta);
		
		btnNoMeGusta.setEnabled(false);
		btnNoMeGusta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = listaVid.getSelectedIndex();
				String vid = listaVid.getModel().getElementAt(i).toString();
				iV.setVid(vid);
				i = listaUsrVal.getSelectedIndex();
				String usrVal = listaUsrVal.getModel().getElementAt(i).toString();
				iV.valorarVideo(usrVal, false);
				inicializar(iU);
				lblMsgExito.setVisible(true);
			}
		});
		btnNoMeGusta.setBounds(585, 138, 168, 23);
		getContentPane().add(btnNoMeGusta);
		
	}


	public void cargarElementos(IUsuario iU) {
		List<String> usuarios = iU.listarUsuarios();
		DefaultListModel<String> listaU = new DefaultListModel<String>();
		int i = 0;
		for (String u: usuarios) {
			listaU.add(i++, u);
		}
		listaUsrVid.setModel(listaU);
		listaUsrVal.setModel(listaU);
	}
	

	public void limpiarLista() {
		((DefaultListModel) listaUsrVid.getModel()).clear();
		listaUsrVid.setEnabled(true);
		((DefaultListModel) listaVid.getModel()).clear();
		listaVid.setEnabled(false);
		((DefaultListModel) listaUsrVal.getModel()).clear();
		listaUsrVal.setEnabled(false);
	}
	
	public void limpiarForm() {
		btnSelecUVid.setEnabled(true);
		btnSelecVid.setEnabled(false);
		btnSeleccionarUVal.setEnabled(false);
		btnMeGusta.setEnabled(false);
		btnNoMeGusta.setEnabled(false);
		
	}
	
	public void resetearMensajes() {
		lblMsgExito.setVisible(false);
	}
	
	public void inicializar(IUsuario iU) {
		limpiarLista();
		limpiarForm();
		cargarElementos(iU);
		resetearMensajes();
	}
}