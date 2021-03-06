package jdom.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class IO {

	public static void save(Document d,String file) throws IOException {
	   XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	   
	   FileOutputStream out = new FileOutputStream(file);
	   sortie.output(d, out);
	   out.close();
	}

	public static Document getDocument(String file) throws Exception {
		SAXBuilder sxb = new SAXBuilder();
		File f = new File(file);
		return sxb.build(f);
	}
}
