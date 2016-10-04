package magnit;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Антон on 27.08.2016.
 */
public class Parse extends DefaultHandler {
    double summ = 0;

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes att) throws SAXException {
        if (qName.equals("entry")) {
            summ += Double.valueOf(att.getValue(0));
        }
    }
    public double getSumm()
    {
        return summ;
    }

}
