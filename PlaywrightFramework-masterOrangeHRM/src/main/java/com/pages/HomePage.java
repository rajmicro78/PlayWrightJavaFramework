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
package com.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HomePage {

  private final Page page;

  private static final String PIM = "text=PIM";
  private static final String EMPLOYEE_INFORMATION = "//h5";
  private static final String ADD_EMPLOYEE_BUTTON = "text='Add Employee'";

  public HomePage(Page page) {
    this.page = page;
  }

  public HomePage clickPIMMenu() {
    page.locator(PIM)
        .first()
        .click();
    return this;
  }

  public AddEmployeesPage navigateToAddEmployeePage() {
    page.locator(ADD_EMPLOYEE_BUTTON).first().click(new Locator.ClickOptions().setForce(true));
    return new AddEmployeesPage(page);
  }

  public void verifyEmployeeInformationTextIsDisplayed() {
    assertThat(page.locator(EMPLOYEE_INFORMATION))
        .containsText("Employee Information");
  }
}