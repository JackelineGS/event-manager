package com.eventmanager.views;

import java.awt.BorderLayout;
import javax.swing.*;

import com.eventmanager.controllers.EventListController;
import com.eventmanager.controllers.RemoveEventController;
import com.eventmanager.core.Model;
import com.eventmanager.core.View;

public class RemoveEventView extends JPanel implements View {

    //Atributos
    private RemoveEventController removeEventController;
    private JTable table;

    //Constructor
    public RemoveEventView(RemoveEventController removeEventController, JTable table)
    {
        this.removeEventController = removeEventController;
        this.table = table;
        make_frame();
    }

    //Metodos
    @Override
    public void update(Model model, Object data)
    {
        if (data != null) {
            JOptionPane.showMessageDialog(this, (String) data);
        }
    }

    private void make_frame()
    {
        setLayout(new BorderLayout());

        //Tabla
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        //Botones
        JPanel btnPanel = new JPanel();
        JButton btnSelectAll = new JButton("Select All");
        JButton btnCancel = new JButton("Cancel");
        JButton btnRemove = new JButton("Remove");

        btnSelectAll.addActionListener(e -> removeEventController.selectAll());
        btnCancel.addActionListener(e -> removeEventController.clearSelection());
        btnRemove.addActionListener(e -> removeEventController.removeSelectionEvents());

        btnPanel.add(btnSelectAll);
        btnPanel.add(btnCancel);
        btnPanel.add(btnRemove);

        add(btnPanel, BorderLayout.SOUTH);
    }

}
