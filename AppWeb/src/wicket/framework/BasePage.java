package wicket.framework;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import wicket.framework.annotation.injector.Injector;

@SuppressWarnings("serial")
public class BasePage extends WebPage {
    
    public BasePage() {
        super();
        Injector.inject(this);
    }

    public BasePage(IModel<?> model) {
        super(model);
        Injector.inject(this);
    }

    public BasePage(PageParameters parameters) {
        super(parameters);
        Injector.inject(this);
    }
    
    protected void onAjax(Component component, AjaxRequestTarget target) {}
}