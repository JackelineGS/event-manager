package com.eventmanager.controllers;

import java.awt.Component;
import java.io.File;
import javax.swing.JMenuItem;
import com.eventmanager.core.Controller;
import com.eventmanager.views.*;

public class HomeController extends Controller
{
    //atributo
    private HomeView homeView;
    private EventListController eventListController;
    private NewEventController newEventController;
    private RemoveEventController removeEventController;
    private SearchEventController searchEventController;

    //Metodos
    @Override
    public void run()
    {
        eventListController = new EventListController();
        newEventController = new NewEventController(eventListController);
        removeEventController = new RemoveEventController();
        searchEventController = new SearchEventController();

        eventListController.run();
        newEventController.run();
        removeEventController.run();
        searchEventController.run();

        //Registro de las vistas
        addView("events",       eventListController.getView());
        addView("newEvent",     newEventController.getView());
        addView("removeEvent",  removeEventController.getView());
        addView("searchEvent", searchEventController.getView());

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

    //SEARCH

    public SearchEventView getSearchEventView()
    {
        return searchEventController.getView();
    }
}
