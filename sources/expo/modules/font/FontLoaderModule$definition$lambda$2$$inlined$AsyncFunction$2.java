package expo.modules.font;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import com.facebook.react.bridge.BaseJavaModule;
import expo.modules.interfaces.font.FontManagerInterface;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.Exceptions;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u00012\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\n¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "args", "", "", "promise", "Lexpo/modules/kotlin/Promise;", "invoke", "([Ljava/lang/Object;Lexpo/modules/kotlin/Promise;)V", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$10"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class FontLoaderModule$definition$lambda$2$$inlined$AsyncFunction$2 extends Lambda implements Function2<Object[], Promise, Unit> {
    final /* synthetic */ FontLoaderModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FontLoaderModule$definition$lambda$2$$inlined$AsyncFunction$2(FontLoaderModule fontLoaderModule) {
        super(2);
        this.this$0 = fontLoaderModule;
    }

    public final void invoke(Object[] objArr, Promise promise) {
        Typeface typeface;
        Intrinsics.checkNotNullParameter(objArr, "args");
        Intrinsics.checkNotNullParameter(promise, BaseJavaModule.METHOD_TYPE_PROMISE);
        String str = objArr[0];
        if (str != null) {
            String str2 = str;
            String str3 = (String) promise;
            Context reactContext = this.this$0.getAppContext().getReactContext();
            if (reactContext != null) {
                Object obj = null;
                if (StringsKt.startsWith$default(str3, "asset://", false, 2, (Object) null)) {
                    AssetManager assets = reactContext.getAssets();
                    String substring = str3.substring(9);
                    Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                    typeface = Typeface.createFromAsset(assets, substring);
                    Intrinsics.checkNotNull(typeface);
                } else {
                    String path = Uri.parse(str3).getPath();
                    if (path != null) {
                        typeface = Typeface.createFromFile(new File(path));
                        Intrinsics.checkNotNull(typeface);
                    } else {
                        throw new FileNotFoundException(str3);
                    }
                }
                try {
                    obj = this.this$0.getAppContext().getLegacyModuleRegistry().getModule(FontManagerInterface.class);
                } catch (Exception unused) {
                }
                FontManagerInterface fontManagerInterface = (FontManagerInterface) obj;
                if (fontManagerInterface != null) {
                    fontManagerInterface.setTypeface(this.this$0.getPrefix() + str2, 0, typeface);
                    return;
                }
                throw new FontManagerInterfaceNotFoundException();
            }
            throw new Exceptions.ReactContextLost();
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Object[]) obj, (Promise) obj2);
        return Unit.INSTANCE;
    }
}
