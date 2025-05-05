package expo.modules.kotlin.objects;

import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.jni.JNIFunctionBody;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class PropertyComponent$$ExternalSyntheticLambda0 implements JNIFunctionBody {
    public final /* synthetic */ PropertyComponent f$0;
    public final /* synthetic */ AppContext f$1;

    public /* synthetic */ PropertyComponent$$ExternalSyntheticLambda0(PropertyComponent propertyComponent, AppContext appContext) {
        this.f$0 = propertyComponent;
        this.f$1 = appContext;
    }

    public final Object invoke(Object[] objArr) {
        return PropertyComponent.attachToJSObject$lambda$0(this.f$0, this.f$1, objArr);
    }
}
