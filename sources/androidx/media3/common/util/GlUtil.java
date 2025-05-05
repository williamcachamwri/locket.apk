package androidx.media3.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.opengl.Matrix;
import androidx.work.Data;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.List;

public final class GlUtil {
    public static final int[] EGL_CONFIG_ATTRIBUTES_RGBA_1010102 = {12352, 4, 12324, 10, 12323, 10, 12322, 10, 12321, 2, 12325, 0, 12326, 0, 12344};
    public static final int[] EGL_CONFIG_ATTRIBUTES_RGBA_8888 = {12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12326, 0, 12344};
    private static final int EGL_GL_COLORSPACE_BT2020_HLG_EXT = 13632;
    private static final int EGL_GL_COLORSPACE_BT2020_PQ_EXT = 13120;
    private static final int EGL_GL_COLORSPACE_KHR = 12445;
    private static final int[] EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_HLG = {12445, 13632, 12344, 12344};
    private static final int[] EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_PQ = {12445, EGL_GL_COLORSPACE_BT2020_PQ_EXT, 12344, 12344};
    private static final int[] EGL_WINDOW_SURFACE_ATTRIBUTES_NONE = {12344};
    private static final String EXTENSION_COLORSPACE_BT2020_HLG = "EGL_EXT_gl_colorspace_bt2020_hlg";
    private static final String EXTENSION_COLORSPACE_BT2020_PQ = "EGL_EXT_gl_colorspace_bt2020_pq";
    private static final String EXTENSION_PROTECTED_CONTENT = "EGL_EXT_protected_content";
    private static final String EXTENSION_SURFACELESS_CONTEXT = "EGL_KHR_surfaceless_context";
    private static final String EXTENSION_YUV_TARGET = "GL_EXT_YUV_target";
    private static final long GL_FENCE_SYNC_FAILED = 0;
    public static final int HOMOGENEOUS_COORDINATE_VECTOR_SIZE = 4;
    public static final float LENGTH_NDC = 2.0f;

    public static final class GlException extends Exception {
        public GlException(String str) {
            super(str);
        }
    }

    private GlUtil() {
    }

    public static float[] getNormalizedCoordinateBounds() {
        return new float[]{-1.0f, -1.0f, 0.0f, 1.0f, 1.0f, -1.0f, 0.0f, 1.0f, -1.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f};
    }

    public static float[] getTextureCoordinateBounds() {
        return new float[]{0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f, 1.0f};
    }

    public static float[] create4x4IdentityMatrix() {
        float[] fArr = new float[16];
        setToIdentity(fArr);
        return fArr;
    }

    public static void setToIdentity(float[] fArr) {
        Matrix.setIdentityM(fArr, 0);
    }

    public static float[] createVertexBuffer(List<float[]> list) {
        float[] fArr = new float[(list.size() * 4)];
        for (int i = 0; i < list.size(); i++) {
            System.arraycopy(list.get(i), 0, fArr, i * 4, 4);
        }
        return fArr;
    }

    public static boolean isProtectedContentExtensionSupported(Context context) {
        if (Util.SDK_INT < 24) {
            return false;
        }
        if (Util.SDK_INT < 26 && ("samsung".equals(Util.MANUFACTURER) || "XT1650".equals(Util.MODEL))) {
            return false;
        }
        if (Util.SDK_INT >= 26 || context.getPackageManager().hasSystemFeature("android.hardware.vr.high_performance")) {
            return isExtensionSupported(EXTENSION_PROTECTED_CONTENT);
        }
        return false;
    }

    public static boolean isSurfacelessContextExtensionSupported() {
        return isExtensionSupported(EXTENSION_SURFACELESS_CONTEXT);
    }

    public static boolean isYuvTargetExtensionSupported() {
        String str;
        if (Util.areEqual(EGL14.eglGetCurrentContext(), EGL14.EGL_NO_CONTEXT)) {
            try {
                EGLDisplay defaultEglDisplay = getDefaultEglDisplay();
                EGLContext createEglContext = createEglContext(defaultEglDisplay);
                createFocusedPlaceholderEglSurface(createEglContext, defaultEglDisplay);
                str = GLES20.glGetString(7939);
                destroyEglContext(defaultEglDisplay, createEglContext);
            } catch (GlException unused) {
                return false;
            }
        } else {
            str = GLES20.glGetString(7939);
        }
        if (str == null || !str.contains(EXTENSION_YUV_TARGET)) {
            return false;
        }
        return true;
    }

