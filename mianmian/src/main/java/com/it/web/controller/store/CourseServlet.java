package com.it.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.it.domain.store.Course;
import com.it.service.store.CourseService;
import com.it.service.store.impl.CourseServiceImpl;
import com.it.utils.BeanUtil;
import com.it.web.controller.BaseServlet;
import com.mysql.cj.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// uri:/store/course?operation=list
@WebServlet("/store/course")
public class CourseServlet extends BaseServlet {
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

        CourseService courseService =new CourseServiceImpl();

        PageInfo all =courseService.findAll(page,size);

        req.setAttribute("page",all);

        req.getRequestDispatcher("/WEB-INF/pages/store/course/list.jsp").forward(req,resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/store/course/add.jsp").forward(req,resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Course course = BeanUtil.fillBean(req,Course.class,"yyyy-MM-dd");

        courseService.save(course);

        resp.sendRedirect(req.getContextPath()+"/store/course?operation=list");
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        CourseService courseService =new CourseServiceImpl();
        Course course = courseService.findById(id);

        req.setAttribute("course",course);

        req.getRequestDispatcher("/WEB-INF/pages/store/course/update.jsp").forward(req,resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Course course = BeanUtil.fillBean(req,Course.class,"yyyy-MM-dd");

        courseService.update(course);

        resp.sendRedirect(req.getContextPath()+"/store/course?operation=list");
    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        Course course = BeanUtil.fillBean(req,Course.class,"yyyy-MM-dd");

        courseService.delete(course);

        resp.sendRedirect(req.getContextPath()+"/store/course?operation=list");
    }
}
