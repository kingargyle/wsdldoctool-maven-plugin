package org.intalio.wsdldoctool;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.Arrays;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.ebayopensource.turmeric.tools.annoparser.driver.Driver;

/**
 * Goal that generates documentation for a given set of WSDLs.
 * 
 * @goal generate-documentation
 * 
 * @phase process-resources @
 */
public class WSDLDocToolMojo extends AbstractMojo {

	/**
	 * WSDL Files to Include
	 * 
	 * @parameter alias="includes"
	 * @required
	 */
	private String[] includes;

	/**
	 * WSDL Files to Include
	 * 
	 * @parameter alias="excludes"
	 * @optional
	 */
	private String[] excludes;

	/**
	 * Output directory location for the generated documentation.
	 * 
	 * @parameter expression="${wsdldoc.outputDir}"
	 *            default-value="${project.build.directory}/wsdldoc/documentation"
	 * @optional
	 */

	private final String outputDir = "${project.build.directory}/wsdldoc/documentation";

	/**
	 * The path the configuration XML fle.
	 * 
	 * @parameter expression="${wsdldoc.configPath}"
	 * @optional
	 */
	private final String configPath = "";

	/**
	 * The path to the css file to be used.
	 * 
	 * @parameter expression="$wsdldoc.cssPath}"
	 * @optional
	 */
	private final String cssPath = "";

	/**
	 * 
	 * {@inheritDoc}
	 */

	public void execute() throws MojoExecutionException {

		if (excludes != null) {

		}

		Driver driver = new Driver(outputDir, configPath,
				Arrays.asList(includes), cssPath);
		driver.process();
	}

	public void setExcludes(String[] excludes) {
		this.excludes = excludes;
	}

	public void setIncludes(String[] includes) {
		this.includes = includes;
	}

	public String[] getIncludes() {
		return includes;
	}

	public String[] getExcludes() {
		return excludes;
	}

	public String getConfigPath() {
		return configPath;
	}

	public String getCSSPath() {
		return cssPath;
	}

	public String getOutputDir() {
		return outputDir;
	}
}
