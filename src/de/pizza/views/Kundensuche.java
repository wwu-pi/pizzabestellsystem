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
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.pizza.views.command.CommandHandler;
import de.pizza.views.command.KundeBearbeitenCommand;
import de.pizza.views.command.ZurUebersichtGehenCommand;

import static java.awt.GridBagConstraints.*;
import static de.pizza.views.UiUtils.*;

public class Kundensuche extends JFrame {
  private static final long serialVersionUID = 1L;

  private CommandHandler commandListener;

  private JTextField telefonnummer;

  public Kundensuche(CommandHandler commandHandler) {
    this.commandListener = commandHandler;
    initialize();
  }

  private void initialize() {
    setTitle("Kundensuche");
    setLayout(new BorderLayout());

    {
      GridBagLayout gridBagLayout = new GridBagLayout();
      JPanel datenPanel = new JPanel(gridBagLayout);

      GridBagConstraints constraints = new GridBagConstraints();
      constraints.fill = BOTH;
      constraints.insets = new Insets(2, 5, 2, 5);

      constraints.gridwidth = REMAINDER;
      telefonnummer = new JTextField("+49251", 25);
      datenPanel.add(telefonnummer, constraints);

      add(BorderLayout.CENTER, datenPanel);
    }

    {
      JPanel controlPanel = new JPanel(new BorderLayout(), true);
      add(controlPanel, BorderLayout.SOUTH);

      JButton kundeSuchen = new JButton("Kunde suchen");
      kundeSuchen.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
          commandListener.process(new KundeBearbeitenCommand(telefonnummer.getText()));
        }
      });

      JButton abbrechen = new JButton("abbrechen");
      abbrechen.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent event) {
          commandListener.process(new ZurUebersichtGehenCommand());
        }
      });
      controlPanel.add(flowLayoutPanelWith(kundeSuchen, abbrechen), BorderLayout.EAST);
      getRootPane().setDefaultButton(kundeSuchen);
    }

    pack();
    Dimension minimumSize = getSize();
    setMinimumSize(minimumSize);
    setLocationRelativeTo(null);
  }

}
