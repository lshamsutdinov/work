package app.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.JXDatePicker;

import app.interfaces.observer.Publisher;
import app.interfaces.observer.Subscriber;
import app.listeners.ButtonAddDialogCommonActionListener;
import app.models.BaseModel;
import app.values.Constants;

public abstract class DialogCommon extends SpecJDialog implements Publisher {

    protected SimpleDateFormat dateFormat;

    protected BaseModel doc;

    protected SpecJLabel lblNumber;
    protected SpecJLabel lblDate;
    protected SpecJLabel lblUser;
    protected SpecJLabel lblSum;

    protected SpecJTextField tfNumber;
    protected JXDatePicker tfDate;
    protected long dateFromDatePicker;
    protected SpecJTextField tfUser;
    protected SpecJTextField tfSum;

    protected SpecJButton btnAdd;
    protected SpecJButton btnCancel;

    protected SpecJPanel panelTextFields;
    protected SpecJPanel panelButton;

    private List<Subscriber> subscribers = new ArrayList<>();

    public DialogCommon(String title) {
        super(title, Constants.DIALOG_COMMON_WIDTH, Constants.DIALOG_COMMON_HEGHT);

        initComponents();
        addListeners();

        super.setMinimumSize(new Dimension(450, 350));

        dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    }

    protected void initComponents() {

        lblNumber = new SpecJLabel(Constants.LBL_NUMBER);
        lblDate = new SpecJLabel(Constants.LBL_DATE);
        lblUser = new SpecJLabel(Constants.LBL_USER);
        lblSum = new SpecJLabel(Constants.LBL_SUM);

        tfNumber = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);
        tfDate = new JXDatePicker();
        tfUser = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);
        tfSum = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);

        btnAdd = new SpecJButton(Constants.BTN_LABEL_ADD);
        btnCancel = new SpecJButton(Constants.BTN_LABEL_CANCEL);

        panelTextFields = new SpecJPanel("panelTextFields", 450, 300);
        panelTextFields.setMinimumSize(new Dimension(450, 300));
        panelTextFields.setLayout(new GridLayout(8, 2, Constants.PANEL_BTN_GRID_LAYOUT_HORIZONTAL_GAP,
                Constants.PANEL_BTN_GRID_LAYOUT_VERTICAL_GAP));
        panelTextFields.setBorder(new EmptyBorder(Constants.PANEL_EMPTY_BORDER_TOP, Constants.PANEL_EMPTY_BORDER_LEFT,
                Constants.PANEL_EMPTY_BORDER_BOTTOM, Constants.PANEL_EMPTY_BORDER_RIGHT));

        panelButton = new SpecJPanel("panelButton", 450, 50);
        panelButton.setMinimumSize(new Dimension(450, 50));
        panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
        panelButton.setBorder(new EmptyBorder(Constants.PANEL_EMPTY_BORDER_TOP, Constants.PANEL_EMPTY_BORDER_LEFT,
                Constants.PANEL_EMPTY_BORDER_BOTTOM, Constants.PANEL_EMPTY_BORDER_RIGHT));

        panelTextFields.add(lblNumber);
        panelTextFields.add(tfNumber);
        panelTextFields.add(lblDate);
        panelTextFields.add(tfDate);
        panelTextFields.add(lblUser);
        panelTextFields.add(tfUser);
        panelTextFields.add(lblSum);
        panelTextFields.add(tfSum);

        panelButton.add(btnAdd);
        panelButton.add(btnCancel);

        this.getContentPane().add(panelTextFields, BorderLayout.CENTER);
        this.getContentPane().add(panelButton, BorderLayout.SOUTH);

    }

    private void addListeners() {
        ButtonAddDialogCommonActionListener buttonAddActionListener = new ButtonAddDialogCommonActionListener(this);
        btnAdd.addActionListener(buttonAddActionListener);

        btnCancel.addActionListener(event -> dispose());

        tfDate.addActionListener(event -> dateFromDatePicker = tfDate.getDate().getTime());
    }

    public abstract void addDoc();

    public BaseModel getDoc() {
        return doc;
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
