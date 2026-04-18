package com.eventmanager.controllers;

import javax.swing.JOptionPane;
import com.eventmanager.core.Controller;
import com.eventmanager.models.SchedulerEvent;
import com.eventmanager.models.SchedulerIO;
import com.eventmanager.views.EventListView;
import com.eventmanager.views.NewEventView;

public class NewEventController extends Controller {

    //Atributos
    private NewEventView newEventView;
    private EventListController eventListController;

    //Constructor
    public NewEventController(EventListController eventListController)
    {
        this.eventListController = eventListController;
    }

    //Metodo

    public void run()
    {
        newEventView = new NewEventView(this);
    }

    public void addEvent(SchedulerEvent event)
    {
        Object[] metadata = new Object[5];
        metadata[0] = event.getDate();
        metadata[1] = event.getEventDesc();
        metadata[2] = event.getFrequency();
        metadata[3] = event.getFwdEmail();
        metadata[4] = event.getAlarm() ? "ON" : "OFF";

        try {
            SchedulerIO schedulerIO = new SchedulerIO();
            schedulerIO.attach(newEventView);
            schedulerIO.saveEvent(event);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR", e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }


        eventListController.addNewRow(metadata);
    }

    //getters

    public NewEventView getView()
    {
        return newEventView;
    }

}
