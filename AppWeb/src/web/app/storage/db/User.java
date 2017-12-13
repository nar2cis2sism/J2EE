package web.app.storage.db;

import java.io.Serializable;

import engine.java.dao.annotation.DAOPrimaryKey;
import engine.java.dao.annotation.DAOProperty;
import engine.java.dao.annotation.DAOTable;

@SuppressWarnings("serial")
@DAOTable(name="tb_user")
public class User implements Serializable {
    
    @DAOPrimaryKey(autoincrement=true)
    private long id;
    
    @DAOProperty
    public String username;

    @DAOProperty
    public String password;

    @DAOProperty(column="chinesename")
    public String name;

    @DAOProperty(column="admin")
    public long isAdmin;

    /******************************* 华丽丽的分割线 *******************************/
    
    public final long getId() {
        return id;
    }
}