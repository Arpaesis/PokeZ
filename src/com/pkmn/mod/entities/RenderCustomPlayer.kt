package com.pkmn.mod.entities

import com.zerra.client.entity.ClientEntityPlayer
import com.zerra.client.gfx.ICamera
import com.zerra.client.gfx.renderer.entity.RenderEntity
import com.zerra.client.gfx.sprite.Animation
import com.zerra.client.gfx.sprite.Sprite
import com.zerra.client.world.ClientWorld
import com.zerra.common.util.ResourceLocation
import com.zerra.common.world.entity.facing.Direction

class RenderCustomPlayer : RenderEntity<ClientEntityPlayer> {

    private val idleNorthAnimation: Animation = Animation()
    private val idleSouthAnimation: Animation = Animation()
    private val idleWestAnimation: Animation = Animation()
    private val idleEastAnimation: Animation = Animation()
    private val walkingNorthAnimation: Animation = Animation()
    private val walkingSouthAnimation: Animation = Animation()
    private val walkingWestAnimation: Animation = Animation()
    private val walkingEastAnimation: Animation = Animation()

    init {
        val texture = ResourceLocation("pkmn:textures/entities/player.png")
        idleNorthAnimation.addSprite(Sprite(texture, 0, 0, 32, 32, 128, 96), 0)
        idleSouthAnimation.addSprite(Sprite(texture, 32, 0, 32, 32, 128, 96), 0)
        idleWestAnimation.addSprite(Sprite(texture, 64, 0, 32, 32, 128, 96), 0)
        idleEastAnimation.addSprite(Sprite(texture, 96, 0, 32, 32, 128, 96), 0)
        for (i in 0 until 3) {
            idleNorthAnimation.addSprite(Sprite(texture, 0, 0 + i * 32, 32, 32, 128, 96), 0)
            idleSouthAnimation.addSprite(Sprite(texture, 32, 0 + i * 32, 32, 32, 128, 96), 0)
            idleWestAnimation.addSprite(Sprite(texture, 64, 0 + i * 32, 32, 32, 128, 96), 0)
            idleEastAnimation.addSprite(Sprite(texture, 96, 0 + i * 32, 32, 32, 128, 96), 0)
        }
    }

    constructor()

    override fun render(entity: ClientEntityPlayer, world: ClientWorld, camera: ICamera, partialTicks: Float) {

        var lastAnimation: Animation = this.idleSouthAnimation
        var animation: Animation = lastAnimation
        if (entity.facingDirection  == Direction.EAST && entity.velocity.x != 0f) {
            animation = walkingEastAnimation
        }else if(entity.facingDirection  == Direction.WEST  && entity.velocity.x != 0f) {
            animation = walkingWestAnimation
        }
        else if(entity.facingDirection == Direction.SOUTH && entity.velocity.z != 0f)
        {
            animation = walkingSouthAnimation
        }
        else if(entity.facingDirection == Direction.NORTH && entity.velocity.z != 0f)
        {
            animation = walkingNorthAnimation
        }
        else {
            if(entity.facingDirection == Direction.NORTH)
            {
                animation = idleNorthAnimation
            }else if(entity.facingDirection == Direction.SOUTH)
            {
                animation = idleSouthAnimation
            }else if(entity.facingDirection == Direction.EAST)
            {
                animation = idleEastAnimation
            }else {
                animation = idleWestAnimation
            }
        }

        if (lastAnimation != animation) {
            lastAnimation.reset()
            lastAnimation = animation
        }

        animation.update()
        this.loadLights(world, entity)
        this.render(entity, animation.getCurrentSprite(), 2f, 2f, camera, partialTicks, true)
    }
}