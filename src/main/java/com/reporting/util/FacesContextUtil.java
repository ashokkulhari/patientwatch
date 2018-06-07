package com.reporting.util;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

public class FacesContextUtil {

	
	public static <T> T getBean(Class<T> clazz) {
	    try {
	        String beanName = getBeanName(clazz);
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        return facesContext.getApplication().evaluateExpressionGet(facesContext, "#{" + beanName + "}", clazz);
	    //return facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, nomeBean);
	    } catch (Exception ex) {ex.printStackTrace();
	        return null;
	    }
	}

	public static <T> String getBeanName(Class<T> clazz) {
	    ManagedBean managedBean = clazz.getAnnotation(ManagedBean.class);
	    String beanName = managedBean.name();

	    if (beanName!=null && !"".equals(beanName.trim())) {
	        beanName = clazz.getSimpleName();
	        beanName = Character.toLowerCase(beanName.charAt(0)) + beanName.substring(1);
	    }

	    return beanName;
	}
}
