package com.example.article.service;

import com.example.article.domain.Article;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class FileService {

    @Autowired
    private ArticleService articleService;

    public Article parseFile(MultipartFile file) throws Exception {
        ZipInputStream zis = new ZipInputStream(file.getInputStream());
        ZipEntry entry;
        Article article;
        entry = zis.getNextEntry();
        if (entry == null) {
            throw new Exception("File is empty");
        }
        String fileName = entry.getName();
        if (!fileName.equals("article.txt")) {
            throw new Exception("Wrong file type");
        }
        InputStream entryInputStream = getInputStreamForEntry(zis);
        String fileText = new String(IOUtils.toByteArray(entryInputStream));
        article = articleService.parseFile(fileText);

        return article;
    }

    private static InputStream getInputStreamForEntry(ZipInputStream zis) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        IOUtils.copy(zis, bos);
        return new ByteArrayInputStream(bos.toByteArray());
    }


}
