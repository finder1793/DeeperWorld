package com.mineinabyss.deeperworld.movement

import com.mineinabyss.deeperworld.movement.transition.SectionTransition
import com.mineinabyss.idofront.messaging.error
import org.bukkit.Location
import org.bukkit.entity.Player

abstract class InvalidTeleportHandler(val player: Player, val from: Location, val to: Location) : TeleportHandler {
    constructor(player: Player, transition: SectionTransition) : this(player, transition.from, transition.to)
    final override fun handleTeleport() {
        handleInvalidTeleport()
        player.error("There is no where for you to teleport")
    }

    override fun isValidTeleport(): Boolean {
        return false
    }

    abstract fun handleInvalidTeleport()
}
