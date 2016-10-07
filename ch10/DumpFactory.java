import com.sun.mirror.apt.*;
import com.sun.mirror.declaration.*;
import com.sun.mirror.type.*;
import com.sun.mirror.util.*;
import static com.sun.mirror.util.DeclarationVisitors.*;

import java.util.*;


public class DumpFactory implements AnnotationProcessorFactory {
  // Process all annotations
  private static final Collection<String> supportedAnnotations
    = Collections.unmodifiableCollection(Arrays.asList("*"));

  // No options support
  private static final Collection<String> supportedOptions = Collections.emptySet();

  public Collection<String> supportedAnnotationTypes() {
    return supportedAnnotations;
  }

  public Collection<String> supportedOptions() {
    return supportedOptions;
  }

  public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> atds,
      AnnotationProcessorEnvironment env) {
    return new DumpProcessor(env);
  }

  private static class DumpProcessor implements AnnotationProcessor {

    private final AnnotationProcessorEnvironment env;

    DumpProcessor(AnnotationProcessorEnvironment env) {
      this.env = env;
    }

    public void process() {
      for (TypeDeclaration typeDecl : env.getSpecifiedTypeDeclarations()) {
        typeDecl.accept(getDeclarationScanner(new DumpVisitor(), NO_OP));
      }
    }

    private static class DumpVisitor extends SimpleDeclarationVisitor {
      public void visitMethodDeclaration(MethodDeclaration d) {
        System.out.println("\t" + d.getSimpleName());
      }
      public void visitClassDeclaration(ClassDeclaration d) {
        System.out.println(d.getQualifiedName());
      }
      public void visitInterfaceDeclaration(InterfaceDeclaration d) {
        System.out.println(d.getQualifiedName());
      }
    }
  }
}
