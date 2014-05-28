package xml;

import java.io.FileOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import launcher.Launcher;

public class XmlGenerateMedia {

	public XmlGenerateMedia() {

	}

	public boolean transform() {
		try {

			TransformerFactory tFactory = TransformerFactory.newInstance();

			Transformer transformer = tFactory
					.newTransformer(new javax.xml.transform.stream.StreamSource(
							"../../XSL/medialocal.xsl"));

			transformer.transform(new javax.xml.transform.stream.StreamSource(
					Launcher.databasePath + "/planification.xml"),
					new javax.xml.transform.stream.StreamResult(
							new FileOutputStream(
									"../../XSL/media_generated/media.xml")));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
}