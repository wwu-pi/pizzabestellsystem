package de.pizza.views;

import javax.swing.JFrame;


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
}
