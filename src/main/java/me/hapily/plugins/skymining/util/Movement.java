package me.hapily.plugins.skymining.util;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class Movement {

    enum Direction{
        FORWARDS,
        BACKWARDS,
        HORIZONTALLY_FORWARDS,
        HORIZONTALLY_BACKWARDS

    }

    public static void clearVelocity(Entity entity){
        entity.setVelocity(new Vector(0,0,0));
    }

    public static void pushEntity(Entity entity, Vector vector, Boolean setVelocity){
        if(!setVelocity){
            entity.getVelocity().add(vector);
        }
        else{
            entity.setVelocity(vector);
        }

    }

    public static void pushEntity(Entity entity, Vector vector, Number speed, Boolean setVelocity){
        vector.normalize().multiply(speed.doubleValue());
        pushEntity(entity, vector, setVelocity);
    }

    public static Vector getVector(Entity entity, Direction direction){
        switch(direction){
            case FORWARDS:
                return entity.getFacing().getDirection();
            case BACKWARDS:
                return entity.getFacing().getOppositeFace().getDirection();
            case HORIZONTALLY_FORWARDS:
                return entity.getFacing().getDirection().setY(0);
            case HORIZONTALLY_BACKWARDS:
                return entity.getFacing().getOppositeFace().getDirection().setY(0);
            default:
                return null;
        }
    }

}
