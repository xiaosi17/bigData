import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testShow() {
        // Arrange
        MyClass myClass = new MyClass();
        myClass.name = "TestName"; // 假设 name 是可访问的，或者通过构造函数/setter 设置

        // Act
        myClass.show();

        // Assert
        assertEquals("TestName\r\n", outContent.toString());
    }

    // 假设的 MyClass 类
    static class MyClass {
        String name; // 为了测试目的，假设 name 是包私有的或公共的

        public void show() {
            System.out.println(this.name);
        }
    }
}