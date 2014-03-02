package com.iinur.timer;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iinur.core.data.RssDao;

public class RssTagsAnalyzeTimerListener implements ServletContextListener{

	private static final Logger log = LoggerFactory.getLogger(RssTagsAnalyzeTimerListener.class);

	private Timer timer = null;

	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		log.debug("Timer is canceled.");
	}

	public void contextInitialized(ServletContextEvent event) {
		// コンテキストを初期化時
		timer = new Timer(true);
		log.debug("Timer is started.");

		timer.schedule(new RssTagsAnalyzeTimerTask(), 0, 1 * 1 * 15 * 60 * 1000);
	}
}

class RssTagsAnalyzeTimerTask extends TimerTask {

	private static final Logger log = LoggerFactory.getLogger(RssTagsAnalyzeTimerTask.class);

	@Override
	public void run() {
		log.info("batch is started.");
		try{
			RssDao dao = new RssDao();
			dao.batch_update_tags();
			dao.batch_update_tags_asoc();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("batch is end.");
	}
}