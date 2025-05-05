package expo.modules.kotlin.modules;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import expo.modules.kotlin.activityresult.AppContextActivityResultCaller;
import expo.modules.kotlin.classcomponent.ClassComponentBuilder;
import expo.modules.kotlin.classcomponent.ClassDefinitionData;
import expo.modules.kotlin.events.BasicEventListener;
import expo.modules.kotlin.events.EventListener;
import expo.modules.kotlin.events.EventListenerWithPayload;
import expo.modules.kotlin.events.EventListenerWithSenderAndPayload;
import expo.modules.kotlin.events.EventName;
import expo.modules.kotlin.events.OnActivityResultPayload;
import expo.modules.kotlin.objects.ObjectDefinitionBuilder;
import expo.modules.kotlin.sharedobjects.SharedObject;
import expo.modules.kotlin.types.LazyKType;
import expo.modules.kotlin.views.ViewDefinitionBuilder;
import expo.modules.kotlin.views.ViewManagerDefinition;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J5\u0010.\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u00192\u001f\b\u0002\u0010/\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e01\u0012\u0004\u0012\u00020\u001e00¢\u0006\u0002\b H\bø\u0001\u0000JG\u0010.\u001a\u00020\u001e\"\n\b\u0000\u00102\u0018\u0001*\u0002032\f\u00104\u001a\b\u0012\u0004\u0012\u0002H2052\u001f\b\u0002\u0010/\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H201\u0012\u0004\u0012\u00020\u001e00¢\u0006\u0002\b H\bø\u0001\u0000J\u000e\u00106\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u0019J\u001c\u00107\u001a\u00020\u001e2\u000e\b\u0004\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001e08H\bø\u0001\u0000J\u001c\u00109\u001a\u00020\u001e2\u000e\b\u0004\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001e08H\bø\u0001\u0000J\u001c\u0010:\u001a\u00020\u001e2\u000e\b\u0004\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001e08H\bø\u0001\u0000J(\u0010;\u001a\u00020\u001e2\u001a\b\u0004\u0010/\u001a\u0014\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u00020\u001e0\u001bH\bø\u0001\u0000J\u001c\u0010>\u001a\u00020\u001e2\u000e\b\u0004\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001e08H\bø\u0001\u0000J\u001c\u0010?\u001a\u00020\u001e2\u000e\b\u0004\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001e08H\bø\u0001\u0000J\"\u0010@\u001a\u00020\u001e2\u0014\b\u0004\u0010/\u001a\u000e\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020\u001e00H\bø\u0001\u0000J4\u0010B\u001a\u00020\u001e2'\u0010/\u001a#\b\u0001\u0012\u0004\u0012\u00020\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u001f0\u001b¢\u0006\u0002\b ¢\u0006\u0002\u0010%JE\u0010C\u001a\u00020\u001e\"\n\b\u0000\u0010D\u0018\u0001*\u00020E2\f\u0010F\u001a\b\u0012\u0004\u0012\u0002HD052\u001d\u0010/\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002HD0G\u0012\u0004\u0012\u00020\u001e00¢\u0006\u0002\b H\bø\u0001\u0000J\u0006\u0010H\u001a\u00020IR*\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR(\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f8\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\t\u001a\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0015\u0010\t\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000RI\u0010\u001a\u001a%\b\u0001\u0012\u0004\u0012\u00020\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0018\u00010\u001b¢\u0006\u0002\b 8\u0000@\u0000X\u000e¢\u0006\u0016\n\u0002\u0010&\u0012\u0004\b!\u0010\t\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R&\u0010'\u001a\u0004\u0018\u00010(8\u0000@\u0000X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b)\u0010\t\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-\u0002\u0007\n\u0005\b20\u0001¨\u0006J"}, d2 = {"Lexpo/modules/kotlin/modules/ModuleDefinitionBuilder;", "Lexpo/modules/kotlin/objects/ObjectDefinitionBuilder;", "module", "Lexpo/modules/kotlin/modules/Module;", "(Lexpo/modules/kotlin/modules/Module;)V", "classData", "", "Lexpo/modules/kotlin/classcomponent/ClassDefinitionData;", "getClassData$annotations", "()V", "getClassData", "()Ljava/util/List;", "setClassData", "(Ljava/util/List;)V", "eventListeners", "", "Lexpo/modules/kotlin/events/EventName;", "Lexpo/modules/kotlin/events/EventListener;", "getEventListeners$annotations", "getEventListeners", "()Ljava/util/Map;", "getModule$annotations", "getModule", "()Lexpo/modules/kotlin/modules/Module;", "name", "", "registerContracts", "Lkotlin/Function2;", "Lexpo/modules/kotlin/activityresult/AppContextActivityResultCaller;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "getRegisterContracts$annotations", "getRegisterContracts", "()Lkotlin/jvm/functions/Function2;", "setRegisterContracts", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "viewManagerDefinition", "Lexpo/modules/kotlin/views/ViewManagerDefinition;", "getViewManagerDefinition$annotations", "getViewManagerDefinition", "()Lexpo/modules/kotlin/views/ViewManagerDefinition;", "setViewManagerDefinition", "(Lexpo/modules/kotlin/views/ViewManagerDefinition;)V", "Class", "body", "Lkotlin/Function1;", "Lexpo/modules/kotlin/classcomponent/ClassComponentBuilder;", "SharedObjectType", "Lexpo/modules/kotlin/sharedobjects/SharedObject;", "sharedObjectClass", "Lkotlin/reflect/KClass;", "Name", "OnActivityDestroys", "Lkotlin/Function0;", "OnActivityEntersBackground", "OnActivityEntersForeground", "OnActivityResult", "Landroid/app/Activity;", "Lexpo/modules/kotlin/events/OnActivityResultPayload;", "OnCreate", "OnDestroy", "OnNewIntent", "Landroid/content/Intent;", "RegisterActivityContracts", "View", "T", "Landroid/view/View;", "viewClass", "Lexpo/modules/kotlin/views/ViewDefinitionBuilder;", "buildModule", "Lexpo/modules/kotlin/modules/ModuleDefinitionData;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@DefinitionMarker
/* compiled from: ModuleDefinitionBuilder.kt */
public final class ModuleDefinitionBuilder extends ObjectDefinitionBuilder {
    private List<ClassDefinitionData> classData;
    private final Map<EventName, EventListener> eventListeners;
    private final Module module;
    private String name;
    private Function2<? super AppContextActivityResultCaller, ? super Continuation<? super Unit>, ? extends Object> registerContracts;
    private ViewManagerDefinition viewManagerDefinition;

    public ModuleDefinitionBuilder() {
        this((Module) null, 1, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ void getClassData$annotations() {
    }

    public static /* synthetic */ void getEventListeners$annotations() {
    }

    public static /* synthetic */ void getModule$annotations() {
    }

    public static /* synthetic */ void getRegisterContracts$annotations() {
    }

    public static /* synthetic */ void getViewManagerDefinition$annotations() {
    }

    public ModuleDefinitionBuilder(Module module2) {
        this.module = module2;
        this.eventListeners = new LinkedHashMap();
        this.classData = new ArrayList();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ModuleDefinitionBuilder(Module module2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : module2);
    }

    public final Module getModule() {
        return this.module;
    }

    public final ViewManagerDefinition getViewManagerDefinition() {
        return this.viewManagerDefinition;
    }

    public final void setViewManagerDefinition(ViewManagerDefinition viewManagerDefinition2) {
        this.viewManagerDefinition = viewManagerDefinition2;
    }

    public final Map<EventName, EventListener> getEventListeners() {
        return this.eventListeners;
    }

    public final Function2<AppContextActivityResultCaller, Continuation<? super Unit>, Object> getRegisterContracts() {
        return this.registerContracts;
    }

    public final void setRegisterContracts(Function2<? super AppContextActivityResultCaller, ? super Continuation<? super Unit>, ? extends Object> function2) {
        this.registerContracts = function2;
    }

    public final List<ClassDefinitionData> getClassData() {
        return this.classData;
    }

    public final void setClassData(List<ClassDefinitionData> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.classData = list;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0008, code lost:
        r0 = r0.getClass();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final expo.modules.kotlin.modules.ModuleDefinitionData buildModule() {
        /*
            r8 = this;
            java.lang.String r0 = r8.name
            if (r0 != 0) goto L_0x0014
            expo.modules.kotlin.modules.Module r0 = r8.module
            if (r0 == 0) goto L_0x0013
            java.lang.Class r0 = r0.getClass()
            if (r0 == 0) goto L_0x0013
            java.lang.String r0 = r0.getSimpleName()
            goto L_0x0014
        L_0x0013:
            r0 = 0
        L_0x0014:
            r2 = r0
            expo.modules.kotlin.modules.ModuleDefinitionData r0 = new expo.modules.kotlin.modules.ModuleDefinitionData
            if (r2 == 0) goto L_0x002a
            expo.modules.kotlin.objects.ObjectDefinitionData r3 = r8.buildObject()
            expo.modules.kotlin.views.ViewManagerDefinition r4 = r8.viewManagerDefinition
            java.util.Map<expo.modules.kotlin.events.EventName, expo.modules.kotlin.events.EventListener> r5 = r8.eventListeners
            kotlin.jvm.functions.Function2<? super expo.modules.kotlin.activityresult.AppContextActivityResultCaller, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r6 = r8.registerContracts
            java.util.List<expo.modules.kotlin.classcomponent.ClassDefinitionData> r7 = r8.classData
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6, r7)
            return r0
        L_0x002a:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Required value was null."
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.modules.ModuleDefinitionBuilder.buildModule():expo.modules.kotlin.modules.ModuleDefinitionData");
    }

    public final void Name(String str) {
        Intrinsics.checkNotNullParameter(str, "name");
        this.name = str;
    }

    public final /* synthetic */ <T extends View> void View(KClass<T> kClass, Function1<? super ViewDefinitionBuilder<T>, Unit> function1) {
        Intrinsics.checkNotNullParameter(kClass, "viewClass");
        Intrinsics.checkNotNullParameter(function1, TtmlNode.TAG_BODY);
        if (getViewManagerDefinition() == null) {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(View.class);
            Intrinsics.needClassReification();
            ViewDefinitionBuilder viewDefinitionBuilder = new ViewDefinitionBuilder(kClass, new LazyKType(orCreateKotlinClass, false, ModuleDefinitionBuilder$View$viewDefinitionBuilder$1.INSTANCE, 2, (DefaultConstructorMarker) null));
            function1.invoke(viewDefinitionBuilder);
            setViewManagerDefinition(viewDefinitionBuilder.build());
            return;
        }
        throw new IllegalArgumentException("The module definition may have exported only one view manager.".toString());
    }

    public final void OnCreate(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, TtmlNode.TAG_BODY);
        getEventListeners().put(EventName.MODULE_CREATE, new BasicEventListener(EventName.MODULE_CREATE, new ModuleDefinitionBuilder$OnCreate$1(function0)));
    }

    public final void RegisterActivityContracts(Function2<? super AppContextActivityResultCaller, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(function2, TtmlNode.TAG_BODY);
        this.registerContracts = function2;
    }

    public final void OnDestroy(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, TtmlNode.TAG_BODY);
        getEventListeners().put(EventName.MODULE_DESTROY, new BasicEventListener(EventName.MODULE_DESTROY, new ModuleDefinitionBuilder$OnDestroy$1(function0)));
    }

    public final void OnActivityEntersForeground(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, TtmlNode.TAG_BODY);
        getEventListeners().put(EventName.ACTIVITY_ENTERS_FOREGROUND, new BasicEventListener(EventName.ACTIVITY_ENTERS_FOREGROUND, new ModuleDefinitionBuilder$OnActivityEntersForeground$1(function0)));
    }

    public final void OnActivityEntersBackground(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, TtmlNode.TAG_BODY);
        getEventListeners().put(EventName.ACTIVITY_ENTERS_BACKGROUND, new BasicEventListener(EventName.ACTIVITY_ENTERS_BACKGROUND, new ModuleDefinitionBuilder$OnActivityEntersBackground$1(function0)));
    }

    public final void OnActivityDestroys(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, TtmlNode.TAG_BODY);
        getEventListeners().put(EventName.ACTIVITY_DESTROYS, new BasicEventListener(EventName.ACTIVITY_DESTROYS, new ModuleDefinitionBuilder$OnActivityDestroys$1(function0)));
    }

    public final void OnNewIntent(Function1<? super Intent, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, TtmlNode.TAG_BODY);
        getEventListeners().put(EventName.ON_NEW_INTENT, new EventListenerWithPayload(EventName.ON_NEW_INTENT, new ModuleDefinitionBuilder$OnNewIntent$1(function1)));
    }

    public final void OnActivityResult(Function2<? super Activity, ? super OnActivityResultPayload, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, TtmlNode.TAG_BODY);
        getEventListeners().put(EventName.ON_ACTIVITY_RESULT, new EventListenerWithSenderAndPayload(EventName.ON_ACTIVITY_RESULT, new ModuleDefinitionBuilder$OnActivityResult$1(function2)));
    }

    public static /* synthetic */ void Class$default(ModuleDefinitionBuilder moduleDefinitionBuilder, String str, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = ModuleDefinitionBuilder$Class$1.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function1, TtmlNode.TAG_BODY);
        ClassComponentBuilder classComponentBuilder = new ClassComponentBuilder(str, Reflection.getOrCreateKotlinClass(Unit.class), Reflection.typeOf(Unit.class));
        function1.invoke(classComponentBuilder);
        moduleDefinitionBuilder.getClassData().add(classComponentBuilder.buildClass());
    }

    public final void Class(String str, Function1<? super ClassComponentBuilder<Unit>, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(function1, TtmlNode.TAG_BODY);
        ClassComponentBuilder classComponentBuilder = new ClassComponentBuilder(str, Reflection.getOrCreateKotlinClass(Unit.class), Reflection.typeOf(Unit.class));
        function1.invoke(classComponentBuilder);
        getClassData().add(classComponentBuilder.buildClass());
    }

    public static /* synthetic */ void Class$default(ModuleDefinitionBuilder moduleDefinitionBuilder, KClass kClass, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            Intrinsics.needClassReification();
            function1 = ModuleDefinitionBuilder$Class$2.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(kClass, "sharedObjectClass");
        Intrinsics.checkNotNullParameter(function1, TtmlNode.TAG_BODY);
        String simpleName = JvmClassMappingKt.getJavaClass(kClass).getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        String str = simpleName;
        Intrinsics.reifiedOperationMarker(6, "SharedObjectType");
        ClassComponentBuilder classComponentBuilder = new ClassComponentBuilder(simpleName, kClass, (KType) null);
        function1.invoke(classComponentBuilder);
        moduleDefinitionBuilder.getClassData().add(classComponentBuilder.buildClass());
    }

    public final /* synthetic */ <SharedObjectType extends SharedObject> void Class(KClass<SharedObjectType> kClass, Function1<? super ClassComponentBuilder<SharedObjectType>, Unit> function1) {
        Intrinsics.checkNotNullParameter(kClass, "sharedObjectClass");
        Intrinsics.checkNotNullParameter(function1, TtmlNode.TAG_BODY);
        String simpleName = JvmClassMappingKt.getJavaClass(kClass).getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "getSimpleName(...)");
        String str = simpleName;
        Intrinsics.reifiedOperationMarker(6, "SharedObjectType");
        ClassComponentBuilder classComponentBuilder = new ClassComponentBuilder(simpleName, kClass, (KType) null);
        function1.invoke(classComponentBuilder);
        getClassData().add(classComponentBuilder.buildClass());
    }
}
