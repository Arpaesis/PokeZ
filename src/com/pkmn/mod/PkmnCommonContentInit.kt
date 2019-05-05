package com.pkmn.mod

import com.pkmn.mod.entities.EntityBulbasaur
import com.pkmn.mod.entities.EntityCharmander
import com.pkmn.mod.entities.EntityMewtwo
import com.pkmn.mod.entities.EntitySquirtle
import com.zerra.api.registry.EntityRegistry
import com.zerra.common.Zerra
import com.zerra.common.load.LoadingManager
import com.zerra.common.util.Logging
import com.zerra.common.util.ResourceLocation
import com.zerra.common.world.entity.Entity
import com.zerra.common.world.storage.Layer

class PkmnCommonContentInit: Logging() {
    companion object {
        private var registered: Boolean = false
        val instance = PkmnCommonContentInit()
    }

    /**
     * Registers an entity to the game.
     *
     * @param registryName
     * The registry name of the entity.
     * @param entityClass
     * The entity's class.
     * @param factory
     * A factory for the entity.
     */
    protected fun <T : Entity> regEntity(registryName: String, entityClass: Class<T>, factory: (zerra: Zerra, layer: Layer) -> T) {
        EntityRegistry.registerEntity(ResourceLocation(registryName), entityClass, factory)
    }

    /**
     * Begins the initialization process.
     *
     * @param loadingManager
     * The [LoadingManager] to use during loading.
     */
    open fun init(loadingManager: LoadingManager) {
        regEntities()

        registered = true
    }

    /**
     * Registers the entities for the game.
     */
    protected open fun regEntities() {
        if (registered) return

        regEntity("pkmn:bulbasaur", EntityBulbasaur::class.java) { _, layer -> EntityBulbasaur(layer) }
        regEntity("pkmn:squirtle", EntitySquirtle::class.java) { _, layer -> EntitySquirtle(layer) }
        regEntity("pkmn:charmander", EntityCharmander::class.java) { _, layer -> EntityCharmander(layer) }
        regEntity("pkmn:mewtwo", EntityMewtwo::class.java) { _, layer -> EntityMewtwo(layer) }
    }
}