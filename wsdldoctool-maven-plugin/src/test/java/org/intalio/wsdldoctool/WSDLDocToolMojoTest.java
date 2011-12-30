package org.intalio.wsdldoctool;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;
import org.codehaus.plexus.util.FileUtils;

public class WSDLDocToolMojoTest extends AbstractMojoTestCase {

	File projectBaseDir;

	/**
	 * @see junit.framework.TestCase#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		projectBaseDir = new File(getBasedir(), toOS("target/tests/"
				+ getTestMojoDirName()));
		FileUtils.deleteDirectory(projectBaseDir);

		// copy src/test/resources/${dirname} into target dir for working with
		// it in a test case
		// Don't want to mess up the source tree with accidents and bugs.
		File srcProjectDir = new File(getBasedir(), toOS("src/test/resources/"
				+ getTestMojoDirName()));
		FileUtils.copyDirectoryStructure(srcProjectDir, projectBaseDir);
	}

	protected String getTestMojoDirName() {
		return "unit/basic-test";
	}

	protected String getTestMojoGoal() {
		return "generate-documentation";
	}

	protected String toOS(String path) {
		return FilenameUtils.separatorsToSystem(path);
	}

	/**
	 * @throws Exception
	 */
	public void testMojoGoal() throws Exception {
		File testPom = new File(projectBaseDir, "basic-test-plugin.xml");

		WSDLDocToolMojo mojo = (WSDLDocToolMojo) lookupMojo(getTestMojoGoal(),
				testPom);

		assertNotNull(mojo);
	}

	public void testDefaultOutputDir() throws Exception {
		File testPom = new File(projectBaseDir, "basic-test-plugin.xml");

		WSDLDocToolMojo mojo = (WSDLDocToolMojo) lookupMojo(getTestMojoGoal(),
				testPom);

		assertEquals("Output Directory did not match expected value.",
				"${project.build.directory}/wsdldoc/documentation",
				mojo.getOutputDir());
	}

	public void testIncludedWSDLFiles() throws Exception {
		File testPom = new File(projectBaseDir, "basic-test-plugin.xml");

		WSDLDocToolMojo mojo = (WSDLDocToolMojo) lookupMojo(getTestMojoGoal(),
				testPom);

		assertNotNull(mojo.getIncludes());
		for (String value : mojo.getIncludes()) {
			if (value.equals("NewWSDLFile.wsdl")) {
				return;
			}
		}
		fail("Did not find expected NewWSDLFile.wsdl entry");
	}

}
