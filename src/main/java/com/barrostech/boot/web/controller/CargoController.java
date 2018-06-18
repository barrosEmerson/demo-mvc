package com.barrostech.boot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.barrostech.boot.domian.Cargo;
import com.barrostech.boot.domian.Departamento;
import com.barrostech.boot.service.CargoService;
import com.barrostech.boot.service.DepartamentoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	@Autowired
	private CargoService cargoService;
	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		
		return "cargo/cadastro";
	}
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("cargos", cargoService.buscarTodos());
		return "cargo/lista";
	}
	@PostMapping("/salvar")
	public String salvar(@Valid Cargo cargo,BindingResult result ,RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "cargo/cadastro";
		}
		cargoService.salvar(cargo);
		attr.addFlashAttribute("success", "Cargo inserido com sucesso.");
		return "redirect:/cargos/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cargo", cargoService.buscarPorId(id));
		return "cargo/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Cargo cargo, BindingResult result,RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "cargo/cadastro";
		}
		cargoService.editar(cargo);
		attr.addFlashAttribute("success", "Cargo atualizado com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		
		if (cargoService.cargoTemFuncionario(id)) {
			model.addAttribute("fail", "cargo não removido. Possui funcionario(s) vinculado(s).");
		} else {
			cargoService.excluir(id);
			model.addAttribute("success", "Cargo excluído com sucesso.");
		}
		
		return listar(model);
	}
	@ModelAttribute("departamentos")
	public List<Departamento> listaDeDepartamentos(){
		return departamentoService.buscarTodos();
	}
}
