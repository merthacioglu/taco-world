package org.mhacioglu.tacoworld.controller;

import org.mhacioglu.tacoworld.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/deleteOrders")
    public String deleteOrders() {
        adminService.deleteAllOrders();
        return "redirect:/admin";
    }


}
