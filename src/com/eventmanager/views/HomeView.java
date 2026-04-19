package com.eventmanager.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import com.eventmanager.controllers.HomeController;
import com.eventmanager.core.Model;
import com.eventmanager.core.View;

@SuppressWarnings("serial")
public class HomeView extends JPanel implements View {

    //atributo
    @SuppressWarnings("unused")
    private HomeController homeController;
    private JFrame mainFrame;
    private final static int MAIN_FRAME_WIDTH = 700;
    private final static int MAIN_FRAME_HEIGHT = 400;
    private final static int MAIN_FRAME_X = 100;
    private final static int MAIN_FRAME_Y = 100;

    //constructor
    public HomeView(HomeController homeController, JFrame mainFrame)
    {
        this.homeController = homeController;
        this.mainFrame = mainFrame;

        make_mainFrame();
        make_tabs();
    }

    //Metodos

    @Override
    public void update(Model model, Object data)
    {}

    public void run()
    {
        mainFrame.add(this);
        mainFrame.setVisible(true);
    }

    private void make_mainFrame()
    {
        mainFrame.setOpacity(1.0f);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setBounds(MAIN_FRAME_X, MAIN_FRAME_Y, MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
        mainFrame.setMinimumSize(new Dimension(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT));


        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
    }

    private void make_tabs()
    {
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.addTab("New event", homeController.getNewEventView());
        tabbedPane.addTab("Events", homeController.getEventListView());
        tabbedPane.addTab("Remove Event", homeController.getRemoveEventView());
        add(tabbedPane, BorderLayout.CENTER);
    }
}
