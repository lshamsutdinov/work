package app.gui;

import java.util.Date;

import app.models.Platezhka;
import app.values.Constants;

public class DialogPlatezhka extends DialogCommon {

    private SpecJTextField tfSotrudink;

    public DialogPlatezhka() {
        super(Constants.BTN_LABEL_PLATEZHKA);
    }

    @Override
    protected void initComponents() {

        super.initComponents();

        SpecJLabel lblSotrudink = new SpecJLabel(Constants.LBL_SOTRUDNIK);

        tfSotrudink = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);

        panelTextFields.add(lblSotrudink);
        panelTextFields.add(tfSotrudink);

    }

    @Override
    public void addDoc() {
        Platezhka platezhka = new Platezhka();
        platezhka.setNumber(tfNumber.getText());
        platezhka.setDate(new Date(dateFromDatePicker));
        platezhka.setUser(tfUser.getText());
        platezhka.setSum(Double.parseDouble(tfSum.getText()));
        platezhka.setSotrudink(tfSotrudink.getText());

        this.doc = platezhka;
    }

}
