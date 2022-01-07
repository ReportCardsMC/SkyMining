package me.hapily.plugins.skymining.util;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class Movement {

    enum Direction{
        FORWARDS,
        BACKWARDS,
        HORIZONTALLY_FORWARDS,
        HORIZONTALLY_BACKWARDS,
        UPWARDS,
        DOWNWARDS,
        NORTH,
        SOUTH,
        EAST,
        WEST,
        NORTH_EAST,
        NORTH_WEST,
        SOUTH_EAST,
        SOUTH_WEST,
        RIGHT,
        LEFT

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
            case UPWARDS:
                return BlockFace.UP.getDirection();
            case DOWNWARDS:
                return BlockFace.DOWN.getDirection();
            case NORTH:
                return BlockFace.NORTH.getDirection();
            case EAST:
                return BlockFace.EAST.getDirection();
            case SOUTH:
                return BlockFace.SOUTH.getDirection();
            case WEST:
                return BlockFace.WEST.getDirection();
            case NORTH_EAST:
                return BlockFace.NORTH_EAST.getDirection();
            case NORTH_WEST:
                return BlockFace.NORTH_WEST.getDirection();
            case SOUTH_EAST:
                return BlockFace.SOUTH_EAST.getDirection();
            case SOUTH_WEST:
                return BlockFace.SOUTH_WEST.getDirection();
            case RIGHT:
                return VectorMath.setYaw(entity.getFacing().getDirection(), VectorMath.getYaw(entity.getFacing().getDirection()) + 90);
            case LEFT:
                return VectorMath.setYaw(entity.getFacing().getDirection(), VectorMath.getYaw(entity.getFacing().getDirection()) - 90);
            default:
                return null;
        }
    }

}
