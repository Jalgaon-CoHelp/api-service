package `in`.jalgaoncohelp.api.utils

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class WordCapitalizerDelegate(private var value: String) : ReadWriteProperty<Any?, String> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        if (value.isBlank()) {
            error("value is blank")
        }
        return value
            .trim()
            .split(" ")
            .joinToString(" ") { it.capitalize() }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        this.value = value
    }
}

fun capitalize(text: String) = WordCapitalizerDelegate(text)