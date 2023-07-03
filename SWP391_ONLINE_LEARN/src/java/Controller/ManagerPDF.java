package Controller;

import Entity.LessonPDF;
import Model.DAOLessonPDF;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerPDF extends HttpServlet {

    private final DAOLessonPDF dao = new DAOLessonPDF();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("service") == null ? "ViewPDF" : request.getParameter("service");
        if (service.equals("ViewPDF")) {
            String PDFID = request.getParameter("PDFID");
            LessonPDF PDF = dao.getLessonPDF(PDFID);
            response.sendRedirect("ManagerLesson?LessonID=" + PDF.getLessonID() + "&PDFID=" + PDFID + "&name=" + PDF.getPDFName() + "&PDF=" + PDF.getFilePDF());
        }

        if (service.equals("DeletePDF")) {
            String PDFID = request.getParameter("PDFID");
            int PDFId = Integer.parseInt(PDFID);
            int number = dao.DeletePDF("PDFID", PDFId);
            // if delete successful
            if (number > 0) {
                String mess = "This PDF was removed successfully!";
                response.sendRedirect("ManagerLesson?mess=" + mess);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String service = request.getParameter("service");
        String NewPDFName = request.getParameter("PDFName").trim();
        String NewFilePDF = request.getParameter("FilePDF");
        String LessonID = request.getParameter("LessonID");
        int lessonID = Integer.parseInt(LessonID);
        if (service.equals("AddPDF")) {
            if (NewPDFName.isEmpty()) {
                String message = "You have to input PDF name";
                response.sendRedirect("ManagerLesson?message=" + message);
            } else if (dao.CheckPDFNameExist(NewPDFName, lessonID)) {
                String message = "PDF " + NewPDFName + " has existed in lesson";
                response.sendRedirect("ManagerLesson?message=" + message);
            } else if (dao.CheckFilePDFExist(NewFilePDF, lessonID)) {
                String message = "File PDF " + NewFilePDF + " has existed in lesson";
                response.sendRedirect("ManagerLesson?message=" + message);
            } else {
                LessonPDF PDF = new LessonPDF(NewPDFName, NewFilePDF, lessonID);
                int number = dao.AddPDF(PDF);
                // if add successful
                if (number > 0) {
                    String mess = "PDF " + NewPDFName + " was added successfully!";
                    response.sendRedirect("ManagerLesson?mess=" + mess);
                }
            }
        } else {
            String PDFID = request.getParameter("PDFID");
            String OldPDFName = request.getParameter("oldName");
            String OldFilePDF = request.getParameter("oldFilePDF");
            int PDFId = Integer.parseInt(PDFID);
            if (NewPDFName.isEmpty()) {
                String message = "You have to input PDF name";
                response.sendRedirect("ManagerLesson?message=" + message);
            } else if (dao.CheckPDFNameExist(NewPDFName, lessonID) && !NewPDFName.equalsIgnoreCase(OldPDFName)) {
                String message = "PDF " + NewPDFName + " has existed in lesson";
                response.sendRedirect("ManagerLesson?message=" + message);
            } else if (dao.CheckFilePDFExist(NewFilePDF, lessonID) && !NewFilePDF.isEmpty() && !NewFilePDF.equalsIgnoreCase(OldFilePDF)) {
                String message = "File PDF " + NewFilePDF + " has existed in lesson";
                response.sendRedirect("ManagerLesson?message=" + message);
            } else {
                LessonPDF PDF = new LessonPDF(PDFId, NewPDFName, NewFilePDF.isEmpty() ? OldFilePDF : NewFilePDF, lessonID);
                int number = dao.UpdatePDF(PDF);
                // if update successful
                if (number > 0) {
                    String mess = "Update Successful";
                    response.sendRedirect("ManagerLesson?mess=" + mess);
                }
            }
        }
        //processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
