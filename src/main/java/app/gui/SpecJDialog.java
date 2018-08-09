package app.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import app.listeners.EscActionListener;
import app.values.Constants;

public class SpecJDialog extends JDialog {

    public SpecJDialog(String title, int width, int height) {
        super.setTitle(title);
        super.setSize(width, height);
        super.setModal(true);
        super.setLocationRelativeTo(null);
        super.setLayout(new BorderLayout(Constants.PANEL_TEXT_BORDER_LAYOUT_HORIZONTAL_GAP,
                Constants.PANEL_TEXT_BORDER_LAYOUT_VERTICAL_GAP));

        super.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.getRootPane().registerKeyboardAction(new EscActionListener(this),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }

    public SpecJDialog(String title, int width, int height, Component comp) {
        this(title, width, height);
        super.getContentPane().add(comp);
    }

    public SpecJDialog(String title, int width, int height, LayoutManager layout) {
        super.setTitle(title);
        super.setSize(width, height);
        super.setModal(true);
        super.setLocationRelativeTo(null);
        super.setLayout(layout);
        super.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.getRootPane().registerKeyboardAction(new EscActionListener(this),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
    }
}
