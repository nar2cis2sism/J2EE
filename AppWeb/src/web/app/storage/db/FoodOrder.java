package web.app.storage.db;

import java.io.Serializable;
import java.util.Date;

import engine.java.dao.annotation.DAOPrimaryKey;
import engine.java.dao.annotation.DAOProperty;
import engine.java.dao.annotation.DAOTable;

@SuppressWarnings("serial")
@DAOTable(name="tb_food_user")
public class FoodOrder implements Serializable {
    
    @DAOPrimaryKey(autoincrement=true)
    private long id;
    
    @DAOProperty
    public long user_id;
    
    @DAOProperty
    public long food_id;

    @DAOProperty
    public String remark;

    @DAOProperty
    public long is_pay;

    @DAOProperty(column="updatetime")
    public Date update_time;

    @DAOProperty(column="createtime")
    public Date create_time;

    /******************************* 华丽丽的分割线 *******************************/
    
    public final long getId() {
        return id;
    }
}