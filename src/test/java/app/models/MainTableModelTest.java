package app.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import app.services.models.MainTableModel;

public class MainTableModelTest extends Assert {

    private MainTableModel mainTableModel_;
    private List<BaseModel> modelsList_;

    @Before
    public void init() {

        Platezhka platezhka1 = new Platezhka();
        Platezhka platezhka2 = new Platezhka();
        platezhka1.setNumber("1");
        platezhka1.setDate(new Date());
        platezhka2.setNumber("2");
        platezhka2.setDate(new Date());

        modelsList_ = new ArrayList<BaseModel>();
        modelsList_.add(platezhka1);
        modelsList_.add(platezhka2);
        mainTableModel_ = new MainTableModel(modelsList_);
    }

    @After
    public void tearDown() {
        modelsList_ = null;
        mainTableModel_ = null;
    }

    @Test
    public void testRowColumnCount() {
        assertTrue(mainTableModel_.getRowCount() == modelsList_.size());
        assertTrue(mainTableModel_.getColumnCount() == MainTableModel.COLUMN_COUNT);
    }

    @Test
    public void testGetValueAt() {

        boolean condition = false;

        if (mainTableModel_.getValueAt(0, 0) instanceof String) {
            condition = true;
        }

        if (mainTableModel_.getValueAt(0, 1) instanceof Boolean) {
            condition = true;
        }

        assertTrue(condition);
    }

    @Test
    public void testGetColumnName() {
        assertTrue("Описание".equals(mainTableModel_.getColumnName(0)));
        assertTrue("Метка удаления".equals(mainTableModel_.getColumnName(1)));
        assertTrue("".equals(mainTableModel_.getColumnName(3)));
    }

    @Test
    public void testGetColumnClass() {
        assertTrue(Boolean.class.getName().equals(mainTableModel_.getColumnClass(1).getName()));
    }

    @Test
    public void testIsCellEditable() {
        assertTrue(mainTableModel_.isCellEditable(0, 1));
        assertFalse(mainTableModel_.isCellEditable(0, 0));
    }

}
