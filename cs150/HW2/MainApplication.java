import javax.swing.*;

public class MainApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainApplication().createAndShowGUI();
        });
    }
    
    //window and login initialization
    private User userModel;
    
    private JFrame frame;
    
    private JLabel messageLabel;
   
    //welcome page
    private Greeter greeter;
    
    private JPanel welcomePanel;
    private JButton showGreetingButton;
   
    //login page
    private JTextField emailField;
    private JPasswordField passwordField;
    
    private JLabel loginMessageLabel;
    private JPanel loginPanel;
    
    private JButton loginButton;
    private JButton goToLoginButton;
    //counter page
    private JPanel counterPanel;

    private JButton buttonA;
    private JButton buttonB;
    private JButton buttonC;

    private JLabel countALabel;
    private JLabel countBLabel;
    private JLabel countCLabel;
    private JLabel totalClicksLabel;

    private CounterPanel counterModel;
    
    private void createAndShowGUI() {
        greeter = new Greeter();
        userModel = new User("test@example.com", "password123");

        frame = new JFrame("Homework 2 Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 250);

        createWelcomePanel();

        frame.setContentPane(welcomePanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createWelcomePanel() {
        welcomePanel = new JPanel(new java.awt.BorderLayout());

        messageLabel = new JLabel("Click the button to see a greeting.", SwingConstants.CENTER);
        showGreetingButton = new JButton("Show Greeting");
        goToLoginButton = new JButton("Go to Login");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(showGreetingButton);
        buttonPanel.add(goToLoginButton);

        welcomePanel.add(messageLabel, java.awt.BorderLayout.CENTER);
        welcomePanel.add(buttonPanel, java.awt.BorderLayout.SOUTH);

        addWelcomeButtonBehavior();
        addGoToLoginButtonBehavior();
    }

    private void addWelcomeButtonBehavior() {
        showGreetingButton.addActionListener(e -> {
            String greeting = greeter.getGreeting();
            messageLabel.setText(greeting);
        });
    }

    private void addGoToLoginButtonBehavior() {
        goToLoginButton.addActionListener(e -> {
            if (loginPanel == null) {
                createLoginPanel();
            }
            frame.setContentPane(loginPanel);
            frame.revalidate();
            frame.repaint();
        });
    }

    private void createLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");
        loginMessageLabel = new JLabel(" ", SwingConstants.CENTER);

        loginPanel.add(emailLabel);
        loginPanel.add(emailField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(loginMessageLabel);

        addLoginButtonBehavior();
    }

    private void addLoginButtonBehavior() {
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            boolean success = userModel.isValidLogin(email, password);

            if (success) {
                loginMessageLabel.setText("Login successful!");
                if (counterPanel == null) {
                    createCounterPanel();
                }
                frame.setContentPane(counterPanel);
                frame.revalidate();
                frame.repaint();
        }
    });
    }

    private void createCounterPanel() {
    // model
    counterModel = new CounterPanel();

    // view
    counterPanel = new JPanel();
    counterPanel.setLayout(new BoxLayout(counterPanel, BoxLayout.Y_AXIS));

    buttonA = new JButton("A");
    buttonB = new JButton("B");
    buttonC = new JButton("C");

    JPanel buttonRow = new JPanel();
    buttonRow.add(buttonA);
    buttonRow.add(buttonB);
    buttonRow.add(buttonC);

    countALabel = new JLabel("Count A: 0");
    countBLabel = new JLabel("Count B: 0");
    countCLabel = new JLabel("Count C: 0");
    totalClicksLabel = new JLabel("Total Clicks: 0");

    counterPanel.add(buttonRow);
    counterPanel.add(countALabel);
    counterPanel.add(countBLabel);
    counterPanel.add(countCLabel);
    counterPanel.add(totalClicksLabel);

    addCounterButtonBehavior();
}

    private void addCounterButtonBehavior() {
    buttonA.addActionListener(e -> {
        counterModel.clickA();
        updateCounterLabels();
    });

    buttonB.addActionListener(e -> {
        counterModel.clickB();
        updateCounterLabels();
    });

    buttonC.addActionListener(e -> {
        counterModel.clickC();
        updateCounterLabels();
    });
}

    private void updateCounterLabels() {
        countALabel.setText("Count A: " + counterModel.getCountA());
        countBLabel.setText("Count B: " + counterModel.getCountB());
        countCLabel.setText("Count C: " + counterModel.getCountC());
        totalClicksLabel.setText("Total Clicks: " + counterModel.getTotalClicks());
    }

}
