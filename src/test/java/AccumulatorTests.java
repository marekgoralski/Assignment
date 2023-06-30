import assignments.AccumulatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccumulatorTests {

    AccumulatorImpl accumulator;

    @BeforeEach
    void setUp() {
        accumulator = new AccumulatorImpl();

    }

    @Test
    @DisplayName("Simple multiplication should work")
    void testSimpleAdd() throws ExecutionException, InterruptedException {
        assertEquals(9, accumulator.accumulate(4, 5),
                "Summarry of 2 numbers");
    }

    @Test
    void testGetTotal() throws ExecutionException, InterruptedException {
        accumulator.accumulate(4, 5);
        assertEquals(9, accumulator.getTotal(), "Should be 9");
        accumulator.accumulate(2);
        assertEquals(11, accumulator.getTotal(), "Should be 11 after adding 2");
    }

    @Test
    void testNULL() throws ExecutionException, InterruptedException {
        accumulator.accumulate(null);
        assertEquals(0, accumulator.getTotal(), "Should return 0 as no values passed");
    }

    @Test
    void testNegatives() throws ExecutionException, InterruptedException {
        accumulator.accumulate(2, 3, 5, -10);
        assertEquals(0, accumulator.getTotal(), "Should return 0 ");
    }

    @Test
    void testIntegerMaxValue() {
        Throwable exception = assertThrows(ExecutionException.class, () -> accumulator.accumulate(2147483647, 1));
        assertEquals("java.lang.ArithmeticException: integer overflow", exception.getMessage());
    }

    @Test
    void testIntegerMinValue() {
        Throwable exception = assertThrows(ExecutionException.class, () -> accumulator.accumulate(-2147483647, -213431));
        assertEquals("java.lang.ArithmeticException: integer overflow", exception.getMessage());
    }

    @Test
    void testEmptyValues() throws ExecutionException, InterruptedException {
        accumulator.accumulate();
        assertEquals(0, accumulator.getTotal(), "Should return 0 ");
    }

    @Test
    void testReset() throws ExecutionException, InterruptedException {
        accumulator.accumulate(1, 24312, 3456, 5678, 456, 68797, 4567, 3454, 4576, 4567865, 4567, 4356, 345, 2345, 32234, 21);
        accumulator.accumulate(1, 24312, 3456, 5678, 456, 68797, 4567, 3454, 4576, 4567865, 4567, 4356, 345, 2345, 32234, 21);
        assertEquals(9454060, accumulator.getTotal(), "Should return 9454060 ");
        accumulator.reset();
        assertEquals(0, accumulator.getTotal(), "Should return 0 ");
    }
}
