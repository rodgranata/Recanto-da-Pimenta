package tests;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;
import static org.junit.Assert.*;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "AlterarContaDoUsuario.csv")

public class RefactoryAlterarContaDoUsuarioTest {
    public WebDriver navegador;

    @Before
    public void setUp() {
        navegador = Web.createChorme();
    }

    @Test
    public void testAlterarContaUsuario(@Param(name = "email")String email,
                                        @Param(name = "senha")String senha,
                                        @Param(name = "telprincipal")String telprincipal,
                                        @Param(name = "telcomercial")String telcomercial){
        String textoMsg = new LoginPage(navegador)
                .clickEntrar()
                .fazerLogin(email, senha)
                .clickEditar()
                .typeTelPrincipal(telprincipal)
                .typeTelComercial(telcomercial)
                .clickSalvar()
                .validarMensagem();

        assertEquals("×\n" + "Dados da conta alterada com sucesso.", textoMsg);

        //Evidência final da execução
        String screenshotArquivo = "C:\\Users\\tatia\\IdeaProjects\\Cadastro Recanto da Pimenta\\TestReport\\" + Generator.datahoraParaArquivo() + "TelaAcessoDadosCadastrais.png";
        Screenshot.print(navegador,screenshotArquivo);
    }

    @After
    public void tearDown() {
        //Fechar navegador
        navegador.quit();
    }
}
