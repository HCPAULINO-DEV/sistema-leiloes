import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private LoginPage loginPage;

    @BeforeEach
    void beforeEach(){
        this.loginPage = new LoginPage();
    }

    @AfterEach
    void afterEach(){
        loginPage.quit();
    }

    @Test
    public void deveEfeturarLoginComUsuarioESenhaValidos(){
        String usuario = "fulano";
        String senha = "pass";
        loginPage.efetuarLogin(usuario, senha);

        Assertions.assertTrue(loginPage.isAtualUrlIgualUrlLeiloes());
        Assertions.assertEquals(usuario, loginPage.getTextoUsuario());
        Assertions.assertTrue(!loginPage.isNaoContemBotaoSair());
    }

    @Test
    public void naoDeveEfeturarLoginComUsuarioESenhaNulos(){
        String usuario = "";
        String senha = "";
        loginPage.efetuarLogin(usuario, senha);

        Assertions.assertTrue(loginPage.isAtualUrlIgualUrlLoginErro());
        Assertions.assertTrue(!loginPage.isNaoContemAlerta());
        Assertions.assertEquals("Entrar", loginPage.isNaoContemBotaoEntrar());
    }

    @Test
    public void naoDeveEfeturarLoginComUsuarioValidoESenhaInvalida(){
        String usuario = "fulano";
        String senha = "senha-incorreta";
        loginPage.efetuarLogin(usuario, senha);

        Assertions.assertTrue(loginPage.isAtualUrlIgualUrlLoginErro());
        Assertions.assertTrue(!loginPage.isNaoContemAlerta());
        Assertions.assertEquals("Entrar", loginPage.isNaoContemBotaoEntrar());
    }

    @Test
    public void naoDeveEfeturarLoginComUsuarioInvalidoESenhaValida(){
        String usuario = "usuario-inavalido";
        String senha = "pass";
        loginPage.efetuarLogin(usuario, senha);

        Assertions.assertTrue(loginPage.isAtualUrlIgualUrlLoginErro());
        Assertions.assertTrue(!loginPage.isNaoContemAlerta());
        Assertions.assertEquals("Entrar", loginPage.isNaoContemBotaoEntrar());
    }
}
