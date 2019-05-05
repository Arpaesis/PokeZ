package com.pkmn.mod

import com.pkmn.mod.entities.*
import com.pkmn.mod.gui.GuiPokemonBattle
import com.zerra.client.util.I18n
import com.zerra.api.registry.GuiRegistry
import com.zerra.client.ZerraClient
import com.zerra.client.entity.ClientEntityPlayer
import com.zerra.client.gfx.renderer.entity.EntityRenderer
import com.zerra.client.gfx.renderer.item.ItemRenderer
import com.zerra.client.gfx.texture.map.TextureMap
import com.zerra.common.ZerraCommonContentInit
import com.zerra.common.load.LoadingManager
import com.zerra.common.util.ResourceLocation
import java.util.*

class PkmnClientContentInit private constructor() : ZerraCommonContentInit() {

    override fun init(loadingManager: LoadingManager) {
        super.init(loadingManager)


        I18n.setLanguage(Locale("en", "us"))
        createItemTasks(ZerraClient.instance.renderingManager!!.itemRenderer!!, loadingManager)
        createTextureLoadingTask(ZerraClient.instance.renderingManager!!.textureManager!!.textureMap, loadingManager)
        createClientTasks(loadingManager)
    }

    override fun regGuis() {
        super.regGuis()
        GuiRegistry.registerGui(ResourceLocation("pokemonBattle")) { _, _, _ -> GuiPokemonBattle() }
    }

    fun createClientTasks(loadingManager: LoadingManager) {
        EntityRenderer.bindEntityRender(ClientEntityPlayer::class.java, RenderCustomPlayer())

        EntityRenderer.bindEntityRender(EntityBulbasaur::class.java, RenderPokemon("1"))
        EntityRenderer.bindEntityRender(EntityCharmander::class.java, RenderPokemon("4"))
        EntityRenderer.bindEntityRender(EntitySquirtle::class.java, RenderPokemon("7"))
        EntityRenderer.bindEntityRender(EntityMewtwo::class.java, RenderPokemon("150"))
    }

    fun createItemTasks(itemRenderer: ItemRenderer, loadingManager: LoadingManager) {
    }

    fun createTextureLoadingTask(textureMap: TextureMap, loadingManager: LoadingManager) {
    }

    companion object {
        val instance = PkmnClientContentInit()
    }
}