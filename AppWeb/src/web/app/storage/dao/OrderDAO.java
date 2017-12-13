package web.app.storage.dao;

import engine.java.dao.DAOTemplate.DAOExpression;
import engine.java.dao.Page;
import web.app.storage.DAOManager.BaseDAO;
import web.app.storage.db.Food;
import web.app.storage.db.FoodOrder;
import web.app.storage.db.User;

import java.sql.Date;
import java.util.List;

public class OrderDAO extends BaseDAO {
    
    public static List<FoodOrder> getOrderList(long startTime, long endTime) {
        return dao.find(FoodOrder.class)
        .where(DAOExpression.create("create_time")
        .between(new Date(startTime), new Date(endTime)))
        .orderDesc("update_time")
        .getAll();
    }
    
    public static void order(User user, Food food) {
        FoodOrder order = new FoodOrder();
        order.user_id = user.getId();
        order.food_id = food.getId();
        order.update_time = order.create_time = new java.util.Date();
        
        dao.save(order);
    }
    
    public static void setRemark(FoodOrder order, String remark) {
        order.remark = remark;
        order.update_time = new java.util.Date();
        dao.update(order, "remark", "update_time");
    }
    
    public static void setPay(FoodOrder order, boolean isPay) {
        order.is_pay = isPay ? 1 : 0;
        order.update_time = new java.util.Date();
        dao.update(order, "is_pay", "update_time");
    }
    
    public static void cancel(FoodOrder order) {
        dao.remove(order);
    }
    
    public static List<FoodOrder> getRecommendList(User user) {
        // SELECT food_id FROM tb_food_user WHERE user_id=3 GROUP BY food_id ORDER BY count(food_id) desc,updatetime DESC LIMIT 0,5
        return dao.find(FoodOrder.class)
        .select("food_id")
        .where(DAOExpression.create("user_id").equal(user.getId()))
        .groupBy("food_id")
        .orderDesc("count(food_id) desc", "updatetime")
        .usePage(new Page(5))
        .getAll();
    }
}