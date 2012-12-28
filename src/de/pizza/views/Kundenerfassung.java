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
import javax.swing.JTextField;

import static de.pizza.views.UiUtils.disableTabInsertion;
import static de.pizza.views.UiUtils.flowLayoutPanelWith;
import static de.pizza.views.UiUtils.wrap;
import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.RELATIVE;
import static java.awt.GridBagConstraints.REMAINDER;

import de.pizza.domain.Kunde;
import de.pizza.views.command.CommandHandler;
import de.pizza.views.command.NeueBestellungErfassen;
import de.pizza.views.command.ZurUebersichtGehenCommand;

public class Kundenerfassung extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Kunde kunde;
	private CommandHandler commandHandler;

	private JTextArea lieferhinweis;
	private JTextArea adresse;
	private JTextField name;

	public Kundenerfassung(Kunde kunde, CommandHandler commandHandler) {
		this.kunde = kunde;
		this.commandHandler = commandHandler;
		initialize();
	}

	private void initialize() {
		setTitle("Kundendaten");
		setLayout(new BorderLayout());
		
		{
			GridBagLayout gridBagLayout = new GridBagLayout();
			JPanel datenPanel = new JPanel(gridBagLayout);
			
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.fill = BOTH;
			constraints.insets = new Insets(2, 5, 2, 5);
			
			constraints.gridwidth = RELATIVE;
			datenPanel.add(new JLabel("Telefonnummer"), constraints);
			
			constraints.gridwidth = REMAINDER;
			JTextField telefonnummer = new JTextField(kunde.getTelefonnummer(), 30);
			telefonnummer.setEnabled(false);
			datenPanel.add(telefonnummer, constraints);
			
			constraints.gridwidth = RELATIVE;
			datenPanel.add(new JLabel("Name"), constraints);
			
			constraints.gridwidth = REMAINDER;
			name = new JTextField(kunde.getName());
			datenPanel.add(name, constraints);
			
			constraints.gridwidth = RELATIVE;
			datenPanel.add(new JLabel("Adresse"), constraints);
			
			constraints.gridwidth = REMAINDER;
			adresse = new JTextArea(kunde.getAdresse(), 3, 2);
			disableTabInsertion(adresse);
			datenPanel.add(wrap(adresse), constraints);
			
			constraints.gridwidth = RELATIVE;
			datenPanel.add(new JLabel("Lieferhinweis"), constraints);
			
			constraints.gridwidth = REMAINDER;
			lieferhinweis = new JTextArea(kunde.getLieferhinweis(), 3, 2);
			disableTabInsertion(lieferhinweis);
			datenPanel.add(wrap(lieferhinweis), constraints);
			
			add(BorderLayout.CENTER, datenPanel);
		}
		
		{
			JPanel controlPanel = new JPanel(new BorderLayout(), true);
			add(controlPanel, BorderLayout.SOUTH);
			
			JButton abschliessen = new JButton("Bestellung aufnehmen");
			abschliessen.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					kunde.setName(name.getText());
					kunde.setAdresse(adresse.getText());
					kunde.setLieferhinweis(lieferhinweis.getText());
					commandHandler.process(new NeueBestellungErfassen(kunde));
				}
			});
			
			JButton abbrechen = new JButton("abbrechen");
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
