package Ressources.Tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.omg.PortableInterceptor.SUCCESSFUL;

import Factory.FactoryMedleyPart;

public class FactoryMedleyPartTests {

	FactoryMedleyPart factory = new FactoryMedleyPart();
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFactory() {
		try{
			factory.init();
			assertTrue(factory.getNavigationController() != null);
			assertTrue(factory.getPluginListController() != null);
			assertTrue(factory.getWorkspaceController() != null);
		}catch (Exception e){
			fail("FactoryMedleyPart exception.");
			e.printStackTrace();
		}
	}

}
