package magnit;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

/**
 * Created by Антон on 27.08.2016.
 */
public class Main {
public  static void main(String[] args) throws Exception {
    Console console = new Console();
    DbTestMagnit  db =new DbTestMagnit();
    db.setConnection(db.getConnection());
    console.setN(100);
    console.insertE();
    console.wXML();    //1 xml
    console.transform();   //1xml - > xml
    System.out.println("RESULT = "+console.parseXML());

}
}
