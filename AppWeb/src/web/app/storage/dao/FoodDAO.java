package web.app.storage.dao;

import engine.java.dao.DAOTemplate.DAOExpression;
import engine.java.dao.Page;
import engine.java.util.string.TextUtils;
import web.app.storage.DAOManager.BaseDAO;
import web.app.storage.db.Food;

import java.util.List;

public class FoodDAO extends BaseDAO {
    
    public static Food getFood(long id) {
        return findItemByProperty(Food.class, "id", id);
    }
    
    public static long getFoodCount(String searchKey) {
        return dao.find(Food.class).where(getWhereExpression(searchKey)).getCount();
    }
    
    public static List<Food> getFoodList(String searchKey, Page page) {
        return dao.find(Food.class)
              .where(getWhereExpression(searchKey))
              .usePage(page)
              .orderBy("price", "searchKey")
              .getAll();
    }
    
    private static DAOExpression getWhereExpression(String searchKey) {
        if (TextUtils.isEmpty(searchKey))
        {
            return null;
        }
        
        return DAOExpression.create("searchKey").like("%" + searchKey + "%");
    }
}