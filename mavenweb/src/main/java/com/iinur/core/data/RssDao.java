package com.iinur.core.data;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iinur.core.data.bean.Rss;

public class RssDao {

	private static final Logger log = LoggerFactory.getLogger(RssDao.class);

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
				rsss = run.query("SELECT * FROM rss where date_written > to_date(?,'YYYY-MM-DD')-1 ORDER BY date_written DESC", rsh, day);//yyyy-mm-dd
			} else if(StringUtils.isEmpty(category2)){
				rsss = run.query("SELECT * FROM rss where category1=? and date_written > to_date(?,'YYYY-MM-DD')-1 ORDER BY date_written DESC", rsh, category1, day);//yyyy-mm-dd
			} else {
				rsss = run.query("SELECT * FROM rss where category1=? and category2=? and date_written > to_date(?,'YYYY-MM-DD')-1 ORDER BY date_written DESC", rsh, category1, category2, day);//yyyy-mm-dd
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new RuntimeException(sqle.toString());
		}
		return rsss;
	}

	public List<Rss> search(String query){
		List<Rss> rsss = null;
		try {
			ResultSetHandler<List<Rss>> rsh = new BeanListHandler<Rss>(
					Rss.class);
			String sql =  "SELECT"
					+ " *, ts_rank(to_tsvector('japanese', title) ,to_tsquery('japanese', ?)) as rank"
					+ " FROM rss"
					+ " WHERE to_tsvector('japanese', title) @@ to_tsquery('japanese', ?)"
					+ " ORDER BY date_written DESC";

			log.debug("sql:" + sql + "//query:" + query);
			rsss = run.query(sql, rsh, query, query);
			if(rsss.size() == 0){
				sql =  "SELECT"
						+ " *, ts_rank(to_tsvector('japanese', title) ,to_tsquery('japanese', ?)) as rank"
						+ " FROM rss"
						+ " WHERE to_tsvector('japanese', title) @@ ?::tsquery"
						+ " ORDER BY date_written DESC";

				log.debug("sql:" + sql + "//query:" + query);
				rsss = run.query(sql, rsh, query, query);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new RuntimeException(sqle.toString());
		}
		return rsss;
	}

	public int insert(String blog_title, String category1, String category2, String title,
			String description, String link, Timestamp date_written, String content, String img_url) {
		int inserts;
		try {
			inserts = run
					.update("insert into rss (blog_title, category1, category2, title, description, link, date_written, content, img_url) values(?,?,?,?,?,?,?,?,?)",
							blog_title, category1, category2, title, description, link,
							date_written, content, img_url);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new RuntimeException(sqle.toString());
		}
		return inserts;
	}
	
	public void batch_update_tags(){
		ResultSetHandler<Long> rsh = new ScalarHandler<Long>();
		String sql = "SELECT update_tags()";
		//long inserts = 0;
		try {
			run.query(sql,rsh);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new RuntimeException(sqle.toString());
		}
		//return inserts;
	}

	public Rss getFromId(int id) {
		Rss rss = null;
		try {
			ResultSetHandler<Rss> rsh = new BeanHandler<Rss>(Rss.class);
			rss = run.query("SELECT * FROM rss where id=?", rsh, id);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new RuntimeException(sqle.toString());
		}
		return rss;
	}
}
