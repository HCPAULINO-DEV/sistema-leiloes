import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

public class CadastroLeilaoTest {

    private CadastroLeilaoPage cadastroLeilaoPage;

    @BeforeEach
    void beforeEach(){
        LoginPage loginPage = new LoginPage();
        loginPage.efetuarLogin("fulano", "pass");
        this.cadastroLeilaoPage = loginPage.irParaCadastroLeilao();
    }

    @AfterEach
    void afterEach(){
        cadastroLeilaoPage.quit();
    }

    @Test
    public void deveEfetuarCadastroDoLeilao(){
        Integer numero = ThreadLocalRandom.current().nextInt(1, 100);
        String nome = "Nome do leilão " + numero;
        String valorInicial = "3000.00";
        String dataAbertura = "30/05/2025";
        cadastroLeilaoPage.cadastrarLeilao(nome, valorInicial, dataAbertura);

        Assertions.assertTrue(cadastroLeilaoPage.isAtualUrlIgualUrlLeiloes());
        Assertions.assertTrue(cadastroLeilaoPage.isLeilaoCadastrado(nome, valorInicial, dataAbertura));
    }
}
