package org.sephire.training.annotations.di;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class CountryRepository {

  private Map<Locale, String> localeToStringMap;

  public CountryRepository() {
    localeToStringMap = new HashMap<>();
    localeToStringMap.put(Locale.UK, "England");
    localeToStringMap.put(Locale.US, "United States");
    localeToStringMap.put(Locale.CANADA, "Canada");
    localeToStringMap.put(Locale.FRANCE, "France");
  }

  public String getCountryForLocale(Locale locale) {
    return localeToStringMap.get(locale);
  }
}
