package web.app.ui.widget.modal;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.core.request.handler.IPartialPageRequestHandler;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

@SuppressWarnings("serial")
public class ModalWindow extends Panel {
    
    private static final ResourceReference JAVASCRIPT
    = new JavaScriptResourceReference(ModalWindow.class, "res/bootstrap.js");
    
    WebMarkupContainer modal;
    Form<?> form;
    Label title;
    Label message;
    AjaxButton ok;
    
    ConfirmListener confirmListener;

    public ModalWindow(String id) {
        super(id);
        
        queue((modal = new WebMarkupContainer("modal")).setOutputMarkupId(true));
        queue(form = new Form<>("form"));
        queue(title = new Label("title", Model.of()));
        queue(message = new Label("message", Model.of()));
        queue(ok = new AjaxButton("ok") {
            
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                if (confirmListener != null) confirmListener.confirm(target);
                close(target);
            }
        });
    }
    
    @Override
    public void renderHead(IHeaderResponse response) {
        response.render(JavaScriptHeaderItem.forReference(JAVASCRIPT));
    }
    
    public void setTitle(String title) {
        this.title.setDefaultModelObject(title);
    }
    
    public void setMessage(String message) {
        this.message.setDefaultModelObject(message);
    }
    
    public void setConfirmListener(ConfirmListener confirmListener) {
        this.confirmListener = confirmListener;
    }
    
    /**
     * Shows the modal window.
     * 
     * @param target Request target associated with current ajax request.
     */
    public void show(IPartialPageRequestHandler target) {
        target.add(form);
        target.appendJavaScript(String.format("$('#%s').modal('show')", modal.getMarkupId()));
    }
    
    /**
     * Closes the modal window.
     * 
     * @param target Request target associated with current ajax request.
     */
    public void close(IPartialPageRequestHandler target) {
        target.appendJavaScript(String.format("$('#%s').modal('hide')", modal.getMarkupId()));
    }
    
    public interface ConfirmListener {
        
        void confirm(AjaxRequestTarget target);
    }
}