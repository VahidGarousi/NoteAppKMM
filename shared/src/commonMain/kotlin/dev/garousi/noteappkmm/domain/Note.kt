package dev.garousi.noteappkmm.domain

import dev.garousi.noteappkmm.presentation.BabyBlueHex
import dev.garousi.noteappkmm.presentation.LightGreenHex
import dev.garousi.noteappkmm.presentation.RedOrangeHex
import dev.garousi.noteappkmm.presentation.RedPinkHex
import dev.garousi.noteappkmm.presentation.VioletHex
import kotlinx.datetime.LocalDateTime

data class Note(
    val id : Long?,
    val title : String,
    val content : String,
    val colorHex : Long,
    val created : LocalDateTime
) {
    companion object {
        private val colors = listOf(RedOrangeHex, RedPinkHex, LightGreenHex, BabyBlueHex, VioletHex)

        fun generateRandomColor() = colors.random()
    }
}