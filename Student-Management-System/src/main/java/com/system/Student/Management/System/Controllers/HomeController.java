package com.system.Student.Management.System.Controllers;

import com.system.Student.Management.System.Models.StudentData;
import com.system.Student.Management.System.Service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("/students/data")
    public String Home(Model model){
        List<StudentData> data=studentService.getAll();
        System.out.println("Hello"+ data.get(0).getId());
        model.addAttribute("data",data);
        return "Home";}
    @RequestMapping(value = "/deletePost", method = RequestMethod.POST)
    public String deletePost(@RequestParam(name = "dataId") Long dataId){
        studentService.deletedata(dataId);
        return "redirect:/students/data";

    }
    @RequestMapping(value = "/updatedata", method = RequestMethod.GET)
    public String editPost(@RequestParam(name="dataId") Long dataId, Model model) {
        StudentData data = studentService.getdata(dataId);
        model.addAttribute("data",data);
        return "edit";
    }
    @RequestMapping(value = "/updatedata", method = RequestMethod.POST)
    public String UpdatePost(@RequestParam(name = "dataId") Long dataId,StudentData updatedata){


        updatedata.setId(dataId);
        studentService.updateData(updatedata);
        return "redirect:/students/data";

    }
    @RequestMapping("adddata")
    public String addData(){
        return "Add";
    }
    @RequestMapping(value="/adddata",method = RequestMethod.POST)
    public String CreatePost(StudentData newdata)
    {


        studentService.newPost(newdata);
        return "redirect:/students/data";
    }







}
