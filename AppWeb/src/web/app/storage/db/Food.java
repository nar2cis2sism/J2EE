package web.app.storage.db;

import java.io.Serializable;

import engine.java.dao.annotation.DAOPrimaryKey;
import engine.java.dao.annotation.DAOProperty;
import engine.java.dao.annotation.DAOTable;

@SuppressWarnings("serial")
@DAOTable(name="tb_food")
public class Food implements Serializable {
    
    @DAOPrimaryKey(autoincrement=true)
    private long id;
    
    @DAOProperty(column="foodname")
    public String name;

    @DAOProperty
    public String price;

    @DAOProperty
    public String searchKey;

    /******************************* 华丽丽的分割线 *******************************/
    
    public final long getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj))
        {
            return true;
        }
        
        if (obj instanceof Food)
        {
            Food food = (Food) obj;
            return food.getId() == id;
        }
        
        return false;
    }
    
    @Override
    public int hashCode() {
        return (int) id;
    }
}