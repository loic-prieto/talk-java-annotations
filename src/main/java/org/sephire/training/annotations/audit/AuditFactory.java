package org.sephire.training.annotations.audit;

import java.util.Arrays;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class AuditFactory {

  public static <T> T get(Class<T> auditedClass) {
    Enhancer enhancer = new Enhancer();
    enhancer.setSuperclass(auditedClass);
    enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
      // We just enhance the methods that are marked with Audit annotation
      boolean isAuditMethod = Arrays.stream(method.getDeclaredAnnotations())
          .anyMatch(annotation -> {
            return annotation.annotationType().getName().equals(Audit.class.getName());
          });
      boolean methodReturnsSomething = !method.getReturnType().equals(Void.TYPE);

      if (isAuditMethod) {
        String argsString = Arrays.stream(args)
            .reduce((arg1, arg2) -> arg1.toString() + ", " + arg2.toString())
            .map((rawObject) -> (String) rawObject)
            .orElseGet(() -> "");

        System.out.println("Method " + method.getName() + " was called with args " + argsString);
      }

      Object result = proxy.invokeSuper(obj, args);

      if (isAuditMethod && methodReturnsSomething) {
        System.out.println("The method returned value " + result.toString());
      }

      return result;
    });

    T auditedService = (T) enhancer.create();

    return auditedService;
  }
}
