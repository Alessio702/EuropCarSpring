package com.example.demo.rest;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Area;
import com.example.demo.service.AreaService;

@RestController
@RequestMapping("/Rest/Area")
public class AreaRest {

	@Autowired
	AreaService areaSer;


	@GetMapping
	public Iterable<Area> getAll() {
		return areaSer.getAllAree();
	}

	@GetMapping(value = "/{id}")
	public Area getByID(@PathVariable("id") Integer id) {
		Area oarea = areaSer.getAreaById(id);
		if (oarea == null) {
			throw new EntityNotFoundException("Missing Entity");
		}
		return oarea;
	}

	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable("id") Integer id) {
		areaSer.deleteAreaById(id);

	}

	@PostMapping
	public Area create(@RequestBody Area oarea) {
		return areaSer.saveOrUpdate(oarea);
	}

	@PutMapping("/{id}")
	public Area update(@PathVariable("id") Integer id, @RequestBody Area oarea) {
		return areaSer.saveOrUpdate(oarea);
	}
}