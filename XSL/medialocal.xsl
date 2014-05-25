<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output name="page" method="xml"      doctype-public="-//W3C//DTD HTML 4.01 Transitional//EN"/>

<!-- Différents fichiers utilisés -->
<xsl:variable name="planif" select="document('../XML/planification.xml')"/>
<xsl:variable name="film" select="document('../XML/movie.xml')"/>
<xsl:variable name="location" select="document('../XML/location.xml')"/>

<xsl:template match="/">

	<xsl:result-document href="medialocal.xml" format="page">
		<xsl:element name="medialocal">
			<xsl:for-each select="$planif/planifications/movie">
				<!-- on ne prend que les films diffusés à la date courante-->
				<xsl:if test="./schedule/date = format-date(current-date(), '[Y]-[M01]-[D01]')">
					<xsl:element name="movie">
						<xsl:apply-templates select="./name"/>
						<xsl:apply-templates select="./schedule"/>
						<xsl:variable name="nomFilm" select="name" />
						<xsl:element name="age">
							<xsl:value-of select="$film/movies/movie[name=$nomFilm]/minimumAge" />
						</xsl:element>
					</xsl:element>
				</xsl:if>
			</xsl:for-each>
		</xsl:element>
	</xsl:result-document>
</xsl:template>

<!--  template pour le nom du film-->
 <xsl:template match="name">
	<xsl:element name="name">
		<xsl:value-of select="." />
	</xsl:element>
</xsl:template>

<!--  template pour le planning-->
<xsl:template match="schedule">

	<xsl:element name="location">
		<xsl:value-of select="./location" />
	</xsl:element>
	<xsl:element name="date">
		<xsl:value-of select="./date" />
	</xsl:element>
	<xsl:element name="hour">
		<xsl:value-of select="./hour" />
	</xsl:element>
	
</xsl:template>


</xsl:stylesheet>