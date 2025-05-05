package expo.modules.font;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.net.Uri;
import expo.modules.interfaces.font.FontManagerInterface;
import expo.modules.kotlin.exception.Exceptions;
import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0003\u0018\u0001\"\u0006\b\u0002\u0010\u0004\u0018\u00012\u0010\u0010\u0005\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u0006H\n¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"<anonymous>", "", "R", "P0", "P1", "it", "", "invoke", "([Ljava/lang/Object;)Ljava/lang/Object;", "expo/modules/kotlin/objects/ObjectDefinitionBuilder$AsyncFunction$13"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ObjectDefinitionBuilder.kt */
public final class FontLoaderModule$definition$lambda$2$$inlined$AsyncFunction$5 extends Lambda implements Function1<Object[], Object> {
    final /* synthetic */ FontLoaderModule this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FontLoaderModule$definition$lambda$2$$inlined$AsyncFunction$5(FontLoaderModule fontLoaderModule) {
        super(1);
        this.this$0 = fontLoaderModule;
    }

    public final Object invoke(Object[] objArr) {
        Typeface typeface;
        Intrinsics.checkNotNullParameter(objArr, "it");
        String str = objArr[0];
        if (str != null) {
            String str2 = str;
            String str3 = objArr[1];
            if (str3 != null) {
                String str4 = str3;
                Context reactContext = this.this$0.getAppContext().getReactContext();
                if (reactContext != null) {
                    Object obj = null;
                    if (StringsKt.startsWith$default(str4, "asset://", false, 2, (Object) null)) {
                        AssetManager assets = reactContext.getAssets();
                        String substring = str4.substring(9);
                        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                        typeface = Typeface.createFromAsset(assets, substring);
                        Intrinsics.checkNotNull(typeface);
                    } else {
                        String path = Uri.parse(str4).getPath();
                        if (path != null) {
                            typeface = Typeface.createFromFile(new File(path));
                            Intrinsics.checkNotNull(typeface);
                        } else {
                            throw new FileNotFoundException(str4);
                        }
                    }
                    try {
                        obj = this.this$0.getAppContext().getLegacyModuleRegistry().getModule(FontManagerInterface.class);
                    } catch (Exception unused) {
                    }
                    FontManagerInterface fontManagerInterface = (FontManagerInterface) obj;
                    if (fontManagerInterface != null) {
                        fontManagerInterface.setTypeface(this.this$0.getPrefix() + str2, 0, typeface);
                        return Unit.INSTANCE;
                    }
                    throw new FontManagerInterfaceNotFoundException();
                }
                throw new Exceptions.ReactContextLost();
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
    }
}
