package magnit;

import org.apache.log4j.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;



/**
 * Created by Антон on 27.08.2016.
 */
public class Console {



    private int N;
    private String query;
    private static Statement stmt;
    private static ResultSet rs;
    DocumentBuilder builder;
    public static String CREATE;
    public static String INSERT;
    public static String TRUNCATE;
    public static String XML1;
    public static String XML2;
    public static String TRANSFORM;
    public static String DOTXSL;
    public static String DOTXML;
    public static String SELECT;
    private static final Logger log = Logger.getLogger(Propert.class);
    private Connection con;
    public  void insertE() throws Exception {

      /*  try {

            query = "DELETE from test";
            stmt = db.getConnection().createStatement();
            stmt.executeUpdate(query);
            query = "INSERT INTO test(FIELD) VALUES (?)";
            PreparedStatement preparedStmt = db.getConnection().prepareStatement(query);
            int batchSize = 100;
            for (int count = 0; count < main.getN(); count++) {
                preparedStmt.setInt(1, count);
                preparedStmt.addBatch();

                if (count % batchSize == 0) {
                    preparedStmt.executeBatch();
                }
            }

            preparedStmt.executeBatch();


        } catch (SQLException e) {
            e.printStackTrace();

        }
    } не работает, потом разберусь, скопипастю другой код *P */




            if (con != null) {
                try {

                    stmt = con.createStatement();
                    stmt.getConnection().setAutoCommit(false);
                    stmt.execute(Propert.getProp(CREATE));
                    stmt.execute(Propert.getProp(TRUNCATE));
                    int batchSize = 100; // Лучше спростить размер у Админа бд.
                    for (int i = 1; i <= getN(); i++) {
                        query = Propert.getProp(INSERT).replace("number", Integer.toString(i));
                        stmt.addBatch(query);
                        if (i % batchSize == 0) {
                            stmt.executeBatch();
                        }
                    }
                    stmt.executeBatch();
                    stmt.execute("commit");
                    log.info("Строки залиты");

                } catch (Exception ex) {
                    log.error("Ошибка, строки не залиты " + ex.getMessage());
                }
            } else {
                log.error("нет соеденения с дб");
            }
        }


    public void wXML() throws TransformerException, IOException {
        DbTestMagnit db = new DbTestMagnit();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            System.out.println("Опаньки");
        }

        Document doc = builder.newDocument();
        Element RootElement = doc.createElement("entries");
        Element entry, field;

        rs = getValues();
        try {
            if (rs != null) {
                while (rs.next()) {
                    entry = doc.createElement("entry");
                    RootElement.appendChild(entry);
                    field = doc.createElement("field");
                    field.appendChild(doc.createTextNode(rs.getString("field")));
                    entry.appendChild(field);
                }
                stmt.execute("sss");
                stmt.close();
                db.getConnection().close();

            } else {
                System.out.println("TEST пуста");
            }
        } catch (SQLException | DOMException e) {
           System.out.println("Ошибка");
            e.printStackTrace();
        }
        doc.appendChild(RootElement);

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.METHOD, "xml");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(Propert.getProp(XML1) + Propert.getProp(DOTXML))));

    }

    public ResultSet getValues() {
        DbTestMagnit db = new DbTestMagnit();
        try {
            PreparedStatement preparedStmt =db.getConnection().prepareStatement(Propert.getProp(SELECT));
            return  preparedStmt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Ошибка");
        }
        return null;
    }

    public void transform() throws TransformerConfigurationException, TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File(Propert.getProp(TRANSFORM) + Propert.getProp(DOTXSL)));
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(new File(Propert.getProp(XML1) + Propert.getProp(DOTXML)));
        transformer.transform(text, new StreamResult(new File(Propert.getProp(XML2) + Propert.getProp(DOTXML))));
    }

    public double parseXML() throws SAXException, IOException, ParserConfigurationException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        Parse sax = new Parse();

        parser.parse(new File(Propert.getProp(XML2) + Propert.getProp(DOTXML)), sax);
        return sax.getSumm();
    }

    public  void setN(int n) {
        N = n;
    }
    public int getN() {
        return N;
    }
}
