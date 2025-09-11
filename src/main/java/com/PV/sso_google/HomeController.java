package com.PV.sso_google;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	 public String index(@AuthenticationPrincipal OidcUser user, Model model) {
	   if (user != null) {
	     String name = user.getFullName() != null ? user.getFullName() : user.getEmail();
	     model.addAttribute("name", name);
	     return "welcome";
	   }
	   return "index";
	 }
}