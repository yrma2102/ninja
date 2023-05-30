package com.coding.web.controllers;

import java.util.Random;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home(HttpSession sesion, Model modelView) {
		modelView.addAttribute("gold", sesion.getAttribute("gold"));
		if(sesion.getAttribute("actividades") != null) {
			modelView.addAttribute("actividades", sesion.getAttribute("actividades"));
		}
		
		return "Gold.jsp";
	}
	
	@PostMapping("/")
	public String enviarGold(HttpSession sesion, @RequestParam(value = "lugar", required = false) String lugar) {

		int gold = 0;
		ArrayList<String> actividades = new ArrayList<String>();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("MMMM D Y h:mm a");
		
		// iniciar gold key
		if (sesion.getAttribute("gold") == null) {
			sesion.setAttribute("gold", gold);
		} else {
			gold = (int) sesion.getAttribute("gold");
		}
		
		if (sesion.getAttribute("actividades") == null) {
			sesion.setAttribute("actividades", actividades);
		} else {
			actividades = (ArrayList<String>) sesion.getAttribute("actividades");
		}
		
		if(gold <= -5) {
			return "Jail.jsp";
		}

		if (lugar.equals("farm")) {
			int cantidad = new Random().nextInt(11) + 10;
			gold += cantidad;
			actividades.add(0, "You entered a farm and earned " + cantidad + " gold. (" + formatoFecha.format(new Date())+")");
			sesion.setAttribute("actividades", actividades);
			sesion.setAttribute("gold", gold);
			return "redirect:/";
		}

		if (lugar.equals("cave")) {
			int cantidad = new Random().nextInt(5,11);
			gold += cantidad;
			actividades.add(0,"You entered a cave and earned " + cantidad + " gold. ("+ formatoFecha.format(new Date())+")");
			sesion.setAttribute("actividades", actividades);
			sesion.setAttribute("gold", gold);
			return "redirect:/";
		}
		
		if (lugar.equals("house")) {
			int cantidad = new Random().nextInt(2,6);
			gold += cantidad;
			actividades.add(0,"You entered a house and earned " + cantidad + " gold. (" + formatoFecha.format(new Date())+")");
			sesion.setAttribute("actividades", actividades);
			sesion.setAttribute("gold", gold);
			return "redirect:/";
		}
		
		if (lugar.equals("casino")) {
			int cantidad = new Random().nextInt(-50,51);
			if(cantidad >=0) {
				gold += cantidad;
				actividades.add(0,"You entered a casino and earned " + cantidad + " gold. ("+ formatoFecha.format(new Date())+")");	
			}else {
				gold += cantidad;
				actividades.add(0,"You entered a casino and lost " + cantidad + " gold. Ouch ("+ formatoFecha.format(new Date())+")");
			}
			sesion.setAttribute("actividades", actividades);
			sesion.setAttribute("gold", gold);
			return "redirect:/";
		}
		
		return "redirect:/";
	}
	@GetMapping("/reset")
	public String home(HttpSession sesion) {
		sesion.invalidate();
		return "redirect:/";
	}
}
