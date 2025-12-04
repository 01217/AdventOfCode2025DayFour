import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class Test {
    @Test
    @DisplayName("Task 1 Test")
    fun taskOneTest() {
        val expectedResult = 13

        Assertions.assertEquals(expectedResult, taskOne("inp/test.in"))
    }

    @Test
    @DisplayName("Task 2 Test")
    fun taskTwoTest() {
        val expectedResult = 43

        Assertions.assertEquals(expectedResult, taskTwo("inp/test.in"))
    }
}