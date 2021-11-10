package com.example.article;

import com.example.article.domain.Article;
import com.example.article.repos.ArticleRepo;
import com.example.article.service.ArticleService;
import com.example.article.service.FileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Controller
@RequestMapping("/")
public class GreetingController {

    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private FileService fileService;


    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Article> articles = articleRepo.findAll();
        model.put("articles", articles);
        return "main";
    }

    @GetMapping("/uploadFile")
    public String uploadFile(Model model){
        return "upload";
    }


    @PostMapping("/uploadFile")
    public String upload(@RequestBody MultipartFile file, Map<String, Object> model) throws Exception {
        //Map<String, Object> model = new HashMap<>();
        fileService.parseFile(file);
        Iterable<Article> articles = articleRepo.findAll();
        model.put("articles", articles);
        return "main";
    }

}