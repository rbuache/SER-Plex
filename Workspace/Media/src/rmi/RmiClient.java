package rmi;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
public class RmiClient {

	public RmiClient(){
	RmiMethod obj;
	try {
		obj = (RmiMethod)Naming.lookup("//localhost/RmiServer");
		System.out.println(obj.getMessage()); 
	} catch (MalformedURLException | RemoteException | NotBoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
	}
}
