package com.google.firebase.firestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VectorValue {
    private final double[] values;

    VectorValue(double[] dArr) {
        double[] dArr2;
        if (dArr == null) {
            dArr2 = new double[0];
        } else {
            dArr2 = (double[]) dArr.clone();
        }
        this.values = dArr2;
    }

    public double[] toArray() {
        return (double[]) this.values.clone();
    }

    /* access modifiers changed from: package-private */
    public List<Double> toList() {
        ArrayList arrayList = new ArrayList(this.values.length);
        int i = 0;
        while (true) {
            double[] dArr = this.values;
            if (i >= dArr.length) {
                return arrayList;
            }
            arrayList.add(i, Double.valueOf(dArr[i]));
            i++;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.values, ((VectorValue) obj).values);
    }

    public int hashCode() {
        return Arrays.hashCode(this.values);
    }
}
