package com.iinur.core.data;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iinur.core.data.bean.Tweet;

public class TweetDao {

	private static final Logger log = LoggerFactory.getLogger(TweetDao.class);

	private QueryRunner run = null;

	public TweetDao() {
		DataSource ds = BaseDao.getDataSource();
		run = new QueryRunner(ds);
	}

	public int insert(Long id, String text, String url, String screen_name,
			String name, String mini_profile_image_url, Timestamp created_at) {
		int inserts;
		try {
			inserts = run
					.update("insert into tweet (id, text, url, screen_name, name, mini_profile_image_url, created_at) values(?,?,?,?,?,?,?)",
							id, text, url, screen_name, name,
							mini_profile_image_url, created_at);
		} catch (SQLException sqle) {
			log.error(sqle.getMessage());
			throw new RuntimeException(sqle.toString());
		}
		return inserts;
	}

	public int existId(Long id) {
		List<Tweet> tweets = null;
		try {
			ResultSetHandler<List<Tweet>> rsh = new BeanListHandler<Tweet>(
					Tweet.class);
			tweets = run.query("SELECT * FROM tweet where id=?", rsh, id);
		} catch (SQLException sqle) {
			log.error(sqle.getMessage());
			throw new RuntimeException(sqle.toString());
		}
		return tweets.size();
	}

	public int existUrl(String url) {
		List<Tweet> tweets = null;
		try {
			ResultSetHandler<List<Tweet>> rsh = new BeanListHandler<Tweet>(
					Tweet.class);
			tweets = run.query("SELECT * FROM tweet where url=?", rsh, url);
		} catch (SQLException sqle) {
			log.error(sqle.getMessage());
			throw new RuntimeException(sqle.toString());
		}
		return tweets.size();
	}
	
	public List<Tweet> getWhereUrl(String url) {
		List<Tweet> tweets = null;
		try {
			ResultSetHandler<List<Tweet>> rsh = new BeanListHandler<Tweet>(
					Tweet.class);
			tweets = run.query("SELECT * FROM tweet where url=?", rsh, url);
		} catch (SQLException sqle) {
			log.error(sqle.getMessage());
			throw new RuntimeException(sqle.toString());
		}
		return tweets;
	}
}
