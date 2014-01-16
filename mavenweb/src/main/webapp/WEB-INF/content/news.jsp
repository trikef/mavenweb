<%@page contentType="text/html; charset=UTF-8"
	pageEncoding="Windows-31J"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<table>
	<s:iterator value="rssList">
		<tr>
			<td><s:date name="date" format="MM/DD HH:mm"/></td>
			<td><a href="<s:property value="link" />"
				title="<s:property value="description" />"
				target="blank"><s:property
						value="title" /></a></td>
			<td><s:property value="blogTitle" /></td>
		</tr>
	</s:iterator>
</table>