    public static boolean isBt2020PqExtensionSupported() {
        return Util.SDK_INT >= 33 && isExtensionSupported(EXTENSION_COLORSPACE_BT2020_PQ);
    }

    public static boolean isBt2020HlgExtensionSupported() {
        return isExtensionSupported(EXTENSION_COLORSPACE_BT2020_HLG);
    }

    public static EGLDisplay getDefaultEglDisplay() throws GlException {
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        checkGlException(!eglGetDisplay.equals(EGL14.EGL_NO_DISPLAY), "No EGL display.");
        checkGlException(EGL14.eglInitialize(eglGetDisplay, new int[1], 0, new int[1], 0), "Error in eglInitialize.");
        checkGlError();
        return eglGetDisplay;
    }

    public static EGLContext createEglContext(EGLDisplay eGLDisplay) throws GlException {
        return createEglContext(EGL14.EGL_NO_CONTEXT, eGLDisplay, 2, EGL_CONFIG_ATTRIBUTES_RGBA_8888);
    }

    public static EGLContext createEglContext(EGLContext eGLContext, EGLDisplay eGLDisplay, int i, int[] iArr) throws GlException {
        boolean z = true;
        Assertions.checkArgument(Arrays.equals(iArr, EGL_CONFIG_ATTRIBUTES_RGBA_8888) || Arrays.equals(iArr, EGL_CONFIG_ATTRIBUTES_RGBA_1010102));
        if (!(i == 2 || i == 3)) {
            z = false;
        }
        Assertions.checkArgument(z);
        EGLContext eglCreateContext = EGL14.eglCreateContext(eGLDisplay, getEglConfig(eGLDisplay, iArr), eGLContext, new int[]{12440, i, 12344}, 0);
        if (eglCreateContext == null || eglCreateContext.equals(EGL14.EGL_NO_CONTEXT)) {
            EGL14.eglTerminate(eGLDisplay);
            throw new GlException("eglCreateContext() failed to create a valid context. The device may not support EGL version " + i);
        }
        checkGlError();
        return eglCreateContext;
    }

    public static EGLSurface createEglSurface(EGLDisplay eGLDisplay, Object obj, int i, boolean z) throws GlException {
        int[] iArr;
        int[] iArr2;
        if (i == 3 || i == 10) {
            iArr2 = EGL_CONFIG_ATTRIBUTES_RGBA_8888;
            iArr = EGL_WINDOW_SURFACE_ATTRIBUTES_NONE;
        } else if (i == 7 || i == 6) {
            iArr2 = EGL_CONFIG_ATTRIBUTES_RGBA_1010102;
            if (z) {
                iArr = EGL_WINDOW_SURFACE_ATTRIBUTES_NONE;
            } else if (i == 6) {
                if (isBt2020PqExtensionSupported()) {
                    iArr = EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_PQ;
                } else {
                    throw new GlException("BT.2020 PQ OpenGL output isn't supported.");
                }
            } else if (isBt2020HlgExtensionSupported()) {
                iArr = EGL_WINDOW_SURFACE_ATTRIBUTES_BT2020_HLG;
            } else {
                throw new GlException("BT.2020 HLG OpenGL output isn't supported.");
            }
        } else {
            throw new IllegalArgumentException("Unsupported color transfer: " + i);
        }
        EGLSurface eglCreateWindowSurface = EGL14.eglCreateWindowSurface(eGLDisplay, getEglConfig(eGLDisplay, iArr2), obj, iArr, 0);
        checkEglException("Error creating a new EGL surface");
        return eglCreateWindowSurface;
    }

