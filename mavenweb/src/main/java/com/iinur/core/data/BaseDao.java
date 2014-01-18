package com.iinur.core.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;

import com.iinur.core.config.DBConfig;
import com.iinur.model.ConfigModel;

public class BaseDao {
	
	private static DBConfig conf;
	
    public static Connection getConnection(ServletContext sc) {
        Connection con = null;
        List<DBConfig> list = ConfigModel.getDBConfigList(sc);
        for (DBConfig c : list) {
			if("main".equals(c.getName())){
				conf = c;
			}
		}
        try {
            Class.forName(conf.getDriver());
            con = DriverManager.getConnection(conf.getUrl(),conf.getUser(),conf.getPass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
