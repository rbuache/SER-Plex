package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.jdom2.Document;

public interface IServerAdmin  extends Remote {

	Document getHoraire() throws RemoteException;
}
