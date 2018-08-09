package app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.Start;
import app.gui.DialogCommon;

public class ButtonAddDialogCommonActionListener implements ActionListener {

    private DialogCommon dialogCommon;

    public ButtonAddDialogCommonActionListener(DialogCommon dialogCommon) {
        this.dialogCommon = dialogCommon;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        dialogCommon.addDoc();

        if (dialogCommon.getDoc() != null) {
            Start.docRepository.add(dialogCommon.getDoc());
            dialogCommon.notifySubscribers();
            dialogCommon.dispose();
        }
    }

}
