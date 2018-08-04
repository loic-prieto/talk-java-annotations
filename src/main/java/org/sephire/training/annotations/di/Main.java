package org.sephire.training.annotations.di;

public class Main {

  public static void main(String[] args) {

    DIFactory diFactory = new DIFactory(Main.class.getPackage());

    GreetingController greetingController = diFactory.getBean(GreetingController.class);
    System.out.println("\n\n\n\n");
    System.out.println(greetingController.getGreeting("Loïc", "es"));
  }
}
