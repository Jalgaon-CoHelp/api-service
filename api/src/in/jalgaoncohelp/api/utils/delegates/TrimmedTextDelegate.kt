package `in`.jalgaoncohelp.api.utils

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class TrimmedTextDelegate(private var value: String) : ReadWriteProperty<Any?, String> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        if (value.isBlank()) {
            error("value is blank")
        }
        return this.value.trim()
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        this.value = value
    }
}

fun trimmed(text: String) = TrimmedTextDelegate(text)