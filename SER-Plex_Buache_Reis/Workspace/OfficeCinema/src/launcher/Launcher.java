package launcher;

import interfaces.IServerOffice;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import gui.Main;
import rmi.RmiServer;

public class Launcher {

	public static RmiServer rmi;
	public static Main gui;
	
	public Launcher(){
		rmi = new RmiServer();
	}
	public static void main(String[] args){
		new Launcher();
		
        try {
            Registry rmiRegistry = LocateRegistry.createRegistry(9999);
            IServerOffice rmiService = (IServerOffice) UnicastRemoteObject.exportObject(rmi, 9999);
            rmiRegistry.bind("RmiService", rmiService);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        gui = new Main();
	}
}
