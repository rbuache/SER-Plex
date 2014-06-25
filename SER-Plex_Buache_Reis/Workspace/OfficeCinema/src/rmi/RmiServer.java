package rmi;

import java.io.File;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.Observer;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import xml.XmlParse;
import interfaces.IClientSignal;
import interfaces.IClientSignal.Signal;
import interfaces.IServerOffice;

public class RmiServer extends Observable implements IServerOffice{

	public RmiServer(){

	}
	
	public void signalAdmin(){
 	   setChanged();
       notifyObservers();
	}

    @Override
    public void addObserver(IClientSignal client) throws RemoteException {
        WrappedObserver wo = new WrappedObserver(client);
        addObserver(wo);
        System.out.println("Added observer: " + wo);
    }

    /*
     *  Observer
     */
	private class WrappedObserver implements Observer, Serializable {

		private static final long serialVersionUID = -2067345842536415833L;
		
		private IClientSignal ro = null;

        public WrappedObserver(IClientSignal ro) {
            this.ro = ro;
        }

        @Override
        public void update(Observable o, Object arg) {
            try {
            	System.out.println(ro);
                ro.update(null, Signal.UPDATE_REQUESTED, null);
            } catch (RemoteException e) {
                System.out.println("Remote exception removing observer: " + this);
                o.deleteObserver(this);
            }
        }
    }

	@Override
	public Document[] getXml() throws RemoteException {
		if(XmlParse.verify()){
			Document[] doc = new Document[4];
			try {
				doc[0] = getDocument("XML/actor.xml");
				doc[1] = getDocument("XML/critic.xml");
				doc[2] = getDocument("XML/genre.xml");
				doc[3] = getDocument("XML/movie.xml");
			} catch (Exception e) {
				return null;
			}
			return doc;
		}
		else{
			return null;
		}
	}
	public static Document getDocument(String file) throws Exception {
		SAXBuilder sxb = new SAXBuilder();
		File f = new File(file);
		return sxb.build(f);
	}
}
