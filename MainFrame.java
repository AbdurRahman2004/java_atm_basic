import java.awt.*;
import java.awt.event.*;

public class MainFrame extends Frame {
    private Image backgroundImage; // Background image

    public MainFrame() {
        setTitle("Main Frame");
        setSize(1600, 1200);
        setLayout(null);

        // Load the background image
        backgroundImage = Toolkit.getDefaultToolkit().getImage("E://Java//ATM//Frst.jpg");

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_ENTER) {
                    ATMLogin atmLogin = new ATMLogin();
                    atmLogin.setVisible(true);
                    dispose();
                }
            }
        });

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);

        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}

class ATMLogin extends Frame {
    private TextField pinField;
    private TextField cardField;
    private Panel detailPanel;
    private Label detailLabel;

    private Font labelFont;
    private Font buttonFont;

    private Image backgroundImage; // Background image
    private int initialAmount = 10000;
    private int remainingAmount;

    public ATMLogin() {
        setTitle("Welcome To Kotak Bank"); // Set the login page title to "Welcome To Kotak Bank"
        setSize(1600, 1200);
        setLayout(null);
        setResizable(false);

        // Load the background image
        backgroundImage = Toolkit.getDefaultToolkit().getImage("E:/Java/ATM/ATMIMG (2).jpg");

        Label atmLabel = new Label("Welcome to Kotak Bank ");
        atmLabel.setBounds(100, 50, 450, 50);
        atmLabel.setAlignment(Label.CENTER);
        Font atmFont = new Font("Arial", Font.BOLD, 30);
        atmLabel.setFont(atmFont);
        add(atmLabel);

        Label managementLabel = new Label("Enter Your details");
        managementLabel.setBounds(100, 150, 450, 25);
        managementLabel.setAlignment(Label.CENTER);
        Font managementFont = new Font("Arial", Font.BOLD, 20);
        managementLabel.setFont(managementFont);
        add(managementLabel);

        // Card Number Label
        Label enterCardLabel = new Label("Card Number:");
        enterCardLabel.setBounds(100, 400, 130, 25);
        enterCardLabel.setAlignment(Label.RIGHT);
        labelFont = new Font("Arial", Font.PLAIN, 20);
        enterCardLabel.setFont(labelFont);
        add(enterCardLabel);

        // Card Number Text Field
        cardField = new TextField();
        cardField.setBounds(240, 400, 260, 30);
        cardField.setFont(labelFont);
        cardField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String text = cardField.getText();

                // Limit input to 16 digits
                if (text.length() >= 16 || !Character.isDigit(c)) {
                    e.consume();
                    return;
                }
            }
        });
        add(cardField);

        // Pin Label
        Label pinLabel = new Label("Pin:");
        pinLabel.setBounds(150, 450, 80, 25);
        pinLabel.setAlignment(Label.RIGHT);
        pinLabel.setFont(labelFont);
        add(pinLabel);

        // PIN Text Field
        pinField = new TextField();
        pinField.setBounds(240, 450, 260, 30);
        pinField.setFont(labelFont);
        pinField.setEchoChar('*');
        pinField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || pinField.getText().length() >= 4) {
                    e.consume();
                }
            }
        });
        add(pinField);

        // Submit Button
        Button submitButton = new Button("Submit");
        buttonFont = new Font("Arial", Font.BOLD, 24);
        submitButton.setFont(buttonFont);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pin = pinField.getText();
                String cardNumber = cardField.getText();
                showDetailPage(cardNumber);
            }
        });

        Panel buttonPanel = new Panel(new BorderLayout());
        buttonPanel.setBounds(220, 550, 200, 40);
        buttonPanel.add(submitButton, BorderLayout.CENTER);
        add(buttonPanel);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

   private void showDetailPage(String cardNumber) {
    removeAll();

    // Detail Panel
    detailPanel = new Panel() {
        public void paint(Graphics g) {
       backgroundImage = Toolkit.getDefaultToolkit().getImage("E:/Java/ATM/ATMIMG (2).jpg");

            super.paint(g);
            // Draw the background image
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    };
    detailPanel.setBounds(0, 0, getWidth(), getHeight()); // Adjust the size to match the frame size
    detailPanel.setLayout(null);

    // Detail Label
    detailLabel = new Label("Please Select Your Transaction ");
    detailLabel.setBounds(200, 50, 450, 50);
    detailLabel.setAlignment(Label.CENTER);
    Font detailFont = new Font("Arial", Font.BOLD, 28);
    detailLabel.setFont(detailFont);
    detailPanel.add(detailLabel);

    // Card Number Label
    Label cardNumberLabel = new Label("Card Number: " + cardNumber);
    cardNumberLabel.setBounds(200, 100, 450, 50);
    cardNumberLabel.setAlignment(Label.CENTER);
    labelFont = new Font("Arial", Font.BOLD, 24);
    cardNumberLabel.setFont(labelFont);
    detailPanel.add(cardNumberLabel);

    // Withdraw Button
    Button withdrawButton = new Button("Withdraw");
    withdrawButton.setBounds(100, 400, 200, 40);
    withdrawButton.setFont(buttonFont);
    withdrawButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            showWithdrawPanel();
        }
    });
    detailPanel.add(withdrawButton);

    // Deposit Button
    Button depositButton = new Button("Deposit");
    depositButton.setBounds(450, 400, 200, 40);
    depositButton.setFont(buttonFont);
    depositButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            showDepositPanel();
        }
    });
    detailPanel.add(depositButton);

    // Mini Statement Button
    Button miniStatementButton = new Button("Mini Statement");
    miniStatementButton.setBounds(250, 500, 200, 40);
    miniStatementButton.setFont(buttonFont);
    miniStatementButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            showMiniStatementPanel();
        }
    });
    detailPanel.add(miniStatementButton);

       // Back Button

        Button backButton = new Button("Back");
        backButton.setBounds(250, 650, 200, 40);
        backButton.setFont(buttonFont);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(detailPanel);
                addComponents();
            }
        });
        detailPanel.add(backButton);

        add(detailPanel);
        validate();

    add(detailPanel);
    validate();
}


    private void showWithdrawPanel() {
        removeAll();

        // Withdraw Panel
        Panel withdrawPanel = new Panel() {
            public void paint(Graphics g) {
               backgroundImage = Toolkit.getDefaultToolkit().getImage("E:/Java/ATM/ATMIMG (2).jpg");

                super.paint(g);
                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        withdrawPanel.setBounds(0, 0, getWidth(), getHeight()); // Adjust the size to match the frame size
        withdrawPanel.setLayout(null);

        // Withdraw Label
        Label withdrawLabel = new Label("Withdraw Panel");
        withdrawLabel.setBounds(0, 50, getWidth(), 50);
        withdrawLabel.setAlignment(Label.CENTER);
        Font withdrawFont = new Font("Arial", Font.BOLD, 48);
        withdrawLabel.setFont(withdrawFont);
        withdrawPanel.add(withdrawLabel);

        // Enter Amount Label
        Label enterAmountLabel = new Label("Enter Your Amount:");
        enterAmountLabel.setBounds(250, 400, 200, 35);
        Font enterAmount = new Font("Arial", Font.BOLD, 18);
        enterAmountLabel.setFont(enterAmount);
        withdrawPanel.add(enterAmountLabel);

        // Amount Text Field
        TextField amountField = new TextField();
        amountField.setBounds(450, 400, 150, 35);
        amountField.setFont(labelFont);
        amountField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String text = amountField.getText();

                // Limit input to digits only
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        withdrawPanel.add(amountField);

        // Back Button
        Button backButton = new Button("Back");
        backButton.setBounds(180, 650, 200, 40);
        backButton.setFont(buttonFont);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(withdrawPanel);
                showDetailPage(cardField.getText());
            }
        });
        withdrawPanel.add(backButton);

        // Submit Button
        Button submitButton = new Button("Submit");
        submitButton.setBounds(450, 650, 200, 40);
        submitButton.setFont(buttonFont);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String amount = amountField.getText();
                remainingAmount = initialAmount - Integer.parseInt(amount);
                if (remainingAmount < 0) {
                    remainingAmount = 0;
                }
                showTransactionPanel(amount);
            }
        });
        withdrawPanel.add(submitButton);

        add(withdrawPanel);
        validate();
    }

   private void showTransactionPanel(String amount) {
    removeAll();

    // Transaction Panel
    Panel transactionPanel = new Panel() {
        public void paint(Graphics g) {
            super.paint(g);
             backgroundImage = Toolkit.getDefaultToolkit().getImage("E:/Java/ATM/Transaction.jpg");

            // Draw the background image
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    };
    transactionPanel.setBounds(0, 0, getWidth(), getHeight()); // Adjust the size to match the frame size
    transactionPanel.setLayout(null);

    // Transaction Label
    Label successLabel = new Label("Your Transaction is Successful");
    successLabel.setBounds(0, 150, getWidth(), 30);
    successLabel.setAlignment(Label.CENTER);
    Font successFont = new Font("Arial", Font.BOLD, 24);
    successLabel.setFont(successFont);
    transactionPanel.add(successLabel);

    // Collect Money Label
    Label collectLabel = new Label("Want your bill?");
    collectLabel.setBounds(100, 350, 400, 30);
    collectLabel.setAlignment(Label.CENTER);
    collectLabel.setFont(successFont);
    transactionPanel.add(collectLabel);


   //NO BUTTON
     
    Button noButton = new Button("NO");
    noButton.setBounds(150, 550, 100, 40);
    noButton.setFont(buttonFont);
    noButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e){

     dispose();
                System.exit(0);
            }
        });
