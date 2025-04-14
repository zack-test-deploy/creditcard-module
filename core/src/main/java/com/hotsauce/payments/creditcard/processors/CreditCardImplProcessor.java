package com.hotsauce.payments.creditcard.processors;

import com.hotsauce.payments.creditcard.annotations.CreditCardImpl;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.Writer;
import java.util.*;

@SupportedAnnotationTypes("com.hotsauce.payments.creditcard.annotations.CreditCardImpl")
@SupportedSourceVersion(SourceVersion.RELEASE_17)
public class CreditCardImplProcessor extends AbstractProcessor {

    private static final String SERVICE_FILE = "META-INF/services/com.hotsauce.payments.creditcard.core.CreditCard";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Map<String, String> nameToImpl = new HashMap<>();
        Set<String> implementations = new HashSet<>();

        for (Element element : roundEnv.getElementsAnnotatedWith(CreditCardImpl.class)) {
            if (!(element instanceof TypeElement)) continue;

            TypeElement typeElement = (TypeElement) element;
            String className = typeElement.getQualifiedName().toString();

            CreditCardImpl annotation = typeElement.getAnnotation(CreditCardImpl.class);
            String name = annotation.value();

            if (nameToImpl.containsKey(name)) {
                String existingClass = nameToImpl.get(name);
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "Duplicate @CreditCardImpl value: '" + name + "' already used by " + existingClass,
                        element
                );
                return true; // Stop processing on conflict
            }

            nameToImpl.put(name, className);
            implementations.add(className);
        }

        if (!implementations.isEmpty()) {
            try {
                Filer filer = processingEnv.getFiler();
                FileObject fileObject = filer.createResource(StandardLocation.CLASS_OUTPUT, "", SERVICE_FILE);
                try (Writer writer = fileObject.openWriter()) {
                    for (String impl : implementations) {
                        writer.write(impl + "\n");
                    }
                }
            } catch (Exception e) {
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "Failed to write service file: " + e.getMessage()
                );
                return false;
            }
        }

        return true;
    }
}
