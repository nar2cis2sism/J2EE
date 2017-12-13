package web.app.ui.page.main.modal;

import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import web.app.ui.widget.modal.ModalWindow;

@SuppressWarnings("serial")
public class PayModal extends ModalWindow {
    
    RadioGroup<Boolean> group;
    boolean isPay;
    Radio<Boolean> not_pay;
    Radio<Boolean> pay;

    public PayModal(String id) {
        super(id);
        
        queue(group = new RadioGroup<>("group", new PropertyModel<Boolean>(this, "isPay")));
        queue(not_pay = new Radio<>("not_pay", new Model<>(Boolean.FALSE)));
        queue(pay = new Radio<>("pay", new Model<>(Boolean.TRUE)));
    }
    
    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean isPay) {
        this.isPay = isPay;
    }
}