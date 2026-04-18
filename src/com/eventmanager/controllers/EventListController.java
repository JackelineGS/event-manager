package com.eventmanager.controllers;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.eventmanager.core.Controller;
import com.eventmanager.models.SchedulerIO;
import com.eventmanager.views.EventListView;

public class EventListController extends Controller {

    //atributos

    private EventListView eventListView;
    private JTable table;

    //Metodos

    @Override
    public void run()
    {
        table = new JTable(getDataColumns(), getNameColumns());
        eventListView = new EventListView(this, table);
    }

    public void addNewRow(Object[] values)
    {
        ((DefaultTableModel) table.getModel()).addRow(values);
    }

    //Getters

    public EventListView getView()
    {
        return eventListView;
    }

    public Vector<String> getNameColumns()
    {
        Vector<String> nameColumns = new Vector<String>();

        nameColumns.add("Date");
        nameColumns.add("Description");
        nameColumns.add("Frequency");
        nameColumns.add("E-mail");
        nameColumns.add("Alarm");

        return nameColumns;
    }

    public Vector<Vector<Object>> getDataColumns()
    {
        Vector<Vector<Object>> dataColumns = null;

        try {
            SchedulerIO schedulerIO = new SchedulerIO();
            schedulerIO.attach(eventListView);
            dataColumns = schedulerIO.getEvents();
        } catch (Exception ex) { }

        return dataColumns;
    }

}
