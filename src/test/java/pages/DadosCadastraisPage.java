package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DadosCadastraisPage extends BasePages{
    public DadosCadastraisPage(WebDriver navegador) {
        super(navegador);
    }

    public EdicaoDadosPage clickEditar(){
        navegador.findElement(By.xpath("//*[@id=\"corpo\"]/div/div[2]/div[2]/div/div/div[1]/div/div[1]/fieldset/div/a[2]")).click();

        return new EdicaoDadosPage(navegador);

    }
}
