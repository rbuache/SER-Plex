package gui;

import interfaces.IClientSignal;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JOptionPane;

import launcher.Launcher;

public class SignalOffice  extends UnicastRemoteObject implements IClientSignal,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SignalOffice() throws RemoteException{
		super();
	}
	@Override
	public void update(Object observable, Signal signalType, String updateMsg)
			throws RemoteException {
		if(signalType.equals(Signal.UPDATE_REQUESTED)){
			JOptionPane.showMessageDialog(Launcher.gui, "Movie in internationnal office has been updated!","Info", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
