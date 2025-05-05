package expo.modules.kotlin.classcomponent;

import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.objects.ObjectDefinitionData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lexpo/modules/kotlin/classcomponent/ClassDefinitionData;", "", "name", "", "constructor", "Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "objectDefinition", "Lexpo/modules/kotlin/objects/ObjectDefinitionData;", "(Ljava/lang/String;Lexpo/modules/kotlin/functions/SyncFunctionComponent;Lexpo/modules/kotlin/objects/ObjectDefinitionData;)V", "getConstructor", "()Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "getName", "()Ljava/lang/String;", "getObjectDefinition", "()Lexpo/modules/kotlin/objects/ObjectDefinitionData;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ClassDefinitionData.kt */
public final class ClassDefinitionData {
    private final SyncFunctionComponent constructor;
    private final String name;
    private final ObjectDefinitionData objectDefinition;

    public ClassDefinitionData(String str, SyncFunctionComponent syncFunctionComponent, ObjectDefinitionData objectDefinitionData) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(syncFunctionComponent, "constructor");
        Intrinsics.checkNotNullParameter(objectDefinitionData, "objectDefinition");
        this.name = str;
        this.constructor = syncFunctionComponent;
        this.objectDefinition = objectDefinitionData;
    }

    public final String getName() {
        return this.name;
    }

    public final SyncFunctionComponent getConstructor() {
        return this.constructor;
    }

    public final ObjectDefinitionData getObjectDefinition() {
        return this.objectDefinition;
    }
}
