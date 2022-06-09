package com.it.web.controller.store;

import com.github.pagehelper.PageInfo;
import com.it.domain.store.Catalog;
import com.it.domain.store.Company;
import com.it.domain.store.QuestionItem;
import com.it.service.store.QuestionItemService;
import com.it.service.store.impl.QuestionItemServiceImpl;
import com.it.utils.BeanUtil;
import com.it.web.controller.BaseServlet;
import com.mysql.cj.util.StringUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

// uri:/store/questionItem?operation=list
@WebServlet("/store/questionItem")
public class QuestionItemServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String operation =req.getParameter("operation");
       req.setCharacterEncoding("UTF-8");
       resp.setContentType("text/html;charset=UTF-8");

        if("list".equals(operation)){
           this.list(req, resp);

       }else if("saveOrUpdate".equals(operation))
        {
            try {
                this.saveOrUpdate(req, resp);
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

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doGet(req, resp);
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String questionId= req.getParameter("questionId");

        req.setAttribute("questionId",questionId);

        System.out.println(questionId);

        PageInfo all =questionItemService.findAll(questionId,1,100);
//        List<QuestionItem> listx = all.getList();

//        for( QuestionItem xxx   :listx)
//        {
//            System.out.println(xxx);
//        }

        req.setAttribute("page",all);

        req.getRequestDispatcher("/WEB-INF/pages/store/questionItem/list.jsp").forward(req,resp);
    }

//    private void toAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<Company> companyList = companyService.findAll();
//
//        req.setAttribute("companyList",companyList);
//
//        List<Catalog> catalogList =catalogService.findAll();
//
//        req.setAttribute("catalogList",catalogList);
//
//        req.getRequestDispatcher("/WEB-INF/pages/store/questionItem/add.jsp").forward(req,resp);
//    }

    private void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionItem questionItem = BeanUtil.fillBean(req,QuestionItem.class);

        System.out.println(questionItem);

        if(StringUtils.isNullOrEmpty(questionItem.getId()))
        {
            questionItemService.save(questionItem);
        }
        else
        {
            questionItemService.update(questionItem);

        }

        list(req,resp);
        //resp.sendRedirect(req.getContextPath()+"/store/questionItem?operation=list");
    }

    private void toEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");

        QuestionItem questionItem = questionItemService.findById(id);

        req.setAttribute("questionItem",questionItem);

        list(req,resp);
       //req.getRequestDispatcher("/WEB-INF/pages/store/questionItem/update.jsp").forward(req,resp);
    }

    private void edit(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        QuestionItem questionItem = BeanUtil.fillBean(req,QuestionItem.class);

        questionItemService.update(questionItem);

        list(req,resp);
//        resp.sendRedirect(req.getContextPath()+"/store/questionItem?operation=list");
    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String questionId= req.getParameter("questionId");
//
//        req.setAttribute("questionId",questionId);


        QuestionItem questionItem = BeanUtil.fillBean(req,QuestionItem.class,"yyyy-MM-dd");

        questionItemService.delete(questionItem);

        list(req,resp);
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

        req.getRequestDispatcher("/WEB-INF/pages/store/questionItem/testFileUpload.jsp").forward(req,resp);


    }
}
