package kotlin.reflect.jvm.internal.impl.renderer;

import java.lang.reflect.Field;
import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.Delegates;
import kotlin.properties.ObservableProperty;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.renderer.ClassifierNamePolicy;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.text.StringsKt;

/* compiled from: DescriptorRendererOptionsImpl.kt */
public final class DescriptorRendererOptionsImpl implements DescriptorRendererOptions {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private final ReadWriteProperty actualPropertiesInPrimaryConstructor$delegate = property(false);
    private final ReadWriteProperty alwaysRenderModifiers$delegate = property(false);
    private final ReadWriteProperty annotationArgumentsRenderingPolicy$delegate = property(AnnotationArgumentsRenderingPolicy.NO_ARGUMENTS);
    private final ReadWriteProperty annotationFilter$delegate = property((Object) null);
    private final ReadWriteProperty boldOnlyForNamesInHtml$delegate = property(false);
    private final ReadWriteProperty classWithPrimaryConstructor$delegate = property(false);
    private final ReadWriteProperty classifierNamePolicy$delegate = property(ClassifierNamePolicy.SOURCE_CODE_QUALIFIED.INSTANCE);
    private final ReadWriteProperty debugMode$delegate = property(false);
    private final ReadWriteProperty defaultParameterValueRenderer$delegate = property(DescriptorRendererOptionsImpl$defaultParameterValueRenderer$2.INSTANCE);
    private final ReadWriteProperty eachAnnotationOnNewLine$delegate = property(false);
    private final ReadWriteProperty enhancedTypes$delegate = property(false);
    private final ReadWriteProperty excludedAnnotationClasses$delegate = property(SetsKt.emptySet());
    private final ReadWriteProperty excludedTypeAnnotationClasses$delegate = property(ExcludedTypeAnnotations.INSTANCE.getInternalAnnotationsForResolve());
    private final ReadWriteProperty includeAdditionalModifiers$delegate = property(true);
    private final ReadWriteProperty includePropertyConstant$delegate = property(false);
    private final ReadWriteProperty informativeErrorType$delegate = property(true);
    private boolean isLocked;
    private final ReadWriteProperty modifiers$delegate = property(DescriptorRendererModifier.ALL_EXCEPT_ANNOTATIONS);
    private final ReadWriteProperty normalizedVisibilities$delegate = property(false);
    private final ReadWriteProperty overrideRenderingPolicy$delegate = property(OverrideRenderingPolicy.RENDER_OPEN);
    private final ReadWriteProperty parameterNameRenderingPolicy$delegate = property(ParameterNameRenderingPolicy.ALL);
    private final ReadWriteProperty parameterNamesInFunctionalTypes$delegate = property(true);
    private final ReadWriteProperty presentableUnresolvedTypes$delegate = property(false);
    private final ReadWriteProperty propertyAccessorRenderingPolicy$delegate = property(PropertyAccessorRenderingPolicy.DEBUG);
    private final ReadWriteProperty propertyConstantRenderer$delegate = property((Object) null);
    private final ReadWriteProperty receiverAfterName$delegate = property(false);
    private final ReadWriteProperty renderCompanionObjectName$delegate = property(false);
    private final ReadWriteProperty renderConstructorDelegation$delegate = property(false);
    private final ReadWriteProperty renderConstructorKeyword$delegate = property(true);
    private final ReadWriteProperty renderDefaultAnnotationArguments$delegate = property(false);
    private final ReadWriteProperty renderDefaultModality$delegate = property(true);
    private final ReadWriteProperty renderDefaultVisibility$delegate = property(true);
    private final ReadWriteProperty renderFunctionContracts$delegate = property(false);
    private final ReadWriteProperty renderPrimaryConstructorParametersAsProperties$delegate = property(false);
    private final ReadWriteProperty renderTypeExpansions$delegate = property(false);
    private final ReadWriteProperty renderUnabbreviatedType$delegate = property(true);
    private final ReadWriteProperty secondaryConstructorsAsPrimary$delegate = property(true);
    private final ReadWriteProperty startFromDeclarationKeyword$delegate = property(false);
    private final ReadWriteProperty startFromName$delegate = property(false);
    private final ReadWriteProperty textFormat$delegate = property(RenderingFormat.PLAIN);
    private final ReadWriteProperty typeNormalizer$delegate = property(DescriptorRendererOptionsImpl$typeNormalizer$2.INSTANCE);
    private final ReadWriteProperty uninferredTypeParameterAsName$delegate = property(false);
    private final ReadWriteProperty unitReturnType$delegate = property(true);
    private final ReadWriteProperty valueParametersHandler$delegate = property(DescriptorRenderer.ValueParametersHandler.DEFAULT.INSTANCE);
    private final ReadWriteProperty verbose$delegate = property(false);
    private final ReadWriteProperty withDefinedIn$delegate = property(true);
    private final ReadWriteProperty withSourceFileForTopLevel$delegate = property(true);
    private final ReadWriteProperty withoutReturnType$delegate = property(false);
    private final ReadWriteProperty withoutSuperTypes$delegate = property(false);
    private final ReadWriteProperty withoutTypeParameters$delegate = property(false);

