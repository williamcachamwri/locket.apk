package com.locket.Locket;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;
import com.locket.Locket.CdnGlideModelLoader;
import com.locket.Locket.FirestoreGlideModelLoader;
import java.io.InputStream;

public class LocketGlideModule extends AppGlideModule {
    public void registerComponents(Context context, Glide glide, Registry registry) {
        registry.prepend(GlideUrl.class, InputStream.class, new FirestoreGlideModelLoader.Factory());
        registry.prepend(GlideUrl.class, InputStream.class, new CdnGlideModelLoader.Factory());
    }
}
