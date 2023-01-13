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
package com.factory;

import com.config.ConfigFactory;
import com.config.FrameworkConfig;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public final class BrowserContextFactory {

  private BrowserContextFactory() {
  }

  private static final Map<String, Function<Playwright, Supplier<Browser>>> BROWSER_MAP = new HashMap<>();
  private static final FrameworkConfig CONFIG = ConfigFactory.getConfig();
  private static final String BROWSER = CONFIG.browser().toLowerCase();
  private static final boolean IS_HEADLESS = CONFIG.headless();

  static {
    BROWSER_MAP.put("firefox", playwright -> () -> playwright.firefox().launch(getLaunchOptions()));
    BROWSER_MAP.put("chrome", playwright -> () -> playwright.chromium().launch(getLaunchOptions()));
  }

  public static BrowserContext getBrowserContext(Playwright playwright) {
    return BROWSER_MAP.get(BROWSER).apply(playwright).get().newContext();
  }

  public static BrowserContext getExistingBrowserContext(Playwright playwright) {
    return BROWSER_MAP.get(BROWSER).apply(playwright)
        .get()
        .newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("reusable-login-state.json")));
  }

  private static BrowserType.LaunchOptions getLaunchOptions() {
    return new BrowserType.LaunchOptions().setHeadless(IS_HEADLESS).setChannel(BROWSER);
  }

}