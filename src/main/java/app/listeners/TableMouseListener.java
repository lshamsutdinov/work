package app.listeners;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import app.operations.CommonMethods;

public class TableMouseListener extends MouseAdapter {

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        JTable table = (JTable) mouseEvent.getSource();

        Point point = mouseEvent.getPoint();

        int row = table.rowAtPoint(point);

        if (mouseEvent.getClickCount() == 2) {
            CommonMethods.dialogViewActionPerformed(table);
        }
    }

}
