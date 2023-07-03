package Entity;

public class Student extends Teacher {

    private String address;
    private String DOB;

    public Student() {
        super();
    }

    public Student(int ID, String FullName, String phone, String address, String image, String email, String gender, String username, String DOB) {
        super(ID, FullName, phone, image, email, gender, username);
        this.address = address;
        this.DOB = DOB;
    }

    public Student(String FullName, String phone, String address, String image, String email, String gender, String username, String DOB) {
        super(FullName, phone, image, email, gender, username);
        this.address = address;
        this.DOB = DOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

}
