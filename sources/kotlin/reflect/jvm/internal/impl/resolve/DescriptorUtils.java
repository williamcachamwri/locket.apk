package kotlin.reflect.jvm.internal.impl.resolve;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.text.HtmlCompat;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.extractor.ts.TsExtractor;
import com.facebook.imagepipeline.transcoder.JpegTranscoderUtils;
import com.facebook.soloader.Elf64;
import com.google.firebase.analytics.FirebaseAnalytics;
import expo.modules.interfaces.permissions.PermissionsResponse;
import io.sentry.protocol.SentryThread;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.UnsignedTypes;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertySetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceFile;
import kotlin.reflect.jvm.internal.impl.descriptors.VariableDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

public class DescriptorUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final FqName JVM_NAME = new FqName("kotlin.jvm.JvmName");

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        Throwable th;
        switch (i) {
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX:
            case 50:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG:
            case 52:
            case 53:
            case 60:
            case Elf64.Ehdr.E_SHSTRNDX:
            case HtmlCompat.FROM_HTML_MODE_COMPACT:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case TsExtractor.TS_STREAM_TYPE_DVBSUBS:
            case 94:
            case 96:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i) {
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX:
            case 50:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG:
            case 52:
            case 53:
            case 60:
            case Elf64.Ehdr.E_SHSTRNDX:
            case HtmlCompat.FROM_HTML_MODE_COMPACT:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case TsExtractor.TS_STREAM_TYPE_DVBSUBS:
            case 94:
            case 96:
                i2 = 2;
                break;
            default:
                i2 = 3;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 5:
            case 6:
            case 8:
            case 11:
            case 13:
            case 14:
            case 15:
            case 21:
            case 23:
            case 24:
            case 34:
            case 35:
            case 36:
            case 57:
            case Elf64.Ehdr.E_SHENTSIZE:
            case 59:
            case LockFreeTaskQueueCore.CLOSED_SHIFT /*61*/:
            case 64:
            case 82:
            case 95:
            case 97:
                objArr[0] = "descriptor";
                break;
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX:
            case 50:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG:
            case 52:
            case 53:
            case 60:
            case Elf64.Ehdr.E_SHSTRNDX:
            case HtmlCompat.FROM_HTML_MODE_COMPACT:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case TsExtractor.TS_STREAM_TYPE_DVBSUBS:
            case 94:
            case 96:
                objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorUtils";
                break;
            case 16:
                objArr[0] = "first";
                break;
            case 17:
                objArr[0] = "second";
                break;
            case 18:
            case 19:
                objArr[0] = "aClass";
                break;
            case 20:
                objArr[0] = "kotlinType";
                break;
            case 25:
                objArr[0] = "declarationDescriptor";
                break;
            case 26:
            case 28:
                objArr[0] = "subClass";
                break;
            case 27:
            case 29:
            case 33:
                objArr[0] = "superClass";
                break;
            case 30:
            case 32:
            case 45:
            case 67:
                objArr[0] = "type";
                break;
            case 31:
                objArr[0] = "other";
                break;
            case 37:
                objArr[0] = "classKind";
                break;
            case 38:
            case 39:
            case 41:
            case 44:
            case 48:
            case 54:
            case 68:
            case 69:
            case 70:
            case 77:
            case MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64:
                objArr[0] = "classDescriptor";
                break;
            case 46:
                objArr[0] = "typeConstructor";
                break;
            case 55:
                objArr[0] = "innerClassName";
                break;
            case 56:
                objArr[0] = FirebaseAnalytics.Param.LOCATION;
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT:
                objArr[0] = "variable";
                break;
            case TsExtractor.TS_SYNC_BYTE:
                objArr[0] = "f";
                break;
            case 73:
                objArr[0] = SentryThread.JsonKeys.CURRENT;
                break;
            case 74:
                objArr[0] = "result";
                break;
            case MdtaMetadataEntry.TYPE_INDICATOR_8_BIT_UNSIGNED_INT:
                objArr[0] = "memberDescriptor";
                break;
            case 79:
            case 80:
            case 81:
                objArr[0] = "annotated";
                break;
            case JpegTranscoderUtils.DEFAULT_JPEG_QUALITY:
            case 87:
            case 90:
            case 92:
                objArr[0] = PermissionsResponse.SCOPE_KEY;
                break;
            case 88:
            case 91:
            case 93:
                objArr[0] = "name";
                break;
            default:
                objArr[0] = "containingDeclaration";
                break;
        }
        switch (i) {
            case 4:
                objArr[1] = "getFqNameSafe";
                break;
            case 7:
                objArr[1] = "getFqNameUnsafe";
                break;
            case 9:
            case 10:
                objArr[1] = "getFqNameFromTopLevelClass";
                break;
            case 12:
                objArr[1] = "getClassIdForNonLocalClass";
                break;
            case 22:
                objArr[1] = "getContainingModule";
                break;
            case 40:
                objArr[1] = "getSuperclassDescriptors";
                break;
            case 42:
            case 43:
                objArr[1] = "getSuperClassType";
                break;
            case 47:
                objArr[1] = "getClassDescriptorForTypeConstructor";
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX:
            case 50:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG:
            case 52:
            case 53:
                objArr[1] = "getDefaultConstructorVisibility";
                break;
            case 60:
                objArr[1] = "unwrapFakeOverride";
                break;
            case Elf64.Ehdr.E_SHSTRNDX:
            case HtmlCompat.FROM_HTML_MODE_COMPACT:
                objArr[1] = "unwrapSubstitutionOverride";
                break;
            case 65:
                objArr[1] = "unwrapFakeOverrideToAnyDeclaration";
                break;
            case 72:
                objArr[1] = "getAllOverriddenDescriptors";
                break;
            case 76:
                objArr[1] = "getAllOverriddenDeclarations";
                break;
            case 83:
            case 84:
                objArr[1] = "getContainingSourceFile";
                break;
            case 86:
                objArr[1] = "getAllDescriptors";
                break;
            case TsExtractor.TS_STREAM_TYPE_DVBSUBS:
                objArr[1] = "getFunctionByName";
                break;
            case 94:
                objArr[1] = "getPropertyByName";
                break;
            case 96:
                objArr[1] = "getDirectMember";
                break;
            default:
                objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/DescriptorUtils";
                break;
        }
        switch (i) {
            case 1:
                objArr[2] = "isLocal";
                break;
            case 2:
                objArr[2] = "getFqName";
                break;
            case 3:
                objArr[2] = "getFqNameSafe";
                break;
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX:
            case 50:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG:
            case 52:
            case 53:
            case 60:
            case Elf64.Ehdr.E_SHSTRNDX:
            case HtmlCompat.FROM_HTML_MODE_COMPACT:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case TsExtractor.TS_STREAM_TYPE_DVBSUBS:
            case 94:
            case 96:
                break;
            case 5:
                objArr[2] = "getFqNameSafeIfPossible";
                break;
            case 6:
                objArr[2] = "getFqNameUnsafe";
                break;
            case 8:
                objArr[2] = "getFqNameFromTopLevelClass";
                break;
            case 11:
                objArr[2] = "getClassIdForNonLocalClass";
                break;
            case 13:
                objArr[2] = "isExtension";
                break;
            case 14:
                objArr[2] = "isOverride";
                break;
            case 15:
                objArr[2] = "isStaticDeclaration";
                break;
            case 16:
            case 17:
                objArr[2] = "areInSameModule";
                break;
            case 18:
            case 19:
                objArr[2] = "getParentOfType";
                break;
            case 20:
            case 23:
                objArr[2] = "getContainingModuleOrNull";
                break;
            case 21:
                objArr[2] = "getContainingModule";
                break;
            case 24:
                objArr[2] = "getContainingClass";
                break;
            case 25:
                objArr[2] = "isAncestor";
                break;
            case 26:
            case 27:
                objArr[2] = "isDirectSubclass";
                break;
            case 28:
            case 29:
                objArr[2] = "isSubclass";
                break;
            case 30:
            case 31:
                objArr[2] = "isSameClass";
                break;
            case 32:
            case 33:
                objArr[2] = "isSubtypeOfClass";
                break;
            case 34:
                objArr[2] = "isAnonymousObject";
                break;
            case 35:
                objArr[2] = "isAnonymousFunction";
                break;
            case 36:
                objArr[2] = "isEnumEntry";
                break;
            case 37:
                objArr[2] = "isKindOf";
                break;
            case 38:
                objArr[2] = "hasAbstractMembers";
                break;
            case 39:
                objArr[2] = "getSuperclassDescriptors";
                break;
            case 41:
                objArr[2] = "getSuperClassType";
                break;
            case 44:
                objArr[2] = "getSuperClassDescriptor";
                break;
            case 45:
                objArr[2] = "getClassDescriptorForType";
                break;
            case 46:
                objArr[2] = "getClassDescriptorForTypeConstructor";
                break;
            case 48:
                objArr[2] = "getDefaultConstructorVisibility";
                break;
            case 54:
            case 55:
            case 56:
                objArr[2] = "getInnerClassByName";
                break;
            case 57:
                objArr[2] = "isStaticNestedClass";
                break;
            case Elf64.Ehdr.E_SHENTSIZE:
                objArr[2] = "isTopLevelOrInnerClass";
                break;
            case 59:
                objArr[2] = "unwrapFakeOverride";
                break;
            case LockFreeTaskQueueCore.CLOSED_SHIFT /*61*/:
                objArr[2] = "unwrapSubstitutionOverride";
                break;
            case 64:
                objArr[2] = "unwrapFakeOverrideToAnyDeclaration";
                break;
            case ConstraintLayout.LayoutParams.Table.LAYOUT_WRAP_BEHAVIOR_IN_PARENT:
            case 67:
                objArr[2] = "shouldRecordInitializerForProperty";
                break;
            case 68:
                objArr[2] = "classCanHaveAbstractFakeOverride";
                break;
            case 69:
                objArr[2] = "classCanHaveAbstractDeclaration";
                break;
            case 70:
                objArr[2] = "classCanHaveOpenMembers";
                break;
            case TsExtractor.TS_SYNC_BYTE:
                objArr[2] = "getAllOverriddenDescriptors";
                break;
            case 73:
            case 74:
                objArr[2] = "collectAllOverriddenDescriptors";
                break;
            case MdtaMetadataEntry.TYPE_INDICATOR_8_BIT_UNSIGNED_INT:
                objArr[2] = "getAllOverriddenDeclarations";
                break;
            case 77:
                objArr[2] = "isSingletonOrAnonymousObject";
                break;
            case MdtaMetadataEntry.TYPE_INDICATOR_UNSIGNED_INT64:
                objArr[2] = "canHaveDeclaredConstructors";
                break;
            case 79:
                objArr[2] = "getJvmName";
                break;
            case 80:
                objArr[2] = "findJvmNameAnnotation";
                break;
            case 81:
                objArr[2] = "hasJvmNameAnnotation";
                break;
            case 82:
                objArr[2] = "getContainingSourceFile";
                break;
            case JpegTranscoderUtils.DEFAULT_JPEG_QUALITY:
                objArr[2] = "getAllDescriptors";
                break;
            case 87:
            case 88:
                objArr[2] = "getFunctionByName";
                break;
            case 90:
            case 91:
                objArr[2] = "getFunctionByNameOrNull";
                break;
            case 92:
            case 93:
                objArr[2] = "getPropertyByName";
                break;
            case 95:
                objArr[2] = "getDirectMember";
                break;
            case 97:
                objArr[2] = "isMethodOfAny";
                break;
            default:
                objArr[2] = "getDispatchReceiverParameterIfNeeded";
                break;
        }
        String format = String.format(str, objArr);
        switch (i) {
            case 4:
            case 7:
            case 9:
            case 10:
            case 12:
            case 22:
            case 40:
            case 42:
            case 43:
            case 47:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX:
            case 50:
            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_TAG:
            case 52:
            case 53:
            case 60:
            case Elf64.Ehdr.E_SHSTRNDX:
            case HtmlCompat.FROM_HTML_MODE_COMPACT:
            case 65:
            case 72:
            case 76:
            case 83:
            case 84:
            case 86:
            case TsExtractor.TS_STREAM_TYPE_DVBSUBS:
            case 94:
            case 96:
                th = new IllegalStateException(format);
                break;
            default:
                th = new IllegalArgumentException(format);
                break;
        }
        throw th;
    }

    private DescriptorUtils() {
    }

    public static ReceiverParameterDescriptor getDispatchReceiverParameterIfNeeded(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(0);
        }
        if (declarationDescriptor instanceof ClassDescriptor) {
            return ((ClassDescriptor) declarationDescriptor).getThisAsReceiverParameter();
        }
        return null;
    }

    public static boolean isLocal(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(1);
        }
        while (declarationDescriptor != null) {
            if (isAnonymousObject(declarationDescriptor) || isDescriptorWithLocalVisibility(declarationDescriptor)) {
                return true;
            }
            declarationDescriptor = declarationDescriptor.getContainingDeclaration();
        }
        return false;
    }

    public static boolean isDescriptorWithLocalVisibility(DeclarationDescriptor declarationDescriptor) {
        return (declarationDescriptor instanceof DeclarationDescriptorWithVisibility) && ((DeclarationDescriptorWithVisibility) declarationDescriptor).getVisibility() == DescriptorVisibilities.LOCAL;
    }

    public static FqNameUnsafe getFqName(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(2);
        }
        FqName fqNameSafeIfPossible = getFqNameSafeIfPossible(declarationDescriptor);
        return fqNameSafeIfPossible != null ? fqNameSafeIfPossible.toUnsafe() : getFqNameUnsafe(declarationDescriptor);
    }

    public static FqName getFqNameSafe(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(3);
        }
        FqName fqNameSafeIfPossible = getFqNameSafeIfPossible(declarationDescriptor);
        if (fqNameSafeIfPossible == null) {
            fqNameSafeIfPossible = getFqNameUnsafe(declarationDescriptor).toSafe();
        }
        if (fqNameSafeIfPossible == null) {
            $$$reportNull$$$0(4);
        }
        return fqNameSafeIfPossible;
    }

    private static FqName getFqNameSafeIfPossible(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(5);
        }
        if ((declarationDescriptor instanceof ModuleDescriptor) || ErrorUtils.isError(declarationDescriptor)) {
            return FqName.ROOT;
        }
        if (declarationDescriptor instanceof PackageViewDescriptor) {
            return ((PackageViewDescriptor) declarationDescriptor).getFqName();
        }
        if (declarationDescriptor instanceof PackageFragmentDescriptor) {
            return ((PackageFragmentDescriptor) declarationDescriptor).getFqName();
        }
        return null;
    }

    private static FqNameUnsafe getFqNameUnsafe(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(6);
        }
        FqNameUnsafe child = getFqName(declarationDescriptor.getContainingDeclaration()).child(declarationDescriptor.getName());
        if (child == null) {
            $$$reportNull$$$0(7);
        }
        return child;
    }

    public static boolean isTopLevelDeclaration(DeclarationDescriptor declarationDescriptor) {
        return declarationDescriptor != null && (declarationDescriptor.getContainingDeclaration() instanceof PackageFragmentDescriptor);
    }

    public static boolean areInSameModule(DeclarationDescriptor declarationDescriptor, DeclarationDescriptor declarationDescriptor2) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(16);
        }
        if (declarationDescriptor2 == null) {
            $$$reportNull$$$0(17);
        }
        return getContainingModule(declarationDescriptor).equals(getContainingModule(declarationDescriptor2));
    }

    public static <D extends DeclarationDescriptor> D getParentOfType(DeclarationDescriptor declarationDescriptor, Class<D> cls) {
        if (cls == null) {
            $$$reportNull$$$0(18);
        }
        return getParentOfType(declarationDescriptor, cls, true);
    }

    public static <D extends DeclarationDescriptor> D getParentOfType(D d, Class<D> cls, boolean z) {
        if (cls == null) {
            $$$reportNull$$$0(19);
        }
        if (d == null) {
            return null;
        }
        if (z) {
            d = d.getContainingDeclaration();
        }
        while (d != null) {
            if (cls.isInstance(d)) {
                return d;
            }
            d = d.getContainingDeclaration();
        }
        return null;
    }

    public static ModuleDescriptor getContainingModuleOrNull(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(20);
        }
        ClassifierDescriptor declarationDescriptor = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor == null) {
            return null;
        }
        return getContainingModuleOrNull((DeclarationDescriptor) declarationDescriptor);
    }

    public static ModuleDescriptor getContainingModule(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(21);
        }
        ModuleDescriptor containingModuleOrNull = getContainingModuleOrNull(declarationDescriptor);
        if (containingModuleOrNull == null) {
            $$$reportNull$$$0(22);
        }
        return containingModuleOrNull;
    }

    public static ModuleDescriptor getContainingModuleOrNull(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(23);
        }
        while (declarationDescriptor != null) {
            if (declarationDescriptor instanceof ModuleDescriptor) {
                return (ModuleDescriptor) declarationDescriptor;
            }
            if (declarationDescriptor instanceof PackageViewDescriptor) {
                return ((PackageViewDescriptor) declarationDescriptor).getModule();
            }
            declarationDescriptor = declarationDescriptor.getContainingDeclaration();
        }
        return null;
    }

    public static boolean isDirectSubclass(ClassDescriptor classDescriptor, ClassDescriptor classDescriptor2) {
        if (classDescriptor == null) {
            $$$reportNull$$$0(26);
        }
        if (classDescriptor2 == null) {
            $$$reportNull$$$0(27);
        }
        for (KotlinType isSameClass : classDescriptor.getTypeConstructor().getSupertypes()) {
            if (isSameClass(isSameClass, classDescriptor2.getOriginal())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSubclass(ClassDescriptor classDescriptor, ClassDescriptor classDescriptor2) {
        if (classDescriptor == null) {
            $$$reportNull$$$0(28);
        }
        if (classDescriptor2 == null) {
            $$$reportNull$$$0(29);
        }
        return isSubtypeOfClass(classDescriptor.getDefaultType(), classDescriptor2.getOriginal());
    }

    private static boolean isSameClass(KotlinType kotlinType, DeclarationDescriptor declarationDescriptor) {
        if (kotlinType == null) {
            $$$reportNull$$$0(30);
        }
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(31);
        }
        ClassifierDescriptor declarationDescriptor2 = kotlinType.getConstructor().getDeclarationDescriptor();
        if (declarationDescriptor2 == null) {
            return false;
        }
        DeclarationDescriptor original = declarationDescriptor2.getOriginal();
        return (original instanceof ClassifierDescriptor) && (declarationDescriptor instanceof ClassifierDescriptor) && ((ClassifierDescriptor) declarationDescriptor).getTypeConstructor().equals(((ClassifierDescriptor) original).getTypeConstructor());
    }

    public static boolean isSubtypeOfClass(KotlinType kotlinType, DeclarationDescriptor declarationDescriptor) {
        if (kotlinType == null) {
            $$$reportNull$$$0(32);
        }
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(33);
        }
        if (isSameClass(kotlinType, declarationDescriptor)) {
            return true;
        }
        for (KotlinType isSubtypeOfClass : kotlinType.getConstructor().getSupertypes()) {
            if (isSubtypeOfClass(isSubtypeOfClass, declarationDescriptor)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isCompanionObject(DeclarationDescriptor declarationDescriptor) {
        return isKindOf(declarationDescriptor, ClassKind.OBJECT) && ((ClassDescriptor) declarationDescriptor).isCompanionObject();
    }

    public static boolean isSealedClass(DeclarationDescriptor declarationDescriptor) {
        return (isKindOf(declarationDescriptor, ClassKind.CLASS) || isKindOf(declarationDescriptor, ClassKind.INTERFACE)) && ((ClassDescriptor) declarationDescriptor).getModality() == Modality.SEALED;
    }

    public static boolean isAnonymousObject(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(34);
        }
        return isClass(declarationDescriptor) && declarationDescriptor.getName().equals(SpecialNames.NO_NAME_PROVIDED);
    }

    public static boolean isEnumEntry(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(36);
        }
        return isKindOf(declarationDescriptor, ClassKind.ENUM_ENTRY);
    }

    public static boolean isEnumClass(DeclarationDescriptor declarationDescriptor) {
        return isKindOf(declarationDescriptor, ClassKind.ENUM_CLASS);
    }

    public static boolean isAnnotationClass(DeclarationDescriptor declarationDescriptor) {
        return isKindOf(declarationDescriptor, ClassKind.ANNOTATION_CLASS);
    }

    public static boolean isInterface(DeclarationDescriptor declarationDescriptor) {
        return isKindOf(declarationDescriptor, ClassKind.INTERFACE);
    }

    public static boolean isClass(DeclarationDescriptor declarationDescriptor) {
        return isKindOf(declarationDescriptor, ClassKind.CLASS);
    }

    public static boolean isClassOrEnumClass(DeclarationDescriptor declarationDescriptor) {
        return isClass(declarationDescriptor) || isEnumClass(declarationDescriptor);
    }

    private static boolean isKindOf(DeclarationDescriptor declarationDescriptor, ClassKind classKind) {
        if (classKind == null) {
            $$$reportNull$$$0(37);
        }
        return (declarationDescriptor instanceof ClassDescriptor) && ((ClassDescriptor) declarationDescriptor).getKind() == classKind;
    }

    public static ClassDescriptor getSuperClassDescriptor(ClassDescriptor classDescriptor) {
        if (classDescriptor == null) {
            $$$reportNull$$$0(44);
        }
        for (KotlinType classDescriptorForType : classDescriptor.getTypeConstructor().getSupertypes()) {
            ClassDescriptor classDescriptorForType2 = getClassDescriptorForType(classDescriptorForType);
            if (classDescriptorForType2.getKind() != ClassKind.INTERFACE) {
                return classDescriptorForType2;
            }
        }
        return null;
    }

    public static ClassDescriptor getClassDescriptorForType(KotlinType kotlinType) {
        if (kotlinType == null) {
            $$$reportNull$$$0(45);
        }
        return getClassDescriptorForTypeConstructor(kotlinType.getConstructor());
    }

    public static ClassDescriptor getClassDescriptorForTypeConstructor(TypeConstructor typeConstructor) {
        if (typeConstructor == null) {
            $$$reportNull$$$0(46);
        }
        ClassDescriptor classDescriptor = (ClassDescriptor) typeConstructor.getDeclarationDescriptor();
        if (classDescriptor == null) {
            $$$reportNull$$$0(47);
        }
        return classDescriptor;
    }

    public static DescriptorVisibility getDefaultConstructorVisibility(ClassDescriptor classDescriptor, boolean z) {
        if (classDescriptor == null) {
            $$$reportNull$$$0(48);
        }
        ClassKind kind = classDescriptor.getKind();
        if (kind == ClassKind.ENUM_CLASS || kind.isSingleton()) {
            DescriptorVisibility descriptorVisibility = DescriptorVisibilities.PRIVATE;
            if (descriptorVisibility == null) {
                $$$reportNull$$$0(49);
            }
            return descriptorVisibility;
        } else if (isSealedClass(classDescriptor)) {
            if (z) {
                DescriptorVisibility descriptorVisibility2 = DescriptorVisibilities.PROTECTED;
                if (descriptorVisibility2 == null) {
                    $$$reportNull$$$0(50);
                }
                return descriptorVisibility2;
            }
            DescriptorVisibility descriptorVisibility3 = DescriptorVisibilities.PRIVATE;
            if (descriptorVisibility3 == null) {
                $$$reportNull$$$0(51);
            }
            return descriptorVisibility3;
        } else if (isAnonymousObject(classDescriptor)) {
            DescriptorVisibility descriptorVisibility4 = DescriptorVisibilities.DEFAULT_VISIBILITY;
            if (descriptorVisibility4 == null) {
                $$$reportNull$$$0(52);
            }
            return descriptorVisibility4;
        } else {
            DescriptorVisibility descriptorVisibility5 = DescriptorVisibilities.PUBLIC;
            if (descriptorVisibility5 == null) {
                $$$reportNull$$$0(53);
            }
            return descriptorVisibility5;
        }
    }

    public static <D extends CallableMemberDescriptor> D unwrapFakeOverride(D d) {
        if (d == null) {
            $$$reportNull$$$0(59);
        }
        while (d.getKind() == CallableMemberDescriptor.Kind.FAKE_OVERRIDE) {
            Collection overriddenDescriptors = d.getOverriddenDescriptors();
            if (!overriddenDescriptors.isEmpty()) {
                d = (CallableMemberDescriptor) overriddenDescriptors.iterator().next();
            } else {
                throw new IllegalStateException("Fake override should have at least one overridden descriptor: " + d);
            }
        }
        if (d == null) {
            $$$reportNull$$$0(60);
        }
        return d;
    }

    public static <D extends DeclarationDescriptorWithVisibility> D unwrapFakeOverrideToAnyDeclaration(D d) {
        if (d == null) {
            $$$reportNull$$$0(64);
        }
        if (d instanceof CallableMemberDescriptor) {
            return unwrapFakeOverride((CallableMemberDescriptor) d);
        }
        if (d == null) {
            $$$reportNull$$$0(65);
        }
        return d;
    }

    public static boolean shouldRecordInitializerForProperty(VariableDescriptor variableDescriptor, KotlinType kotlinType) {
        if (variableDescriptor == null) {
            $$$reportNull$$$0(66);
        }
        if (kotlinType == null) {
            $$$reportNull$$$0(67);
        }
        if (variableDescriptor.isVar() || KotlinTypeKt.isError(kotlinType)) {
            return false;
        }
        if (TypeUtils.acceptsNullable(kotlinType)) {
            return true;
        }
        KotlinBuiltIns builtIns = DescriptorUtilsKt.getBuiltIns(variableDescriptor);
        if (!KotlinBuiltIns.isPrimitiveType(kotlinType) && !KotlinTypeChecker.DEFAULT.equalTypes(builtIns.getStringType(), kotlinType) && !KotlinTypeChecker.DEFAULT.equalTypes(builtIns.getNumber().getDefaultType(), kotlinType) && !KotlinTypeChecker.DEFAULT.equalTypes(builtIns.getAnyType(), kotlinType)) {
            UnsignedTypes unsignedTypes = UnsignedTypes.INSTANCE;
            if (!UnsignedTypes.isUnsignedType(kotlinType)) {
                return false;
            }
        }
        return true;
    }

    public static <D extends CallableDescriptor> Set<D> getAllOverriddenDescriptors(D d) {
        if (d == null) {
            $$$reportNull$$$0(71);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        collectAllOverriddenDescriptors(d.getOriginal(), linkedHashSet);
        return linkedHashSet;
    }

    private static <D extends CallableDescriptor> void collectAllOverriddenDescriptors(D d, Set<D> set) {
        if (d == null) {
            $$$reportNull$$$0(73);
        }
        if (set == null) {
            $$$reportNull$$$0(74);
        }
        if (!set.contains(d)) {
            for (CallableDescriptor original : d.getOriginal().getOverriddenDescriptors()) {
                CallableDescriptor original2 = original.getOriginal();
                collectAllOverriddenDescriptors(original2, set);
                set.add(original2);
            }
        }
    }

    public static SourceFile getContainingSourceFile(DeclarationDescriptor declarationDescriptor) {
        if (declarationDescriptor == null) {
            $$$reportNull$$$0(82);
        }
        if (declarationDescriptor instanceof PropertySetterDescriptor) {
            declarationDescriptor = ((PropertySetterDescriptor) declarationDescriptor).getCorrespondingProperty();
        }
        if (declarationDescriptor instanceof DeclarationDescriptorWithSource) {
            SourceFile containingFile = ((DeclarationDescriptorWithSource) declarationDescriptor).getSource().getContainingFile();
            if (containingFile == null) {
                $$$reportNull$$$0(83);
            }
            return containingFile;
        }
        SourceFile sourceFile = SourceFile.NO_SOURCE_FILE;
        if (sourceFile == null) {
            $$$reportNull$$$0(84);
        }
        return sourceFile;
    }
}
