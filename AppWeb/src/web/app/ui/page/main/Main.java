package web.app.ui.page.main;

import engine.java.common.CalendarFormat;
import engine.java.util.string.TextUtils;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AbstractAjaxTimerBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.time.Duration;

import web.app.WicketSession;
import web.app.setting.AuthorizationStrategy.AuthorizedPage;
import web.app.storage.dao.OrderDAO;
import web.app.ui.page.food.FoodList;
import web.app.ui.page.login.Login;
import web.app.ui.page.main.modal.PayModal;
import web.app.ui.page.main.modal.RemarkModal;
import web.app.ui.widget.modal.ModalWindow;
import web.app.ui.widget.modal.ModalWindow.ConfirmListener;
import wicket.framework.BasePage;
import wicket.framework.annotation.ComponentInjector;
import wicket.framework.annotation.LinkInjector;
import wicket.framework.annotation.ModelInjector;

import java.util.Calendar;

@SuppressWarnings("serial")
public class Main extends BasePage implements AuthorizedPage {
    
    @ModelInjector
    MainModel model;
    
    @ComponentInjector("user.name")
    Label username;
    @ComponentInjector @LinkInjector(onClick="clickMenu")
    Link<String> menu;
    @ComponentInjector @LinkInjector(onClick="logout")
    Link<String> logout;
    @ComponentInjector @LinkInjector(onClick="refresh")
    Link<String> refresh;
    @ComponentInjector
    Label date;
    @ComponentInjector
    OrderContainer order_container;
    @ComponentInjector
    OrderList order_list;
    @ComponentInjector
    Label consume;
    @ComponentInjector
    Label timer_off;
    @ComponentInjector
    Label timer_salary;
    @ComponentInjector
    Label timer_holiday;
    @ComponentInjector
    PageIndicator page_indicator;
    @ComponentInjector
    RemarkModal modal_remark;
    @ComponentInjector
    PayModal modal_pay;
    @ComponentInjector
    ModalWindow modal_cancel;
    
    void clickMenu() {
        setResponsePage(FoodList.class);
    }
    
    void logout() {
        WicketSession.removeUser();
        setResponsePage(Login.class);
    }
    
    void refresh() {
        setResponsePage(Main.class);
    }
    
    @Override
    protected void onInitialize() {
        super.onInitialize();
        
        date.setDefaultModel(model.new DateModel());
        consume.setDefaultModel(model.new ConsumeModel());
        initTimer();
        page_indicator.setList(DateRange.dateList);
    }
    
    private void initTimer() {
        Calendar cal = Calendar.getInstance();
        CalendarFormat.formatPrecision(cal, Calendar.HOUR_OF_DAY);
        cal.set(Calendar.HOUR_OF_DAY, 18);
        timer_off.setDefaultModel(model.new TimerModel(cal));
        
        if (cal.get(Calendar.DATE) > 15) cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 15);
        timer_salary.setDefaultModel(model.new TimerModel(cal));
        
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DATE, 29);
        timer_holiday.setDefaultModel(model.new TimerModel(cal));

        add(new AbstractAjaxTimerBehavior(Duration.ONE_SECOND) {
            
            @Override
            protected void onBind() {
                timer_off.setOutputMarkupId(true);
                timer_salary.setOutputMarkupId(true);
                timer_holiday.setOutputMarkupId(true);
            }
            
            @Override
            protected void onTimer(AjaxRequestTarget target) {
                target.add(timer_off);
                target.add(timer_salary);
                target.add(timer_holiday);
            }
        });
    }
    
    class OrderContainer extends WebMarkupContainer {

        public OrderContainer(String id) {
            super(id);
        }
        
        @Override
        public boolean isVisible() {
            return model.hasOrder();
        }
    }

    class OrderList extends ListView<OrderListItem> {
    
        public OrderList(String id) {
            super(id);
        }
    
        @Override
        protected void populateItem(ListItem<OrderListItem> item) {
            final OrderListItem order = item.getModelObject();
            
            item.add(new Label("username", order.user.name));
            item.add(new Label("foodname", order.food.name));
            item.add(new Label("price", order.food.price));
            item.add(populateRemark(order));
            item.add(populatePay(order));
            item.add(populateCancel(order));
        }
        
        private Component populateRemark(final OrderListItem order) {
            final boolean editable = model.user.getId() == order.user.getId()
                            && model.dateRange.getLabel() == DateRange.LABLE_TODAY;
            String remark = order.order.remark;
            if (TextUtils.isEmpty(remark) && editable)
            {
                remark = "编辑";
            }
            
            return new AjaxLink<String>("remark") {
                
                @Override
                protected void onComponentTag(ComponentTag tag) {
                    if (editable)
                    {
                        super.onComponentTag(tag);
                    }
                    else
                    {
                        tag.setName("span");
                    }
                }
                
                @Override
                public void onClick(AjaxRequestTarget target) {
                    modal_remark.setTitle("你的要求是");
                    modal_remark.setRemark(order.order.remark);
                    modal_remark.setConfirmListener(new ConfirmListener() {
                        
                        @Override
                        public void confirm(AjaxRequestTarget target) {
                            OrderDAO.setRemark(order.order, modal_remark.getRemark());
                            target.add(Main.this);
                        }
                    });
                    modal_remark.show(target);
                }
            }
            .setBody(new Model<>(remark));
        }
        
        private Component populatePay(final OrderListItem order) {
            final boolean editable = model.user.isAdmin == 1;
            final boolean isPay = order.order.is_pay == 1;
            
            return new AjaxLink<String>("pay") {
                
                @Override
                protected void onComponentTag(ComponentTag tag) {
                    if (editable)
                    {
                        super.onComponentTag(tag);
                    }
                    else
                    {
                        tag.setName("span");
                    }
                }
                
                @Override
                public void onClick(AjaxRequestTarget target) {
                    modal_pay.setTitle("设置支付状态");
                    modal_pay.setPay(isPay);
                    modal_pay.setConfirmListener(new ConfirmListener() {
                        
                        @Override
                        public void confirm(AjaxRequestTarget target) {
                            OrderDAO.setPay(order.order, modal_pay.isPay());
                            target.add(Main.this);
                        }
                    });
                    modal_pay.show(target);
                }
            }
            .setBody(new Model<>(isPay ? "已支付" : "未支付"))
            .add(new AttributeModifier("class", isPay ? "ispay_status" : "isnotpay_status"));
        }
        
        private Component populateCancel(final OrderListItem order) {
            return new AjaxLink<String>("cancel") {
                
                @Override
                public void onClick(AjaxRequestTarget target) {
                    modal_cancel.setTitle("提示");
                    modal_cancel.setMessage("确定取消 '"+order.food.name+"' ?");
                    modal_cancel.setConfirmListener(new ConfirmListener() {
                        
                        @Override
                        public void confirm(AjaxRequestTarget target) {
                            OrderDAO.cancel(order.order);
                            model.setDateRange(model.dateRange);
                            target.add(Main.this);
                        }
                    });
                    modal_cancel.show(target);
                }
            };
        }
    }

    class PageIndicator extends ListView<DateRange> {

        public PageIndicator(String id) {
            super(id);
        }

        @Override
        protected void populateItem(final ListItem<DateRange> item) {
            item.add(new Link<String>("page_number") {
                
                @Override
                public void onClick() {
                    model.setDateRange(item.getModelObject());
                }
                
                @Override
                public IModel<?> getBody() {
                    return item.getModel();
                }
            });
            
            if (model.dateRange == item.getModelObject())
            {
                item.add(new AttributeModifier("class", "active"));
            }
        }
    }
}