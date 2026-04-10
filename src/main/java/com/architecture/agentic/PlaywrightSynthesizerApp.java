package com.architecture.agentic;

import com.architecture.agentic.agents.LocatorAgent;
import com.architecture.agentic.agents.SynthesisAgent;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlaywrightSynthesizerApp {

    // Initialize Log4j2 Logger
    private static final Logger logger = LogManager.getLogger(PlaywrightSynthesizerApp.class);

    public static void main(String[] args) {
        logger.info("Starting Java Agentic DOM-to-Playwright Synthesizer...");

        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("OPENAI_API_KEY");
        String modelName = dotenv.get("MODEL_NAME", "gpt-4o");

        if (apiKey == null || apiKey.isEmpty() || apiKey.equals("your_openai_api_key_here")) {
            logger.fatal("Missing OPENAI_API_KEY in .env file. Shutting down system.");
            System.exit(1);
        }

        // Initialize Agents
        logger.debug("Initializing AI Agents with model: {}", modelName);
        LocatorAgent locatorAgent = new LocatorAgent(apiKey, modelName);
        SynthesisAgent synthesisAgent = new SynthesisAgent(apiKey, modelName);

        // Mock Input Data
        String mockDomSnippet = """
            <div class="login-container">
                <input type="text" id="user" name="username" placeholder="Enter Email" />
                <input type="password" id="pass" name="password" />
                <button class="btn btn-primary" data-testid="login-submit-btn">Secure Login</button>
            </div>
            """;
        String targetElement = "The login button";
        String userIntent = "Click the login button and wait for the dashboard to load";

        try {
            logger.info("Step 1: Locator Agent analyzing DOM...");
            String optimizedLocator = locatorAgent.identifyResilientSelector(mockDomSnippet, targetElement);
            logger.info("Optimized Java Locator Identified: {}", optimizedLocator);

            logger.info("Step 2: Synthesis Agent generating Playwright Java code...");
            String generatedCode = synthesisAgent.generatePlaywrightCode(optimizedLocator, userIntent);
            
            logger.info("\n================ GENERATED PLAYWRIGHT JAVA CODE ================\n{}\n================================================================", generatedCode);

        } catch (Exception e) {
            logger.error("Agentic Workflow Failed due to an exception", e);
        }
    }
  
}
