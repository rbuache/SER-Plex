package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import org.jdom2.Document;

public interface IServerOffice extends Remote {

	/**
	 * Method used by clients to register on the server
	 * @param client The client
	 * @throws RemoteException
	 */
	void addObserver(IClientSignal client) throws RemoteException; 

	Document[] getXml() throws RemoteException;
}
