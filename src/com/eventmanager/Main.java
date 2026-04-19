package com.eventmanager;

import com.eventmanager.core.Controller;
import com.eventmanager.controllers.HomeController;

public class Main {
    public static void main(String[] args)
    {
        Controller c = new HomeController();
        c.run();
    }
}


