
package uts.poo.proyecto.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import uts.poo.proyecto.dao.VentaDAO;
import uts.poo.proyecto.vista.FrmListaVentas;

public class ContrListaVentas implements ActionListener {

    private FrmListaVentas vista;
    private VentaDAO dao = new VentaDAO();

    public ContrListaVentas(FrmListaVentas vista) {
        this.vista = vista;
        this.vista.btnCerrar.addActionListener(this);
        cargarTabla();
        this.vista.setVisible(true);
    }

    
    private void cargarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) vista.tblVentas.getModel();
        modelo.setRowCount(0);

        List<String[]> rows = dao.listarVentasResumen();

        for (String[] r : rows) {
            modelo.addRow(r);
        }
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnCerrar) {
            vista.dispose();
        }
    }
}
