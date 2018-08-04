package org.sephire.training.annotations.di;

import java.util.Locale;

@Component
public class TranslationService {

  public String translate(String word, Locale locale) {
    return "Gutentag";
  }
}
