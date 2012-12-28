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

import static de.pizza.views.UiUtils.disableTabInsertion;
import static de.pizza.views.UiUtils.flowLayoutPanelWith;
import static de.pizza.views.UiUtils.wrap;
import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.RELATIVE;
import static java.awt.GridBagConstraints.REMAINDER;

import de.pizza.domain.BestellDaten;
import de.pizza.views.command.BestellerfassungAbgebrochenCommand;
import de.pizza.views.command.BestellerfassungAbgeschlossenCommand;
import de.pizza.views.command.CommandHandler;

public class Bestellungserfassung extends JFrame {
	private static final long serialVersionUID = 1L;

  protected BestellDaten bestellDaten;
  protected CommandHandler commandHandler;

  private JTextArea inhalt;
  private JTextArea lieferhinweis;

  public Bestellungserfassung(BestellDaten bestellDaten, CommandHandler commandHandler) {
    this.bestellDaten = bestellDaten;
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
      JTextArea zeit = new JTextArea(bestellDaten.getZeitAsString(), 1, 30);
      zeit.setEnabled(false);
      datenPanel.add(wrap(zeit), constraints);

      constraints.gridwidth = RELATIVE;
      datenPanel.add(new JLabel("Kunde"), constraints);

      constraints.gridwidth = REMAINDER;
      JTextArea kunde = new JTextArea(bestellDaten.getKundenname());
      kunde.setEnabled(false);
      datenPanel.add(wrap(kunde), constraints);

      constraints.gridwidth = RELATIVE;
      datenPanel.add(new JLabel("Adresse"), constraints);

      constraints.gridwidth = REMAINDER;
      JTextArea adresse = new JTextArea(bestellDaten.getKundenadresse());
      adresse.setEnabled(false);
      datenPanel.add(wrap(adresse), constraints);

      constraints.gridwidth = RELATIVE;
      datenPanel.add(new JLabel("Bestellung"), constraints);

      constraints.gridwidth = REMAINDER;
      inhalt = new JTextArea(bestellDaten.getInhalt(), 6, 1);
      disableTabInsertion(inhalt);
      datenPanel.add(wrap(inhalt), constraints);

      constraints.gridwidth = RELATIVE;
      datenPanel.add(new JLabel("Lieferhinweis"), constraints);

      constraints.gridwidth = REMAINDER;
      lieferhinweis = new JTextArea(bestellDaten.getLieferhinweis(), 3, 2);
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
          bestellDaten.setInhalt(inhalt.getText());
          bestellDaten.setLieferhinweis(lieferhinweis.getText());
          commandHandler.process(new BestellerfassungAbgeschlossenCommand(bestellDaten));
        }
      });

      JButton abbrechen = new JButton("abbrechen");
      abbrechen.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
          commandHandler.process(new BestellerfassungAbgebrochenCommand());
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
