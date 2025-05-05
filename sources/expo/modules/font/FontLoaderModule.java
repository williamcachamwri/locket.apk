package expo.modules.font;

import android.content.Context;
import androidx.tracing.Trace;
import expo.modules.kotlin.Promise;
import expo.modules.kotlin.exception.Exceptions;
import expo.modules.kotlin.functions.AsyncFunction;
import expo.modules.kotlin.functions.AsyncFunctionComponent;
import expo.modules.kotlin.functions.AsyncFunctionWithPromiseComponent;
import expo.modules.kotlin.modules.Module;
import expo.modules.kotlin.modules.ModuleDefinitionBuilder;
import expo.modules.kotlin.modules.ModuleDefinitionData;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.types.AnyType;
import expo.modules.kotlin.types.LazyKType;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\u000e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000fH\u0002R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bXD¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lexpo/modules/font/FontLoaderModule;", "Lexpo/modules/kotlin/modules/Module;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "prefix", "", "getPrefix$annotations", "getPrefix", "()Ljava/lang/String;", "definition", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "queryCustomNativeFonts", "", "expo-font_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FontLoaderModule.kt */
public class FontLoaderModule extends Module {
    private final String prefix = "";

    public static /* synthetic */ void getPrefix$annotations() {
    }

    private final Context getContext() {
        Context reactContext = getAppContext().getReactContext();
        if (reactContext != null) {
            return reactContext;
        }
        throw new Exceptions.ReactContextLost();
    }

    public String getPrefix() {
        return this.prefix;
    }

    public ModuleDefinitionData definition() {
        AsyncFunction asyncFunction;
        Module module = this;
        Trace.beginSection("[ExpoModulesCore] " + (module.getClass() + ".ModuleDefinition"));
        try {
            ModuleDefinitionBuilder moduleDefinitionBuilder = new ModuleDefinitionBuilder(module);
            moduleDefinitionBuilder.Name("ExpoFontLoader");
            moduleDefinitionBuilder.Constants((Pair<String, ? extends Object>[]) new Pair[]{TuplesKt.to("customNativeFonts", queryCustomNativeFonts())});
            ObjectDefinitionBuilder objectDefinitionBuilder = moduleDefinitionBuilder;
            if (String.class == Promise.class) {
                asyncFunction = new AsyncFunctionWithPromiseComponent("loadAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FontLoaderModule$definition$lambda$2$$inlined$AsyncFunction$1.INSTANCE))}, new FontLoaderModule$definition$lambda$2$$inlined$AsyncFunction$2(this));
            } else {
                asyncFunction = new AsyncFunctionComponent("loadAsync", new AnyType[]{new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FontLoaderModule$definition$lambda$2$$inlined$AsyncFunction$3.INSTANCE)), new AnyType(new LazyKType(Reflection.getOrCreateKotlinClass(String.class), false, FontLoaderModule$definition$lambda$2$$inlined$AsyncFunction$4.INSTANCE))}, new FontLoaderModule$definition$lambda$2$$inlined$AsyncFunction$5(this));
            }
            objectDefinitionBuilder.getAsyncFunctions().put("loadAsync", asyncFunction);
            return moduleDefinitionBuilder.buildModule();
        } finally {
            Trace.endSection();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.util.Collection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: java.util.List<java.lang.String>} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0033, code lost:
        r8 = r8.getGroupValues();
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<java.lang.String> queryCustomNativeFonts() {
        /*
            r10 = this;
            android.content.Context r0 = r10.getContext()
            android.content.res.AssetManager r0 = r0.getAssets()
            kotlin.text.Regex r1 = new kotlin.text.Regex
            java.lang.String r2 = "^(.+?)(_bold|_italic|_bold_italic)?\\.(ttf|otf)$"
            r1.<init>((java.lang.String) r2)
            java.lang.String r2 = "fonts/"
            java.lang.String[] r0 = r0.list(r2)
            r2 = 0
            if (r0 == 0) goto L_0x0075
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Collection r3 = (java.util.Collection) r3
            int r4 = r0.length
            r5 = 0
            r6 = r5
        L_0x0022:
            r7 = 1
            if (r6 >= r4) goto L_0x0049
            r8 = r0[r6]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r9 = 2
            kotlin.text.MatchResult r8 = kotlin.text.Regex.find$default(r1, r8, r5, r9, r2)
            if (r8 == 0) goto L_0x0040
            java.util.List r8 = r8.getGroupValues()
            if (r8 == 0) goto L_0x0040
            java.lang.Object r7 = r8.get(r7)
            java.lang.String r7 = (java.lang.String) r7
            goto L_0x0041
        L_0x0040:
            r7 = r2
        L_0x0041:
            if (r7 == 0) goto L_0x0046
            r3.add(r7)
        L_0x0046:
            int r6 = r6 + 1
            goto L_0x0022
        L_0x0049:
            java.util.List r3 = (java.util.List) r3
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r1 = r3.iterator()
        L_0x0058:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0072
            java.lang.Object r2 = r1.next()
            r3 = r2
            java.lang.String r3 = (java.lang.String) r3
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            boolean r3 = kotlin.text.StringsKt.isBlank(r3)
            r3 = r3 ^ r7
            if (r3 == 0) goto L_0x0058
            r0.add(r2)
            goto L_0x0058
        L_0x0072:
            r2 = r0
            java.util.List r2 = (java.util.List) r2
        L_0x0075:
            if (r2 != 0) goto L_0x007b
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
        L_0x007b:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.font.FontLoaderModule.queryCustomNativeFonts():java.util.List");
    }
}
