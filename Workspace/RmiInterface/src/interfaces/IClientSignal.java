package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClientSignal extends Remote {
	public enum Signal {
		UPDATE_REQUESTED
	}
	
	void update(Object observable, Signal signalType, String updateMsg) throws RemoteException;
}
