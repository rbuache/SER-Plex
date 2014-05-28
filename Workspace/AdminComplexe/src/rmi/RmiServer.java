package rmi;

import interfaces.IServerAdmin;
import java.rmi.RemoteException;
import jdom.util.IO;
import launcher.Launcher;
import org.jdom2.Document;

public class RmiServer implements IServerAdmin{


	@Override
	public Document getHoraire() throws RemoteException {
		Document doc = null;
		
		if(Launcher.media.transform()){
			try {
				doc = IO.getDocument("../../XSL/media_generated/media.xml");
			} catch (Exception e) {
				return null;
			}
		}
		
		return doc;
	}

}
