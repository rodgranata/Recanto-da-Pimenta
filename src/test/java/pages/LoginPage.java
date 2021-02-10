package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginPage extends BasePages {
    public LoginPage(WebDriver navegador) {
        super(navegador);
    }

    public LoginFormPage clickEntrar(){
        //Fechando o pop-up inicial
        navegador.findElement(By.xpath("/html/body/div[16]/div/div/a")).click();

        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Clicando no Entrar
        navegador.findElement(By.xpath("//*[@id=\"cabecalho\"]/div[2]/div[1]/div[2]/div[2]/div[2]/ul/li[1]/a")).click();

        return new LoginFormPage(navegador);
    }

}
