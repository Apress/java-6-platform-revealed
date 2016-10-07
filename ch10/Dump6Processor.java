import javax.annotation.processing.*;
import javax.lang.model.*;
import javax.lang.model.element.*;

import java.util.*;

// Source version 
@SupportedSourceVersion(SourceVersion.RELEASE_6)

// Process all annotations
@SupportedAnnotationTypes("*")

// No options support
// Empty set when not annotated with @SupportedOptions

public class Dump6Processor extends AbstractProcessor {

  public boolean process(Set<? extends TypeElement> annotations,
      RoundEnvironment roundEnv) {

    if (!roundEnv.processingOver()) {
      for (TypeElement element : annotations) {
        System.out.println(element.getQualifiedName() +
          "(" + element.getNestingKind() + ")");
      }
    }
    return false; // No annotations claimed
  }
}
