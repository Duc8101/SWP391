package Controller;

import Entity.LessonVideo;
import Model.DAOLessonVideo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerVideo extends HttpServlet {

    private final DAOLessonVideo dao = new DAOLessonVideo();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String service = request.getParameter("service") == null ? "WatchVideo" : request.getParameter("service");
        if (service.equals("WatchVideo")) {
            String VideoID = request.getParameter("VideoID");
            LessonVideo video = dao.getLessonVideo(VideoID);
            response.sendRedirect("ManagerLesson?LessonID=" + video.getLessonID() + "&VideoID=" + VideoID + "&name=" + video.getVideoName() + "&video=" + video.getFileVideo());
        }

        if (service.equals("DeleteVideo")) {
            String VideoID = request.getParameter("VideoID");
            int videoID = Integer.parseInt(VideoID);
            int number = dao.DeleteVideo("VideoID", videoID);
            // if delete successful
            if (number > 0) {
                String mess = "This video was removed successfully";
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
        String NewVideoName = request.getParameter("VideoName").trim();
        String NewVideoFile = request.getParameter("FileVideo");
        String LessonID = request.getParameter("LessonID");
        int lessonID = Integer.parseInt(LessonID);
        if (service.equals("AddVideo")) {
            if (NewVideoName.isEmpty()) {
                String message = "You have to input video name";
                response.sendRedirect("ManagerLesson?message=" + message);
            } else if (dao.CheckVideoNameExist(NewVideoName, lessonID)) {
                String message = "Video name '" + NewVideoName + "' has existed in lesson";
                response.sendRedirect("ManagerLesson?message=" + message);
            } else if (dao.CheckFileVideoExist(NewVideoFile, lessonID)) {
                String message = "File Video '" + NewVideoFile + "' has existed in lesson";
                response.sendRedirect("ManagerLesson?message=" + message);
            } else {
                LessonVideo video = new LessonVideo(NewVideoName, NewVideoFile, lessonID);
                int number = dao.AddVideo(video);
                // if add successful
                if (number > 0) {
                    String mess = "Video '" + NewVideoName + "' was added successfully!";
                    response.sendRedirect("ManagerLesson?mess=" + mess);
                }
            }
        } else {
            String OldVideoName = request.getParameter("oldVideoName");
            String OldFileVideo = request.getParameter("oldFileVideo");
            String VideoID = request.getParameter("VideoID");
            int videoID = Integer.parseInt(VideoID);
            if (NewVideoName.isEmpty()) {
                String message = "You have to input video name";
                response.sendRedirect("ManagerLesson?message=" + message);
            } else if (dao.CheckVideoNameExist(NewVideoName, lessonID) && !NewVideoName.equalsIgnoreCase(OldVideoName)) {
                String message = "Video name '" + NewVideoName + "' has existed in lesson";
                response.sendRedirect("ManagerLesson?message=" + message);
            } else if (dao.CheckFileVideoExist(NewVideoFile, lessonID) && !NewVideoFile.isEmpty() && !NewVideoFile.equalsIgnoreCase(OldFileVideo)) {
                String message = "File Video '" + NewVideoFile + "' has existed in lesson";
                response.sendRedirect("ManagerLesson?message=" + message);
            } else {
                LessonVideo video = new LessonVideo(videoID, OldVideoName, NewVideoFile.isEmpty() ? OldFileVideo : NewVideoFile);
                int number = dao.UpdateVideo(video);
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
