package de.pizza.views;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import de.pizza.controller.Pizzeria;
import de.pizza.domain.serializer.KundenSenkeImpl;

final class WindowClosingHandler extends WindowAdapter {
  private final Pizzeria pizzeria;

  WindowClosingHandler(Pizzeria pizzeria) {
    this.pizzeria = pizzeria;
  }

  @Override
  public void windowClosing(WindowEvent e) {
    try {
      pizzeria.persistiereKunden(new KundenSenkeImpl(new ObjectOutputStream(new FileOutputStream("kunden.dat"))));
      System.exit(0);
    } catch (IOException exception) {
      exception.printStackTrace();
      System.exit(1);
    }
  }
}