/**
 * CreeperSpawner.java
 * 
 * Created on 10:39:49
 */
package de.kniffo80.mobplugin.entities.spawners;

import cn.nukkit.IPlayer;
import cn.nukkit.block.Block;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import de.kniffo80.mobplugin.AutoSpawnTask;
import de.kniffo80.mobplugin.FileLogger;
import de.kniffo80.mobplugin.entities.animal.walking.Rabbit;
import de.kniffo80.mobplugin.entities.autospawn.AbstractEntitySpawner;
import de.kniffo80.mobplugin.entities.autospawn.SpawnResult;

/**
 * Each entity get it's own spawner class.
 * 
 * @author <a href="mailto:kniffman@googlemail.com">Michael Gertz</a>
 */
public class RabbitSpawner extends AbstractEntitySpawner {

    /**
     * @param spawnTask
     */
    public RabbitSpawner(AutoSpawnTask spawnTask) {
        super(spawnTask);
    }

    public SpawnResult spawn(IPlayer iPlayer, Position pos, Level level) {
        SpawnResult result = SpawnResult.OK;

        int blockId = level.getBlockIdAt((int) pos.x, (int) pos.y, (int) pos.z);
        int blockLightLevel = level.getBlockLightAt((int) pos.x, (int) pos.y, (int) pos.z);
        
        // normally, they spawn in deserts, flower forests, taiga, mega taiga, cold taiga, ice plains, ice mountains, ice spikes, and the "hills" and "M" variants
        // but as this are nearly all biomes, we leave that as is 
        if (pos.y > 127 || pos.y < 1 || level.getBlockIdAt((int) pos.x, (int) pos.y, (int) pos.z) == Block.AIR) { // cannot spawn on AIR block
            result = SpawnResult.POSITION_MISMATCH;
        } else { // creeper is spawned
            this.spawnTask.createEntity(getEntityName(), pos.add(0, 1.75, 0));
        }

        FileLogger.info(String.format("Spawn for %s at %d,%d,%d with lightlevel %d and blockId %d, result: %s", iPlayer.getName(), pos.x, pos.y, pos.z, blockLightLevel, blockId, result));

        return result;
    }
    
    /* (@Override)
     * @see cn.nukkit.entity.ai.IEntitySpawner#getEntityNetworkId()
     */
    @Override
    public int getEntityNetworkId() {
        return Rabbit.NETWORK_ID;
    }

    /* (@Override)
     * @see cn.nukkit.entity.ai.IEntitySpawner#getEntityName()
     */
    @Override
    public String getEntityName() {
        return "Rabbit";
    }

}
