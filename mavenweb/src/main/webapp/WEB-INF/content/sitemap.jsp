<%@page contentType="text/html; charset=UTF-8" pageEncoding="Windows-31J"%><%@taglib uri="/struts-tags" prefix="s"%><?xml version="1.0" encoding="UTF-8"?>
<urlset xmlns="http://www.sitemaps.org/schemas/sitemap/0.9">
<url>
  <loc><s:property value="url" />/</loc>
  <priority>1.0</priority>
  <changefreq>always</changefreq>
</url>
<url>
  <loc><s:property value="url" />/?c1=2ch</loc>
  <priority>0.8</priority>
  <changefreq>always</changefreq>
</url>
<url>
  <loc><s:property value="url" />/?c1=game&amp;c2=ff14</loc>
  <priority>0.8</priority>
  <changefreq>always</changefreq>
</url>
<url>
  <loc><s:property value="url" />/?c1=game&amp;c2=pad</loc>
  <priority>0.8</priority>
  <changefreq>always</changefreq>
</url>
<s:iterator value="tags"><url>
  <loc><s:url value="%{url}" ><s:param name="q" value="%{word}" /></s:url></loc>
  <priority>0.7</priority>
  <changefreq>always</changefreq>
</url>
</s:iterator></urlset>