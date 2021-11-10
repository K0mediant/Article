package com.example.article.service;

import com.example.article.domain.Article;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class FileService {

    @Autowired
    private ArticleService articleService;

    public Iterable<Article> parseFile(MultipartFile file) throws Exception {
        ZipInputStream zis = new ZipInputStream(file.getInputStream());
        ZipEntry entry;
        String files ="";
        List<Article> articles = new ArrayList<>();
        while ((entry = zis.getNextEntry()) != null){
            String type = entry.getName().substring(entry.getName().indexOf(".")+1);
            if (!type.equals("txt")) {
                throw new Exception("Wrong file type");
            }
            InputStream entryInputStream = getInputStreamForEntry(zis);
            String fileText = new String(entryInputStream.readAllBytes());
            articles.add(articleService.parseFile(fileText));
        }
        return articles;
    }

    private static InputStream getInputStreamForEntry(ZipInputStream zis) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        IOUtils.copy(zis, bos);
        return new ByteArrayInputStream(bos.toByteArray());
    }


}
