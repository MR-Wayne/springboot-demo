package com.example.demo.controller;

import com.example.demo.DemoProperties;
import com.example.demo.dao.GirlDao;
import com.example.demo.model.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private DemoProperties demoProperties;

    @Autowired
    private GirlDao girlDao;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say() {
        return "hello";
    }

    @RequestMapping(value = "/cupSize", method = RequestMethod.GET)
    public String cupSize() {
        return "cupSize:" + demoProperties.getCupSize();

    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/{id}/say", method = RequestMethod.GET)
    //@GetMapping("") 效果相同
    public String say(@PathVariable("id") Integer id) {
        return "id:" + id;
    }

    @RequestMapping(value = "add", method = RequestMethod.PUT)
    public String addGirl(@Valid Girl girl, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getFieldError().getDefaultMessage());
            return bindingResult.getFieldError().getDefaultMessage();
        }
        girlDao.save(girl);
        return "success";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Girl> girlList() {
        List<Girl> girls=girlDao.findAll();
        return girls;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "d");
        list.sort((e1, e2) -> {
            int result = e1.compareTo(e2);
            return result;
        });
        list.forEach (System.out::println);
        list.stream().filter((e) -> e.compareTo("a")>0).forEach(System.out::print);
    }
}
