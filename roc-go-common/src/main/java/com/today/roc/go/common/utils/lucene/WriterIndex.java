package com.today.roc.go.common.utils.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.*;

/**
 * .写索引
 *
 * @author ZCP
 * @version 2017-10-12
 **/
public class WriterIndex {

    public static void main(String[] args) throws IOException {
        //索引位置
        String indexDir = "D:\\java\\SVN\\roc-lucene\\src\\priv\\resource\\index";
        //添加内容的位置
        String dataDir = "D:\\java\\SVN\\roc-lucene\\src\\priv\\resource\\content";

        long startTime = System.currentTimeMillis();

        WriterIndex writerIndex = new WriterIndex(indexDir);
        try {
            int indexNums = writerIndex.index(dataDir,new TextFilesFilter());
            System.out.println("写入的文档数："+indexNums);
        }finally {
            writerIndex.close();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime +" ms ");
    }

    /**
     * 写入索引
     * @param indexDir
     * @param dataDir
     * @throws IOException
     */
    public static void write(String indexDir , String dataDir) throws IOException {
        long startTime = System.currentTimeMillis();

        WriterIndex writerIndex = new WriterIndex(indexDir);
        try {
            int indexNums = writerIndex.index(dataDir,new TextFilesFilter());
            System.out.println("写入的文档数："+indexNums);
        }finally {
            writerIndex.close();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime +" ms ");
    }

    private IndexWriter writer;

    /**
     * 创建Lucene IndexWriter
     * @param indexDir
     * @throws IOException
     */
    public WriterIndex(String indexDir) throws IOException {
        //索引位置
        Directory dir = FSDirectory.open(new File(indexDir));
        writer = new IndexWriter(dir,new StandardAnalyzer(Version.LUCENE_30),true, IndexWriter.MaxFieldLength.UNLIMITED);
    }

    /**
     * 关闭流
     * @throws IOException
     */
    public void close() throws IOException {
        writer.close();
    }

    /**
     * 文件过滤
     * @param dataDir
     * @param fileFilter
     * @return
     * @throws IOException
     */
    public int index (String dataDir , FileFilter fileFilter) throws IOException {
        File[] files = new File(dataDir).listFiles();

        for (File file : files){
            if (!file.isDirectory()&&!file.isHidden()&& file.exists()&&file.canRead()
                    &&(fileFilter == null || fileFilter.accept(file))){
                indexFile(file);
            }
        }
        return writer.numDocs();
    }

    /**
     * 内部类
     * 写入索引的内容判断
     */
    private static class TextFilesFilter implements FileFilter{
        @Override
        public boolean accept(File pathname) {
            return pathname.getName().toLowerCase().endsWith(".txt");
        }
    }

    /**
     * 设置索引文件的内容
     * @param f
     * @return
     * @throws IOException
     */
    public Document getDocument(File f) throws IOException {
        Document doc = new Document();
        doc.add(new Field("contents",getContent(f), Field.Store.YES, Field.Index.ANALYZED));
        doc.add(new Field("filename",f.getName(), Field.Store.YES, Field.Index.NOT_ANALYZED));
        doc.add(new Field("fullpath",f.getCanonicalPath(), Field.Store.YES, Field.Index.NOT_ANALYZED));
        return doc;
    }

    /**
     * 写入索引
     * @param f
     * @throws IOException
     */
    public void indexFile(File f) throws IOException {
        Document doc = getDocument(f);
        writer.addDocument(doc);
    }

    public static String getContent(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuffer sb = new StringBuffer();
        String line ;
        while ( (line = bufferedReader.readLine()) != null){
            sb.append(line + "\n");
        }
        return sb.toString();
    }

}
