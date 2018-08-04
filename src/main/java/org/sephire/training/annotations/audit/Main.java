package org.sephire.training.annotations.audit;

public class Main {

  public static void main(String[] args) {
    GreetingService greetingService = new GreetingService();
    System.out.println("The greeting is: " + greetingService.getGreetingWithName("Loïc"));

    GreetingService auditedGreetingService = AuditFactory.get(GreetingService.class);
    System.out
        .println("The audited greeting is: " + auditedGreetingService.getGreetingWithName("Loïc"));
  }
}
