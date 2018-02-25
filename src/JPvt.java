
import jPvt.RinexFileReader;

public class JPvt {

    public static void main(String[] args) {
        RinexFileReader tc = new RinexFileReader("data/kuuj007g.12n");
        if (!tc.readFile()) {
            System.exit(-1);
        }

    }
}
