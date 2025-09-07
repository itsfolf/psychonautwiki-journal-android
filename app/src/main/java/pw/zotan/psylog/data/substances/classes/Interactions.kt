package pw.zotan.psylog.data.substances.classes

import androidx.compose.ui.graphics.Color

enum class InteractionType {
    DANGEROUS {
        override val color = Color(0xffFF7B66)
        override val dangerCount = 3
    },
    UNSAFE {
        override val color = Color(0xFFFFC466)
        override val dangerCount = 2
    },
    UNCERTAIN {
        override val color = Color(0xffFFF966)
        override val dangerCount = 1
    };

    abstract val color: Color
    abstract val dangerCount: Int
}

data class Interactions(
    val dangerous: List<String>,
    val unsafe: List<String>,
    val uncertain: List<String>
)