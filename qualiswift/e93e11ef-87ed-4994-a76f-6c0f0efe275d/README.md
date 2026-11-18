# OrangeHRM Login Page Automated Tests with Playwright

This project contains automated tests for the OrangeHRM login page using Playwright with TypeScript.

## Requirements
- Node.js version 14 or higher.
- Playwright installed as a dependency.

## Setup
1. Install dependencies:
```sh
npm install
```

2. Set environment variables:
- `BASE_URL` - Base URL of the OrangeHRM application (e.g., https://opensource-demo.orangehrmlive.com/web/index.php/auth/login)
- `ADMIN_USERNAME` - The valid username (default: `Admin`)
- `ADMIN_PASSWORD` - The valid password (default: `admin123`)

Example (Linux/macOS):
```sh
export BASE_URL=https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
export ADMIN_USERNAME=Admin
export ADMIN_PASSWORD=admin123
```

3. Run tests:
```sh
npx playwright test
```

## Test Configuration
- Tests are located in `tests/qualiswift.spec.ts`.
- Test artifacts such as screenshots, videos, and traces are saved only on failure.
- Tests use the base URL set from the `BASE_URL` environment variable.

## Notes
- Tests cover login functionality including validation, authentication, password visibility toggle, and navigation.
- Make sure the application URL is reachable and stable prior to running the tests.
