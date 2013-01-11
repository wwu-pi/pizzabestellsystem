package de.pizza.views;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.views.command.CommandHandler;
import de.pizza.views.command.MaskenErsteller;


public class GuiHandlerImpl implements GuiHandler {

  private JFrame aktuelleMaske = new JFrame("empty");

  private WindowClosingHandler windowClosingHandler;

  public GuiHandlerImpl(WindowClosingHandler windowClosingHandler) {
    this.windowClosingHandler = windowClosingHandler;
  }

  public void wechseleZu(JFrame neuerMaske) {
    aktuelleMaske.setVisible(false);
    aktuelleMaske.dispose();

    aktuelleMaske = neuerMaske;

    aktuelleMaske.setLocationRelativeTo(null);
    aktuelleMaske.addWindowListener(windowClosingHandler);
    aktuelleMaske.setVisible(true);
  }

  public void wechseleZu(MaskenErsteller maskenErsteller, Pizzeria pizzeria, CommandHandler commandHandler) {
    wechseleZu(maskenErsteller.erstelleMaske(pizzeria, commandHandler));
  }
}
