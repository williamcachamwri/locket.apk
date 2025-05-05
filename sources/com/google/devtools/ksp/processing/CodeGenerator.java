package com.google.devtools.ksp.processing;

import com.google.devtools.ksp.symbol.KSClassDeclaration;
import com.google.devtools.ksp.symbol.KSFile;
import java.io.File;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J0\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\rH&J(\u0010\u0010\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\rH&J0\u0010\u0012\u001a\u00020\b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\rH&J*\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\rH&J\"\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\rH&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001aÀ\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/processing/CodeGenerator;", "", "generatedFile", "", "Ljava/io/File;", "getGeneratedFile", "()Ljava/util/Collection;", "associate", "", "sources", "", "Lcom/google/devtools/ksp/symbol/KSFile;", "packageName", "", "fileName", "extensionName", "associateByPath", "path", "associateWithClasses", "classes", "Lcom/google/devtools/ksp/symbol/KSClassDeclaration;", "createNewFile", "Ljava/io/OutputStream;", "dependencies", "Lcom/google/devtools/ksp/processing/Dependencies;", "createNewFileByPath", "api"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CodeGenerator.kt */
public interface CodeGenerator {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CodeGenerator.kt */
    public static final class DefaultImpls {
    }

    void associate(List<? extends KSFile> list, String str, String str2, String str3);

    void associateByPath(List<? extends KSFile> list, String str, String str2);

    void associateWithClasses(List<? extends KSClassDeclaration> list, String str, String str2, String str3);

    OutputStream createNewFile(Dependencies dependencies, String str, String str2, String str3);

    OutputStream createNewFileByPath(Dependencies dependencies, String str, String str2);

    Collection<File> getGeneratedFile();

    static /* synthetic */ OutputStream createNewFile$default(CodeGenerator codeGenerator, Dependencies dependencies, String str, String str2, String str3, int i, Object obj) {
        if (obj == null) {
            if ((i & 8) != 0) {
                str3 = "kt";
            }
            return codeGenerator.createNewFile(dependencies, str, str2, str3);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createNewFile");
    }

    static /* synthetic */ OutputStream createNewFileByPath$default(CodeGenerator codeGenerator, Dependencies dependencies, String str, String str2, int i, Object obj) {
        if (obj == null) {
            if ((i & 4) != 0) {
                str2 = "kt";
            }
            return codeGenerator.createNewFileByPath(dependencies, str, str2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createNewFileByPath");
    }

    static /* synthetic */ void associate$default(CodeGenerator codeGenerator, List list, String str, String str2, String str3, int i, Object obj) {
        if (obj == null) {
            if ((i & 8) != 0) {
                str3 = "kt";
            }
            codeGenerator.associate(list, str, str2, str3);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: associate");
    }

    static /* synthetic */ void associateByPath$default(CodeGenerator codeGenerator, List list, String str, String str2, int i, Object obj) {
        if (obj == null) {
            if ((i & 4) != 0) {
                str2 = "kt";
            }
            codeGenerator.associateByPath(list, str, str2);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: associateByPath");
    }

    static /* synthetic */ void associateWithClasses$default(CodeGenerator codeGenerator, List list, String str, String str2, String str3, int i, Object obj) {
        if (obj == null) {
            if ((i & 8) != 0) {
                str3 = "kt";
            }
            codeGenerator.associateWithClasses(list, str, str2, str3);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: associateWithClasses");
    }
}
