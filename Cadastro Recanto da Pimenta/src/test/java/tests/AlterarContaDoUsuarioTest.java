package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "AlterarContaDoUsuario.csv")

public class AlterarContaDoUsuarioTest {

    private WebDriver navegador;

    @Rule
    public TestName test = new TestName();


    @Before
    public void setUp() {
        navegador = Web.createChorme();

        //Fechando o pop-up inicial
        navegador.findElement(By.xpath("/html/body/div[16]/div/div/a")).click();

        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Clicando no Entrar
        navegador.findElement(By.xpath("//*[@id=\"cabecalho\"]/div[2]/div[1]/div[2]/div[2]/div[2]/ul/li[1]/a")).click();
    }

    @Test
    public void testAlterarContaUsuario(@Param(name = "email")String email,
                                        @Param(name = "senha")String senha,
                                        @Param(name = "telprincipal")String telprincipal,
                                        @Param(name = "telcomercial")String telcomercial) {

        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Identificando o formulário de cadastro
        WebElement formularioJasouCadastrado = navegador.findElement(By.xpath("//*[@id=\"corpo\"]/div/div[2]/div[2]/div[1]"));

        //Inserindo e-mail no campo Email do formulário Já sou cadastrado
        formularioJasouCadastrado.findElement(By.id("id_email")).sendKeys(email);

        //Inserindo senha no campo Senha do formulário Já sou cadastrado
        formularioJasouCadastrado.findElement(By.id("id_senha")).sendKeys(senha);

        //Evidência da tela de acesso da minha conta
        String screenshotAcesso = "C:\\Users\\tatia\\IdeaProjects\\Cadastro Recanto da Pimenta\\TestReport\\" + Generator.datahoraParaArquivo() + "TelaAcessoDadosCadastrais.png";
        Screenshot.print(navegador,screenshotAcesso);

        //Identificando o botão Prosseguir
        WebElement botaoProsseguir = navegador.findElement(By.xpath("//*[@id=\"corpo\"]/div/div[2]/div[2]/div[1]/div/form/fieldset/div[2]/div/button"));

        //Clicar no botão Prosseguir
        botaoProsseguir.click();

        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Clicar no botão EDITAR DADOS CADASTRAIS
        navegador.findElement(By.xpath("//*[@id=\"corpo\"]/div/div[2]/div[2]/div/div/div[1]/div/div[1]/fieldset/div/a[2]")).click();

        //Inserindo o telefone fixo
        navegador.findElement(By.id("id_telefone_principal")).sendKeys(telprincipal);

        //Inserindo o telefone comercial
        navegador.findElement(By.id("id_telefone_comercial")).sendKeys(telcomercial);

        //Clicando no botão Salvar
        navegador.findElement((By.xpath("//*[@id=\"corpo\"]/div/div[2]/div[2]/form/div[2]/div/button"))).click();

        //Validando mensagem que a conta foi alterada com suceso

        WebElement alertaDeSucesso = navegador.findElement(By.cssSelector("#corpo > div > div.alert.alert-success.alert-geral"));
        String mensagem = alertaDeSucesso.getText();
        assertEquals("×\n" + "Dados da conta alterada com sucesso.", mensagem);

        //Evidência final da execução
        String screenshotArquivo = "C:\\Users\\tatia\\IdeaProjects\\Cadastro Recanto da Pimenta\\TestReport\\" + Generator.datahoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.print(navegador,screenshotArquivo);
    }

    @After
    public void tearDown() {
        //Fechar navegador
        navegador.quit();
    }
}
