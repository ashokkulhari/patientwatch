package com.reporting.pages;

import java.io.ByteArrayInputStream;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
 
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
 
@ManagedBean(name = "fileUploadView")
@SessionScoped
public class FileUploadView implements Serializable{
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UploadedFile file;
 
	private StreamedContent photo;
    public StreamedContent getPhoto() {
		return photo;
	}

	public void setPhoto(StreamedContent photo) {
		this.photo = photo;
	}

	public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {System.out.println("..............fileupload............."+file);
        if(file != null) {System.out.println(".."+file.getFileName() +" , " +file.getSize());
        this.photo =getImage();
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    public StreamedContent getImage() {
			
 		if(file.getContents()==null){System.out.println("...getting default image...");
 			return new DefaultStreamedContent();
 		}else{
 			return new DefaultStreamedContent(new ByteArrayInputStream(file.getContents()),"image/jpg");
 		}
        
}
    public void handleFileUpload(FileUploadEvent event) {
    	System.out.println("..............handleFileUpload............."+event.getFile().getFileName());
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}