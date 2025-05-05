package com.swmansion.reanimated.sensor;

public enum ReanimatedSensorType {
    ACCELEROMETER(10),
    GYROSCOPE(4),
    GRAVITY(9),
    MAGNETIC_FIELD(2),
    ROTATION_VECTOR(11);
    
    private final int type;

    private ReanimatedSensorType(int i) {
        this.type = i;
    }

    public int getType() {
        return this.type;
    }

    public static ReanimatedSensorType getInstanceById(int i) {
        if (i == 1) {
            return ACCELEROMETER;
        }
        if (i == 2) {
            return GYROSCOPE;
        }
        if (i == 3) {
            return GRAVITY;
        }
        if (i == 4) {
            return MAGNETIC_FIELD;
        }
        if (i == 5) {
            return ROTATION_VECTOR;
        }
        throw new IllegalArgumentException("[Reanimated] Unknown sensor type.");
    }
}
