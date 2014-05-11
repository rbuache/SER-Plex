<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output name="page" method="html"      doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>

<xsl:variable name="planif" select="document('../XML/planification.xml')"/>
<xsl:variable name="film" select="document('../XML/movie.xml')"/>
<xsl:variable name="acteur" select="document('../XML/actor.xml')"/>
<xsl:variable name="critique" select="document('../XML/critic.xml')"/>
<xsl:variable name="location" select="document('../XML/location.xml')"/>

<xsl:template match="/">
	<xsl:result-document href="index.html" format="page">
	<html>
		<head>
			<title>Films à l'affiche</title>
			<link href="style.css" type="text/css" rel="stylesheet"/>
		</head>
		<body>
				<h1>Films à l'affiche</h1>
				<p>
					Trier par : 
					<a href="index.html">Titre</a>
					<a href="tri_site.html">Site</a> 
					<a href="tri_heure.html">Heure de projection</a>
				</p>
				<table border="1">

				<xsl:for-each select="$planif/planifications/movie">
				  <xsl:sort select="name"/>
						<tr>
							<xsl:variable name="nomFilm" select="name" />
							<xsl:variable name="source" select="$film/movies/movie[name=$nomFilm]/picture" />
							<td>
								<img src="{$source}" height="200" weight="120" />
							</td>
							<xsl:apply-templates select="./name"/>
							<xsl:apply-templates select="./schedule"/>
						</tr>
				</xsl:for-each>
				</table>
		</body>
	</html>
	</xsl:result-document>
<xsl:result-document href="tri_site.html" format="page">
	<html>
		<head>
			<title>Films à l'affiche</title>
			<link href="style.css" type="text/css" rel="stylesheet"/>
		</head>
		<body>
				<h1>Films à l'affiche</h1>
				<p>
					Trier par : 
					<a href="index.html">Titre</a>
					<a href="tri_site.html">Site</a> 
					<a href="tri_heure.html">Heure de projection</a>
				</p>
				<table border="1">

				<xsl:for-each select="$planif/planifications/movie">
				  <xsl:sort select="schedule/location"/>
						<tr>
							<xsl:variable name="nomFilm" select="name" />
							<xsl:variable name="source" select="$film/movies/movie[name=$nomFilm]/picture" />
							<td>
								<img src="{$source}" height="200" weight="120" />
							</td>
							<xsl:apply-templates select="./name"/>
							<xsl:apply-templates select="./schedule"/>
						</tr>
				</xsl:for-each>
				</table>
		</body>
	</html>
	</xsl:result-document>
	<xsl:result-document href="tri_heure.html" format="page">
	<html>
		<head>
			<title>Films à l'affiche</title>
			<link href="style.css" type="text/css" rel="stylesheet"/>
		</head>
		<body>
				<h1>Films à l'affiche</h1>
				<p>
					Trier par : 
					<a href="index.html">Titre</a>
					<a href="tri_site.html">Site</a> 
					<a href="tri_heure.html">Heure de projection</a>
				</p>
				<table border="1">

				<xsl:for-each select="$planif/planifications/movie">
				  <xsl:sort select="schedule/hour" data-type="number"/>
						<tr>
							<xsl:variable name="nomFilm" select="name" />
							<xsl:variable name="source" select="$film/movies/movie[name=$nomFilm]/picture" />
							<td>
								<img src="{$source}" height="200" weight="120" />
							</td>
							<xsl:apply-templates select="./name"/>
							<xsl:apply-templates select="./schedule"/>
						</tr>
				</xsl:for-each>
				</table>
		</body>
	</html>
	</xsl:result-document>	
	<xsl:for-each select="$film/movies/movie">
	<xsl:variable name="nomFilm" select="name" />
		<xsl:result-document href="{$nomFilm}.html" format="page" >
		<html>
			<head>
				<title><xsl:value-of select="$nomFilm" /></title>
				<link href="style.css" type="text/css" rel="stylesheet"/>
			</head>
			<body>
					<h1><xsl:value-of select="$nomFilm" /></h1>
					<p>
						<a href="index.html">Retour</a>
					</p>
			</body>
		</html>
		</xsl:result-document>
	</xsl:for-each>
</xsl:template>


 <xsl:template match="name">
 <xsl:variable name="nomFilm" select="." />
	<td>
			
		<a href="{$nomFilm}.html">
			<xsl:value-of select="." />
		</a>
	</td>
	
</xsl:template>

<xsl:template match="schedule">
	
	<td><b><xsl:value-of select="./location" /></b> Salle: <xsl:value-of select="./location/@room" /></td>
	<td><xsl:apply-templates select="./date"/></td>
	<td><xsl:apply-templates select="./hour"/></td>
	
</xsl:template>


</xsl:stylesheet>