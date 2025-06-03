import org.openqa.selenium.WebDriver;

public class LeiloesPage extends PageObjects{

    public static final String URL_LEILAO_NEW = "http://localhost:8087/leiloes/new";
    public static final String URL_LEILAO_UPDATE = "http://localhost:8087/leiloes/1/form";

    public LeiloesPage(WebDriver browser) {
        super(browser);
    }

    public CadastroLeilaoPage irParaCadastroLeilao() {
        browser.navigate().to(URL_LEILAO_NEW);

        return new CadastroLeilaoPage(browser);
    }

    public AtualizacaoLeilaoPage irParaAtualizacaoLeilaoIdUm(){
        browser.navigate().to(URL_LEILAO_UPDATE);

        return new AtualizacaoLeilaoPage(browser);
    }
}
