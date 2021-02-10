package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;


public class AlertaDeSucesso extends BasePages {
    public AlertaDeSucesso(WebDriver navegador) {
        super(navegador);
    }

    public String validarMensagem(){
        return navegador.findElement(By.cssSelector("#corpo > div > div.alert.alert-success.alert-geral")).getText();

    }
}
