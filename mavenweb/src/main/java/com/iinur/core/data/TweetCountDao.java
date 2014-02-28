package com.iinur.core.data;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TweetCountDao {

	private static final Logger log = LoggerFactory.getLogger(TweetCountDao.class);

	private QueryRunner run = null;
	
	public TweetCountDao(){
		DataSource ds = BaseDao.getDataSource();
		run = new QueryRunner(ds);
	}
	
	public int count(String url){
		Integer count = null;
		try {
			ResultSetHandler<Integer> rsh = new ScalarHandler<Integer>();
			count = run.query("SELECT * FROM tweet_count WHERE url=? ORDER BY created_at DESC LIMIT 1", rsh, url);
		} catch (SQLException sqle) {
			log.error(sqle.getMessage());
			throw new RuntimeException(sqle.toString());
		}
		return count;
	}
	
	public void insert(String url, Integer count){
		try {
			run.update("INSERT INTO tweet_count (url, num) VALUES(?,?)",
							url, count);
		} catch (SQLException sqle) {
			log.error(sqle.getMessage());
			throw new RuntimeException(sqle.toString());
		}
	}
}
