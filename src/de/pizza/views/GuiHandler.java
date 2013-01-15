package de.pizza.views;

import javax.swing.JFrame;

import de.pizza.views.command.MaskenErsteller;

/**
 * Sorgt für einen sauberen Wechsel von einer Maske zur Nächsten
 */
public interface GuiHandler {

  public abstract void wechseleZu(JFrame neuerMaske);

  public abstract void wechseleZuMaskeVon(MaskenErsteller ersteller);

}
