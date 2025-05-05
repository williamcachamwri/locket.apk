package org.checkerframework.framework.qual;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.METHOD})
@InheritedAnnotation
@Repeatable(List.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnsuresQualifierIf {

    @Documented
    @Target({ElementType.METHOD})
    @InheritedAnnotation
    @Retention(RetentionPolicy.RUNTIME)
    public @interface List {
        EnsuresQualifierIf[] value();
    }

    String[] expression();

    Class<? extends Annotation> qualifier();

    boolean result();
}
