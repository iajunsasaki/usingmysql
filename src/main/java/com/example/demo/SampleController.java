package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.data.Fruit;
import com.example.demo.repositories.FruitsRepository;


@Controller
public class SampleController {
	
	@Autowired
	private FruitsRepository repository;
	
	@GetMapping("/")
	public ModelAndView index(ModelAndView mv) {
		System.out.println("test");
		mv.setViewName("index");
		mv.addObject("title", "サンプル");
		mv.addObject("msg", "これはサンプルのメッセージです。");
		return mv;
	}
	
	@GetMapping("/fruits")
	public ModelAndView fruits(ModelAndView mv) {
		System.out.println("fruits/index");
		List<Fruit> fruits = this.repository.findAll();
		mv.setViewName("fruits/index");
		mv.addObject("title", "フルーツ在庫");
		mv.addObject("msg", "フルーツ在庫を管理します");
		mv.addObject("data", fruits);
		return mv;
	}
	
	@GetMapping("/fruits/add")
	public ModelAndView add(ModelAndView mv) {
		System.out.println("fruits/add");
		mv.setViewName("fruits/add");
		mv.addObject("title", "新規登録");
		mv.addObject("msg", "新たに情報を登録します");
		return mv;
	}
	
	@GetMapping("/fruits/update")
	public ModelAndView update(ModelAndView mv, @RequestParam("id") Integer id) {
		System.out.println("fruits/update");
		mv.setViewName("fruits/update");
		mv.addObject("title", "更新");
		mv.addObject("msg", "ID=" + id + "の登録情報を更新します");
		Optional<Fruit> form = this.repository.findById(id);
		mv.addObject("form", form.orElse(new Fruit()));
		return mv;
	}

	@GetMapping("/fruits/delete")
	public ModelAndView delete(ModelAndView mv, @RequestParam("id") Integer id) {
		System.out.println("fruits/delete");
		mv.setViewName("fruits/delete");
		mv.addObject("title", "削除");
		mv.addObject("msg", "ID=" + id + "の登録情報を削除します");
		Optional<Fruit> form = this.repository.findById(id);
		mv.addObject("form", form.orElse(new Fruit()));
		return mv;
	}

	@PostMapping("/fruits/add")
	public String add(Fruit fruit) {
		System.out.println("fruits/add(post)");
		repository.saveAndFlush(fruit);
		return "redirect:/fruits";
	}

	@PostMapping("/fruits/update")
	@Transactional(readOnly=false)
	public String update(Fruit fruit) {
		System.out.println("fruits/update(post)");
		repository.saveAndFlush(fruit);
		return "redirect:/fruits";
	}

	@PostMapping("/fruits/delete")
	@Transactional(readOnly=false)
	public String delete(Fruit fruit) {
		System.out.println("fruits/delete(post)");
		repository.delete(fruit);
		return "redirect:/fruits";
	}
}
