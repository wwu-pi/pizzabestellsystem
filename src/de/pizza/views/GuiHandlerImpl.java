package de.pizza.views;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;

public class GuiHandlerImpl implements GuiHandler {
  private final Pizzeria pizzeria;

  private JFrame aktuelleMaske = new JFrame("empty");

  public GuiHandlerImpl(Pizzeria pizzeria) {
    this.pizzeria = pizzeria;
  }

  public void wechseleZu(JFrame neuerMaske) {
    aktuelleMaske.setVisible(false);
    aktuelleMaske.dispose();

    aktuelleMaske = neuerMaske;

    aktuelleMaske.setLocationRelativeTo(null);
    aktuelleMaske.addWindowListener(new WindowClosingHandler(pizzeria));
    aktuelleMaske.setVisible(true);
  }
}
