package de.pizza.views;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.views.command.CommandHandler;
import de.pizza.views.command.MaskenErsteller;


public class GuiHandlerImpl implements GuiHandler {

  private JFrame aktuelleMaske = new JFrame("empty");

  private final WindowClosingHandler windowClosingHandler;
  private final CommandHandler commandHandler;

  private Pizzeria pizzeria;

  public GuiHandlerImpl(Pizzeria pizzeria,
      WindowClosingHandler windowClosingHandler, CommandHandler commandHandler) {
    this.pizzeria = pizzeria;
    this.windowClosingHandler = windowClosingHandler;
    this.commandHandler = commandHandler;
  }

  public void wechseleZu(JFrame neuerMaske) {
    aktuelleMaske.setVisible(false);
    aktuelleMaske.dispose();

    aktuelleMaske = neuerMaske;

    aktuelleMaske.setLocationRelativeTo(null);
    aktuelleMaske.addWindowListener(windowClosingHandler);
    aktuelleMaske.setVisible(true);
  }

  public void wechseleZuMaskeVon(MaskenErsteller ersteller) {
    wechseleZu(ersteller.erstelleMaske(pizzeria, commandHandler));
  }
}
