package com.udacity.jwdnd.course1.cloudstorage;

import org.springframework.ui.Model;

public class Utils {

    public static void setResult(Model model, boolean success, String errorMessage) {
        model.addAttribute("result_success", success);
        if (!success) {
            model.addAttribute("error_message", errorMessage);
        }
    }
}
