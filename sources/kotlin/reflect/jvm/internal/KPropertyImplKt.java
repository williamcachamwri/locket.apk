package kotlin.reflect.jvm.internal;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.KPropertyImpl;
import kotlin.reflect.jvm.internal.calls.CallerImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a\f\u0010\t\u001a\u00020\b*\u00020\nH\u0002\"\"\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00028@X\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u000b"}, d2 = {"boundReceiver", "", "Lkotlin/reflect/jvm/internal/KPropertyImpl$Accessor;", "getBoundReceiver", "(Lkotlin/reflect/jvm/internal/KPropertyImpl$Accessor;)Ljava/lang/Object;", "computeCallerForAccessor", "Lkotlin/reflect/jvm/internal/calls/Caller;", "isGetter", "", "isJvmFieldPropertyInCompanionObject", "Lkotlin/reflect/jvm/internal/impl/descriptors/PropertyDescriptor;", "kotlin-reflection"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: KPropertyImpl.kt */
public final class KPropertyImplKt {
    public static final Object getBoundReceiver(KPropertyImpl.Accessor<?, ?> accessor) {
        Intrinsics.checkNotNullParameter(accessor, "<this>");
        return accessor.getProperty().getBoundReceiver();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0125  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlin.reflect.jvm.internal.calls.Caller<?> computeCallerForAccessor(kotlin.reflect.jvm.internal.KPropertyImpl.Accessor<?, ?> r6, boolean r7) {
        /*
            kotlin.reflect.jvm.internal.KDeclarationContainerImpl$Companion r0 = kotlin.reflect.jvm.internal.KDeclarationContainerImpl.Companion
            kotlin.text.Regex r0 = r0.getLOCAL_PROPERTY_SIGNATURE$kotlin_reflection()
            kotlin.reflect.jvm.internal.KPropertyImpl r1 = r6.getProperty()
            java.lang.String r1 = r1.getSignature()
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            boolean r0 = r0.matches(r1)
            if (r0 == 0) goto L_0x001b
            kotlin.reflect.jvm.internal.calls.ThrowingCaller r6 = kotlin.reflect.jvm.internal.calls.ThrowingCaller.INSTANCE
            kotlin.reflect.jvm.internal.calls.Caller r6 = (kotlin.reflect.jvm.internal.calls.Caller) r6
            return r6
        L_0x001b:
            kotlin.reflect.jvm.internal.RuntimeTypeMapper r0 = kotlin.reflect.jvm.internal.RuntimeTypeMapper.INSTANCE
            kotlin.reflect.jvm.internal.KPropertyImpl r1 = r6.getProperty()
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r1 = r1.getDescriptor()
            kotlin.reflect.jvm.internal.JvmPropertySignature r0 = r0.mapPropertySignature(r1)
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.JvmPropertySignature.KotlinProperty
            r2 = 0
            if (r1 == 0) goto L_0x0189
            kotlin.reflect.jvm.internal.JvmPropertySignature$KotlinProperty r0 = (kotlin.reflect.jvm.internal.JvmPropertySignature.KotlinProperty) r0
            kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$JvmPropertySignature r1 = r0.getSignature()
            if (r7 == 0) goto L_0x0041
            boolean r3 = r1.hasGetter()
            if (r3 == 0) goto L_0x004c
            kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$JvmMethodSignature r1 = r1.getGetter()
            goto L_0x004d
        L_0x0041:
            boolean r3 = r1.hasSetter()
            if (r3 == 0) goto L_0x004c
            kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf$JvmMethodSignature r1 = r1.getSetter()
            goto L_0x004d
        L_0x004c:
            r1 = r2
        L_0x004d:
            if (r1 == 0) goto L_0x0074
            kotlin.reflect.jvm.internal.KPropertyImpl r3 = r6.getProperty()
            kotlin.reflect.jvm.internal.KDeclarationContainerImpl r3 = r3.getContainer()
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r4 = r0.getNameResolver()
            int r5 = r1.getName()
            java.lang.String r4 = r4.getString(r5)
            kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver r0 = r0.getNameResolver()
            int r1 = r1.getDesc()
            java.lang.String r0 = r0.getString(r1)
            java.lang.reflect.Method r0 = r3.findMethodBySignature(r4, r0)
            goto L_0x0075
        L_0x0074:
            r0 = r2
        L_0x0075:
            if (r0 != 0) goto L_0x0125
            kotlin.reflect.jvm.internal.KPropertyImpl r0 = r6.getProperty()
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r0 = r0.getDescriptor()
            kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor) r0
            boolean r0 = kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt.isUnderlyingPropertyOfInlineClass(r0)
            if (r0 == 0) goto L_0x00f9
            kotlin.reflect.jvm.internal.KPropertyImpl r0 = r6.getProperty()
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r0 = r0.getDescriptor()
            kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility r0 = r0.getVisibility()
            kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility r1 = kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities.INTERNAL
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)
            if (r0 == 0) goto L_0x00f9
            kotlin.reflect.jvm.internal.KPropertyImpl r7 = r6.getProperty()
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r7 = r7.getDescriptor()
            kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor r7 = r7.getContainingDeclaration()
            java.lang.Class r7 = kotlin.reflect.jvm.internal.calls.ValueClassAwareCallerKt.toInlineClass((kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor) r7)
            if (r7 == 0) goto L_0x00da
            kotlin.reflect.jvm.internal.KPropertyImpl r0 = r6.getProperty()
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor r0 = r0.getDescriptor()
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r0 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor) r0
            java.lang.reflect.Method r7 = kotlin.reflect.jvm.internal.calls.ValueClassAwareCallerKt.getInlineClassUnboxMethod(r7, r0)
            if (r7 == 0) goto L_0x00da
            boolean r0 = r6.isBound()
            if (r0 == 0) goto L_0x00cf
            kotlin.reflect.jvm.internal.calls.InternalUnderlyingValOfInlineClass$Bound r0 = new kotlin.reflect.jvm.internal.calls.InternalUnderlyingValOfInlineClass$Bound
            java.lang.Object r1 = getBoundReceiver(r6)
            r0.<init>(r7, r1)
            kotlin.reflect.jvm.internal.calls.InternalUnderlyingValOfInlineClass r0 = (kotlin.reflect.jvm.internal.calls.InternalUnderlyingValOfInlineClass) r0
            goto L_0x00d6
        L_0x00cf:
            kotlin.reflect.jvm.internal.calls.InternalUnderlyingValOfInlineClass$Unbound r0 = new kotlin.reflect.jvm.internal.calls.InternalUnderlyingValOfInlineClass$Unbound
            r0.<init>(r7)
            kotlin.reflect.jvm.internal.calls.InternalUnderlyingValOfInlineClass r0 = (kotlin.reflect.jvm.internal.calls.InternalUnderlyingValOfInlineClass) r0
        L_0x00d6:
            kotlin.reflect.jvm.internal.calls.Caller r0 = (kotlin.reflect.jvm.internal.calls.Caller) r0
            goto L_0x01cb
        L_0x00da:
            kotlin.reflect.jvm.internal.KotlinReflectionInternalError r7 = new kotlin.reflect.jvm.internal.KotlinReflectionInternalError
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Underlying property of inline class "
            r0.<init>(r1)
            kotlin.reflect.jvm.internal.KPropertyImpl r6 = r6.getProperty()
            java.lang.StringBuilder r6 = r0.append(r6)
            java.lang.String r0 = " should have a field"
            java.lang.StringBuilder r6 = r6.append(r0)
            java.lang.String r6 = r6.toString()
            r7.<init>(r6)
            throw r7
        L_0x00f9:
            kotlin.reflect.jvm.internal.KPropertyImpl r0 = r6.getProperty()
            java.lang.reflect.Field r0 = r0.getJavaField()
            if (r0 == 0) goto L_0x010c
            kotlin.reflect.jvm.internal.calls.CallerImpl r7 = computeCallerForAccessor$computeFieldCaller(r6, r7, r0)
            r0 = r7
            kotlin.reflect.jvm.internal.calls.Caller r0 = (kotlin.reflect.jvm.internal.calls.Caller) r0
            goto L_0x01cb
        L_0x010c:
            kotlin.reflect.jvm.internal.KotlinReflectionInternalError r7 = new kotlin.reflect.jvm.internal.KotlinReflectionInternalError
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "No accessors or field is found for property "
            r0.<init>(r1)
            kotlin.reflect.jvm.internal.KPropertyImpl r6 = r6.getProperty()
            java.lang.StringBuilder r6 = r0.append(r6)
            java.lang.String r6 = r6.toString()
            r7.<init>(r6)
            throw r7
        L_0x0125:
            int r7 = r0.getModifiers()
            boolean r7 = java.lang.reflect.Modifier.isStatic(r7)
            if (r7 != 0) goto L_0x014d
            boolean r7 = r6.isBound()
            if (r7 == 0) goto L_0x0141
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method$BoundInstance r7 = new kotlin.reflect.jvm.internal.calls.CallerImpl$Method$BoundInstance
            java.lang.Object r1 = getBoundReceiver(r6)
            r7.<init>(r0, r1)
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method r7 = (kotlin.reflect.jvm.internal.calls.CallerImpl.Method) r7
            goto L_0x0148
        L_0x0141:
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method$Instance r7 = new kotlin.reflect.jvm.internal.calls.CallerImpl$Method$Instance
            r7.<init>(r0)
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method r7 = (kotlin.reflect.jvm.internal.calls.CallerImpl.Method) r7
        L_0x0148:
            r0 = r7
            kotlin.reflect.jvm.internal.calls.Caller r0 = (kotlin.reflect.jvm.internal.calls.Caller) r0
            goto L_0x01cb
        L_0x014d:
            boolean r7 = computeCallerForAccessor$isJvmStaticProperty(r6)
            if (r7 == 0) goto L_0x016c
            boolean r7 = r6.isBound()
            if (r7 == 0) goto L_0x0161
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method$BoundJvmStaticInObject r7 = new kotlin.reflect.jvm.internal.calls.CallerImpl$Method$BoundJvmStaticInObject
            r7.<init>(r0)
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method r7 = (kotlin.reflect.jvm.internal.calls.CallerImpl.Method) r7
            goto L_0x0168
        L_0x0161:
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method$JvmStaticInObject r7 = new kotlin.reflect.jvm.internal.calls.CallerImpl$Method$JvmStaticInObject
            r7.<init>(r0)
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method r7 = (kotlin.reflect.jvm.internal.calls.CallerImpl.Method) r7
        L_0x0168:
            r0 = r7
            kotlin.reflect.jvm.internal.calls.Caller r0 = (kotlin.reflect.jvm.internal.calls.Caller) r0
            goto L_0x01cb
        L_0x016c:
            boolean r7 = r6.isBound()
            if (r7 == 0) goto L_0x017e
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method$BoundStatic r7 = new kotlin.reflect.jvm.internal.calls.CallerImpl$Method$BoundStatic
            java.lang.Object r1 = getBoundReceiver(r6)
            r7.<init>(r0, r1)
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method r7 = (kotlin.reflect.jvm.internal.calls.CallerImpl.Method) r7
            goto L_0x0185
        L_0x017e:
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method$Static r7 = new kotlin.reflect.jvm.internal.calls.CallerImpl$Method$Static
            r7.<init>(r0)
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method r7 = (kotlin.reflect.jvm.internal.calls.CallerImpl.Method) r7
        L_0x0185:
            r0 = r7
            kotlin.reflect.jvm.internal.calls.Caller r0 = (kotlin.reflect.jvm.internal.calls.Caller) r0
            goto L_0x01cb
        L_0x0189:
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.JvmPropertySignature.JavaField
            if (r1 == 0) goto L_0x019b
            kotlin.reflect.jvm.internal.JvmPropertySignature$JavaField r0 = (kotlin.reflect.jvm.internal.JvmPropertySignature.JavaField) r0
            java.lang.reflect.Field r0 = r0.getField()
            kotlin.reflect.jvm.internal.calls.CallerImpl r7 = computeCallerForAccessor$computeFieldCaller(r6, r7, r0)
            r0 = r7
            kotlin.reflect.jvm.internal.calls.Caller r0 = (kotlin.reflect.jvm.internal.calls.Caller) r0
            goto L_0x01cb
        L_0x019b:
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.JvmPropertySignature.JavaMethodProperty
            if (r1 == 0) goto L_0x01f1
            if (r7 == 0) goto L_0x01a8
            kotlin.reflect.jvm.internal.JvmPropertySignature$JavaMethodProperty r0 = (kotlin.reflect.jvm.internal.JvmPropertySignature.JavaMethodProperty) r0
            java.lang.reflect.Method r7 = r0.getGetterMethod()
            goto L_0x01b0
        L_0x01a8:
            kotlin.reflect.jvm.internal.JvmPropertySignature$JavaMethodProperty r0 = (kotlin.reflect.jvm.internal.JvmPropertySignature.JavaMethodProperty) r0
            java.lang.reflect.Method r7 = r0.getSetterMethod()
            if (r7 == 0) goto L_0x01d8
        L_0x01b0:
            boolean r0 = r6.isBound()
            if (r0 == 0) goto L_0x01c2
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method$BoundInstance r0 = new kotlin.reflect.jvm.internal.calls.CallerImpl$Method$BoundInstance
            java.lang.Object r1 = getBoundReceiver(r6)
            r0.<init>(r7, r1)
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method r0 = (kotlin.reflect.jvm.internal.calls.CallerImpl.Method) r0
            goto L_0x01c9
        L_0x01c2:
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method$Instance r0 = new kotlin.reflect.jvm.internal.calls.CallerImpl$Method$Instance
            r0.<init>(r7)
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method r0 = (kotlin.reflect.jvm.internal.calls.CallerImpl.Method) r0
        L_0x01c9:
            kotlin.reflect.jvm.internal.calls.Caller r0 = (kotlin.reflect.jvm.internal.calls.Caller) r0
        L_0x01cb:
            kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor r6 = r6.getDescriptor()
            kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor r6 = (kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor) r6
            r7 = 0
            r1 = 2
            kotlin.reflect.jvm.internal.calls.Caller r6 = kotlin.reflect.jvm.internal.calls.ValueClassAwareCallerKt.createValueClassAwareCallerIfNeeded$default(r0, r6, r7, r1, r2)
            return r6
        L_0x01d8:
            kotlin.reflect.jvm.internal.KotlinReflectionInternalError r6 = new kotlin.reflect.jvm.internal.KotlinReflectionInternalError
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r1 = "No source found for setter of Java method property: "
            r7.<init>(r1)
            java.lang.reflect.Method r0 = r0.getGetterMethod()
            java.lang.StringBuilder r7 = r7.append(r0)
            java.lang.String r7 = r7.toString()
            r6.<init>(r7)
            throw r6
        L_0x01f1:
            boolean r1 = r0 instanceof kotlin.reflect.jvm.internal.JvmPropertySignature.MappedKotlinProperty
            if (r1 == 0) goto L_0x0270
            if (r7 == 0) goto L_0x01fe
            kotlin.reflect.jvm.internal.JvmPropertySignature$MappedKotlinProperty r0 = (kotlin.reflect.jvm.internal.JvmPropertySignature.MappedKotlinProperty) r0
            kotlin.reflect.jvm.internal.JvmFunctionSignature$KotlinFunction r7 = r0.getGetterSignature()
            goto L_0x0206
        L_0x01fe:
            kotlin.reflect.jvm.internal.JvmPropertySignature$MappedKotlinProperty r0 = (kotlin.reflect.jvm.internal.JvmPropertySignature.MappedKotlinProperty) r0
            kotlin.reflect.jvm.internal.JvmFunctionSignature$KotlinFunction r7 = r0.getSetterSignature()
            if (r7 == 0) goto L_0x0257
        L_0x0206:
            kotlin.reflect.jvm.internal.KPropertyImpl r0 = r6.getProperty()
            kotlin.reflect.jvm.internal.KDeclarationContainerImpl r0 = r0.getContainer()
            java.lang.String r1 = r7.getMethodName()
            java.lang.String r7 = r7.getMethodDesc()
            java.lang.reflect.Method r7 = r0.findMethodBySignature(r1, r7)
            if (r7 == 0) goto L_0x023e
            int r0 = r7.getModifiers()
            java.lang.reflect.Modifier.isStatic(r0)
            boolean r0 = r6.isBound()
            if (r0 == 0) goto L_0x0235
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method$BoundInstance r0 = new kotlin.reflect.jvm.internal.calls.CallerImpl$Method$BoundInstance
            java.lang.Object r6 = getBoundReceiver(r6)
            r0.<init>(r7, r6)
            kotlin.reflect.jvm.internal.calls.Caller r0 = (kotlin.reflect.jvm.internal.calls.Caller) r0
            goto L_0x023d
        L_0x0235:
            kotlin.reflect.jvm.internal.calls.CallerImpl$Method$Instance r6 = new kotlin.reflect.jvm.internal.calls.CallerImpl$Method$Instance
            r6.<init>(r7)
            r0 = r6
            kotlin.reflect.jvm.internal.calls.Caller r0 = (kotlin.reflect.jvm.internal.calls.Caller) r0
        L_0x023d:
            return r0
        L_0x023e:
            kotlin.reflect.jvm.internal.KotlinReflectionInternalError r7 = new kotlin.reflect.jvm.internal.KotlinReflectionInternalError
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "No accessor found for property "
            r0.<init>(r1)
            kotlin.reflect.jvm.internal.KPropertyImpl r6 = r6.getProperty()
            java.lang.StringBuilder r6 = r0.append(r6)
            java.lang.String r6 = r6.toString()
            r7.<init>(r6)
            throw r7
        L_0x0257:
            kotlin.reflect.jvm.internal.KotlinReflectionInternalError r7 = new kotlin.reflect.jvm.internal.KotlinReflectionInternalError
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "No setter found for property "
            r0.<init>(r1)
            kotlin.reflect.jvm.internal.KPropertyImpl r6 = r6.getProperty()
            java.lang.StringBuilder r6 = r0.append(r6)
            java.lang.String r6 = r6.toString()
            r7.<init>(r6)
            throw r7
        L_0x0270:
            kotlin.NoWhenBranchMatchedException r6 = new kotlin.NoWhenBranchMatchedException
            r6.<init>()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.KPropertyImplKt.computeCallerForAccessor(kotlin.reflect.jvm.internal.KPropertyImpl$Accessor, boolean):kotlin.reflect.jvm.internal.calls.Caller");
    }

    private static final boolean computeCallerForAccessor$isJvmStaticProperty(KPropertyImpl.Accessor<?, ?> accessor) {
        return accessor.getProperty().getDescriptor().getAnnotations().hasAnnotation(UtilKt.getJVM_STATIC());
    }

    private static final boolean computeCallerForAccessor$isNotNullProperty(KPropertyImpl.Accessor<?, ?> accessor) {
        return !TypeUtils.isNullableType(accessor.getProperty().getDescriptor().getType());
    }

    private static final CallerImpl<Field> computeCallerForAccessor$computeFieldCaller(KPropertyImpl.Accessor<?, ?> accessor, boolean z, Field field) {
        CallerImpl.FieldGetter fieldGetter;
        CallerImpl.FieldGetter fieldGetter2;
        if (isJvmFieldPropertyInCompanionObject(accessor.getProperty().getDescriptor()) || !Modifier.isStatic(field.getModifiers())) {
            if (z) {
                if (accessor.isBound()) {
                    fieldGetter = new CallerImpl.FieldGetter.BoundInstance(field, getBoundReceiver(accessor));
                } else {
                    fieldGetter = new CallerImpl.FieldGetter.Instance(field);
                }
                return fieldGetter;
            } else if (accessor.isBound()) {
                return new CallerImpl.FieldSetter.BoundInstance(field, computeCallerForAccessor$isNotNullProperty(accessor), getBoundReceiver(accessor));
            } else {
                return new CallerImpl.FieldSetter.Instance(field, computeCallerForAccessor$isNotNullProperty(accessor));
            }
        } else if (computeCallerForAccessor$isJvmStaticProperty(accessor)) {
            if (z) {
                if (accessor.isBound()) {
                    fieldGetter2 = new CallerImpl.FieldGetter.BoundJvmStaticInObject(field);
                } else {
                    fieldGetter2 = new CallerImpl.FieldGetter.JvmStaticInObject(field);
                }
                return fieldGetter2;
            } else if (accessor.isBound()) {
                return new CallerImpl.FieldSetter.BoundJvmStaticInObject(field, computeCallerForAccessor$isNotNullProperty(accessor));
            } else {
                return new CallerImpl.FieldSetter.JvmStaticInObject(field, computeCallerForAccessor$isNotNullProperty(accessor));
            }
        } else if (z) {
            return new CallerImpl.FieldGetter.Static(field);
        } else {
            return new CallerImpl.FieldSetter.Static(field, computeCallerForAccessor$isNotNullProperty(accessor));
        }
    }

    private static final boolean isJvmFieldPropertyInCompanionObject(PropertyDescriptor propertyDescriptor) {
        DeclarationDescriptor containingDeclaration = propertyDescriptor.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(containingDeclaration, "getContainingDeclaration(...)");
        if (!DescriptorUtils.isCompanionObject(containingDeclaration)) {
            return false;
        }
        DeclarationDescriptor containingDeclaration2 = containingDeclaration.getContainingDeclaration();
        if ((DescriptorUtils.isInterface(containingDeclaration2) || DescriptorUtils.isAnnotationClass(containingDeclaration2)) && (!(propertyDescriptor instanceof DeserializedPropertyDescriptor) || !JvmProtoBufUtil.isMovedFromInterfaceCompanion(((DeserializedPropertyDescriptor) propertyDescriptor).getProto()))) {
            return false;
        }
        return true;
    }
}
