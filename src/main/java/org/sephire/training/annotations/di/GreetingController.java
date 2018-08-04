package org.sephire.training.annotations.di;

import java.util.Locale;

@Component
public class GreetingController {

  private GreetingService greetingService;

  public GreetingController(GreetingService greetingService) {
    this.greetingService = greetingService;
  }

  public String getGreeting(String name, String locale) {
    String httpResponse = new StringBuilder()
        .append("HTTP/1.1 200 OK\n\n")
        .append(greetingService.getGreetingMessageForName(name, Locale.FRANCE))
        .toString();

    return httpResponse;
  }

}
