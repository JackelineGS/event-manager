package com.eventmanager.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.eventmanager.core.Model;
import com.eventmanager.core.View;

import javax.swing.table.DefaultTableModel;

public class SchedulerIO implements Model {
    //Atributos
    private static final String DIRECTORY = ".";
    private static final String FILE = "events.txt";
    private List<View> views = new ArrayList<>();
    private String notice;

    //Metodos
    @Override
    public void attach(View view)
    {
        views.add(view);
    }

    @Override
    public void detach(View view)
    {
        views.remove(view);
    }

    @Override
    public void notifyViews()
    {
        for (View v : views) {
            v.update(this, notice);
        }
    }

    public void saveEvent(SchedulerEvent event) throws Exception
    {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(DIRECTORY, FILE), true));
            writer.write(event.toString(), 0, event.toString().length());
            writer.newLine();
            writer.close();
        } catch (FileNotFoundException fnfe) {
            notice = "File not found";
            notifyViews();
        } catch (Exception ex) {
            notice = "Error while writing the file";
            notifyViews();
        }
    }

    public Vector<Vector<Object>> getEvents() throws Exception
    {
        Vector<Vector<Object>> response = new Vector<Vector<Object>>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(DIRECTORY, FILE)));
            String line = reader.readLine();

            while (line != null) {
                Vector<Object> eventInfo = new Vector<Object>();
                String[] tokens = line.split(";");

                eventInfo.add(tokens[0]);
                eventInfo.add(tokens[1]);
                eventInfo.add(Frequency.valueOf(tokens[2]));
                eventInfo.add(tokens[3]);
                eventInfo.add(tokens[4].equals("1") ? "ON" : "OFF");

                response.add(eventInfo);
                line = reader.readLine();
            }

            reader.close();
        } catch (FileNotFoundException fnfe) {
            notice = "File not found";
            notifyViews();
        } catch (Exception ex) {
            notice = "There was a problem reading the event file";
            notifyViews();
        }

        return response;
    }

    //REMOVE EVENTS

    public void removeEvents(DefaultTableModel model){
        try{
            //Limpiar el archivo y reescribir solo los eventos que quedan
            FileWriter fw = new FileWriter(FILE, false);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i< model.getRowCount(); i++) {
                String date      = (String) model.getValueAt(i, 1);
                String desc      = (String) model.getValueAt(i, 2);
                String frequency = (String) model.getValueAt(i, 3);
                String email     = (String) model.getValueAt(i, 4);
                String alarm     = model.getValueAt(i, 5).equals("ON") ? "1" : "0";

                bw.write(date + "," + desc + "," + frequency + "," + email + "," + alarm);
                bw.newLine();
            }
            bw.close();
            notifyViews();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    //SEARCH EVENTS

    public Vector<Vector<Object>> searchEvents(String keyword) throws Exception {
        Vector<Vector<Object>> allEvents = getEvents(); // propaga la excepción
        Vector<Vector<Object>> results = new Vector<>();

        for (Vector<Object> row : allEvents) {
            for (Object cell : row) {
                if (cell.toString().toLowerCase().contains(keyword.toLowerCase())) {
                    results.add(row);
                    break;
                }
            }
        }
        return results;
    }
}
