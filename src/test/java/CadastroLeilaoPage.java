import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CadastroLeilaoPage extends PageObjects{

    public static final String URL_LEILOES = "http://localhost:8087/leiloes";
    public static final String URL_LEILAO_NEW = "http://localhost:8087/leiloes/new";

    public CadastroLeilaoPage(WebDriver browser) {
        super(browser);
    }

    public LeiloesPage cadastrarLeilao(String nome, String valorInicial, String dataAbertura) {
        inserirNomeLeilao(nome);
        inserirValorInicialLeilao(valorInicial);
        inserirDataAberturaLeilao(dataAbertura);
        clicarBotaoSalvarLeilao();

        return new LeiloesPage(browser);
    }

    public void inserirNomeLeilao(String nome) {
        browser.findElement(By.id("form-nome")).sendKeys(nome);
    }

    public void inserirValorInicialLeilao(String valorInicial) {
        browser.findElement(By.id("form-valorInicial")).sendKeys(valorInicial);
    }

    public void inserirDataAberturaLeilao(String dataAbertura) {
        browser.findElement(By.id("form-dataAbertura")).sendKeys(dataAbertura);
    }

    public void clicarBotaoSalvarLeilao() {
        browser.findElement(By.id("button")).click();
    }

    public boolean isLeilaoCadastrado(String nome, String valorInicial, String dataAbertura) {
        WebElement linhaDaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaDataAbertura = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValorInicial = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));

        return colunaNome.getText().equals(nome)
                && colunaDataAbertura.getText().equals(dataAbertura)
                && colunaValorInicial.getText().equals(valorInicial);
    }

    public boolean isAtualUrlIgualUrlLeiloes() {
        return browser.getCurrentUrl().equals(URL_LEILOES);
    }

    public boolean isNaoContemAlerta() {
        return browser.findElements(By.cssSelector(".alert.alert-danger")).isEmpty();
    }

    public boolean isContemTexto(String texto){
        return browser.getPageSource().contains(texto);
    }

    public boolean isAtualUrlIgualUrlCadastroLeiloes() {
        return browser.getCurrentUrl().equals(URL_LEILAO_NEW);
    }

    public String getTextoAlerta() {
        return browser.findElement(By.cssSelector(".alert.alert-danger")).getText();
    }

}
