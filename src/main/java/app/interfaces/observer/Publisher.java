package app.interfaces.observer;

import java.util.List;

public interface Publisher {

    public List<Subscriber> getSubscribers();

    public void addSubscriber(Subscriber listener);

    public void removeSubscriber(Subscriber listener);

    public void removeAllSubscribers();

    public void notifySubscribers();

}
