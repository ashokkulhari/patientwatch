package com.reporting.pages;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.reporting.model.TabInfo;

@ManagedBean(name="tabbean")
@SessionScoped
public class TabBean implements Serializable{
  private List<TabInfo> tabs;
  private static final long serialVersionUID = 1L;
  private static final Logger LOG = LoggerFactory.getLogger(TabBean.class);
  
  
  @PostConstruct
  public void init() {System.out.println("......init .initializing tabs .................");
        tabs = Arrays.asList(new TabInfo("About", "about"),
                new TabInfo("Reviews", "reviews"),
                new TabInfo("Contact Us", "contact-us")
                );
  }
  
  public TabBean() {System.out.println(".......initializing tabs .................");
        tabs = Arrays.asList(new TabInfo("About", "about"),
                new TabInfo("Reviews", "reviews"),
                new TabInfo("Contact Us", "contact-us")
                );
  }

  public List<TabInfo> getTabs() {
      return tabs;
  }
}