    public boolean getIncludeAnnotationArguments() {
        return DescriptorRendererOptions.DefaultImpls.getIncludeAnnotationArguments(this);
    }

    public boolean getIncludeEmptyAnnotationArguments() {
        return DescriptorRendererOptions.DefaultImpls.getIncludeEmptyAnnotationArguments(this);
    }

    public final boolean isLocked() {
        return this.isLocked;
    }

    public final void lock() {
        this.isLocked = true;
    }

    public final DescriptorRendererOptionsImpl copy() {
        DescriptorRendererOptionsImpl descriptorRendererOptionsImpl = new DescriptorRendererOptionsImpl();
        Field[] declaredFields = getClass().getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue(declaredFields, "getDeclaredFields(...)");
        for (Field field : declaredFields) {
            if ((field.getModifiers() & 8) == 0) {
                field.setAccessible(true);
                Object obj = field.get(this);
                ObservableProperty observableProperty = obj instanceof ObservableProperty ? (ObservableProperty) obj : null;
                if (observableProperty != null) {
                    String name = field.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                    StringsKt.startsWith$default(name, "is", false, 2, (Object) null);
                    KDeclarationContainer orCreateKotlinClass = Reflection.getOrCreateKotlinClass(DescriptorRendererOptionsImpl.class);
                    String name2 = field.getName();
                    StringBuilder sb = new StringBuilder("get");
                    String name3 = field.getName();
                    Intrinsics.checkNotNullExpressionValue(name3, "getName(...)");
                    if (name3.length() > 0) {
                        char upperCase = Character.toUpperCase(name3.charAt(0));
                        String substring = name3.substring(1);
                        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
                        name3 = upperCase + substring;
                    }
                    field.set(descriptorRendererOptionsImpl, descriptorRendererOptionsImpl.property(observableProperty.getValue(this, new PropertyReference1Impl(orCreateKotlinClass, name2, sb.append(name3).toString()))));
                }
            }
        }
        return descriptorRendererOptionsImpl;
    }

    private final <T> ReadWriteProperty<DescriptorRendererOptionsImpl, T> property(T t) {
        Delegates delegates = Delegates.INSTANCE;
        return new DescriptorRendererOptionsImpl$property$$inlined$vetoable$1(t, this);
    }

