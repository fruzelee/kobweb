package com.varabyte.kobweb.compose.css

import org.jetbrains.compose.web.css.*

// See: https://developer.mozilla.org/en-US/docs/Web/CSS/outline
@Suppress("EqualsOrHashCode")
class CSSOutline : CSSStyleValue {
    var width: CSSLengthValue? = null
    var style: LineStyle? = null
    var color: CSSColorValue? = null

    override fun equals(other: Any?): Boolean {
        return if (other is CSSOutline) {
            width == other.width && style == other.style && color == other.color
        } else false
    }

    override fun toString(): String {
        val values = listOfNotNull(color, style, width)
        return values.joinToString(" ")
    }
}

inline fun StyleBuilder.outline(crossinline outlineBuilder: CSSOutline.() -> Unit) {
    property("outline", CSSOutline().apply(outlineBuilder))
}

fun StyleBuilder.outline(
    width: CSSLengthValue? = null,
    style: LineStyle? = null,
    color: CSSColorValue? = null
) {
    outline {
        this.width = width
        this.style = style
        this.color = color
    }
}

// See: https://developer.mozilla.org/en-US/docs/Web/CSS/outline-color
class OutlineColor(val value: String) {
    companion object {
        // Keyword
        val Invert get() = OutlineColor("invert")

        // Global
        val Inherit get() = OutlineColor("inherit")
        val Initial get() = OutlineColor("initial")
        val Revert get() = OutlineColor("revert")
        val Unset get() = OutlineColor("unset")

    }
}

fun StyleBuilder.outlineColor(outlineColor: OutlineColor) {
    property("outline-color", outlineColor.value)
}

fun StyleBuilder.outlineColor(value: CSSColorValue) {
    property("outline-color", value)
}

// See: https://developer.mozilla.org/en-US/docs/Web/CSS/outline-offset
fun StyleBuilder.outlineOffset(value: CSSLengthValue) {
    property("outline-offset", value)
}

// See: https://developer.mozilla.org/en-US/docs/Web/CSS/outline-style
fun StyleBuilder.outlineStyle(value: LineStyle) {
    property("outline-style", value)
}

// See: https://developer.mozilla.org/en-US/docs/Web/CSS/outline-width
class OutlineWidth(val value: String) {
    companion object {
        // Keyword
        val Thin get() = OutlineWidth("thin")
        val Medium get() = OutlineWidth("medium")
        val Thick get() = OutlineWidth("thick")

        // Global
        val Inherit get() = OutlineWidth("inherit")
        val Initial get() = OutlineWidth("initial")
        val Revert get() = OutlineWidth("revert")
        val Unset get() = OutlineWidth("unset")
    }
}

fun StyleBuilder.outlineWidth(outlineWidth: OutlineWidth) {
    property("outline-width", outlineWidth.value)
}

fun StyleBuilder.outlineWidth(value: CSSLengthValue) {
    property("outline-width", value)
}
