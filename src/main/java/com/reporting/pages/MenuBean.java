package com.reporting.pages;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class MenuBean implements Serializable {
	private static final long serialVersionUID = 1L;
	  private static final Logger LOG = LoggerFactory.getLogger(MenuBean.class);
    private MenuModel model = new DefaultMenuModel();

    @PostConstruct
    public void init() {
        addMenu("File", "New", "Open", "Close", "Exit");
        addMenu("Edit", "Undo", "Redo", "Cut", "Copy");
        addMenu(addMenu("View", "Summary"), "Tools", "Settings", "Layout");
        addMenu(addMenu("Reports", "Summary Reports"), "Age Wise Report", "Report2", "Repoer3");
        addMenu("Help", "Help topics", "Support");
    }

    public DefaultSubMenu addMenu(String label, String... items) {
        return addMenu(null, label, items);
    }

    public DefaultSubMenu addMenu(DefaultSubMenu parentMenu,
                                  String label, String... items) {
        DefaultSubMenu theMenu = new DefaultSubMenu(label);
        for (Object item : items) {
            DefaultMenuItem mi = new DefaultMenuItem(item);
            mi.setUrl("#");
            theMenu.addElement(mi);
        }
        if (parentMenu == null) {
            model.addElement(theMenu);
        } else {
            parentMenu.addElement(theMenu);
        }
        return theMenu;
    }

    public MenuModel getMenuModel() {
        return model;
    }
}