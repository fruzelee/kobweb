package com.varabyte.kobweb.silk.components.forms

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.UserSelect
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.*
import com.varabyte.kobweb.compose.ui.graphics.Color
import com.varabyte.kobweb.compose.ui.unit.dp
import com.varabyte.kobweb.silk.components.ComponentKey
import com.varabyte.kobweb.silk.components.ComponentState
import com.varabyte.kobweb.silk.components.ComponentStyle
import com.varabyte.kobweb.silk.components.ComponentVariant
import com.varabyte.kobweb.silk.theme.SilkTheme
import com.varabyte.kobweb.silk.theme.colors.shifted
import com.varabyte.kobweb.silk.theme.shapes.Rect
import com.varabyte.kobweb.silk.theme.shapes.Shape
import com.varabyte.kobweb.silk.theme.shapes.clip

enum class ButtonState : ComponentState {
    DEFAULT,
    HOVER,
    PRESSED,
}

abstract class ButtonStyle : ComponentStyle<ButtonState> {
    open val color: Color?
        @Composable
        get() = null

    open val hoverColor: Color?
        @Composable
        get() = null

    open val pressedColor: Color?
        @Composable
        get() = null

    open val shape: Shape?
        @Composable
        get() = null

    @Composable
    override fun toModifier(state: ButtonState): Modifier {
        var modifier: Modifier = Modifier

        when (state) {
            ButtonState.DEFAULT -> color
            ButtonState.HOVER -> hoverColor
            ButtonState.PRESSED -> pressedColor
        }?.let { color -> modifier = modifier.background(color) }

        shape?.let { modifier = modifier.clip(it) }
        return modifier
    }
}

object ButtonKey : ComponentKey<ButtonStyle>
class BaseButtonStyle : ButtonStyle() {
    override val color: Color
        @Composable
        get() = SilkTheme.palette.primary

    override val hoverColor: Color
        @Composable
        get() = color.shifted()

    override val pressedColor: Color
        @Composable
        get() = color.shifted().shifted()

    override val shape: Shape
        @Composable
        get() = Rect(4.dp)
}

interface ButtonVariant : ComponentVariant<ButtonState, ButtonStyle>

object Buttons {
    const val LEFT = 0.toShort()
    const val MIDDLE = 1.toShort()
    const val RIGHT = 2.toShort()
}

/**
 * An area which provides a SilkTheme-aware background color.
 */
@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var state by remember { mutableStateOf(ButtonState.DEFAULT) }
    var inButton by remember { mutableStateOf(false) }
    Box(
        SilkTheme.componentStyles[ButtonKey].toModifier(state)
            .then(modifier)
            // Text shouldn't be selectable
            .userSelect(UserSelect.None)
            .onMouseEnter {
                state = ButtonState.HOVER
                inButton = true
            }
            .onMouseLeave {
                state = ButtonState.DEFAULT
                inButton = false
            }
            .onMouseDown { evt ->
                if (evt.button == Buttons.LEFT) {
                    state = ButtonState.PRESSED
                }
            }
            .onMouseUp { evt ->
                if (evt.button == Buttons.LEFT && state == ButtonState.PRESSED) {
                    onClick()
                    state = if (inButton) { ButtonState.HOVER } else { ButtonState.DEFAULT }
                }
            }
    ) {
        content()
    }
}