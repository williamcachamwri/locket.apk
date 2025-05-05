package expo.modules.kotlin.functions;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.ReadableArrayIterator;
import expo.modules.kotlin.ReadableArrayIteratorKt;
import expo.modules.kotlin.exception.ArgumentCastException;
import expo.modules.kotlin.exception.CodedException;
import expo.modules.kotlin.exception.InvalidArgsNumberException;
import expo.modules.kotlin.exception.UnexpectedException;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import expo.modules.kotlin.types.AnyType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0018\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H&J\u001f\u0010)\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u00052\u0006\u0010*\u001a\u00020+H\u0004¢\u0006\u0002\u0010,J3\u0010)\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u00052\u000e\u0010*\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00052\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&H\u0004¢\u0006\u0002\u0010-J\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/R\u0014\u0010\b\u001a\u00020\t8@X\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR$\u0010\f\u001a\u00020\r8\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R&\u0010\u0019\u001a\u0004\u0018\u00010\u001a8\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001b\u0010\u000f\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\u00020\r8@X\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0011¨\u00061"}, d2 = {"Lexpo/modules/kotlin/functions/AnyFunction;", "", "name", "", "desiredArgsTypes", "", "Lexpo/modules/kotlin/types/AnyType;", "(Ljava/lang/String;[Lexpo/modules/kotlin/types/AnyType;)V", "argsCount", "", "getArgsCount$expo_modules_core_release", "()I", "canTakeOwner", "", "getCanTakeOwner$annotations", "()V", "getCanTakeOwner", "()Z", "setCanTakeOwner", "(Z)V", "getDesiredArgsTypes", "()[Lexpo/modules/kotlin/types/AnyType;", "[Lexpo/modules/kotlin/types/AnyType;", "getName", "()Ljava/lang/String;", "ownerType", "Lkotlin/reflect/KType;", "getOwnerType$annotations", "getOwnerType", "()Lkotlin/reflect/KType;", "setOwnerType", "(Lkotlin/reflect/KType;)V", "requiredArgumentsCount", "takesOwner", "getTakesOwner$expo_modules_core_release", "attachToJSObject", "", "appContext", "Lexpo/modules/kotlin/AppContext;", "jsObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "convertArgs", "args", "Lcom/facebook/react/bridge/ReadableArray;", "(Lcom/facebook/react/bridge/ReadableArray;)[Ljava/lang/Object;", "([Ljava/lang/Object;Lexpo/modules/kotlin/AppContext;)[Ljava/lang/Object;", "getCppRequiredTypes", "", "Lexpo/modules/kotlin/jni/ExpectedType;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AnyFunction.kt */
public abstract class AnyFunction {
    private boolean canTakeOwner;
    private final AnyType[] desiredArgsTypes;
    private final String name;
    private KType ownerType;
    private final int requiredArgumentsCount;

    public static /* synthetic */ void getCanTakeOwner$annotations() {
    }

    public static /* synthetic */ void getOwnerType$annotations() {
    }

    public abstract void attachToJSObject(AppContext appContext, JavaScriptModuleObject javaScriptModuleObject);

    public AnyFunction(String str, AnyType[] anyTypeArr) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(anyTypeArr, "desiredArgsTypes");
        this.name = str;
        this.desiredArgsTypes = anyTypeArr;
        AnyFunction anyFunction = this;
        Iterator it = ArraysKt.reversed((T[]) this.desiredArgsTypes).iterator();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (!it.hasNext()) {
                i2 = -1;
                break;
            } else if (!((AnyType) it.next()).getKType().isMarkedNullable()) {
                break;
            } else {
                i2++;
            }
        }
        this.requiredArgumentsCount = i2 >= 0 ? this.desiredArgsTypes.length - i2 : i;
    }

    /* access modifiers changed from: protected */
    public final String getName() {
        return this.name;
    }

    /* access modifiers changed from: protected */
    public final AnyType[] getDesiredArgsTypes() {
        return this.desiredArgsTypes;
    }

    public final int getArgsCount$expo_modules_core_release() {
        return this.desiredArgsTypes.length;
    }

    public final boolean getCanTakeOwner() {
        return this.canTakeOwner;
    }

    public final void setCanTakeOwner(boolean z) {
        this.canTakeOwner = z;
    }

    public final KType getOwnerType() {
        return this.ownerType;
    }

    public final void setOwnerType(KType kType) {
        this.ownerType = kType;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        r0 = r0.getKType();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean getTakesOwner$expo_modules_core_release() {
        /*
            r5 = this;
            boolean r0 = r5.canTakeOwner
            r1 = 0
            if (r0 == 0) goto L_0x004e
            expo.modules.kotlin.types.AnyType[] r0 = r5.desiredArgsTypes
            java.lang.Object r0 = kotlin.collections.ArraysKt.firstOrNull((T[]) r0)
            expo.modules.kotlin.types.AnyType r0 = (expo.modules.kotlin.types.AnyType) r0
            r2 = 0
            if (r0 == 0) goto L_0x001b
            kotlin.reflect.KType r0 = r0.getKType()
            if (r0 == 0) goto L_0x001b
            kotlin.reflect.KClassifier r0 = r0.getClassifier()
            goto L_0x001c
        L_0x001b:
            r0 = r2
        L_0x001c:
            boolean r3 = r0 instanceof kotlin.reflect.KClass
            if (r3 == 0) goto L_0x0023
            kotlin.reflect.KClass r0 = (kotlin.reflect.KClass) r0
            goto L_0x0024
        L_0x0023:
            r0 = r2
        L_0x0024:
            if (r0 != 0) goto L_0x0027
            return r1
        L_0x0027:
            java.lang.Class<expo.modules.kotlin.jni.JavaScriptObject> r3 = expo.modules.kotlin.jni.JavaScriptObject.class
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r3)
            if (r3 == 0) goto L_0x0035
            r0 = 1
            return r0
        L_0x0035:
            kotlin.reflect.KType r3 = r5.ownerType
            if (r3 == 0) goto L_0x003e
            kotlin.reflect.KClassifier r3 = r3.getClassifier()
            goto L_0x003f
        L_0x003e:
            r3 = r2
        L_0x003f:
            boolean r4 = r3 instanceof kotlin.reflect.KClass
            if (r4 == 0) goto L_0x0046
            r2 = r3
            kotlin.reflect.KClass r2 = (kotlin.reflect.KClass) r2
        L_0x0046:
            if (r2 != 0) goto L_0x0049
            return r1
        L_0x0049:
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r2)
            return r0
        L_0x004e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.functions.AnyFunction.getTakesOwner$expo_modules_core_release():boolean");
    }

    /* access modifiers changed from: protected */
    public final Object[] convertArgs(ReadableArray readableArray) throws CodedException {
        CodedException codedException;
        Intrinsics.checkNotNullParameter(readableArray, "args");
        if (this.requiredArgumentsCount <= readableArray.size()) {
            int size = readableArray.size();
            AnyType[] anyTypeArr = this.desiredArgsTypes;
            if (size <= anyTypeArr.length) {
                int length = anyTypeArr.length;
                Object[] objArr = new Object[length];
                int i = 0;
                for (int i2 = 0; i2 < length; i2++) {
                    objArr[i2] = null;
                }
                ReadableArrayIterator it = ReadableArrayIteratorKt.iterator(readableArray);
                int size2 = readableArray.size();
                while (i < size2) {
                    AnyType anyType = this.desiredArgsTypes[i];
                    Dynamic next = it.next();
                    try {
                        objArr[i] = AnyType.convert$default(anyType, next, (AppContext) null, 2, (Object) null);
                        Unit unit = Unit.INSTANCE;
                        Unit unit2 = Unit.INSTANCE;
                        next.recycle();
                        i++;
                    } catch (Throwable th) {
                        next.recycle();
                        throw th;
                    }
                }
                return objArr;
            }
        }
        throw new InvalidArgsNumberException(readableArray.size(), this.desiredArgsTypes.length, this.requiredArgumentsCount);
    }

    public static /* synthetic */ Object[] convertArgs$default(AnyFunction anyFunction, Object[] objArr, AppContext appContext, int i, Object obj) throws CodedException {
        if (obj == null) {
            if ((i & 2) != 0) {
                appContext = null;
            }
            return anyFunction.convertArgs(objArr, appContext);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: convertArgs");
    }

    /* access modifiers changed from: protected */
    public final Object[] convertArgs(Object[] objArr, AppContext appContext) throws CodedException {
        Class<?> cls;
        CodedException codedException;
        Intrinsics.checkNotNullParameter(objArr, "args");
        if (this.requiredArgumentsCount <= objArr.length) {
            int length = objArr.length;
            AnyType[] anyTypeArr = this.desiredArgsTypes;
            if (length <= anyTypeArr.length) {
                int length2 = anyTypeArr.length;
                Object[] objArr2 = new Object[length2];
                int i = 0;
                int i2 = 0;
                while (true) {
                    cls = null;
                    if (i2 >= length2) {
                        break;
                    }
                    objArr2[i2] = null;
                    i2++;
                }
                Iterator it = ArrayIteratorKt.iterator(objArr);
                int length3 = objArr.length;
                while (i < length3) {
                    Object next = it.next();
                    AnyType anyType = this.desiredArgsTypes[i];
                    try {
                        objArr2[i] = anyType.convert(next, appContext);
                        Unit unit = Unit.INSTANCE;
                        i++;
                    } catch (Throwable th) {
                        if (th instanceof CodedException) {
                            codedException = (CodedException) th;
                        } else if (th instanceof expo.modules.core.errors.CodedException) {
                            String code = th.getCode();
                            Intrinsics.checkNotNullExpressionValue(code, "getCode(...)");
                            codedException = new CodedException(code, th.getMessage(), th.getCause());
                        } else {
                            codedException = new UnexpectedException((Throwable) th);
                        }
                        KType kType = anyType.getKType();
                        if (next != null) {
                            cls = next.getClass();
                        }
                        throw new ArgumentCastException(kType, i, String.valueOf(cls), codedException);
                    }
                }
                return objArr2;
            }
        }
        throw new InvalidArgsNumberException(objArr.length, this.desiredArgsTypes.length, this.requiredArgumentsCount);
    }

    public final List<ExpectedType> getCppRequiredTypes() {
        AnyType[] anyTypeArr = this.desiredArgsTypes;
        Collection arrayList = new ArrayList(anyTypeArr.length);
        for (AnyType cppRequiredTypes : anyTypeArr) {
            arrayList.add(cppRequiredTypes.getCppRequiredTypes());
        }
        return (List) arrayList;
    }
}
