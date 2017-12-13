package web.app.ui.page.food;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import web.app.setting.AuthorizationStrategy.AuthorizedPage;
import web.app.storage.dao.OrderDAO;
import web.app.storage.db.Food;
import web.app.ui.page.main.Main;
import web.app.ui.widget.tip.Tip;
import wicket.framework.BasePage;
import wicket.framework.annotation.ComponentInjector;
import wicket.framework.annotation.ModelInjector;

@SuppressWarnings("serial")
public class FoodList extends BasePage implements AuthorizedPage {
    
    @ModelInjector
    FoodListModel model;

    @ComponentInjector(outputMarkupId=true)
    Tip tips;
    @ComponentInjector("user.name")
    Label username;
    @ComponentInjector(outputMarkupId=true)
    TextField<String> search;
    @ComponentInjector
    AjaxLink<String> delete;
    @ComponentInjector
    AjaxLink<String> confirm;
    @ComponentInjector
    AjaxLink<String> back;
    @ComponentInjector
    RecommendListView recommend_list;
    @ComponentInjector(outputMarkupId=true)
    WebMarkupContainer list_container;
    @ComponentInjector
    RadioGroup<Food> select_food;
    @ComponentInjector
    FoodListView food_list;
    @ComponentInjector
    PageIndicator page_indicator;
    
    @Override
    protected void onAjax(Component component, AjaxRequestTarget target) {
        if (component == delete)
        {
            model.search = null;
            model.refresh();
            target.add(search, list_container);
        }
        else if (component == confirm)
        {
            Food food = model.select_food;
            if (food == null)
            {
                tips.showTip("请先选择菜名");
                target.add(tips);
            }
            else
            {
                OrderDAO.order(model.user, food);
                setResponsePage(Main.class);
            }
        }
        else if (component == back)
        {
            setResponsePage(Main.class);
        }
    }
    
    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        search.add(new AjaxFormComponentUpdatingBehavior("inputchange") {
            
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                model.refresh();
                target.add(list_container);
            }
        });
        select_food.add(new AjaxFormChoiceComponentUpdatingBehavior() {

            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                tips.showTip(null);
                target.add(tips);
            }
        });
        food_list.setModel(model.new FoodListViewModel());
        page_indicator.setModel(model.new PageIndicator());
    }
    
    class RecommendListView extends ListView<Food> {

        public RecommendListView(String id) {
            super(id);
        }

        @Override
        protected void populateItem(ListItem<Food> item) {
            final Food food = item.getModelObject();
            
            item.add(new Label("food_name", food.name));
            item.add(new AjaxEventBehavior("click") {
                
                @Override
                protected void onEvent(AjaxRequestTarget target) {
                    model.search = food.name;
                    model.select_food = food;
                    model.refresh();
                    target.add(search, list_container);
                }
            });
        }
    }
    
    class FoodListView extends ListView<Food> {

        public FoodListView(String id) {
            super(id);
        }

        @Override
        protected void populateItem(ListItem<Food> item) {
            Food food = item.getModelObject();
            
            item.add(new Radio<Food>("radio", new Model<>(food)));
            item.add(new Label("name", food.name));
            item.add(new Label("price", food.price));
        }
    }

    class PageIndicator extends ListView<Integer> {

        public PageIndicator(String id) {
            super(id);
        }

        @Override
        protected void populateItem(final ListItem<Integer> item) {
            item.add(new AjaxLink<String>("page_number") {
                
                @Override
                public void onClick(AjaxRequestTarget target) {
                    model.page.setCurrentPage(item.getModelObject());
                    target.add(list_container);
                }
                
                @Override
                public IModel<?> getBody() {
                    return item.getModel();
                }
            });
            
            if (model.page.getCurrentPage() == item.getModelObject())
            {
                item.add(new AttributeModifier("class", "active"));
            }
        }
    }
}