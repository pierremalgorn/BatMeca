package fr.epf.batmeca.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:batmeca.properties")
public class Config {

	@Autowired
	private Environment env;

	private String scripts;
	private String resource;

	@PostConstruct
	public void init() {
		scripts = env.getProperty("scripts.path");
		resource = env.getProperty("resource.path");
	}

	public String getScriptsPath() {
		return scripts;
	}

	public String getResourcePath() {
		return resource;
	}
}
