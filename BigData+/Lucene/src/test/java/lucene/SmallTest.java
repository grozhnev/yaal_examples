package lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.MockAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.RandomIndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.LuceneTestCase;

import java.io.IOException;

/**
 * Example source: https://github.com/apache/lucene-solr/blob/master/lucene/core/src/test/org/apache/lucene/TestDemo.java
 */
public class SmallTest extends LuceneTestCase {

    public void testDemo() throws IOException {
        String text = "Some of the query types provided by Elasticsearch support Apache Lucene query parser syntax.";
        Document doc = new Document();
        String fieldName = "fieldname";
        doc.add(newTextField(fieldName, text, Field.Store.YES));

        Directory directory = newDirectory();
        Analyzer analyzer = new MockAnalyzer(random());
        RandomIndexWriter iwriter = new RandomIndexWriter(random(), directory, analyzer);
        iwriter.addDocument(doc);
        iwriter.close();

        IndexReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = newSearcher(ireader);
        Query query = new TermQuery(new Term(fieldName, "lucene"));
        TopDocs hits = isearcher.search(query, 1);
        assertEquals(1, hits.totalHits);

        ireader.close();
        directory.close();
        analyzer.close();
    }
}