    private static EGLSurface createPbufferSurface(EGLDisplay eGLDisplay, int i, int i2, int[] iArr) throws GlException {
        EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eGLDisplay, getEglConfig(eGLDisplay, iArr), new int[]{12375, i, 12374, i2, 12344}, 0);
        checkEglException("Error creating a new EGL Pbuffer surface");
        return eglCreatePbufferSurface;
    }

    public static EGLSurface createFocusedPlaceholderEglSurface(EGLContext eGLContext, EGLDisplay eGLDisplay) throws GlException {
        EGLSurface eGLSurface;
        int[] iArr = EGL_CONFIG_ATTRIBUTES_RGBA_8888;
        if (isSurfacelessContextExtensionSupported()) {
            eGLSurface = EGL14.EGL_NO_SURFACE;
        } else {
            eGLSurface = createPbufferSurface(eGLDisplay, 1, 1, iArr);
        }
        focusEglSurface(eGLDisplay, eGLContext, eGLSurface, 1, 1);
        return eGLSurface;
    }

    public static long getContextMajorVersion() throws GlException {
        int[] iArr = new int[1];
        EGL14.eglQueryContext(EGL14.eglGetDisplay(0), EGL14.eglGetCurrentContext(), 12440, iArr, 0);
        checkGlError();
        return (long) iArr[0];
    }

    public static long createGlSyncFence() throws GlException {
        if (getContextMajorVersion() < 3) {
            return 0;
        }
        long glFenceSync = GLES30.glFenceSync(37143, 0);
        checkGlError();
        GLES20.glFlush();
        checkGlError();
        return glFenceSync;
    }

    public static void deleteSyncObject(long j) throws GlException {
        deleteSyncObjectQuietly(j);
        checkGlError();
    }

    public static void deleteSyncObjectQuietly(long j) {
        GLES30.glDeleteSync(j);
    }

    public static void awaitSyncObject(long j) throws GlException {
        if (j == 0) {
            GLES20.glFinish();
            return;
        }
        GLES30.glWaitSync(j, 0, -1);
        checkGlError();
    }

    public static EGLContext getCurrentContext() {
        return EGL14.eglGetCurrentContext();
    }

    public static void checkGlError() throws GlException {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        while (true) {
            int glGetError = GLES20.glGetError();
            if (glGetError == 0) {
                break;
            }
            if (z) {
                sb.append(10);
            }
            String gluErrorString = GLU.gluErrorString(glGetError);
            if (gluErrorString == null) {
                gluErrorString = "error code: 0x" + Integer.toHexString(glGetError);
            }
            sb.append("glError: ").append(gluErrorString);
            z = true;
        }
        if (z) {
            throw new GlException(sb.toString());
        }
    }

    private static void assertValidTextureSize(int i, int i2) throws GlException {
        boolean z = true;
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(3379, iArr, 0);
        int i3 = iArr[0];
        if (i3 <= 0) {
            z = false;
        }
        Assertions.checkState(z, "Create a OpenGL context first or run the GL methods on an OpenGL thread.");
        if (i < 0 || i2 < 0) {
            throw new GlException("width or height is less than 0");
        } else if (i > i3 || i2 > i3) {
            throw new GlException("width or height is greater than GL_MAX_TEXTURE_SIZE " + i3);
        }
    }

    public static void clearFocusedBuffers() throws GlException {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        GLES20.glClearDepthf(1.0f);
        GLES20.glClear(16640);
        checkGlError();
    }

    public static void focusEglSurface(EGLDisplay eGLDisplay, EGLContext eGLContext, EGLSurface eGLSurface, int i, int i2) throws GlException {
        focusRenderTarget(eGLDisplay, eGLContext, eGLSurface, 0, i, i2);
    }

    public static void focusFramebuffer(EGLDisplay eGLDisplay, EGLContext eGLContext, EGLSurface eGLSurface, int i, int i2, int i3) throws GlException {
        focusRenderTarget(eGLDisplay, eGLContext, eGLSurface, i, i2, i3);
    }

    public static void focusFramebufferUsingCurrentContext(int i, int i2, int i3) throws GlException {
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(36006, iArr, 0);
        if (iArr[0] != i) {
            GLES20.glBindFramebuffer(36160, i);
        }
        checkGlError();
        GLES20.glViewport(0, 0, i2, i3);
        checkGlError();
    }

    public static FloatBuffer createBuffer(float[] fArr) {
        return (FloatBuffer) createBuffer(fArr.length).put(fArr).flip();
    }

    private static FloatBuffer createBuffer(int i) {
        return ByteBuffer.allocateDirect(i * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    }

    public static int createExternalTexture() throws GlException {
        int generateTexture = generateTexture();
        bindTexture(36197, generateTexture, 9729);
        return generateTexture;
    }

    public static int createTexture(Bitmap bitmap) throws GlException {
        int generateTexture = generateTexture();
        setTexture(generateTexture, bitmap);
        return generateTexture;
    }

    public static int createTexture(int i, int i2, boolean z) throws GlException {
        if (z) {
            return createTextureUninitialized(i, i2, 34842, 5131);
        }
        return createTextureUninitialized(i, i2, 6408, 5121);
    }

    private static int createTextureUninitialized(int i, int i2, int i3, int i4) throws GlException {
        assertValidTextureSize(i, i2);
        int generateTexture = generateTexture();
        bindTexture(3553, generateTexture, 9729);
        GLES20.glTexImage2D(3553, 0, i3, i, i2, 0, 6408, i4, (Buffer) null);
        checkGlError();
        return generateTexture;
    }

    public static int generateTexture() throws GlException {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        checkGlError();
        return iArr[0];
    }

    public static void setTexture(int i, Bitmap bitmap) throws GlException {
        assertValidTextureSize(bitmap.getWidth(), bitmap.getHeight());
        bindTexture(3553, i, 9729);
        GLUtils.texImage2D(3553, 0, bitmap, 0);
        checkGlError();
    }

    public static void bindTexture(int i, int i2, int i3) throws GlException {
        GLES20.glBindTexture(i, i2);
        checkGlError();
        GLES20.glTexParameteri(i, Data.MAX_DATA_BYTES, i3);
        checkGlError();
        GLES20.glTexParameteri(i, 10241, i3);
        checkGlError();
        GLES20.glTexParameteri(i, 10242, 33071);
        checkGlError();
        GLES20.glTexParameteri(i, 10243, 33071);
        checkGlError();
    }

    public static int createFboForTexture(int i) throws GlException {
        int[] iArr = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        checkGlError();
        GLES20.glBindFramebuffer(36160, iArr[0]);
        checkGlError();
        GLES20.glFramebufferTexture2D(36160, 36064, 3553, i, 0);
        checkGlError();
        return iArr[0];
    }

    public static void deleteTexture(int i) throws GlException {
        GLES20.glDeleteTextures(1, new int[]{i}, 0);
        checkGlError();
    }

    public static void destroyEglContext(EGLDisplay eGLDisplay, EGLContext eGLContext) throws GlException {
        if (eGLDisplay != null && !eGLDisplay.equals(EGL14.EGL_NO_DISPLAY)) {
            EGL14.eglMakeCurrent(eGLDisplay, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_SURFACE, EGL14.EGL_NO_CONTEXT);
            checkEglException("Error releasing context");
            if (eGLContext != null && !eGLContext.equals(EGL14.EGL_NO_CONTEXT)) {
                EGL14.eglDestroyContext(eGLDisplay, eGLContext);
                checkEglException("Error destroying context");
            }
            EGL14.eglReleaseThread();
            checkEglException("Error releasing thread");
            EGL14.eglTerminate(eGLDisplay);
            checkEglException("Error terminating display");
        }
    }

    public static void destroyEglSurface(EGLDisplay eGLDisplay, EGLSurface eGLSurface) throws GlException {
        if (eGLDisplay != null && !eGLDisplay.equals(EGL14.EGL_NO_DISPLAY) && eGLSurface != null && !eGLSurface.equals(EGL14.EGL_NO_SURFACE)) {
            EGL14.eglDestroySurface(eGLDisplay, eGLSurface);
            checkEglException("Error destroying surface");
        }
    }

    public static void deleteFbo(int i) throws GlException {
        GLES20.glDeleteFramebuffers(1, new int[]{i}, 0);
        checkGlError();
    }

    public static void deleteRbo(int i) throws GlException {
        GLES20.glDeleteRenderbuffers(1, new int[]{i}, 0);
        checkGlError();
    }

    public static void blitFrameBuffer(int i, GlRect glRect, int i2, GlRect glRect2) throws GlException {
        GlRect glRect3 = glRect;
        GlRect glRect4 = glRect2;
        int[] iArr = new int[1];
        GLES20.glGetIntegerv(36006, iArr, 0);
        checkGlError();
        int i3 = i;
        GLES30.glBindFramebuffer(36008, i);
        checkGlError();
        GLES30.glBindFramebuffer(36009, i2);
        checkGlError();
        GLES30.glBlitFramebuffer(glRect3.left, glRect3.bottom, glRect3.right, glRect3.top, glRect4.left, glRect4.bottom, glRect4.right, glRect4.top, 16384, 9729);
        checkGlError();
        GLES20.glBindFramebuffer(36160, iArr[0]);
        checkGlError();
    }

    public static int createPixelBufferObject(int i) throws GlException {
        int[] iArr = new int[1];
        GLES30.glGenBuffers(1, iArr, 0);
        checkGlError();
        GLES30.glBindBuffer(35051, iArr[0]);
        checkGlError();
        GLES30.glBufferData(35051, i, (Buffer) null, 35049);
        checkGlError();
        GLES30.glBindBuffer(35051, 0);
        checkGlError();
        return iArr[0];
    }

    public static void schedulePixelBufferRead(int i, int i2, int i3, int i4) throws GlException {
        focusFramebufferUsingCurrentContext(i, i2, i3);
        GLES30.glBindBuffer(35051, i4);
        checkGlError();
        GLES30.glReadBuffer(36064);
        GLES30.glReadPixels(0, 0, i2, i3, 6408, 5121, 0);
        checkGlError();
        GLES30.glBindBuffer(35051, 0);
        checkGlError();
    }

    public static ByteBuffer mapPixelBufferObject(int i, int i2) throws GlException {
        GLES20.glBindBuffer(35051, i);
        checkGlError();
        ByteBuffer byteBuffer = (ByteBuffer) GLES30.glMapBufferRange(35051, 0, i2, 1);
        checkGlError();
        GLES30.glBindBuffer(35051, 0);
        checkGlError();
        return byteBuffer;
    }

    public static void unmapPixelBufferObject(int i) throws GlException {
        GLES30.glBindBuffer(35051, i);
        checkGlError();
        GLES30.glUnmapBuffer(35051);
        checkGlError();
        GLES30.glBindBuffer(35051, 0);
        checkGlError();
    }

    public static void deleteBuffer(int i) throws GlException {
        GLES20.glDeleteBuffers(1, new int[]{i}, 0);
        checkGlError();
    }

    public static void checkGlException(boolean z, String str) throws GlException {
        if (!z) {
            throw new GlException(str);
        }
    }

    private static EGLConfig getEglConfig(EGLDisplay eGLDisplay, int[] iArr) throws GlException {
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        if (EGL14.eglChooseConfig(eGLDisplay, iArr, 0, eGLConfigArr, 0, 1, new int[1], 0)) {
            return eGLConfigArr[0];
        }
        throw new GlException("eglChooseConfig failed.");
    }

    private static boolean isExtensionSupported(String str) {
        String eglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373);
        if (eglQueryString == null || !eglQueryString.contains(str)) {
            return false;
        }
        return true;
    }

    private static void focusRenderTarget(EGLDisplay eGLDisplay, EGLContext eGLContext, EGLSurface eGLSurface, int i, int i2, int i3) throws GlException {
        EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, eGLContext);
        checkEglException("Error making context current");
        focusFramebufferUsingCurrentContext(i, i2, i3);
    }

    private static void checkEglException(String str) throws GlException {
        int eglGetError = EGL14.eglGetError();
        if (eglGetError != 12288) {
            throw new GlException(str + ", error code: 0x" + Integer.toHexString(eglGetError));
        }
    }
}