    static {
        Class<DescriptorRendererOptionsImpl> cls = DescriptorRendererOptionsImpl.class;
        $$delegatedProperties = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "classifierNamePolicy", "getClassifierNamePolicy()Lorg/jetbrains/kotlin/renderer/ClassifierNamePolicy;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "withDefinedIn", "getWithDefinedIn()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "withSourceFileForTopLevel", "getWithSourceFileForTopLevel()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "modifiers", "getModifiers()Ljava/util/Set;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "startFromName", "getStartFromName()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "startFromDeclarationKeyword", "getStartFromDeclarationKeyword()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "debugMode", "getDebugMode()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "classWithPrimaryConstructor", "getClassWithPrimaryConstructor()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "verbose", "getVerbose()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "unitReturnType", "getUnitReturnType()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "withoutReturnType", "getWithoutReturnType()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "enhancedTypes", "getEnhancedTypes()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "normalizedVisibilities", "getNormalizedVisibilities()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "renderDefaultVisibility", "getRenderDefaultVisibility()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "renderDefaultModality", "getRenderDefaultModality()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "renderConstructorDelegation", "getRenderConstructorDelegation()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "renderPrimaryConstructorParametersAsProperties", "getRenderPrimaryConstructorParametersAsProperties()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "actualPropertiesInPrimaryConstructor", "getActualPropertiesInPrimaryConstructor()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "uninferredTypeParameterAsName", "getUninferredTypeParameterAsName()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "includePropertyConstant", "getIncludePropertyConstant()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "propertyConstantRenderer", "getPropertyConstantRenderer()Lkotlin/jvm/functions/Function1;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "withoutTypeParameters", "getWithoutTypeParameters()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "withoutSuperTypes", "getWithoutSuperTypes()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "typeNormalizer", "getTypeNormalizer()Lkotlin/jvm/functions/Function1;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "defaultParameterValueRenderer", "getDefaultParameterValueRenderer()Lkotlin/jvm/functions/Function1;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "secondaryConstructorsAsPrimary", "getSecondaryConstructorsAsPrimary()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "overrideRenderingPolicy", "getOverrideRenderingPolicy()Lorg/jetbrains/kotlin/renderer/OverrideRenderingPolicy;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "valueParametersHandler", "getValueParametersHandler()Lorg/jetbrains/kotlin/renderer/DescriptorRenderer$ValueParametersHandler;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "textFormat", "getTextFormat()Lorg/jetbrains/kotlin/renderer/RenderingFormat;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "parameterNameRenderingPolicy", "getParameterNameRenderingPolicy()Lorg/jetbrains/kotlin/renderer/ParameterNameRenderingPolicy;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "receiverAfterName", "getReceiverAfterName()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "renderCompanionObjectName", "getRenderCompanionObjectName()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "propertyAccessorRenderingPolicy", "getPropertyAccessorRenderingPolicy()Lorg/jetbrains/kotlin/renderer/PropertyAccessorRenderingPolicy;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "renderDefaultAnnotationArguments", "getRenderDefaultAnnotationArguments()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "eachAnnotationOnNewLine", "getEachAnnotationOnNewLine()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "excludedAnnotationClasses", "getExcludedAnnotationClasses()Ljava/util/Set;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "excludedTypeAnnotationClasses", "getExcludedTypeAnnotationClasses()Ljava/util/Set;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "annotationFilter", "getAnnotationFilter()Lkotlin/jvm/functions/Function1;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "annotationArgumentsRenderingPolicy", "getAnnotationArgumentsRenderingPolicy()Lorg/jetbrains/kotlin/renderer/AnnotationArgumentsRenderingPolicy;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "alwaysRenderModifiers", "getAlwaysRenderModifiers()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "renderConstructorKeyword", "getRenderConstructorKeyword()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "renderUnabbreviatedType", "getRenderUnabbreviatedType()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "renderTypeExpansions", "getRenderTypeExpansions()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "includeAdditionalModifiers", "getIncludeAdditionalModifiers()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "parameterNamesInFunctionalTypes", "getParameterNamesInFunctionalTypes()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "renderFunctionContracts", "getRenderFunctionContracts()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "presentableUnresolvedTypes", "getPresentableUnresolvedTypes()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "boldOnlyForNamesInHtml", "getBoldOnlyForNamesInHtml()Z")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(cls), "informativeErrorType", "getInformativeErrorType()Z"))};
    }

    public ClassifierNamePolicy getClassifierNamePolicy() {
        return (ClassifierNamePolicy) this.classifierNamePolicy$delegate.getValue(this, $$delegatedProperties[0]);
    }

