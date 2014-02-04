package selenium;

import junit.framework.TestCase;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleniumException;

public class SeleniumHelloWorldExampleTest extends TestCase {
	private DefaultSelenium selenium;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		selenium = createSeleniumClient("http://localhost:9090/");
		selenium.start();
	}

	@Override
	public void tearDown() throws Exception {
		selenium.stop();
		super.tearDown();
	}

	protected DefaultSelenium createSeleniumClient(String url) throws Exception {
		return new DefaultSelenium("localhost", 4444, "*firefox", url);
	}

	public void testHelloWorld() throws Exception {
		try {
			selenium.open("http://localhost:9090/mavenweb/");
			assertEquals("news", selenium.getText("//h1"));
		} catch (SeleniumException ex) {
			fail(ex.getMessage());
			throw ex;
		}
	}
}
