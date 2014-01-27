<%@page contentType="text/html; charset=UTF-8"
	pageEncoding="Windows-31J"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<h1>news</h1>
<table>
	<s:iterator value="rsss">
		<tr>
			<td><s:date name="date_written" format="MM/DD HH:mm" /></td>
			<td><a href="<s:property value="link" />"
				title="<s:property value="description" />" target="blank"><s:property
						value="title" /></a></td>
			<td><s:property value="blog_title" /></td>
		</tr>
	</s:iterator>
</table>