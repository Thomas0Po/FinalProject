package Ressources.Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Models.PluginConf;
import Models.PluginsConf;

public class PluginsConfTests {
	PluginsConf conf = new PluginsConf();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCheckIfPluginExist() {
		PluginConf plugin = new PluginConf();
		plugin.setName("fakePlugin");
		plugin.setPath("./");
		
		conf.addPluginConf(plugin);
		//fail("Not yet implemented");
	}

}
