package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* compiled from: Jsr305Settings.kt */
public final class Jsr305Settings {
    private final Lazy description$delegate;
    private final ReportLevel globalLevel;
    private final boolean isDisabled;
    private final ReportLevel migrationLevel;
    private final Map<FqName, ReportLevel> userDefinedLevelForSpecificAnnotation;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Jsr305Settings)) {
            return false;
        }
        Jsr305Settings jsr305Settings = (Jsr305Settings) obj;
        return this.globalLevel == jsr305Settings.globalLevel && this.migrationLevel == jsr305Settings.migrationLevel && Intrinsics.areEqual((Object) this.userDefinedLevelForSpecificAnnotation, (Object) jsr305Settings.userDefinedLevelForSpecificAnnotation);
    }

    public int hashCode() {
        int hashCode = this.globalLevel.hashCode() * 31;
        ReportLevel reportLevel = this.migrationLevel;
        return ((hashCode + (reportLevel == null ? 0 : reportLevel.hashCode())) * 31) + this.userDefinedLevelForSpecificAnnotation.hashCode();
    }

    public String toString() {
        return "Jsr305Settings(globalLevel=" + this.globalLevel + ", migrationLevel=" + this.migrationLevel + ", userDefinedLevelForSpecificAnnotation=" + this.userDefinedLevelForSpecificAnnotation + ')';
    }

    public Jsr305Settings(ReportLevel reportLevel, ReportLevel reportLevel2, Map<FqName, ? extends ReportLevel> map) {
        Intrinsics.checkNotNullParameter(reportLevel, "globalLevel");
        Intrinsics.checkNotNullParameter(map, "userDefinedLevelForSpecificAnnotation");
        this.globalLevel = reportLevel;
        this.migrationLevel = reportLevel2;
        this.userDefinedLevelForSpecificAnnotation = map;
        this.description$delegate = LazyKt.lazy(new Jsr305Settings$description$2(this));
        this.isDisabled = reportLevel == ReportLevel.IGNORE && reportLevel2 == ReportLevel.IGNORE && map.isEmpty();
    }

    public final ReportLevel getGlobalLevel() {
        return this.globalLevel;
    }

    public final ReportLevel getMigrationLevel() {
        return this.migrationLevel;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Jsr305Settings(ReportLevel reportLevel, ReportLevel reportLevel2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reportLevel, (i & 2) != 0 ? null : reportLevel2, (i & 4) != 0 ? MapsKt.emptyMap() : map);
    }

    public final Map<FqName, ReportLevel> getUserDefinedLevelForSpecificAnnotation() {
        return this.userDefinedLevelForSpecificAnnotation;
    }

    public final boolean isDisabled() {
        return this.isDisabled;
    }
}
