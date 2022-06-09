package com.it.web.controller;

import com.it.service.store.*;
import com.it.service.store.impl.*;
import com.it.service.system.DeptService;
import com.it.service.system.UserService;
import com.it.service.system.impl.DeptServiceImpl;
import com.it.service.system.impl.UserServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class BaseServlet extends HttpServlet {

    protected CompanyService companyService  ;
    protected DeptService deptService  ;
    protected UserService userService  ;
    protected CourseService courseService  ;
    protected CatalogService catalogService  ;
    protected QuestionService questionService  ;
    protected QuestionItemService questionItemService  ;
    @Override
    public void init() throws ServletException {
        deptService= new DeptServiceImpl();
        companyService= new CompanyServiceImpl();
        userService= new UserServiceImpl();
        courseService= new CourseServiceImpl();
        catalogService= new CatalogServiceImpl();
        questionService=new QuestionServiceImpl();
        questionItemService=new QuestionItemServiceImpl();
    }
}
