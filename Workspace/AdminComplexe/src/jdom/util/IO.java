package jdom.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class IO {

	//Source:http://cynober.developpez.com/tutoriel/java/xml/jdom/
	static void save(Document d,String file) throws IOException {
	   XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	   sortie.output(d, new FileOutputStream(file));
	}

	static Document getDocument(String file) throws Exception {
		SAXBuilder sxb = new SAXBuilder();
		return sxb.build(new File(file));
	}
}
