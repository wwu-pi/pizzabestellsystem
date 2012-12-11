package de.herrbert.pizza.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import static de.herrbert.pizza.views.UiUtils.*;
import static java.awt.GridBagConstraints.*;

import de.herrbert.pizza.domain.Bestellung;
import de.herrbert.pizza.views.command.ZurUebersichtGehenCommand;
import de.herrbert.pizza.views.command.CommandListener;

public class Bestellerfassung extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Bestellung bestellung;

	private JTextArea inhalt;

	private JTextArea lieferhinweis;

	private JButton abschliessen;

	private CommandListener commandListener;

	public Bestellerfassung(Bestellung bestellung, CommandListener commandListener) {
		this.bestellung = bestellung;
		this.commandListener = commandListener;
		initialize();
	}

	private void initialize() {
		setTitle("Bestelldaten");
		setLayout(new BorderLayout());
		
		{
			GridBagLayout gridBagLayout = new GridBagLayout();
			JPanel datenPanel = new JPanel(gridBagLayout);
			
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.fill = BOTH;
			constraints.insets = new Insets(2, 5, 2, 5);
			
			constraints.gridwidth = RELATIVE;
			datenPanel.add(new JLabel("Zeit"), constraints);
			
			constraints.gridwidth = REMAINDER;
			JTextArea zeit = new JTextArea(getFormatierteZeit(), 1, 30);
			zeit.setEnabled(false);
			datenPanel.add(wrap(zeit), constraints);
			
			constraints.gridwidth = RELATIVE;
			datenPanel.add(new JLabel("Kunde"), constraints);
			
			constraints.gridwidth = REMAINDER;
			JTextArea kunde = new JTextArea("Max Mustermann");
			kunde.setEnabled(false);
			datenPanel.add(wrap(kunde), constraints);
			
			constraints.gridwidth = RELATIVE;
			datenPanel.add(new JLabel("Adresse"), constraints);
			
			constraints.gridwidth = REMAINDER;
			JTextArea adresse = new JTextArea("Schlossplatz 2\n48149 Münster");
			adresse.setEnabled(false);
			datenPanel.add(wrap(adresse), constraints);
			
			constraints.gridwidth = RELATIVE;
			datenPanel.add(new JLabel("Bestellung"), constraints);
			
			constraints.gridwidth = REMAINDER;
			inhalt = new JTextArea(6, 1);
			disableTabInsertion(inhalt);
			datenPanel.add(wrap(inhalt), constraints);
			
			constraints.gridwidth = RELATIVE;
			datenPanel.add(new JLabel("Lieferhinweis"), constraints);
			
			constraints.gridwidth = REMAINDER;
			lieferhinweis = new JTextArea(bestellung.getLieferhinweis(), 3, 2);
			disableTabInsertion(lieferhinweis);
			datenPanel.add(wrap(lieferhinweis), constraints);
			
			add(BorderLayout.CENTER, datenPanel);
		}
		
		{
			JPanel controlPanel = new JPanel(new BorderLayout(), true);
			add(controlPanel, BorderLayout.SOUTH);
			
			abschliessen = new JButton("Bestellung abschließen");
			getRootPane().setDefaultButton(abschliessen);
			Component[] components = { abschliessen };
			controlPanel.add(flowLayoutPanelWith(components), BorderLayout.WEST);
			
			JButton abbrechen = new JButton("abbrechen");
			Component[] components1 = { abbrechen };
			controlPanel.add(flowLayoutPanelWith(components1), BorderLayout.EAST);
		}
		
		pack();
		Dimension minimumSize = getSize();
		setMinimumSize(minimumSize);
		setLocationRelativeTo(null);
	}

	private String getFormatierteZeit() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm (dd.MM.yyyy)");
		return formatter.format(bestellung.getZeit());
	}

	
}
