package fr.epf.batmeca.webservice;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.epf.batmeca.entity.Test;
import fr.epf.batmeca.handler.FolderHandler;
import fr.epf.batmeca.service.ITestService;

@RestController
public class ShowController {

	@Autowired
	private ITestService testService;

	@RequestMapping(value = "/ShowHistoric", method = RequestMethod.GET)
	protected String showHistoricGet(@RequestParam("id") String idValue) {
		int id = Integer.parseInt(idValue);
		Test test = testService.find(id);
		FolderHandler f = new FolderHandler();
		String data = null;

		try {
			data = f.readHistoric(test); // TODO handle this differently
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	@RequestMapping(value = "/ShowResult", method = RequestMethod.GET)
	protected String showResultGet(@RequestParam("id") String idValue) {
		int id = Integer.parseInt(idValue);
		Test test = testService.find(id);
		FolderHandler f = new FolderHandler();
		String data = null;

		try {
			data = f.readResult(test); // TODO handle this differently
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}
}
