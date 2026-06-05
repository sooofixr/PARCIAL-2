import ui.CantanteUI;

import javax.swing.SwingUtilities;

public class MainUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CantanteUI ventana = new CantanteUI();
                ventana.setVisible(true);
            }
        });
    }
}