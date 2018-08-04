package org.sephire.training.annotations.di;

import java.util.Locale;

@Component
public class GreetingService {

  private CountryRepository countryRepository;
  private TranslationService translationService;

  public GreetingService(CountryRepository countryRepository,
      TranslationService translationService) {
    this.countryRepository = countryRepository;
    this.translationService = translationService;
  }

  public String getGreetingMessageForName(String name, Locale locale) {
    return
        translationService.translate("greetings", locale)
            + " from "
            + countryRepository.getCountryForLocale(locale)
            + ", "
            + name;
  }

}
