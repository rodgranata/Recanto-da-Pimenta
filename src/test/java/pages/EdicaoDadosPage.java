package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EdicaoDadosPage extends BasePages {
    public EdicaoDadosPage(WebDriver navegador) {
        super(navegador);
    }

    public EdicaoDadosPage typeTelPrincipal(String telprincipal){
        navegador.findElement(By.id("id_telefone_principal")).sendKeys(telprincipal);

        return this;
    }

    public EdicaoDadosPage typeTelComercial (String telcomercial){
        navegador.findElement(By.id("id_telefone_comercial")).sendKeys(telcomercial);

        return this;
    }

    public AlertaDeSucesso clickSalvar(){
        navegador.findElement((By.xpath("//*[@id=\"corpo\"]/div/div[2]/div[2]/form/div[2]/div/button"))).click();

        return new AlertaDeSucesso(navegador);
    }
}
