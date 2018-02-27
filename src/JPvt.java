
import jPvt.RinexFileReader;

public class JPvt {

    public static void main(String[] args) {
        String fn = "data/CKSV00TWN_R_20180120000_01D_GN.rnx";
        //String fn = "data/TWTF00TWN_R_20180120000_01D_30S_MO.rnx";
        RinexFileReader tc = new RinexFileReader(fn);
        if (!tc.readFile()) {
            System.exit(-1);
        }
    }
}
