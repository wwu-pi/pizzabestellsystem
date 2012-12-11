package de.herrbert.pizza.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import de.herrbert.pizza.controller.Pizzeria;
import de.herrbert.pizza.domain.Bestellung;
import de.herrbert.pizza.views.command.BestellungBearbeitenCommand;
import de.herrbert.pizza.views.command.CommandListener;

public class BestellUebersicht extends JFrame {
	private static final long serialVersionUID = 1L;

	private CommandListener commandListener;
	
	private Pizzeria pizzeria;

	private JList<Bestellung> bestellungen;
	private JButton loeschen;
	private JButton bearbeiten;
	
	BestellUebersicht(Pizzeria pizzeria, CommandListener commandListener) {
		this.pizzeria = pizzeria;
		this.commandListener = commandListener;
		initialize();
	}

	private void initialize() {
		setTitle("Bestellübersicht");
		setLayout(new BorderLayout());
		
		JPanel controlPanel = new JPanel(new BorderLayout(), true);
		add(controlPanel, BorderLayout.SOUTH);
		
		{
			bestellungen = new JList<Bestellung>(getBestellungenModel());
			bestellungen.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			bestellungen.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent event) {
					stelleKorrektenButtonZustandSicher();
				}
			});
			JScrollPane bestellungenPanel = new JScrollPane(bestellungen);
			add(bestellungenPanel, BorderLayout.CENTER);
		}
		
		{
			JPanel neueBestellungPanel = new JPanel(new FlowLayout());
			JButton neueBestellungButton = new JButton("Bestellung aufnehmen");
			neueBestellungPanel.add(neueBestellungButton);
			controlPanel.add(neueBestellungPanel, BorderLayout.WEST);
			getRootPane().setDefaultButton(neueBestellungButton);
		}
		
		{
			JPanel bestellungsAenderungen = new JPanel(new FlowLayout());
			{
				bearbeiten = new JButton("bearbeiten");
				bearbeiten.setEnabled(false);
				bearbeiten.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Bestellung ausgewaehlteBestellung = bestellungen.getSelectedValue();
						System.out.println(ausgewaehlteBestellung);
						commandListener.process(new BestellungBearbeitenCommand(ausgewaehlteBestellung));
					}
				});
				bestellungsAenderungen.add(bearbeiten);
			}
			
			{
				loeschen = new JButton("stornieren");
				loeschen.setEnabled(false);
				loeschen.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						bestellungen.getSelectedValue().stornieren();
						bestellungen.setModel(getBestellungenModel());
						stelleKorrektenButtonZustandSicher();
					}
				});
				bestellungsAenderungen.add(loeschen);
			}
			
			controlPanel.add(bestellungsAenderungen, BorderLayout.EAST);
		}
		
		pack();
		Dimension minimumSize = getSize();
		setMinimumSize(minimumSize);
		setLocationRelativeTo(null);
	}

	private DefaultListModel<Bestellung> getBestellungenModel() {
		DefaultListModel<Bestellung> bestellungenModel = new DefaultListModel<>();
		for (Bestellung bestellung : pizzeria.getAlleBestellungen()) {
			bestellungenModel.addElement(bestellung);
		}
		return bestellungenModel;
	}

	private void stelleKorrektenButtonZustandSicher() {
		boolean buttonAktivieren = (sindBestellungenVorhanden() && istEineBestellungAusgewaehlt());
		loeschen.setEnabled(buttonAktivieren);
		bearbeiten.setEnabled(buttonAktivieren);
	}

	private boolean sindBestellungenVorhanden() {
		return bestellungen.getModel().getSize() >= 0;
	}

	private boolean istEineBestellungAusgewaehlt() {
		return bestellungen.getSelectedIndex() != -1;
	}
}
