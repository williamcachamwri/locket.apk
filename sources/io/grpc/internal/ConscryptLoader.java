package io.grpc.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.Provider;

public final class ConscryptLoader {
    private static final Method IS_CONSCRYPT_METHOD;
    private static final Method NEW_PROVIDER_METHOD;

    static {
        Method method;
        Method method2;
        try {
            Class<?> cls = Class.forName("org.conscrypt.Conscrypt");
            method = cls.getMethod("newProvider", new Class[0]);
            method2 = cls.getMethod("isConscrypt", new Class[]{Provider.class});
        } catch (ClassNotFoundException unused) {
            method = null;
            method2 = null;
        } catch (NoSuchMethodException e) {
            throw new AssertionError(e);
        }
        NEW_PROVIDER_METHOD = method;
        IS_CONSCRYPT_METHOD = method2;
    }

    public static boolean isPresent() {
        return NEW_PROVIDER_METHOD != null;
    }

    public static boolean isConscrypt(Provider provider) {
        if (!isPresent()) {
            return false;
        }
        try {
            return ((Boolean) IS_CONSCRYPT_METHOD.invoke((Object) null, new Object[]{provider})).booleanValue();
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e2) {
            throw new AssertionError(e2);
        }
    }

    public static Provider newProvider() throws Throwable {
        if (isPresent()) {
            return (Provider) NEW_PROVIDER_METHOD.invoke((Object) null, new Object[0]);
        }
        Class.forName("org.conscrypt.Conscrypt");
        throw new AssertionError("Unexpected failure referencing Conscrypt class");
    }
}
