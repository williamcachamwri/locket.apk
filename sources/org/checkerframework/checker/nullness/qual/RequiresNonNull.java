package org.checkerframework.checker.nullness.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.PreconditionAnnotation;

@Documented
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Repeatable(List.class)
@Retention(RetentionPolicy.RUNTIME)
@PreconditionAnnotation(qualifier = NonNull.class)
public @interface RequiresNonNull {

    @Documented
    @Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
    @Retention(RetentionPolicy.RUNTIME)
    @PreconditionAnnotation(qualifier = NonNull.class)
    public @interface List {
        RequiresNonNull[] value();
    }

    String[] value();
}
