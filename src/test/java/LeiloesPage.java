import org.openqa.selenium.WebDriver;

public class LeiloesPage extends PageObjects{

    public static final String URL_LEILAO_NEW = "http://localhost:8087/leiloes/new";

    public LeiloesPage(WebDriver browser) {
        super(browser);
    }

    public CadastroLeilaoPage irParaCadastroLeilao() {
        browser.navigate().to(URL_LEILAO_NEW);

        return new CadastroLeilaoPage(browser);
    }
}
