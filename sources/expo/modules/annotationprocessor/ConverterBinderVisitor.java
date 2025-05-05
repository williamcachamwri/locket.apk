package expo.modules.annotationprocessor;

import com.google.devtools.ksp.UtilsKt;
import com.google.devtools.ksp.processing.CodeGenerator;
import com.google.devtools.ksp.processing.Dependencies;
import com.google.devtools.ksp.processing.KSPLogger;
import com.google.devtools.ksp.symbol.FunctionKind;
import com.google.devtools.ksp.symbol.KSFile;
import com.google.devtools.ksp.symbol.KSFunctionDeclaration;
import com.google.devtools.ksp.symbol.KSNode;
import com.google.devtools.ksp.symbol.KSType;
import com.google.devtools.ksp.symbol.KSTypeArgument;
import com.google.devtools.ksp.symbol.KSTypeReference;
import com.google.devtools.ksp.symbol.KSValueParameter;
import com.google.devtools.ksp.symbol.KSVisitorVoid;
import com.google.devtools.ksp.symbol.Visibility;
import com.squareup.kotlinpoet.ClassName;
import com.squareup.kotlinpoet.CodeBlock;
import com.squareup.kotlinpoet.FileSpec;
import com.squareup.kotlinpoet.FunSpec;
import com.squareup.kotlinpoet.KModifier;
import com.squareup.kotlinpoet.ParameterizedTypeName;
import com.squareup.kotlinpoet.TypeName;
import com.squareup.kotlinpoet.TypeSpec;
import com.squareup.kotlinpoet.ksp.KsTypesKt;
import expo.modules.annotation.Config;
import io.sentry.SentryEvent;
import io.sentry.protocol.SentryStackFrame;
import java.io.Closeable;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J0\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0017\u0010\u0016\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u000b\u001a\u00020\fH\u0002¢\u0006\u0002\u0010\u0017J\u001d\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u0019H\u0016¢\u0006\u0002\u0010\u001bR\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lexpo/modules/annotationprocessor/ConverterBinderVisitor;", "Lcom/google/devtools/ksp/symbol/KSVisitorVoid;", "clazz", "Lcom/squareup/kotlinpoet/ClassName;", "codeGenerator", "Lcom/google/devtools/ksp/processing/CodeGenerator;", "logger", "Lcom/google/devtools/ksp/processing/KSPLogger;", "(Lcom/squareup/kotlinpoet/ClassName;Lcom/google/devtools/ksp/processing/CodeGenerator;Lcom/google/devtools/ksp/processing/KSPLogger;)V", "createFileDependencies", "Lcom/google/devtools/ksp/processing/Dependencies;", "function", "Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;", "generateConverterProvider", "Lcom/squareup/kotlinpoet/FileSpec;", "packageName", "", "className", "forType", "receivesType", "", "resolveConverterType", "shouldReceiveType", "(Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;)Ljava/lang/Boolean;", "visitFunctionDeclaration", "", "data", "(Lcom/google/devtools/ksp/symbol/KSFunctionDeclaration;Lkotlin/Unit;)V", "expo-modules-core$android-annotation-processor"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExpoSymbolProcessor.kt */
public final class ConverterBinderVisitor extends KSVisitorVoid {
    private final ClassName clazz;
    private final CodeGenerator codeGenerator;
    private final KSPLogger logger;

    public /* bridge */ /* synthetic */ Object visitFunctionDeclaration(KSFunctionDeclaration kSFunctionDeclaration, Object obj) {
        visitFunctionDeclaration(kSFunctionDeclaration, (Unit) obj);
        return Unit.INSTANCE;
    }

    public ConverterBinderVisitor(ClassName className, CodeGenerator codeGenerator2, KSPLogger kSPLogger) {
        Intrinsics.checkNotNullParameter(codeGenerator2, "codeGenerator");
        Intrinsics.checkNotNullParameter(kSPLogger, SentryEvent.JsonKeys.LOGGER);
        this.clazz = className;
        this.codeGenerator = codeGenerator2;
        this.logger = kSPLogger;
    }

