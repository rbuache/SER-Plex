<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output name="page" method="html"      doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>

<!-- Différent fichier utilisé -->
<xsl:variable name="planif" select="document('../XML/planification.xml')"/>
<xsl:variable name="film" select="document('../XML/movie.xml')"/>
<xsl:variable name="acteur" select="document('../XML/actor.xml')"/>
<xsl:variable name="critique" select="document('../XML/critic.xml')"/>
<xsl:variable name="location" select="document('../XML/location.xml')"/>

<xsl:template match="/">

	<!--  Crée le fichier index. Tri par nom -->
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
	<!--  Crée le fichier tri_site, tri par site -->
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
	<!--  Crée le fichier tri_heure, tri par heure de passage-->
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
	
	<!--  Crée toutes les pages des films-->
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
					<xsl:apply-templates select="$film/movies/movie[name=$nomFilm]" />
					<p>
						<a href="index.html">Retour</a>
					</p>
			</body>
		</html>
		</xsl:result-document>
	</xsl:for-each>
	
	<!--  Crée toutes les pages des acteurs -->
	<xsl:for-each select="$acteur/actors/actor">
	<xsl:variable name="nomActeur" select="name" />
		<xsl:result-document href="{$nomActeur}.html" format="page" >
		<html>
			<head>
				<title><xsl:value-of select="$nomActeur" /></title>
				<link href="style.css" type="text/css" rel="stylesheet"/>
			</head>
			<body>
					<h1><xsl:value-of select="$nomActeur" /></h1>
					<xsl:apply-templates select="$acteur/actors/actor[name=$nomActeur]" />
					<p>
						<a href="index.html">Retour</a>
					</p>
			</body>
		</html>
		</xsl:result-document>
	</xsl:for-each>
</xsl:template>

<!--  template principal pour afficher un acteur -->
<xsl:template match="actor">
	<table>
		<tr>
			<td>
				<xsl:variable name="source" select="picture" />
				<img src="{$source}" />
			</td>
			<td>
				<b>Born: </b><xsl:value-of select="born"/><br/>
				<b>Biography: </b><xsl:value-of select="biography"/><br/>
				<b>Filmography: </b>
				<xsl:for-each select="filmography/movie">
					<xsl:value-of select="." /><xsl:text> </xsl:text>
				</xsl:for-each>
			</td>
		</tr>
	</table>
</xsl:template>

<!--   template principal pour afficher un film-->
<xsl:template match="movie">
<xsl:variable name="nomFilm" select="name" />
	<table>
		<tr>
			<td>
				<xsl:variable name="source" select="picture" />
				<img src="{$source}" height="200" weight="120" />
			</td>
			<td>
				<b>Resume:</b><xsl:apply-templates select="resume" /><br/>
				<b>Year:</b><xsl:apply-templates select="year" /><br/>
				<b>Director:</b><xsl:apply-templates select="director" /><br/>
				<b>Genre:</b><xsl:apply-templates select="genre" /><br/>
				<b>AgeMinimum:</b><xsl:apply-templates select="minimumAge" /><br/>
				<b>Actors:</b>
				<xsl:for-each select="$acteur/actors/actor/filmography[movie=$nomFilm]">
					<xsl:variable name="actor" select="../name" />
					<a href="{$actor}.html"><xsl:value-of select="$actor"/></a>
				</xsl:for-each>
				<br/>
			</td>
		</tr>
	</table>
	<h2>Critiques:</h2>
	<table>
		<xsl:apply-templates select="$critique/critics/movie[@name=$nomFilm]/*" mode="critic"/>
	</table>

</xsl:template>

<!--  template principal pour afficher un film -->
<xsl:template match="movie/critic" mode="critic">
	<tr><td>
		Par: <b><xsl:value-of select="source" /></b>
		<xsl:text>   Language: </xsl:text><xsl:value-of select="language" />
		<xsl:text>   Date: </xsl:text><xsl:value-of select="date" />
		<xsl:text>   Note: </xsl:text><xsl:value-of select="note" /><br/><br/>
		<xsl:value-of select="content" /><br/><br/>
	</td></tr>
</xsl:template>

<!--  template pour le nom du film : crée le lien-->
 <xsl:template match="name">
 <xsl:variable name="nomFilm" select="." />
	<td>
			
		<a href="{$nomFilm}.html">
			<xsl:value-of select="." />
		</a>
	</td>
	
</xsl:template>

<!--  template pour le planning-->
<xsl:template match="schedule">
	
	<td><b><xsl:value-of select="./location" /></b> Salle: <xsl:value-of select="./location/@room" /></td>
	<td><xsl:apply-templates select="./date"/></td>
	<td><xsl:apply-templates select="./hour"/></td>
	
</xsl:template>


</xsl:stylesheet>