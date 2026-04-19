package com.eventmanager.views;

import java.awt.BorderLayout;
import javax.swing.*;

import com.eventmanager.controllers.EventListController;
import com.eventmanager.controllers.RemoveEventController;
import com.eventmanager.controllers.SearchEventController;
import com.eventmanager.core.Model;
import com.eventmanager.core.View;

public class SearchEventView extends JPanel implements View {

    //Atributos
    private SearchEventController searchEventController;
    private JTable table;

    //Constructor
    public SearchEventView(SearchEventController searchEventController, JTable table){
        this.searchEventController = searchEventController;
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

        // Campo de búsqueda
        JPanel searchPanel = new JPanel();
        JTextField txtKeyword = new JTextField(20);
        JButton btnSearch = new JButton("Search");
        JButton btnClear = new JButton("Clear");

        // llama al controller correcto pasando el keyword
        btnSearch.addActionListener(e -> searchEventController.searchEvents(txtKeyword.getText()));
        btnClear.addActionListener(e -> {
            txtKeyword.setText("");
            searchEventController.clearSearch();
        });

        searchPanel.add(txtKeyword);
        searchPanel.add(btnSearch);
        searchPanel.add(btnClear);

        add(searchPanel, BorderLayout.NORTH);

        // Tabla
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }

}
