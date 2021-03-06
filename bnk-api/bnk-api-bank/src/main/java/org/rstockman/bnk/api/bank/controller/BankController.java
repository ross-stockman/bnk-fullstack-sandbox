package org.rstockman.bnk.api.bank.controller;

import java.util.List;

import org.rstockman.bnk.api.bank.dto.BankRequestParams;
import org.rstockman.bnk.api.bank.dto.BankResource;
import org.rstockman.bnk.common.dao.SimpleDAO;
import org.rstockman.bnk.common.exceptions.ExceptionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banks")
public class BankController {

	@Autowired
	private SimpleDAO<BankResource, BankRequestParams, String, String> dao;

	@GetMapping("/{id}")
	public BankResource get(@PathVariable String id) {
		var obj = dao.get(id);
		if (obj.isEmpty()) {
			throw ExceptionFactory.resourceNotFound();
		}
		return obj.get();
	}

	@GetMapping
	public List<BankResource> getAll(BankRequestParams params) {
		return dao.getAll(params);
	}

	@PostMapping
	public String create(@RequestBody BankResource obj) {
		return dao.create(obj);
	}

	@PutMapping("/{id}")
	public void put(@PathVariable String key, @RequestBody BankResource obj) {
		dao.put(key, obj);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String key) {
		dao.delete(key);
	}
}
