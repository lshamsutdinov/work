package app.operations;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.Start;
import app.gui.DialogCommon;
import app.gui.DialogView;
import app.models.BaseModel;
import app.models.Nakladnaya;
import app.models.Platezhka;
import app.models.Zayavka;
import app.values.Constants;

public class CommonMethods {

    private static Logger log = LoggerFactory.getLogger(CommonMethods.class.getName());

    private CommonMethods() {
        throw new IllegalAccessError("Utility class");
    }

    public static void showErrorDialog(String msg, String title, Exception exception) {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
        log.info("Exception: ", exception);
    }

    public static void showErrorDialog(String msg, String title) {
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void dialogDocActionPerformed(Class clazz) {
        try {

            DialogCommon dialogCommon = (DialogCommon) clazz.newInstance();
            dialogCommon.addSubscriber(Start.getSubscriberStart());
            dialogCommon.setVisible(true);

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                | SecurityException exception) {
            log.info("Exception: ", exception);
        }
    }

    public static void dialogViewActionPerformed(JTable table) {
        if (Start.docRepository.isEmpty()) {
            CommonMethods.showErrorDialog("Таблица не заполнена. Просматривать нечего!", Constants.ERROR_TITLE_PROGRAM);
            return;
        }

        if (table.getSelectedRow() == -1) {
            CommonMethods.showErrorDialog("Документ не выбран!", Constants.ERROR_TITLE_PROGRAM);
            return;
        }

        BaseModel doc = Start.docRepository.get(table.getSelectedRow());

        StringBuilder sb = new StringBuilder();

        if (doc instanceof Nakladnaya) {
            sb.append(Constants.NAKLADNAYA);
        } else if (doc instanceof Platezhka) {
            sb.append(Constants.PLATEZHKA);
        } else if (doc instanceof Zayavka) {
            sb.append(Constants.ZAYAVKA);
        }

        DialogView dialogView = new DialogView(sb.toString(), 350, 350, doc);
        dialogView.setVisible(true);
    }

}
