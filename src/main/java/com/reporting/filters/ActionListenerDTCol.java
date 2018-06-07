package com.reporting.filters;

import java.io.Serializable;

import javax.faces.bean.SessionScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

@SessionScoped
public class ActionListenerDTCol implements ActionListener, Serializable {

	private static final long serialVersionUID = 1L;

	public ActionListenerDTCol(){
    }

    @Override
    public void processAction(ActionEvent event)
                              throws AbortProcessingException {
        System.out.println("Action Listener Fired :D"+event);
    }

}