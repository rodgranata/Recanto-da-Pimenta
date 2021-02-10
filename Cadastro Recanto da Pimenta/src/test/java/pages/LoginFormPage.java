package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import suporte.Web;

import java.util.concurrent.TimeUnit;

public class LoginFormPage extends BasePages {
    public LoginFormPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginFormPage typeLogin(String login){
        navegador.findElement(By.xpath("//*[@id=\"corpo\"]/div/div[2]/div[2]/div[1]")).findElement(By.id("id_email")).sendKeys(login);

        return this;
    }

    public LoginFormPage typeSenha(String senha){
        navegador.findElement(By.xpath("//*[@id=\"corpo\"]/div/div[2]/div[2]/div[1]")).findElement(By.id("id_senha")).sendKeys(senha);

        return this;
    }

    public DadosCadastraisPage clickProsseguir(){
        navegador.findElement(By.xpath("//*[@id=\"corpo\"]/div/div[2]/div[2]/div[1]/div/form/fieldset/div[2]/div/button")).click();

        return new DadosCadastraisPage(navegador);
    }

    public DadosCadastraisPage fazerLogin(String login, String senha){
        typeLogin(login);
        typeSenha(senha);
        clickProsseguir();

        return new DadosCadastraisPage(navegador);
    }
}

