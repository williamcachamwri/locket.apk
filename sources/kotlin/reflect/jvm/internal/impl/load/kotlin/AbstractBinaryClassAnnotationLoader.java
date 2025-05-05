package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.SpecialJvmAnnotations;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader.AnnotationsContainer;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MemberSignature;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoBufUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.ProtoTypeTableUtilKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.TypeTable;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.ClassMapperLite;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMemberSignature;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmProtoBufUtil;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.protobuf.GeneratedMessageLite;
import kotlin.reflect.jvm.internal.impl.protobuf.MessageLite;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotationLoader;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ProtoContainer;
import kotlin.text.StringsKt;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

/* compiled from: AbstractBinaryClassAnnotationLoader.kt */
public abstract class AbstractBinaryClassAnnotationLoader<A, S extends AnnotationsContainer<? extends A>> implements AnnotationLoader<A> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final KotlinClassFinder kotlinClassFinder;

    /* compiled from: AbstractBinaryClassAnnotationLoader.kt */
    public static abstract class AnnotationsContainer<A> {
        public abstract Map<MemberSignature, List<A>> getMemberAnnotations();
    }

    /* compiled from: AbstractBinaryClassAnnotationLoader.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind[] r0 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r1 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY_GETTER     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r1 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY_SETTER     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind r1 = kotlin.reflect.jvm.internal.impl.serialization.deserialization.AnnotatedCallableKind.PROPERTY     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.load.kotlin.AbstractBinaryClassAnnotationLoader.WhenMappings.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public abstract S getAnnotationsContainer(KotlinJvmBinaryClass kotlinJvmBinaryClass);

    /* access modifiers changed from: protected */
    public byte[] getCachedFileContent(KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        Intrinsics.checkNotNullParameter(kotlinJvmBinaryClass, "kotlinClass");
        return null;
    }

    public abstract JvmMetadataVersion getJvmMetadataVersion();

    public abstract A loadAnnotation(ProtoBuf.Annotation annotation, NameResolver nameResolver);

    /* access modifiers changed from: protected */
    public abstract KotlinJvmBinaryClass.AnnotationArgumentVisitor loadAnnotation(ClassId classId, SourceElement sourceElement, List<A> list);

    public AbstractBinaryClassAnnotationLoader(KotlinClassFinder kotlinClassFinder2) {
        Intrinsics.checkNotNullParameter(kotlinClassFinder2, "kotlinClassFinder");
        this.kotlinClassFinder = kotlinClassFinder2;
    }

    /* access modifiers changed from: protected */
    public final KotlinClassFinder getKotlinClassFinder() {
        return this.kotlinClassFinder;
    }

    /* access modifiers changed from: protected */
    public final KotlinJvmBinaryClass.AnnotationArgumentVisitor loadAnnotationIfNotSpecial(ClassId classId, SourceElement sourceElement, List<A> list) {
        Intrinsics.checkNotNullParameter(classId, "annotationClassId");
        Intrinsics.checkNotNullParameter(sourceElement, "source");
        Intrinsics.checkNotNullParameter(list, "result");
        if (SpecialJvmAnnotations.INSTANCE.getSPECIAL_ANNOTATIONS().contains(classId)) {
            return null;
        }
        return loadAnnotation(classId, sourceElement, list);
    }

    private final KotlinJvmBinaryClass toBinaryClass(ProtoContainer.Class classR) {
        SourceElement source = classR.getSource();
        KotlinJvmBinarySourceElement kotlinJvmBinarySourceElement = source instanceof KotlinJvmBinarySourceElement ? (KotlinJvmBinarySourceElement) source : null;
        if (kotlinJvmBinarySourceElement != null) {
            return kotlinJvmBinarySourceElement.getBinaryClass();
        }
        return null;
    }

    public List<A> loadClassAnnotations(ProtoContainer.Class classR) {
        Intrinsics.checkNotNullParameter(classR, TtmlNode.RUBY_CONTAINER);
        KotlinJvmBinaryClass binaryClass = toBinaryClass(classR);
        if (binaryClass != null) {
            ArrayList arrayList = new ArrayList(1);
            binaryClass.loadClassAnnotations(new AbstractBinaryClassAnnotationLoader$loadClassAnnotations$1(this, arrayList), getCachedFileContent(binaryClass));
            return arrayList;
        }
        throw new IllegalStateException(("Class for loading annotations is not found: " + classR.debugFqName()).toString());
    }

    public List<A> loadCallableAnnotations(ProtoContainer protoContainer, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind) {
        Intrinsics.checkNotNullParameter(protoContainer, TtmlNode.RUBY_CONTAINER);
        Intrinsics.checkNotNullParameter(messageLite, "proto");
        Intrinsics.checkNotNullParameter(annotatedCallableKind, "kind");
        if (annotatedCallableKind == AnnotatedCallableKind.PROPERTY) {
            return loadPropertyAnnotations(protoContainer, (ProtoBuf.Property) messageLite, PropertyRelatedElement.PROPERTY);
        }
        MemberSignature callableSignature$default = getCallableSignature$default(this, messageLite, protoContainer.getNameResolver(), protoContainer.getTypeTable(), annotatedCallableKind, false, 16, (Object) null);
        if (callableSignature$default == null) {
            return CollectionsKt.emptyList();
        }
        return findClassAndLoadMemberAnnotations$default(this, protoContainer, callableSignature$default, false, false, (Boolean) null, false, 60, (Object) null);
    }

    public List<A> loadPropertyBackingFieldAnnotations(ProtoContainer protoContainer, ProtoBuf.Property property) {
        Intrinsics.checkNotNullParameter(protoContainer, TtmlNode.RUBY_CONTAINER);
        Intrinsics.checkNotNullParameter(property, "proto");
        return loadPropertyAnnotations(protoContainer, property, PropertyRelatedElement.BACKING_FIELD);
    }

    public List<A> loadPropertyDelegateFieldAnnotations(ProtoContainer protoContainer, ProtoBuf.Property property) {
        Intrinsics.checkNotNullParameter(protoContainer, TtmlNode.RUBY_CONTAINER);
        Intrinsics.checkNotNullParameter(property, "proto");
        return loadPropertyAnnotations(protoContainer, property, PropertyRelatedElement.DELEGATE_FIELD);
    }

    /* compiled from: AbstractBinaryClassAnnotationLoader.kt */
    private enum PropertyRelatedElement {
        PROPERTY,
        BACKING_FIELD,
        DELEGATE_FIELD;

        static {
            PropertyRelatedElement[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        }
    }

    private final List<A> loadPropertyAnnotations(ProtoContainer protoContainer, ProtoBuf.Property property, PropertyRelatedElement propertyRelatedElement) {
        PropertyRelatedElement propertyRelatedElement2 = propertyRelatedElement;
        Boolean bool = Flags.IS_CONST.get(property.getFlags());
        Intrinsics.checkNotNullExpressionValue(bool, "get(...)");
        boolean booleanValue = bool.booleanValue();
        boolean isMovedFromInterfaceCompanion = JvmProtoBufUtil.isMovedFromInterfaceCompanion(property);
        if (propertyRelatedElement2 == PropertyRelatedElement.PROPERTY) {
            MemberSignature propertySignature$default = AbstractBinaryClassAnnotationLoaderKt.getPropertySignature$default(property, protoContainer.getNameResolver(), protoContainer.getTypeTable(), false, true, false, 40, (Object) null);
            if (propertySignature$default == null) {
                return CollectionsKt.emptyList();
            }
            return findClassAndLoadMemberAnnotations$default(this, protoContainer, propertySignature$default, true, false, Boolean.valueOf(booleanValue), isMovedFromInterfaceCompanion, 8, (Object) null);
        }
        MemberSignature propertySignature$default2 = AbstractBinaryClassAnnotationLoaderKt.getPropertySignature$default(property, protoContainer.getNameResolver(), protoContainer.getTypeTable(), true, false, false, 48, (Object) null);
        if (propertySignature$default2 == null) {
            return CollectionsKt.emptyList();
        }
        boolean z = false;
        boolean contains$default = StringsKt.contains$default((CharSequence) propertySignature$default2.getSignature(), (CharSequence) "$delegate", false, 2, (Object) null);
        if (propertyRelatedElement2 == PropertyRelatedElement.DELEGATE_FIELD) {
            z = true;
        }
        if (contains$default != z) {
            return CollectionsKt.emptyList();
        }
        return findClassAndLoadMemberAnnotations(protoContainer, propertySignature$default2, true, true, Boolean.valueOf(booleanValue), isMovedFromInterfaceCompanion);
    }

    public List<A> loadEnumEntryAnnotations(ProtoContainer protoContainer, ProtoBuf.EnumEntry enumEntry) {
        Intrinsics.checkNotNullParameter(protoContainer, TtmlNode.RUBY_CONTAINER);
        Intrinsics.checkNotNullParameter(enumEntry, "proto");
        MemberSignature.Companion companion = MemberSignature.Companion;
        String string = protoContainer.getNameResolver().getString(enumEntry.getName());
        String asString = ((ProtoContainer.Class) protoContainer).getClassId().asString();
        Intrinsics.checkNotNullExpressionValue(asString, "asString(...)");
        return findClassAndLoadMemberAnnotations$default(this, protoContainer, companion.fromFieldNameAndDesc(string, ClassMapperLite.mapClass(asString)), false, false, (Boolean) null, false, 60, (Object) null);
    }

    static /* synthetic */ List findClassAndLoadMemberAnnotations$default(AbstractBinaryClassAnnotationLoader abstractBinaryClassAnnotationLoader, ProtoContainer protoContainer, MemberSignature memberSignature, boolean z, boolean z2, Boolean bool, boolean z3, int i, Object obj) {
        if (obj == null) {
            return abstractBinaryClassAnnotationLoader.findClassAndLoadMemberAnnotations(protoContainer, memberSignature, (i & 4) != 0 ? false : z, (i & 8) != 0 ? false : z2, (i & 16) != 0 ? null : bool, (i & 32) != 0 ? false : z3);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: findClassAndLoadMemberAnnotations");
    }

    private final List<A> findClassAndLoadMemberAnnotations(ProtoContainer protoContainer, MemberSignature memberSignature, boolean z, boolean z2, Boolean bool, boolean z3) {
        KotlinJvmBinaryClass findClassWithAnnotationsAndInitializers = findClassWithAnnotationsAndInitializers(protoContainer, Companion.getSpecialCaseContainerClass(protoContainer, z, z2, bool, z3, this.kotlinClassFinder, getJvmMetadataVersion()));
        if (findClassWithAnnotationsAndInitializers == null) {
            return CollectionsKt.emptyList();
        }
        List<A> list = (List) getAnnotationsContainer(findClassWithAnnotationsAndInitializers).getMemberAnnotations().get(memberSignature);
        return list == null ? CollectionsKt.emptyList() : list;
    }

    public List<A> loadValueParameterAnnotations(ProtoContainer protoContainer, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind, int i, ProtoBuf.ValueParameter valueParameter) {
        Intrinsics.checkNotNullParameter(protoContainer, TtmlNode.RUBY_CONTAINER);
        Intrinsics.checkNotNullParameter(messageLite, "callableProto");
        Intrinsics.checkNotNullParameter(annotatedCallableKind, "kind");
        Intrinsics.checkNotNullParameter(valueParameter, "proto");
        MemberSignature callableSignature$default = getCallableSignature$default(this, messageLite, protoContainer.getNameResolver(), protoContainer.getTypeTable(), annotatedCallableKind, false, 16, (Object) null);
        if (callableSignature$default == null) {
            return CollectionsKt.emptyList();
        }
        return findClassAndLoadMemberAnnotations$default(this, protoContainer, MemberSignature.Companion.fromMethodSignatureAndParameterIndex(callableSignature$default, i + computeJvmParameterIndexShift(protoContainer, messageLite)), false, false, (Boolean) null, false, 60, (Object) null);
    }

    private final int computeJvmParameterIndexShift(ProtoContainer protoContainer, MessageLite messageLite) {
        if (messageLite instanceof ProtoBuf.Function) {
            if (ProtoTypeTableUtilKt.hasReceiver((ProtoBuf.Function) messageLite)) {
                return 1;
            }
        } else if (messageLite instanceof ProtoBuf.Property) {
            if (ProtoTypeTableUtilKt.hasReceiver((ProtoBuf.Property) messageLite)) {
                return 1;
            }
        } else if (messageLite instanceof ProtoBuf.Constructor) {
            Intrinsics.checkNotNull(protoContainer, "null cannot be cast to non-null type org.jetbrains.kotlin.serialization.deserialization.ProtoContainer.Class");
            ProtoContainer.Class classR = (ProtoContainer.Class) protoContainer;
            if (classR.getKind() == ProtoBuf.Class.Kind.ENUM_CLASS) {
                return 2;
            }
            if (classR.isInner()) {
                return 1;
            }
        } else {
            throw new UnsupportedOperationException("Unsupported message: " + messageLite.getClass());
        }
        return 0;
    }

    public List<A> loadExtensionReceiverParameterAnnotations(ProtoContainer protoContainer, MessageLite messageLite, AnnotatedCallableKind annotatedCallableKind) {
        Intrinsics.checkNotNullParameter(protoContainer, TtmlNode.RUBY_CONTAINER);
        Intrinsics.checkNotNullParameter(messageLite, "proto");
        Intrinsics.checkNotNullParameter(annotatedCallableKind, "kind");
        MemberSignature callableSignature$default = getCallableSignature$default(this, messageLite, protoContainer.getNameResolver(), protoContainer.getTypeTable(), annotatedCallableKind, false, 16, (Object) null);
        if (callableSignature$default == null) {
            return CollectionsKt.emptyList();
        }
        return findClassAndLoadMemberAnnotations$default(this, protoContainer, MemberSignature.Companion.fromMethodSignatureAndParameterIndex(callableSignature$default, 0), false, false, (Boolean) null, false, 60, (Object) null);
    }

    public List<A> loadTypeAnnotations(ProtoBuf.Type type, NameResolver nameResolver) {
        Intrinsics.checkNotNullParameter(type, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Object extension = type.getExtension(JvmProtoBuf.typeAnnotation);
        Intrinsics.checkNotNullExpressionValue(extension, "getExtension(...)");
        Iterable<ProtoBuf.Annotation> iterable = (Iterable) extension;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (ProtoBuf.Annotation annotation : iterable) {
            Intrinsics.checkNotNull(annotation);
            arrayList.add(loadAnnotation(annotation, nameResolver));
        }
        return (List) arrayList;
    }

    public List<A> loadTypeParameterAnnotations(ProtoBuf.TypeParameter typeParameter, NameResolver nameResolver) {
        Intrinsics.checkNotNullParameter(typeParameter, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Object extension = typeParameter.getExtension(JvmProtoBuf.typeParameterAnnotation);
        Intrinsics.checkNotNullExpressionValue(extension, "getExtension(...)");
        Iterable<ProtoBuf.Annotation> iterable = (Iterable) extension;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (ProtoBuf.Annotation annotation : iterable) {
            Intrinsics.checkNotNull(annotation);
            arrayList.add(loadAnnotation(annotation, nameResolver));
        }
        return (List) arrayList;
    }

    /* access modifiers changed from: protected */
    public final KotlinJvmBinaryClass findClassWithAnnotationsAndInitializers(ProtoContainer protoContainer, KotlinJvmBinaryClass kotlinJvmBinaryClass) {
        Intrinsics.checkNotNullParameter(protoContainer, TtmlNode.RUBY_CONTAINER);
        if (kotlinJvmBinaryClass != null) {
            return kotlinJvmBinaryClass;
        }
        if (protoContainer instanceof ProtoContainer.Class) {
            return toBinaryClass((ProtoContainer.Class) protoContainer);
        }
        return null;
    }

    public static /* synthetic */ MemberSignature getCallableSignature$default(AbstractBinaryClassAnnotationLoader abstractBinaryClassAnnotationLoader, MessageLite messageLite, NameResolver nameResolver, TypeTable typeTable, AnnotatedCallableKind annotatedCallableKind, boolean z, int i, Object obj) {
        if (obj == null) {
            if ((i & 16) != 0) {
                z = false;
            }
            return abstractBinaryClassAnnotationLoader.getCallableSignature(messageLite, nameResolver, typeTable, annotatedCallableKind, z);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getCallableSignature");
    }

    /* access modifiers changed from: protected */
    public final MemberSignature getCallableSignature(MessageLite messageLite, NameResolver nameResolver, TypeTable typeTable, AnnotatedCallableKind annotatedCallableKind, boolean z) {
        Intrinsics.checkNotNullParameter(messageLite, "proto");
        Intrinsics.checkNotNullParameter(nameResolver, "nameResolver");
        Intrinsics.checkNotNullParameter(typeTable, "typeTable");
        Intrinsics.checkNotNullParameter(annotatedCallableKind, "kind");
        if (messageLite instanceof ProtoBuf.Constructor) {
            MemberSignature.Companion companion = MemberSignature.Companion;
            JvmMemberSignature.Method jvmConstructorSignature = JvmProtoBufUtil.INSTANCE.getJvmConstructorSignature((ProtoBuf.Constructor) messageLite, nameResolver, typeTable);
            if (jvmConstructorSignature == null) {
                return null;
            }
            return companion.fromJvmMemberSignature(jvmConstructorSignature);
        } else if (messageLite instanceof ProtoBuf.Function) {
            MemberSignature.Companion companion2 = MemberSignature.Companion;
            JvmMemberSignature.Method jvmMethodSignature = JvmProtoBufUtil.INSTANCE.getJvmMethodSignature((ProtoBuf.Function) messageLite, nameResolver, typeTable);
            if (jvmMethodSignature == null) {
                return null;
            }
            return companion2.fromJvmMemberSignature(jvmMethodSignature);
        } else if (!(messageLite instanceof ProtoBuf.Property)) {
            return null;
        } else {
            GeneratedMessageLite.GeneratedExtension<ProtoBuf.Property, JvmProtoBuf.JvmPropertySignature> generatedExtension = JvmProtoBuf.propertySignature;
            Intrinsics.checkNotNullExpressionValue(generatedExtension, "propertySignature");
            JvmProtoBuf.JvmPropertySignature jvmPropertySignature = (JvmProtoBuf.JvmPropertySignature) ProtoBufUtilKt.getExtensionOrNull((GeneratedMessageLite.ExtendableMessage) messageLite, generatedExtension);
            if (jvmPropertySignature == null) {
                return null;
            }
            int i = WhenMappings.$EnumSwitchMapping$0[annotatedCallableKind.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return null;
                    }
                    return AbstractBinaryClassAnnotationLoaderKt.getPropertySignature((ProtoBuf.Property) messageLite, nameResolver, typeTable, true, true, z);
                } else if (!jvmPropertySignature.hasSetter()) {
                    return null;
                } else {
                    MemberSignature.Companion companion3 = MemberSignature.Companion;
                    JvmProtoBuf.JvmMethodSignature setter = jvmPropertySignature.getSetter();
                    Intrinsics.checkNotNullExpressionValue(setter, "getSetter(...)");
                    return companion3.fromMethod(nameResolver, setter);
                }
            } else if (!jvmPropertySignature.hasGetter()) {
                return null;
            } else {
                MemberSignature.Companion companion4 = MemberSignature.Companion;
                JvmProtoBuf.JvmMethodSignature getter = jvmPropertySignature.getGetter();
                Intrinsics.checkNotNullExpressionValue(getter, "getGetter(...)");
                return companion4.fromMethod(nameResolver, getter);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean isImplicitRepeatableContainer(ClassId classId) {
        KotlinJvmBinaryClass findKotlinClass;
        Intrinsics.checkNotNullParameter(classId, "classId");
        if (classId.getOuterClassId() == null || !Intrinsics.areEqual((Object) classId.getShortClassName().asString(), (Object) "Container") || (findKotlinClass = KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, classId, getJvmMetadataVersion())) == null || !SpecialJvmAnnotations.INSTANCE.isAnnotatedWithContainerMetaAnnotation(findKotlinClass)) {
            return false;
        }
        return true;
    }

    /* compiled from: AbstractBinaryClassAnnotationLoader.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KotlinJvmBinaryClass getSpecialCaseContainerClass(ProtoContainer protoContainer, boolean z, boolean z2, Boolean bool, boolean z3, KotlinClassFinder kotlinClassFinder, JvmMetadataVersion jvmMetadataVersion) {
            ProtoContainer.Class outerClass;
            Intrinsics.checkNotNullParameter(protoContainer, TtmlNode.RUBY_CONTAINER);
            Intrinsics.checkNotNullParameter(kotlinClassFinder, "kotlinClassFinder");
            Intrinsics.checkNotNullParameter(jvmMetadataVersion, "jvmMetadataVersion");
            if (z) {
                if (bool != null) {
                    if (protoContainer instanceof ProtoContainer.Class) {
                        ProtoContainer.Class classR = (ProtoContainer.Class) protoContainer;
                        if (classR.getKind() == ProtoBuf.Class.Kind.INTERFACE) {
                            ClassId createNestedClassId = classR.getClassId().createNestedClassId(Name.identifier("DefaultImpls"));
                            Intrinsics.checkNotNullExpressionValue(createNestedClassId, "createNestedClassId(...)");
                            return KotlinClassFinderKt.findKotlinClass(kotlinClassFinder, createNestedClassId, jvmMetadataVersion);
                        }
                    }
                    if (bool.booleanValue() && (protoContainer instanceof ProtoContainer.Package)) {
                        SourceElement source = protoContainer.getSource();
                        JvmPackagePartSource jvmPackagePartSource = source instanceof JvmPackagePartSource ? (JvmPackagePartSource) source : null;
                        JvmClassName facadeClassName = jvmPackagePartSource != null ? jvmPackagePartSource.getFacadeClassName() : null;
                        if (facadeClassName != null) {
                            String internalName = facadeClassName.getInternalName();
                            Intrinsics.checkNotNullExpressionValue(internalName, "getInternalName(...)");
                            ClassId classId = ClassId.topLevel(new FqName(StringsKt.replace$default(internalName, (char) IOUtils.DIR_SEPARATOR_UNIX, (char) FilenameUtils.EXTENSION_SEPARATOR, false, 4, (Object) null)));
                            Intrinsics.checkNotNullExpressionValue(classId, "topLevel(...)");
                            return KotlinClassFinderKt.findKotlinClass(kotlinClassFinder, classId, jvmMetadataVersion);
                        }
                    }
                } else {
                    throw new IllegalStateException(("isConst should not be null for property (container=" + protoContainer + ')').toString());
                }
            }
            if (z2 && (protoContainer instanceof ProtoContainer.Class)) {
                ProtoContainer.Class classR2 = (ProtoContainer.Class) protoContainer;
                if (classR2.getKind() == ProtoBuf.Class.Kind.COMPANION_OBJECT && (outerClass = classR2.getOuterClass()) != null && (outerClass.getKind() == ProtoBuf.Class.Kind.CLASS || outerClass.getKind() == ProtoBuf.Class.Kind.ENUM_CLASS || (z3 && (outerClass.getKind() == ProtoBuf.Class.Kind.INTERFACE || outerClass.getKind() == ProtoBuf.Class.Kind.ANNOTATION_CLASS)))) {
                    SourceElement source2 = outerClass.getSource();
                    KotlinJvmBinarySourceElement kotlinJvmBinarySourceElement = source2 instanceof KotlinJvmBinarySourceElement ? (KotlinJvmBinarySourceElement) source2 : null;
                    if (kotlinJvmBinarySourceElement != null) {
                        return kotlinJvmBinarySourceElement.getBinaryClass();
                    }
                    return null;
                }
            }
            if (!(protoContainer instanceof ProtoContainer.Package) || !(protoContainer.getSource() instanceof JvmPackagePartSource)) {
                return null;
            }
            SourceElement source3 = protoContainer.getSource();
            Intrinsics.checkNotNull(source3, "null cannot be cast to non-null type org.jetbrains.kotlin.load.kotlin.JvmPackagePartSource");
            JvmPackagePartSource jvmPackagePartSource2 = (JvmPackagePartSource) source3;
            KotlinJvmBinaryClass knownJvmBinaryClass = jvmPackagePartSource2.getKnownJvmBinaryClass();
            return knownJvmBinaryClass == null ? KotlinClassFinderKt.findKotlinClass(kotlinClassFinder, jvmPackagePartSource2.getClassId(), jvmMetadataVersion) : knownJvmBinaryClass;
        }
    }
}
