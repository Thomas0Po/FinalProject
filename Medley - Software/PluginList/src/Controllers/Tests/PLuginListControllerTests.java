package Controllers.Tests;

import static org.junit.Assert.*;
import javafx.scene.control.ListView;

import org.junit.Before;
import org.junit.Test;

import plugincontract.APlugin;
import Controllers.PluginListController;

public class PLuginListControllerTests {

	PluginListController controller;
	
	@Before
	   public void setUp() {
	      controller = new PluginListController();
	      try{
	    	  controller.init();
	      } catch (Exception e){
	    	  
	      }
	      controller.initView();
	   }

	@Test
	public void testInitObserver() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testGetMainComponent() {
		ListView<APlugin> pl = new ListView<APlugin>();
		assertEquals(pl, controller.getMainComponent());

		fail("Not yet implemented");
	}

	@Test
	public void testGetMenuBarComponent() {
		fail("Not yet implemented");
	}

	@Test
	public void testOpenPluginClick() {
		fail("Not yet implemented");
	}

	@Test
	public void testInit() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitView() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMenuBar() {
		fail("Not yet implemented");
	}

	@Test
	public void testClose() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyPluginListClick() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsPluginAlreadyInList() {
		assertTrue(this.controller.isPluginAlreadyInList("PluginTest"));
		assertFalse(this.controller.isPluginAlreadyInList("NotInList"));
	}

	@Test
	public void testUpdateView() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveAndApplyPluginList() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemovePluginsConf() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddPluginsConf() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetLanguage() {
		this.controller.setLanguage("");
		fail("Not yet implemented");
	}

	@Test
	public void testGetVersion() {
		fail("Not yet implemented");
	}

}
