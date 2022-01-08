package me.hapily.plugins.skymining.mines;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.util.BoundingBox;

import java.util.*;

public class Mine {

    public HashMap<Material, Number> mine;
    public String name;
    public BoundingBox box;
    public int x1;
    public int y1;
    public int z1;
    public int x2;
    public int y2;
    public int z2;
    public String world;

    public Mine(HashMap<Material, Number> chancepermat, String name, Location point1, Location point2){
        this.mine = chancepermat;
        this.name = name;
        this.x1 = (int) point1.getX();
        this.y1 = (int) point1.getY();
        this.z1 = (int) point1.getZ();
        this.x2 = (int) point2.getX();
        this.y2 = (int) point2.getY();
        this.z2 = (int) point2.getZ();
        this.world = point1.getWorld().getName();
        this.box = new BoundingBox(x1, y1, z1, x2, y2, z2);
        // Adding the mine to the mines list
        Mines.mines.put(name, this);
    }

    public ArrayList<Material> getMaterials(){
        ArrayList<Material> mats = new ArrayList<>();
        for(Map.Entry<Material, Number> entry : mine.entrySet()){
            mats.add(entry.getKey());
        }
        return mats;
    }

    public Number getChance(Material material){
        return mine.get(material);
    }

    public List<Block> getBlocks() {
        World w = Bukkit.getWorld(world);
        List <Block> array = new ArrayList<>();
        for (int x3 = x1; x3 <= x2; x3++) {
            for (int y3 = y1; y3 <= y2; y3++) {
                for (int z3 = z1; z3 <= z2; z3++) {
                    assert w != null;
                    Block b = w.getBlockAt(x3, y3, z3);
                    array.add(b);
                }
            }
        }
        return array;
    }

    public void reset(){
        for(Block block : getBlocks()){
            block.setType(getMaterial());
        }
    }
    // getMaterial() is to get the material by chance
    private Material getMaterial(){
        TreeMap<Double, Material> tree = new TreeMap<>();
        double count = 0;
        for(Map.Entry<Material, Number> entry : mine.entrySet()){
            tree.put(count, entry.getKey());
            count += entry.getValue().doubleValue();
        }
        Random rand = new Random();
        Double picked = rand.nextDouble() * count;
        return tree.get(tree.floorKey(picked));
    }

}
