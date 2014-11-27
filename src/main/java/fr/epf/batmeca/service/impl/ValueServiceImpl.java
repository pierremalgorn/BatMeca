package fr.epf.batmeca.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import fr.epf.batmeca.service.IValueService;

@Service
public class ValueServiceImpl implements IValueService {

	@Value("${project.name}")
	private String name;
	@Value("${project.root}")
	private String root;
	@Value("${resource.path}")
	private String resourcePath;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getRoot() {
		return root;
	}

	@Override
	public String getResourcePath() {
		return resourcePath;
	}
}
