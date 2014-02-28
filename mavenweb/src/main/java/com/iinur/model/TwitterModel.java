package com.iinur.model;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import com.iinur.core.data.TweetCountDao;
import com.iinur.core.data.TweetDao;
import com.iinur.core.data.bean.Tweet;

public class TwitterModel {

	private static final Logger log = LoggerFactory.getLogger(TwitterModel.class);
	
	public int countSearch(String key){
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://urls.api.twitter.com/1/urls/count.json?url=" + key);
		int count = 0;
            ResponseHandler<Integer> responseHandler = new ResponseHandler<Integer>() {

                public Integer handleResponse(
                        final HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        String responseBody = entity != null ? EntityUtils.toString(entity) : null;
                        JSONObject json = new JSONObject(responseBody);
                        return new Integer(json.get("count").toString());
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }

            };
            try {
				count = httpclient.execute(httpGet, responseHandler);
			} catch (ClientProtocolException e) {
				log.error(e.getMessage());
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		return count;
	}

	public void registrationTweetCount(String url, Integer count){
		TweetCountDao dao = new TweetCountDao();
		dao.insert(url, count);
	}

	public List<Status> search(String key) {
		Twitter twitter = TwitterFactory.getSingleton();
		Query query = new Query(key);
		QueryResult result = null;
		try {
			result = twitter.search(query);
			log.debug("count:"+result.getCount());
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