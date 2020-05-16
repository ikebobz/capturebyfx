/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package webapps;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.output.*;

/**
 *
 * @author ikenna
 */
@MultipartConfig(
fileSizeThreshold = 1024*1024*1,
maxFileSize = 1024*1024*10,
maxRequestSize = 1024*1024*15,
location = "D:\\uploads"
)
public class mainWorker extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected int count =0;
    StringBuilder sb = new StringBuilder();
    HtmlBrowser browser; 
    private File file;
    String fpath;
    int maxMemSize = 1024*1024*1;
    int maxFileSize = 1024*1024*10;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet mainWorker</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mainWorker at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
       // processRequest(request, response);
        response.getWriter().write(sb.toString());
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
       
        try
        {
        browser = new HtmlBrowser();
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        List<FileItem> items = upload.parseRequest(request);
        for(FileItem item : items){
         if(item.getFieldName().equals("btchsz"))
             browser.batchSize = Integer.parseInt(item.getString());
         if(item.getFieldName().equals("count"))
             browser.recCount = Integer.parseInt(item.getString());
         if(item.getFieldName().equals("cycle"))
             browser.retrialCycle = Integer.parseInt(item.getString());
         if(item.getFieldName().equals("uname"))
             browser.username = item.getString();
         if(item.getFieldName().equals("pass"))
             browser.passw = item.getString();
         if(item.getFieldName().equals("selbrw"))
             browser.browserSelect = Integer.parseInt(item.getString());
         if(item.getFieldName().equals("nondeliver"))
             browser.nondeliver_r = Integer.parseInt(item.getString());
         if(item.getFieldName().equals("measure"))
             browser.nondeliver_m = Integer.parseInt(item.getString());
         if(item.getFieldName().equals("check"))
         {
             if(item.getString().equals("uda"))
             browser.udaonly = true;
         }
         if(item.getFieldName().equals("pickfile"))
         {
          String fileName = FilenameUtils.getName(item.getName());
          //String path = getServletContext().getInitParameter("uploadpath");
          String path = getServletContext().getRealPath("uploads");
          response.getWriter().write(path+"\n");
         /* if(fileName.indexOf("\\")>=0)
              file = new File(path+fileName.substring(fileName.lastIndexOf("\\")));
          else 
              file = new File(path+fileName.substring(fileName.lastIndexOf("\\")+1));*/
          file = new File(path,item.getName());
          item.write(file);
         };
         
     
        }
        response.getWriter().write("processing completed");
       }
        
        catch(Exception e)
        {
        PrintWriter writer = response.getWriter();
        writer.write(e.getMessage());
        writer.close();
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
   
}
