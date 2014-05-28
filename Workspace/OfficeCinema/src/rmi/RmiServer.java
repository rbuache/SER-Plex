package rmi;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;
import java.util.Observer;

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
	
}
