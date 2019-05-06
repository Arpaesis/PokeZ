package com.pkmn.mod

import com.zerra.api.registry.EntityRegistry
import com.zerra.common.ZerraCommonContentInit
import com.zerra.common.event.impl.EntityLivingEvent
import com.zerra.common.load.LoadingManager
import com.zerra.common.util.ResourceLocation
import com.zerra.common.world.entity.Entity
import com.zerra.common.world.entity.EntityPlayer
import com.zerra.common.world.tile.Tiles
import com.zerra.server.ZerraServer
import org.joml.Vector2i
import java.util.function.Consumer

class PkmnServerContentInit private constructor() : ZerraCommonContentInit() {

    override fun init(loadingManager: LoadingManager) {
        super.init(loadingManager)
        this.regSpawns()

        ZerraServer.instance.eventHandler.registerCallback(EntityLivingEvent.Update::class.java, Consumer {
            val e = it.entity


            if(e is EntityPlayer && e.ticksExisted % 20 == 0)
            {


                if((e.velocity.x != 0f || e.velocity.z != 0f) && e.layer.getPlate(e.platePosition)!!.getTileAt(Vector2i(e.tilePosition.x, e.tilePosition.z)) == Tiles.SAND)
                {
                    ZerraServer.instance.world?.let { world ->
                        if(world.random.nextInt(100) < 10)
                        {
                            val entity = EntityRegistry.getNewEntityInstance<Entity>(ResourceLocation("pkmn:bulbasaur"), world.zerra, world.getLayer(0))
                            println("POKEMON ENCOUNTER")
                        }
                    }
                }
            }
        })
    }

    private fun regSpawns() {
        // EntityManager.addSpawnEntry(SpawnEntryBuilder(EntityBulbasaur::class.java).setTimeRange(0, WorldTime.DAY_LENGTH).build())
        // EntityManager.addSpawnEntry(SpawnEntryBuilder(EntitySquirtle::class.java).setTimeRange(0, WorldTime.DAY_LENGTH).build())
        // EntityManager.addSpawnEntry(SpawnEntryBuilder(EntityCharmander::class.java).setTimeRange(0, WorldTime.DAY_LENGTH).build())
        // EntityManager.addSpawnEntry(SpawnEntryBuilder(EntityBat::class.java).setTimeRange(WorldTime.TIME_DUSK, 1000).build())
    }

    companion object {
        val instance = PkmnServerContentInit()
    }
}