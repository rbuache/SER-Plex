<?xml version="1.0" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:variable name="actor" select="document('../XML/actor.xml')"/>
<xsl:variable name="critic" select="document('../XML/critic.xml')"/>
<xsl:variable name="genre" select="document('../XML/genre.xml')"/>
<xsl:variable name="location" select="document('../XML/location.xml')"/>
<xsl:variable name="movie" select="document('../XML/movie.xml')"/>
<xsl:variable name="planification" select="document('../XML/planification.xml')"/>


<xsl:template match="/">
<html>
	<head>
		<title>TEST</title>
	</head>
	<body>
		<xsl:apply-templates select="$actor//" />
	</body>
</html>
</xsl:template>

<xsl:template match="actor">
	<xsl:text>-</xsl:text>
	<xsl:value-of select="name"/>
</xsl:template>



</xsl:stylesheet>