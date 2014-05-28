package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerOffice extends Remote {

	/**
	 * Method used by clients to register on the server
	 * @param client The client
	 * @throws RemoteException
	 */
	void addObserver(IClientSignal client) throws RemoteException; 

}
