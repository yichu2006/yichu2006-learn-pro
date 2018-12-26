package net.codingme.lucene.web.service;

import net.codingme.lucene.web.model.FileModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LuceneService {

    public List<FileModel> getTopN(String keywrod, Integer num) throws Exception;
}
