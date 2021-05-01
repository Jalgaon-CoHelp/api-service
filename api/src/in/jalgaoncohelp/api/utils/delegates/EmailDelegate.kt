package `in`.jalgaoncohelp.api.utils

import java.util.regex.Pattern
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class EmailDelegate(private var value: String) : ReadWriteProperty<Any?, String> {
    private val emailPattern = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$".toPattern(Pattern.CASE_INSENSITIVE)

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        if (!emailPattern.matcher(value).matches()) {
            error("email is invalid")
        }
        return this.value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        this.value = value
    }
}

fun validEmail(email: String) = EmailDelegate(email)