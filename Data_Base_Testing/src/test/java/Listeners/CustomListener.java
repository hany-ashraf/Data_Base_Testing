package Listeners;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;


public class CustomListener implements TestWatcher {

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("Test Passed: " + context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("Test Failed: " + context.getDisplayName());
        cause.printStackTrace();
    }
}
