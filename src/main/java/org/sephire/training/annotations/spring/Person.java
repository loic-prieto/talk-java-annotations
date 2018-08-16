package org.sephire.training.annotations.spring;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Person {

  private int id;
  private String name;
}