transactionPanel.add(noButton);


    

    // OK Button
    Button okButton = new Button("Yes");
    okButton.setBounds(300, 550, 100, 40);
    okButton.setFont(buttonFont);
    okButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        Dialog dialog = new Dialog(ATMLogin.this, "Transaction Complete", true);
        
        dialog.setLayout(new BorderLayout()); // Use BorderLayout for the dialog

        // Transaction success message
        Label dialogLabel = new Label("Your Amount RS." + amount + " is withdrawn Successfully");
        dialogLabel.setAlignment(Label.CENTER);
        dialog.add(dialogLabel, BorderLayout.NORTH);

        // Collect money message
        Label dialogLabel1 = new Label("Please Collect your Cash");
        dialogLabel1.setAlignment(Label.CENTER);
        dialog.add(dialogLabel1, BorderLayout.CENTER);

        // Close button
        Button closeButton = new Button("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        Panel buttonPanel = new Panel(new FlowLayout(FlowLayout.CENTER)); // Use FlowLayout for the button
        buttonPanel.add(closeButton);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setSize(600, 250);
        dialog.setBounds(1000,350,500,350);
       
        dialog.setVisible(true);
    }
});
    transactionPanel.add(okButton);


    // Back Button
    Button backButton = new Button("Back");
    backButton.setBounds(680, 750, 200, 40);
    backButton.setFont(buttonFont);
    backButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            remove(transactionPanel);
            showDetailPage(cardField.getText());
        }
    });
    transactionPanel.add(backButton);

    add(transactionPanel);
    validate();
}

   


   private void showMiniStatementPanel() {
    removeAll();

    // Mini Statement Panel
    Panel miniStatementPanel = new Panel() {
        public void paint(Graphics g) {
            backgroundImage = Toolkit.getDefaultToolkit().getImage("E:/Java/ATM/ATMIMG (2).jpg");

            super.paint(g);
            // Draw the background image
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    };
    miniStatementPanel.setBounds(0, 0, getWidth(), getHeight()); // Adjust the size to match the frame size
    miniStatementPanel.setLayout(null);

    // Mini Statement Label
    Label miniStatementLabel = new Label("Mini Statement ");
    miniStatementLabel.setBounds(280, 150, 280 , 35);
    
    Font miniStatementFont = new Font("Arial", Font.BOLD, 36);
    miniStatementLabel.setFont(miniStatementFont);
    miniStatementPanel.add(miniStatementLabel);

    // Amount Label
    Label amountLabel;
    if (remainingAmount == 0) {
        amountLabel = new Label("Your Balance is : " + initialAmount);
    } else {
        amountLabel = new Label("Your Balance is : " + remainingAmount);
    }
    amountLabel.setBounds(250, 350, 350 , 30);
    Font miniStatementFont1 = new Font("Arial", Font.BOLD, 25);
    amountLabel.setFont(miniStatementFont1);
    miniStatementPanel.add(amountLabel);

    // Back Button
    Button backButton = new Button("Back");
    backButton.setBounds(280, 550, 200, 40);
    backButton.setFont(buttonFont);
    backButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            remove(miniStatementPanel);
            showDetailPage(cardField.getText());
        }
    });
    miniStatementPanel.add(backButton);

    add(miniStatementPanel);
    validate();
}

    private void addComponents() {
        removeAll();

        setTitle("ATM Management System");

        add(cardField);
        add(pinField);

        Label atmLabel = new Label("Welcome to Kotak Bank ");
        atmLabel.setBounds(100, 50, 450, 50);
        atmLabel.setAlignment(Label.CENTER);
        Font atmFont = new Font("Arial", Font.BOLD, 30);
        atmLabel.setFont(atmFont);
        add(atmLabel);

        Label managementLabel = new Label("Enter Your details");
        managementLabel.setBounds(100, 150, 450, 25);
        managementLabel.setAlignment(Label.CENTER);
        Font managementFont = new Font("Arial", Font.BOLD, 20);
        managementLabel.setFont(managementFont);
        add(managementLabel);

        // Card Number Label
        Label enterCardLabel = new Label("Card Number:");
        enterCardLabel.setBounds(100, 400, 130, 25);
        enterCardLabel.setAlignment(Label.RIGHT);
        labelFont = new Font("Arial", Font.PLAIN, 20);
        enterCardLabel.setFont(labelFont);
        add(enterCardLabel);

        // Pin Label
        Label enterPinLabel = new Label("Pin:");
        enterPinLabel.setBounds(150, 450, 80, 25);
        enterPinLabel.setAlignment(Label.RIGHT);
        enterPinLabel.setFont(labelFont);
        add(enterPinLabel);

        Button submitButton = new Button("Submit");
        submitButton.setBounds(600, 400, 200, 40);
        submitButton.setFont(buttonFont);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pin = pinField.getText();
                String cardNumber = cardField.getText();
                showDetailPage(cardNumber);
            }
        });
        Panel buttonPanel = new Panel(new BorderLayout());
        buttonPanel.setBounds(220, 550, 200, 40);
        buttonPanel.add(submitButton, BorderLayout.CENTER);
        add(buttonPanel);

        setVisible(true);
        validate();
    }
