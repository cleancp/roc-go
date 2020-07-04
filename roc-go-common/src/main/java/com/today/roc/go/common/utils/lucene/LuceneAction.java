package com.today.roc.go.common.utils.lucene;

import org.apache.lucene.queryParser.ParseException;

import java.io.IOException;

/**
 * .Lucene
 *
 * @author ZCP
 * @version 2017-10-12
 **/
public class LuceneAction {
    //索引位置
    private static final String indexDir = "D:\\java\\myDevelop\\roc-go\\src\\main\\resources\\index";
    //添加内容的位置
    private static final String dataDir = "D:\\java\\myDevelop\\roc-go\\src\\main\\resources\\content";
    public static void main(String[] args) throws IOException, ParseException {
        //写
        WriterIndex.write(indexDir,dataDir);
        //查
        String keyword = "索引";
        ReaderIndex readerIndex = new ReaderIndex();
        readerIndex.search(indexDir,keyword);
    }

}
