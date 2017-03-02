<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">

	<xsl:output indent="yes" method="xml" />

	<xsl:template match="/">

		<poema fecha="{html/body/div/h2}" lugar="{html/body/div/h2/em}">
			<titulo>
				<xsl:value-of select="html/body/div/h1" />
			</titulo>
			<xsl:for-each select="html/body/div/p">
				<verso>
					<xsl:value-of select="." />
				</verso>
			</xsl:for-each>
		</poema>
	</xsl:template>
</xsl:stylesheet>