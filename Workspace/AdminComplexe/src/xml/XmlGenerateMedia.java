package xml;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;

import launcher.Launcher;

public class XmlGenerateMedia {

	public XmlGenerateMedia(){


	}
	public boolean transform(){
//		try {
//
//			TransformerFactory tFactory = TransformerFactory.newInstance();
//
//			Transformer transformer = tFactory
//					.newTransformer(new javax.xml.transform.stream.StreamSource(
//							"../../XSL/medialocal.xsl"));
//
//			transformer.transform(new javax.xml.transform.stream.StreamSource(
//					Launcher.databasePath + "/planification.xml"), new javax.xml.transform.stream.StreamResult(
//					new FileOutputStream("../../XSL/media_generated/media.xml")));
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
		
//		try {
//			Runtime runTime = Runtime.getRuntime();
//			Process process = runTime
//					.exec("java -cp saxon9he.jar net.sf.saxon.Transform -s:../XML/planification.xml -xsl:../XSL/medialocal.xsl -o:../XSL/media_generated/*");
//		} catch (IOException ex) {
//			ex.printStackTrace();
//			return false;
//		}

		return false;
	}
}
