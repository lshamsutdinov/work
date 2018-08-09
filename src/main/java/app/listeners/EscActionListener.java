package app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class EscActionListener implements ActionListener {

    private JDialog dialog;

    public EscActionListener(JDialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        dialog.dispose();
    }
}
