package com.squareup.kotlinpoet;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0000H\u0002J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\f\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u000bHÖ\u0001J\b\u0010\u0014\u001a\u00020\u0003H\u0016R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\u0015"}, d2 = {"Lcom/squareup/kotlinpoet/Import;", "", "qualifiedName", "", "alias", "(Ljava/lang/String;Ljava/lang/String;)V", "getAlias", "()Ljava/lang/String;", "importString", "getQualifiedName", "compareTo", "", "other", "component1", "component2", "copy", "equals", "", "", "hashCode", "toString", "kotlinpoet"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* compiled from: Import.kt */
public final class Import implements Comparable<Import> {
    private final String alias;
    private final String importString;
    private final String qualifiedName;

    public static /* synthetic */ Import copy$default(Import importR, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = importR.qualifiedName;
        }
        if ((i & 2) != 0) {
            str2 = importR.alias;
        }
        return importR.copy(str, str2);
    }

    public final String component1() {
        return this.qualifiedName;
    }

    public final String component2() {
        return this.alias;
    }

    public final Import copy(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "qualifiedName");
        return new Import(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Import)) {
            return false;
        }
        Import importR = (Import) obj;
        return Intrinsics.areEqual((Object) this.qualifiedName, (Object) importR.qualifiedName) && Intrinsics.areEqual((Object) this.alias, (Object) importR.alias);
    }

    public int hashCode() {
        int hashCode = this.qualifiedName.hashCode() * 31;
        String str = this.alias;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public Import(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "qualifiedName");
        this.qualifiedName = str;
        this.alias = str2;
        StringBuilder sb = new StringBuilder();
        sb.append(UtilKt.escapeSegmentsIfNecessary$default(str, 0, 1, (Object) null));
        if (str2 != null) {
            sb.append("·as·" + UtilKt.escapeIfNecessary$default(str2, false, 1, (Object) null));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        this.importString = sb2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Import(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : str2);
    }

    public final String getQualifiedName() {
        return this.qualifiedName;
    }

    public final String getAlias() {
        return this.alias;
    }

    public String toString() {
        return this.importString;
    }

    public int compareTo(Import importR) {
        Intrinsics.checkNotNullParameter(importR, "other");
        return this.importString.compareTo(importR.importString);
    }
}
