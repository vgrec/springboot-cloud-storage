package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionController implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Object o,
            Exception e) {

        System.out.println("Exception: ");
        System.out.println(e);

        if (e instanceof MaxUploadSizeExceededException) {
            ModelAndView modelAndView = new ModelAndView("result.html");
            System.out.println("Updating the model");
            modelAndView.addObject("result_success", false);
            modelAndView.addObject("error_message", "Not possible to upload as the file size limit exceeded.");
            return modelAndView;
        }

        return new ModelAndView("home.html");
    }
}
