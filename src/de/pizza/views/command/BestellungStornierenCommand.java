package de.pizza.views.command;

import de.pizza.domain.Bestellung;
import de.pizza.views.GuiHandler;

public class BestellungStornierenCommand implements Command {

  private Bestellung bestellung;

  public BestellungStornierenCommand(Bestellung bestellung) {
    this.bestellung = bestellung;
  }

  @Override
  public void execute(GuiHandler guiHandler) {
    bestellung.stornieren();
  }

}
