package app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.Start;
import app.interfaces.observer.Publisher;
import app.interfaces.observer.Subscriber;
import app.models.BaseModel;
import app.operations.CommonMethods;
import app.values.Constants;

public class ButtonLoadActionListener implements ActionListener, Publisher {

    private static final Logger log = LoggerFactory.getLogger(ButtonLoadActionListener.class.getName());

    private List<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void actionPerformed(ActionEvent event) {

        addSubscriber(Start.getSubscriberStart());

        ArrayList<BaseModel> docRepository;

        JFileChooser fileopen = new JFileChooser();
        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();

            try (FileInputStream fis = new FileInputStream(file); ObjectInputStream oin = new ObjectInputStream(fis)) {

                if (!file.exists()) {
                    return;
                }

                docRepository = (ArrayList<BaseModel>) oin.readObject();

                Start.docRepository.addAll(docRepository);

                notifySubscribers();

            } catch (IOException | ClassNotFoundException exception) {
                log.info("Exception: ", exception);
                CommonMethods.showErrorDialog(Constants.ERROR_MESSAGE_PROGRAM, Constants.ERROR_TITLE_PROGRAM);
            }

        }

    }

    @Override
    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    @Override
    public void addSubscriber(Subscriber listener) {
        subscribers.add(listener);
    }

    @Override
    public void removeSubscriber(Subscriber listener) {
        subscribers.remove(listener);
    }

    @Override
    public void removeAllSubscribers() {
        subscribers.clear();
    }

    @Override
    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.doAction();
        }
    }

}
