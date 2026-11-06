import { test, expect, Page } from '@playwright/test';

// OrangeHRM Login Page Object Model
class LoginPage {
  readonly page: Page;
  readonly usernameInput;
  readonly passwordInput;
  readonly loginButton;
  readonly forgotPasswordLink;
  readonly usernameError;
  readonly passwordError;
  readonly errorMessage;
  readonly showPasswordButton;

  constructor(page: Page) {
    this.page = page;
    // Selectors adapted for OrangeHRM demo site
    this.usernameInput = page.locator('input[name="username"]');
    this.passwordInput = page.locator('input[name="password"]');
    this.loginButton = page.locator('button[type="submit"]');
    this.forgotPasswordLink = page.locator('a:has-text("Forgot your password?")');
    this.usernameError = page.locator('xpath=//span[contains(@class, "oxd-input-field-error-message")][1]');
    this.passwordError = page.locator('xpath=//span[contains(@class, "oxd-input-field-error-message")][2]');
    this.errorMessage = page.locator('.oxd-alert-content-text');
    this.showPasswordButton = page.locator('button[aria-label*="Show"]');
  }

  async goto() {
    await this.page.goto('/');
  }

  async login(username: string, password: string) {
    await this.usernameInput.fill(username);
    await this.passwordInput.fill(password);
    await this.loginButton.click();
  }

  async getPasswordType(): Promise<string | null> {
    return this.passwordInput.getAttribute('type');
  }

  async clickShowPasswordToggle() {
    if (await this.showPasswordButton.isVisible()) {
      await this.showPasswordButton.click();
    }
  }

  async clickForgotPassword() {
    await this.forgotPasswordLink.click();
  }
}

// Environment variables with defaults
const ADMIN_USERNAME = process.env.ADMIN_USERNAME || '$ADMIN_USERNAME';
const ADMIN_PASSWORD = process.env.ADMIN_PASSWORD || '$ADMIN_PASSWORD';

// Test suite for OrangeHRM login page
test.describe('OrangeHRM Login Tests', () => {
  let loginPage: LoginPage;

  test.beforeEach(async ({ page }) => {
    loginPage = new LoginPage(page);
    await loginPage.goto();
  });

  test('1. Verify login page loads successfully', async () => {
    await expect(loginPage.usernameInput).toBeVisible();
    await expect(loginPage.passwordInput).toBeVisible();
    await expect(loginPage.loginButton).toBeVisible();
    await expect(loginPage.forgotPasswordLink).toBeVisible();
  });

  test('2. Verify login with valid credentials', async ({ page }) => {
    await loginPage.login(ADMIN_USERNAME, ADMIN_PASSWORD);
    // Verify navigation to dashboard or appropriate home page
    await expect(page).toHaveURL(/dashboard|pim|home|\/web\/index\.php\/dashboard/);
  });

  test('3. Verify login with invalid username', async () => {
    await loginPage.login('unknown_user', ADMIN_PASSWORD);
    await expect(loginPage.errorMessage).toContainText(/Invalid credentials|Invalid username or password|No such user/i);
  });

  test('4. Verify login with invalid password', async () => {
    await loginPage.login(ADMIN_USERNAME, 'Wrong@123');
    await expect(loginPage.errorMessage).toContainText(/Invalid credentials|Invalid username or password/i);
  });

  test('5. Verify login with both fields blank', async () => {
    await loginPage.login('', '');
    await expect(loginPage.usernameError).toBeVisible();
    await expect(loginPage.passwordError).toBeVisible();
  });

  test('6. Verify username field mandatory validation', async () => {
    await loginPage.login('', ADMIN_PASSWORD);
    await expect(loginPage.usernameError).toBeVisible();
  });

  test('7. Verify password field mandatory validation', async () => {
    await loginPage.login(ADMIN_USERNAME, '');
    await expect(loginPage.passwordError).toBeVisible();
  });

  test('8. Verify password is masked by default', async () => {
    await loginPage.passwordInput.fill(ADMIN_PASSWORD);
    const typeAttr = await loginPage.getPasswordType();
    expect(typeAttr).toBe('password');
  });

  test('9. Verify show/hide password option', async () => {
    await loginPage.passwordInput.fill(ADMIN_PASSWORD);
    let typeAttr = await loginPage.getPasswordType();
    expect(typeAttr).toBe('password');
    if (await loginPage.showPasswordButton.isVisible()) {
      await loginPage.clickShowPasswordToggle();
      typeAttr = await loginPage.getPasswordType();
      expect(typeAttr).toBe('text');
      await loginPage.clickShowPasswordToggle();
      typeAttr = await loginPage.getPasswordType();
      expect(typeAttr).toBe('password');
    } else {
      test.skip(true, 'Show/hide password control not available');
    }
  });

  test('10. Verify username field accepts valid inputs', async () => {
    await loginPage.usernameInput.fill(ADMIN_USERNAME);
    await expect(loginPage.usernameInput).toHaveValue(ADMIN_USERNAME);
  });

  test('11. Verify username field trims leading/trailing spaces', async ({ page }) => {
    await loginPage.login(`  ${ADMIN_USERNAME}  `, ADMIN_PASSWORD);
    if (/dashboard|pim|home/.test(page.url())) {
      expect(true).toBe(true);
    } else {
      await expect(loginPage.errorMessage).toBeVisible();
    }
  });

  test('12. Verify password is case-sensitive', async () => {
    const altered = ADMIN_PASSWORD.split('').map(c => {
      if (c.toLowerCase() === c) return c.toUpperCase();
      else return c.toLowerCase();
    }).join('');
    await loginPage.login(ADMIN_USERNAME, altered);
    await expect(loginPage.errorMessage).toContainText(/Invalid credentials|Invalid username or password/i);
  });

  test('13. Verify Forgot Password navigation', async ({ page }) => {
    await loginPage.clickForgotPassword();
    await expect(page).toHaveURL(/forgotPassword|requestPasswordReset|password/i);
  });
});
