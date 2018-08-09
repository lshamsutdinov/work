package app.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import app.models.BaseModel;
import app.models.BaseModelModified;
import app.models.Nakladnaya;
import app.models.Platezhka;
import app.models.Zayavka;
import app.values.Constants;

public class DialogView extends SpecJDialog {

    private SimpleDateFormat dateFormat;
    private BaseModel doc;

    private SpecJTextArea jtxtArea;

    private SpecJButton btnOk;

    public DialogView(String title, int width, int height, BaseModel doc) {
        super(title, width, height);

        this.doc = doc;

        initComponents();
        addListeners();

        super.setMinimumSize(new Dimension(350, 350));
        super.setResizable(false);

        dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        fillTextArea();

    }

    private void initComponents() {

        jtxtArea = new SpecJTextArea(Constants.TEXT_AREA_ROWS, Constants.TEXT_AREA_COLUMNS);
        JScrollPane scrollPaneTxtArea = new JScrollPane(jtxtArea);
        scrollPaneTxtArea.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTxtArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        btnOk = new SpecJButton("OK");

        SpecJPanel panelTextArea = new SpecJPanel("panelTextArea", 350, 300);
        panelTextArea.setMinimumSize(new Dimension(350, 300));
        panelTextArea.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
        panelTextArea.setBorder(new EmptyBorder(Constants.PANEL_EMPTY_BORDER_TOP, Constants.PANEL_EMPTY_BORDER_LEFT,
                Constants.PANEL_EMPTY_BORDER_BOTTOM, Constants.PANEL_EMPTY_BORDER_RIGHT));

        SpecJPanel panelButton = new SpecJPanel("panelButton", 350, 50);
        panelButton.setMinimumSize(new Dimension(350, 50));
        panelButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 2));
        panelButton.setBorder(new EmptyBorder(Constants.PANEL_EMPTY_BORDER_TOP, Constants.PANEL_EMPTY_BORDER_LEFT,
                Constants.PANEL_EMPTY_BORDER_BOTTOM, Constants.PANEL_EMPTY_BORDER_RIGHT));

        panelTextArea.add(scrollPaneTxtArea);

        panelButton.add(btnOk);

        this.getContentPane().add(panelTextArea, BorderLayout.CENTER);
        this.getContentPane().add(panelButton, BorderLayout.SOUTH);

    }

    private void addListeners() {
        btnOk.addActionListener(event -> dispose());
    }

    private void fillTextArea() {

        StringBuilder sb = new StringBuilder();
        if (doc instanceof Nakladnaya) {
            sb.append(Constants.LBL_NUMBER + ": " + doc.getNumber() + "\n" + Constants.LBL_DATE + ": "
                    + dateFormat.format(doc.getDate()) + "\n" + Constants.LBL_USER + ": " + doc.getUser() + "\n"
                    + Constants.LBL_SUM + ": " + doc.getSum() + "\n" + Constants.LBL_VALUTA + ": "
                    + ((BaseModelModified) doc).getValuta() + "\n" + Constants.LBL_KURS_VALUTY + ": "
                    + ((BaseModelModified) doc).getKursValuty() + "\n" + Constants.LBL_TOVAR + ": "
                    + ((Nakladnaya) doc).getTovar() + "\n" + Constants.LBL_KOLICHESTVO + ": "
                    + ((Nakladnaya) doc).getKolichestvo() + "\n");
        } else if (doc instanceof Platezhka) {
            sb.append(Constants.LBL_NUMBER + ": " + doc.getNumber() + "\n" + Constants.LBL_DATE + ": "
                    + dateFormat.format(doc.getDate()) + "\n" + Constants.LBL_USER + ": " + doc.getUser() + "\n"
                    + Constants.LBL_SUM + ": " + doc.getSum() + "\n" + Constants.LBL_SOTRUDNIK + ": "
                    + ((Platezhka) doc).getSotrudink() + "\n");
        } else if (doc instanceof Zayavka) {
            sb.append(Constants.LBL_NUMBER + ": " + doc.getNumber() + "\n" + Constants.LBL_DATE + ": "
                    + dateFormat.format(doc.getDate()) + "\n" + Constants.LBL_USER + ": " + doc.getUser() + "\n"
                    + Constants.LBL_SUM + ": " + doc.getSum() + "\n" + Constants.LBL_VALUTA + ": "
                    + ((BaseModelModified) doc).getValuta() + "\n" + Constants.LBL_KURS_VALUTY + ": "
                    + ((BaseModelModified) doc).getKursValuty() + "\n" + Constants.LBL_KONTRAGENT + ": "
                    + ((Zayavka) doc).getKontragent() + "\n" + Constants.LBL_KOMISSIYA + ": "
                    + ((Zayavka) doc).getKomissiya() + "\n");
        }

        jtxtArea.setText("");
        jtxtArea.append(sb.toString());
    }

}
