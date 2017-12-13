package web.app.ui.page.main.modal;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;

import web.app.ui.widget.modal.ModalWindow;

@SuppressWarnings("serial")
public class RemarkModal extends ModalWindow {
    
    TextField<String> input;
    String remark;

    public RemarkModal(String id) {
        super(id);
        
        queue(input = new TextField<>("input", new PropertyModel<String>(this, "remark")));
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
}