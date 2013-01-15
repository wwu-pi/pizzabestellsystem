package de.pizza.views.command;

/**
 * Die implementierende Klasse führt die übergebenen {@link Command} per
 * {@link Command#execute(de.pizza.controller.Pizzeria, de.pizza.views.GuiHandler)}
 * aus
 */
public interface CommandHandler {

  void process(Command command);

}
