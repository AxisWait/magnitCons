package magnit;

import java.io.IOException;
import org.apache.log4j.Logger;
import java.io.InputStream;
import java.util.Properties;


/**
 * Created by Антон on 27.08.2016.
 */
public class Propert
{
    private String CONFIG;
    private static Properties prop;
    private static final Logger log = Logger.getLogger(Propert.class);
    private Propert()
    {
        prop = new Properties();
        InputStream is = getClass().getClassLoader().getResourceAsStream(CONFIG);
        if (is != null)
        {
            try
            {
                prop.load(is);
                log.debug("Загружен");
            } catch (IOException e)
            {
                log.error("Ошибка загрузки " + e.getMessage());
            }
        } else {
            log.error("Файл не найден");
            System.exit(1);
        }
    }

    public static String getProp(String property)
    {
        if(prop==null)
            new Propert();
        return prop.getProperty(property);
    }
}

