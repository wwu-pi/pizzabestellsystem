package de.pizza.views;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.KeyboardFocusManager;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UiUtils {

  public static JTextArea disableTabInsertion(JTextArea textarea) {
    textarea.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
    textarea.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
    return textarea;
  }

  /**
   * @param component
   * @return component, die von einem {@link JScrollPane} umgeben ist
   */
  public static JScrollPane wrap(Component component) {
    return new JScrollPane(component);
  }

  public static JPanel flowLayoutPanelWith(Component... components) {
    JPanel panel = new JPanel(new FlowLayout());
    for (Component component : components) {
      panel.add(component);
    }
    return panel;
  }
}
