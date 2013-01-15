package de.pizza.views;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.views.command.CommandHandler;
import de.pizza.views.command.MaskenErsteller;

/**
 * Sorgt für einen sauberen Wechsel von einer Maske zur Nächsten
 */
public interface GuiHandler {

  public abstract void wechseleZu(JFrame neuerMaske);

  public abstract void wechseleZu(MaskenErsteller maskenErsteller, Pizzeria pizzeria, CommandHandler commandHandler);

}
