package app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.Start;
import app.operations.CommonMethods;
import app.values.Constants;

public class ButtonSaveActionListener implements ActionListener {

    private static final Logger log = LoggerFactory.getLogger(ButtonSaveActionListener.class.getName());

    @Override
    public void actionPerformed(ActionEvent event) {

        if (Start.docRepository.isEmpty()) {
            CommonMethods.showErrorDialog("Данных нет. Сохранять нечего!", "Ошибка");
            return;
        }

        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Сохранить файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();

            try (FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos)) {

                if (!file.exists() && !file.createNewFile()) {
                    throw new IOException("Can't create new file.");
                }

                oos.writeObject(Start.docRepository);
                oos.flush();

            } catch (IOException exception) {
                log.info("Exception: ", exception);
                CommonMethods.showErrorDialog(Constants.ERROR_MESSAGE_FILE, Constants.ERROR_TITLE_PROGRAM);
            }

        }

    }

}
