package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import launcher.Launcher;

@SuppressWarnings("serial")
public class Media extends JPanel{

	JButton generer = new JButton("Generer");
	public Media(){
		add(generer);
		
		generer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!Launcher.media.transform()){
					JOptionPane.showMessageDialog(Launcher.gui, "Error during transform","Crash", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
}
