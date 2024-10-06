/*
 * This file is part of LiquidBounce (https://github.com/CCBlueX/LiquidBounce)
 *
 * Copyright (c) 2015-2024 CCBlueX
 *
 * LiquidBounce is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LiquidBounce is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LiquidBounce. If not, see <https://www.gnu.org/licenses/>.
 *
 *
 */
package net.ccbluex.liquidbounce.features.module.modules.movement.speed.modes.karhu

import net.ccbluex.liquidbounce.config.Choice
import net.ccbluex.liquidbounce.config.ChoiceConfigurable
import net.ccbluex.liquidbounce.event.repeatable
import net.ccbluex.liquidbounce.utils.client.chat
import net.ccbluex.liquidbounce.utils.entity.downwards
import net.ccbluex.liquidbounce.utils.entity.upwards

class SpeedKarhuLow(override val parent: ChoiceConfigurable<*>) : Choice("KarhuLow") {
    private var shouldLowHop = false
    private var airTicks = 0

    val repeatable = repeatable {
        if (player.isOnGround && shouldLowHop) {
            player.jump()
            waitTicks(1)
            player.downwards(0.1f)
            shouldLowHop = false
        } else if (player.isOnGround) player.jump()

        if (player.isOnGround) {
            airTicks = 0
        } else airTicks++

        if (airTicks == 5) shouldLowHop = true

        chat("$airTicks, $shouldLowHop")
    }
}
