package com.iinur.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.iinur.core.db.BaseDao;
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
        
		ServletContext sc = ServletActionContext.getServletContext();
        Connection con = BaseDao.getConnection(sc);
        Statement stmt = con.createStatement();
        stmt.executeUpdate("insert into rss (blog_title, category, title, description, link, date_written) values('test_b','pad','こんにちは','鼻歌','http://test.com','2014/1/20 10:10')");
        ResultSet rs =stmt.executeQuery("select * from rss");
        while (rs.next()) {
            System.out.println(rs.getString(2));
          }
        System.out.println("con=" + con);
        con.close();
        return "success";
    }
}