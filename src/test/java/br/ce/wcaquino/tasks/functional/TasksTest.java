package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {

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
	public void naoDeveSalvarTarefaSemDescricao() {

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
	public void naoDeveSalvarTarefaSemData() {

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
	public void naoDeveSalvarTarefaComDataPassada() {

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
