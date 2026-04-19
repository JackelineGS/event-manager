package com.eventmanager.controllers;

import java.awt.Component;
import java.io.File;
import javax.swing.JMenuItem;
import com.eventmanager.core.Controller;
import com.eventmanager.views.HomeView;
import com.eventmanager.views.EventListView;
import com.eventmanager.views.NewEventView;
import com.eventmanager.views.RemoveEventView;

public class HomeController extends Controller
{
    //atributo
    private HomeView homeView;
    private EventListController eventListController;
    private NewEventController newEventController;
    private RemoveEventController removeEventController;

    //Metodos
    @Override
    public void run()
    {
        eventListController = new EventListController();
        newEventController = new NewEventController(eventListController);
        removeEventController = new RemoveEventController();

        eventListController.run();
        newEventController.run();
        removeEventController.run();

        //Registro de las vistas
        addView("events",       eventListController.getView());
        addView("newEvent",     newEventController.getView());
        addView("removeEvent",  removeEventController.getView());

        homeView = new HomeView(this, mainFrame);
        homeView.run();
    }

    //getters

    public EventListView getEventListView()
    {
        return eventListController.getView();
    }

    public NewEventView getNewEventView()
    {
        return newEventController.getView();
    }

    //REMOVE

    public RemoveEventView getRemoveEventView(){
        return removeEventController.getView();
    }

}
