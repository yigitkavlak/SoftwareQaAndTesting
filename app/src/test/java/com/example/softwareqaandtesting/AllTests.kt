package com.example.softwareqaandtesting

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    CalculationTypeTest::class,
    CalculatorViewModelUnitTest::class,
    LoginUnitTest::class,
    RegisterViewModelUnitTest::class,
    EmailParametrizedTest::class,
    NameAndSurnameParametrizedTest::class,
    PasswordParametrizedTest::class
)
class AllTests {
}