private void showDepositPanel() {
    removeAll();

    // Deposit Panel
    Panel depositPanel = new Panel() {
        public void paint(Graphics g) {
            backgroundImage = Toolkit.getDefaultToolkit().getImage("E:/Java/ATM/ATMIMG (2).jpg");

            super.paint(g);
            // Draw the background image
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    };
    depositPanel.setBounds(0, 0, getWidth(), getHeight()); // Adjust the size to match the frame size
    depositPanel.setLayout(null);

    // Deposit Label
    Label depositLabel = new Label("Deposit Panel");
    depositLabel.setBounds(0, 50, getWidth(), 50);
    depositLabel.setAlignment(Label.CENTER);
    Font depositFont = new Font("Arial", Font.BOLD, 48);
    depositLabel.setFont(depositFont);
    depositPanel.add(depositLabel);

    // Enter Amount Label
    Label enterAmountLabel = new Label("Enter Amount to Deposit:");
    enterAmountLabel.setBounds(120, 400, 300, 35);
    Font enterAmount = new Font("Arial", Font.BOLD, 18);
    enterAmountLabel.setFont(enterAmount);
    depositPanel.add(enterAmountLabel);

    // Amount Text Field
    TextField amountField = new TextField();
    amountField.setBounds(450, 400, 150, 35);
    amountField.setFont(labelFont);
    amountField.addKeyListener(new KeyAdapter() {
        public void keyTyped(KeyEvent e) {
            char c = e.getKeyChar();
            String text = amountField.getText();

            // Limit input to digits only
            if (!Character.isDigit(c)) {
                e.consume();
            }
        }
    });
    depositPanel.add(amountField);

    // Back Button
    Button backButton = new Button("Back");
    backButton.setBounds(130, 650, 200, 40);
    backButton.setFont(buttonFont);
    backButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            remove(depositPanel);
            showDetailPage(cardField.getText());
        }
    });
    depositPanel.add(backButton);

    // Submit Button
    Button submitButton = new Button("Submit");
    submitButton.setBounds(400, 650, 200, 40);
    submitButton.setFont(buttonFont);
    submitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String amount = amountField.getText();
            int depositAmount = Integer.parseInt(amount);
            remainingAmount = initialAmount + depositAmount;
            showDepositedAmountPanel(amount);
            
        }
    });
    depositPanel.add(submitButton);

    add(depositPanel);
    validate();
}

