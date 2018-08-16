package org.sephire.training.annotations.audit;

import java.util.Arrays;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class AuditFactory {

  public static <T> T get(Class<T> auditedClass) {
    // This guy will modify the class to give them audit super powers
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(auditedClass);

    // The audited class will execute the code inside this callback instead
    // of its own code
    enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {

      // We will only modify methods that are marked with the @Audit annotation
      boolean isAuditMethod = Arrays.stream(method.getDeclaredAnnotations())
          .anyMatch(annotation -> {
            return annotation.annotationType().getName().equals(Audit.class.getName());
          });

      // Check wether we should return something from the function
      boolean methodReturnsSomething = !method.getReturnType().equals(Void.TYPE);

      // The class will execute this
      if (isAuditMethod) {
        String argsString = Arrays.stream(args)
            .reduce((arg1, arg2) -> arg1.toString() + ", " + arg2.toString())
            .map((rawObject) -> (String) rawObject)
            .orElseGet(() -> "");

        System.out.println("Method " + method.getName() + " was called with args " + argsString);
      }

      // And also its original code
      Object result = proxy.invokeSuper(obj, args);

      // Also log the result of the method if it has any
      if (isAuditMethod && methodReturnsSomething) {
        System.out.println("The method returned value " + result.toString());
      }

      // Give back control to the caller
      return result;
    });

    // Create and return an instance of the modified class
    T auditedService = (T) enhancer.create();
    return auditedService;
  }
}