    public void setClassifierNamePolicy(ClassifierNamePolicy classifierNamePolicy) {
        Intrinsics.checkNotNullParameter(classifierNamePolicy, "<set-?>");
        this.classifierNamePolicy$delegate.setValue(this, $$delegatedProperties[0], classifierNamePolicy);
    }

    public boolean getWithDefinedIn() {
        return ((Boolean) this.withDefinedIn$delegate.getValue(this, $$delegatedProperties[1])).booleanValue();
    }

    public void setWithDefinedIn(boolean z) {
        this.withDefinedIn$delegate.setValue(this, $$delegatedProperties[1], Boolean.valueOf(z));
    }

    public boolean getWithSourceFileForTopLevel() {
        return ((Boolean) this.withSourceFileForTopLevel$delegate.getValue(this, $$delegatedProperties[2])).booleanValue();
    }

    public Set<DescriptorRendererModifier> getModifiers() {
        return (Set) this.modifiers$delegate.getValue(this, $$delegatedProperties[3]);
    }

    public void setModifiers(Set<? extends DescriptorRendererModifier> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.modifiers$delegate.setValue(this, $$delegatedProperties[3], set);
    }

    public boolean getStartFromName() {
        return ((Boolean) this.startFromName$delegate.getValue(this, $$delegatedProperties[4])).booleanValue();
    }

    public void setStartFromName(boolean z) {
        this.startFromName$delegate.setValue(this, $$delegatedProperties[4], Boolean.valueOf(z));
    }

    public boolean getStartFromDeclarationKeyword() {
        return ((Boolean) this.startFromDeclarationKeyword$delegate.getValue(this, $$delegatedProperties[5])).booleanValue();
    }

    public boolean getDebugMode() {
        return ((Boolean) this.debugMode$delegate.getValue(this, $$delegatedProperties[6])).booleanValue();
    }

    public void setDebugMode(boolean z) {
        this.debugMode$delegate.setValue(this, $$delegatedProperties[6], Boolean.valueOf(z));
    }

    public boolean getClassWithPrimaryConstructor() {
        return ((Boolean) this.classWithPrimaryConstructor$delegate.getValue(this, $$delegatedProperties[7])).booleanValue();
    }

    public boolean getVerbose() {
        return ((Boolean) this.verbose$delegate.getValue(this, $$delegatedProperties[8])).booleanValue();
    }

    public void setVerbose(boolean z) {
        this.verbose$delegate.setValue(this, $$delegatedProperties[8], Boolean.valueOf(z));
    }

    public boolean getUnitReturnType() {
        return ((Boolean) this.unitReturnType$delegate.getValue(this, $$delegatedProperties[9])).booleanValue();
    }

    public boolean getWithoutReturnType() {
        return ((Boolean) this.withoutReturnType$delegate.getValue(this, $$delegatedProperties[10])).booleanValue();
    }

    public boolean getEnhancedTypes() {
        return ((Boolean) this.enhancedTypes$delegate.getValue(this, $$delegatedProperties[11])).booleanValue();
    }

    public boolean getNormalizedVisibilities() {
        return ((Boolean) this.normalizedVisibilities$delegate.getValue(this, $$delegatedProperties[12])).booleanValue();
    }

    public boolean getRenderDefaultVisibility() {
        return ((Boolean) this.renderDefaultVisibility$delegate.getValue(this, $$delegatedProperties[13])).booleanValue();
    }

    public boolean getRenderDefaultModality() {
        return ((Boolean) this.renderDefaultModality$delegate.getValue(this, $$delegatedProperties[14])).booleanValue();
    }

    public boolean getRenderConstructorDelegation() {
        return ((Boolean) this.renderConstructorDelegation$delegate.getValue(this, $$delegatedProperties[15])).booleanValue();
    }

    public boolean getRenderPrimaryConstructorParametersAsProperties() {
        return ((Boolean) this.renderPrimaryConstructorParametersAsProperties$delegate.getValue(this, $$delegatedProperties[16])).booleanValue();
    }

    public boolean getActualPropertiesInPrimaryConstructor() {
        return ((Boolean) this.actualPropertiesInPrimaryConstructor$delegate.getValue(this, $$delegatedProperties[17])).booleanValue();
    }

