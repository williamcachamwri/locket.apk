package com.squareup.kotlinpoet;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 .2\u00020\u0001:\u0001.B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u0017\b\u0016\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bB\u001f\b\u0016\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\fB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fB\u0017\b\u0016\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u0010B7\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u0011J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\nHÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u000eHÆ\u0003J\t\u0010 \u001a\u00020\u0007HÆ\u0003J?\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0015\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0000¢\u0006\u0002\b&J\u0013\u0010'\u001a\u00020\u00072\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\u0006\u0010+\u001a\u00020,J\b\u0010-\u001a\u00020\u0003H\u0016R\u0011\u0010\u0012\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0017R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014¨\u0006/"}, d2 = {"Lcom/squareup/kotlinpoet/MemberName;", "", "packageName", "", "simpleName", "(Ljava/lang/String;Ljava/lang/String;)V", "isExtension", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "enclosingClassName", "Lcom/squareup/kotlinpoet/ClassName;", "(Lcom/squareup/kotlinpoet/ClassName;Ljava/lang/String;)V", "(Lcom/squareup/kotlinpoet/ClassName;Ljava/lang/String;Z)V", "operator", "Lcom/squareup/kotlinpoet/KOperator;", "(Ljava/lang/String;Lcom/squareup/kotlinpoet/KOperator;)V", "(Lcom/squareup/kotlinpoet/ClassName;Lcom/squareup/kotlinpoet/KOperator;)V", "(Ljava/lang/String;Lcom/squareup/kotlinpoet/ClassName;Ljava/lang/String;Lcom/squareup/kotlinpoet/KOperator;Z)V", "canonicalName", "getCanonicalName", "()Ljava/lang/String;", "getEnclosingClassName", "()Lcom/squareup/kotlinpoet/ClassName;", "()Z", "getOperator", "()Lcom/squareup/kotlinpoet/KOperator;", "getPackageName", "getSimpleName", "component1", "component2", "component3", "component4", "component5", "copy", "emit", "", "out", "Lcom/squareup/kotlinpoet/CodeWriter;", "emit$kotlinpoet", "equals", "other", "hashCode", "", "reference", "Lcom/squareup/kotlinpoet/CodeBlock;", "toString", "Companion", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: MemberName.kt */
public final class MemberName {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final String canonicalName;
    private final ClassName enclosingClassName;
    private final boolean isExtension;
    private final KOperator operator;
    private final String packageName;
    private final String simpleName;

