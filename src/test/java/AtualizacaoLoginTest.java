import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

public class AtualizacaoLoginTest {

    private AtualizacaoLeilaoPage atualizacaoLeilaoPage;
    private LeiloesPage leiloesPage;

    @BeforeEach
    void begoreEach(){
        LoginPage loginPage = new LoginPage();
        this.leiloesPage = loginPage.efetuarLogin("fulano", "pass");
        this.atualizacaoLeilaoPage = leiloesPage.irParaAtualizacaoLeilaoIdUm();
    }

    @AfterEach
    void afterEach(){
        atualizacaoLeilaoPage.quit();
    }

    @Test
    public void deveEfetuarAtualizacaoDoLeilao() {
        String nome = "Item do leilão atualizado";
        String valorInicial = "1.00";
        String dataAbertura = "30/05/2025";

        atualizacaoLeilaoPage.atualizarLeilao(nome, valorInicial, dataAbertura);

        Assertions.assertTrue(atualizacaoLeilaoPage.isAtualUrlIgualUrlLeiloes());
        Assertions.assertTrue(atualizacaoLeilaoPage.isLeilaoCadastrado(nome, valorInicial, dataAbertura));
    }
}
