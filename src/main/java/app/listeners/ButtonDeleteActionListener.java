package app.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import app.Start;
import app.interfaces.observer.Publisher;
import app.interfaces.observer.Subscriber;
import app.models.BaseModel;

public class ButtonDeleteActionListener implements ActionListener, Publisher {

    private List<Subscriber> subscribers = new ArrayList<>();

    @Override
    public void actionPerformed(ActionEvent event) {
        addSubscriber(Start.getSubscriberStart());

        List<BaseModel> newRepository = new ArrayList<>();
        newRepository.clear();

        for (BaseModel doc : Start.docRepository) {

            if (!doc.isForDel()) {
                newRepository.add(doc);
            }
        }

        Start.docRepository.clear();
        Start.docRepository.addAll(newRepository);

        notifySubscribers();
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
