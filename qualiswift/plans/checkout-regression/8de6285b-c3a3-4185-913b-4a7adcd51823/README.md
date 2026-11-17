Qualiswift approved plan: Test plan
Version 2 · SHA-256 da06f0e698c629e27d1a644b96a1754c8a867e93fac91496f47eaeaf5b8896a4
1. Verify login page loads successfully
Case ID: LOGIN_001
Preconditions: Application URL is available
1. 1. Open the application URL.
Step Test Data: N/A
2. https://opensource-demo.orangehrmlive.com/web/index.php/auth/login
Step Expected: Login page should load successfully with username, password, Login button and relevant links.
Step Test Data: N/A
Test Data: N/A
Expected: Login page should load successfully with username, password, Login button and relevant links.
2. Verify login with valid credentials
Case ID: LOGIN_002
Preconditions: User account exists and is active
1. 1. Enter valid username.
Step Test Data: Username: $ADMIN_USERNAME
Password: $ADMIN_PASSWORD
2. 2. Enter valid password.
Step Test Data: Username: $ADMIN_USERNAME
Password: $ADMIN_PASSWORD
3. 3. Click Login.
Step Expected: User should be authenticated and redirected to the home/dashboard page.
Step Test Data: Username: $ADMIN_USERNAME
Password: $ADMIN_PASSWORD
Test Data: Username: $ADMIN_USERNAME
Password: $ADMIN_PASSWORD
Expected: User should be authenticated and redirected to the home/dashboard page.
3. Verify login with invalid username
Case ID: LOGIN_003
Preconditions: Login page is open
1. 1. Enter an unregistered username.
Step Test Data: Username: unknown_user
Password: $ADMIN_PASSWORD
2. 2. Enter a valid-format password.
Step Test Data: Username: unknown_user
Password: $ADMIN_PASSWORD
3. 3. Click Login.
Step Expected: Login should fail and a suitable error message should be displayed.
Step Test Data: Username: unknown_user
Password: $ADMIN_PASSWORD
Test Data: Username: unknown_user
Password: $ADMIN_PASSWORD
Expected: Login should fail and a suitable error message should be displayed.
4. Verify login with invalid password
Case ID: LOGIN_004
Preconditions: A valid user account exists
1. 1. Enter a valid username.
Step Test Data: Username: $ADMIN_USERNAME
Password: Wrong@123
2. 2. Enter an incorrect password.
Step Test Data: Username: $ADMIN_USERNAME
Password: Wrong@123
3. 3. Click Login.
Step Expected: Login should fail and a suitable error message should be displayed.
Step Test Data: Username: $ADMIN_USERNAME
Password: Wrong@123
Test Data: Username: $ADMIN_USERNAME
Password: Wrong@123
Expected: Login should fail and a suitable error message should be displayed.
5. Verify login with both fields blank
Case ID: LOGIN_005
Preconditions: Login page is open
1. 1. Leave username and password blank.
Step Test Data: Username: blank
Password: blank
2. 2. Click Login.
Step Expected: Required-field validation messages should be displayed and login should not proceed.
Step Test Data: Username: blank
Password: blank
Test Data: Username: blank
Password: blank
Expected: Required-field validation messages should be displayed and login should not proceed.
6. Verify username field mandatory validation
Case ID: LOGIN_006
Preconditions: Login page is open
1. 1. Leave username blank.
Step Test Data: Username: blank
Password: $ADMIN_PASSWORD
2. 2. Enter a valid password.
Step Test Data: Username: blank
Password: $ADMIN_PASSWORD
3. 3. Click Login.
Step Expected: A required validation message should be displayed for username.
Step Test Data: Username: blank
Password: $ADMIN_PASSWORD
Test Data: Username: blank
Password: $ADMIN_PASSWORD
Expected: A required validation message should be displayed for username.
7. Verify password field mandatory validation
Case ID: LOGIN_007
Preconditions: Login page is open
1. 1. Enter a valid username.
Step Test Data: Username: valid_user
Password:
2. 2. Leave password blank.
Step Test Data: Username: valid_user
Password:
3. 3. Click Login.
Step Expected: A required validation message should be displayed for password.
Step Test Data: Username: valid_user
Password:
Test Data: Username: valid_user
Password:
Expected: A required validation message should be displayed for password.
8. Verify password is masked by default
Case ID: LOGIN_008
Preconditions: Login page is open
1. 1. Enter any password in the password field.
Step Expected: Password characters should be masked (for example, displayed as dots or bullets).
Step Test Data: Password: $ADMIN_PASSWORD
Test Data: Password: $ADMIN_PASSWORD
Expected: Password characters should be masked (for example, displayed as dots or bullets).
9. Verify show/hide password option
Case ID: LOGIN_009
Preconditions: Login page is open and password visibility control is available
1. 1. Enter a password.
Step Test Data: Password: $ADMIN_PASSWORD
2. 2. Click the show-password control.
Step Test Data: Password: $ADMIN_PASSWORD
3. 3. Click it again.
Step Expected: Password should become readable when shown and masked again when hidden.
Step Test Data: Password: $ADMIN_PASSWORD
Test Data: Password: $ADMIN_PASSWORD
Expected: Password should become readable when shown and masked again when hidden.
10. Verify username field accepts valid input
Case ID: LOGIN_010
Preconditions: Login page is open
1. 1. Enter a valid username in the username field.
Step Expected: The username should be accepted according to the field's defined format and length rules.
Step Test Data: Username: $ADMIN_USERNAME
Test Data: Username: $ADMIN_USERNAME
Expected: The username should be accepted according to the field's defined format and length rules.
11. Verify username field trims leading/trailing spaces
Case ID: LOGIN_011
Preconditions: Login page is open; account exists
1. 1. Enter the valid username with leading/trailing spaces.
Step Test Data: Username:  $ADMIN_USERNAME
Password: admin@123
2. 2. Enter the valid password.
Step Test Data: Username:  $ADMIN_USERNAME
Password: admin@123
3. 3. Click Login.
Step Expected: The application should handle surrounding spaces according to requirements; it should not cause unintended authentication failure.
Step Test Data: Username:  $ADMIN_USERNAME
Password: admin@123
Test Data: Username:  $ADMIN_USERNAME
Password: admin@123
Expected: The application should handle surrounding spaces according to requirements; it should not cause unintended authentication failure.
12. Verify password is case-sensitive
Case ID: LOGIN_012
Preconditions: A valid user account exists
1. 1. Enter a valid username.
Step Test Data: Username: valid_user
Password: AdMin@123
2. 2. Enter the correct password with different letter casing.
Step Test Data: Username: valid_user
Password: AdMin@123
3. 3. Click Login.
Step Expected: Login should fail if the password does not exactly match the registered password.
Step Test Data: Username: valid_user
Password: AdMin@123
Test Data: Username: valid_user
Password: AdMin@123
Expected: Login should fail if the password does not exactly match the registered password.
13. Verify Forgot Password navigation
Case ID: LOGIN_13
Preconditions: Login page is open
1. 1. Click the Forgot Password link.
Step Expected: User should be redirected to the password recovery page or flow.
Step Test Data: N/A
Test Data: N/A
Expected: User should be redirected to the password recovery page or flow.
