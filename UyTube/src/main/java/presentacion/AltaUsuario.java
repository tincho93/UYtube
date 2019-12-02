package presentacion;

import java.awt.Image;
import javax.swing.*;
import interfaces.ICategoria;
import interfaces.IUsuario;
import javax.imageio.ImageIO;
import java.awt.Color;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import java.util.List;

public class AltaUsuario extends JInternalFrame {
	private JTextField nick;
	private JTextField nombre;
	private JTextField apellido;
	private JTextField email;
	private JTextField nomCanal;
	private JTextField img;
	private Boolean agregarFoto = false;
	private Boolean agregarNomCanal = false;
	private Boolean publico = false;
	private JComboBox<Integer> fDia = new JComboBox<Integer>();
	private JComboBox<Integer> fMes = new JComboBox<Integer>();
	private JComboBox<Integer> fAnio = new JComboBox<Integer>();
	private JLabel lblMsgErrorNick = new JLabel("Ya existe el nickname");
	private JLabel lblMsgErrorEmail = new JLabel("Ya existe el email.");
	private JTextPane desCanal = new JTextPane();
	private JLabel lblMsgError = new JLabel("Error: Falta completar algun campo.");
	private JLabel lblMsgExito = new JLabel("El usuario ha sido ingresado con exito.");
	private JCheckBox chckbxCanalPublico = new JCheckBox("Canal Publico");
	private JCheckBox chckbxPersonalizarNombreDel = new JCheckBox("Personalizar nombre del canal");
	private JCheckBox chckbxInsertarImagenDe = new JCheckBox("Insertar imagen de perfil");
	private final JLabel lblImagen = new JLabel("");
	private JButton btnSelecFoto = new JButton("Seleccionar");
	private final JLabel lblContrasea = new JLabel("Contrase\u00F1a");
	private JPasswordField password;
	private JComboBox categoria = new JComboBox();

