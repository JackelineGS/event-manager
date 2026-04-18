package com.eventmanager.core;

import java.awt.CardLayout; //Abstract Window Toolkit -- apila multiples paneles
import java.awt.Component; //base abstracta (botones, paneles, etiquetas)
import java.util.HashMap;
import java.util.Map; //
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Controller {

    /*atributos*/
    protected static final JFrame mainFrame = new JFrame(); //Ventana principal del programa
    private static final JPanel viewsViewer = new JPanel(new CardLayout()); //Almacena todas las vistas que cargan en la ventana principal
    private static final Map<String,Component> mainFrameComponents = new HashMap<>(); //Almacena los componentes del marco principal cuyo comportamiento puede cambiar durante la ejecución. Normalmente será {@link JMenuBar}.

    //Coloca el visor de vistas en la ventana principal
    {
        mainFrame.add(viewsViewer);
    }

    //METODOS ------------------------------------------------------------------------------------------------------

    public abstract void run(); //ejecuta el controlador y la vista asociada

    public static final void addView(String viewName, View view) //Agrega una vista al marco principal
    {
        viewsViewer.add((Component)view, viewName);
    }

    public static final void loadView(String viewName)
    {
        CardLayout cl=(CardLayout)viewsViewer.getLayout();
        cl.show(viewsViewer,viewName);
    }

    public static final void addComponent(String name, Component component)
    {
        mainFrameComponents.put(name, component);
    }

    public static final void removeComponents(String name)
    {
        mainFrameComponents.remove(name);
    }

    public static final Component getComponent(String name)
    {
        return mainFrameComponents.get(name);
    }

}
