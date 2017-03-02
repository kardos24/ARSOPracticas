<?xml version="1.0"?>
<xsl:stylesheet xmlns:c="http://www.um.es/as"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

	<xsl:output indent="yes" method="xml" />

	<!-- Estilo imperativo -->
	<xsl:template match="/">
		<html>
			<head>
				<title>Poema</title>
			</head>
			<body>
				<div>
					<h1>
						<xsl:value-of select="poema/titulo" />
					</h1>
					<h2>
						<xsl:value-of select="poema/@fecha" />
					</h2>
					<h2>
						<em>
							<xsl:value-of select="poema/@lugar" />
						</em>
					</h2>
					<xsl:for-each select="poema/verso">
						<p>
							<xsl:value-of select="." />
						</p>
					</xsl:for-each>
				</div>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>