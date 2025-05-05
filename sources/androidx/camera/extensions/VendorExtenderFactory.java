package androidx.camera.extensions;

import androidx.camera.extensions.internal.VendorExtender;

interface VendorExtenderFactory {
    VendorExtender createVendorExtender(int i);
}
