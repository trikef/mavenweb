package com.iinur.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.iinur.core.data.bean.Rss;
import com.iinur.model.RssModel;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author k
 *
 */
public class NewsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

    private List<Rss> rsss;

	//category
    public String c1;
    public String c2;
    public String d = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

    public List<Rss> getRsss() {
		return rsss;
	}

	public void setRsss(List<Rss> rsss) {
		this.rsss = rsss;
	}

	public String execute() {

		ServletContext sc = ServletActionContext.getServletContext();

        RssModel model = new RssModel(sc);
        this.setRsss(model.get(c1, c2, d));

		return "success";
	}
}
