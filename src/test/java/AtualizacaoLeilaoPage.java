import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AtualizacaoLeilaoPage extends PageObjects{


    public static final String URL_LEILOES = "http://localhost:8087/leiloes";

    public AtualizacaoLeilaoPage(WebDriver browser) {
        super(browser);
    }

    public LeiloesPage atualizarLeilao(String nome, String valorInicial, String dataAbertura) {
        inserirNomeLeilao(nome);
        inserirValorInicialLeilao(valorInicial);
        inserirDataAberturaLeilao(dataAbertura);
        clicarBotaoSalvarLeilao();

        return new LeiloesPage(browser);
    }

    private void clicarBotaoSalvarLeilao() {
        browser.findElement(By.id("button")).click();

    }

    private void inserirDataAberturaLeilao(String dataAbertura) {
        browser.findElement(By.id("form-dataAbertura")).clear();
        browser.findElement(By.id("form-dataAbertura")).sendKeys(dataAbertura);
    }

    private void inserirValorInicialLeilao(String valorInicial) {
        browser.findElement(By.id("form-valorInicial")).clear();
        browser.findElement(By.id("form-valorInicial")).sendKeys(valorInicial);

    }

    private void inserirNomeLeilao(String nome) {
        browser.findElement(By.id("form-nome")).clear();
        browser.findElement(By.id("form-nome")).sendKeys(nome);
    }

    public boolean isAtualUrlIgualUrlLeiloes() {
        return browser.getCurrentUrl().equals(URL_LEILOES);
    }

    public boolean isLeilaoCadastrado(String nome, String valorInicial, String dataAbertura) {
        WebElement linhaDaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:first-child"));
        WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaDataAbertura = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValorInicial = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));

        return colunaNome.getText().equals(nome)
                && colunaDataAbertura.getText().equals(dataAbertura)
                && colunaValorInicial.getText().equals(valorInicial);
    }
}
