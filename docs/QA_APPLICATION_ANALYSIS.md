# Pure Survey Platform — QA Application Analysis

**Application:** Pure Survey | Platform  
**Base URL:** https://survey.co.za/app/login  
**Explored with:** Manual QA notes + live Selenium exploration (`target/live-exploration.txt`)

---

## 1. Application overview

Pure Survey is a web-based employee survey management platform. After login, users land on a **Dashboard** with survey summaries and quick actions. The app uses:

- A **left side navigation** for Dashboard and Surveys-related modules
- A **top navigation bar** for major areas (Surveys, Distribution, Compliance, Integrations, Administration)
- **Tables, filters, and search** on list pages
- **Modals/panels** for creating surveys and questions

---

## 2. Navigation structure

### 2.1 Login

| Item | Detail |
|------|--------|
| URL | `https://survey.co.za/app/login` |
| Fields | Email, Password, optional “Remember me” checkbox |
| Action | **Sign in** button |
| Success | Redirects to `https://survey.co.za/app` (Dashboard) |

### 2.2 Dashboard (Home)

| Item | Detail |
|------|--------|
| URL | `https://survey.co.za/app` |
| Heading | Time-based greeting, e.g. “Good afternoon, Manager” |
| Side nav | Dashboard, Surveys, Question Bank, Translations, Insight Reports |
| Top nav | Surveys, Distribution, Compliance, Integrations, Administration |
| Key actions | **New Survey**, **Filters**, **Customise**, **Export PDF** |
| Onboarding | **Got it, let's go** may appear on first visit (dismiss before other actions) |

### 2.3 Surveys module (side nav under Surveys area)

| Screen | URL | Page heading |
|--------|-----|--------------|
| Surveys list | `/app/surveys` | Surveys |
| Question Bank | `/app/question-bank` | Question Bank |
| Translations | `/app/translations` | Survey Translations |
| Insight Reports | `/app/insight-reports` | Insight Reports |

**Surveys list — observed UI:**

- **Create Survey** button
- Folder sidebar (e.g. All Surveys, custom folders, New Folder)
- Status filters: **All**, **Draft**, **Active**, **Closed**, **Archived**
- Search: placeholder `Search surveys...`
- Table columns: Title, Status, Responses, Created, Actions
- Row actions (from prior exploration): Duplicate, Archive, Delete permanently, Move to folder, Save as template

**Create Survey panel** (opens from Create Survey):

- Start from Scratch, Build with AI, template categories (People & HR, etc.)
- Template search field

**Survey builder** (after creating/opening a survey):

- Tabs: Design, Responses, Report, Preview, Publish
- Add Question, Add Section, Welcome/Thank You pages
- TODO: Confirm exact publish workflow and required fields before automating publish tests.

### 2.4 Distribution module (top nav → Distribution)

Click **Distribution** in the **top** nav, then use sub-links:

| Screen | URL | Page heading |
|--------|-----|--------------|
| Distribution Lists | `/app/lists` | Distribution Lists |
| Panels | `/app/panels` | Panels |
| Email Templates | `/app/email-templates` | Email Templates |
| WhatsApp Templates | `/app/whatsapp-templates` | WhatsApp Templates |

> **Note:** The top **Surveys** button does not navigate to the surveys list; use the **left side nav** link “Surveys” instead.

### 2.5 Compliance module (top nav → Compliance)

| Screen | URL | Page heading |
|--------|-----|--------------|
| Consent Records | `/app/consent` | Consent Management *(menu says Consent Records)* |
| Opt-out Management | `/app/opt-outs` | Opt-out Management |
| Audit Log | `/app/audit-log` | Audit Log |
| Access Logs | `/app/access-logs` | Access Logs |

### 2.6 Integrations module (top nav → Integrations)

