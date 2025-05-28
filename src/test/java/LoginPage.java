import org.openqa.selenium.By;

public class LoginPage extends PageObjects{

    public static final String URL_LOGIN = "http://localhost:8087/login?error";
    public static final String URL_LEILOES = "http://localhost:8087/leiloes";
    public static final String URL_LOGIN_ERRO = "http://localhost:8087/login?error";
    public static final String URL_LEILAO_NEW = "http://localhost:8087/leiloes/new";

    public LoginPage() {
        super(null);
        browser.navigate().to(URL_LOGIN);
    }

    public LeiloesPage efetuarLogin(String usuario, String senha) {
        inserirUsuario(usuario);
        inserirSenha(senha);
        clicarBotaoLogin();

        return new LeiloesPage(browser);
    }

    public void inserirUsuario(String usuario){
        browser.findElement(By.id("username")).sendKeys(usuario);
    }

    public void inserirSenha(String password){
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public void clicarBotaoLogin(){
        browser.findElement(By.id("btn-primary")).click();
    }

    public boolean isAtualUrlIgualUrlLeiloes() {
        return browser.getCurrentUrl().equals(URL_LEILOES);
    }

    public String getTextoUsuario() {
        return browser.findElement(By.id("font-italic")).getText();
    }

    public boolean isNaoContemBotaoSair() {
        return browser.findElements(By.cssSelector(".text-light.ml-3")).isEmpty();
    }

    public boolean isAtualUrlIgualUrlLoginErro() {
        return browser.getCurrentUrl().equals(URL_LOGIN_ERRO);
    }

    public boolean isNaoContemAlerta() {
        return browser.findElements(By.id("alert-danger")).isEmpty();
    }

    public String isNaoContemBotaoEntrar() {
        return browser.findElement(By.cssSelector(".text-light")).getText();
    }

    public CadastroLeilaoPage irParaCadastroLeilao() {
        browser.navigate().to(URL_LEILAO_NEW);

        return new CadastroLeilaoPage(browser);
    }
}
