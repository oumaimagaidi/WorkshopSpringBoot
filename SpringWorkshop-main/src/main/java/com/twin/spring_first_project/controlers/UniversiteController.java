package com.twin.spring_first_project.controlers;


import com.twin.spring_first_project.services.IUniversiteService;
import com.twin.spring_first_project.services.UniversiteService;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UniversiteController {
    IUniversiteService universiteService;
}
