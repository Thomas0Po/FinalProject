package Ressources.Tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javafx.application.Platform;
import javafx.stage.Stage;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.PortableInterceptor.SUCCESSFUL;

import com.sun.javafx.fxml.builder.JavaFXSceneBuilder;

import Abstractions.Models.ShortCutBindModel;
import Abstractions.Models.ShortCutsListModel;
import Controllers.MainController;

public class MainControllerTest {

	MainController controller;
	
	@Before
	public void setUp() throws Exception {
		Platform.isFxApplicationThread();
		
		controller = new MainController();
		controller.launch(new Stage());
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitSubControllers() {
		assertEquals(true, controller.initSubControllers());
	}

	@Test
	public void testQuit() {
		
		fail("Not yet implemented");
	}

	
	@Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
	@Test
	public void testChangeLanguage() {
		File f = new File("src/Ressources/Configs/MainConfig");
		InputStream input = null;
//		URL url = getClass().getClassLoader().getResource("Ressources/Configs/MainConfig");
		try {
			input = new FileInputStream(f);// url.openStream();
			Properties prop = new Properties();
			prop.load(input);
			String currentl = prop.getProperty("currentLanguage");
			input.close();
			assertTrue(currentl.equals("FR") || currentl.equals("EN"));
			if (currentl.equals("FR"))
				controller.changeLanguage("EN");
			else
				controller.changeLanguage("FR");
			input = new FileInputStream(f);
//			input = url.openStream();
			prop.load(input);
			String newl = prop.getProperty("currentLanguage");
			if (currentl.equals("FR"))
				assertTrue(newl.equals("EN"));
			else
				assertTrue(newl.equals("FR"));
			
		} catch (IOException e) {
			e.printStackTrace();
			fail("testChangeLanguageFail : ioException");
		}catch(Exception e){
			e.printStackTrace();
			fail("testChangeLanguageFail");
		}finally{
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Test
	public void testSetShortcuts() {
		ShortCutsListModel shortcutlist = new ShortCutsListModel();
		Map<String, ShortCutBindModel> map = shortcutlist.getShortcuts();
		
		fail("Not yet implemented");
	}

}