| Screen | URL | Page heading |
|--------|-----|--------------|
| Automations | `/app/event-triggers` | Automations |
| Webhooks | `/app/webhook-configs` | Webhook Endpoints *(menu says Webhooks)* |
| Data Exports | `/app/data-exports` | Data Exports |

### 2.7 Administration module (top nav → Administration)

| Screen | URL | Page heading |
|--------|-----|--------------|
| Users | `/app/users` | Users |
| Org Units | `/app/org-units` | Organisation Units |
| Settings | `/app/settings` | Settings |

---

## 3. Main user flows

1. **Login** → Enter email/password → Sign in → Dashboard
2. **View dashboard** → See greeting, recent surveys, use New Survey / Filters
3. **Browse surveys** → Side nav “Surveys” → Filter by status → Search → Open row / Create Survey
4. **Manage question library** → Question Bank → Search / Add Question / Import
5. **Manage translations** → Translations → (table content — TODO: confirm columns when data exists)
6. **View insight reports** → Insight Reports
7. **Distribution setup** → Distribution → Distribution Lists / Panels / Email / WhatsApp templates
8. **Compliance review** → Compliance → Consent / Opt-out / Audit / Access logs
9. **Integrations** → Automations / Webhooks / Data Exports
10. **Administration** → Users / Org Units / Settings

---

## 4. Suggested functional E2E test scenarios

### Login

| # | Scenario | Expected result |
|---|----------|-----------------|
| L1 | Valid login | Dashboard loads; greeting contains user role/name |
| L2 | Invalid password | Error message shown; user stays on login page |
| L3 | Empty email | Sign in blocked or validation shown (TODO: confirm exact behaviour) |

### Dashboard

| # | Scenario | Expected result |
|---|----------|-----------------|
| D1 | Dashboard loads after login | URL contains `/app`; greeting visible |
| D2 | New Survey button visible | Button present on dashboard |
| D3 | Navigate via side nav | Each link opens correct page heading |

### Surveys

| # | Scenario | Expected result |
|---|----------|-----------------|
| S1 | Surveys list loads | Heading “Surveys”; table headers visible |
| S2 | Status filter tabs | Clicking Draft/Active/etc. updates list (TODO: assert row status) |
| S3 | Search surveys | Typing in search filters visible rows |
| S4 | Open Create Survey panel | Panel shows “Start from Scratch” or similar options |
| S5 | Question Bank navigation | Heading “Question Bank” |
| S6 | Translations navigation | Heading “Survey Translations” |
| S7 | Insight Reports navigation | Heading “Insight Reports” |

### Distribution

| # | Scenario | Expected result |
|---|----------|-----------------|
| DI1 | Open Distribution Lists | Heading “Distribution Lists”; URL `/app/lists` |
| DI2 | Open Panels | Heading “Panels”; URL `/app/panels` |
| DI3 | Open Email Templates | Heading “Email Templates” |
| DI4 | Open WhatsApp Templates | Heading “WhatsApp Templates” |

### Compliance, Integrations, Administration (smoke — automated)

| # | Scenario | Expected result |
|---|----------|-----------------|
| C1–C4 | Open each Compliance sub-page | Correct URL and heading |
| I1–I3 | Open each Integrations sub-page | Correct URL and heading |
| A1–A3 | Open each Administration sub-page | Correct URL and heading |

---

## 5. Items needing manual confirmation (TODO)

- Exact text and locator for **invalid login** error message
- Whether **empty field validation** is inline or on submit
- **Create Survey → Start from Scratch** full flow (survey name field, save behaviour)
- **Publish survey** prerequisites and confirmation dialogs
- **Distribution Lists / Panels** create/edit forms and mandatory fields
- **Translations** table structure when empty vs. populated
- Whether **“Got it, let's go”** appears every session or only once per user

---

## 6. Automation coverage in this project

The framework automates smoke navigation for all modules in `src/test/resources/testng.xml` (**27 tests**). Start with navigation and smoke checks; extend with data setup tests once TODOs above are confirmed.
