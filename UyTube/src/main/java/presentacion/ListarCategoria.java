package presentacion;

import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import interfaces.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarCategoria extends JInternalFrame {
	private JList list;

	public ListarCategoria(ICategoria iC) {
		setTitle("Listado de categorias");
		setBounds(100, 100, 800, 542);
		getContentPane().setLayout(null);
		list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(12, 10, 400, 451);
		getContentPane().add(list);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarCategoria.this.setVisible(false);
				limpiarLista();
			}
			
		});
		btnSalir.setBounds(26, 473, 117, 25);
		getContentPane().add(btnSalir);
		this.setVisible(false);

	}
	
	public void cargarElementos(ICategoria iC) {
		List<String> categorias = iC.listarCategorias();
		DefaultListModel<String> listaCat = new DefaultListModel<String>();
		int i = 0;
		for (String c: categorias) {
			listaCat.add(i++, c);
		}
		list.setModel(listaCat);
	}
	
	public void limpiarLista() {
		DefaultListModel<String> listaCat = new DefaultListModel<String>();
		list.setModel(listaCat);
	}
}

