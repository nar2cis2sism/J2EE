package web.app.ui.widget.tip;

import engine.java.util.string.TextUtils;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

@SuppressWarnings("serial")
public class Tip extends Panel {
    
    WebMarkupContainer tips_container;
    Label tips;

    public Tip(String id) {
        this(id, null);
    }
    
    public Tip(String id, String tip) {
        super(id);
        
        queue(tips_container = new WebMarkupContainer("tips_container"));
        queue(tips = new Label("tips", Model.of()));
        
        showTip(tip);
    }
    
    public void showTip(String tip) {
        if (TextUtils.isEmpty(tip))
        {
            tips_container.setVisible(false);
        }
        else
        {
            tips.setDefaultModelObject(tip);
            tips_container.setVisible(true);
        }
    }
}