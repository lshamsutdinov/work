package app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import app.gui.DialogNakladnaya;
import app.gui.DialogPlatezhka;
import app.gui.DialogZayavka;
import app.gui.SpecJButton;
import app.gui.SpecJFrame;
import app.gui.SpecJPanel;
import app.interfaces.observer.Subscriber;
import app.listeners.ButtonDeleteActionListener;
import app.listeners.ButtonLoadActionListener;
import app.listeners.ButtonSaveActionListener;
import app.listeners.TableMouseListener;
import app.models.BaseModel;
import app.operations.CommonMethods;
import app.services.models.MainTableModel;
import app.values.Constants;

public class Start implements Subscriber {

    private JTable table;
    private JScrollPane scrollPaneTable;

    private SpecJButton btnNakladnaya;
    private SpecJButton btnPlatezhka;
    private SpecJButton btnZayavka;
    private SpecJButton btnSave;
    private SpecJButton btnLoad;
    private SpecJButton btnView;
    private SpecJButton btnExit;
    private SpecJButton btnDelete;

    private SpecJPanel panelTable;
    private SpecJPanel panelButton;
    private SpecJFrame frame;

    public static List<BaseModel> docRepository;

    private static Start start;

    public Start() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException exception) {
            CommonMethods.showErrorDialog(Constants.ERROR_MESSAGE_PROGRAM, Constants.ERROR_TITLE_PROGRAM, exception);
        }

        JFrame.setDefaultLookAndFeelDecorated(true);

        docRepository = new ArrayList<>();

        createTable();
        createButtons();
        createPanels();

        createFrame();

        addButtonListeners();
    }

    public static void main(String[] args) {

        start = new Start();

        /*
         * Создание и отображение формы.
         * 
         * Благодаря запуску через
         * "java.awt.EventQueue.invokeLater(new Runnable() {...}" создание и
         * отображение формы произойдет после того, как все ожидаемые события
         * обработаются, т.е. фрейм создасться и отобразится, когда все ресурсы
         * будут готовы. Это необходимо, чтобы все элементы гарантированно
         * отобразились в окне. (Если сделать "frame.setVisible(true)" из
         * главного потока, то есть вероятность, что какой-либо элемент не
         * отобразиться в окне).
         * 
         */
        javax.swing.SwingUtilities.invokeLater(() -> start.frame.setVisible(true));
    }

    public static Start getSubscriberStart() {
        return start;
    }

    private void createTable() {
        table = new JTable();
        table.setModel(new MainTableModel(docRepository));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        table.getColumnModel().getColumn(0).setMinWidth(150);
        table.getColumnModel().getColumn(1).setMinWidth(50);
        table.getColumnModel().getColumn(1).setMaxWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(130);
        // table.setPreferredScrollableViewportSize(new Dimension(450, 150));

        scrollPaneTable = new JScrollPane(table);
        scrollPaneTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPaneTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    private void createButtons() {
        btnNakladnaya = new SpecJButton(Constants.BTN_LABEL_NAKLADNAYA);
        btnPlatezhka = new SpecJButton(Constants.BTN_LABEL_PLATEZHKA);
        btnZayavka = new SpecJButton(Constants.BTN_LABEL_ZAYAVKA);
        btnSave = new SpecJButton(Constants.BTN_LABEL_SAVE);
        btnLoad = new SpecJButton(Constants.BTN_LABEL_LOAD);
        btnView = new SpecJButton(Constants.BTN_LABEL_VIEW);
        btnExit = new SpecJButton(Constants.BTN_LABEL_EXIT);
        btnDelete = new SpecJButton(Constants.BTN_LABEL_DELETE);
    }

    private void createPanels() {
        panelTable = new SpecJPanel("tablePanel", Constants.PANEL_TEXT_PREF_WIDTH, Constants.PANEL_TEXT_PREF_HEIGHT);
        panelTable.setMinimumSize(new Dimension(Constants.PANEL_TEXT_MIN_WIDTH, Constants.PANEL_TEXT_MIN_HEIGHT));
        panelTable.setLayout(new BorderLayout(Constants.PANEL_TEXT_BORDER_LAYOUT_HORIZONTAL_GAP,
                Constants.PANEL_TEXT_BORDER_LAYOUT_VERTICAL_GAP));
        panelTable.setBorder(new EmptyBorder(Constants.PANEL_EMPTY_BORDER_TOP, Constants.PANEL_EMPTY_BORDER_LEFT,
                Constants.PANEL_EMPTY_BORDER_BOTTOM, Constants.PANEL_EMPTY_BORDER_RIGHT));

        panelButton = new SpecJPanel("buttonPanel", Constants.PANEL_BTN_PREF_WIDTH, Constants.PANEL_BTN_PREF_HEIGHT);
        panelButton.setMinimumSize(new Dimension(Constants.PANEL_BTN_MIN_WIDTH, Constants.PANEL_BTN_MIN_HEIGHT));
        panelButton
                .setLayout(new GridLayout(Constants.PANEL_BTN_GRID_LAYOUT_ROWS, Constants.PANEL_BTN_GRID_LAYOUT_COLUMNS,
                        Constants.PANEL_BTN_GRID_LAYOUT_HORIZONTAL_GAP, Constants.PANEL_BTN_GRID_LAYOUT_VERTICAL_GAP));
        panelButton.setBorder(new EmptyBorder(Constants.PANEL_EMPTY_BORDER_TOP, Constants.PANEL_EMPTY_BORDER_LEFT,
                Constants.PANEL_EMPTY_BORDER_BOTTOM, Constants.PANEL_EMPTY_BORDER_RIGHT));

        panelTable.add(scrollPaneTable, BorderLayout.CENTER);

        panelButton.add(btnNakladnaya);
        panelButton.add(btnPlatezhka);
        panelButton.add(btnZayavka);
        panelButton.add(btnSave);
        panelButton.add(btnLoad);
        panelButton.add(btnView);
        panelButton.add(btnDelete);
        panelButton.add(btnExit);
    }

    private void createFrame() {
        frame = new SpecJFrame("Test", Constants.FRAME_PREF_WIDTH, Constants.FRAME_PREF_HEIGHT, new BorderLayout(2, 2));
        frame.setMinimumSize(new Dimension(Constants.FRAME_MIN_WIDTH, Constants.FRAME_MIN_HEIGHT));

        frame.getContentPane().add(panelTable, BorderLayout.CENTER);
        frame.getContentPane().add(panelButton, BorderLayout.EAST);
    }

    private void addButtonListeners() {
        btnNakladnaya.addActionListener(event -> CommonMethods.dialogDocActionPerformed(DialogNakladnaya.class));
        btnPlatezhka.addActionListener(event -> CommonMethods.dialogDocActionPerformed(DialogPlatezhka.class));
        btnZayavka.addActionListener(event -> CommonMethods.dialogDocActionPerformed(DialogZayavka.class));
        btnSave.addActionListener(new ButtonSaveActionListener());
        btnLoad.addActionListener(new ButtonLoadActionListener());
        btnView.addActionListener(event -> CommonMethods.dialogViewActionPerformed(table));
        btnDelete.addActionListener(new ButtonDeleteActionListener());
        btnExit.addActionListener(event -> System.exit(0));

        table.addMouseListener(new TableMouseListener());
    }

    @Override
    public void doAction() {
        ((AbstractTableModel) table.getModel()).fireTableDataChanged();
    }

}
