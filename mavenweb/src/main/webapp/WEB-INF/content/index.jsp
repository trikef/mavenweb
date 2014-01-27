<%@page contentType="text/html; charset=UTF-8"
	pageEncoding="Windows-31J"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-xxx', 'xxx.com');
  ga('send', 'pageview');

</script>
</head>
<body>
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
</body>
</html>