package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiMethod extends Remote{
	 public String getMessage() throws RemoteException;
}
