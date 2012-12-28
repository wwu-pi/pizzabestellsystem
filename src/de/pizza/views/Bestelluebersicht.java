package de.pizza.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import static de.pizza.views.UiUtils.flowLayoutPanelWith;

import de.pizza.controller.Pizzeria;
import de.pizza.domain.Bestellung;
import de.pizza.views.command.BestellungBearbeitenCommand;
import de.pizza.views.command.CommandHandler;
import de.pizza.views.command.KundenSucheCommand;

public class Bestelluebersicht extends JFrame {
  private static final long serialVersionUID = 1L;

  private final CommandHandler commandHandler;
  private final Pizzeria pizzeria;

  private JList<Bestellung> bestellungen;
  private JButton stornieren;
  private JButton bearbeiten;

  public Bestelluebersicht(Pizzeria pizzeria, CommandHandler commandHandler) {
    this.pizzeria = pizzeria;
    this.commandHandler = commandHandler;
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
      JButton neueBestellungButton = new JButton("Bestellung aufnehmen");
      neueBestellungButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
          commandHandler.process(new KundenSucheCommand());
        }
      });
      controlPanel.add(flowLayoutPanelWith(neueBestellungButton), BorderLayout.EAST);
      getRootPane().setDefaultButton(neueBestellungButton);
    }

    {
      {
        bearbeiten = new JButton("bearbeiten");
        bearbeiten.setEnabled(false);
        bearbeiten.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            Bestellung ausgewaehlteBestellung = bestellungen.getSelectedValue();
            commandHandler.process(new BestellungBearbeitenCommand(ausgewaehlteBestellung));
          }
        });
      }

      {
        stornieren = new JButton("stornieren");
        stornieren.setEnabled(false);
        final JFrame thisFrame = this;
        stornieren.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if (confirmStornieren()) {
              return;
            }
            bestellungen.getSelectedValue().stornieren();
            bestellungen.setModel(getBestellungenModel());
            stelleKorrektenButtonZustandSicher();
          }

          private boolean confirmStornieren() {
            Object[] options = { "Stornieren", "Abbrechen" };
            int userChoice = JOptionPane.showOptionDialog(thisFrame,
                "Bestellung stornieren?", "Bestellung stornieren?",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[1]);
            return userChoice == 1;
          }
        });
      }

      controlPanel.add(flowLayoutPanelWith(bearbeiten, stornieren), BorderLayout.WEST);
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
    stornieren.setEnabled(buttonAktivieren);
    bearbeiten.setEnabled(buttonAktivieren);
  }

  private boolean sindBestellungenVorhanden() {
    return bestellungen.getModel().getSize() >= 0;
  }

  private boolean istEineBestellungAusgewaehlt() {
    return bestellungen.getSelectedIndex() != -1;
  }
}
