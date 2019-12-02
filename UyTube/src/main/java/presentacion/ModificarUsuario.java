package presentacion;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import datatypes.DtCanal;
import datatypes.DtUsuario;
import interfaces.*;
import java.awt.Color;

public class ModificarUsuario extends JInternalFrame {
	private JList listaUsr;
	private JTextField nick;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField email;
	private JTextField nomCanal;
	private Boolean agregarFoto = false;
	private Boolean agregarNomCanal = false;
	private Boolean publico = false;
	private JComboBox<Integer> fDia = new JComboBox<Integer>();
	private JComboBox<Integer> fMes = new JComboBox<Integer>();
	private JComboBox<Integer> fAnio = new JComboBox<Integer>();
	private JTextPane desCanal = new JTextPane();
	private JCheckBox chckbxCanalPublico = new JCheckBox("");
	private final JLabel lblImagen = new JLabel("");
	private final JScrollPane scrollDescCanal = new JScrollPane();
	private JLabel img = new JLabel("");
	private JList listaVid = new JList();
	private JList listaLisRep = new JList();
	private JButton btnSeleccionarUsuario = new JButton("Seleccionar");
	private DefaultListModel<String> listaV = new DefaultListModel<String>();
	private DefaultListModel<String> listaL = new DefaultListModel<String>();
	private JButton btnSelecVideo = new JButton("Seleccionar");
	private JButton btnSelecLista = new JButton("Seleccionar");
	private JButton btnConfirmar = new JButton("Modificar");
	private JButton btnModImg = new JButton("Modificar");
	private JButton btnBorrarImg = new JButton("Borrar");
	private String imgPath = "";
	private JLabel lblMsgError = new JLabel("Falta completar algun campo");
	private JLabel lblMsgExito = new JLabel("El usuario fue modificado correctamente");
	private JLabel lblMsgAdvert = new JLabel("<html>Advertencia: si selecciona un video o lista se perdera cualquier modificacion en el usuario. <br/>Presione nuevamente 'Seleccionar' si quiere continuar de todas formas. </html>");
	private boolean insiste = false;
	
	private ModificarVideo mvIF;
	private ModificarLista mlIF; 
	private final JLabel lblCategoria = new JLabel("Categoria");
	private final JComboBox categoria = new JComboBox();
	
