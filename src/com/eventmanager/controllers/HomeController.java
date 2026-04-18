package com.eventmanager.controllers;

import java.awt.Component;
import java.io.File;
import javax.swing.JMenuItem;
import com.eventmanager.core.Controller;
import com.eventmanager.views.HomeView;
import com.eventmanager.views.EventListView;
import com.eventmanager.views.NewEventView;

public class HomeController extends Controller
{
    //atributo
    private HomeView homeView;
    private EventListController eventListController = new EventListController();
    private NewEventController newEventController = new NewEventController(eventListController);

    //Metodos
    @Override
    public void run()
    {
        eventListController.run();
        newEventController.run();

        homeView = new HomeView(this, mainFrame);
        addView("HomeView", homeView);
        mainFrame.setVisible(true);
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

}
