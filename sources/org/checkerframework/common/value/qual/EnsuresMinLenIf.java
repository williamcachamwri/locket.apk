package org.checkerframework.common.value.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.ConditionalPostconditionAnnotation;
import org.checkerframework.framework.qual.InheritedAnnotation;
import org.checkerframework.framework.qual.QualifierArgument;

@Repeatable(List.class)
@ConditionalPostconditionAnnotation(qualifier = MinLen.class)
@Documented
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@InheritedAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface EnsuresMinLenIf {

    @ConditionalPostconditionAnnotation(qualifier = MinLen.class)
    @Documented
    @Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
    @InheritedAnnotation
    @Retention(RetentionPolicy.RUNTIME)
    public @interface List {
        EnsuresMinLenIf[] value();
    }

    String[] expression();

    boolean result();

    @QualifierArgument("value")
    int targetValue() default 0;
}
