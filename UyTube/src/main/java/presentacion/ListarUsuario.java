package presentacion;

import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import interfaces.IUsuario;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarUsuario extends JInternalFrame {
	private JList list;

	public ListarUsuario(IUsuario iU) {
		setTitle("Lista de Usuarios");
		setBounds(100, 100, 800, 542);
		getContentPane().setLayout(null);
		ListarUsuario.this.setVisible(false);
		
		list  = new JList();
		list.setBounds(33, 23, 198, 404);
		getContentPane().add(list);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarLista();
				ListarUsuario.this.setVisible(false);
			}
		});
		btnSalir.setBounds(72, 456, 117, 25);
		getContentPane().add(btnSalir);

	}
	
	public void cargarElementos(IUsuario iU) {
		List<String> usuarios = iU.listarUsuarios();
		DefaultListModel<String> listaUsr = new DefaultListModel<String>();
		int i = 0;
		for (String u: usuarios) {
			listaUsr.add(i++, u);
		}
		list.setModel(listaUsr);
	}
	
	public void limpiarLista() {
		DefaultListModel<String> listaUsr = new DefaultListModel<String>();
		list.setModel(listaUsr);
	}
}
