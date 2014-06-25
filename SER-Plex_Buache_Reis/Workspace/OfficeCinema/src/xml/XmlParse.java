package xml;

import java.io.File;
import java.io.IOException;

import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaders;

public class XmlParse {

	public static boolean verify() {
		SAXBuilder builder = new SAXBuilder(XMLReaders.DTDVALIDATING);
		try {
			builder.build(new File("XML/actor.xml"));
			builder.build(new File("XML/critic.xml"));
			builder.build(new File("XML/genre.xml"));
			builder.build(new File("XML/movie.xml"));
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
