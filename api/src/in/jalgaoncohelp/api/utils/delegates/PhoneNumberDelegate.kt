package `in`.jalgaoncohelp.api.utils

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PhoneNumberDelegate(private var value: String) : ReadWriteProperty<Any?, String> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        if (value.trim().length <= 5) {
            error("phone number is invalid")
        }
        return this.value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        this.value = value
    }
}

fun validPhoneNumber(phoneNumber: String) = PhoneNumberDelegate(phoneNumber)