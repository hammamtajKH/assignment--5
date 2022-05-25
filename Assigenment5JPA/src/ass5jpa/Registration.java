/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass5jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author bob
 */
@Entity
@Table(name = "Registration")
public class Registration {
    @Id
    @ManyToOne
    @JoinColumn(name = "studentid")
    private Student student;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "courseid ")
    private Course course;
    
    @Column(name = "semester")
    private String semester;

    public Integer getStudent() {
        return student.getId();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getCourse() {
        return course.getId();
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Registration() {
    }
    
    
}
