/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxloginregcrubform1;

/**
 *
 * @author Jean_Nico .T
 */
public class studentData {
    
    private Integer studentNumber;
    private String fullName;
    private String year;
    private String course;
    private String gender;
    
    public studentData(Integer studentNumber, String fullName, String year, String course, String gender){
      this.studentNumber = studentNumber;
      this.fullName = fullName;
      this.year = year;
      this.course = course;
      this.gender = gender;
      
    }
    
    public Integer studentNumber(){
        return studentNumber;      
    }
    public String fullName(){
    return fullName;
    }
    public String year(){
     return year;
    }
    public String course(){
      return course;
    }
    public String gender(){
      return gender;
    }

    String getFullName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
