package service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ValueService {

	@Value("${project.name}")
	private String name;
	@Value("${project.root}")
	private String root;
	@Value("${resource.path}")
	private String resourcePath;

	public String getName() {
		return name;
	}

	public String getRoot() {
		return root;
	}

	public String getResourcePath() {
		return resourcePath;
	}
}
