package moe.yuru.newhorizons.models;

import java.util.Random;   
import com.badlogic.gdx.utils.ObjectMap;

/**
 * Normal difficulty opponent for versus games.
 * 
 * @author DinoGurnari
 */
public class OpponentNormal implements Opponent {

    private Town player;

    private float coins;
    private ObjectMap<String, Float> resources;

    public OpponentNormal() {
        this.resources = new ObjectMap<>();

        // Starting resources
        coins = 10000f;
        for (Faction faction : Faction.values()) {
            resources.put(faction.name(), 1000f);
        }
    }

    @Override
    public void update(float delta) {
        Random rand = new Random();

        // Coins
        this.coins = player.getCoins()*0.8f + rand.nextInt((int) (player.getCoins()/5));

        // Faction resources
        for (Faction faction : Faction.values()) {
            float randResource = player.getResources(faction)*0.8f + rand.nextInt((int) (player.getResources(faction)/5));
            setResources(faction, randResource);
        }
        
    }

    public void setPlayer(Town player) {
        this.player = player;
    }

    /**
     * @param faction a game faction
     * @param amount  of resources to add in this faction
     */
    public void setResources(Faction faction, float amount) {
        if (amount >= 0) {
            resources.put(faction.name(), amount);
        }
    }

    /**
     * @return current coins balance
     */
    public float getCoins() {
        return coins;
    }

    /**
     * @param faction a game faction
     * @return ressource balance in this faction
     */
    public float getResources(Faction faction) {
        return resources.get(faction.name());
    }
}
