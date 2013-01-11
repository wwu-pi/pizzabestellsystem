package de.pizza.domain;

import java.util.Calendar;
import java.util.Date;

public class Zeitgeber {

  public interface Strategy {
    Date getZeit();
  }

  private final class DefaultStrategy implements Strategy {
    @Override
    public Date getZeit() {
      return Calendar.getInstance().getTime();
    }
  }

  private static Strategy strategy = new Zeitgeber().new DefaultStrategy();

  public static void setStrategy(Strategy strategy) {
    Zeitgeber.strategy = strategy;
  }

  public static void resetStrategy() {
    Zeitgeber.strategy = new Zeitgeber().new DefaultStrategy();
  }

  public static Date getAktuelleZeit() {
    return strategy.getZeit();
  }

}
