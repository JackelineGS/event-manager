package com.eventmanager.controllers;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.eventmanager.core.Controller;
import com.eventmanager.models.SchedulerIO;
import com.eventmanager.views.EventListView;
import com.eventmanager.views.RemoveEventView;

public class RemoveEventController extends Controller {

    //Atributos
    private RemoveEventView removeEventView;
    private EventListView eventListView;
    private JTable table;


    //Metodos
    @Override
    public void run()
    {
       //Creamos la tabla vacia
        table = new JTable(new DefaultTableModel(getNameColumns(), 0) {
           @Override
           public Class<?> getColumnClass(int col){
               return col == 0 ? Boolean.class : String.class;
           }
        });

        //Creamos la vista
        removeEventView = new RemoveEventView(this, table);
        //Cargamos los datos (removeEventView ya existe)
        loadEvents();
    }

    private void loadEvents(){
        try {
            SchedulerIO schedulerIO = new SchedulerIO();
            schedulerIO.attach(removeEventView);
            Vector<Vector<Object>> data = schedulerIO.getEvents();
            if(data != null){
                for (Vector<Object> row: data){
                    row.add(0, Boolean.FALSE);
                    addNewRow(row.toArray());
                }
            }

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void addNewRow(Object[] values){
        ((DefaultTableModel) table.getModel()).addRow(values);
    }

    public RemoveEventView getView(){
        return removeEventView;
    }

    public void selectAll(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for(int i = 0; i < model.getRowCount(); i++){
            model.setValueAt(Boolean.TRUE, i, 0);
        }
    }

    public void clearSelection(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for(int i = 0; i<model.getRowCount(); i++){
            model.setValueAt(Boolean.FALSE, i, 0);
        }
    }

    public void removeSelectionEvents(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        //Recorre
        for(int i = model.getRowCount() - 1; i >= 0; i--){
            if(Boolean.TRUE.equals(model.getValueAt(i,0))){
                model.removeRow(i);
            }
        }
        //Persiste los cambios en los archivos
        saveRemainingEvents(model);
    }

    private void saveRemainingEvents(DefaultTableModel model){
        try{
            SchedulerIO schedulerIO = new SchedulerIO();
            schedulerIO.attach(removeEventView);
            schedulerIO.removeEvents(model); //se implementara en Scheduler IO
        } catch (Exception ex){
            ex.printStackTrace();
        }
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
