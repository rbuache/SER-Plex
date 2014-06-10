package xml;

import launcher.Launcher;
import net.sf.saxon.Transform;

public class XmlGenerateMedia {

	public XmlGenerateMedia(){


	}
	public boolean transform(){
		try {
			
			Transform.main(new String[]{"-s:" + Launcher.databasePath + "/planification.xml", 
										"-xsl:../../XSL/medialocal.xsl",
										"-o:../../XSL/media_generated/*"});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
