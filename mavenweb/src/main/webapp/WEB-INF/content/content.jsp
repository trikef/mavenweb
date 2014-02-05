<%@page contentType="text/html; charset=UTF-8"
	pageEncoding="Windows-31J"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title><s:if test="!rss.title.isEmpty()"><s:property value="rss.title"/>|</s:if>newsp</title>
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
<link href="/css/main.css" rel="stylesheet">
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
				<a class="navbar-brand" href="/">newsp</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="./">Home</a></li>
					<li><a href="/2ちゃんねる/category/2ch">2ch</a></li>
					<li><a href="/finalfantasyxiv/category2/game/ff14">FF14</a></li>
					<li><a href="/パズドラ/category2/game/pad">パズドラ</a></li>
					<li><a href="/it/category/it">IT</a></li>
					<li><a href="/lifehack/category/life">Lifehack</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-2">
				<form action="/" method="get">
					<div class="row">
						<div class="col-xs-12">
							<div class="input-group">
								<input type="text" class="form-control" name="q"> <span
									class="input-group-btn">
									<button class="btn btn-default" type="submit">検索</button>
								</span>
							</div>
							<!-- /input-group -->
						</div>
						<!-- /.col-lg-6 -->
					</div>
					<!-- /.row -->
				</form>
				<div class="row">
					<div class="col-xs-12">
						<h4>関連キーワード</h4>
						<ul class="tags">
						<s:iterator value="tags">
							<li><a href="/<s:property value="word" />/tag"> <s:property value="word" /></a></li>
						</s:iterator>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-10">
				<a href="<s:property value="rss.link" />" title="<s:property value="rss.description" />" target="blank">
					<h1><s:property value="rss.title"/></h1>
				</a>
				<a href="<s:property value="rss.link" />" title="<s:property value="rss.description" />" target="blank">[元の記事を読む]</a>
				<table class="table table-striped">
					<tr>
						<td class="td-small">
							<s:if test="!rss.img_url.isEmpty()"><a href="<s:property value="rss.link" />" title="<s:property value="rss.description" />" target="blank">
							<nowrap><img src="<s:property escape="false" value="rss.img_url" />" class="img-thumbnail img-small" /></nowrap>
							</a></s:if>
						</td>
						<td class="content-resize-col-1">
							<a href="<s:property value="rss.link" />" title="<s:property value="rss.description" />" target="blank">
								<s:property value="rss.description" />
							</a>
							<span class="text-light"><s:property value="rss.blog_title" /></span>
							<p class="text-right"><small class="text-light"><s:date name="rss.date_written" format="MM/dd HH:mm" /></small></p>
						</td>
					</tr>
					<tr>
						<td class="content-resize" colspan="2">
						<s:property escape="false" value="rss.content" />
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<!-- /container -->
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="/js/bootstrap.min.js"></script>
</body>
</html>