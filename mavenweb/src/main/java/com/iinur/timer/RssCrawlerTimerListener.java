package com.iinur.timer;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.iinur.crawler.RssCrawler;

public class RssCrawlerTimerListener implements ServletContextListener {

	// タイマーの起動時間：24:00
	// private static final int HOUR = 24;
	// private static final int MINUTE = 0;
	// private static final int SECOND = 0;
	private Timer timer = null;

	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		event.getServletContext().log("Timer is canceled.");
	}

	public void contextInitialized(ServletContextEvent event) {
		// コンテキストを初期化時
		timer = new Timer(true);
		event.getServletContext().log("Timer is started.");
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

	private RssCrawler crawler;
	private ServletContext sc;

	private RssCrawlerTimerTask() {
	}

	public RssCrawlerTimerTask(ServletContext sc) {
		this.sc = sc;
		this.crawler = new RssCrawler(sc);
	}

	@Override
	public void run() {
		sc.log("crawler is started.");
		crawler.crawl();
		sc.log(Calendar.getInstance().getTime().toString());
		sc.log("crawler is end.");
	}

}