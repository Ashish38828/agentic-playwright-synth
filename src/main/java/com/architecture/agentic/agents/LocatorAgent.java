package com.architecture.agentic.agents;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

public class LocatorAgent {

    private final ChatLanguageModel model;

    public LocatorAgent(String apiKey, String modelName) {
        // Temperature 0.1 ensures highly deterministic, analytical output for DOM parsing
        this.model = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .temperature(0.1)
                .build();
    }

    /**
     * Analyzes HTML and returns the most resilient Playwright Java selector.
     */
    public String identifyResilientSelector(String domSnippet, String targetElementDescription) {
        String prompt = String.format("""
            You are an elite QA Architect. Your job is to analyze HTML snippets and return the MOST resilient Playwright selector for the requested element.
            
            Rule 1: Prioritize user-facing attributes (getByRole, getByText, getByLabel).
            Rule 2: If accessibility attributes are missing, prioritize test IDs (getByTestId).
            Rule 3: NEVER use brittle XPaths or deeply nested CSS unless absolutely necessary.
            Rule 4: Output strictly in Java Playwright syntax (e.g., page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit"))).
            Rule 5: Return ONLY the exact Playwright locator string. Do not include markdown formatting or explanations.
            
            Target Element: %s
            
            HTML Snippet:
            %s
            """, targetElementDescription, domSnippet);

        return model.generate(prompt);
    }
}
