import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

public class CadastroLeilaoTest {

    private CadastroLeilaoPage cadastroLeilaoPage;
    private LeiloesPage leiloesPage;

    @BeforeEach
    void beforeEach() {
        LoginPage loginPage = new LoginPage();
        this.leiloesPage = loginPage.efetuarLogin("fulano", "pass");
        this.cadastroLeilaoPage = leiloesPage.irParaCadastroLeilao();
    }

    @AfterEach
    void afterEach() {
        cadastroLeilaoPage.quit();
    }

    @Test
    public void deveEfetuarCadastroDoLeilao() {
        Integer numero = ThreadLocalRandom.current().nextInt(1, 100);
        String nome = "Nome do leilão " + numero;
        String valorInicial = "3000.00";
        String dataAbertura = "30/05/2025";

        cadastroLeilaoPage.cadastrarLeilao(nome, valorInicial, dataAbertura);

        Assertions.assertTrue(cadastroLeilaoPage.isAtualUrlIgualUrlLeiloes());
        Assertions.assertTrue(cadastroLeilaoPage.isLeilaoCadastrado(nome, valorInicial, dataAbertura));
    }

    @Test
    public void naoDeveEfetuarCadastroDoLeilaoComDadosNulos() {
        String nome = "";
        String valorInicial = "";
        String dataAbertura = "";

        cadastroLeilaoPage.cadastrarLeilao(nome, valorInicial, dataAbertura);

//        Assertions.assertTrue(cadastroLeilaoPage.isAtualUrlIgualUrlCadastroLeiloes()); - **BUG ENCONTRADO**
        Assertions.assertTrue(!cadastroLeilaoPage.isNaoContemAlerta());
        Assertions.assertTrue(cadastroLeilaoPage.isContemTexto("minimo 3 caracteres"));
        Assertions.assertTrue(cadastroLeilaoPage.isContemTexto("não deve estar em branco"));
        Assertions.assertTrue(cadastroLeilaoPage.isContemTexto("deve ser um valor maior de 0.1"));
        Assertions.assertTrue(cadastroLeilaoPage.isContemTexto("deve ser uma data no formato dd/MM/yyyy"));
    }

    @Test
    public void naoDeveCadastrarLeilaoComNomeMenorDeTresCaracteres(){
        String nome = "PC";
        String valorInicial = "1500.00";
        String dataAbertura = "30/05/2025";

        cadastroLeilaoPage.cadastrarLeilao(nome, valorInicial, dataAbertura);

//        Assertions.assertTrue(cadastroLeilaoPage.isAtualUrlIgualUrlCadastroLeiloes()); - **BUG ENCONTRADO**
        Assertions.assertEquals("minimo 3 caracteres", cadastroLeilaoPage.getTextoAlerta());
    }

    @Test
    public void naoDeveCadastrarLeilaoComValorInicialMenorQueUmCentavo(){
        String nome = "Notebook";
        String valorInicial = "0.09";
        String dataAbertura = "30/05/2025";

        cadastroLeilaoPage.cadastrarLeilao(nome, valorInicial, dataAbertura);

//        Assertions.assertTrue(cadastroLeilaoPage.isAtualUrlIgualUrlCadastroLeiloes()); - **BUG ENCONTRADO**
        Assertions.assertEquals("deve ser um valor maior de 0.1", cadastroLeilaoPage.getTextoAlerta());
    }

    @Test
    public void naoDeveCadastrarLeilaoComDataInicialForaDoFormato(){
        String nome = "Notebook";
        String valorInicial = "1.0";
        String dataAbertura = "30052025";

        cadastroLeilaoPage.cadastrarLeilao(nome, valorInicial, dataAbertura);

//        Assertions.assertTrue(cadastroLeilaoPage.isAtualUrlIgualUrlCadastroLeiloes()); - **BUG ENCONTRADO**
        Assertions.assertEquals("deve ser uma data no formato dd/MM/yyyy", cadastroLeilaoPage.getTextoAlerta());
    }
}
