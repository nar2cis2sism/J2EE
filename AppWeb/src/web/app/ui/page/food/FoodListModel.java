package web.app.ui.page.food;

import engine.java.dao.Page;

import org.apache.wicket.model.LoadableDetachableModel;

import web.app.WicketSession;
import web.app.storage.dao.FoodDAO;
import web.app.storage.dao.OrderDAO;
import web.app.storage.db.Food;
import web.app.storage.db.FoodOrder;
import web.app.storage.db.User;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class FoodListModel {
    
    final User user;
    final Page page;
    String search;
    final List<Food> recommend_list;
    Food select_food;
    
    public FoodListModel() {
        user = WicketSession.getUser();
        page = new Page(10);
        page.switchStartFromZero(false);
        recommend_list = getRecommendList();
        
        refresh();
    }
    
    private List<Food> getRecommendList() {
        List<FoodOrder> orderList = OrderDAO.getRecommendList(user);
        if (orderList == null) return null;
        
        List<Food> list = new ArrayList<>(orderList.size());
        for (FoodOrder order : orderList)
        {
            list.add(FoodDAO.getFood(order.food_id));
        }
        
        return list;
    }
    
    public void refresh() {
        page.setTotalRecord((int) FoodDAO.getFoodCount(search));
        page.setCurrentPage(1);
    }
    
    class FoodListViewModel extends LoadableDetachableModel<List<Food>> {

        @Override
        protected List<Food> load() {
            return FoodDAO.getFoodList(search, page);
        }
    }
    
    class PageIndicator extends LoadableDetachableModel<List<Integer>> {

        @Override
        protected List<Integer> load() {
            if (page.hasOnlyOnePage()) return null;
            int pageCount = page.getTotalPage();
            List<Integer> list = new ArrayList<>(pageCount);
            for (int i = 1; i <= pageCount; i++)
            {
                list.add(i);
            }
            
            return list;
        }
    }
}