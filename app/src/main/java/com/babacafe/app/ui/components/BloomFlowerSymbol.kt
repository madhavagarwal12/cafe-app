package com.babacafe.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.babacafe.app.ui.theme.BloomCream100
import com.babacafe.app.ui.theme.BloomCream200
import com.babacafe.app.ui.theme.BloomPollen500
import com.babacafe.app.ui.theme.BloomPollen600
import com.babacafe.app.ui.theme.BloomStem500

/**
 * Botanical Flower Symbol from Bloom Design System
 * 9 rotated organic petals + pollen disc with center texture + optional stem.
 */
@Composable
fun BloomFlowerSymbol(
    modifier: Modifier = Modifier,
    size: Dp = 180.dp,
    showStem: Boolean = false,
    alpha: Float = 1.0f
) {
    Canvas(
        modifier = modifier.size(size)
    ) {
        val centerX = size.toPx() / 2f
        val centerY = if (showStem) size.toPx() * 0.4f else size.toPx() / 2f
        val radius = size.toPx() * 0.35f

        // Draw Stem
        if (showStem) {
            val stemWidth = 3.5.dp.toPx()
            drawLine(
                color = BloomStem500.copy(alpha = alpha),
                start = Offset(centerX, centerY),
                end = Offset(centerX, size.toPx()),
                strokeWidth = stemWidth
            )
        }

        // Draw 9 Petals
        for (i in 0 until 9) {
            val angle = i * 40f
            rotate(degrees = angle, pivot = Offset(centerX, centerY)) {
                val petalPath = Path().apply {
                    moveTo(centerX, centerY - 6f)
                    cubicTo(
                        centerX - radius * 0.25f, centerY - radius * 0.4f,
                        centerX - radius * 0.2f, centerY - radius,
                        centerX, centerY - radius * 1.15f
                    )
                    cubicTo(
                        centerX + radius * 0.2f, centerY - radius,
                        centerX + radius * 0.25f, centerY - radius * 0.4f,
                        centerX, centerY - 6f
                    )
                    close()
                }

                drawPath(
                    path = petalPath,
                    color = BloomCream100.copy(alpha = alpha)
                )

                // Thin petal outline
                drawPath(
                    path = petalPath,
                    color = BloomCream200.copy(alpha = alpha * 0.6f),
                    style = androidx.compose.ui.graphics.drawscope.Stroke(width = 1.dp.toPx())
                )
            }
        }

        // Center Pollen Disc
        val pollenRadius = radius * 0.22f
        drawCircle(
            color = BloomPollen500.copy(alpha = alpha),
            radius = pollenRadius,
            center = Offset(centerX, centerY)
        )

        // Pollen texture specks
        val specks = listOf(
            Offset(-0.35f, -0.25f),
            Offset(0.3f, -0.4f),
            Offset(0.45f, 0.25f),
            Offset(-0.2f, 0.45f),
            Offset(0.1f, 0.0f),
            Offset(-0.5f, 0.25f)
        )
        specks.forEach { speck ->
            drawCircle(
                color = BloomPollen600.copy(alpha = alpha * 0.75f),
                radius = 1.8.dp.toPx(),
                center = Offset(
                    centerX + speck.x * pollenRadius,
                    centerY + speck.y * pollenRadius
                )
            )
        }
    }
}
