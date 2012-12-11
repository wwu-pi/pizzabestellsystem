package de.herrbert.pizza.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import de.herrbert.pizza.controller.Pizzeria;
import de.herrbert.pizza.domain.Bestellung;
import de.herrbert.pizza.domain.serializer.KundenSerialisiererImpl;

public class Bestelluebersicht extends JFrame {
	private Pizzeria pizzeria;
	private JButton loeschen;
	private JButton bearbeiten;
	
	Bestelluebersicht(Pizzeria pizzeria) {
		this.pizzeria = pizzeria;
		initialize();
	}

	private void initialize() {
		setTitle("Bestellübersicht");
		
		JPanel mainPanel = new JPanel(new BorderLayout(), true);
		JPanel controlPanel = new JPanel(new BorderLayout(), true);
		mainPanel.add(controlPanel, BorderLayout.SOUTH);
		add(mainPanel);
		
		DefaultListModel<Bestellung> bestellungenModel = new DefaultListModel<>();
		for (Bestellung bestellung : pizzeria.getAlleBestellungen()) {
			bestellungenModel.addElement(bestellung);
		}
		JList<Bestellung> bestellungen = new JList<Bestellung>(bestellungenModel);
		JScrollPane bestellungenPanel = new JScrollPane(bestellungen);
		mainPanel.add(bestellungenPanel, BorderLayout.CENTER);
		
		{
			JPanel neueBestellungPanel = new JPanel(new FlowLayout());
			JButton neueBestellungButton = new JButton("Bestellung aufnehmen");
			neueBestellungPanel.add(neueBestellungButton);
			controlPanel.add(neueBestellungPanel, BorderLayout.WEST);
		}
		
		{
			JPanel bestellungsAenderungen = new JPanel(new FlowLayout());
			{
				bearbeiten = new JButton("bearbeiten");
				bearbeiten.setEnabled(false);
				bestellungsAenderungen.add(bearbeiten);
			}
			
			{
				loeschen = new JButton("löschen");
				loeschen.setEnabled(false);
				bestellungsAenderungen.add(loeschen);
			}
			
			controlPanel.add(bestellungsAenderungen, BorderLayout.EAST);
		}
		
		pack();
		Dimension minimumSize = getSize();
		setMinimumSize(minimumSize);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
