package com.example.BackendVolatile;


import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.DisplayName;

@DisplayName("我的第一个测试用例")
public class FirstTest {

    @BeforeAll
    //定义了整个测试类在开始前以及结束时的操作，只能修饰静态方法，主要用于在测试过程中所需要的全局数据和外部资源的初始化和清理
    public static void init() {
        System.out.println("初始化数据");
    }

    @AfterAll
    public static void cleanup() {
        System.out.println("清理数据");
    }

    @BeforeEach
    public void tearup() {
        System.out.println("当前测试方法开始");
    }

    @AfterEach
    public void tearDown() {
        System.out.println("当前测试方法结束");
    }

    @DisplayName("我的第一个测试")
    @Test
    void testFirstTest() {
        System.out.println("我的第一个测试开始测试");
    }

    @DisplayName("我的第二个测试")
    @Disabled
    @Test
    void testSecondTest() {
        System.out.println("我的第二个测试开始测试");
    }

    @DisplayName("lambda")
    @Test
    void testGroupAssertions() {
        int[] numbers = {0, 1, 2, 3, 4};
        Assertions.assertAll("numbers",
                () -> Assertions.assertEquals(numbers[1], 1),
                () -> Assertions.assertEquals(numbers[3], 3),
                () -> Assertions.assertEquals(numbers[4], 4)
        );
    }
}
