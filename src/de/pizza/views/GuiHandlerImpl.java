package de.pizza.views;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.views.command.Command;
import de.pizza.views.command.CommandHandler;


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

  public void wechseleZu(Command command, Pizzeria pizzeria, CommandHandler commandHandler) {
    wechseleZu(command.erstelleMaske(pizzeria, commandHandler));
  }
}
