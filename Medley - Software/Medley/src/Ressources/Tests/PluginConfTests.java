package Ressources.Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Models.PluginConf;

public class PluginConfTests {

	PluginConf conf = new PluginConf();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		conf.setToHide("true");
		assertTrue(conf.isToHide());
		conf.setToCharge("false");
		assertTrue(!conf.isToCharge());
		conf.setToRun("true");
		assertTrue(conf.isToRun());
	}

}
