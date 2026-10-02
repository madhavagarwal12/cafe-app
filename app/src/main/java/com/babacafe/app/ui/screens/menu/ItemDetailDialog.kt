package com.babacafe.app.ui.screens.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.babacafe.app.data.model.MenuItem
import com.babacafe.app.data.model.PreparationStyle
import com.babacafe.app.data.model.SizeOption
import com.babacafe.app.ui.components.BloomButton
import com.babacafe.app.ui.components.BloomButtonVariant
import com.babacafe.app.ui.components.BloomInput
import com.babacafe.app.ui.components.BloomTag
import com.babacafe.app.ui.theme.BloomBlue700
import com.babacafe.app.ui.theme.BloomCream100
import com.babacafe.app.ui.theme.BloomCream200
import com.babacafe.app.ui.theme.BloomPollen500
import com.babacafe.app.ui.theme.BloomTheme

@Composable
fun ItemDetailDialog(
    item: MenuItem,
    onDismiss: () -> Unit,
    onAddToCart: (MenuItem, SizeOption?, PreparationStyle, String, Int) -> Unit
) {
    val typography = BloomTheme.typography
    val colors = BloomTheme.colors
    val spacing = BloomTheme.spacing

    var selectedSize by remember {
        mutableStateOf(item.sizeOptions.firstOrNull())
    }

    var selectedStyle by remember {
        mutableStateOf(item.availableStyles.firstOrNull() ?: PreparationStyle.DEFAULT)
    }

    var quantity by remember { mutableIntStateOf(1) }
    var specialInstructions by remember { mutableStateOf("") }

    val calculatedUnitPrice: Double = run {
        var p = selectedSize?.price ?: item.basePrice
        if (selectedStyle == PreparationStyle.GRAVY && item.availableStyles.contains(PreparationStyle.GRAVY)) {
            if (item.id.contains("manchurian") || item.id.contains("chilli_paneer")) {
                p = 130.0
            }
        }
        p
    }

    val calculatedTotal = calculatedUnitPrice * quantity

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            shape = RoundedCornerShape(spacing.radiusSquare),
            color = BloomBlue700,
            border = androidx.compose.foundation.BorderStroke(spacing.borderFocus, BloomCream200)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp)
            ) {
                // Header with close button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BloomTag(
                        text = item.category.title,
                        hasPollenDot = item.isBabaSpecial
                    )

                    IconButton(onClick = onDismiss, modifier = Modifier.size(28.dp)) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            tint = BloomCream200
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = item.name.uppercase(),
                    style = typography.h1,
                    color = BloomCream100
                )

                if (item.hindiName.isNotEmpty()) {
                    Text(
                        text = item.hindiName,
                        style = typography.bodySmall,
                        color = colors.textMuted
                    )
                }

                if (item.description.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = item.description,
                        style = typography.body,
                        color = colors.text
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 14.dp),
                    thickness = spacing.borderHairline,
                    color = colors.line
                )

                // 1. Size Options (if available)
                if (item.sizeOptions.isNotEmpty()) {
                    Text(
                        text = "SELECT PORTION SIZE",
                        style = typography.caption,
                        color = colors.textMuted
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item.sizeOptions.forEach { size ->
                            val isSelected = selectedSize?.label == size.label
                            BloomTag(
                                text = "${size.label}: ₹${size.price.toInt()}",
                                isSelected = isSelected,
                                onClick = { selectedSize = size },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                }

                // 2. Preparation Styles (Dry vs Gravy)
                if (item.availableStyles.isNotEmpty()) {
                    Text(
                        text = "CHOOSE STYLE",
                        style = typography.caption,
                        color = colors.textMuted
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item.availableStyles.forEach { style ->
                            val isSelected = selectedStyle == style
                            val priceLabel = if (style == PreparationStyle.GRAVY) "Gravy (₹130)" else "Dry (₹100)"
                            BloomTag(
                                text = priceLabel,
                                isSelected = isSelected,
                                onClick = { selectedStyle = style },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                }

                // 3. Special Request / Notes
                BloomInput(
                    value = specialInstructions,
                    onValueChange = { specialInstructions = it },
                    label = "Special Instructions",
                    placeholder = "e.g. Less spicy, extra crisp, no onion"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // 4. Quantity and Live Price
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "QUANTITY",
                        style = typography.caption,
                        color = colors.textMuted
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .border(spacing.borderHairline, BloomCream200)
                                .clickable { if (quantity > 1) quantity-- },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Remove,
                                contentDescription = "Decrease",
                                tint = BloomCream200,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        Text(
                            text = quantity.toString(),
                            style = typography.h2,
                            color = BloomCream100
                        )

                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .border(spacing.borderHairline, BloomCream200)
                                .clickable { quantity++ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Increase",
                                tint = BloomCream200,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // 5. Add to Cart CTA
                BloomButton(
                    text = "ADD TO ORDER · ₹${calculatedTotal.toInt()}",
                    onClick = {
                        onAddToCart(
                            item,
                            selectedSize,
                            selectedStyle,
                            specialInstructions,
                            quantity
                        )
                    },
                    variant = BloomButtonVariant.ACCENT,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