    public boolean getUninferredTypeParameterAsName() {
        return ((Boolean) this.uninferredTypeParameterAsName$delegate.getValue(this, $$delegatedProperties[18])).booleanValue();
    }

    public boolean getIncludePropertyConstant() {
        return ((Boolean) this.includePropertyConstant$delegate.getValue(this, $$delegatedProperties[19])).booleanValue();
    }

    public Function1<ConstantValue<?>, String> getPropertyConstantRenderer() {
        return (Function1) this.propertyConstantRenderer$delegate.getValue(this, $$delegatedProperties[20]);
    }

    public boolean getWithoutTypeParameters() {
        return ((Boolean) this.withoutTypeParameters$delegate.getValue(this, $$delegatedProperties[21])).booleanValue();
    }

    public void setWithoutTypeParameters(boolean z) {
        this.withoutTypeParameters$delegate.setValue(this, $$delegatedProperties[21], Boolean.valueOf(z));
    }

    public boolean getWithoutSuperTypes() {
        return ((Boolean) this.withoutSuperTypes$delegate.getValue(this, $$delegatedProperties[22])).booleanValue();
    }

    public void setWithoutSuperTypes(boolean z) {
        this.withoutSuperTypes$delegate.setValue(this, $$delegatedProperties[22], Boolean.valueOf(z));
    }

    public Function1<KotlinType, KotlinType> getTypeNormalizer() {
        return (Function1) this.typeNormalizer$delegate.getValue(this, $$delegatedProperties[23]);
    }

    public Function1<ValueParameterDescriptor, String> getDefaultParameterValueRenderer() {
        return (Function1) this.defaultParameterValueRenderer$delegate.getValue(this, $$delegatedProperties[24]);
    }

    public boolean getSecondaryConstructorsAsPrimary() {
        return ((Boolean) this.secondaryConstructorsAsPrimary$delegate.getValue(this, $$delegatedProperties[25])).booleanValue();
    }

    public OverrideRenderingPolicy getOverrideRenderingPolicy() {
        return (OverrideRenderingPolicy) this.overrideRenderingPolicy$delegate.getValue(this, $$delegatedProperties[26]);
    }

    public DescriptorRenderer.ValueParametersHandler getValueParametersHandler() {
        return (DescriptorRenderer.ValueParametersHandler) this.valueParametersHandler$delegate.getValue(this, $$delegatedProperties[27]);
    }

    public RenderingFormat getTextFormat() {
        return (RenderingFormat) this.textFormat$delegate.getValue(this, $$delegatedProperties[28]);
    }

    public void setTextFormat(RenderingFormat renderingFormat) {
        Intrinsics.checkNotNullParameter(renderingFormat, "<set-?>");
        this.textFormat$delegate.setValue(this, $$delegatedProperties[28], renderingFormat);
    }

    public ParameterNameRenderingPolicy getParameterNameRenderingPolicy() {
        return (ParameterNameRenderingPolicy) this.parameterNameRenderingPolicy$delegate.getValue(this, $$delegatedProperties[29]);
    }

    public void setParameterNameRenderingPolicy(ParameterNameRenderingPolicy parameterNameRenderingPolicy) {
        Intrinsics.checkNotNullParameter(parameterNameRenderingPolicy, "<set-?>");
        this.parameterNameRenderingPolicy$delegate.setValue(this, $$delegatedProperties[29], parameterNameRenderingPolicy);
    }

    public boolean getReceiverAfterName() {
        return ((Boolean) this.receiverAfterName$delegate.getValue(this, $$delegatedProperties[30])).booleanValue();
    }

    public void setReceiverAfterName(boolean z) {
        this.receiverAfterName$delegate.setValue(this, $$delegatedProperties[30], Boolean.valueOf(z));
    }

    public boolean getRenderCompanionObjectName() {
        return ((Boolean) this.renderCompanionObjectName$delegate.getValue(this, $$delegatedProperties[31])).booleanValue();
    }

