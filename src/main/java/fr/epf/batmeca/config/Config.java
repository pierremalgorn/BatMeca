package fr.epf.batmeca.config;

import java.io.File;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:value.properties")
public class Config {

	@Autowired
	Environment env;

	private String project;
	private String resource;

	@PostConstruct
	public void init() {
		project = env.getProperty("project.root") + File.separator
				+ env.getProperty("project.name");
		resource = env.getProperty("resource.path");
	}

	public String getProjectPath() {
		return project;
	}

	public String getResourcePath() {
		return resource;
	}
}
