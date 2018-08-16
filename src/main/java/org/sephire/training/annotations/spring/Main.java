package org.sephire.training.annotations.spring;

public class Main {

  public static void main(String[] args) {
    PersonController personController = new PersonController();

    Person person = personController.getPerson(1);
    System.out.println("Invoking the controller returns " + person);
  }

}
