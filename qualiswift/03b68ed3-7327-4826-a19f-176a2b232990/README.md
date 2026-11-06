# OrangeHRM Playwright Tests

This project contains Playwright test scripts to validate the login functionality of the OrangeHRM demo application.

## Setup

1. Ensure [Node.js](https://nodejs.org/) is installed (v14+ recommended).
2. Clone this repository.
3. Install dependencies:

```bash
npm install
```

4. Configure environment variables (create a `.env` file or export in your environment):

- `BASE_URL` - The base URL of the OrangeHRM application, e.g., `https://opensource-demo.orangehrmlive.com/web/index.php/auth/login`
- `ADMIN_USERNAME` - The admin username (default fallback used if not set).
- `ADMIN_PASSWORD` - The admin password (default fallback used if not set).

## Running Tests

To run the tests against the defined `BASE_URL`:

```bash
npx playwright test
```

## Project Structure

- `playwright.config.ts` - Playwright configuration file.
- `tests/qualiswift.spec.ts` - Playwright tests with Page Object Model embedded.

## Notes

- Screenshots, videos, and trace files are captured only on test failures.
- Credentials default to placeholders if environment variables are not set.
- Tests cover login page presence, success and failure scenarios, field validations, password masking, and navigation.
