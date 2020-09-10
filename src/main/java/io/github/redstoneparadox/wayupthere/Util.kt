package io.github.redstoneparadox.wayupthere

import net.minecraft.util.Identifier

fun String.id(): Identifier {
    return Identifier.tryParse(this) ?: Identifier("minecraft", "empty")
}