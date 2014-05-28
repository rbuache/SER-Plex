package xml;

import java.io.FileOutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import launcher.Launcher;
import net.sf.saxon.TransformerFactoryImpl;

public class XmlGenerateMedia {

	public XmlGenerateMedia(){


	}
	public boolean transform(){
		try {
			
//			net.sf.saxon.TransformerFactoryImpl saxFactory = (TransformerFactoryImpl) net.sf.saxon.TransformerFactoryImpl.newInstance();
//			TransformerFactory saxFactory = SAXTransformerFactory.newInstance();
//			SAXTransformerFactory saxFactory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
			
//			TransformerFactory saxFactory = new TransformerFactoryImpl();
//			TransformerFactory saxFactory = TransformerFactory.newInstance(
//			        "net.sf.saxon.TransformerFactoryImpl", null);
			
//			
//			Transformer transformer = saxFactory.newTransformer(new StreamSource("../../XSL/medialocal.xsl"));
//			
//			transformer.transform(new StreamSource(Launcher.databasePath + "/planification.xml"), 
//								  new StreamResult("../../XSL/media_generated/media.xml"));
//			
			TransformerFactory tFactory = TransformerFactory.newInstance();

			Transformer transformer = tFactory
					.newTransformer(new javax.xml.transform.stream.StreamSource(
							"../../XSL/medialocal.xsl"));

			transformer.transform(new javax.xml.transform.stream.StreamSource(
					Launcher.databasePath + "/planification.xml"), new javax.xml.transform.stream.StreamResult(
					new FileOutputStream("../../XSL/media_generated/media.xml")));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
//		try {
//			Runtime runTime = Runtime.getRuntime();
//			Process process = runTime
//					.exec("java -cp saxon9he.jar net.sf.saxon.Transform -s:../XML/planification.xml -xsl:../XSL/medialocal.xsl -o:../XSL/media_generated/*");
//		} catch (IOException ex) {
//			ex.printStackTrace();
//			return false;
//		}
//
//		return false;
	}
}
