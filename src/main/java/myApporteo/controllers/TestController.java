package myApporteo.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('APPORTEUR') or hasRole('ADMIN') or hasRole('PARTENAIRE')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/apporteur")
  @PreAuthorize("hasRole('APPORTEUR')")
  
    public String apporteurrAccess() {
        return "APPORTEUR Board.";
    }
    @GetMapping("/partenaire")
    @PreAuthorize("hasRole('PARTENAIRE')")
    
      public String partenaireAccess() {
          return "APPORTEUR Board.";
      }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}