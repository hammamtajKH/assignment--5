/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass5jpa;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author bob
 */
public class OperationController implements Initializable {

    @FXML
    private TextField textfieldID;
    @FXML
    private TextField textfieldName;
    @FXML
    private TextField textfieldMajor;
    @FXML
    private TextField textfieldGrade;
    @FXML
    private TextField textfieldIDCourse;
    @FXML
    private TextField textfieldSemester;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, Integer> tcID;
    @FXML
    private TableColumn<Student, String> tcName;
    @FXML
    private TableColumn<Student, String> tcMajor;
    @FXML
    private TableColumn<Student, Double> tcGrade;
    @FXML
    private Button ButtonShow;
    @FXML
    private Button ButtonAdd;
    @FXML
    private Button ButtonUpdate;
    @FXML
    private Button ButtonDelete;
    @FXML
    private Button ButtonReset;
    @FXML
    private Button ButtonRegister;
    EntityManagerFactory emf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        emf = Persistence.createEntityManagerFactory("Ass5JPAPU");
        tcID.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcMajor.setCellValueFactory(new PropertyValueFactory("major"));
        tcGrade.setCellValueFactory(new PropertyValueFactory("grade"));
        tableView.getSelectionModel().selectedItemProperty().addListener(
                event -> showSelectedStudent());
    }

    @FXML
    private void ButtonShowHandel(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("Select std from Student std");
        List<Student> deps = q.getResultList();
        for (Student dep : deps) {
            Student student = new Student();
            student.setId(dep.getId());
            student.setName(dep.getName());
            student.setMajor(dep.getMajor());
            student.setGrade(dep.getGrade());
            tableView.getItems().add(student);
        }
        em.getTransaction().commit();
        em.close();
    }

    @FXML
    private void ButtonAddHandel(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student student = new Student();
        student.setId(Integer.parseInt(textfieldID.getText()));
        student.setName(textfieldName.getText());
        student.setMajor(textfieldMajor.getText());
        student.setGrade(Double.parseDouble(textfieldGrade.getText()));
        em.persist(student);
        em.getTransaction().commit();
        em.close();
    }

    @FXML
    private void ButtonUpdateHandel(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("Update Student s Set s.name = '"+textfieldName.getText() +"',s.major = '"+textfieldMajor.getText()+"',s.grade = "+textfieldGrade.getText()+" Where s.id = "+textfieldID.getText());
        int updatedRows = query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @FXML
    private void ButtonDeleteHandel(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("Delete from Student s Where s.id = "+textfieldID.getText());
        int deletedRows = query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @FXML
    private void ButtonResetHandel(ActionEvent event) {
        textfieldID.setText("");
        textfieldName.setText("");
        textfieldMajor.setText("");
        textfieldGrade.setText("");
        textfieldIDCourse.setText("");
        textfieldSemester.setText("");
        tableView.getItems().clear();
    }

    @FXML
    private void ButtonRegisterHandel(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student student = new Student();
        Course course = new Course();
        Registration registration = new Registration();
        student.setId(Integer.parseInt(textfieldID.getText()));
        course.setId(Integer.parseInt(textfieldIDCourse.getText()));
        registration.setSemester(textfieldSemester.getText());
        registration.setStudent(student);
        registration.setCourse(course);
        em.persist(registration);
        em.getTransaction().commit();
        em.close();
    }

    private void showSelectedStudent() {
        Student student = tableView.getSelectionModel().getSelectedItem();
        if (student != null) {
            textfieldID.setText(String.valueOf(student.getId()));
            textfieldName.setText(student.getName());
            textfieldMajor.setText(student.getMajor());
            textfieldGrade.setText(String.valueOf(student.getGrade()));
        }

    }

}
