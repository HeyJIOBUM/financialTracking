package org.heyjiobum.fintrackbackend.app.controller;

import lombok.AllArgsConstructor;
import org.heyjiobum.fintrackbackend.app.entity.Budget;
import org.heyjiobum.fintrackbackend.app.service.BudgetService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class BudgetsController {
    private final BudgetService budgetService;
    
    @GetMapping("/me/budgets")
    public List<Budget> getUserBudgets() {
        String username = this.getCurrentlyAuthenticatedUsername();
        return budgetService.findBudgetsByUsername(username);
    }

    @PostMapping("/me/budgets")
    public List<Budget> addUserBudgets(@RequestBody Budget budget) {
        String username = this.getCurrentlyAuthenticatedUsername();
        return budgetService.addBudgetsToUserByUsername(budget, username);
    }

    @DeleteMapping("/me/budgets/{budgetId}")
    public void deleteUserBudget(@PathVariable long budgetId) {
        String username = this.getCurrentlyAuthenticatedUsername();
        budgetService.deleteBudgetByBudgetId(budgetId, username);
    }

    @PutMapping("/me/budgets/{budgetId}")
    public void updateUserBudget(@PathVariable long budgetId, @RequestBody Budget budget) {
        String username = this.getCurrentlyAuthenticatedUsername();
        budgetService.updateBudgetByBudgetId(budgetId, budget, username);
    }
    
    private String getCurrentlyAuthenticatedUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
