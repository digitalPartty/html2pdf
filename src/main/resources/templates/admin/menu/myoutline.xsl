<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:outline="http://wkhtmltopdf.org/outline" xmlns="http://www.w3.org/1999/xhtml">
	<xsl:output doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitiona
l.dtd" indent="yes" />
	<xsl:template match="outline:outline">
		<html>
			<head>
				<title>目录</title>
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
				<style>
					*{
						margin:0;
						padding:0;
						font-family:'思源黑体 Normal';
					}
					.flexBetween {
						display: flex;
						display: -webkit-box;
						display: -moz-box;
						display: -ms-flexbox;
						display: -webkit-flex;
						align-items: center;
						-webkit-align-items: center;
						box-align: center;
						-moz-box-align: center;
						-webkit-box-align: center;
						justify-content: space-between;
						-webkit-justify-content: space-between;
						box-pack: justify;
						-moz-box-pack: justify;
						-webkit-box-pack: justify;
					}
					.pageTitle{
						display: flex;
						align-items: center;
						justify-content: space-between;
						width: 731.76px;
						height: 48.59px;
						margin: 0 auto;
						padding:52px 0 30px;
					}
					.pageTitle p{
					height: 100%;
					}
					.pageTitle p:first-child{
						background-color: #174DA0;
						width: 68.5%;
						font-size: 18px;
						font-weight: bold;
						color: #fff;
						line-height: 46px;
						padding-left: 25px;
						margin-left:0;
					font-family:'思源黑体 Normal';

					}
					.pageTitle p:nth-child(2){
						background-color:#107FC4 ;
						width: 17%;
						margin:0 4px;
					}
					.pageTitle p:last-child{
						background-color:#80C0DF ;
						width:8%;
						margin-right:0;
					}
					.content {
						margin:8px 50px;
						position: relative;
						padding:0 45px 0 40px;
					}
					.content::after {
						content: "";
						position: absolute;
						left: 50px;
						right: 40px;
						top: 50%;
						border-bottom: 2px dashed #539367;
						pointer-events: none;
						z-index:1;
					}
					.page {
						float: right;
						font-weight:500;
						background-color: #fff;
						padding: 0 8px;
						z-index:2;
						line-height: 26px;
						position:absolute;
						right: 40px;
						font-family:'思源黑体 Normal';
					}
					a {
						text-decoration:none;
						color: black;
						background-color: #fff;
						text-align:left;
						z-index:2;
						position: relative;
						padding-right:8px;
						font-family:'思源黑体 Normal';
					}
					li {list-style: none;}
					ul {
						font-size: 14px;
						font-family:'思源黑体 Normal';
						font-weight: bold;
						padding-left: 0em;
					}
					ul ul{
						padding-left: 2em;
						font-weight: 100;
						font-size: 14px;
					}
					.topTime{
						text-align: right;
						font-size: 12px;
						padding-top:24px;
						padding-right:30px;
						font-family:'思源黑体 Normal';
					}
				</style>
			</head>
			<body>
<!--				<p class="topTime">2024年1月 - 2024年6月</p>-->
				<div class="pageTitle flexBetween">
					<p>目录</p>
					<p></p>
					<p></p>
				</div>
				<ul><xsl:apply-templates select="outline:item/outline:item"/></ul>
			</body>
		</html>
	</xsl:template>
	<xsl:template match="outline:item">
		<li>
			<xsl:if test="@title!=''">
				<div class="content">
					<a>
						<xsl:if test="@link">
							<xsl:attribute name="href"><xsl:value-of select="@link"/></xsl:attribute>
						</xsl:if>
						<xsl:if test="@backLink">
							<xsl:attribute name="name"><xsl:value-of select="@backLink"/></xsl:attribute>
						</xsl:if>
						<xsl:value-of select="@title" />
					</a>
					<span class="page"><xsl:value-of select="@page" /></span>
				</div>
			</xsl:if>
			<ul>
				<xsl:comment>added to prevent self-closing tags in QtXmlPatterns</xsl:comment>
				<xsl:apply-templates select="outline:item"/>
			</ul>
		</li>
	</xsl:template>
</xsl:stylesheet>
