package com.iinur.timer;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iinur.crawler.RssCrawler;

public class RssCrawlerTimerListener implements ServletContextListener {

	private static final Logger log = LoggerFactory.getLogger(RssCrawlerTimerListener.class);
	// タイマーの起動時間：24:00
	// private static final int HOUR = 24;
	// private static final int MINUTE = 0;
	// private static final int SECOND = 0;
	private Timer timer = null;

	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		log.debug("Timer is canceled.");
	}

	public void contextInitialized(ServletContextEvent event) {
		// コンテキストを初期化時
		timer = new Timer(true);
		log.debug("Timer is started.");
		// Calendar calendar = Calendar.getInstance();
		// calendar.set(Calendar.HOUR_OF_DAY, HOUR);
		// calendar.set(Calendar.MINUTE, MINUTE);
		// calendar.set(Calendar.SECOND, SECOND);

		// タイマーに実行したいジョブを設定
		/*
		 * timer.schedule(new RssCrawlerTimerTask(event.getServletContext()),
		 * calendar.getTime(), 1 * 24 * 60 * 60 * 1000);
		 */

		timer.schedule(new RssCrawlerTimerTask(event.getServletContext()), 0, 1 * 1 * 15 * 60 * 1000);
	}
}

class RssCrawlerTimerTask extends TimerTask {

	private static final Logger log = LoggerFactory.getLogger(RssCrawlerTimerTask.class);

	private RssCrawler crawler;

	private RssCrawlerTimerTask() {
	}

	public RssCrawlerTimerTask(ServletContext sc) {
		this.crawler = new RssCrawler(sc);
	}

	@Override
	public void run() {
		log.debug("crawler is started.");
		try{
			crawler.crawl();
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		log.debug("crawler is end.");
	}

}