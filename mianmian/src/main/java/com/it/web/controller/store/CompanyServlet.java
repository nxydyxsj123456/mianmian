package com.it.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.it.domain.store.Company;
import com.it.service.store.CompanyService;
import com.it.service.store.impl.CompanyServiceImpl;
import com.it.utils.BeanUtil;
import com.it.web.controller.BaseServlet;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// uri:/store/company?operation=list
@WebServlet("/store/company")
public class CompanyServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String operation =req.getParameter("operation");
       req.setCharacterEncoding("UTF-8");
       resp.setContentType("text/html;charset=UTF-8");

        if("list".equals(operation)){
           this.list(req, resp);

       }else if("toAdd".equals(operation))
        {
            this.toAdd(req, resp);
        }else if("save".equals(operation))
        {
            this.save(req, resp);
        }else if("toEdit".equals(operation))
        {
            this.toEdit(req, resp);
        }else if("edit".equals(operation))
        {
            this.edit(req, resp);
        }else if("delete".equals(operation))
        {
            this.delete(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doGet(req, resp);
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page=1;
        int size=5;
        String tmpPage= req.getParameter("page");
        String tmpsize=req.getParameter("size");
        if(!StringUtils.isNullOrEmpty(tmpPage))
        {
            page=Integer.parseInt(tmpPage);
        }
        if(!StringUtils.isNullOrEmpty(tmpsize))
        {
            size=Integer.parseInt(tmpsize);
        }

        CompanyService companyService =new CompanyServiceImpl();

        PageInfo all =companyService.findAll(page,size);

        req.setAttribute("page",all);

        req.getRequestDispatcher("/WEB-INF/pages/store/company/list.jsp").forward(req,resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/store/company/add.jsp").forward(req,resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Company company = BeanUtil.fillBean(req,Company.class,"yyyy-MM-dd");

        companyService.save(company);

        resp.sendRedirect(req.getContextPath()+"/store/company?operation=list");
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        CompanyService companyService =new CompanyServiceImpl();
        Company company = companyService.findById(id);

        req.setAttribute("company",company);

        req.getRequestDispatcher("/WEB-INF/pages/store/company/update.jsp").forward(req,resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Company company = BeanUtil.fillBean(req,Company.class,"yyyy-MM-dd");

        companyService.update(company);

        resp.sendRedirect(req.getContextPath()+"/store/company?operation=list");
    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        Company company = BeanUtil.fillBean(req,Company.class,"yyyy-MM-dd");

        companyService.delete(company);

        resp.sendRedirect(req.getContextPath()+"/store/company?operation=list");
    }
}
