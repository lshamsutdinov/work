package app.gui;

import java.util.Date;

import app.models.Zayavka;
import app.values.Constants;

public class DialogZayavka extends DialogCommonModified {

    private SpecJTextField tfKontragent;
    private SpecJTextField tfKomissiya;

    public DialogZayavka() {
        super(Constants.BTN_LABEL_ZAYAVKA);
    }

    @Override
    protected void initComponents() {

        super.initComponents();

        SpecJLabel lblKontragent = new SpecJLabel(Constants.LBL_KONTRAGENT);
        SpecJLabel lblKomissiya = new SpecJLabel(Constants.LBL_KOMISSIYA);

        tfKontragent = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);
        tfKomissiya = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);

        panelTextFields.add(lblKontragent);
        panelTextFields.add(tfKontragent);
        panelTextFields.add(lblKomissiya);
        panelTextFields.add(tfKomissiya);

    }

    @Override
    public void addDoc() {
        Zayavka zayavka = new Zayavka();
        zayavka.setNumber(tfNumber.getText());
        zayavka.setDate(new Date(dateFromDatePicker));
        zayavka.setUser(tfUser.getText());
        zayavka.setSum(Double.parseDouble(tfSum.getText()));
        zayavka.setValuta(tfValuta.getText());
        zayavka.setKursValuty(Double.parseDouble(tfKursValuty.getText()));
        zayavka.setKontragent(tfKontragent.getText());
        zayavka.setKomissiya(Double.parseDouble(tfKomissiya.getText()));

        this.doc = zayavka;
    }

}
