package web.app.ui.page.main;

import engine.java.common.CalendarFormat;
import engine.java.util.Util;

import org.apache.wicket.model.LoadableDetachableModel;

import web.app.WicketSession;
import web.app.storage.dao.FoodDAO;
import web.app.storage.dao.OrderDAO;
import web.app.storage.dao.UserDAO;
import web.app.storage.db.Food;
import web.app.storage.db.FoodOrder;
import web.app.storage.db.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public class MainModel {
    
    User user;
    DateRange dateRange;
    List<OrderListItem> order_list;
    
    public MainModel() {
        user = WicketSession.getUser();
        setDateRange(DateRange.lookupDateRange(System.currentTimeMillis()));
    }
    
    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
        order_list = getOrderList();
    }
    
    private List<OrderListItem> getOrderList() {
        List<FoodOrder> orderList = OrderDAO.getOrderList(dateRange.getStart(), dateRange.getEnd());
        if (orderList == null)
        {
            return null;
        }
        
        List<OrderListItem> list = new ArrayList<>(orderList.size());
        for (FoodOrder order : orderList)
        {
            list.add(new OrderListItem(order));
        }
        
        return list;
    }
    
    public boolean hasOrder() {
        return order_list != null && !order_list.isEmpty();
    }
    
    class DateModel extends LoadableDetachableModel<String> {

        @Override
        protected String load() {
            String s = CalendarFormat.format(CalendarFormat.getCalendar(dateRange.getStart()), "%tF");
            if (!hasOrder())
            {
                s += " 暂无订单";
            }
            
            return s;
        }
    }
    
    class ConsumeModel extends LoadableDetachableModel<String> {

        @Override
        protected String load() {
            if (!hasOrder()) return null;
            int totalCount = order_list.size();
            float totalPrice = 0;
            OrderListItem maxPrice = null;
            boolean hasMutipleMaxPrice = false;
            
            for (OrderListItem item : order_list)
            {
                int price = item.getFoodPrice();
                totalPrice += price;
                if (hasMutipleMaxPrice)
                {
                    break;
                }
                else if (maxPrice == null || price > maxPrice.getFoodPrice())
                {
                    maxPrice = item;
                }
                else if (price == maxPrice.getFoodPrice())
                {
                    hasMutipleMaxPrice = true;
                }
            }

            float averagePrice = totalPrice / totalCount;
            StringBuilder sb = new StringBuilder()
            .append("总数：").append(totalCount).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
            .append("总价：").append(Util.formatNumber(totalPrice, ",##0")).append("元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
            .append("人均：").append(Util.formatNumber(averagePrice, ",##0.0")).append("元&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
            if (totalCount > 1 && !hasMutipleMaxPrice)
            {
                sb.append("本次最壕：").append(maxPrice.user.name).append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
                .append("消费了").append(maxPrice.getFoodPrice()).append("元");
            }
            
            return sb.toString();
        }
    }
    
    class TimerModel extends LoadableDetachableModel<String> {
        
        private final long time;
        
        public TimerModel(Calendar cal) {
            time = cal.getTimeInMillis();
        }

        @Override
        protected String load() {
            long timeInMillis = time - System.currentTimeMillis();
            long one_second = 1000;
            long one_minute = 60 * one_second;
            long one_hour = 60 * one_minute;
            long one_day = 24 * one_hour;
            
            StringBuilder sb = new StringBuilder();
            long day = timeInMillis / one_day;
            if (day > 0)
            {
                sb.append(day).append("天");
                return sb.toString();
            }
            
            timeInMillis %= one_day;
            long hour = timeInMillis / one_hour;
            if (hour > 0)
            {
                sb.append(hour).append("时");
            }
            
            timeInMillis %= one_hour;
            long minute = timeInMillis / one_minute;
            if (minute > 0)
            {
                sb.append(minute).append("分");
            }
            
            timeInMillis %= one_minute;
            long second = timeInMillis / one_second;
            if (second > 0)
            {
                sb.append(second).append("秒");
            }
            
            return sb.toString();
        }
    }
}

class OrderListItem {
    
    public User user;
    public Food food;
    public FoodOrder order;
    
    public OrderListItem(FoodOrder order) {
        user = UserDAO.getUser(order.user_id);
        food = FoodDAO.getFood(order.food_id);
        this.order = order;
    }
    
    public int getFoodPrice() {
        return Integer.parseInt(food.price);
    }
}

class DateRange {
    
    public static final String LABLE_TODAY              = "今天";
    public static final String LABLE_YESTODAY           = "昨天";
    public static final String LABLE_LAST_DAY           = "前天";
    public static final String LABLE_THREE_DAYS_AGO     = "三天前";
    public static final String LABLE_FOUR_DAYS_AGO      = "四天前";
    public static final String LABLE_FIVE_DAYS_AGO      = "五天前";
    public static final String LABLE_SIX_DAYS_AGO       = "六天前";
    public static final String LABLE_SEVEN_DAYS_AGO     = "七天前";
    
    private final long start;
    private final long end;
    private final String label;
    
    DateRange(long start, long end, String label) {
        this.start = start;
        this.end = end;
        this.label = label;
    }
    
    boolean includes(long time) {
        return start <= time && time < end;
    }
    
    public long getStart() {
        return start;
    }
    
    public long getEnd() {
        return end;
    }
    
    public String getLabel() {
        return label;
    }
    
    @Override
    public String toString() {
        return label;
    }
    
    public static final List<DateRange> dateList;
    
    static
    {
        dateList = new LinkedList<DateRange>();
        
        // Today
        Calendar cal = Calendar.getInstance();
        CalendarFormat.formatAllDay(cal);
        long today = cal.getTimeInMillis();
        
        long start = today;
        cal.add(Calendar.DATE, 1);
        long end = cal.getTimeInMillis();
        dateList.add(new DateRange(start, end, LABLE_TODAY));
        
        // Yesterday
        cal.setTimeInMillis(today);
        end = today;
        cal.add(Calendar.DAY_OF_MONTH, -1);
        start = cal.getTimeInMillis();
        dateList.add(new DateRange(start, end, LABLE_YESTODAY));
        
        // Last day
        end = start;
        cal.add(Calendar.DAY_OF_MONTH, -1);
        start = cal.getTimeInMillis();
        dateList.add(new DateRange(start, end, LABLE_LAST_DAY));
        
        // Three days ago...
        end = start;
        cal.add(Calendar.DAY_OF_MONTH, -1);
        start = cal.getTimeInMillis();
        dateList.add(new DateRange(start, end, LABLE_THREE_DAYS_AGO));

        end = start;
        cal.add(Calendar.DAY_OF_MONTH, -1);
        start = cal.getTimeInMillis();
        dateList.add(new DateRange(start, end, LABLE_FOUR_DAYS_AGO));

        end = start;
        cal.add(Calendar.DAY_OF_MONTH, -1);
        start = cal.getTimeInMillis();
        dateList.add(new DateRange(start, end, LABLE_FIVE_DAYS_AGO));

        end = start;
        cal.add(Calendar.DAY_OF_MONTH, -1);
        start = cal.getTimeInMillis();
        dateList.add(new DateRange(start, end, LABLE_SIX_DAYS_AGO));

        end = start;
        cal.add(Calendar.DAY_OF_MONTH, -1);
        start = cal.getTimeInMillis();
        dateList.add(new DateRange(start, end, LABLE_SEVEN_DAYS_AGO));
    }
    
    public static DateRange lookupDateRange(long date) {
        for (DateRange dateRange : dateList)
        {
            if (dateRange.includes(date))
            {
                return dateRange;
            }
        }
        
        return null;
    }
}