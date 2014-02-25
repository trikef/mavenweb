package com.iinur.model;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import com.iinur.core.data.TweetDao;
import com.iinur.core.data.bean.Tweet;

public class TwitterModel {

	private static final Logger log = LoggerFactory.getLogger(TwitterModel.class);
			
	public List<Status> search(String key) {
		Twitter twitter = TwitterFactory.getSingleton();
		Query query = new Query(key);
		QueryResult result = null;
		try {
			result = twitter.search(query);
		} catch (TwitterException e) {
			log.error(e.getMessage());
		}
		return result.getTweets();
	}

	public int registration(Status tweet, String url) {
		int exists;
		TweetDao dao = new TweetDao();
		exists = dao.existId(tweet.getId());
		if (exists == 0) {
			dao.insert(new Long(tweet.getId()), tweet.getText(), url, tweet
					.getUser().getScreenName(), tweet.getUser().getName(),
					tweet.getUser().getMiniProfileImageURL(),
					new Timestamp(tweet.getCreatedAt().getTime()));
		}
		return exists;
	}

	public List<Tweet> getWhereUrl(String url) {
		TweetDao dao = new TweetDao();
		if(dao.existUrl(url) < 10){
			List<Status> tweets = search(url);
			for (Status tweet : tweets) {
				registration(tweet, url);
			}
			log.debug("get tweets num:" + tweets.size() + " url:" + url);
		}
		return dao.getWhereUrl(url);
	}
}