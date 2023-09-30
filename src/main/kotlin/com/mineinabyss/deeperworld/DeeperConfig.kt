@file:UseSerializers(DurationSerializer::class)

package com.mineinabyss.deeperworld

import com.mineinabyss.deeperworld.world.section.Section
import com.mineinabyss.idofront.serialization.DurationSerializer
import com.mineinabyss.idofront.serialization.WorldSerializer
import com.mineinabyss.idofront.time.ticks
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.bukkit.World
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

val deeperConfig get() = deeperWorld.config.data
@Serializable
data class DeeperWorldConfig(
    val sections: List<Section> = emptyList(),
    val damageOutsideSections: Double = 0.0,
    val damageExcludedWorlds: Set<@Serializable(with = WorldSerializer::class) World> = emptySet(),
    val remountPacketDelay: Duration = 40.ticks,
    val fall: FallDamageConfig = FallDamageConfig(),
    val time: TimeConfig = TimeConfig(),
) {
    val worlds = sections.map { it.world }.toSet()

    @Serializable
    data class FallDamageConfig(
        val maxSafeDist: Float = -1f,
        val fallDistanceDamageScaler: Double = 0.01,
        val startingDamage: Double = 1.0,
        val hitDelay: @Serializable(DurationSerializer::class) Duration = 10.ticks,
        val spawnParticles: Boolean = true
    )

    @Serializable
    data class TimeConfig(
        val updateInterval: @Serializable(DurationSerializer::class) Duration = 1800.seconds,
        val mainWorld: @Serializable(WorldSerializer::class) World? = null,
        val syncedWorlds: Map<@Serializable(WorldSerializer::class) World, Long> = emptyMap(),
    )
}