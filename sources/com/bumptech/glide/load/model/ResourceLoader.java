package com.bumptech.glide.load.model;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.ModelLoader;
import io.sentry.android.core.SentryLogcatAdapter;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public class ResourceLoader<Data> implements ModelLoader<Integer, Data> {
    private static final String TAG = "ResourceLoader";
    private final Resources resources;
    private final ModelLoader<Uri, Data> uriLoader;

    public boolean handles(Integer num) {
        return true;
    }

    public ResourceLoader(Resources resources2, ModelLoader<Uri, Data> modelLoader) {
        this.resources = resources2;
        this.uriLoader = modelLoader;
    }

    public ModelLoader.LoadData<Data> buildLoadData(Integer num, int i, int i2, Options options) {
        Uri resourceUri = getResourceUri(num);
        if (resourceUri == null) {
            return null;
        }
        return this.uriLoader.buildLoadData(resourceUri, i, i2, options);
    }

    private Uri getResourceUri(Integer num) {
        try {
            return Uri.parse("android.resource://" + this.resources.getResourcePackageName(num.intValue()) + IOUtils.DIR_SEPARATOR_UNIX + this.resources.getResourceTypeName(num.intValue()) + IOUtils.DIR_SEPARATOR_UNIX + this.resources.getResourceEntryName(num.intValue()));
        } catch (Resources.NotFoundException e) {
            if (!Log.isLoggable(TAG, 5)) {
                return null;
            }
            SentryLogcatAdapter.w(TAG, "Received invalid resource id: " + num, e);
            return null;
        }
    }

    public static class StreamFactory implements ModelLoaderFactory<Integer, InputStream> {
        private final Resources resources;

        public void teardown() {
        }

        public StreamFactory(Resources resources2) {
            this.resources = resources2;
        }

        public ModelLoader<Integer, InputStream> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ResourceLoader(this.resources, multiModelLoaderFactory.build(Uri.class, InputStream.class));
        }
    }

    @Deprecated
    public static class FileDescriptorFactory implements ModelLoaderFactory<Integer, ParcelFileDescriptor> {
        private final Resources resources;

        public void teardown() {
        }

        public FileDescriptorFactory(Resources resources2) {
            this.resources = resources2;
        }

        public ModelLoader<Integer, ParcelFileDescriptor> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ResourceLoader(this.resources, multiModelLoaderFactory.build(Uri.class, ParcelFileDescriptor.class));
        }
    }

    public static final class AssetFileDescriptorFactory implements ModelLoaderFactory<Integer, AssetFileDescriptor> {
        private final Resources resources;

        public void teardown() {
        }

        public AssetFileDescriptorFactory(Resources resources2) {
            this.resources = resources2;
        }

        public ModelLoader<Integer, AssetFileDescriptor> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ResourceLoader(this.resources, multiModelLoaderFactory.build(Uri.class, AssetFileDescriptor.class));
        }
    }

    public static class UriFactory implements ModelLoaderFactory<Integer, Uri> {
        private final Resources resources;

        public void teardown() {
        }

        public UriFactory(Resources resources2) {
            this.resources = resources2;
        }

        public ModelLoader<Integer, Uri> build(MultiModelLoaderFactory multiModelLoaderFactory) {
            return new ResourceLoader(this.resources, UnitModelLoader.getInstance());
        }
    }
}
