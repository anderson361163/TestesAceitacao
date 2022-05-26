package br.com.alura.leilao.login;

import org.openqa.selenium.NoSuchElementException;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTeste {

	private static final String URL_LOGIN = "http://localhost:8080/login";
	private WebDriver browser;

	//vai rodar apenas uma vez
	@BeforeAll
	public static void beforeAll() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_102.exe");
	}
	
	@BeforeEach
	public void beforeEach() {
		this.browser = new ChromeDriver();
		browser.navigate().to(URL_LOGIN);
	}
	
	@AfterEach
	public void afterEach() {
		this.browser.quit();
	}
	
	@Test
	public void deveriaEfetuarLoginComDadosValidos() {

		browser.findElement(By.id("username")).sendKeys("fulano");
		browser.findElement(By.id("password")).sendKeys("pass");
		browser.findElement(By.id("login-form")).submit();

		Assert.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
		Assert.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());

	}
	
	@Test
	public void naoDeveriaLogarComDadosInvalidos() {
		
		browser.findElement(By.id("username")).sendKeys("invalido");
		browser.findElement(By.id("password")).sendKeys("123123");
		browser.findElement(By.id("login-form")).submit();

		Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
		Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválido"));
		Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")).getText());
		
	}
	
}
