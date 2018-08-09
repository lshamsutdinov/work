package app.services.models;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import app.models.BaseModel;
import app.models.Nakladnaya;
import app.models.Platezhka;
import app.models.Zayavka;
import app.values.Constants;

public class MainTableModel extends AbstractTableModel {

    // Константа для хранения количества столбцов в таблице.
    public static final int COLUMN_COUNT = 2;

    // Переменная для хранения ссылки на объекта коллекции для хранения списка
    // документов
    private List<BaseModel> list;

    // Создание объекта для форматирования даты.
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    // Конструктор.
    public MainTableModel(List<BaseModel> list) {
        this.list = list;
    }

    //////////////////////////////////////////////////////////////////////
    // Реализованные методы абстрактного класса для заполнения таблицы. //
    //////////////////////////////////////////////////////////////////////

    // Метод возвращает количество строк, которое будет отображаться в
    // таблице. Здесь list это список. Чтобы JTable знал количество
    // строк, которое нужно показать достаточно получить из list размер.
    @Override
    public int getRowCount() {
        return list.size();
    }

    // Метод возвращает количество столбцов(колонок) которое будет отображаться
    // в таблице.
    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    // Метод отвечает за то, какие данные в каких ячейках JTable будут
    // показываться. Методу в качестве параметров передаетяс индекс строки
    // и столбца ячейки JTable. Алгоритм работы здесь простой. По индексу
    // строки мы из списка list получаем соответствующую сущность, а по
    // индексу колонки узнаем данные из какого поля BaseModel необходимо
    // показать.
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BaseModel doc = list.get(rowIndex);
        StringBuilder sb = new StringBuilder();

        switch (columnIndex) {

        case 0:
            return fillColumnString(doc, sb);

        case 1:
            return doc.isForDel();

        default:
            return "";

        }
    }

    private String fillColumnString(BaseModel doc, StringBuilder sb) {
        if (doc instanceof Nakladnaya) {
            sb.append(Constants.NAKLADNAYA + " " + Constants.WORD_FROM + " " + dateFormat.format(doc.getDate()) + " "
                    + Constants.WORD_NUMBER + " " + doc.getNumber() + "\n");
        } else if (doc instanceof Platezhka) {
            sb.append(Constants.PLATEZHKA + " " + Constants.WORD_FROM + " " + dateFormat.format(doc.getDate()) + " "
                    + Constants.WORD_NUMBER + " " + doc.getNumber() + "\n");
        } else if (doc instanceof Zayavka) {
            sb.append(Constants.ZAYAVKA + " " + Constants.WORD_FROM + " " + dateFormat.format(doc.getDate()) + " "
                    + Constants.WORD_NUMBER + " " + doc.getNumber() + "\n");
        }
        return sb.toString();
    }

    // Чтобы чекбоксы были редактируемыми.
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        list.get(rowIndex).setForDel((Boolean) aValue);
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    // Метод возвращает заголовок колонки по её индексу. У нас 2 поля,
    // 2 колоноки. Внутри метода проверяем индекс и возвращаем
    // соответствующее имя колонки.
    @Override
    public String getColumnName(int column) {
        switch (column) {
        case 0:
            return "Описание";
        case 1:
            return "Метка удаления";
        default:
            return "";

        }

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        if (columnIndex == 1) {
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex);

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return columnIndex == 1;
    }

}