package com.today.utils.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

/**
 * .查询信息
 *
 * @author ZCP
 * @version 2017-10-12
 **/
public class ReaderIndex {

    public static void main(String[] args) throws IOException, ParseException {
        //索引位置
        String indexDir = "D:\\java\\SVN\\roc-lucene\\src\\priv\\resource\\index";
        //搜索词
        String keyword = "索引";
        search(indexDir,keyword);
    }

    public static void search(String indexDir , String keyword) throws IOException, ParseException {
        //索引位置
        Directory dir = FSDirectory.open(new File(indexDir));
        IndexSearcher is = new IndexSearcher(dir);
        //查询解析器
        QueryParser parser = new QueryParser(Version.LUCENE_30,"contents",new StandardAnalyzer(Version.LUCENE_30));
        Query query = parser.parse(keyword);
        long start = System.currentTimeMillis();
        //搜索
        TopDocs hits = is.search(query,10);
        long end = System.currentTimeMillis();
        System.out.println("hits Num ：" + hits.totalHits + " times ：" + (end - start) + " query ：" + keyword);

        for (ScoreDoc scoreDoc : hits.scoreDocs){
            Document doc = is.doc(scoreDoc.doc);
            System.out.println("分数："+scoreDoc.score);
            System.out.println("------------fullpath");
            System.out.println(doc.get("fullpath"));
            System.out.println("------------contents");
            System.out.println(doc.get("contents"));
            System.out.println("------------filename");
            System.out.println(doc.get("filename"));
        }
        is.close();
    }
}
