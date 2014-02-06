<%@page contentType="text/html; charset=UTF-8"
	pageEncoding="Windows-31J"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title><s:if test="!q.isEmpty()"><s:property value="q"/>�̋L���ꗗ|</s:if>newsp</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="<s:if test="!q.isEmpty()"><s:property value="q"/>�ɂ��ċL�������Љ�I</s:if>2ch��u���O�A�C�ɂȂ�ʔ����j���[�X�̍ŐV�L���ꗗ���">

<meta property="og:type" content="blog" />
<meta property="og:title" content="<s:if test="!q.isEmpty()"><s:property value="q"/>�̋L���ꗗ|</s:if>newsp" />
<meta property="og:description" content="<s:if test="!q.isEmpty()"><s:property value="q"/>�ɂ��ċL�������Љ�I</s:if>2ch��u���O�A�C�ɂȂ�ʔ����j���[�X�̍ŐV�L���ꗗ���" />
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
						<ul class="tags">
						<s:iterator value="tags">
							<li><a href="/<s:property value="word" />/tag"> <s:property value="word" /></a></li>
						</s:iterator>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-md-10">
				<s:if test="!q.isEmpty()"><h1><s:property value="q"/>�̋L���ꗗ</h1></s:if>
				<table class="table table-striped">
					<s:iterator value="rsss">
					<tr>
						<td class="td-small">
							<s:if test="!img_url.isEmpty()"><a href="<s:property value="link" />" title="<s:property value="description" />" target="blank">
							<nowrap><img src="<s:property escape="false" value="img_url" />" class="img-thumbnail img-small" /></nowrap>
							</a></s:if>
						</td>
						<td>
							<a href="<s:property value="link" />" title="<s:property value="description" />" target="blank">
								<s:property value="title" />
							</a>
							<span class="text-light"><s:property value="blog_title" /></span>
							<p class="text-right text-light">
								<a href="/<s:property value="title" />/content/<s:property value="id" />">�֘A�L�[���[�h</a>
								<small class="text-light"><s:date name="date_written" format="MM/dd HH:mm" /></small>
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