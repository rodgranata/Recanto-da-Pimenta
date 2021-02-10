package suporte;

import com.sun.jna.platform.win32.Sspi;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
//Classe para retornar um String com o formato yyyyMMddhhmmss
public class Generator {
    public static String datahoraParaArquivo() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return  new SimpleDateFormat("yyyyMMddhhmmss").format(ts);
    }
}
