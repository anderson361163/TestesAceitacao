package br.com.alura.leilao.login;

import org.openqa.selenium.NoSuchElementException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTeste {

	@Test
	public void deveriaEfetuarLoginComDadosValidos() {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_102.exe");
		WebDriver browser = new ChromeDriver();
		browser.navigate().to("http://localhost:8080/login");

		browser.findElement(By.id("username")).sendKeys("fulano");
		browser.findElement(By.id("password")).sendKeys("pass");
		browser.findElement(By.id("login-form")).submit();

		Assert.assertFalse(browser.getCurrentUrl().equals("http://localhost:8080/login"));
		Assert.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());

		browser.quit();

	}
	
	@Test
	public void naoDeveriaLogarComDadosInvalidos() {
		
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_102.exe");
		WebDriver browser = new ChromeDriver();
		browser.navigate().to("http://localhost:8080/login");

		browser.findElement(By.id("username")).sendKeys("invalido");
		browser.findElement(By.id("password")).sendKeys("123123");
		browser.findElement(By.id("login-form")).submit();

		Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
		Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválido"));
		Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")).getText());
		
		
		browser.quit();
		
	}
	
}
