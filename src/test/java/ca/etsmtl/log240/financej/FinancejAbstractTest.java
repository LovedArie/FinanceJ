package ca.etsmtl.log240.financej;
import org.uispec4j.*;
import org.uispec4j.interception.MainClassAdapter;
import org.junit.Test;
public abstract class FinancejAbstractTest extends UISpecTestCase {
    /*protected Table ledgerTable;
    protected Button ledgerButton;
    protected Button reportsButton;*/
    protected TextBox usernameField;
    protected PasswordField passwordField;
    protected Button loginButton;
    protected Button exitButton;
    static {
        UISpec4J.init();
    }
    protected void setUp() throws Exception {
        super.setUp();
        setAdapter(new MainClassAdapter(FinanceJ.class));
        Window window = getMainWindow();
        usernameField = window.getTextBox("USERNAME_TEXT_FIELD");
        usernameField.setText("client1");
        passwordField = window.getPasswordField("PASSWORD_PASSWORD_FIELD");
        passwordField.setPassword("client1");
        loginButton = window.getButton("Connexion");
        UISpec4J.setWindowInterceptionTimeLimit(100);
    }
}