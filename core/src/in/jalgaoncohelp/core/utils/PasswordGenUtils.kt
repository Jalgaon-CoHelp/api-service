package `in`.jalgaoncohelp.core.utils

fun generatePassword(): String {
    val chars = 'A'..'Z'
    val nums = 0..9

    // Builds Password of pattern: AA2222
    return buildString {
        repeat(2) { append(chars.random()) }
        repeat(4) { append(nums.random()) }
    }
}