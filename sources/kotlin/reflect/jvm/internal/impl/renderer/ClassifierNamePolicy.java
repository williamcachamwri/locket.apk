package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import org.apache.commons.io.FilenameUtils;

/* compiled from: ClassifierNamePolicy.kt */
public interface ClassifierNamePolicy {
    String renderClassifier(ClassifierDescriptor classifierDescriptor, DescriptorRenderer descriptorRenderer);

    /* compiled from: ClassifierNamePolicy.kt */
    public static final class SHORT implements ClassifierNamePolicy {
        public static final SHORT INSTANCE = new SHORT();

        private SHORT() {
        }

        public String renderClassifier(ClassifierDescriptor classifierDescriptor, DescriptorRenderer descriptorRenderer) {
            Intrinsics.checkNotNullParameter(classifierDescriptor, "classifier");
            Intrinsics.checkNotNullParameter(descriptorRenderer, "renderer");
            if (classifierDescriptor instanceof TypeParameterDescriptor) {
                Name name = ((TypeParameterDescriptor) classifierDescriptor).getName();
                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                return descriptorRenderer.renderName(name, false);
            }
            ArrayList arrayList = new ArrayList();
            DeclarationDescriptor declarationDescriptor = classifierDescriptor;
            do {
                arrayList.add(declarationDescriptor.getName());
                declarationDescriptor = declarationDescriptor.getContainingDeclaration();
            } while (declarationDescriptor instanceof ClassDescriptor);
            return RenderingUtilsKt.renderFqName(CollectionsKt.asReversedMutable(arrayList));
        }
    }

    /* compiled from: ClassifierNamePolicy.kt */
    public static final class FULLY_QUALIFIED implements ClassifierNamePolicy {
        public static final FULLY_QUALIFIED INSTANCE = new FULLY_QUALIFIED();

        private FULLY_QUALIFIED() {
        }

        public String renderClassifier(ClassifierDescriptor classifierDescriptor, DescriptorRenderer descriptorRenderer) {
            Intrinsics.checkNotNullParameter(classifierDescriptor, "classifier");
            Intrinsics.checkNotNullParameter(descriptorRenderer, "renderer");
            if (classifierDescriptor instanceof TypeParameterDescriptor) {
                Name name = ((TypeParameterDescriptor) classifierDescriptor).getName();
                Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                return descriptorRenderer.renderName(name, false);
            }
            FqNameUnsafe fqName = DescriptorUtils.getFqName(classifierDescriptor);
            Intrinsics.checkNotNullExpressionValue(fqName, "getFqName(...)");
            return descriptorRenderer.renderFqName(fqName);
        }
    }

    /* compiled from: ClassifierNamePolicy.kt */
    public static final class SOURCE_CODE_QUALIFIED implements ClassifierNamePolicy {
        public static final SOURCE_CODE_QUALIFIED INSTANCE = new SOURCE_CODE_QUALIFIED();

        private SOURCE_CODE_QUALIFIED() {
        }

        public String renderClassifier(ClassifierDescriptor classifierDescriptor, DescriptorRenderer descriptorRenderer) {
            Intrinsics.checkNotNullParameter(classifierDescriptor, "classifier");
            Intrinsics.checkNotNullParameter(descriptorRenderer, "renderer");
            return qualifiedNameForSourceCode(classifierDescriptor);
        }

        private final String qualifiedNameForSourceCode(ClassifierDescriptor classifierDescriptor) {
            Name name = classifierDescriptor.getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            String render = RenderingUtilsKt.render(name);
            if (classifierDescriptor instanceof TypeParameterDescriptor) {
                return render;
            }
            DeclarationDescriptor containingDeclaration = classifierDescriptor.getContainingDeclaration();
            Intrinsics.checkNotNullExpressionValue(containingDeclaration, "getContainingDeclaration(...)");
            String qualifierName = qualifierName(containingDeclaration);
            return (qualifierName == null || Intrinsics.areEqual((Object) qualifierName, (Object) "")) ? render : qualifierName + FilenameUtils.EXTENSION_SEPARATOR + render;
        }

        private final String qualifierName(DeclarationDescriptor declarationDescriptor) {
            if (declarationDescriptor instanceof ClassDescriptor) {
                return qualifiedNameForSourceCode((ClassifierDescriptor) declarationDescriptor);
            }
            if (!(declarationDescriptor instanceof PackageFragmentDescriptor)) {
                return null;
            }
            FqNameUnsafe unsafe = ((PackageFragmentDescriptor) declarationDescriptor).getFqName().toUnsafe();
            Intrinsics.checkNotNullExpressionValue(unsafe, "toUnsafe(...)");
            return RenderingUtilsKt.render(unsafe);
        }
    }
}