private void showDepositedAmountPanel(String amount) {
    removeAll();

    // DepositedAmount Panel
    Panel depositedAmountPanel = new Panel() {
        public void paint(Graphics g) {
backgroundImage = Toolkit.getDefaultToolkit().getImage("E:/Java/ATM/Transaction.jpg");
            super.paint(g);

            // Draw the background image
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    };
    depositedAmountPanel.setBounds(0, 0, getWidth(), getHeight()); // Adjust the size to match the frame size
    depositedAmountPanel.setLayout(null);

    // DepositedAmount Label
    Label depositedAmountLabel = new Label("Your Amount is deposited Succesfully ");
    depositedAmountLabel.setBounds(0, 150, getWidth(), 30);
    depositedAmountLabel.setAlignment(Label.CENTER);
    Font depositedAmountFont = new Font("Arial", Font.BOLD, 24);
    depositedAmountLabel.setFont(depositedAmountFont);
    depositedAmountPanel.add(depositedAmountLabel);

    // Back Button
    Button backButton = new Button("Back");
    backButton.setBounds(680, 750, 200, 40);
    backButton.setFont(buttonFont);
    backButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            remove(depositedAmountPanel);
            showDetailPage(cardField.getText());
        }
    });
    depositedAmountPanel.add(backButton);

    add(depositedAmountPanel);
    validate();
}



    public void paint(Graphics g) {
        super.paint(g);
        
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}