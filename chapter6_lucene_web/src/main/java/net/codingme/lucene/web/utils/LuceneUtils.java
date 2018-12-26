package net.codingme.lucene.web.utils;

import net.codingme.lucene.web.model.FileModel;
import net.codingme.lucene.web.model.ik.IKAnalyzer6x;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Lucene工具类 - 创建索引
 */
public class LuceneUtils {

    public static String fileDir = "chapter6_lucene_web/src/main/resources/static/files";
    public static String indexDir = "chapter6_lucene_web/src/main/resources/static/indexdir";

    public static void main(String[] args) throws IOException {
        createIndex();
    }

    /**
     * 创建索引
     * @throws IOException
     */
    public static void createIndex() throws IOException {
        // 创建使用的分词器
        IKAnalyzer6x ikAnalyzer6x = new IKAnalyzer6x();
        // 索引配置对象
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(ikAnalyzer6x);
        //索引库的打开方式：没有写，默认是追加
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

        // 索引信息存放目录
        Path indexPath = Paths.get(indexDir);
        if (!Files.isReadable(indexPath)) {
            System.out.println(indexPath.toAbsolutePath() + "目录不存在或者不可读！");
            System.exit(0);
        }

        File filesDir = new File(fileDir);
        if (!filesDir.exists()) {
            System.out.println(filesDir.getAbsolutePath() + "不存在..");
            System.exit(0);
        }

        // 索引存放到文件系统中
        FSDirectory fsDirectory = FSDirectory.open(indexPath);
        // 创建索引写对象
        IndexWriter indexWriter = new IndexWriter(fsDirectory, indexWriterConfig);

        FieldType fieldType = getFieldType();
        Long startTime = System.currentTimeMillis();

        File[] files = filesDir.listFiles();
        for (File file : files) {
            FileModel fileModel = TikaUtils.getFileInfo(file);
            // 创建Document
            Document document = new Document();
            document.add(new Field("title",fileModel.getTitle(),fieldType));
            document.add(new Field("content",fileModel.getContent(),fieldType));
            indexWriter.addDocument(document);
            System.out.println("正在索引"+fileModel.getTitle());
        }

        indexWriter.commit();
        indexWriter.close();
        fsDirectory.close();

        Long endTime = System.currentTimeMillis();
        System.out.println("索引文档完成，耗时："+(endTime - startTime) +"ms");
    }

    /**
     * 获取一个FieldType
     * @return
     */
    public static FieldType getFieldType() {
        FieldType fieldType = new FieldType();
        //反向索引中存储 文档id、词频、位置、偏移量
        fieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
        fieldType.setStored(true);   //存储
        fieldType.setTokenized(true);  //分词
        //高亮设置
        fieldType.setStoreTermVectors(true);
        fieldType.setStoreTermVectorPositions(true);
        fieldType.setStoreTermVectorOffsets(true);
        return fieldType;
    }



}
