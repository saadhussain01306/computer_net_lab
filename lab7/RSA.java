/*RSA algorithm*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RSA{
    private BigInteger p, q, N, phi, e, d;
    private int bitLength = 1024;
    private Random r;

    public RSA() {
        r = new Random();
        p = BigInteger.probablePrime(bitLength, r);
        q = BigInteger.probablePrime(bitLength, r);
        System.out.println("Prime number p is " + p);
        System.out.println("Prime number q is " + q);
        N = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        e = BigInteger.probablePrime(bitLength / 2, r);

        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }

        System.out.println("Public key is " + e);
        d = e.modInverse(phi);
        System.out.println("Private key is " + d);
    }

    public static void main(String[] args) throws IOException {
        RSA rsa = new RSA();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the plain text: ");
        String testString = br.readLine();
        System.out.println("Encrypting string: " + testString);
        System.out.println("String in bytes: " + bytesToString(testString.getBytes()));
        byte[] encrypted = rsa.encrypt(testString.getBytes());
        byte[] decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypting Bytes: " + bytesToString(decrypted));
        System.out.println("Decrypted string: " + new String(decrypted, StandardCharsets.UTF_8));
    }

    private static String bytesToString(byte[] encrypted) {
        StringBuilder result = new StringBuilder();
        for (byte b : encrypted) {
            result.append(Byte.toString(b));
        }
        return result.toString();
    }

    public byte[] encrypt(byte[] message) {
        return (new BigInteger(message)).modPow(e, N).toByteArray();
    }

    public byte[] decrypt(byte[] message) {
        return (new BigInteger(message)).modPow(d, N).toByteArray();
    }
}


OUTPUT:-
/*Prime number p is 135300281134717879746902808366541099903975251288789284334641807832207682871127271567920761055463729098924430287718421850746198499428795090338316070179909825871150136529664667741679224565002205330126017727809227066298450635996226617624131459287230966759830732923433622770966482580389693040579733621803440243969
Prime number q is 170562584752136934187250756707709666849234818501724317538955107998074989545033582877327247129830049849750916598428228136636890219614528565333654188553119948743435910519651826713018595370044199045107161079680301688990314455906280489353568884786496504824152441495041826797939948169147055493631177589953202830599
Public key is 7213875375108038073672044021293975116552046473166347060461685329555475221863979286020739066801430989540112524679688665752398505800679918797492724602732629
Private key is 1023289063268676675003449072500393039208459382322802680700731614917438721396140666246676991920886203548324527726189056503719860211034605091455352678223091981743421712993884339011094735886130392514148334790206925134627434958358052819633299212582327504398962772728040049042420140860964364438644651432492580422743897821761291862425154343255252583775026543171384094323231691736281136294303552307517561025433816717347431371791281087849264286227621150609745146508179071035602417184619084140537419568547987943501393721117769124162442581924784185567686609430632478656509370462194940451391673893318824171257060825753452343549
Enter the plain text: 
details are 1234
Encrypting string: details are 1234
String in bytes: 1001011169710510811532971141013249505152
Decrypting Bytes: 1001011169710510811532971141013249505152
Decrypted string: details are 1234*/
