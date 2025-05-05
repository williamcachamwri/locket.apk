package androidx.media3.common.util;

import android.os.Bundle;
import android.util.SparseArray;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BundleCollectionUtil {
    public static <T> ImmutableList<Bundle> toBundleList(List<T> list, Function<T, Bundle> function) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i = 0; i < list.size(); i++) {
            builder.add((Object) function.apply(list.get(i)));
        }
        return builder.build();
    }

    public static <T> ImmutableList<T> fromBundleList(Function<Bundle, T> function, List<Bundle> list) {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i = 0; i < list.size(); i++) {
            builder.add((Object) function.apply((Bundle) Assertions.checkNotNull(list.get(i))));
        }
        return builder.build();
    }

    public static <T> ArrayList<Bundle> toBundleArrayList(Collection<T> collection, Function<T, Bundle> function) {
        ArrayList<Bundle> arrayList = new ArrayList<>(collection.size());
        for (T apply : collection) {
            arrayList.add(function.apply(apply));
        }
        return arrayList;
    }

    public static <T> SparseArray<T> fromBundleSparseArray(Function<Bundle, T> function, SparseArray<Bundle> sparseArray) {
        SparseArray<T> sparseArray2 = new SparseArray<>(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++) {
            sparseArray2.put(sparseArray.keyAt(i), function.apply(sparseArray.valueAt(i)));
        }
        return sparseArray2;
    }

    public static <T> SparseArray<Bundle> toBundleSparseArray(SparseArray<T> sparseArray, Function<T, Bundle> function) {
        SparseArray<Bundle> sparseArray2 = new SparseArray<>(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++) {
            sparseArray2.put(sparseArray.keyAt(i), function.apply(sparseArray.valueAt(i)));
        }
        return sparseArray2;
    }

    public static Bundle stringMapToBundle(Map<String, String> map) {
        Bundle bundle = new Bundle();
        for (Map.Entry next : map.entrySet()) {
            bundle.putString((String) next.getKey(), (String) next.getValue());
        }
        return bundle;
    }

    public static HashMap<String, String> bundleToStringHashMap(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap<>();
        if (bundle == Bundle.EMPTY) {
            return hashMap;
        }
        for (String str : bundle.keySet()) {
            String string = bundle.getString(str);
            if (string != null) {
                hashMap.put(str, string);
            }
        }
        return hashMap;
    }

    public static ImmutableMap<String, String> bundleToStringImmutableMap(Bundle bundle) {
        if (bundle == Bundle.EMPTY) {
            return ImmutableMap.of();
        }
        return ImmutableMap.copyOf(bundleToStringHashMap(bundle));
    }

    public static Bundle getBundleWithDefault(Bundle bundle, String str, Bundle bundle2) {
        Bundle bundle3 = bundle.getBundle(str);
        return bundle3 != null ? bundle3 : bundle2;
    }

    public static ArrayList<Integer> getIntegerArrayListWithDefault(Bundle bundle, String str, ArrayList<Integer> arrayList) {
        ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList(str);
        return integerArrayList != null ? integerArrayList : arrayList;
    }

    public static void ensureClassLoader(Bundle bundle) {
        if (bundle != null) {
            bundle.setClassLoader((ClassLoader) Util.castNonNull(BundleCollectionUtil.class.getClassLoader()));
        }
    }

    private BundleCollectionUtil() {
    }
}
