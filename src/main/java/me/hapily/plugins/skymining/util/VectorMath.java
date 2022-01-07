package me.hapily.plugins.skymining.util;

import org.bukkit.util.Vector;

public class VectorMath {

    public static final double PI = Math.PI;
    public static final double HALF_PI = PI / 2;
    public static final double DEG_TO_RAD = PI / 180;
    public static final double RAD_TO_DEG =  180 / PI;

    public static float getYaw(Vector vector) {
        if (((Double) vector.getX()).equals((double) 0) && ((Double) vector.getZ()).equals((double) 0)){
            return 0;
        }
        return (float) (Math.atan2(vector.getZ(), vector.getX()) * RAD_TO_DEG);
    }

    public static float getPitch(Vector vector) {
        double xy = Math.sqrt(vector.getX() * vector.getX() + vector.getZ() * vector.getZ());
        return (float) (Math.atan(vector.getY() / xy) * RAD_TO_DEG);
    }

    public static Vector setYaw(Vector vector, float yaw) {
        vector = fromYawAndPitch(yaw, getPitch(vector));
        return vector;
    }

    public static Vector setPitch(Vector vector, float pitch) {
        vector = fromYawAndPitch(getYaw(vector), pitch);
        return vector;
    }

    public static Vector fromYawAndPitch(float yaw, float pitch) {
        double y = Math.sin(pitch * DEG_TO_RAD);
        double div = Math.cos(pitch * DEG_TO_RAD);
        double x = Math.cos(yaw * DEG_TO_RAD);
        double z = Math.sin(yaw * DEG_TO_RAD);
        x *= div;
        z *= div;
        return new Vector(x,y,z);
    }

}
