import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.Test
import kotlin.test.assertEquals

class PalindromeTests {
    val stringUtils = StringUtils()
    @ParameterizedTest
    @ValueSource(strings = ["aaaa", "AaAaaaA", "Radar", "My gym", "Don't nod", "No lemon, no melon"])
    fun testIsPalindrome(string: String?) {
        assertTrue(stringUtils.isPalindrome(string))
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "hello!", "horror", "Ill will", "((()))", "[))))]", "222222122222"])
    fun testIsNotPalindrome(string: String?) {
        assertFalse(stringUtils.isPalindrome(string))
    }
}

class StringUtils {
    fun isPalindrome(str: String?): Boolean {
        return str ?. let { it.reversed().equals(it, true) } ?: true
    }
}

class Main {

    @Test
    fun testMinOf() {
        val result = Calculator().minOf(2, 1)
        assertEquals(1, result)
    }
}

class SS
val SS.isPalindrome: (String?) -> Boolean
    get() = { str ->
        str?.filter { it !in "' " }?.run {
            reversed().equals(this, true) and !isNullOrBlank()
        } ?: false
    }

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CalculatorTest {

    @ParameterizedTest
    @MethodSource("argFactory")
    fun testMinOf(first: Int, second: Int, expected: Int) {
        val result = Calculator().minOf(first, second)
        assertEquals(expected, result)
    }

    private fun argFactory(): List<Arguments?>? {
        return listOf(arguments(2, 1, 1), arguments(31, 10, 10), arguments(5, 0, 0))
    }
}

class Calculator {

    fun minOf(a: Int, b: Int): Int {
        return if (a <= b) a else b
    }
}