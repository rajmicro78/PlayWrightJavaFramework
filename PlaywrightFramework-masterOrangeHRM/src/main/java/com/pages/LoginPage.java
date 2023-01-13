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

import com.entity.LoginDetails;
import com.manager.PlaywrightManager;
import com.microsoft.playwright.Page;

public class LoginPage {

  private final Page page;

  private static final String USERNAME = "input[name='username']";
  private static final String PASSWORD = "input[name='password']";
  private static final String LOGIN_BUTTON = "button[type='submit']";

  private LoginPage(Page page) {
    this.page = page;
  }

  public static LoginPage getInstance() {
    return new LoginPage(PlaywrightManager.getPage());
  }

  public HomePage loginToApplication(LoginDetails loginDetails) {
    page.locator(USERNAME).fill(loginDetails.getUserName());
    page.locator(PASSWORD).fill(loginDetails.getPassword());
    page.locator(LOGIN_BUTTON).click();
    return new HomePage(page);
  }
}