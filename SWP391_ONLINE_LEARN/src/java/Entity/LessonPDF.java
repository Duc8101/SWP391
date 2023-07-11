package Entity;

public class LessonPDF {

    private int PDFID;
    private String PDFName;
    private String FilePDF;
    private int LessonID;

    public LessonPDF() {
    }

    public LessonPDF(int PDFID, String PDFName, String FilePDF, int LessonID) {
        this.PDFID = PDFID;
        this.PDFName = PDFName;
        this.FilePDF = FilePDF;
        this.LessonID = LessonID;
    }

    public LessonPDF(int PDFID, String PDFName, String FilePDF) {
        this.PDFID = PDFID;
        this.PDFName = PDFName;
        this.FilePDF = FilePDF;
    }

    public LessonPDF(String PDFName, String FilePDF, int LessonID) {
        this.PDFName = PDFName;
        this.FilePDF = FilePDF;
        this.LessonID = LessonID;
    }

    public int getPDFID() {
        return PDFID;
    }

    public void setPDFID(int PDFID) {
        this.PDFID = PDFID;
    }

    public String getPDFName() {
        return PDFName;
    }

    public void setPDFName(String PDFName) {
        this.PDFName = PDFName;
    }

    public String getFilePDF() {
        return FilePDF;
    }

    public void setFilePDF(String FilePDF) {
        this.FilePDF = FilePDF;
    }

    public int getLessonID() {
        return LessonID;
    }

    public void setLessonID(int LessonID) {
        this.LessonID = LessonID;
    }
    
    
}
