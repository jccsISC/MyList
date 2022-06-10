package com.jccsisc.mylist.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.jccsisc.mylist.R
import java.lang.Exception

inline fun <reified T : Activity> Activity.goToActivity(noinline init: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    intent.init()
    startActivity(intent)
}

fun View.showView(showView: Boolean = true) {
    if (showView) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}


fun Fragment.showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this.requireContext(), msg, duration).show()
}


/**
 * Cambiar background a una view ya sea color o drawable
 * */
fun View.changeBgOrDrawableView(
    drawable: Int = R.drawable.shape_default,
    color: Int = R.color.black
) {
    this.apply {
        when (this) {
            is CardView -> {
                setCardBackgroundColor(ContextCompat.getColor(this.context, color))
            }
            is TextView ->
                if (drawable != R.drawable.shape_default) {
                    setBackgroundResource(drawable)
                } else {
                    setBackgroundColor(ContextCompat.getColor(this.context, color))
                }
            is ConstraintLayout ->
                if (drawable != R.drawable.shape_default) {
                    setBackgroundResource(drawable)
                } else {
                    setBackgroundColor(ContextCompat.getColor(this.context, color))
                }
            is LinearLayout ->
                if (drawable != R.drawable.shape_default) {
                    setBackgroundResource(drawable)
                } else {
                    setBackgroundColor(ContextCompat.getColor(this.context, color))
                }
            is Button ->
                if (drawable != R.drawable.shape_default) {
                    setBackgroundResource(drawable)
                    setTextColor(resources.getColor(color))
                } else {
                    setBackgroundColor(ContextCompat.getColor(this.context, color))
                    setTextColor(ContextCompat.getColor(this.context, color))
                }
            else ->
                if (drawable != R.drawable.shape_default) {
                    setBackgroundResource(drawable)
                } else {
                    setBackgroundColor(ContextCompat.getColor(this.context, color))
                }
        }
    }
}

fun View.setOnSingleClickListener(doOnClick: ((View) -> Unit)) =
    setOnClickListener(OnSingleClickListener(doOnClick))

/**
 * Cambiar color del texto TextView
 * */
fun TextView.changeTextColor(color: Int) {
    this.setTextColor(ContextCompat.getColor(this.context, color))
}

fun setColor(context: Context, color: Int) = run {
    ContextCompat.getColor(context, color)
}

/**
 * Esta función te permita cambiar el color de los caracteres que desees
 * de una cadena, solo se le asigna a la cadena, el color y el rango
 *
 * Ejemplo: "Hola mundo de nuevo".conbineColorInText(RED, 5, 10, style(bold, normal))
 * resultado: la palabra mundo en blanco
 * */
fun String.conbineColorInText(color: Int, start: Int, end: Int, style: Int? = Typeface.NORMAL) =
    run {
        val spannable = SpannableStringBuilder(this)
        spannable.setSpan(
            ForegroundColorSpan(color),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        if (style != null) {
            spannable.setSpan(StyleSpan(style), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        spannable
    }

/**
 * Limpiar campos
 * */
fun EditText.cleanText() = this.text.toString().trim().trimIndent()

/**
 * Cambia de color el navBar
 * Pasar parametro:
 * 1.-Color blanco con texto negro: Color.WHITE //el android
 * 2.-Color transparente texto blanco: Color.TRANSPARENT //el de android
 * 3.-Cualquier otro color: R.color.MyColor //muestra la barra de dicho color y el texto blanco
 * 4.-Color del texto: 0 = White, 1 = Black
 * */
/**
 * Color del texto en la barra de notificaciones
 * */
const val WHITE_COLOR_TEXT = 0
const val BLACK_COLOR_TEXT = 1
const val BLACK_TEXT_COLOR_NAV_BAR = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
const val WHITE_TEXT_COLOR_NAV_BAR = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
fun Activity.setColorNavBar(colorStatusBar: Int, fullScreen: Boolean = false, colorTextWhiteBlack: Int = 0) {
    this.apply {
        window.apply {
            when (colorStatusBar) {
                Color.WHITE -> {
                    statusBarColor = colorStatusBar
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                Color.BLACK -> {
                    statusBarColor = colorStatusBar
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                Color.BLUE -> {
                    statusBarColor = colorStatusBar
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                Color.RED -> {
                    statusBarColor = colorStatusBar
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                Color.GREEN -> {
                    statusBarColor = colorStatusBar
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                Color.GRAY -> {
                    statusBarColor = colorStatusBar
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
                Color.TRANSPARENT -> {
                    clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                    addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                    decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    statusBarColor = colorStatusBar
                }
                else -> {
                    try {
                        if (fullScreen) {
                            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            statusBarColor = setColor(context, colorStatusBar)
                        } else {
                            when(colorTextWhiteBlack) {
                                WHITE_COLOR_TEXT -> {
                                    //texto blanco
                                    clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                                    addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
                                    statusBarColor = setColor(context, colorStatusBar)
                                    decorView.systemUiVisibility = WHITE_TEXT_COLOR_NAV_BAR
                                }
                                BLACK_COLOR_TEXT -> {
                                    //texto negro
                                    clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                                    addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
                                    statusBarColor = setColor(context, colorStatusBar)
                                    decorView.systemUiVisibility = BLACK_TEXT_COLOR_NAV_BAR
                                }
                            }
                        }
                    } catch (e: Exception) {
                        e
                        Log.e(
                            "error",
                            "El color es nativo, no está mapeado a la lista de colores nativos, favor de agregar, de lo contrario se espera un color en Hexadecimal"
                        )
                    }
                }
            }
        }
    }
}