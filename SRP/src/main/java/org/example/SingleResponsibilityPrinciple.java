package org.example;

/**
 * SRP IMPLEMENTATION
 * * Each class here has exactly ONE reason to change:
 * 1. AccountService: Business logic changes.
 * 2. AccountRepository: Database schema/logic changes.
 * 3. AccountNotifier: Notification/Email provider changes.
 */

class AccountService {
    public void openAccount() {
        System.out.println("Processing account internal business logic...");
    }
}

class AccountRepository {
    public void saveAccount() {
        System.out.println("Storing account object in database...");
    }
}

class AccountNotifier {
    public void sendWelcomeMessage() {
        System.out.println("Sending out welcome message via SMTP/SMS...");
    }
}

public class SingleResponsibilityPrinciple {
    public static void main(String[] args) {
        // Orchestration
        AccountService service = new AccountService();
        AccountRepository repository = new AccountRepository();
        AccountNotifier notifier = new AccountNotifier();

        service.openAccount();    // Responsibility: Business Rules
        repository.saveAccount(); // Responsibility: Persistence
        notifier.sendWelcomeMessage(); // Responsibility: Communication
    }
}