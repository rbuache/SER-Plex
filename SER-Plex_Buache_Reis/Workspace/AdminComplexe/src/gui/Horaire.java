package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import org.jdom2.Element;

import launcher.Launcher;

@SuppressWarnings("serial")
public class Horaire extends JPanel{

	JComboBox<String> movieDispo = new JComboBox<>();
	JComboBox<String> movieSchedule = new JComboBox<>();
	JTable schedule = new JTable();
	JButton addMovie = new JButton("Ajouter film");
	JButton delMovie = new JButton("Supprimer film");
	JButton addSchedule = new JButton("Ajouter Schedule");
	JButton delSchedule = new JButton("Supprimer Schedule");
	
	public Horaire(){
		
		//this.setLayout(new GridLayout(0, 1));

		add(new JLabel("Film disponible:"));
		add(movieDispo);
		add(new JLabel("Film à l'affiche:"));
		add(movieSchedule);
		add(addMovie);
		add(delMovie);
		add(addSchedule);
		add(delSchedule);
		JScrollPane sc = new JScrollPane(schedule);
		add(sc);
		
		updateMovies();
		updatePlanification();
		
		addMovie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String select = (String)movieDispo.getSelectedItem();
				Launcher.planification.addPlanification(select);
				updatePlanification();
			}
		});
		delMovie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String select = (String)movieSchedule.getSelectedItem();
				if(select != null){
					Launcher.planification.suppPlanification(select);
					updatePlanification();
				}
			}
		});
		movieSchedule.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateSchedule();
			}
		});
		addSchedule.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Launcher.planification.addSchedule((String)movieSchedule.getSelectedItem());
				updateSchedule();
			}
		});
		delSchedule.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(schedule.getSelectedRow() >= 0){
					Launcher.planification.delSchedule((Element)schedule.getModel().getValueAt(schedule.getSelectedRow(), 4));
				}
				updateSchedule();
			}
		});

		updateSchedule();
	}
	private void updateMovies(){
		movieDispo.removeAllItems();
		for(String s : Launcher.planification.getMovies()){
			movieDispo.addItem(s);
		}
	}
	private void updatePlanification(){
		movieSchedule.removeAllItems();
		for(String s : Launcher.planification.getPlanification()){
			movieSchedule.addItem(s);
		}
	}
	private void updateSchedule(){
		String movie = (String)movieSchedule.getSelectedItem();
		schedule.setModel(new DefaultTableModel(Launcher.planification.getSchedule(movie),new String[]{"Site","Salle","Date","Heure","Element"}));
		schedule.removeColumn(schedule.getColumnModel().getColumn(4));
		schedule.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent arg0) {
				Launcher.planification.updateSchedule(
						(Element)Horaire.this.schedule.getModel().getValueAt(arg0.getFirstRow(), 4),
						new String[]{
						(String)Horaire.this.schedule.getModel().getValueAt(arg0.getFirstRow(),0),
						(String)Horaire.this.schedule.getModel().getValueAt(arg0.getFirstRow(),1),
						(String)Horaire.this.schedule.getModel().getValueAt(arg0.getFirstRow(),2),
						(String)Horaire.this.schedule.getModel().getValueAt(arg0.getFirstRow(),3)
				});
			}
		});
	}
}
