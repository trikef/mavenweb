package com.iinur.core.data;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iinur.core.data.bean.Tag;

public class TagDao {

	private static final Logger log = LoggerFactory.getLogger(TagDao.class);

	private QueryRunner run = null;

	public TagDao() {
		DataSource ds = BaseDao.getDataSource();
		run = new QueryRunner(ds);
	}
	
	public List<Tag> getTags(){
		List<Tag> tags = null;
		int limit = 30;
		try {
			ResultSetHandler<List<Tag>> rsh = new BeanListHandler<Tag>(Tag.class);
			String sql =  "SELECT word,count(*) rank FROM tags_analyze GROUP BY word ORDER BY count(*) DESC LIMIT ?";

			//log.debug("sql:" + sql + "//limit:" + limit);
			tags = run.query(sql, rsh, limit);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new RuntimeException(sqle.toString());
		}
		return tags;
	}
	
	public List<Tag> getRelationTags(String url){
		List<Tag> tags = null;
		int limit = 30;
		try {
			ResultSetHandler<List<Tag>> rsh = new BeanListHandler<Tag>(Tag.class);
			String sql =  "SELECT word,count(*) rank FROM tags_analyze where url=? GROUP BY word ORDER BY count(*) DESC LIMIT ?";

			tags = run.query(sql, rsh, url, limit);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new RuntimeException(sqle.toString());
		}
		return tags;
	}
}
