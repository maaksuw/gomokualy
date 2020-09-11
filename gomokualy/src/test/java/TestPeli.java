
import java.util.Scanner;
import logiikka.Peli;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestPeli {
    
    private Peli p;
    
    public TestPeli() {
        Scanner s = new Scanner(System.in);
        p = new Peli();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
        
    }
}
