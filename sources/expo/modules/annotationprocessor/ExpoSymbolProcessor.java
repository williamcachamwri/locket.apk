package expo.modules.annotationprocessor;

import com.google.devtools.ksp.processing.CodeGenerator;
import com.google.devtools.ksp.processing.KSPLogger;
import com.google.devtools.ksp.processing.Resolver;
import com.google.devtools.ksp.processing.SymbolProcessor;
import com.google.devtools.ksp.symbol.KSAnnotated;
import com.google.devtools.ksp.symbol.KSAnnotation;
import com.google.devtools.ksp.symbol.KSFunctionDeclaration;
import com.google.devtools.ksp.symbol.KSType;
import com.squareup.kotlinpoet.ClassName;
import com.squareup.kotlinpoet.ksp.KsTypesKt;
import expo.modules.annotation.ConverterBinder;
import io.sentry.SentryEvent;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lexpo/modules/annotationprocessor/ExpoSymbolProcessor;", "Lcom/google/devtools/ksp/processing/SymbolProcessor;", "codeGenerator", "Lcom/google/devtools/ksp/processing/CodeGenerator;", "logger", "Lcom/google/devtools/ksp/processing/KSPLogger;", "(Lcom/google/devtools/ksp/processing/CodeGenerator;Lcom/google/devtools/ksp/processing/KSPLogger;)V", "process", "", "Lcom/google/devtools/ksp/symbol/KSAnnotated;", "resolver", "Lcom/google/devtools/ksp/processing/Resolver;", "expo-modules-core$android-annotation-processor"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoSymbolProcessor.kt */
public final class ExpoSymbolProcessor implements SymbolProcessor {
    private final CodeGenerator codeGenerator;
    private final KSPLogger logger;

    public ExpoSymbolProcessor(CodeGenerator codeGenerator2, KSPLogger kSPLogger) {
        Intrinsics.checkNotNullParameter(codeGenerator2, "codeGenerator");
        Intrinsics.checkNotNullParameter(kSPLogger, SentryEvent.JsonKeys.LOGGER);
        this.codeGenerator = codeGenerator2;
        this.logger = kSPLogger;
    }

    public List<KSAnnotated> process(Resolver resolver) {
        KSAnnotation kSAnnotation;
        ClassName className;
        Intrinsics.checkNotNullParameter(resolver, "resolver");
        String name = ConverterBinder.class.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        Sequence<KSFunctionDeclaration> filter = SequencesKt.filter(Resolver.getSymbolsWithAnnotation$default(resolver, name, false, 2, (Object) null), ExpoSymbolProcessor$process$$inlined$filterIsInstance$1.INSTANCE);
        Intrinsics.checkNotNull(filter, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesKt.filterIsInstance>");
        if (!filter.iterator().hasNext()) {
            return CollectionsKt.emptyList();
        }
        for (KSFunctionDeclaration kSFunctionDeclaration : filter) {
            Iterator<KSAnnotation> it = kSFunctionDeclaration.getAnnotations().iterator();
            while (true) {
                if (!it.hasNext()) {
                    kSAnnotation = null;
                    break;
                }
                kSAnnotation = it.next();
                if (Intrinsics.areEqual((Object) kSAnnotation.getShortName().asString(), (Object) ConverterBinder.class.getSimpleName())) {
                    break;
                }
            }
            Intrinsics.checkNotNull(kSAnnotation);
            Object value = kSAnnotation.getArguments().get(0).getValue();
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type com.google.devtools.ksp.symbol.KSType");
            KSType kSType = (KSType) value;
            if (Intrinsics.areEqual((Object) kSType.toString(), (Object) "Void")) {
                className = null;
            } else {
                className = KsTypesKt.toClassName(kSType);
            }
            kSFunctionDeclaration.accept(new ConverterBinderVisitor(className, this.codeGenerator, this.logger), Unit.INSTANCE);
        }
        return CollectionsKt.emptyList();
    }
}
