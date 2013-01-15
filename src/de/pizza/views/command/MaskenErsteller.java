package de.pizza.views.command;

import javax.swing.JFrame;

import de.pizza.controller.Pizzeria;
import de.pizza.views.GuiHandler;

/**
 * <p>Die implemtierende Klasse konstruiert eine Maske und liefert sie als
 * {@link JFrame} zurück.</p>
 * 
 * <p>Durch die Entkopplung (Aufruf durch
 * {@link GuiHandler#wechseleZu(MaskenErsteller, Pizzeria, CommandHandler)})
 * werden die Masken nur zur Laufzeit und nicht unnötigerweise in den Tests
 * erstellt. Wenn die Masken bereits in den Tests konstruiert werden, verlängert
 * sich die Testausführung jeweils enorm (Bsp: von 0,003s auf ~0,6s).</p>
 */
public interface MaskenErsteller {

  public JFrame erstelleMaske(Pizzeria pizzeria, CommandHandler commandHandler);

}