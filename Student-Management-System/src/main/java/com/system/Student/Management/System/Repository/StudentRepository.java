package com.system.Student.Management.System.Repository;

import com.system.Student.Management.System.Models.StudentData;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    @PersistenceUnit(unitName = "datakey")
    private EntityManagerFactory emf;
    public List<StudentData> getAll(){
        EntityManager em=emf.createEntityManager();
        TypedQuery<StudentData> query=em.createQuery("SELECT p from StudentData p",StudentData.class);
        List<StudentData> resultList=query.getResultList();
        return resultList;
    }
    public void deletedata(Long dataId){
        EntityManager em = emf.createEntityManager();
        StudentData delPost=em.find(StudentData.class,dataId);
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.remove(delPost);
            transaction.commit();
        }catch(Exception e) {
            transaction.rollback();
        }


    }
    public StudentData getdata(Long dataId) {
        EntityManager em = emf.createEntityManager();
        return em.find(StudentData.class,dataId);
    }
    public void updateData(StudentData updatedata){

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(updatedata);
            transaction.commit();
        }catch(Exception e) {
            transaction.rollback();
        }
    }
    public StudentData createdata(StudentData newdata) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newdata);
            transaction.commit();
        }catch(Exception e) {
            transaction.rollback();
        }

        return newdata;
    }

}