    public void visitFunctionDeclaration(KSFunctionDeclaration kSFunctionDeclaration, Unit unit) {
        Boolean shouldReceiveType;
        Throwable th;
        KSFunctionDeclaration kSFunctionDeclaration2 = kSFunctionDeclaration;
        Intrinsics.checkNotNullParameter(kSFunctionDeclaration2, SentryStackFrame.JsonKeys.FUNCTION);
        Intrinsics.checkNotNullParameter(unit, "data");
        if (kSFunctionDeclaration.getFunctionKind() == FunctionKind.TOP_LEVEL && UtilsKt.getVisibility(kSFunctionDeclaration2) == Visibility.PUBLIC) {
            ClassName resolveConverterType = resolveConverterType(kSFunctionDeclaration);
            if (resolveConverterType != null && (shouldReceiveType = shouldReceiveType(kSFunctionDeclaration)) != null) {
                String str = Config.packageNamePrefix + resolveConverterType.getPackageName();
                String str2 = resolveConverterType.getSimpleName() + Config.classNameSuffix;
                FileSpec generateConverterProvider = generateConverterProvider(str, str2, kSFunctionDeclaration, resolveConverterType, shouldReceiveType.booleanValue());
                KSPLogger.info$default(this.logger, "Generating: " + str + "." + str2, (KSNode) null, 2, (Object) null);
                Closeable outputStreamWriter = new OutputStreamWriter(CodeGenerator.createNewFile$default(this.codeGenerator, createFileDependencies(kSFunctionDeclaration), str, str2, (String) null, 8, (Object) null), StandardCharsets.UTF_8);
                try {
                    generateConverterProvider.writeTo((Appendable) (OutputStreamWriter) outputStreamWriter);
                    Unit unit2 = Unit.INSTANCE;
                    CloseableKt.closeFinally(outputStreamWriter, (Throwable) null);
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    CloseableKt.closeFinally(outputStreamWriter, th);
                    throw th3;
                }
            }
        } else {
            this.logger.error("ConverterBinder has to be a public top-level function", kSFunctionDeclaration2);
        }
    }

    private final Boolean shouldReceiveType(KSFunctionDeclaration kSFunctionDeclaration) {
        KSTypeReference type;
        int size = kSFunctionDeclaration.getParameters().size();
        String str = null;
        if (size == 0 || size == 1) {
            KSValueParameter kSValueParameter = (KSValueParameter) CollectionsKt.firstOrNull(kSFunctionDeclaration.getParameters());
            if (!(kSValueParameter == null || (type = kSValueParameter.getType()) == null)) {
                str = type.toString();
            }
            return Boolean.valueOf(Intrinsics.areEqual((Object) str, (Object) "KType"));
        }
        this.logger.error("ConverterBinder cannot receive more then one argument", kSFunctionDeclaration);
        return null;
    }

    private final Dependencies createFileDependencies(KSFunctionDeclaration kSFunctionDeclaration) {
        KSFile containingFile = kSFunctionDeclaration.getContainingFile();
        if (containingFile == null) {
            return new Dependencies(false, new KSFile[0]);
        }
        return new Dependencies(false, containingFile);
    }

    private final ClassName resolveConverterType(KSFunctionDeclaration kSFunctionDeclaration) {
        ClassName className = this.clazz;
        if (className != null) {
            return className;
        }
        KSTypeReference returnType = kSFunctionDeclaration.getReturnType();
        KSType resolve = returnType != null ? returnType.resolve() : null;
        if (resolve == null) {
            this.logger.error("Cannot resolve return type", kSFunctionDeclaration);
            return null;
        } else if (resolve.getArguments().size() != 1) {
            this.logger.error("Incorrect return type", kSFunctionDeclaration);
            return null;
        } else {
            KSTypeReference type = ((KSTypeArgument) CollectionsKt.first(resolve.getArguments())).getType();
            KSType resolve2 = type != null ? type.resolve() : null;
            if (resolve2 != null) {
                return KsTypesKt.toClassName(resolve2);
            }
            this.logger.error("Cannot resolve converter inner type", kSFunctionDeclaration);
            return null;
        }
    }

    private final FileSpec generateConverterProvider(String str, String str2, KSFunctionDeclaration kSFunctionDeclaration, ClassName className, boolean z) {
        ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.Companion.get(new ClassName("expo.modules.kotlin.types", "TypeConverter"), className);
        ClassName className2 = new ClassName("kotlin.reflect", "KType");
        FileSpec.Builder builder = FileSpec.Companion.builder(str, str2);
        TypeSpec.Builder classBuilder = TypeSpec.Companion.classBuilder(str2);
        FunSpec.Builder returns$default = FunSpec.Builder.returns$default(FunSpec.Companion.builder(Config.converterProviderFunctionName).addParameter("type", (TypeName) className2, new KModifier[0]), (TypeName) parameterizedTypeName, (CodeBlock) null, 2, (Object) null);
        if (z) {
            String asString = kSFunctionDeclaration.getPackageName().asString();
            returns$default.addStatement("return " + asString + "." + kSFunctionDeclaration.getSimpleName().asString() + "(type)", new Object[0]);
        } else {
            String asString2 = kSFunctionDeclaration.getPackageName().asString();
            returns$default.addStatement("return " + asString2 + "." + kSFunctionDeclaration.getSimpleName().asString() + "()", new Object[0]);
        }
        Unit unit = Unit.INSTANCE;
        return builder.addType(classBuilder.addFunction(returns$default.build()).build()).build();
    }
}
