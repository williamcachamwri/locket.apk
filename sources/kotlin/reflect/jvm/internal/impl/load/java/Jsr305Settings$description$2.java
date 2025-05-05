package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: Jsr305Settings.kt */
final class Jsr305Settings$description$2 extends Lambda implements Function0<String[]> {
    final /* synthetic */ Jsr305Settings this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Jsr305Settings$description$2(Jsr305Settings jsr305Settings) {
        super(0);
        this.this$0 = jsr305Settings;
    }

    public final String[] invoke() {
        Jsr305Settings jsr305Settings = this.this$0;
        List createListBuilder = CollectionsKt.createListBuilder();
        createListBuilder.add(jsr305Settings.getGlobalLevel().getDescription());
        ReportLevel migrationLevel = jsr305Settings.getMigrationLevel();
        if (migrationLevel != null) {
            createListBuilder.add("under-migration:" + migrationLevel.getDescription());
        }
        for (Map.Entry next : jsr305Settings.getUserDefinedLevelForSpecificAnnotation().entrySet()) {
            createListBuilder.add("@" + next.getKey() + AbstractJsonLexerKt.COLON + ((ReportLevel) next.getValue()).getDescription());
        }
        return (String[]) CollectionsKt.build(createListBuilder).toArray(new String[0]);
    }
}
