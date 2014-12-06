package fr.epf.batmeca.webservice;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.epf.batmeca.entity.Test;
import fr.epf.batmeca.service.IFileService;
import fr.epf.batmeca.service.ITestService;

@RestController
public class ShowWebservice {

	@Autowired
	private ITestService testService;
	@Autowired
	private IFileService fileService;

	@RequestMapping(value = "/ShowHistoric", method = RequestMethod.GET)
	protected String showHistoricGet(@RequestParam("id") String idValue) {
		int id = Integer.parseInt(idValue);
		Test test = testService.find(id);
		try {
			return fileService.readHistory(test);
		} catch (IOException e) {
			// TODO handle this exception
			e.printStackTrace();
			return e.getMessage();
		}
	}

	@RequestMapping(value = "/ShowResult", method = RequestMethod.GET)
	protected String showResultGet(@RequestParam("id") String idValue) {
		int id = Integer.parseInt(idValue);
		Test test = testService.find(id);
		try {
			return fileService.readResult(test);
		} catch (IOException e) {
			// TODO handle this exception
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
