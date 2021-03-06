package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.jdom2.Element;

import launcher.Launcher;

@SuppressWarnings("serial")
public class Site extends JPanel{

	String[] coloneSite = {"Nom",
            "Adresse",
            "Telephone"};
	
	String[] coloneRoom = {"Nom",
            "Nombre de si�ge"};
	
	JTable site = new JTable();
	JTable room = new JTable();
	JButton addSite = new JButton("Ajouter");
	JButton delSite = new JButton("Supprimer");
	JButton addRoom = new JButton("Ajouter");
	JButton delRoom = new JButton("Supprimer");
	
	public Site(){
		
		site.setSize(300, 50);
		
		site.setVisible(true);
		this.setLayout(new BoxLayout(this, 1));
		this.add(new JLabel("Les diff�rents sites du complexe :"),BorderLayout.NORTH);
		
		JScrollPane spSite = new JScrollPane(site);
		this.add(spSite);
		this.add(addSite);
		this.add(delSite);
		
		this.add(new JLabel("Les salles du site s�lectionn� :"),BorderLayout.CENTER);
		JScrollPane spRoom = new JScrollPane(room);
		this.add(spRoom);
		this.add(addRoom);
		this.add(delRoom);
		
		addSite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Launcher.location.createLocation();
				DefaultTableModel tmp = new DefaultTableModel(Launcher.location.getLocations(),new String[]{"Site","Adresse","T�l�phone","node"});
								
				tmp.addTableModelListener(new TableModelListener() {
					
					@Override
					public void tableChanged(TableModelEvent arg0) {
						Launcher.location.updateLocation((Element)Site.this.site.getModel().getValueAt(arg0.getFirstRow(), 3), new String[]{
								(String)Site.this.site.getModel().getValueAt(arg0.getFirstRow(),0),
								(String)Site.this.site.getModel().getValueAt(arg0.getFirstRow(),1),
								(String)Site.this.site.getModel().getValueAt(arg0.getFirstRow(),2)
						});
					}
				});
				
				Site.this.site.setModel(tmp);
				Site.this.site.removeColumn(site.getColumnModel().getColumn(3));

			}
		});
		delSite.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(site.getSelectedRow() >= 0){
				Launcher.location.deleteLocation((Element)site.getModel().getValueAt(site.getSelectedRow(), 3));
				DefaultTableModel tmp = new DefaultTableModel(Launcher.location.getLocations(),new String[]{"Site","Adresse","T�l�phone","node"});
								
				tmp.addTableModelListener(new TableModelListener() {
					
					@Override
					public void tableChanged(TableModelEvent arg0) {
						Launcher.location.updateLocation((Element)Site.this.site.getModel().getValueAt(arg0.getFirstRow(), 3), new String[]{
								(String)Site.this.site.getModel().getValueAt(arg0.getFirstRow(),0),
								(String)Site.this.site.getModel().getValueAt(arg0.getFirstRow(),1),
								(String)Site.this.site.getModel().getValueAt(arg0.getFirstRow(),2)
						});
					}
					
				});
				
				
				Site.this.site.setModel(tmp);
				Site.this.site.removeColumn(site.getColumnModel().getColumn(3));
				}
			}
		});
		addRoom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(site.getSelectedRow() >= 0){
					Element e = (Element)site.getModel().getValueAt(site.getSelectedRow(), 3);
					Launcher.location.createRoom(e);
					Site.this.room.setModel((new DefaultTableModel(Launcher.location.getRooms(e),new String[]{"Nom","Nombre de place","node"})));
					room.removeColumn(room.getColumnModel().getColumn(2));
					
					room.getModel().addTableModelListener(new TableModelListener() {
						
						@Override
						public void tableChanged(TableModelEvent arg0) {
							Launcher.location.updateRoom((Element)Site.this.room.getModel().getValueAt(arg0.getFirstRow(), 2), new String[]{
									(String)Site.this.room.getModel().getValueAt(arg0.getFirstRow(),0),
									(String)Site.this.room.getModel().getValueAt(arg0.getFirstRow(),1),
							});
						}
					});
				}
				
			}
		});
		delRoom.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(room.getSelectedRow() >= 0){
					
					Launcher.location.deleteRoom((Element)room.getModel().getValueAt(room.getSelectedRow(), 2));
					DefaultTableModel tmp = new DefaultTableModel(Launcher.location.getRooms((Element)room.getModel().getValueAt(room.getSelectedRow(),2)),new String[]{"Nom","Nombre de place","node"});
									
					tmp.addTableModelListener(new TableModelListener() {
						
						@Override
						public void tableChanged(TableModelEvent arg0) {
							Launcher.location.updateRoom((Element)Site.this.room.getModel().getValueAt(arg0.getFirstRow(), 3), new String[]{
									(String)Site.this.room.getModel().getValueAt(arg0.getFirstRow(),0),
									(String)Site.this.room.getModel().getValueAt(arg0.getFirstRow(),1)
							});
						}
						
					});
					
					
					Site.this.room.setModel(tmp);
					Site.this.room.removeColumn(room.getColumnModel().getColumn(2));
				}
			}
		});
				
		site.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		room.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	
		site.setModel(new DefaultTableModel(Launcher.location.getLocations(),new String[]{"Site","Adresse","T�l�phone","node"}));
		site.removeColumn(site.getColumnModel().getColumn(3));
		
		room.setModel(new DefaultTableModel(new Object[0][2],new String[]{"Nom","Nombre de place","node"}));
		room.removeColumn(room.getColumnModel().getColumn(2));
		
		site.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent arg0) {
				Launcher.location.updateLocation((Element)Site.this.site.getModel().getValueAt(arg0.getFirstRow(), 3), new String[]{
						(String)Site.this.site.getModel().getValueAt(arg0.getFirstRow(),0),
						(String)Site.this.site.getModel().getValueAt(arg0.getFirstRow(),1),
						(String)Site.this.site.getModel().getValueAt(arg0.getFirstRow(),2)
				});
			}
		});
		
		site.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				int index = ((DefaultListSelectionModel)arg0.getSource()).getMinSelectionIndex();
				if(index >= 0){
					Element e = (Element)Site.this.site.getModel().getValueAt(index, 3);
					Site.this.room.setModel((new DefaultTableModel(Launcher.location.getRooms(e),new String[]{"Nom","Nombre de place","node"})));
					room.removeColumn(room.getColumnModel().getColumn(2));
					
					room.getModel().addTableModelListener(new TableModelListener() {
						
						@Override
						public void tableChanged(TableModelEvent arg0) {
							Launcher.location.updateRoom((Element)Site.this.room.getModel().getValueAt(arg0.getFirstRow(), 2), new String[]{
									(String)Site.this.room.getModel().getValueAt(arg0.getFirstRow(),0),
									(String)Site.this.room.getModel().getValueAt(arg0.getFirstRow(),1),
							});
						}
					});
				}
			}
		});
	}
	

}
