package com.iinur.action;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.iinur.core.data.BaseDao;
import com.iinur.core.data.bean.Rss;
import com.opensymphony.xwork2.ActionSupport;

@InterceptorRefs({@InterceptorRef("timer"),
	@InterceptorRef("defaultStack")})
public class ZeroAction extends ActionSupport {

    private static final long serialVersionUID = 1L;

    private String replyMsg;

    public String getReplyMsg() {
        return replyMsg;
    }

    public void setReplyMsg(String message) {
        this.replyMsg = message;
    }

    public String execute() throws Exception {

        this.setReplyMsg( "これがZero Configuration" );
        
        DataSource ds = BaseDao.getDataSource();
        QueryRunner run = new QueryRunner( ds );
        List<Rss> rsss = null;
        try
        {
			int inserts = run
					.update("insert into rss (blog_title, category, title, description, link, date_written) values(?,?,?,?,?,?)",
							"test_c", "pad", "こんにちは", "鼻歌", "http://test.com",
							Timestamp.valueOf("2014-01-20 10:10:01"));
			ResultSetHandler<List<Rss>> rsh = new BeanListHandler<Rss>(Rss.class);
			rsss = run.query("SELECT * FROM rss", rsh);
        }
        catch(SQLException sqle) {
            // Handle it
        	sqle.printStackTrace();
        }
        for (Rss rss : rsss) {
			System.out.println(rss.getCreated_at());
		}
        return "success";
    }
}