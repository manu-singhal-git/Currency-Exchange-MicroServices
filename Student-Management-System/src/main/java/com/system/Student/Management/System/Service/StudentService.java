package com.system.Student.Management.System.Service;

import com.system.Student.Management.System.Models.StudentData;
import com.system.Student.Management.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public List<StudentData> getAll(){
        return studentRepository.getAll();

    }
    public void deletedata(Long dataId){
        studentRepository.deletedata(dataId);

    }
    public StudentData getdata(Long dataId) {
        return studentRepository.getdata(dataId);
    }
    public void updateData(StudentData updatedata){
        studentRepository.updateData(updatedata);
    }
    public  void newPost(StudentData newdata) {

        studentRepository.createdata(newdata);
    }
}
