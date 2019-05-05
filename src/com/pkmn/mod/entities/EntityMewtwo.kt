package com.pkmn.mod.entities

import com.zerra.common.world.entity.EntityLiving
import com.zerra.common.world.storage.Layer

class EntityMewtwo(layer: Layer) : EntityLiving(layer) {
    private var targetX = 0
    private var targetZ = 0
    private var shouldMove = false

    init {
        this.setSize(2f, 2f)
    }

    override fun update() {
        // TODO move this to AI
        if (this.world!!.isServer) {
            if ((this.ticksExisted + (this.world.random.nextInt(31) - 15)) % 60 == 0 && !shouldMove) {
                targetX = this.tilePosition.x() + this.world.random.nextInt(13) - 6
                targetZ = this.tilePosition.z() + this.world.random.nextInt(13) - 6
                shouldMove = true
            }

            // System.out.println("CURRENT POSITION: " + this.getTilePosition().x() + ", 0, " + this.getTilePosition().z());
            // System.out.println("TARGET POSITION: " + targetX + ", 0, " + targetZ);

            if (shouldMove) {
                if (targetX != this.tilePosition.x() || targetZ != this.tilePosition.z()) {
                    val xSpeed = targetX - this.tilePosition.x()
                    val zSpeed = targetZ - this.tilePosition.z()

                    val factor = (SPEED.defaultValue / Math.sqrt((xSpeed * xSpeed + zSpeed * zSpeed).toDouble())).toFloat()

                    this.velocity.x = xSpeed * factor
                    this.velocity.z = zSpeed * factor
                } else {
                    this.velocity.x = 0f
                    this.velocity.z = 0f
                    shouldMove = false
                }
            }

            if (this.world.random.nextInt(100) < 1) {
                //this.world.playSound(this.position, ZerraSounds.ZOMBIE_LIVING, 0.2f, 1.3f - this.world.random.nextFloat())
            }
        }
        super.update()
    }
}