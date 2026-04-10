package com.architecture.agentic.agents;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

public class SynthesisAgent {

    private final ChatLanguageModel model;

    public SynthesisAgent(String apiKey, String modelName) {
        // Temperature 0.2 allows slight creativity for variable naming while keeping syntax strict
        this.model = OpenAiChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .temperature(0.2)
                .build();
    }

    /**
     * Synthesizes the final Playwright Java code block based on the action and locator.
     */
    public String generatePlaywrightCode(String locator, String actionIntent) {
        String prompt = String.format("""
            You are a Senior Java SDET. Generate a clean, modern Playwright Java code snippet based on the provided locator and intent.
            
            Rule 1: Use proper Java Playwright API syntax.
            Rule 2: Include a brief, professional inline comment explaining the action.
            Rule 3: Return ONLY the raw Java code. No markdown blocks, no explanations.
            
            Locator: %s
            Action Intent: %s
            """, locator, actionIntent);

        return model.generate(prompt);
    }
}
