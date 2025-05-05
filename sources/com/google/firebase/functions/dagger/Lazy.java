package com.google.firebase.functions.dagger;

public interface Lazy<T> {
    T get();
}
