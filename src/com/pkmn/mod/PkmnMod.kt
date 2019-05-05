package com.pkmn.mod

import com.zerra.api.mod.Mod
import com.zerra.api.mod.ModInit
import com.zerra.api.mod.info.ModInfo
import com.zerra.api.mod.info.ModInfoBuilder
import com.zerra.common.Reference
import com.zerra.common.util.Side

class PkmnMod: Mod {
    override val modInfo: ModInfo
        get() =  ModInfoBuilder("pkmn", "Pokemon Mod", "1.0", Reference.VERSION)
                .setModDescription("A Pokemon mod for Zerra.")
                .build()

    override fun init(modInit: ModInit) {

        PkmnCommonContentInit.instance.init(modInit.loadingManager)

        when (modInit.side) {
            Side.SERVER -> PkmnServerContentInit.instance.init(modInit.loadingManager)
            Side.CLIENT -> PkmnClientContentInit.instance.init(modInit.loadingManager)
        }
    }

    override fun postInit() {
    }
}