	public ModificarUsuario(IUsuario iU, IVideo iV, IListaReproduccion iL, ModificarVideo mvIF ,  ModificarLista mlIF) {
		CFactory f = CFactory.getInstancia();
		ICategoria iC = f.getICategoria();
		
		this.mvIF = mvIF;
		this.mlIF = mlIF; 
		
		setRootPaneCheckingEnabled(false);
		setTitle("Modificar usuario");
		setBounds(100, 100, 800, 542);
		getContentPane().setLayout(null);
		ModificarUsuario.this.setVisible(false);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarLista();
				ModificarUsuario.this.setVisible(false);
			}
		});
		btnSalir.setBounds(586, 473, 168, 25);
		getContentPane().add(btnSalir);
		

		
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(391, 51, 70, 15);
		getContentPane().add(lblNickname);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(391, 74, 70, 15);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(391, 97, 70, 15);
		getContentPane().add(lblApellido);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(391, 120, 70, 15);
		getContentPane().add(lblEmail);
		
		JLabel lblFechaDeNacimento = new JLabel("Fecha de nacimento");
		lblFechaDeNacimento.setBounds(391, 147, 148, 15);
		getContentPane().add(lblFechaDeNacimento);
		
		nick = new JTextField();
		nick.setEnabled(false);
		nick.setBounds(552, 49, 202, 19);
		getContentPane().add(nick);
		nick.setColumns(10);
		
		nombre = new JTextField();
		nombre.setEnabled(false);
		nombre.setBounds(552, 72, 202, 19);
		getContentPane().add(nombre);
		nombre.setColumns(10);
		
		apellido = new JTextField();
		apellido.setEnabled(false);
		apellido.setBounds(552, 95, 202, 19);
		getContentPane().add(apellido);
		apellido.setColumns(10);
		
		email = new JTextField();
		email.setEnabled(false);
		email.setBounds(552, 118, 202, 19);
		getContentPane().add(email);
		email.setColumns(10);
		fDia.setEnabled(false);


		fDia.setBounds(552, 142, 49, 24);
		fDia.addItem(null);
		for (Integer i=1; i<=31; i++) {
			fDia.addItem(i);
		}
		getContentPane().add(fDia);
		fMes.setEnabled(false);
		
		
		fMes.setBounds(613, 142, 52, 24);
		fMes.addItem(null);
		for (Integer i=1; i<=12; i++) {
			fMes.addItem(i);
		}
		getContentPane().add(fMes);
		fAnio.setEnabled(false);
		
		
		fAnio.setBounds(677, 142, 77, 24);
		fAnio.addItem(null);
		for (Integer i=1920; i<=2019; i++) {
			fAnio.addItem(i);
		}
		getContentPane().add(fAnio);
		
		JLabel lblNombreDelCanal = new JLabel("Nombre");
		lblNombreDelCanal.setBounds(391, 211, 148, 15);
		getContentPane().add(lblNombreDelCanal);
		
		nomCanal = new JTextField();
		nomCanal.setEnabled(false);
		nomCanal.setBounds(552, 208, 207, 19);
		getContentPane().add(nomCanal);
		nomCanal.setColumns(10);
		
		JLabel lblDescripcionDelCanal = new JLabel("Descripcion");
		lblDescripcionDelCanal.setBounds(391, 292, 200, 15);
		getContentPane().add(lblDescripcionDelCanal);
		chckbxCanalPublico.setEnabled(false);
		
		chckbxCanalPublico.setBounds(725, 238, 29, 23);
		getContentPane().add(chckbxCanalPublico);
		scrollDescCanal.setBounds(391, 309, 363, 57);
		
		getContentPane().add(scrollDescCanal);
		desCanal.setEnabled(false);
		scrollDescCanal.setViewportView(desCanal);
		
		JLabel lblInfoCanal = new JLabel("Informacion del Canal");
		lblInfoCanal.setFont(new Font("Dialog", Font.BOLD, 14));
		lblInfoCanal.setBounds(391, 177, 202, 27);
		getContentPane().add(lblInfoCanal);
		
		JLabel lblInfoUsuario = new JLabel("Informacion del Usuario");
		lblInfoUsuario.setFont(new Font("Dialog", Font.BOLD, 14));
		lblInfoUsuario.setBounds(391, 12, 202, 27);
		getContentPane().add(lblInfoUsuario);
		
		JLabel lblPublico = new JLabel("Publico");
		lblPublico.setBounds(391, 237, 70, 15);
		getContentPane().add(lblPublico);
		
		
		btnSeleccionarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				iU.limpiarControlador();
				resetearFormulario(iC);
				int i = listaUsr.getSelectedIndex();
				String usr = listaUsr.getModel().getElementAt(i).toString();
				DtUsuario infoU = iU.obtenerInfoUsuario(usr);
				DtCanal infoC =iU.obtenerInfoCanal();
				
				//INFO USUARIO
				nick.setText(infoU.getNickname());
				nombre.setText(infoU.getNombre());
				apellido.setText(infoU.getApellido());
				email.setText(infoU.getCorreoE());
				fDia.setSelectedItem(infoU.getfNac().get(Calendar.DAY_OF_MONTH));
				fMes.setSelectedItem(infoU.getfNac().get(Calendar.MONTH)+1);
				fAnio.setSelectedItem(infoU.getfNac().get(Calendar.YEAR));
				imgPath = infoU.getImagen();
				
				//INFO CANAL
				nomCanal.setText(infoC.getNombre());
				desCanal.setText(infoC.getDescripcion());
				chckbxCanalPublico.setSelected(infoC.getPublico());
				publico = infoC.getPublico();
				if (infoC.getCategoria() != null){
					categoria.setSelectedItem(infoC.getCategoria());
				}
				
				//VIDEOS
				List<String> videos = iV.listarVideosDeUsuario(usr);
				if (!videos.isEmpty()) {
					for (String v: videos) {
						((DefaultListModel) listaVid.getModel()).addElement(v);
					}
				}
				
				//LISTAS DE REPRODUCCION
				List<String> listasRep = iL.listarListasParticulares(usr);
				if (!listasRep.isEmpty()) {
					for (String lP: listasRep) {
						((DefaultListModel) listaLisRep.getModel()).addElement(lP);
					}
				}
				habilitarForm(true);
			}
		});
		btnSeleccionarUsuario.setBounds(23, 209, 168, 25);
		getContentPane().add(btnSeleccionarUsuario);
		
		DefaultListModel<String> listaS1 = new DefaultListModel<String>();
		
		DefaultListModel<String> listaS2 = new DefaultListModel<String>();

		getContentPane().add(img);
		
		JScrollPane scrollVid = new JScrollPane();
		scrollVid.setBounds(23, 276, 138, 187);
		getContentPane().add(scrollVid);
		
		listaVid.setModel(listaV);
		scrollVid.setViewportView(listaVid);
		
		JScrollPane scrollLisRep = new JScrollPane();
		scrollLisRep.setBounds(188, 277, 138, 187);
		getContentPane().add(scrollLisRep);
		
		
		listaLisRep.setModel(listaL);
		scrollLisRep.setViewportView(listaLisRep);
		
		JLabel lblVideos = new JLabel("Videos");
		lblVideos.setBounds(70, 261, 49, 15);
		getContentPane().add(lblVideos);
		
		JLabel lblListasDeReproduccion = new JLabel("Listas");
		lblListasDeReproduccion.setBounds(241, 261, 39, 15);
		getContentPane().add(lblListasDeReproduccion);
		
		JScrollPane scrollListaUsr = new JScrollPane();
		scrollListaUsr.setBounds(23, 17, 168, 179);
		getContentPane().add(scrollListaUsr);
		
		listaUsr  = new JList();
		listaUsr.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
			}
		});
		scrollListaUsr.setViewportView(listaUsr);
		
		btnSelecVideo.setEnabled(false);
		btnSelecVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!insiste) {
					lblMsgAdvert.setVisible(true);
					insiste = true;
				} else {
					int i = listaUsr.getSelectedIndex();
					String usr = listaUsr.getModel().getElementAt(i).toString();
					i = listaVid.getSelectedIndex();
					String vid = listaVid.getModel().getElementAt(i).toString();  
					mvIF.inicializar();
					mvIF.cargarVideo(usr, vid);
					ModificarUsuario.this.setVisible(false);
					mvIF.setVisible(true);					
				}
				
			}
		});
		btnSelecVideo.setBounds(23, 475, 138, 23);
		getContentPane().add(btnSelecVideo);
		
		btnSelecLista.setEnabled(false);
		btnSelecLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!insiste) {
					lblMsgAdvert.setVisible(true);
					insiste = true;
				} else {
					int i = listaUsr.getSelectedIndex();
					String usr = listaUsr.getModel().getElementAt(i).toString();
					i = listaLisRep.getSelectedIndex();
					String lis = listaLisRep.getModel().getElementAt(i).toString();
					mlIF.inicializar();
					mlIF.cargarLista(usr, lis);
					ModificarUsuario.this.setVisible(false);
					mlIF.setVisible(true);					
				}
			}
		});
		btnSelecLista.setBounds(188, 475, 138, 23);
		getContentPane().add(btnSelecLista);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarMsg();
				if (nick.getText().isEmpty() || nombre.getText().isEmpty() || apellido.getText().isEmpty() ||
						email.getText().isEmpty() || fDia.equals(null) || fMes.equals(null) || fAnio.equals(null) 
						|| nomCanal.getText().isEmpty()	|| desCanal.getText().isEmpty()) {
						lblMsgError.setVisible(true);
					} else {
						Calendar fNac = Calendar.getInstance();
				        fNac.set((Integer) fAnio.getSelectedItem(), (Integer) fMes.getSelectedItem()-1, (Integer) fDia.getSelectedItem());
				        iU.modificarInfoUsuario(nombre.getText(), apellido.getText(), fNac, imgPath);
				        iU.modificarInfoCanal(nomCanal.getText(), desCanal.getText(), chckbxCanalPublico.isSelected());
				        String nomCat = null;
				        if (categoria.getSelectedIndex() != 0){
							nomCat = categoria.getSelectedItem().toString();
						}
						iU.modificarCatCanal(nick.getText(), nomCat);
				        inicializar(iU, iC);
				        lblMsgExito.setVisible(true);
					}
			}
		});
		
		btnConfirmar.setEnabled(false);
		btnConfirmar.setBounds(408, 473, 168, 25);
		getContentPane().add(btnConfirmar);
		btnModImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser abrir = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
				FileNameExtensionFilter filter = new FileNameExtensionFilter( "jpeg, jpg, png o bmp", "jpeg", "png", "jpg", "bmp");
				abrir.setFileFilter(filter);
				int r = abrir.showOpenDialog(null); 
				if (r == JFileChooser.APPROVE_OPTION) { 
					img.setText(abrir.getSelectedFile().getAbsolutePath()); 
					
					try {
						imgPath = abrir.getSelectedFile().getAbsolutePath();
						mostrarImg(imgPath);
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnModImg.setBounds(230, 147, 120, 23);
		getContentPane().add(btnModImg);
		btnBorrarImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imgPath = "src/main/resources/img/default.png";
				try {
					mostrarImg(imgPath);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		
		btnBorrarImg.setBounds(230, 176, 120, 23);
		getContentPane().add(btnBorrarImg);
		
		listaUsr.setModel(new DefaultListModel<>());
		
		lblMsgError.setForeground(Color.RED);
		lblMsgError.setBounds(489, 435, 176, 14);
		getContentPane().add(lblMsgError);
		
		lblMsgExito.setForeground(new Color(124, 252, 0));
		lblMsgExito.setBounds(472, 435, 232, 14);
		getContentPane().add(lblMsgExito);
		

		lblMsgAdvert.setForeground(new Color(0, 0, 255));
		lblMsgAdvert.setBounds(391, 383, 363, 66);
		getContentPane().add(lblMsgAdvert);
		lblCategoria.setBounds(391, 267, 120, 14);
		
		getContentPane().add(lblCategoria);
		categoria.setBounds(552, 268, 207, 20);
		categoria.setEnabled(false);
		
		getContentPane().add(categoria);
	}
	
	public void cargarElementos(IUsuario iU) {
		List<String> usuarios = iU.listarUsuarios();
		DefaultListModel<String> listaU = new DefaultListModel<String>();
		int i = 0;
		for (String u: usuarios) {
			listaU.add(i++, u);
		}
		listaUsr.setModel(listaU);
	}
	
	public void limpiarLista() {
		((DefaultListModel) listaUsr.getModel()).clear();
	}

	public void cargarCategorias(ICategoria iC) {
		List<String> categorias = iC.listarCategorias();
		categoria.removeAllItems();
		categoria.addItem("<Sin categoria>");
		for (String c: categorias) {
			categoria.addItem(c);
		}
		categoria.setSelectedIndex(0);
	}

	public void resetearFormulario(ICategoria iC) {
		nick.setText("");
		nombre.setText("");
		apellido.setText("");
		email.setText("");
		nomCanal.setText("");
		img.setText("");
		fDia.setSelectedIndex(0);
		fMes.setSelectedIndex(0);
		fAnio.setSelectedIndex(0);
		desCanal.setText("");
		agregarFoto = false;
		agregarNomCanal = false;
		publico = false;
		imgPath = "";
		chckbxCanalPublico.setSelected(false);
		nomCanal.setEnabled(false);
		lblImagen.setIcon(null);
		((DefaultListModel) listaVid.getModel()).clear();
		((DefaultListModel) listaLisRep.getModel()).clear();
		insiste = false;
		cargarCategorias(iC);
	}
	
	public void inicializar(IUsuario iU, ICategoria iC) {
		borrarMsg();
		habilitarForm(false);
		limpiarLista();
		resetearFormulario(iC);
		cargarElementos(iU);
	}
	
	private void habilitarForm(boolean flag) {
		listaUsr.setEnabled(!flag);
		nombre.setEnabled(flag);
		apellido.setEnabled(flag);
		nomCanal.setEnabled(flag);
		fDia.setEnabled(flag);
		fMes.setEnabled(flag);
		fAnio.setEnabled(flag);
		desCanal.setEnabled(flag);
		chckbxCanalPublico.setEnabled(flag);
		listaVid.setEnabled(flag);
		listaLisRep.setEnabled(flag);
		btnSeleccionarUsuario.setEnabled(!flag);
		btnSelecVideo.setEnabled(flag);
		btnSelecLista.setEnabled(flag);
		btnConfirmar.setEnabled(flag);
		btnModImg.setEnabled(flag);
		btnBorrarImg.setEnabled(flag);
		categoria.setEnabled(flag);
	}
	
	private void borrarMsg() {
		lblMsgError.setVisible(false);
		lblMsgExito.setVisible(false);
		lblMsgAdvert.setVisible(false);
	}
	
	public void mostrarImg(final String filename) throws Exception {
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	                
	        BufferedImage image = null;
	        try {
	          image = ImageIO.read(new File(filename));
	        }
	        catch (Exception e) {
	          e.printStackTrace();
	          System.exit(1);
	        }
	        ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(120, 120, Image.SCALE_FAST));
	        img.setIcon(imageIcon);
	      }
	    });
	  }
}