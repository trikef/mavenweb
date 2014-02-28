<%@page contentType="text/html; charset=UTF-8"
	pageEncoding="Windows-31J"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title><s:if test="!q.isEmpty()"><s:property value="q"/>の記事一覧|</s:if>newsp</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="<s:if test="!q.isEmpty()"><s:property value="q"/>について記事をご紹介！</s:if>2chやブログ、気になる面白いニュースの最新記事一覧を提供">

<meta property="og:type" content="blog" />
<meta property="og:title" content="<s:if test="!q.isEmpty()"><s:property value="q"/>の記事一覧|</s:if>newsp" />
<meta property="og:description" content="<s:if test="!q.isEmpty()"><s:property value="q"/>について記事をご紹介！</s:if>2chやブログ、気になる面白いニュースの最新記事一覧を提供" />
<meta property="og:url" content="http://newsp.iinur.com/" />
<meta property="og:image" content="<s:property escape="false" value="rsss[0].img_url" />" />
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
</head>
<body>
<!-- Google Tag Manager -->
<noscript><iframe src="//www.googletagmanager.com/ns.html?id=GTM-WBTG8Z"
height="0" width="0" style="display:none;visibility:hidden"></iframe></noscript>
<script>(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
'//www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
})(window,document,'script','dataLayer','GTM-xxx');</script>
<!-- End Google Tag Manager -->
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
					<li class="active"><a href="/">Home</a></li>
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
			<div class="col-md-3">
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
						<ul class="tags">
						<s:iterator value="tags">
							<li><a href="/<s:property value="word" />/tag"> <s:property value="word" /></a></li>
						</s:iterator>
						</ul>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<h4>人気の記事</h4>
						<table class="table table-striped">
							<s:iterator value="rsssRanking">
							<tr>
								<td class="td-small">
									<s:if test="!img_url.isEmpty()"><a href="<s:property value="link" />" title="<s:property value="description" />" target="blank">
									<nowrap><img src="<s:property escape="false" value="img_url" />" class="img-thumbnail img-small" /></nowrap>
									</a>
									</s:if>
								</td>
								<td>
									<div class="pop-small">
										<s:if test="%{num > 0}">
										<span class="twnum"><s:property value="num" />RT</span>
										</s:if>
										<a href="/<s:property value="%{title.replaceAll('%','%25')}" />/content/<s:property value="id" />">
											<s:property value="title" />
										</a>
									</div>
									<span class="text-light"><s:property value="blog_title" /></span>
								</td>
							</tr>
							</s:iterator>
						</table>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<s:if test="!q.isEmpty()"><h1><s:property value="q"/>の記事一覧</h1></s:if>
				<s:else><h4>最近の記事一覧</h4></s:else>
				<table class="table table-striped">
					<s:iterator value="rsss">
					<tr>
						<td class="td-small">
							<s:if test="!img_url.isEmpty()"><a href="<s:property value="link" />" title="<s:property value="description" />" target="blank">
							<nowrap><img src="<s:property escape="false" value="img_url" />" class="img-thumbnail img-small" /></nowrap>
							</a>
							</s:if>
						</td>
						<td>
							<s:if test="%{num > 0}">
							<span class="twnum"><s:property value="num" />RT</span>
							</s:if>
							<a href="/<s:property value="%{title.replaceAll('%','%25')}" />/content/<s:property value="id" />">
								<s:property value="title" />
							</a>
							<span class="text-light"><s:property value="blog_title" /></span>
							<p class="text-right text-light">
								<small class="text-light"><s:date name="date_written" format="yyyy/MM/dd HH:mm" /></small>
							</p>
						</td>
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