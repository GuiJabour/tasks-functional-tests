package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() throws MalformedURLException {
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL(" http://192.168.0.165:4444/wd/hub"), cap);
		driver.navigate().to("http://192.168.0.165:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();

		try {

			// Clicar em add Todo
			driver.findElement(By.id("addTodo")).click();

			// Escrever a descri��o
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

			// Clicar em Salvar
			driver.findElement(By.id("saveButton")).click();

			// Validar Mensagem de Sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", mensagem);
		} finally {
			// Fechar o Browser
			driver.quit();
		}

	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();

		try {

			// Clicar em add Todo
			driver.findElement(By.id("addTodo")).click();

			// Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

			// Clicar em Salvar
			driver.findElement(By.id("saveButton")).click();

			// Validar Mensagem de Sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", mensagem);
		} finally {
			// Fechar o Browser
			driver.quit();
		}

	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();

		try {

			// Clicar em add Todo
			driver.findElement(By.id("addTodo")).click();

			// Escrever a descri��o
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// Clicar em Salvar
			driver.findElement(By.id("saveButton")).click();

			// Validar Mensagem de Sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", mensagem);
		} finally {
			// Fechar o Browser
			driver.quit();
		}

	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {

		WebDriver driver = acessarAplicacao();

		try {

			// Clicar em add Todo
			driver.findElement(By.id("addTodo")).click();

			// Escrever a descri��o
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

			// Escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2005");

			// Clicar em Salvar
			driver.findElement(By.id("saveButton")).click();

			// Validar Mensagem de Sucesso
			String mensagem = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", mensagem);
		} finally {
			// Fechar o Browser
			driver.quit();
		}

	}
	
}