    public void setRenderCompanionObjectName(boolean z) {
        this.renderCompanionObjectName$delegate.setValue(this, $$delegatedProperties[31], Boolean.valueOf(z));
    }

    public PropertyAccessorRenderingPolicy getPropertyAccessorRenderingPolicy() {
        return (PropertyAccessorRenderingPolicy) this.propertyAccessorRenderingPolicy$delegate.getValue(this, $$delegatedProperties[32]);
    }

    public boolean getRenderDefaultAnnotationArguments() {
        return ((Boolean) this.renderDefaultAnnotationArguments$delegate.getValue(this, $$delegatedProperties[33])).booleanValue();
    }

    public boolean getEachAnnotationOnNewLine() {
        return ((Boolean) this.eachAnnotationOnNewLine$delegate.getValue(this, $$delegatedProperties[34])).booleanValue();
    }

    public Set<FqName> getExcludedAnnotationClasses() {
        return (Set) this.excludedAnnotationClasses$delegate.getValue(this, $$delegatedProperties[35]);
    }

    public Set<FqName> getExcludedTypeAnnotationClasses() {
        return (Set) this.excludedTypeAnnotationClasses$delegate.getValue(this, $$delegatedProperties[36]);
    }

    public void setExcludedTypeAnnotationClasses(Set<FqName> set) {
        Intrinsics.checkNotNullParameter(set, "<set-?>");
        this.excludedTypeAnnotationClasses$delegate.setValue(this, $$delegatedProperties[36], set);
    }

    public Function1<AnnotationDescriptor, Boolean> getAnnotationFilter() {
        return (Function1) this.annotationFilter$delegate.getValue(this, $$delegatedProperties[37]);
    }

    public AnnotationArgumentsRenderingPolicy getAnnotationArgumentsRenderingPolicy() {
        return (AnnotationArgumentsRenderingPolicy) this.annotationArgumentsRenderingPolicy$delegate.getValue(this, $$delegatedProperties[38]);
    }

    public void setAnnotationArgumentsRenderingPolicy(AnnotationArgumentsRenderingPolicy annotationArgumentsRenderingPolicy) {
        Intrinsics.checkNotNullParameter(annotationArgumentsRenderingPolicy, "<set-?>");
        this.annotationArgumentsRenderingPolicy$delegate.setValue(this, $$delegatedProperties[38], annotationArgumentsRenderingPolicy);
    }

    public boolean getAlwaysRenderModifiers() {
        return ((Boolean) this.alwaysRenderModifiers$delegate.getValue(this, $$delegatedProperties[39])).booleanValue();
    }

    public boolean getRenderConstructorKeyword() {
        return ((Boolean) this.renderConstructorKeyword$delegate.getValue(this, $$delegatedProperties[40])).booleanValue();
    }

    public boolean getRenderUnabbreviatedType() {
        return ((Boolean) this.renderUnabbreviatedType$delegate.getValue(this, $$delegatedProperties[41])).booleanValue();
    }

    public boolean getRenderTypeExpansions() {
        return ((Boolean) this.renderTypeExpansions$delegate.getValue(this, $$delegatedProperties[42])).booleanValue();
    }

    public boolean getIncludeAdditionalModifiers() {
        return ((Boolean) this.includeAdditionalModifiers$delegate.getValue(this, $$delegatedProperties[43])).booleanValue();
    }

    public boolean getParameterNamesInFunctionalTypes() {
        return ((Boolean) this.parameterNamesInFunctionalTypes$delegate.getValue(this, $$delegatedProperties[44])).booleanValue();
    }

    public boolean getPresentableUnresolvedTypes() {
        return ((Boolean) this.presentableUnresolvedTypes$delegate.getValue(this, $$delegatedProperties[46])).booleanValue();
    }

    public boolean getBoldOnlyForNamesInHtml() {
        return ((Boolean) this.boldOnlyForNamesInHtml$delegate.getValue(this, $$delegatedProperties[47])).booleanValue();
    }

    public boolean getInformativeErrorType() {
        return ((Boolean) this.informativeErrorType$delegate.getValue(this, $$delegatedProperties[48])).booleanValue();
    }
}
