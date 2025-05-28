import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.concurrent.TimeUnit;

public class PageObjects {
    protected WebDriver browser;

    public PageObjects(WebDriver browser) {
        System.setProperty("webdriver.edge.driver", "drivers/edgedriver.exe");  //CHAMA O DRIVER DO EDGE
        if (browser == null){
            this.browser = new EdgeDriver(); //CASO SEJA NECESSÁRIO ABRIR UMA NOVA PAGINA NO BROWSER
        } else {
            this.browser = browser; //CASO NÃO SEJA NECESSÁRIO ABRIR UMA PÁGINA NO BROWSER, E A PÁGINA ATUAL SER REAPROVEITADA
        }

        this.browser.manage().timeouts()
                .implicitlyWait(5, TimeUnit.SECONDS) //PARA TRABALHAR COM AJAX OU ELEMENTOS DO JAVASCRIPT QUE DEMORE PARA CARREGAR
                .pageLoadTimeout(10, TimeUnit.SECONDS); //PARA TRABALHAR COM AJAX OU PÁGINAS QUE DEMORE PARA CARREGAR

    }

    public void quit() {
        browser.quit(); //PARA FECHAR TODAS AS ABAS APÓS TESTES
    }
}
