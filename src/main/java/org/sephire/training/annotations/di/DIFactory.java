package org.sephire.training.annotations.di;

import static java.lang.System.out;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;
import org.reflections.Reflections;

public class DIFactory {

  private Set<Class<?>> injectableComponents;


  public DIFactory(Package packageToScan) {
    Date initDate = new Date();
    out.println("============================");
    out.println("Initializing DI Factory for " + packageToScan + "...");
    Reflections reflections = new Reflections(packageToScan);
    this.injectableComponents = reflections.getTypesAnnotatedWith(Component.class);
    long initialisingTime = new Date().getTime() - initDate.getTime();
    out.println("Done. Took " + initialisingTime + "ms to build.");
    out.println("============================");
  }

  public <T> T getBean(Class<T> beanClass) {
    out.println("Building bean " + beanClass + "...");
    if (!injectableComponents.contains(beanClass)) {
      throw new IllegalArgumentException(
          "The bean " + beanClass.getName() + " is not a component in the DI Factory");
    }

    Constructor<?> constructor = beanClass.getConstructors()[0];
    Object[] parameters = Arrays.stream(constructor.getParameters())
        .map((parameter) -> getBean(parameter.getType()))
        .toArray();

    T bean;
    try {
      bean = (T) constructor.newInstance(parameters);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(
          "Could not instantiate the bean " + beanClass.getName() + ": " + e.getMessage(), e);
    }

    out.println("Done building bean " + beanClass + ".");
    return bean;
  }

}
