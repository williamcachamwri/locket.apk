package expo.modules.kotlin.objects;

import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.jni.ExpectedType;
import expo.modules.kotlin.jni.JavaScriptModuleObject;
import expo.modules.kotlin.types.JSTypeConverter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0013"}, d2 = {"Lexpo/modules/kotlin/objects/PropertyComponent;", "", "name", "", "getter", "Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "setter", "(Ljava/lang/String;Lexpo/modules/kotlin/functions/SyncFunctionComponent;Lexpo/modules/kotlin/functions/SyncFunctionComponent;)V", "getGetter", "()Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "getName", "()Ljava/lang/String;", "getSetter", "attachToJSObject", "", "appContext", "Lexpo/modules/kotlin/AppContext;", "jsObject", "Lexpo/modules/kotlin/jni/JavaScriptModuleObject;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PropertyComponent.kt */
public final class PropertyComponent {
    private final SyncFunctionComponent getter;
    private final String name;
    private final SyncFunctionComponent setter;

    public PropertyComponent(String str, SyncFunctionComponent syncFunctionComponent, SyncFunctionComponent syncFunctionComponent2) {
        Intrinsics.checkNotNullParameter(str, "name");
        this.name = str;
        this.getter = syncFunctionComponent;
        this.setter = syncFunctionComponent2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PropertyComponent(String str, SyncFunctionComponent syncFunctionComponent, SyncFunctionComponent syncFunctionComponent2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : syncFunctionComponent, (i & 4) != 0 ? null : syncFunctionComponent2);
    }

    public final String getName() {
        return this.name;
    }

    public final SyncFunctionComponent getGetter() {
        return this.getter;
    }

    public final SyncFunctionComponent getSetter() {
        return this.setter;
    }

    public final void attachToJSObject(AppContext appContext, JavaScriptModuleObject javaScriptModuleObject) {
        ExpectedType[] expectedTypeArr;
        ExpectedType[] expectedTypeArr2;
        List<ExpectedType> cppRequiredTypes;
        List<ExpectedType> cppRequiredTypes2;
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        Intrinsics.checkNotNullParameter(javaScriptModuleObject, "jsObject");
        PropertyComponent$$ExternalSyntheticLambda1 propertyComponent$$ExternalSyntheticLambda1 = null;
        PropertyComponent$$ExternalSyntheticLambda0 propertyComponent$$ExternalSyntheticLambda0 = this.getter != null ? new PropertyComponent$$ExternalSyntheticLambda0(this, appContext) : null;
        if (this.setter != null) {
            propertyComponent$$ExternalSyntheticLambda1 = new PropertyComponent$$ExternalSyntheticLambda1(this, appContext);
        }
        PropertyComponent$$ExternalSyntheticLambda1 propertyComponent$$ExternalSyntheticLambda12 = propertyComponent$$ExternalSyntheticLambda1;
        String str = this.name;
        SyncFunctionComponent syncFunctionComponent = this.getter;
        boolean takesOwner$expo_modules_core_release = syncFunctionComponent != null ? syncFunctionComponent.getTakesOwner$expo_modules_core_release() : false;
        SyncFunctionComponent syncFunctionComponent2 = this.getter;
        if (syncFunctionComponent2 == null || (cppRequiredTypes2 = syncFunctionComponent2.getCppRequiredTypes()) == null || (expectedTypeArr = (ExpectedType[]) cppRequiredTypes2.toArray(new ExpectedType[0])) == null) {
            expectedTypeArr = new ExpectedType[0];
        }
        ExpectedType[] expectedTypeArr3 = expectedTypeArr;
        SyncFunctionComponent syncFunctionComponent3 = this.setter;
        boolean takesOwner$expo_modules_core_release2 = syncFunctionComponent3 != null ? syncFunctionComponent3.getTakesOwner$expo_modules_core_release() : false;
        SyncFunctionComponent syncFunctionComponent4 = this.setter;
        if (syncFunctionComponent4 == null || (cppRequiredTypes = syncFunctionComponent4.getCppRequiredTypes()) == null || (expectedTypeArr2 = (ExpectedType[]) cppRequiredTypes.toArray(new ExpectedType[0])) == null) {
            expectedTypeArr2 = new ExpectedType[0];
        }
        javaScriptModuleObject.registerProperty(str, takesOwner$expo_modules_core_release, expectedTypeArr3, propertyComponent$$ExternalSyntheticLambda0, takesOwner$expo_modules_core_release2, expectedTypeArr2, propertyComponent$$ExternalSyntheticLambda12);
    }

    /* access modifiers changed from: private */
    public static final Object attachToJSObject$lambda$0(PropertyComponent propertyComponent, AppContext appContext, Object[] objArr) {
        Intrinsics.checkNotNullParameter(propertyComponent, "this$0");
        Intrinsics.checkNotNullParameter(appContext, "$appContext");
        Intrinsics.checkNotNullParameter(objArr, "args");
        return JSTypeConverter.convertToJSValue$default(JSTypeConverter.INSTANCE, propertyComponent.getter.call(objArr, appContext), (JSTypeConverter.ContainerProvider) null, 2, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final Object attachToJSObject$lambda$1(PropertyComponent propertyComponent, AppContext appContext, Object[] objArr) {
        Intrinsics.checkNotNullParameter(propertyComponent, "this$0");
        Intrinsics.checkNotNullParameter(appContext, "$appContext");
        Intrinsics.checkNotNullParameter(objArr, "args");
        propertyComponent.setter.call(objArr, appContext);
        return null;
    }
}
