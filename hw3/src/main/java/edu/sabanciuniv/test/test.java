package edu.sabanciuniv.test;

import edu.sabanciuniv.objects.*;
import edu.sabanciuniv.utility.EntityManagerUtils;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.Month;

public class test {
    public static void main(String[] args) {

        Student student1 = new Student(
                "Duhan Ugurlu", LocalDate.of(1997, Month.JULY,06)
                ,"Ankara","Male");
        Student student2 = new Student(
                "Imgesu Usta", LocalDate.of(1996, Month.JULY,19)
                , "Ankara","Female");
        Student student3 = new Student(
                "Nargiz Ramazanova", LocalDate.of(1998, Month.DECEMBER,23)
                , "Baku","Female");

        Course course1=new Course("Calculus I",101,3.00);
        Course course2=new Course("Calculus II",102,3.00);
        Course course3=new Course("Advanced Calculus I",201,3.00);
        Course course4=new Course("Advanced Calculus II",202,3.00);


        Instructor permanentInstructor1=new PermanentInstructor(
                "Burak Kaya","Ankara","5551234567",20000.00);
        Instructor permanentInstructor2=new PermanentInstructor(
                "Ali Veli","Ankara","5555555555",25000.00);

        Instructor visitingResearcher1=new VisitingResearcher(
                "Cem Tezer","Ankara","5551111111",45000.00);
        Instructor visitingResearcher2=new VisitingResearcher(
                "Ali Doganaksoy","Ankara","5552222222",45000.00);

        course1.setInstructor(permanentInstructor1);
        course2.setInstructor(permanentInstructor2);
        course3.setInstructor(visitingResearcher2);
        course4.setInstructor(visitingResearcher1);

        course1.getStudentList().add(student1);
        course2.getStudentList().add(student2);
        course3.getStudentList().add(student3);
        course4.getStudentList().add(student1);

        EntityManager entityManager = EntityManagerUtils.getEntityManager("mysqlPU");
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(course1);
            entityManager.persist(course2);
            entityManager.persist(course3);
            entityManager.persist(course4);
            entityManager.persist(student1);
            entityManager.persist(student2);
            entityManager.persist(student3);
            entityManager.persist(permanentInstructor1);
            entityManager.persist(permanentInstructor2);
            entityManager.persist(visitingResearcher1);
            entityManager.persist(visitingResearcher2);
            entityManager.getTransaction().commit();
            System.out.println("All data persisted.");
        }catch(Exception e){
            entityManager.getTransaction().rollback();
        }finally{
            EntityManagerUtils.closeEntityManager(entityManager);

        }
    }
}