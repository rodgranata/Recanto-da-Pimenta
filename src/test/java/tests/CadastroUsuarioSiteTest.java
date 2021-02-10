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
import org.openqa.selenium.support.ui.Select;
import suporte.Generator;
import suporte.Screenshot;
import suporte.Web;


import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "CadastroUsuarioSite.csv")

public class CadastroUsuarioSiteTest {
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
    public void testAdicionarUmCadastrodeUsuario(@Param(name = "email")String email,
                                                 @Param(name = "senha")String senha,
                                                 @Param(name = "nome")String nome,
                                                 @Param(name = "cpf")String cpf,
                                                 @Param(name = "celular")String celular,
                                                 @Param(name = "telefone")String telefone,
                                                 @Param(name = "sexo")String sexo,
                                                 @Param(name = "nascimento")String nascimento,
                                                 @Param(name = "CEP")String CEP,
                                                 @Param(name = "endereco")String endereco,
                                                 @Param(name = "numero")String numero,
                                                 @Param(name = "bairro")String bairro) {

        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Identificando o formulário de cadastro
        WebElement formularioCadastro = navegador.findElement(By.xpath("//*[@id=\"corpo\"]/div/div[2]/div[2]/div[2]"));

        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Inserindo e-mail no campo Digite o email que deseja cadastrar:
        formularioCadastro.findElement(By.id("id_email")).sendKeys(email);

        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Identificando o botão Cadastrar
        WebElement botaoCadastrar = navegador.findElement(By.xpath("//*[@id=\"corpo\"]/div/div[2]/div[2]/div[2]/div/form/fieldset/div/button"));

        //Clicando no botão cadastrar
        botaoCadastrar.click();

        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Identificando o formulário Dados para Acesso
        WebElement formularioDadosdeAcesso = navegador.findElement(By.xpath("//*[@id=\"formCadastroCompleto\"]/form/div[1]/div/div"));

        //Inserindo a confirmação do e-mail
        formularioDadosdeAcesso.findElement(By.id("id_confirmacao_email")).sendKeys(email);

        //Inserindo a senha
        formularioDadosdeAcesso.findElement(By.id("id_senha")).sendKeys(senha);

        //Inserindo a confirmação da senha
        formularioDadosdeAcesso.findElement(By.id("id_confirmacao_senha")).sendKeys(senha);

        //Identificando o formulário Dados Pessoais
        WebElement formularioDadosPessoais = navegador.findElement(By.xpath("//*[@id=\"formCadastroCompleto\"]/form/div[3]"));

        //Inserindo o nome completo
        formularioDadosPessoais.findElement(By.id("id_nome")).sendKeys(nome);

        //Inserindo o CPF
        formularioDadosPessoais.findElement(By.id("id_cpf")).sendKeys(cpf);

        //Inserindo o celular
        formularioDadosPessoais.findElement(By.id("id_telefone_celular")).sendKeys(celular);

        //Inserindo o telefone fixo
        formularioDadosPessoais.findElement(By.id("id_telefone_principal")).sendKeys(telefone);

        //Selecionando opção no combo sexo
        WebElement campoSexo = formularioDadosPessoais.findElement(By.id("id_sexo"));
        new Select(campoSexo).selectByVisibleText(sexo);

        //Inserindo a data de nascimento
        formularioDadosPessoais.findElement(By.id("id_data_nascimento")).sendKeys(nascimento);

        //Inserindo o CEP
        formularioDadosPessoais.findElement(By.id("id_cep")).sendKeys(CEP);

        //Inserindo o endereço
        formularioDadosPessoais.findElement(By.id("id_endereco")).sendKeys(endereco);

        //Inserindo o número do endereço
        formularioDadosPessoais.findElement(By.id("id_numero")).sendKeys(numero);

        //Inserindo o bairro
        formularioDadosPessoais.findElement(By.id("id_bairro")).sendKeys(bairro);

        //Identificando o botão Criar Conta
        WebElement criarConta = navegador.findElement(By.cssSelector("#formCadastroCompleto > form > div.acao-editar.row-fluid > div > button"));

        //Clicando no botão Criar Conta
        criarConta.click();

        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement alertaCadastroSucesso = navegador.findElement(By.cssSelector("#corpo > div > div.alert.alert-success.alert-geral"));
        String mensagem = alertaCadastroSucesso.getText();
        assertEquals("×\n" + "Cliente criado com sucesso.", mensagem);

        //Evidência final da execução
        String screenshotArquivo = "C:\\Users\\tatia\\IdeaProjects\\Cadastro Recanto da Pimenta\\TestReport\\" + Generator.datahoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.print(navegador,screenshotArquivo);

    }

    @Test
    public void testeUsuarioExistente() {
        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Identificando o formulário de cadastro
        WebElement formularioCadastro = navegador.findElement(By.xpath("//*[@id=\"corpo\"]/div/div[2]/div[2]/div[2]"));

        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Inserindo e-mail no campo Digite o email que deseja cadastrar:
        formularioCadastro.findElement(By.id("id_email")).sendKeys("teste1@teste.com.br");

        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Identificando o botão Cadastrar
        WebElement botaoCadastrar = navegador.findElement(By.xpath("//*[@id=\"corpo\"]/div/div[2]/div[2]/div[2]/div/form/fieldset/div/button"));

        //Clicando no botão cadastrar
        botaoCadastrar.click();

        WebElement alertaCadastroSucesso = navegador.findElement(By.cssSelector("#corpo > div > div.alert.alert-danger.alert-geral"));
        String mensagem = alertaCadastroSucesso.getText();
        assertEquals("×\n" + "Já existe um cliente cadastrado com este email.", mensagem);

        //Evidência final da execução
        String screenshotArquivo = "C:\\Users\\tatia\\IdeaProjects\\Cadastro Recanto da Pimenta\\TestReport\\" + Generator.datahoraParaArquivo() + test.getMethodName() + ".png";
        Screenshot.print(navegador,screenshotArquivo);

    }

    @Test
    public void testeUsuarioBloqueado(){
        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Identificando o formulário de cadastro
        WebElement formularioCadastro = navegador.findElement(By.xpath("//*[@id=\"corpo\"]/div/div[2]/div[2]/div[2]"));

        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Inserindo e-mail no campo Digite o email que deseja cadastrar:
        formularioCadastro.findElement(By.id("id_email")).sendKeys("teste2@teste.com.br");

        navegador.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Identificando o botão Cadastrar
        WebElement botaoCadastrar = navegador.findElement(By.xpath("//*[@id=\"corpo\"]/div/div[2]/div[2]/div[2]/div/form/fieldset/div/button"));

        //Clicando no botão cadastrar
        botaoCadastrar.click();

        WebElement alertaCadastroSucesso = navegador.findElement(By.cssSelector("#corpo > div > div.alert.alert-danger.alert-geral"));
        String mensagem = alertaCadastroSucesso.getText();
        assertEquals("×\n" + "O email teste2@teste.com.br foi bloqueado em nossa loja. Caso queira utilizá-lo novamente, solicite o desbloqueio através do Fale Conosco.", mensagem);

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
