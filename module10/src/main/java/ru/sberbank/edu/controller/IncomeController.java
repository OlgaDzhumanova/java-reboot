package ru.sberbank.edu.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sberbank.edu.entity.Income;

@Controller
public class IncomeController {

    private Income income;

    @Autowired
    public void setIncome(Income income) {
        this.income = income;
    }

    @GetMapping("/finance")
    public String finance(Model model) {
        model.addAttribute("finance", income);
        return "finance";
    }

    @PostMapping("/finance")
    public String edit(@Valid @ModelAttribute("finance") Income income, BindingResult result) {
        if (income.getSum() < 50000) {
            return "errors/error";
        }
        if (result.hasErrors()) {
            return "errors/invalidFormat";
        }
        return "counter";
    }
}

