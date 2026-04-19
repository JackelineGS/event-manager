package com.eventmanager.views;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.eventmanager.controllers.EventListController;
import com.eventmanager.core.Model;
import com.eventmanager.core.View;

@SuppressWarnings("serial")
public class    EventListView extends JPanel implements View {

    //Atributos
    @SuppressWarnings("unused")
    private EventListController eventListController;
    private JTable table;

    //Constructor
    public EventListView(EventListController eventListController, JTable table)
    {
        this.eventListController = eventListController;
        this.table = table;

        make_frame();
    }

    //Metodos
    @Override
    public void update(Model model, Object data)
    {
        if (data != null) {
            String notice = (String) data;
            JOptionPane.showMessageDialog(this, notice);
        }
    }

    private void make_frame()
    {
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}
