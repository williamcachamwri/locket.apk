package androidx.camera.core.processing.util;

import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.processing.ShaderProvider;
import androidx.core.util.Preconditions;
import androidx.work.Data;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class GLUtils {
    public static final String BLANK_FRAGMENT_SHADER = "precision mediump float;\nuniform float uAlphaScale;\nvoid main() {\n    gl_FragColor = vec4(0.0, 0.0, 0.0, uAlphaScale);\n}\n";
    public static final String BLANK_VERTEX_SHADER = "uniform mat4 uTransMatrix;\nattribute vec4 aPosition;\nvoid main() {\n    gl_Position = uTransMatrix * aPosition;\n}\n";
    public static final String DEFAULT_VERTEX_SHADER = String.format(Locale.US, "uniform mat4 uTexMatrix;\nuniform mat4 uTransMatrix;\nattribute vec4 aPosition;\nattribute vec4 aTextureCoord;\nvarying vec2 %s;\nvoid main() {\n    gl_Position = uTransMatrix * aPosition;\n    %s = (uTexMatrix * aTextureCoord).xy;\n}\n", new Object[]{VAR_TEXTURE_COORD, VAR_TEXTURE_COORD});
    public static final int EGL_GL_COLORSPACE_BT2020_HLG_EXT = 13632;
    public static final int EGL_GL_COLORSPACE_KHR = 12445;
    public static final int[] EMPTY_ATTRIBS = {12344};
    public static final String HDR_VERTEX_SHADER = String.format(Locale.US, "#version 300 es\nin vec4 aPosition;\nin vec4 aTextureCoord;\nuniform mat4 uTexMatrix;\nuniform mat4 uTransMatrix;\nout vec2 %s;\nvoid main() {\n  gl_Position = uTransMatrix * aPosition;\n  %s = (uTexMatrix * aTextureCoord).xy;\n}\n", new Object[]{VAR_TEXTURE_COORD, VAR_TEXTURE_COORD});
    public static final int[] HLG_SURFACE_ATTRIBS = {EGL_GL_COLORSPACE_KHR, EGL_GL_COLORSPACE_BT2020_HLG_EXT, 12344};
    public static final OutputSurface NO_OUTPUT_SURFACE = OutputSurface.of(EGL14.EGL_NO_SURFACE, 0, 0);
    public static final int PIXEL_STRIDE = 4;
    /* access modifiers changed from: private */
    public static final ShaderProvider SHADER_PROVIDER_DEFAULT = new ShaderProvider() {
        public String createFragmentShader(String str, String str2) {
            return String.format(Locale.US, "#extension GL_OES_EGL_image_external : require\nprecision mediump float;\nvarying vec2 %s;\nuniform samplerExternalOES %s;\nuniform float uAlphaScale;\nvoid main() {\n    vec4 src = texture2D(%s, %s);\n    gl_FragColor = vec4(src.rgb, src.a * uAlphaScale);\n}\n", new Object[]{str2, str, str, str2});
        }
    };
    /* access modifiers changed from: private */
    public static final ShaderProvider SHADER_PROVIDER_HDR_DEFAULT = new ShaderProvider() {
        public String createFragmentShader(String str, String str2) {
            return String.format(Locale.US, "#version 300 es\n#extension GL_OES_EGL_image_external_essl3 : require\nprecision mediump float;\nuniform samplerExternalOES %s;\nuniform float uAlphaScale;\nin vec2 %s;\nout vec4 outColor;\n\nvoid main() {\n  vec4 src = texture(%s, %s);\n  outColor = vec4(src.rgb, src.a * uAlphaScale);\n}", new Object[]{str, str2, str, str2});
        }
    };
    /* access modifiers changed from: private */
    public static final ShaderProvider SHADER_PROVIDER_HDR_YUV = new ShaderProvider() {
        public String createFragmentShader(String str, String str2) {
            return String.format(Locale.US, "#version 300 es\n#extension GL_EXT_YUV_target : require\nprecision mediump float;\nuniform __samplerExternal2DY2YEXT %s;\nuniform float uAlphaScale;\nin vec2 %s;\nout vec4 outColor;\n\nvec3 yuvToRgb(vec3 yuv) {\n  const vec3 yuvOffset = vec3(0.0625, 0.5, 0.5);\n  const mat3 yuvToRgbColorMat = mat3(\n    1.1689f, 1.1689f, 1.1689f,\n    0.0000f, -0.1881f, 2.1502f,\n    1.6853f, -0.6530f, 0.0000f\n  );\n  return clamp(yuvToRgbColorMat * (yuv - yuvOffset), 0.0, 1.0);\n}\n\nvoid main() {\n  vec3 srcYuv = texture(%s, %s).xyz;\n  vec3 srcRgb = yuvToRgb(srcYuv);\n  outColor = vec4(srcRgb, uAlphaScale);\n}", new Object[]{str, str2, str, str2});
        }
    };
    public static final int SIZEOF_FLOAT = 4;
    public static final String TAG = "GLUtils";
    public static final FloatBuffer TEX_BUF;
    public static final float[] TEX_COORDS;
    public static final String VAR_TEXTURE = "sTexture";
    public static final String VAR_TEXTURE_COORD = "vTextureCoord";
    public static final String VERSION_UNKNOWN = "0.0";
    public static final FloatBuffer VERTEX_BUF;
    public static final float[] VERTEX_COORDS;

    public enum InputFormat {
        UNKNOWN,
        DEFAULT,
        YUV
    }

    static {
        float[] fArr = {-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
        VERTEX_COORDS = fArr;
        VERTEX_BUF = createFloatBuffer(fArr);
        float[] fArr2 = {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f};
        TEX_COORDS = fArr2;
        TEX_BUF = createFloatBuffer(fArr2);
    }

    private GLUtils() {
    }

    public static abstract class Program2D {
        protected int mAlphaScaleLoc = -1;
        protected int mPositionLoc = -1;
        protected int mProgramHandle;
        protected int mTransMatrixLoc = -1;

        /* JADX WARNING: Removed duplicated region for block: B:27:0x0075  */
        /* JADX WARNING: Removed duplicated region for block: B:29:0x007a  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x007f  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected Program2D(java.lang.String r8, java.lang.String r9) {
            /*
                r7 = this;
                java.lang.String r0 = "glAttachShader"
                java.lang.String r1 = "Could not link program: "
                r7.<init>()
                r2 = -1
                r7.mTransMatrixLoc = r2
                r7.mAlphaScaleLoc = r2
                r7.mPositionLoc = r2
                r3 = 35633(0x8b31, float:4.9932E-41)
                int r8 = androidx.camera.core.processing.util.GLUtils.loadShader(r3, r8)     // Catch:{ IllegalStateException -> 0x006f, IllegalArgumentException -> 0x006d }
                r3 = 35632(0x8b30, float:4.9931E-41)
                int r9 = androidx.camera.core.processing.util.GLUtils.loadShader(r3, r9)     // Catch:{ IllegalStateException -> 0x006a, IllegalArgumentException -> 0x0068 }
                int r3 = android.opengl.GLES20.glCreateProgram()     // Catch:{ IllegalStateException -> 0x0065, IllegalArgumentException -> 0x0063 }
                java.lang.String r4 = "glCreateProgram"
                androidx.camera.core.processing.util.GLUtils.checkGlErrorOrThrow(r4)     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
                android.opengl.GLES20.glAttachShader(r3, r8)     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
                androidx.camera.core.processing.util.GLUtils.checkGlErrorOrThrow(r0)     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
                android.opengl.GLES20.glAttachShader(r3, r9)     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
                androidx.camera.core.processing.util.GLUtils.checkGlErrorOrThrow(r0)     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
                android.opengl.GLES20.glLinkProgram(r3)     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
                r0 = 1
                int[] r4 = new int[r0]     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
                r5 = 35714(0x8b82, float:5.0046E-41)
                r6 = 0
                android.opengl.GLES20.glGetProgramiv(r3, r5, r4, r6)     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
                r4 = r4[r6]     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
                if (r4 != r0) goto L_0x0048
                r7.mProgramHandle = r3     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
                r7.loadLocations()
                return
            L_0x0048:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
                r4.<init>(r1)     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
                java.lang.String r1 = android.opengl.GLES20.glGetProgramInfoLog(r3)     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
                java.lang.StringBuilder r1 = r4.append(r1)     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
                java.lang.String r1 = r1.toString()     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
                r0.<init>(r1)     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
                throw r0     // Catch:{ IllegalStateException -> 0x0061, IllegalArgumentException -> 0x005f }
            L_0x005f:
                r0 = move-exception
                goto L_0x0073
            L_0x0061:
                r0 = move-exception
                goto L_0x0073
            L_0x0063:
                r0 = move-exception
                goto L_0x0066
            L_0x0065:
                r0 = move-exception
            L_0x0066:
                r3 = r2
                goto L_0x0073
            L_0x0068:
                r0 = move-exception
                goto L_0x006b
            L_0x006a:
                r0 = move-exception
            L_0x006b:
                r9 = r2
                goto L_0x0072
            L_0x006d:
                r0 = move-exception
                goto L_0x0070
            L_0x006f:
                r0 = move-exception
            L_0x0070:
                r8 = r2
                r9 = r8
            L_0x0072:
                r3 = r9
            L_0x0073:
                if (r8 == r2) goto L_0x0078
                android.opengl.GLES20.glDeleteShader(r8)
            L_0x0078:
                if (r9 == r2) goto L_0x007d
                android.opengl.GLES20.glDeleteShader(r9)
            L_0x007d:
                if (r3 == r2) goto L_0x0082
                android.opengl.GLES20.glDeleteProgram(r3)
            L_0x0082:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.processing.util.GLUtils.Program2D.<init>(java.lang.String, java.lang.String):void");
        }

        public void use() {
            GLES20.glUseProgram(this.mProgramHandle);
            GLUtils.checkGlErrorOrThrow("glUseProgram");
            GLES20.glEnableVertexAttribArray(this.mPositionLoc);
            GLUtils.checkGlErrorOrThrow("glEnableVertexAttribArray");
            GLES20.glVertexAttribPointer(this.mPositionLoc, 2, 5126, false, 0, GLUtils.VERTEX_BUF);
            GLUtils.checkGlErrorOrThrow("glVertexAttribPointer");
            updateTransformMatrix(GLUtils.create4x4IdentityMatrix());
            updateAlpha(1.0f);
        }

        public void updateTransformMatrix(float[] fArr) {
            GLES20.glUniformMatrix4fv(this.mTransMatrixLoc, 1, false, fArr, 0);
            GLUtils.checkGlErrorOrThrow("glUniformMatrix4fv");
        }

        public void updateAlpha(float f) {
            GLES20.glUniform1f(this.mAlphaScaleLoc, f);
            GLUtils.checkGlErrorOrThrow("glUniform1f");
        }

        public void delete() {
            GLES20.glDeleteProgram(this.mProgramHandle);
        }

        /* access modifiers changed from: private */
        public void loadLocations() {
            int glGetAttribLocation = GLES20.glGetAttribLocation(this.mProgramHandle, "aPosition");
            this.mPositionLoc = glGetAttribLocation;
            GLUtils.checkLocationOrThrow(glGetAttribLocation, "aPosition");
            int glGetUniformLocation = GLES20.glGetUniformLocation(this.mProgramHandle, "uTransMatrix");
            this.mTransMatrixLoc = glGetUniformLocation;
            GLUtils.checkLocationOrThrow(glGetUniformLocation, "uTransMatrix");
            int glGetUniformLocation2 = GLES20.glGetUniformLocation(this.mProgramHandle, "uAlphaScale");
            this.mAlphaScaleLoc = glGetUniformLocation2;
            GLUtils.checkLocationOrThrow(glGetUniformLocation2, "uAlphaScale");
        }
    }

    public static class SamplerShaderProgram extends Program2D {
        private int mSamplerLoc;
        private int mTexCoordLoc;
        private int mTexMatrixLoc;

        public SamplerShaderProgram(DynamicRange dynamicRange, InputFormat inputFormat) {
            this(dynamicRange, resolveDefaultShaderProvider(dynamicRange, inputFormat));
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public SamplerShaderProgram(DynamicRange dynamicRange, ShaderProvider shaderProvider) {
            super(dynamicRange.is10BitHdr() ? GLUtils.HDR_VERTEX_SHADER : GLUtils.DEFAULT_VERTEX_SHADER, GLUtils.getFragmentShaderSource(shaderProvider));
            this.mSamplerLoc = -1;
            this.mTexMatrixLoc = -1;
            this.mTexCoordLoc = -1;
            loadLocations();
        }

        public void use() {
            super.use();
            GLES20.glUniform1i(this.mSamplerLoc, 0);
            GLES20.glEnableVertexAttribArray(this.mTexCoordLoc);
            GLUtils.checkGlErrorOrThrow("glEnableVertexAttribArray");
            GLES20.glVertexAttribPointer(this.mTexCoordLoc, 2, 5126, false, 0, GLUtils.TEX_BUF);
            GLUtils.checkGlErrorOrThrow("glVertexAttribPointer");
        }

        public void updateTextureMatrix(float[] fArr) {
            GLES20.glUniformMatrix4fv(this.mTexMatrixLoc, 1, false, fArr, 0);
            GLUtils.checkGlErrorOrThrow("glUniformMatrix4fv");
        }

        private void loadLocations() {
            loadLocations();
            int glGetUniformLocation = GLES20.glGetUniformLocation(this.mProgramHandle, GLUtils.VAR_TEXTURE);
            this.mSamplerLoc = glGetUniformLocation;
            GLUtils.checkLocationOrThrow(glGetUniformLocation, GLUtils.VAR_TEXTURE);
            int glGetAttribLocation = GLES20.glGetAttribLocation(this.mProgramHandle, "aTextureCoord");
            this.mTexCoordLoc = glGetAttribLocation;
            GLUtils.checkLocationOrThrow(glGetAttribLocation, "aTextureCoord");
            int glGetUniformLocation2 = GLES20.glGetUniformLocation(this.mProgramHandle, "uTexMatrix");
            this.mTexMatrixLoc = glGetUniformLocation2;
            GLUtils.checkLocationOrThrow(glGetUniformLocation2, "uTexMatrix");
        }

        private static ShaderProvider resolveDefaultShaderProvider(DynamicRange dynamicRange, InputFormat inputFormat) {
            if (!dynamicRange.is10BitHdr()) {
                return GLUtils.SHADER_PROVIDER_DEFAULT;
            }
            Preconditions.checkArgument(inputFormat != InputFormat.UNKNOWN, "No default sampler shader available for" + inputFormat);
            if (inputFormat == InputFormat.YUV) {
                return GLUtils.SHADER_PROVIDER_HDR_YUV;
            }
            return GLUtils.SHADER_PROVIDER_HDR_DEFAULT;
        }
    }

    public static class BlankShaderProgram extends Program2D {
        public BlankShaderProgram() {
            super(GLUtils.BLANK_VERTEX_SHADER, GLUtils.BLANK_FRAGMENT_SHADER);
        }
    }

    public static EGLSurface createWindowSurface(EGLDisplay eGLDisplay, EGLConfig eGLConfig, Surface surface, int[] iArr) {
        EGLSurface eglCreateWindowSurface = EGL14.eglCreateWindowSurface(eGLDisplay, eGLConfig, surface, iArr, 0);
        checkEglErrorOrThrow("eglCreateWindowSurface");
        if (eglCreateWindowSurface != null) {
            return eglCreateWindowSurface;
        }
        throw new IllegalStateException("surface was null");
    }

    public static int loadShader(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        checkGlErrorOrThrow("glCreateShader type=" + i);
        GLES20.glShaderSource(glCreateShader, str);
        GLES20.glCompileShader(glCreateShader);
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        Logger.w(TAG, "Could not compile shader: " + str);
        String glGetShaderInfoLog = GLES20.glGetShaderInfoLog(glCreateShader);
        GLES20.glDeleteShader(glCreateShader);
        throw new IllegalStateException("Could not compile shader type " + i + ":" + glGetShaderInfoLog);
    }

    public static int querySurface(EGLDisplay eGLDisplay, EGLSurface eGLSurface, int i) {
        int[] iArr = new int[1];
        EGL14.eglQuerySurface(eGLDisplay, eGLSurface, i, iArr, 0);
        return iArr[0];
    }

    public static Size getSurfaceSize(EGLDisplay eGLDisplay, EGLSurface eGLSurface) {
        return new Size(querySurface(eGLDisplay, eGLSurface, 12375), querySurface(eGLDisplay, eGLSurface, 12374));
    }

    public static FloatBuffer createFloatBuffer(float[] fArr) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(fArr.length * 4);
        allocateDirect.order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = allocateDirect.asFloatBuffer();
        asFloatBuffer.put(fArr);
        asFloatBuffer.position(0);
        return asFloatBuffer;
    }

    public static EGLSurface createPBufferSurface(EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i, int i2) {
        EGLSurface eglCreatePbufferSurface = EGL14.eglCreatePbufferSurface(eGLDisplay, eGLConfig, new int[]{12375, i, 12374, i2, 12344}, 0);
        checkEglErrorOrThrow("eglCreatePbufferSurface");
        if (eglCreatePbufferSurface != null) {
            return eglCreatePbufferSurface;
        }
        throw new IllegalStateException("surface was null");
    }

    public static Map<InputFormat, Program2D> createPrograms(DynamicRange dynamicRange, Map<InputFormat, ShaderProvider> map) {
        Object obj;
        HashMap hashMap = new HashMap();
        InputFormat[] values = InputFormat.values();
        int length = values.length;
        for (int i = 0; i < length; i++) {
            InputFormat inputFormat = values[i];
            ShaderProvider shaderProvider = map.get(inputFormat);
            if (shaderProvider != null) {
                obj = new SamplerShaderProgram(dynamicRange, shaderProvider);
            } else if (inputFormat == InputFormat.YUV || inputFormat == InputFormat.DEFAULT) {
                obj = new SamplerShaderProgram(dynamicRange, inputFormat);
            } else {
                Preconditions.checkState(inputFormat == InputFormat.UNKNOWN, "Unhandled input format: " + inputFormat);
                if (dynamicRange.is10BitHdr()) {
                    obj = new BlankShaderProgram();
                } else {
                    ShaderProvider shaderProvider2 = map.get(InputFormat.DEFAULT);
                    if (shaderProvider2 != null) {
                        obj = new SamplerShaderProgram(dynamicRange, shaderProvider2);
                    } else {
                        obj = new SamplerShaderProgram(dynamicRange, InputFormat.DEFAULT);
                    }
                }
            }
            Log.d(TAG, "Shader program for input format " + inputFormat + " created: " + obj);
            hashMap.put(inputFormat, obj);
        }
        return hashMap;
    }

    public static int createTexture() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        checkGlErrorOrThrow("glGenTextures");
        int i = iArr[0];
        GLES20.glBindTexture(36197, i);
        checkGlErrorOrThrow("glBindTexture " + i);
        GLES20.glTexParameteri(36197, 10241, 9728);
        GLES20.glTexParameteri(36197, Data.MAX_DATA_BYTES, 9729);
        GLES20.glTexParameteri(36197, 10242, 33071);
        GLES20.glTexParameteri(36197, 10243, 33071);
        checkGlErrorOrThrow("glTexParameter");
        return i;
    }

    public static float[] create4x4IdentityMatrix() {
        float[] fArr = new float[16];
        Matrix.setIdentityM(fArr, 0);
        return fArr;
    }

    public static void checkLocationOrThrow(int i, String str) {
        if (i < 0) {
            throw new IllegalStateException("Unable to locate '" + str + "' in program");
        }
    }

    public static void checkEglErrorOrThrow(String str) {
        int eglGetError = EGL14.eglGetError();
        if (eglGetError != 12288) {
            throw new IllegalStateException(str + ": EGL error: 0x" + Integer.toHexString(eglGetError));
        }
    }

    public static void checkGlErrorOrThrow(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            throw new IllegalStateException(str + ": GL error 0x" + Integer.toHexString(glGetError));
        }
    }

    public static void checkEglErrorOrLog(String str) {
        try {
            checkEglErrorOrThrow(str);
        } catch (IllegalStateException e) {
            Logger.e(TAG, e.toString(), e);
        }
    }

    public static void checkInitializedOrThrow(AtomicBoolean atomicBoolean, boolean z) {
        Preconditions.checkState(z == atomicBoolean.get(), z ? "OpenGlRenderer is not initialized" : "OpenGlRenderer is already initialized");
    }

    public static void checkGlThreadOrThrow(Thread thread) {
        Preconditions.checkState(thread == Thread.currentThread(), "Method call must be called on the GL thread.");
    }

    public static String getGlVersionNumber() {
        Matcher matcher = Pattern.compile("OpenGL ES ([0-9]+)\\.([0-9]+).*").matcher(GLES20.glGetString(7938));
        if (!matcher.find()) {
            return "0.0";
        }
        return ((String) Preconditions.checkNotNull(matcher.group(1))) + "." + ((String) Preconditions.checkNotNull(matcher.group(2)));
    }

    public static int[] chooseSurfaceAttrib(String str, DynamicRange dynamicRange) {
        int[] iArr = EMPTY_ATTRIBS;
        if (dynamicRange.getEncoding() != 3) {
            return iArr;
        }
        if (str.contains("EGL_EXT_gl_colorspace_bt2020_hlg")) {
            return HLG_SURFACE_ATTRIBS;
        }
        Logger.w(TAG, "Dynamic range uses HLG encoding, but device does not support EGL_EXT_gl_colorspace_bt2020_hlg.Fallback to default colorspace.");
        return iArr;
    }

    public static int generateFbo() {
        int[] iArr = new int[1];
        GLES20.glGenFramebuffers(1, iArr, 0);
        checkGlErrorOrThrow("glGenFramebuffers");
        return iArr[0];
    }

    public static int generateTexture() {
        int[] iArr = new int[1];
        GLES20.glGenTextures(1, iArr, 0);
        checkGlErrorOrThrow("glGenTextures");
        return iArr[0];
    }

    public static void deleteTexture(int i) {
        GLES20.glDeleteTextures(1, new int[]{i}, 0);
        checkGlErrorOrThrow("glDeleteTextures");
    }

    public static void deleteFbo(int i) {
        GLES20.glDeleteFramebuffers(1, new int[]{i}, 0);
        checkGlErrorOrThrow("glDeleteFramebuffers");
    }

    /* access modifiers changed from: private */
    public static String getFragmentShaderSource(ShaderProvider shaderProvider) {
        try {
            String createFragmentShader = shaderProvider.createFragmentShader(VAR_TEXTURE, VAR_TEXTURE_COORD);
            if (createFragmentShader != null && createFragmentShader.contains(VAR_TEXTURE_COORD) && createFragmentShader.contains(VAR_TEXTURE)) {
                return createFragmentShader;
            }
            throw new IllegalArgumentException("Invalid fragment shader");
        } catch (Throwable th) {
            if (th instanceof IllegalArgumentException) {
                throw th;
            }
            throw new IllegalArgumentException("Unable retrieve fragment shader source", th);
        }
    }
}
