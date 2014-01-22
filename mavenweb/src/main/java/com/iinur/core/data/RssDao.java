package com.iinur.core.data;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.StringUtils;

import com.iinur.core.data.bean.Rss;

public class RssDao {

	private QueryRunner run = null;

	public RssDao() {
		DataSource ds = BaseDao.getDataSource();
		run = new QueryRunner(ds);
	}

	public int existTitle(String title) {
		List<Rss> rsss = null;
		try {
			ResultSetHandler<List<Rss>> rsh = new BeanListHandler<Rss>(
					Rss.class);
			rsss = run.query("SELECT * FROM rss where title=?", rsh, title);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new RuntimeException(sqle.toString());
		}
		return rsss.size();
	}

	public List<Rss> getWhereDay(String category1, String category2, String day) {
		List<Rss> rsss = null;
		try {
			ResultSetHandler<List<Rss>> rsh = new BeanListHandler<Rss>(
					Rss.class);
			if(StringUtils.isEmpty(category1)){
				rsss = run.query("SELECT * FROM rss where to_char(date_written, 'yyyy-mm-dd')=? ORDER BY date_written DESC", rsh, day);//yyyy-mm-dd
			} else if(StringUtils.isEmpty(category2)){
				rsss = run.query("SELECT * FROM rss where category1=? and to_char(date_written, 'yyyy-mm-dd')=? ORDER BY date_written DESC", rsh, category1, day);//yyyy-mm-dd
			} else {
				rsss = run.query("SELECT * FROM rss where category1=? and category2=? and to_char(date_written, 'yyyy-mm-dd')=? ORDER BY date_written DESC", rsh, category1, category2, day);//yyyy-mm-dd
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new RuntimeException(sqle.toString());
		}
		return rsss;
	}

	public int insert(String blog_title, String category1, String category2, String title,
			String description, String link, Timestamp date_written) {
		int inserts;
		try {
			inserts = run
					.update("insert into rss (blog_title, category1, category2, title, description, link, date_written) values(?,?,?,?,?,?,?)",
							blog_title, category1, category2, title, description, link,
							date_written);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new RuntimeException(sqle.toString());
		}
		return inserts;
	}
}
