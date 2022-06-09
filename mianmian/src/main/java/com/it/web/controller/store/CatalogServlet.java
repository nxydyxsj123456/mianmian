package com.it.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.it.domain.store.Catalog;
import com.it.domain.store.Course;
import com.it.service.store.CatalogService;
import com.it.service.store.impl.CatalogServiceImpl;
import com.it.utils.BeanUtil;
import com.it.web.controller.BaseServlet;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// uri:/store/catalog?operation=list
@WebServlet("/store/catalog")
public class CatalogServlet extends BaseServlet {
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

        CatalogService catalogService =new CatalogServiceImpl();

        PageInfo all =catalogService.findAll(page,size);

        req.setAttribute("page",all);

        req.getRequestDispatcher("/WEB-INF/pages/store/catalog/list.jsp").forward(req,resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Course> course = courseService.findAll();

        req.setAttribute("courseList",course);

        req.getRequestDispatcher("/WEB-INF/pages/store/catalog/add.jsp").forward(req,resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Catalog catalog = BeanUtil.fillBean(req,Catalog.class,"yyyy-MM-dd");

        catalogService.save(catalog);

        resp.sendRedirect(req.getContextPath()+"/store/catalog?operation=list");
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");

        Catalog catalog = catalogService.findById(id);

        req.setAttribute("catalog",catalog);

        List<Course> course = courseService.findAll();

        req.setAttribute("courseList",course);

        req.getRequestDispatcher("/WEB-INF/pages/store/catalog/update.jsp").forward(req,resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Catalog catalog = BeanUtil.fillBean(req,Catalog.class,"yyyy-MM-dd");

        catalogService.update(catalog);

        resp.sendRedirect(req.getContextPath()+"/store/catalog?operation=list");
    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        Catalog catalog = BeanUtil.fillBean(req,Catalog.class,"yyyy-MM-dd");

        catalogService.delete(catalog);

        resp.sendRedirect(req.getContextPath()+"/store/catalog?operation=list");
    }
}
