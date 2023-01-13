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
package com.manager;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import static java.util.Objects.isNull;

public final class PlaywrightManager {

  private PlaywrightManager() {
  }

  private static final ThreadLocal<Playwright> PLAYWRIGHT_THREAD_LOCAL = ThreadLocal.withInitial(Playwright::create);
  private static final ThreadLocal<BrowserContext> BROWSER_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();
  private static final ThreadLocal<Page> PAGE_THREAD_LOCAL = new ThreadLocal<>();

  static Playwright getPlaywrightInstance() {
    return PLAYWRIGHT_THREAD_LOCAL.get();
  }

  public static BrowserContext getBrowserContext() {
    return BROWSER_CONTEXT_THREAD_LOCAL.get();
  }

  static void setBrowserContext(BrowserContext browserContext) {
    BROWSER_CONTEXT_THREAD_LOCAL.set(browserContext);
  }

  public static Page getPage() {
    if (isNull(PAGE_THREAD_LOCAL.get())) {
      setPage();
    }
    return PAGE_THREAD_LOCAL.get();
  }

  static void setPage() {
    PAGE_THREAD_LOCAL.set(getBrowserContext().newPage());
  }

  public static void cleanUp() {
    BROWSER_CONTEXT_THREAD_LOCAL.get().close();
    PLAYWRIGHT_THREAD_LOCAL.get().close();
    BROWSER_CONTEXT_THREAD_LOCAL.remove();
    PAGE_THREAD_LOCAL.remove();
    PLAYWRIGHT_THREAD_LOCAL.remove();
  }
}