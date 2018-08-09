package app.gui;

import app.values.Constants;

public abstract class DialogCommonModified extends DialogCommon {

    protected SpecJLabel lblValuta;
    protected SpecJLabel lblKursValuty;

    protected SpecJTextField tfValuta;
    protected SpecJTextField tfKursValuty;

    public DialogCommonModified(String title) {
        super(title);
    }

    @Override
    protected void initComponents() {

        super.initComponents();

        lblValuta = new SpecJLabel(Constants.LBL_VALUTA);
        lblKursValuty = new SpecJLabel(Constants.LBL_KURS_VALUTY);

        tfValuta = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);
        tfKursValuty = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);

        panelTextFields.add(lblValuta);
        panelTextFields.add(tfValuta);
        panelTextFields.add(lblKursValuty);
        panelTextFields.add(tfKursValuty);

    }

}
