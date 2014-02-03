<%@page contentType="text/html; charset=UTF-8"
	pageEncoding="Windows-31J"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title>newsp<s:if test="!q.isEmpty()">|<s:property value="q"/>の記事一覧</s:if></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="2chやブログ、気になる面白いニュースの最新記事一覧を提供">

<meta property="og:type" content="blog" />
<meta property="og:title" content="newsp<s:if test="!q.isEmpty()">|<s:property value="q"/>の記事一覧</s:if>" />
<meta property="og:description" content="2chやブログ、気になる面白いニュースの最新記事一覧を提供" />
<meta property="og:url" content="http://newsp.iinur.com/" />
<meta property="og:image" content="" />
<meta property="og:site_name" content="newsp" />
<meta property="og:locale" content="ja_JP" />

<!-- Le styles -->
<link href="/css/bootstrap.css" rel="stylesheet">
<style>
body {
	padding-top: 60px;
}
</style>
<link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
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
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="./">newsp</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="./">Home</a></li>
					<li><a href="./?c1=2ch">2ch</a></li>
					<li><a href="./?c1=game&c2=ff14">FF14</a></li>
					<li><a href="./?c1=game&c2=pad">パズドラ</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-sm-3">
				<form action="" method="get">
					<div class="row">
						<div class="col-lg-8">
							<div class="input-group">
								<input type="text" class="form-control" name="q"> <span
									class="input-group-btn">
									<button class="btn btn-default" type="submit">Go!</button>
								</span>
							</div>
							<!-- /input-group -->
						</div>
						<!-- /.col-lg-6 -->
					</div>
					<!-- /.row -->
				</form>
				<table>
					<s:iterator value="tags">
						<tr>
							<td><a href="?q=<s:property value="word" />"> <s:property
										value="word" /></a></td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div class="col-sm-8">
				<s:if test="!q.isEmpty()"><h1><s:property value="q"/>の記事一覧</h1></s:if>
				<table class="table table-striped">
					<s:iterator value="rsss">
						<tr>
							<td><s:date name="date_written" format="MM/dd HH:mm" /></td>
							<td><a href="<s:property value="link" />"
								title="<s:property value="description" />" target="blank"><s:property
										value="title" /></a></td>
							<td><s:property value="blog_title" /></td>
						</tr>
					</s:iterator>
				</table>
			</div>
		</div>
	</div>
	<!-- /container -->
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="/js/bootstrap.min.js"></script>
</body>
</html>