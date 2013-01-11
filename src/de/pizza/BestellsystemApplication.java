package de.pizza;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Set;

import de.pizza.controller.Pizzeria;
import de.pizza.domain.Kunde;
import de.pizza.domain.serializer.KundenQuelle;
import de.pizza.domain.serializer.KundenQuelleImpl;
import de.pizza.views.Bestelluebersicht;
import de.pizza.views.GuiHandler;
import de.pizza.views.GuiHandlerImpl;
import de.pizza.views.WindowClosingHandler;
import de.pizza.views.command.Command;
import de.pizza.views.command.CommandHandler;

public class BestellsystemApplication implements CommandHandler {
  private Pizzeria pizzeria;
  private GuiHandler guiHandler;

  public BestellsystemApplication(Pizzeria pizzeria, GuiHandler guiHandler) {
    this.pizzeria = pizzeria;
    this.guiHandler = guiHandler;
  }

  private void start() {
    guiHandler.wechseleZu(new Bestelluebersicht(pizzeria, this));
  }

  @Override
  public void process(Command command) {
    command.execute(pizzeria, this, guiHandler);
  }

  public static void main(String[] args) {
    Pizzeria pizzeria = ladePizzeriaFallsVorhanden();
    GuiHandler guiHandler = new GuiHandlerImpl(new WindowClosingHandler(pizzeria));
    BestellsystemApplication app = new BestellsystemApplication(pizzeria, guiHandler);
    app.start();
  }

  private static Pizzeria ladePizzeriaFallsVorhanden() {
    File kundenDatei = new File("kunden.dat");
    if (kundenDatei.exists()) {
      try {
        return new Pizzeria(new KundenQuelleImpl(new ObjectInputStream(new FileInputStream(kundenDatei))));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return pizzeriaMitFuenfKunden();
  }

  private static Pizzeria pizzeriaMitFuenfKunden() {
    final Pizzeria pizzeria = new Pizzeria(new KundenQuelle() {
      @Override
      public Set<Kunde> ladeKunden() {
        Set<Kunde> kunden = new HashSet<>();
        for (int i = 0; i < 5; i++) {
          Kunde kunde = new Kunde("" + i);
          kunde.setName("" + i + ". Kunde");
          kunde.bestellerfassungStarten().bestellungAufnehmen();
          kunden.add(kunde);
        }
        return kunden;
      }
    });
    return pizzeria;
  }

}
