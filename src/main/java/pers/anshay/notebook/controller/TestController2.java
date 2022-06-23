package pers.anshay.notebook.controller;

import pers.anshay.notebook.service.ISolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author machao
 * @date 2022/5/19
 */
@RestController
@RequestMapping("/api/fib")
public class TestController2 {
	@Autowired
	private ISolutionService service;

	@GetMapping("/index/{index}")
	public ResponseEntity getFib(@PathVariable("index") int index) throws Exception {
		int result = service.getResult(index);
		return ResponseEntity.ok(result);
	}
}
