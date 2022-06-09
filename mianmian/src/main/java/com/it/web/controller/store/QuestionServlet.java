package com.it.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.it.domain.store.Catalog;
import com.it.domain.store.Company;
import com.it.domain.store.Question;
import com.it.service.store.CompanyService;
import com.it.service.store.QuestionService;
import com.it.service.store.impl.QuestionServiceImpl;
import com.it.utils.BeanUtil;
import com.it.web.controller.BaseServlet;
import com.mysql.cj.util.StringUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

// uri:/store/question?operation=list
@WebServlet("/store/question")
public class QuestionServlet extends BaseServlet {
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
            try {
                this.save(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("toEdit".equals(operation))
        {
            this.toEdit(req, resp);
        }else if("edit".equals(operation))
        {
            try {
                this.edit(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if("delete".equals(operation))
        {
            this.delete(req, resp);
        }else if("toTestFileUpload".equals(operation))
        {
            this.toTestFileUpload(req, resp);
        }else if("testFileUpload".equals(operation))
        {
            try {
                this.testFileUpload(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if("toExport".equals(operation))
        {
            try {
                this.toExport(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
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

        QuestionService questionService =new QuestionServiceImpl();

        PageInfo all =questionService.findAll(page,size);

        req.setAttribute("page",all);

        req.getRequestDispatcher("/WEB-INF/pages/store/question/list.jsp").forward(req,resp);
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Company> companyList = companyService.findAll();

        req.setAttribute("companyList",companyList);

        List<Catalog> catalogList =catalogService.findAll();

        req.setAttribute("catalogList",catalogList);

        req.getRequestDispatcher("/WEB-INF/pages/store/question/add.jsp").forward(req,resp);
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if(ServletFileUpload.isMultipartContent(req)) {
            DiskFileItemFactory diskfactory = new DiskFileItemFactory();

            ServletFileUpload fileUpload = new ServletFileUpload(diskfactory);
            List<FileItem> fileItems = fileUpload.parseRequest(req);

            Question question = BeanUtil.fillBean(fileItems,Question.class);


            boolean flag= false;
            for(FileItem fileItem :fileItems)
            {
                if(!StringUtils.isNullOrEmpty(fileItem.getName()))
                {
                    flag=true;
                    break;
                }
            }

            String picture = questionService.save(question,flag);

            for( FileItem fileItem :fileItems)
            {
                if(!fileItem.isFormField())
                {
                    fileItem.write(new File(this.getServletContext().getRealPath("upload/"),picture));


                }
               // System.out.println(fileItem);
            }
        }

        resp.sendRedirect(req.getContextPath()+"/store/question?operation=list");
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");

        Question question = questionService.findById(id);

        req.setAttribute("question",question);

        List<Company> companyList = companyService.findAll();

        req.setAttribute("companyList",companyList);

        List<Catalog> catalogList =catalogService.findAll();

        req.setAttribute("catalogList",catalogList);

        req.getRequestDispatcher("/WEB-INF/pages/store/question/update.jsp").forward(req,resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if(ServletFileUpload.isMultipartContent(req)) {
            DiskFileItemFactory diskfactory = new DiskFileItemFactory();

            ServletFileUpload fileUpload = new ServletFileUpload(diskfactory);
            List<FileItem> fileItems = fileUpload.parseRequest(req);

            Question question = BeanUtil.fillBean(fileItems,Question.class);

            boolean flag= false;
            for(FileItem fileItem :fileItems)
            {
                if(!StringUtils.isNullOrEmpty(fileItem.getName()))
                {
                    flag=true;
                    break;
                }
            }
            questionService.update(question,flag);

            for( FileItem fileItem :fileItems)
            {
                if(!fileItem.isFormField())
                {
                    fileItem.write(new File(this.getServletContext().getRealPath("upload/"),question.getId()));

                }
                // System.out.println(fileItem);
            }

        }


        resp.sendRedirect(req.getContextPath()+"/store/question?operation=list");
    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        Question question = BeanUtil.fillBean(req,Question.class,"yyyy-MM-dd");

        questionService.delete(question);

        resp.sendRedirect(req.getContextPath()+"/store/question?operation=list");
    }

    private void testFileUpload(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if(ServletFileUpload.isMultipartContent(req))
        {
            DiskFileItemFactory diskfactory =new DiskFileItemFactory();

            ServletFileUpload fileUpload =new ServletFileUpload(diskfactory);

            List<FileItem> fileItems = fileUpload.parseRequest(req);
            for( FileItem fileItem :fileItems)
            {
                if(!fileItem.isFormField())
                {
                    fileItem.write(new File(this.getServletContext().getRealPath("upload/"),fileItem.getName()));


                }
                System.out.println(fileItem);
            }


        }



    }

    private void toTestFileUpload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/pages/store/question/testFileUpload.jsp").forward(req,resp);


    }

    private void toExport(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        questionService.getReport();




    }

}
