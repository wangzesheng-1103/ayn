package com.zjwaty.arithmetic.controller;

import com.zjwaty.arithmetic.entity.Exercise;
import com.zjwaty.arithmetic.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/main")
    public String main(HttpSession session, Model model) {
        String username = (String) session.getAttribute("currentUser");
        if (username == null) {
            return "redirect:/user/toLogin";
        }
        model.addAttribute("username", username);
        return "index";
    }

    @GetMapping("/generate")
    public String generateExercise(@RequestParam(defaultValue = "10") int count,
                                   HttpSession session, Model model) {
        String username = (String) session.getAttribute("currentUser");
        if (username == null) {
            return "redirect:/user/toLogin";
        }

        if (count < 1 || count > 50) {
            model.addAttribute("error", "题目数量需在1-50之间");
            return "index";
        }

        Exercise exercise = exerciseService.generateExercise(username, count);
        model.addAttribute("exercise", exercise);
        return "exercise";
    }

    @PostMapping("/submit")
    public String submitExercise(Exercise exercise, HttpSession session, Model model) {
        String username = (String) session.getAttribute("currentUser");
        if (username == null) {
            return "redirect:/user/toLogin";
        }

        exercise.setUsername(username);
        Exercise result = exerciseService.submitExercise(exercise);
        model.addAttribute("result", result);
        return "result";
    }

    @GetMapping("/history")
    public String getHistory(HttpSession session, Model model) {
        String username = (String) session.getAttribute("currentUser");
        if (username == null) {
            return "redirect:/user/toLogin";
        }

        List<Exercise> history = exerciseService.getHistory(username);
        model.addAttribute("history", history);
        return "history";
    }
}