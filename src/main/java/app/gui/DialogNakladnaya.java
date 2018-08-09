package app.gui;

import java.util.Date;

import app.models.Nakladnaya;
import app.values.Constants;

public class DialogNakladnaya extends DialogCommonModified {

    private SpecJTextField tfTovar;
    private SpecJTextField tfKolichestvo;

    public DialogNakladnaya() {
        super(Constants.BTN_LABEL_NAKLADNAYA);
    }

    @Override
    protected void initComponents() {

        super.initComponents();

        SpecJLabel lblTovar = new SpecJLabel(Constants.LBL_TOVAR);
        SpecJLabel lblKolichestvo = new SpecJLabel(Constants.LBL_KOLICHESTVO);

        tfTovar = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);
        tfKolichestvo = new SpecJTextField(Constants.TEXT_FIELD_COLUMNS);

        panelTextFields.add(lblTovar);
        panelTextFields.add(tfTovar);
        panelTextFields.add(lblKolichestvo);
        panelTextFields.add(tfKolichestvo);

    }

    @Override
    public void addDoc() {
        Nakladnaya nakladnaya = new Nakladnaya();
        nakladnaya.setNumber(tfNumber.getText());
        nakladnaya.setDate(new Date(dateFromDatePicker));
        nakladnaya.setUser(tfUser.getText());
        nakladnaya.setSum(Double.parseDouble(tfSum.getText()));
        nakladnaya.setValuta(tfValuta.getText());
        nakladnaya.setKursValuty(Double.parseDouble(tfKursValuty.getText()));
        nakladnaya.setTovar(tfTovar.getText());
        nakladnaya.setKolichestvo(Double.parseDouble(tfKolichestvo.getText()));

        this.doc = nakladnaya;
    }

}
