package edu.mum.cs.projects.attendance.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class HelpController {

	@RequestMapping("/help")
	public String help(HttpServletRequest request, Model model) {
		return "help";

	}

}