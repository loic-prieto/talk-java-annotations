package org.sephire.training.annotations.audit;

public class GreetingService {

  @Audit
  public String getGreetingWithName(String name) {
    return "Hello there, " + name;
  }
}
