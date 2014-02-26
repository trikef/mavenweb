<%@page contentType="text/html; charset=UTF-8"
	pageEncoding="Windows-31J"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title><s:if test="!rss.title.isEmpty()"><s:property value="rss.title"/>|</s:if>newsp</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="<s:if test="!rss.title.isEmpty()">�u<s:property value="rss.title"/>�v�̋L���ɂ��Ċ֘A�L�[���[�h�������Љ�!</s:if>2ch��u���O�A�C�ɂȂ�ʔ����j���[�X�̍ŐV�L���ꗗ���">

<meta property="og:type" content="blog" />
<meta property="og:title" content="<s:if test="!rss.title.isEmpty()"><s:property value="rss.title"/>|</s:if>newsp" />
<meta property="og:description" content="<s:if test="!rss.title.isEmpty()">�u<s:property value="rss.title"/>�v�̋L���ɂ��Ċ֘A�L�[���[�h�������Љ�!</s:if>2ch��u���O�A�C�ɂȂ�ʔ����j���[�X�̍ŐV�L���ꗗ���" />
<meta property="og:url" content="http://newsp.iinur.com/" />
<meta property="og:image" content="<s:property escape="false" value="rss.img_url" />" />
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
					<li><a href="/2�����˂�/category/2ch">2ch</a></li>
					<li><a href="/finalfantasyxiv/category2/game/ff14">FF14</a></li>
					<li><a href="/�p�Y�h��/category2/game/pad">�p�Y�h��</a></li>
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
									<button class="btn btn-default" type="submit">����</button>
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
						<div class="tags">
							<h5>�֘A�L�[���[�h</h5>
							<ul>
							<s:iterator value="tags">
								<li><a href="/<s:property value="word" />/tag"> <s:property value="word" /></a></li>
							</s:iterator>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-10">
				<a href="<s:property value="rss.link" />" title="<s:property value="rss.description" />" target="blank">
					<h1><s:property value="rss.title"/></h1>
				</a>
				<a href="<s:property value="rss.link" />" title="<s:property value="rss.description" />" target="blank">[���̋L����ǂ�]</a>
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
							<p class="text-right"><small class="text-light"><s:date name="rss.date_written" format="yyyy/MM/dd HH:mm" /></small></p>
						</td>
					</tr>
					<tr>
						<td class="content-resize" colspan="2">
						<s:property escape="false" value="rss.content" />
						</td>
					</tr>
				</table>
				<s:if test="tweets != null && !tweets.isEmpty()">
				<h4>���̋L���ւ̃c�C�[�g</h4>
				<table class="table table-striped">
					<s:iterator value="tweets">
					<tr>
						<td>
							<a href="https://twitter.com/<s:property value="screen_name" />/status/<s:property value="id" />" target="_blank">
								<img src="<s:property value="mini_profile_image_url" />" />
								<s:property value="name" />
							</a>
							<span class="text-light"><s:date name="created_at" format="yyyy/MM/dd HH:mm" />@<s:property value="screen_name" /></span>
							<p><s:property value="text" /></p>
						</td>
					</tr>
					</s:iterator>
				</table>
				</s:if>
			</div>
		</div>
	</div>
	<!-- /container -->
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="/js/bootstrap.min.js"></script>
</body>
</html>