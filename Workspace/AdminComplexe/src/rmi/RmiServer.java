package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer  extends UnicastRemoteObject implements RmiMethod{

	public RmiServer() throws RemoteException {
		super(0);
		
		System.out.println("RMI server started");
		 
        try { //special exception handler for registry creation
            LocateRegistry.createRegistry(1099); 
            System.out.println("java RMI registry created.");
        } catch (RemoteException e) {
            //do nothing, error means registry already exists
            System.out.println("java RMI registry already exists.");
        }
        // Bind this object instance to the name "RmiServer"
        try {
			Naming.rebind("//localhost/RmiServer", this);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("PeerServer bound in registry");
        
	}

	@Override
	public String getMessage() throws RemoteException {
		return "ça marche!!";
	}

}
