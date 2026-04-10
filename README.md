# Agentic Playwright Synthesizer (Java & LangChain4j)

An autonomous, multi-agent AI pipeline that dynamically analyzes raw HTML/DOM structures and synthesizes resilient, production-ready Playwright Java automation scripts.

## The Architectural Problem
UI automation traditionally suffers from "selector fragility." SDETs waste hours manually inspecting the DOM, often defaulting to brittle XPaths or deeply nested CSS selectors that break during the next deployment.

## The Agentic Solution
This orchestration engine utilizes a multi-agent workflow to bring AI-driven resilience to test creation:
1. **The Locator Agent:** Analyzes the raw DOM snippet and identifies the most resilient selector based on enterprise best practices (prioritizing `data-test-id`, ARIA roles, and accessible names).
2. **The Synthesis Agent:** Takes the optimal selector and the user's plain-English intent, generating fully compliant Playwright Java code.

## Enterprise Tech Stack
* **Language:** Java 17+
* **Build Tool:** Maven
* **Automation Framework:** Playwright for Java
* **AI Orchestration:** LangChain4j & OpenAI SDK (`gpt-4o`)
* **Observability:** Log4j2

## Architecture Flow
`User Intent + HTML Snippet` -> `LocatorAgent (LangChain4j)` -> `SynthesisAgent (LangChain4j)` -> `Playwright Java Test Block`

## Sample Execution
```text
2026-04-10 23:38:12 [main] INFO  com.architecture.agentic.PlaywrightSynthesizerApp - Starting Java Agentic DOM-to-Playwright Synthesizer...
2026-04-10 23:38:13 [main] INFO  com.architecture.agentic.PlaywrightSynthesizerApp - Step 1: Locator Agent analyzing DOM...
2026-04-10 23:38:14 [main] INFO  com.architecture.agentic.PlaywrightSynthesizerApp - Optimized Java Locator Identified: page.getByTestId("login-submit-btn")
2026-04-10 23:38:14 [main] INFO  com.architecture.agentic.PlaywrightSynthesizerApp - Step 2: Synthesis Agent generating Playwright Java code...
2026-04-10 23:38:16 [main] INFO  com.architecture.agentic.PlaywrightSynthesizerApp - 
================ GENERATED PLAYWRIGHT JAVA CODE ================
// Click the login button and wait for the dashboard to load
page.getByTestId("login-submit-btn").click();
page.waitForURL("**/dashboard");
================================================================
