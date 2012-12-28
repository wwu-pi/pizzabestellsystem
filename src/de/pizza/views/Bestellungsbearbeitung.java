package de.pizza.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import static de.pizza.views.UiUtils.*;
import static java.awt.GridBagConstraints.*;

import de.pizza.domain.Bestellung;
import de.pizza.views.command.CommandHandler;
import de.pizza.views.command.ZurUebersichtGehenCommand;

public class Bestellungsbearbeitung extends JFrame {
	private static final long serialVersionUID = 1L;
	
	protected Bestellung bestellung;
	protected CommandHandler commandHandler;

	protected JButton abbrechen;

	private JTextArea inhalt;
	private JTextArea lieferhinweis;

	public Bestellungsbearbeitung(Bestellung bestellung, CommandHandler commandHandler) {
		this.bestellung = bestellung;
		this.commandHandler = commandHandler;
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
			JTextArea zeit = new JTextArea(bestellung.getZeitAsString(), 1, 30);
			zeit.setEnabled(false);
			datenPanel.add(wrap(zeit), constraints);
			
			constraints.gridwidth = RELATIVE;
			datenPanel.add(new JLabel("Kunde"), constraints);
			
			constraints.gridwidth = REMAINDER;
			JTextArea kunde = new JTextArea(bestellung.getKundenname());
			kunde.setEnabled(false);
			datenPanel.add(wrap(kunde), constraints);
			
			constraints.gridwidth = RELATIVE;
			datenPanel.add(new JLabel("Adresse"), constraints);
			
			constraints.gridwidth = REMAINDER;
			JTextArea adresse = new JTextArea(bestellung.getKundenadresse());
			adresse.setEnabled(false);
			datenPanel.add(wrap(adresse), constraints);
			
			constraints.gridwidth = RELATIVE;
			datenPanel.add(new JLabel("Bestellung"), constraints);
			
			constraints.gridwidth = REMAINDER;
			inhalt = new JTextArea(bestellung.getInhalt(), 6, 1);
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
			
			JButton abschliessen = new JButton("Bestellung abschließen");
			abschliessen.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					bestellung.setInhalt(inhalt.getText());
					bestellung.setLieferhinweis(lieferhinweis.getText());
					commandHandler.process(new ZurUebersichtGehenCommand());
				}
			});
			
			abbrechen = new JButton("abbrechen");
			abbrechen.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					commandHandler.process(new ZurUebersichtGehenCommand());
				}
			});

			controlPanel.add(flowLayoutPanelWith(abschliessen, abbrechen), BorderLayout.EAST);
			getRootPane().setDefaultButton(abschliessen);
		}
		
		pack();
		Dimension minimumSize = getSize();
		setMinimumSize(minimumSize);
		setLocationRelativeTo(null);
	}
	
}
