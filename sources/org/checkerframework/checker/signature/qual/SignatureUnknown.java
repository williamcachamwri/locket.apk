package org.checkerframework.checker.signature.qual;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.DefaultQualifierInHierarchy;
import org.checkerframework.framework.qual.SubtypeOf;

@SubtypeOf({})
@Documented
@Target({})
@DefaultQualifierInHierarchy
@Retention(RetentionPolicy.RUNTIME)
public @interface SignatureUnknown {
}
