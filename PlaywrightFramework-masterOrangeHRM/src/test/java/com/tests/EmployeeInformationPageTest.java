/*
 * 
 *
 * Copyright (c) ATOMCTO.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * Author - Rajeev 
 * Year - 2023/01
 * Ver - 1.02
 */
package com.tests;

import com.entity.EmployeeDetails;
import com.entity.LoginDetails;
import com.pages.LoginPage;
import com.tests.base.TestSetup;
import com.tests.testdata.EmployeeTestData;
import com.tests.testdata.LoginTestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(value = ExecutionMode.CONCURRENT)
class EmployeeInformationPageTest extends TestSetup {

  private final EmployeeDetails employeeDetails = EmployeeTestData.getRandomEmployeeDetails();
  private final LoginDetails loginDetails = LoginTestData.getValidLoginDetails();

  @Test
  void testWhetherEmployeeInformationIsDisplayed() {
    LoginPage.getInstance()
        .loginToApplication(loginDetails)
        .clickPIMMenu()
        .verifyEmployeeInformationTextIsDisplayed();
  }

  @Test
  void testNewEmployeeCanBeCreated() {
    LoginPage.getInstance()
        .loginToApplication(loginDetails)
        .clickPIMMenu()
        .navigateToAddEmployeePage()
        .addNewEmployee(employeeDetails)
        .verifyWhetherEmployeeCreatedSuccessfully();
  }
}