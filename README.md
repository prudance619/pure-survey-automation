# Pure Survey — Selenium Automation Framework

Beginner-friendly test automation for the **Pure Survey | Platform** web app  
(https://survey.co.za/app/login).

Built with **Java**, **Selenium WebDriver**, **Maven**, **TestNG**, and **Allure** reports.

---

## What this project does

- Logs into Pure Survey with credentials from `config.properties`
- Smoke-tests **Login**, **Dashboard**, **Surveys**, **Distribution**, **Compliance**, **Integrations**, and **Administration** modules
- Uses the **Page Object Model (POM)** with **PageFactory**
- Waits for elements with **WebDriverWait** (no `Thread.sleep` in tests)
- Saves a **screenshot** when a test fails
- Generates an **Allure** HTML report

For QA notes and suggested test scenarios, see [docs/QA_APPLICATION_ANALYSIS.md](docs/QA_APPLICATION_ANALYSIS.md).

---

## Project structure

```
Automate 2/
├── pom.xml                          # Maven dependencies and plugins
├── README.md                        # This file
├── docs/
│   └── QA_APPLICATION_ANALYSIS.md   # QA exploration notes
├── screenshots/                     # Created when a test fails
└── src/test/
    ├── resources/
    │   ├── config.properties        # URL, browser, username, password
    │   ├── testng.xml               # Test suite (which tests to run)
    │   └── allure.properties
    └── java/za/puresurvey/
        ├── base/          BaseTest.java
        ├── config/        ConfigReader.java
        ├── listeners/     TestListener.java (screenshots on failure)
        ├── utils/         DriverFactory, WaitHelper, ScreenshotHelper
        ├── pages/         Page objects (one package per module)
        │   ├── login/           LoginPage
        │   ├── dashboard/       DashboardPage
        │   ├── surveys/         SurveysPage, QuestionBankPage, ...
        │   ├── distribution/    DistributionListPage, PanelsPage, ...
        │   ├── compliance/      ConsentRecordsPage, AuditLogPage, ...
        │   ├── integration/     AutomationsPage, WebhooksPage, ...
        │   └── administration/  UsersPage, OrgUnitsPage, SettingsPage
        ├── tests/         Test classes grouped by module
        └── explore/       AppExplorer.java (optional manual exploration tool)
```

---

## Prerequisites

1. **Java JDK 17+** — check: `java -version`
2. **Apache Maven** — check: `mvn -version`
3. **Google Chrome** (default browser) — WebDriverManager downloads the matching driver automatically

---

## Configuration

Edit `src/test/resources/config.properties`:

| Property | Example | Purpose |
|----------|---------|---------|
| `base.url` | `https://survey.co.za/app/login` | Login page URL |
| `browser` | `chrome` | `chrome`, `firefox`, or `edge` |
| `username` | your email | Login email |
| `password` | your password | Login password |
| `timeout.seconds` | `25` | Max wait time for elements |

---

## Install dependencies

Open a terminal in this project folder and run:

```bash
mvn clean test-compile
```

Maven downloads Selenium, TestNG, Allure, and WebDriverManager on the first run.

---

## Run tests

**Run the full suite:**

```bash
mvn clean test
```

**Run one test class:**

```bash
mvn test -Dtest=LoginTest
```

**Run one test method:**

```bash
mvn test -Dtest=LoginTest#validLoginOpensDashboard
```

Tests open a real browser window, log in, and perform actions. Do not use the machine while tests run if that causes focus issues.

---

## View reports

### TestNG report (built-in)

After `mvn test`, open:

```
target/surefire-reports/index.html
```

### Allure report (recommended)

1. Run tests: `mvn clean test`
2. Generate and open the report:

```bash
mvn allure:serve
```

This opens Allure in your browser with steps, descriptions, and failure details.

To only generate HTML without opening the browser:

```bash
mvn allure:report
```

Report output: `target/site/allure-maven-plugin/index.html`

---

## Screenshots on failure

Failed tests save PNG files under:

```
screenshots/<testMethodName>_<timestamp>.png
```

---

## Optional: explore the app manually

`AppExplorer` logs into the app and writes UI details to `target/live-exploration.txt`:

```bash
mvn test-compile exec:java "-Dexec.mainClass=za.puresurvey.explore.AppExplorer" "-Dexec.classpathScope=test"
```

---

## Tips for beginners

1. **Start with one test** — run `LoginTest` first to confirm credentials and browser work.
2. **Read page classes before tests** — each test calls page methods; follow the flow in the test file.
3. **Use WaitHelper** — never add `Thread.sleep` in new code; use `waitForVisible`, `safeClick`, etc.
4. **Update locators in page classes only** — if the UI changes, fix the `@FindBy` in the relevant page, not in every test.
5. **Check TODO comments** — some flows need manual QA confirmation before stronger assertions are added.

---

## Troubleshooting

| Problem | What to try |
|---------|-------------|
| Login fails | Verify username/password in `config.properties` and that the site is reachable |
| Chrome/driver mismatch | Update Chrome or run `mvn clean test` again (WebDriverManager refreshes the driver) |
| Element not found | UI may have changed — re-run `AppExplorer` and update the page object locator |
| Onboarding popup blocks clicks | `dismissOnboardingIfPresent()` runs on most pages; report if a new popup appears |

---

## License / credentials

Do not commit real passwords to public Git repositories. Use `config.properties` locally or environment-specific config files.


Mostly “does the page load?”

Login works (valid user reaches dashboard)
Expected page heading appears (e.g. “Surveys”, “Panels”)
URL is roughly correct (/app/surveys, /app/lists, etc.)
Some key elements are visible (table headers, search box, New Survey, Filters)
A little “can I interact without crashing?”

Status filter tabs can be clicked (not that the list filters correctly)
Search field accepts text (not that results are right)
Create Survey opens a panel (not that a survey is created)
Not really tested

Business logic (publish survey, add question, create distribution list)
Data correctness (row counts, filter results, reports)
Form validation details (except “bad password stays on login”)
Full workflows from start to finish
