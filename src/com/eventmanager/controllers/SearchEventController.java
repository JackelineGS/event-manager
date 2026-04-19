package com.eventmanager.controllers;

import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.eventmanager.core.Controller;
import com.eventmanager.models.SchedulerIO;
import com.eventmanager.views.EventListView;
import com.eventmanager.views.RemoveEventView;
import com.eventmanager.views.SearchEventView;

public class SearchEventController extends Controller {

    private SearchEventView searchEventView;
    private JTable table;

    //Metodos
    @Override
    public void run()
    {
        //Creamos la tabla vacia
        table = new JTable(new DefaultTableModel(getNameColumns(), 0) {
            @Override
            public Class<?> getColumnClass(int col){
                return String.class;
            }
        });

        //Creamos la vista
        searchEventView = new SearchEventView(this, table);
        //Cargamos los datos hallados
        loadEvents();
    }

    private void loadEvents(){
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // limpia la tabla
            SchedulerIO schedulerIO = new SchedulerIO();
            schedulerIO.attach(searchEventView); // ✅ attach correcto
            Vector<Vector<Object>> data = schedulerIO.getEvents();
            if (data != null) {
                for (Vector<Object> row : data) {
                    addNewRow(row.toArray());
                }
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void searchEvents(String keyword){

        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // limpia tabla antes de mostrar resultados
            SchedulerIO schedulerIO = new SchedulerIO();
            schedulerIO.attach(searchEventView);
            Vector<Vector<Object>> results = schedulerIO.searchEvents(keyword);
            if (results != null) {
                for (Vector<Object> row : results) {
                    addNewRow(row.toArray());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void clearSearch(){
        loadEvents();
    }

    public void addNewRow(Object[] values){
        ((DefaultTableModel) table.getModel()).addRow(values);
    }

    public SearchEventView getView() {
        return searchEventView;
    }

    public Vector<String> getNameColumns(){
        Vector<String> nameColumns = new Vector<>();
        nameColumns.add("Select"); // columna checkbox
        nameColumns.add("Date");
        nameColumns.add("Description");
        nameColumns.add("Frequency");
        nameColumns.add("E-mail");
        nameColumns.add("Alarm");
        return nameColumns;
    }
}
