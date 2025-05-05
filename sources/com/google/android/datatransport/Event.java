package com.google.android.datatransport;

public abstract class Event<T> {
    public abstract Integer getCode();

    public abstract EventContext getEventContext();

    public abstract T getPayload();

    public abstract Priority getPriority();

    public abstract ProductData getProductData();

    public static <T> Event<T> ofData(int i, T t, ProductData productData, EventContext eventContext) {
        return new AutoValue_Event(Integer.valueOf(i), t, Priority.DEFAULT, productData, eventContext);
    }

    public static <T> Event<T> ofData(int i, T t, ProductData productData) {
        return new AutoValue_Event(Integer.valueOf(i), t, Priority.DEFAULT, productData, (EventContext) null);
    }

    public static <T> Event<T> ofData(int i, T t, EventContext eventContext) {
        return new AutoValue_Event(Integer.valueOf(i), t, Priority.DEFAULT, (ProductData) null, eventContext);
    }

    public static <T> Event<T> ofData(int i, T t) {
        return new AutoValue_Event(Integer.valueOf(i), t, Priority.DEFAULT, (ProductData) null, (EventContext) null);
    }

    public static <T> Event<T> ofData(T t, ProductData productData, EventContext eventContext) {
        return new AutoValue_Event((Integer) null, t, Priority.DEFAULT, productData, eventContext);
    }

    public static <T> Event<T> ofData(T t, ProductData productData) {
        return new AutoValue_Event((Integer) null, t, Priority.DEFAULT, productData, (EventContext) null);
    }

    public static <T> Event<T> ofData(T t, EventContext eventContext) {
        return new AutoValue_Event((Integer) null, t, Priority.DEFAULT, (ProductData) null, eventContext);
    }

    public static <T> Event<T> ofData(T t) {
        return new AutoValue_Event((Integer) null, t, Priority.DEFAULT, (ProductData) null, (EventContext) null);
    }

    public static <T> Event<T> ofTelemetry(int i, T t, ProductData productData, EventContext eventContext) {
        return new AutoValue_Event(Integer.valueOf(i), t, Priority.VERY_LOW, productData, eventContext);
    }

    public static <T> Event<T> ofTelemetry(int i, T t, ProductData productData) {
        return new AutoValue_Event(Integer.valueOf(i), t, Priority.VERY_LOW, productData, (EventContext) null);
    }

    public static <T> Event<T> ofTelemetry(int i, T t, EventContext eventContext) {
        return new AutoValue_Event(Integer.valueOf(i), t, Priority.VERY_LOW, (ProductData) null, eventContext);
    }

    public static <T> Event<T> ofTelemetry(int i, T t) {
        return new AutoValue_Event(Integer.valueOf(i), t, Priority.VERY_LOW, (ProductData) null, (EventContext) null);
    }

    public static <T> Event<T> ofTelemetry(T t, ProductData productData, EventContext eventContext) {
        return new AutoValue_Event((Integer) null, t, Priority.VERY_LOW, productData, eventContext);
    }

    public static <T> Event<T> ofTelemetry(T t, ProductData productData) {
        return new AutoValue_Event((Integer) null, t, Priority.VERY_LOW, productData, (EventContext) null);
    }

    public static <T> Event<T> ofTelemetry(T t, EventContext eventContext) {
        return new AutoValue_Event((Integer) null, t, Priority.VERY_LOW, (ProductData) null, eventContext);
    }

    public static <T> Event<T> ofTelemetry(T t) {
        return new AutoValue_Event((Integer) null, t, Priority.VERY_LOW, (ProductData) null, (EventContext) null);
    }

    public static <T> Event<T> ofUrgent(int i, T t, ProductData productData, EventContext eventContext) {
        return new AutoValue_Event(Integer.valueOf(i), t, Priority.HIGHEST, productData, eventContext);
    }

    public static <T> Event<T> ofUrgent(int i, T t, ProductData productData) {
        return new AutoValue_Event(Integer.valueOf(i), t, Priority.HIGHEST, productData, (EventContext) null);
    }

    public static <T> Event<T> ofUrgent(int i, T t, EventContext eventContext) {
        return new AutoValue_Event(Integer.valueOf(i), t, Priority.HIGHEST, (ProductData) null, eventContext);
    }

    public static <T> Event<T> ofUrgent(int i, T t) {
        return new AutoValue_Event(Integer.valueOf(i), t, Priority.HIGHEST, (ProductData) null, (EventContext) null);
    }

    public static <T> Event<T> ofUrgent(T t, ProductData productData, EventContext eventContext) {
        return new AutoValue_Event((Integer) null, t, Priority.HIGHEST, productData, eventContext);
    }

    public static <T> Event<T> ofUrgent(T t, ProductData productData) {
        return new AutoValue_Event((Integer) null, t, Priority.HIGHEST, productData, (EventContext) null);
    }

    public static <T> Event<T> ofUrgent(T t, EventContext eventContext) {
        return new AutoValue_Event((Integer) null, t, Priority.HIGHEST, (ProductData) null, eventContext);
    }

    public static <T> Event<T> ofUrgent(T t) {
        return new AutoValue_Event((Integer) null, t, Priority.HIGHEST, (ProductData) null, (EventContext) null);
    }
}
