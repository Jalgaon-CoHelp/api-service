package `in`.jalgaoncohelp.api.utils

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class MobileNumberDelegate(private var value: String) : ReadWriteProperty<Any?, String> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): String {
        if (value.trim().length != 10) {
            error("phone is invalid mobile number")
        }
        return this.value
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        this.value = value
    }
}

fun validMobileNumber(mobileNumber: String) = MobileNumberDelegate(mobileNumber)