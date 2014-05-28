package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import interfaces.IClientSignal;
import interfaces.IServerOffice;
import launcher.Launcher;

public class RmiClient {
	
	public RmiClient(IClientSignal asker) throws RemoteException {
		super();
		
		//we connect to server
		IServerOffice remoteService = null;
		try {
			remoteService = (IServerOffice) Naming.lookup("//localhost:9999/RmiService");
		} catch (MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		remoteService.addObserver(asker);
	}
}