	/**
	 * Create the frame.
	 */
	public AltaUsuario(IUsuario iU, ICategoria iC) {
		setTitle("Agregar usuario");
		setBounds(100, 100, 800, 542);
		getContentPane().setLayout(null);
		
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(23, 27, 70, 15);
		getContentPane().add(lblNickname);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(23, 74, 70, 15);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(23, 124, 70, 15);
		getContentPane().add(lblApellido);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(23, 175, 70, 15);
		getContentPane().add(lblEmail);
		
		JLabel lblFechaDeNacimento = new JLabel("Fecha de nacimento");
		lblFechaDeNacimento.setBounds(23, 225, 148, 15);
		getContentPane().add(lblFechaDeNacimento);
		
		nick = new JTextField();
		nick.setBounds(184, 25, 202, 19);
		getContentPane().add(nick);
		nick.setColumns(10);
		
		nombre = new JTextField();
		nombre.setBounds(184, 72, 202, 19);
		getContentPane().add(nombre);
		nombre.setColumns(10);
		
		apellido = new JTextField();
		apellido.setBounds(184, 122, 202, 19);
		getContentPane().add(apellido);
		apellido.setColumns(10);
		
		email = new JTextField();
		email.setBounds(184, 173, 202, 19);
		getContentPane().add(email);
		email.setColumns(10);


		fDia.setBounds(184, 220, 49, 24);
		fDia.addItem(null);
		for (Integer i=1; i<=31; i++) {
			fDia.addItem(i);
		}
		getContentPane().add(fDia);
		
		
		fMes.setBounds(245, 220, 52, 24);
		fMes.addItem(null);
		for (Integer i=1; i<=12; i++) {
			fMes.addItem(i);
		}
		getContentPane().add(fMes);
		
		
		fAnio.setBounds(309, 220, 77, 24);
		fAnio.addItem(null);
		for (Integer i=2019; i>=1900; i--) {
			fAnio.addItem(i);
		}
		getContentPane().add(fAnio);
		
		lblMsgErrorNick.setForeground(Color.RED);
		lblMsgErrorNick.setBounds(184, 45, 190, 15);
		lblMsgErrorNick.setVisible(false);
		getContentPane().add(lblMsgErrorNick);
		
		
		lblMsgErrorEmail.setForeground(Color.RED);
		lblMsgErrorEmail.setBounds(184, 193, 190, 15);
		lblMsgErrorEmail.setVisible(false);
		getContentPane().add(lblMsgErrorEmail);
		
		
		chckbxPersonalizarNombreDel.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				agregarNomCanal = ! agregarNomCanal;
				nomCanal.setEnabled(agregarNomCanal);
			}
		});
		chckbxPersonalizarNombreDel.setBounds(461, 23, 284, 23);
		getContentPane().add(chckbxPersonalizarNombreDel);
		
		JLabel lblNombreDelCanal = new JLabel("Nombre del canal");
		lblNombreDelCanal.setBounds(432, 72, 148, 15);
		getContentPane().add(lblNombreDelCanal);
		
		nomCanal = new JTextField();
		nomCanal.setEnabled(false);
		nomCanal.setBounds(588, 72, 190, 19);
		getContentPane().add(nomCanal);
		nomCanal.setColumns(10);
		
		JLabel lblDescripcionDelCanal = new JLabel("Descripcion del canal");
		lblDescripcionDelCanal.setBounds(432, 124, 200, 15);
		getContentPane().add(lblDescripcionDelCanal);
		
		
		desCanal.setBounds(432, 151, 346, 89);
		getContentPane().add(desCanal);
		
		
		chckbxInsertarImagenDe.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				agregarFoto = ! agregarFoto;
				btnSelecFoto.setEnabled(agregarFoto);
			}
		});
		chckbxInsertarImagenDe.setBounds(67, 310, 284, 15);
		getContentPane().add(chckbxInsertarImagenDe);
		
		img = new JTextField();
		img.setEnabled(false);
		img.setBounds(23, 346, 238, 19);
		getContentPane().add(img);
		img.setColumns(10);
		
		
		lblMsgError.setForeground(Color.RED);
		lblMsgError.setBounds(451, 419, 284, 15);
		lblMsgError.setVisible(false);
		getContentPane().add(lblMsgError);
		
		
		lblMsgExito.setForeground(new Color(127, 255, 0));
		lblMsgExito.setBounds(451, 419, 323, 15);
		lblMsgExito.setVisible(false);
		getContentPane().add(lblMsgExito);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nick.getText().isEmpty() || nombre.getText().isEmpty() || apellido.getText().isEmpty() ||
					email.getText().isEmpty() || fDia.equals(null) || fMes.equals(null) || fAnio.equals(null) 
					|| (img.getText().isEmpty() && agregarFoto) || (nomCanal.getText().isEmpty() && agregarNomCanal) 
					|| desCanal.getText().isEmpty() || password.getText().isEmpty()) {
					borrarMsg();
					lblMsgError.setVisible(true);
				} else if (iU.existeEmail(email.getText())){
					borrarMsg();
					lblMsgErrorEmail.setVisible(true);
				} else if (iU.existeNickname(nick.getText())) {
					borrarMsg();
					lblMsgErrorNick.setVisible(true);
				} else {
					Calendar fNac = Calendar.getInstance();
			        fNac.set((Integer) fAnio.getSelectedItem(), (Integer) fMes.getSelectedItem()-1, (Integer) fDia.getSelectedItem());
			        iU.agregarUsuario(nick.getText(), nombre.getText(), apellido.getText(), fNac, email.getText());
					iU.modificarContrasena(password.getText());
			        if (agregarFoto) {
			        	iU.modificarImagen(img.getText());
			        } else {
			        	iU.modificarImagen("src/main/resources/img/default.png");
			        }
			        iU.agregarCanal();
			        if (agregarNomCanal) {
			        	iU.modificarInfoCanal(nomCanal.getText(), desCanal.getText(), publico);
			        } else {
			        	iU.modificarInfoCanal(nick.getText(), desCanal.getText(), publico);
			        }
					if (categoria.getSelectedIndex() != 0){
						iU.modificarCatCanal(nick.getText(), categoria.getSelectedItem().toString());
					}
			        resetearFormulario(iC);
			        lblMsgExito.setVisible(true);
				}
			}
		});
		btnAgregar.setBounds(451, 463, 117, 25);
		getContentPane().add(btnAgregar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetearFormulario(iC);
				AltaUsuario.this.setVisible(false);
			}
		});
		btnCancelar.setBounds(603, 463, 117, 25);
		getContentPane().add(btnCancelar);
		
		
		chckbxCanalPublico.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				publico = ! publico;
			}
		});
		chckbxCanalPublico.setBounds(490, 266, 177, 23);
		getContentPane().add(chckbxCanalPublico);
		
		
		btnSelecFoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser abrir = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
				FileNameExtensionFilter filter = new FileNameExtensionFilter( "jpeg, jpg, png o bmp", "jpeg", "png", "jpg", "bmp");
				abrir.setFileFilter(filter);
				int r = abrir.showOpenDialog(null); 
				if (r == JFileChooser.APPROVE_OPTION) { 
					img.setText(abrir.getSelectedFile().getAbsolutePath()); 
					
					try {
						String path = abrir.getSelectedFile().getAbsolutePath();
						mostrarImg(path);
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} 
				
			}
		});
		btnSelecFoto.setBounds(270, 346, 117, 19);
		getContentPane().add(btnSelecFoto);
		btnSelecFoto.setEnabled(false);
		
		lblImagen.setBackground(Color.WHITE);
		lblImagen.setBounds(270, 265, 100, 70);
		
		getContentPane().add(lblImagen);
		lblContrasea.setBounds(23, 391, 148, 14);
		
		getContentPane().add(lblContrasea);
		
		password = new JPasswordField();
		password.setBounds(184, 388, 202, 20);
		getContentPane().add(password);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(432, 310, 93, 14);
		getContentPane().add(lblCategoria);
		

		categoria.setBounds(588, 305, 190, 20);
		getContentPane().add(categoria);


		resetearFormulario(iC);
		borrarMsg();

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
		password.setText("");
		agregarFoto = false;
		agregarNomCanal = false;
		publico = false;
		chckbxCanalPublico = new JCheckBox("Canal Publico");
		chckbxCanalPublico.setSelected(false); 
		chckbxPersonalizarNombreDel = new JCheckBox("Personalizar nombre del canal");
		chckbxInsertarImagenDe = new JCheckBox("Insertar imagen de perfil");
		btnSelecFoto.setEnabled(false);
		nomCanal.setEnabled(false);
		lblImagen.setIcon(null);
		cargarCategorias(iC);
		borrarMsg();
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

	public void borrarMsg() {
		lblMsgError.setVisible(false);
		lblMsgExito.setVisible(false);
		lblMsgErrorNick.setVisible(false);
		lblMsgErrorEmail.setVisible(false);
	}
	
	public void mostrarImg(final String filename) throws Exception {
	    SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	                
	        BufferedImage image = null;
	        try {
	          image = ImageIO.read(new File(filename));
	        } catch (Exception e) {
	          e.printStackTrace();
	          System.exit(1);
	        }
	        ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(100, 70, Image.SCALE_FAST));
	        lblImagen.setIcon(imageIcon);
	      }
	    });
	  }
}
