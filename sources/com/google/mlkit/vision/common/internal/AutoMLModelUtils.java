package com.google.mlkit.vision.common.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.common.internal.model.ModelUtils;
import com.google.mlkit.common.model.LocalModel;
import com.google.mlkit.common.sdkinternal.Constants;
import io.sentry.instrumentation.file.SentryFileInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.mlkit:vision-common@@17.3.0 */
public class AutoMLModelUtils {
    private AutoMLModelUtils() {
    }

    public static String[] getModelAndLabelFilePaths(Context context, LocalModel localModel, boolean z) throws IOException {
        String str;
        String str2;
        if (z) {
            str = (String) Preconditions.checkNotNull(localModel.getAssetFilePath());
        } else {
            str = (String) Preconditions.checkNotNull(localModel.getAbsoluteFilePath());
        }
        if (localModel.isManifestFile()) {
            ModelUtils.AutoMLManifest parseManifestFile = ModelUtils.parseManifestFile(str, z, context);
            if (parseManifestFile != null) {
                Preconditions.checkState(Constants.AUTOML_IMAGE_LABELING_MODEL_TYPE.equals(parseManifestFile.getModelType()), "Model type should be: %s.", Constants.AUTOML_IMAGE_LABELING_MODEL_TYPE);
                str = new File(new File(str).getParent(), parseManifestFile.getModelFile()).toString();
                str2 = new File(new File(str).getParent(), parseManifestFile.getLabelsFile()).toString();
            } else {
                throw new IOException("Failed to parse manifest file.");
            }
        } else {
            str2 = "";
        }
        return new String[]{str, str2};
    }

    public static List<String> readLabelsFile(Context context, String str, boolean z) throws IOException {
        InputStream inputStream;
        ArrayList arrayList = new ArrayList();
        if (z) {
            inputStream = context.getAssets().open(str);
        } else {
            File file = new File(str);
            inputStream = SentryFileInputStream.Factory.create(new FileInputStream(file), file);
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                arrayList.add(readLine);
            }
            if (inputStream != null) {
                inputStream.close();
            }
            return arrayList;
        } catch (Throwable th) {
            try {
                Throwable.class.getDeclaredMethod("addSuppressed", new Class[]{Throwable.class}).invoke(th, new Object[]{th});
            } catch (Exception unused) {
            }
        }
        throw th;
    }
}
