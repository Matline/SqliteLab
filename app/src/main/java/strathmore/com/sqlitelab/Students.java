package strathmore.com.sqlitelab;

/**
 * Created by Matline on 24/10/2017.
 */



public class Students {

    //private variables
    int std_id;
    String course_name;
    String _year;

    public Students() {
        this.std_id = std_id;
        this.course_name = course_name;
        this._year = _year;
    }
    public Students(String bbit, String third) {
       // this.course_name = course_name;
        //this._year = _year;
    }

    public int getStd_id() {
        return std_id;
    }

    public void setStd_id(int std_id) {
        this.std_id = std_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String get_year() {
        return _year;
    }

    public void set_year(String _year) {
        this._year = _year;
    }






}