    public static /* synthetic */ MemberName copy$default(MemberName memberName, String str, ClassName className, String str2, KOperator kOperator, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = memberName.packageName;
        }
        if ((i & 2) != 0) {
            className = memberName.enclosingClassName;
        }
        ClassName className2 = className;
        if ((i & 4) != 0) {
            str2 = memberName.simpleName;
        }
        String str3 = str2;
        if ((i & 8) != 0) {
            kOperator = memberName.operator;
        }
        KOperator kOperator2 = kOperator;
        if ((i & 16) != 0) {
            z = memberName.isExtension;
        }
        return memberName.copy(str, className2, str3, kOperator2, z);
    }

    @JvmStatic
    public static final MemberName get(Class<?> cls, String str) {
        return Companion.get(cls, str);
    }

    @JvmStatic
    public static final MemberName get(KClass<?> kClass, String str) {
        return Companion.get(kClass, str);
    }

    public final String component1() {
        return this.packageName;
    }

    public final ClassName component2() {
        return this.enclosingClassName;
    }

    public final String component3() {
        return this.simpleName;
    }

    public final KOperator component4() {
        return this.operator;
    }

    public final boolean component5() {
        return this.isExtension;
    }

    public final MemberName copy(String str, ClassName className, String str2, KOperator kOperator, boolean z) {
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        Intrinsics.checkNotNullParameter(str2, "simpleName");
        return new MemberName(str, className, str2, kOperator, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MemberName)) {
            return false;
        }
        MemberName memberName = (MemberName) obj;
        return Intrinsics.areEqual((Object) this.packageName, (Object) memberName.packageName) && Intrinsics.areEqual((Object) this.enclosingClassName, (Object) memberName.enclosingClassName) && Intrinsics.areEqual((Object) this.simpleName, (Object) memberName.simpleName) && this.operator == memberName.operator && this.isExtension == memberName.isExtension;
    }

    public int hashCode() {
        int hashCode = this.packageName.hashCode() * 31;
        ClassName className = this.enclosingClassName;
        int i = 0;
        int hashCode2 = (((hashCode + (className == null ? 0 : className.hashCode())) * 31) + this.simpleName.hashCode()) * 31;
        KOperator kOperator = this.operator;
        if (kOperator != null) {
            i = kOperator.hashCode();
        }
        int i2 = (hashCode2 + i) * 31;
        boolean z = this.isExtension;
        if (z) {
            z = true;
        }
        return i2 + (z ? 1 : 0);
    }

    public MemberName(String str, ClassName className, String str2, KOperator kOperator, boolean z) {
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        Intrinsics.checkNotNullParameter(str2, "simpleName");
        this.packageName = str;
        this.enclosingClassName = className;
        this.simpleName = str2;
        this.operator = kOperator;
        this.isExtension = z;
        StringBuilder sb = new StringBuilder();
        if (className != null) {
            sb.append(className.getCanonicalName());
            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        } else if (!StringsKt.isBlank(str)) {
            sb.append(str);
            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        }
        sb.append(str2);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        this.canonicalName = sb2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MemberName(String str, ClassName className, String str2, KOperator kOperator, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, className, str2, (i & 8) != 0 ? null : kOperator, (i & 16) != 0 ? false : z);
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final ClassName getEnclosingClassName() {
        return this.enclosingClassName;
    }

    public final String getSimpleName() {
        return this.simpleName;
    }

    public final KOperator getOperator() {
        return this.operator;
    }

    public final boolean isExtension() {
        return this.isExtension;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MemberName(String str, String str2) {
        this(str, (ClassName) null, str2, (KOperator) null, false, 24, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        Intrinsics.checkNotNullParameter(str2, "simpleName");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MemberName(String str, String str2, boolean z) {
        this(str, (ClassName) null, str2, (KOperator) null, z);
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        Intrinsics.checkNotNullParameter(str2, "simpleName");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MemberName(ClassName className, String str) {
        this(className.getPackageName(), className, str, (KOperator) null, false, 24, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(className, "enclosingClassName");
        Intrinsics.checkNotNullParameter(str, "simpleName");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MemberName(ClassName className, String str, boolean z) {
        this(className.getPackageName(), className, str, (KOperator) null, z);
        Intrinsics.checkNotNullParameter(className, "enclosingClassName");
        Intrinsics.checkNotNullParameter(str, "simpleName");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MemberName(String str, KOperator kOperator) {
        this(str, (ClassName) null, kOperator.getFunctionName$kotlinpoet(), kOperator, false, 16, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        Intrinsics.checkNotNullParameter(kOperator, "operator");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public MemberName(ClassName className, KOperator kOperator) {
        this(className.getPackageName(), className, kOperator.getFunctionName$kotlinpoet(), kOperator, false, 16, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(className, "enclosingClassName");
        Intrinsics.checkNotNullParameter(kOperator, "operator");
    }

    public final String getCanonicalName() {
        return this.canonicalName;
    }

    public final CodeBlock reference() {
        if (this.enclosingClassName == null) {
            return CodeBlock.Companion.of("::%M", this);
        }
        return CodeBlock.Companion.of("%T::%N", this.enclosingClassName, this.simpleName);
    }

    public final void emit$kotlinpoet(CodeWriter codeWriter) {
        Intrinsics.checkNotNullParameter(codeWriter, "out");
        if (this.operator == null) {
            CodeWriter.emit$default(codeWriter, UtilKt.escapeSegmentsIfNecessary$default(codeWriter.lookupName(this), 0, 1, (Object) null), false, 2, (Object) null);
            return;
        }
        codeWriter.lookupName(this);
        CodeWriter.emit$default(codeWriter, this.operator.getOperator$kotlinpoet(), false, 2, (Object) null);
    }

    public String toString() {
        return this.canonicalName;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\bJ\u001d\u0010\u0003\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\b\tJ\u001d\u0010\u0003\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¢\u0006\u0002\b\t¨\u0006\u000b"}, d2 = {"Lcom/squareup/kotlinpoet/MemberName$Companion;", "", "()V", "member", "Lcom/squareup/kotlinpoet/MemberName;", "Lcom/squareup/kotlinpoet/ClassName;", "simpleName", "", "Ljava/lang/Class;", "get", "Lkotlin/reflect/KClass;", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: MemberName.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final /* synthetic */ MemberName member(ClassName className, String str) {
            Intrinsics.checkNotNullParameter(className, "<this>");
            Intrinsics.checkNotNullParameter(str, "simpleName");
            return new MemberName(className, str);
        }

        @JvmStatic
        public final MemberName get(KClass<?> kClass, String str) {
            Intrinsics.checkNotNullParameter(kClass, "<this>");
            Intrinsics.checkNotNullParameter(str, "simpleName");
            return new MemberName(ClassNames.get(kClass), str);
        }

        @JvmStatic
        public final MemberName get(Class<?> cls, String str) {
            Intrinsics.checkNotNullParameter(cls, "<this>");
            Intrinsics.checkNotNullParameter(str, "simpleName");
            return new MemberName(ClassNames.get(cls), str);
        }
    }
}
