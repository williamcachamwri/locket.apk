package expo.modules.kotlin.modules;

import expo.modules.kotlin.ConcatIterator;
import expo.modules.kotlin.activityresult.AppContextActivityResultCaller;
import expo.modules.kotlin.classcomponent.ClassDefinitionData;
import expo.modules.kotlin.events.EventListener;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.events.EventsDefinition;
import expo.modules.kotlin.functions.AnyFunction;
import expo.modules.kotlin.functions.BaseAsyncFunctionComponent;
import expo.modules.kotlin.functions.SyncFunctionComponent;
import expo.modules.kotlin.objects.ObjectDefinitionData;
import expo.modules.kotlin.objects.PropertyComponent;
import expo.modules.kotlin.views.ViewManagerDefinition;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001Bt\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u0012+\b\u0002\u0010\f\u001a%\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\r¢\u0006\u0002\b\u0011\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\u0002\u0010\u0015R\u001d\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00170\t¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR%\u0010\u001c\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t0\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0013\u0010!\u001a\u0004\u0018\u00010\"¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u001d\u0010.\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020/0\t¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0019R6\u0010\f\u001a%\b\u0001\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\r¢\u0006\u0002\b\u0011¢\u0006\n\n\u0002\u00103\u001a\u0004\b1\u00102R\u001d\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002050\t¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0019R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b7\u00108¨\u00069"}, d2 = {"Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "", "name", "", "objectDefinition", "Lexpo/modules/kotlin/objects/ObjectDefinitionData;", "viewManagerDefinition", "Lexpo/modules/kotlin/views/ViewManagerDefinition;", "eventListeners", "", "Lexpo/modules/kotlin/events/EventName;", "Lexpo/modules/kotlin/events/EventListener;", "registerContracts", "Lkotlin/Function2;", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultCaller;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "classData", "", "Lexpo/modules/kotlin/classcomponent/ClassDefinitionData;", "(Ljava/lang/String;Lexpo/modules/kotlin/objects/ObjectDefinitionData;Lexpo/modules/kotlin/views/ViewManagerDefinition;Ljava/util/Map;Lkotlin/jvm/functions/Function2;Ljava/util/List;)V", "asyncFunctions", "Lexpo/modules/kotlin/functions/BaseAsyncFunctionComponent;", "getAsyncFunctions", "()Ljava/util/Map;", "getClassData", "()Ljava/util/List;", "constantsProvider", "Lkotlin/Function0;", "getConstantsProvider", "()Lkotlin/jvm/functions/Function0;", "getEventListeners", "eventsDefinition", "Lexpo/modules/kotlin/events/EventsDefinition;", "getEventsDefinition", "()Lexpo/modules/kotlin/events/EventsDefinition;", "functions", "Lexpo/modules/kotlin/ConcatIterator;", "Lexpo/modules/kotlin/functions/AnyFunction;", "getFunctions", "()Lexpo/modules/kotlin/ConcatIterator;", "getName", "()Ljava/lang/String;", "getObjectDefinition", "()Lexpo/modules/kotlin/objects/ObjectDefinitionData;", "properties", "Lexpo/modules/kotlin/objects/PropertyComponent;", "getProperties", "getRegisterContracts", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "syncFunctions", "Lexpo/modules/kotlin/functions/SyncFunctionComponent;", "getSyncFunctions", "getViewManagerDefinition", "()Lexpo/modules/kotlin/views/ViewManagerDefinition;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModuleDefinitionData.kt */
public final class ModuleDefinitionData {
    private final Map<String, BaseAsyncFunctionComponent> asyncFunctions;
    private final List<ClassDefinitionData> classData;
    private final Function0<Map<String, Object>> constantsProvider;
    private final Map<EventName, EventListener> eventListeners;
    private final EventsDefinition eventsDefinition;
    private final ConcatIterator<AnyFunction> functions;
    private final String name;
    private final ObjectDefinitionData objectDefinition;
    private final Map<String, PropertyComponent> properties;
    private final Function2<AppContextActivityResultCaller, Continuation<? super Unit>, Object> registerContracts;
    private final Map<String, SyncFunctionComponent> syncFunctions;
    private final ViewManagerDefinition viewManagerDefinition;

    public ModuleDefinitionData(String str, ObjectDefinitionData objectDefinitionData, ViewManagerDefinition viewManagerDefinition2, Map<EventName, ? extends EventListener> map, Function2<? super AppContextActivityResultCaller, ? super Continuation<? super Unit>, ? extends Object> function2, List<ClassDefinitionData> list) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(objectDefinitionData, "objectDefinition");
        Intrinsics.checkNotNullParameter(map, "eventListeners");
        Intrinsics.checkNotNullParameter(list, "classData");
        this.name = str;
        this.objectDefinition = objectDefinitionData;
        this.viewManagerDefinition = viewManagerDefinition2;
        this.eventListeners = map;
        this.registerContracts = function2;
        this.classData = list;
        this.constantsProvider = objectDefinitionData.getConstantsProvider();
        this.syncFunctions = objectDefinitionData.getSyncFunctions();
        this.asyncFunctions = objectDefinitionData.getAsyncFunctions();
        this.eventsDefinition = objectDefinitionData.getEventsDefinition();
        this.properties = objectDefinitionData.getProperties();
        this.functions = objectDefinitionData.getFunctions();
    }

    public final String getName() {
        return this.name;
    }

    public final ObjectDefinitionData getObjectDefinition() {
        return this.objectDefinition;
    }

    public final ViewManagerDefinition getViewManagerDefinition() {
        return this.viewManagerDefinition;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ ModuleDefinitionData(java.lang.String r10, expo.modules.kotlin.objects.ObjectDefinitionData r11, expo.modules.kotlin.views.ViewManagerDefinition r12, java.util.Map r13, kotlin.jvm.functions.Function2 r14, java.util.List r15, int r16, kotlin.jvm.internal.DefaultConstructorMarker r17) {
        /*
            r9 = this;
            r0 = r16 & 4
            r1 = 0
            if (r0 == 0) goto L_0x0007
            r5 = r1
            goto L_0x0008
        L_0x0007:
            r5 = r12
        L_0x0008:
            r0 = r16 & 8
            if (r0 == 0) goto L_0x0012
            java.util.Map r0 = kotlin.collections.MapsKt.emptyMap()
            r6 = r0
            goto L_0x0013
        L_0x0012:
            r6 = r13
        L_0x0013:
            r0 = r16 & 16
            if (r0 == 0) goto L_0x0019
            r7 = r1
            goto L_0x001a
        L_0x0019:
            r7 = r14
        L_0x001a:
            r0 = r16 & 32
            if (r0 == 0) goto L_0x0024
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
            r8 = r0
            goto L_0x0025
        L_0x0024:
            r8 = r15
        L_0x0025:
            r2 = r9
            r3 = r10
            r4 = r11
            r2.<init>(r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.modules.ModuleDefinitionData.<init>(java.lang.String, expo.modules.kotlin.objects.ObjectDefinitionData, expo.modules.kotlin.views.ViewManagerDefinition, java.util.Map, kotlin.jvm.functions.Function2, java.util.List, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final Map<EventName, EventListener> getEventListeners() {
        return this.eventListeners;
    }

    public final Function2<AppContextActivityResultCaller, Continuation<? super Unit>, Object> getRegisterContracts() {
        return this.registerContracts;
    }

    public final List<ClassDefinitionData> getClassData() {
        return this.classData;
    }

    public final Function0<Map<String, Object>> getConstantsProvider() {
        return this.constantsProvider;
    }

    public final Map<String, SyncFunctionComponent> getSyncFunctions() {
        return this.syncFunctions;
    }

    public final Map<String, BaseAsyncFunctionComponent> getAsyncFunctions() {
        return this.asyncFunctions;
    }

    public final EventsDefinition getEventsDefinition() {
        return this.eventsDefinition;
    }

    public final Map<String, PropertyComponent> getProperties() {
        return this.properties;
    }

    public final ConcatIterator<AnyFunction> getFunctions() {
        return this.functions;
    }
}
