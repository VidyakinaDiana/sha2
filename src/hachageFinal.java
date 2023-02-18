import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class hachageFinal {
    String word = "";
    public ArrayList<byte[]> utf8(String msg) {
        char[] tabC = msg.toCharArray();
        ArrayList<Long> tab = new ArrayList<>();
        ArrayList<Character> reverse = new ArrayList<>();

        for (char c : tabC) {
            reverse.add(c);
        }

        Collections.reverse(reverse);
        StringBuilder s = new StringBuilder();
        byte[] test = msg.getBytes(StandardCharsets.UTF_8);



        int tailleArr = 0;


        for(int i = 0 ; i< test.length ;i++) {
            int a = reverse.get(i);
            for (int j = 0; j < 8; j++) {
                if (a % 2 == 0) {
                    s.append(0);
                    a = a / 2;
                } else {
                    a = a / 2;
                    s.append(1);
                }
                tailleArr++;
            }
            s.append(" ");
        }

        StringBuilder tailleSt = new StringBuilder(Integer.toBinaryString(tailleArr));

        int taillePourSt = tailleSt.length();



        while(taillePourSt < 8){
            tailleSt.insert(0, "0");
            taillePourSt++;
        }


        Collections.reverse(tab);
        String tabBinaire = String.valueOf(s);
        String[] arrayBinary = tabBinaire.split(" ");
        ArrayList<String> tbinary = new ArrayList<>(Arrays.asList(arrayBinary));

        ArrayList<StringBuilder> tbinary3= new ArrayList<>();
        for (String value : arrayBinary) {
            StringBuilder testo = new StringBuilder(value);
            testo.reverse();
            tbinary3.add(testo);
        }

        Collections.reverse(tbinary3);
        tbinary3.add(new StringBuilder("10000000"));

        StringBuilder sp = new StringBuilder(tbinary.size());

        int taille = tbinary3.size()+1;

        sp.append("0".repeat(8));

        while(taille % 512 != 0){
            tbinary3.add(sp);
            taille++;
        }

        tbinary3.add(new StringBuilder(tailleSt));
       // System.out.println(tbinary3);


        ArrayList<String> mbape = new ArrayList<>();
        ArrayList<String> grizman = new ArrayList<>();
        for (StringBuilder stringBuilder : tbinary3) {
            String messi = stringBuilder.toString();
            mbape.add(messi);
        }

        for(int i = 0 ; i < 64 ; i+=4){
            if(i < 60) {
                grizman.add(mbape.get(i) + mbape.get(i + 1) + mbape.get(i + 2) + mbape.get(i + 3));
            }
            else {
                StringBuilder str = new StringBuilder();
                int normalsize = grizman.get(13).length()-mbape.get(15).length();
                int j = 0;
                String tailleTAb = mbape.get(511);
                while(j < normalsize){
                    String var = "0";
                    str.append(var);
                    j++;
                }
                grizman.add(str+tailleTAb);
            }
        }
        ArrayList<Long> pepe = new ArrayList<>();

        for (String value : grizman) {
            StringBuilder valueBuilder = new StringBuilder(value);
            while(valueBuilder.length() != 32) {
                valueBuilder.insert(0, "0");
            }
            value = valueBuilder.toString();

            for (int i = 0; i < value.length(); i++) {
                pepe.add(Long.parseLong(String.valueOf(value.charAt(i))));
            }
        }

        byte [] bapon = new byte[32];
        int x = 0;

        ArrayList<byte[]> casillas = new ArrayList<>();

        for (Long aLong : pepe) {

            bapon[x] = Byte.parseByte(String.valueOf(aLong));
            x++;

            if (x == 32) {
                casillas.add(bapon);
                bapon = new byte[32];
                x = 0;
            }
        }

       // System.out.println(grizman);

      //  System.out.println("512/32 = 16 -----------------------------------------------------------------------------");

        for (byte[] casilla : casillas) {
         //   System.out.println(Arrays.toString(casilla));
        }

        return casillas;
    }

    //QUESTION 2

    public byte[] ROTR(byte[] a , byte n ) {
        byte[] res = new byte[a.length];
        System.arraycopy(a,a.length-n,res,0,n);
        System.arraycopy(a,0,res,n,a.length-n);
        return res;
    }

    public byte[] SHR(byte[] a , int n){

        byte[] tab = new byte[a.length];
        for (int i = tab.length-1; i > -1; i--) {
            tab[i] = a[(i + n) % a.length];
        }

        byte[] t = new byte[tab.length];

        System.arraycopy(tab, tab.length - n, t, 0, n);
        System.arraycopy(tab, 0, t, n, tab.length - n);

        byte[] tfinal = new byte[t.length-n];
        System.arraycopy(t,0,tfinal,0,t.length-n);

        while(tfinal.length < 32){
           byte[] messi = new byte[tfinal.length+1];
            System.arraycopy(tfinal,0,messi,1,tfinal.length);
            messi[0] = 0;
            tfinal = messi;
        }

        return tfinal;
    }

    public String[] byteToString(byte [] tmp){

        String[] strinArraySigma0 = new String[tmp.length];
        for (int i = 0; i < tmp.length; i++) {
            strinArraySigma0[i] = Integer.toString(tmp[i]);
        }
        return strinArraySigma0;
    }

    private byte[] convertByteArray(String s){
        byte[] arrayByte = new byte[s.length()];
        for (int  j = 0;  j < s.length();  j++) {
            arrayByte[j] = (byte) (s.charAt( j) - '0');
        }

        ArrayList<Byte> arrayListByte = new ArrayList<>();
        for (byte b : arrayByte) {
            arrayListByte.add(b);
        }

        int tailleArrayList2 = arrayListByte.size();
        while (tailleArrayList2 < 32 ){
            arrayListByte.add(0, (byte) 0);
            tailleArrayList2++;
        }

        byte[] arrayByte2 = new byte[arrayListByte.size()];

        for (int  j = 0 ;  j < arrayListByte.size() ;  j++){
            arrayByte2[j] = arrayListByte.get( j);
        }

        return arrayByte2;
    }


    private Integer sumByteArray(byte[] arrayB1 , byte[] arrayB2){
        String[] arrayS1 = byteToString(arrayB1);
        String[] arrayS2 = byteToString(arrayB2);

        String s1 = String.join("", arrayS1);
        String s2 = String.join("", arrayS2);

        int bigIntegerS1 ;
        int bigIntegerS2 ;

        if(s1.length() < 32){
            bigIntegerS1 = Integer.parseInt(s1,2);
        }else{
            BigInteger bigInteger = new BigInteger(s1,2);
            bigIntegerS1 = bigInteger.intValue();
        }
        if(s2.length() < 32){
            bigIntegerS2 = Integer.parseInt(s2,2);
        }else{
            BigInteger bigInteger = new BigInteger(s2,2);
            bigIntegerS2 = bigInteger.intValue();
        }

        StringBuilder b1 = new StringBuilder(Integer.toBinaryString(bigIntegerS1));
        StringBuilder b2 = new StringBuilder(Integer.toBinaryString(bigIntegerS2));

        while (b1.length() < 32) {
            b1.insert(0, "0");
        }
        while (b2.length() < 32) {
            b2.insert(0, "0");
        }

        int t1;
        int t2;

        if(b1.length() < 32){
            t1 = Integer.parseInt(String.valueOf(b1),2);
        }else{
            BigInteger bigInteger = new BigInteger(String.valueOf(b1),2);
            t1 = bigInteger.intValue();
        }
        if(b2.length() < 32){
            t2 = Integer.parseInt(String.valueOf(b2),2);
        }else{
            BigInteger bigInteger = new BigInteger(String.valueOf(b2),2);
            t2 = bigInteger.intValue();
        }


        return (int) ((t1 + t2) % (Math.pow(2,32)));
    }

    public byte[] intToString(int a){
        String t1 = Integer.toBinaryString(a);

        return convertByteArray(t1);
    }

    public void affichageEnHexa(ArrayList<Long> tabBy){
        for (Long aLong : tabBy) {
          //  System.out.println(Long.toHexString(aLong));
        }
    }

    public void affichageEnHexaConcatene(ArrayList<Long> tabBy, int u){
        for (Long aLong : tabBy) {
            if(u != 0)
            System.out.print(Long.toHexString(aLong));
        }
    }

    public ArrayList<byte[]> TransformationEnBytes(ArrayList<Long> tabBy){
        ArrayList<byte[]> K = new ArrayList<>();
        ArrayList<byte[]> KFinal = new ArrayList<>();
        ArrayList<ArrayList<Byte>> testo3 = new ArrayList<>();
        byte[] ByteArray;

        for (Long aLong : tabBy) {
            BigInteger b = new BigInteger(Long.toBinaryString(aLong));
            String asp = b.toString();
            ByteArray = new byte[asp.length()];
            for (int j = 0; j < asp.length(); j++) {
                ByteArray[j] = (byte) (asp.charAt(j) - '0');
            }
            K.add(ByteArray);
        }

        for (byte[] array : K) {
            ArrayList<Byte> arrayTransforme = new ArrayList<>(array.length);
            for (byte b : array) {
                arrayTransforme.add(b);
            }
            testo3.add(arrayTransforme);
        }

        for (int i = 0; i < testo3.size(); i++) {
            ArrayList<Byte> ajout = testo3.get(i);
            while (ajout.size() < 32){
                ajout.add(0, (byte) 0);
            }
            testo3.set(i,ajout);
        }

        for (ArrayList<Byte> enfin : testo3) {
            byte[] array = new byte[enfin.size()];
            for (int i = 0; i < enfin.size(); i++) {
                array[i] = enfin.get(i);
            }
            KFinal.add(array);
        }

        return KFinal;
    }

    public byte[] XOR (byte[] a, byte [] b) {
        int taille = Math.max(a.length,b.length);
        byte[] res = new byte[taille];
        for (int i = 0; i < taille; i++) {
            res[i] = (byte) (verifTaille(a, (byte) i)^verifTaille(b, (byte) i));
        }
        return res;
    }

    public byte verifTaille(byte[] a , byte n){
        return n < a.length ? a[n] : 0;
    }

    public boolean testPrimitif(int p) {
        BigInteger verif = BigInteger.valueOf(p);
        return verif.isProbablePrime(100);
    }

    public ArrayList<Long> hashVal(int nbPrimes) {
        ArrayList<Long> tabByte = new ArrayList<>();

        for (int i = 0; i < nbPrimes; i++) {
            if (testPrimitif(i)) {
                BigDecimal bigDecimal = BigDecimal.valueOf(Math.sqrt(i));
                String conv = String.valueOf(bigDecimal);
                String transf = conv.substring(conv.indexOf(".") + 1);

                BigInteger bigInteger = new BigInteger(transf);
                BigInteger bigInteger1 = new BigInteger("2");
                BigInteger val = new BigInteger(bigInteger1.pow(32).toString());
                BigInteger res = new BigInteger(bigInteger.multiply(val).toString());

                String se = res.toString(10);
                long a = Long.parseLong(se.substring(0,10));
                if(i == 17 ){
                    a = Long.parseLong(se.substring(0,9));
                }

                tabByte.add(a);
            }
        }
        return tabByte;
    }


    public ArrayList<Long> hashVal2() {
        ArrayList<Long> tabByte = new ArrayList<>();

        final long[] K = {
                1116352408, 1899447441, 3049323471L, 3921009573L, 961987163L, 1508970993, 2453635748L, 2870763221L,
                3624381080L, 310598401, 607225278, 1426881987L, 1925078388, 2162078206L, 2614888103L, 3248222580L,
                3835390401L, 4022224774L, 264347078, 604807628L, 770255983, 1249150122, 1555081692, 1996064986,
                2554220882L, 2821834349L, 2952996808L, 3210313671L, 3336571891L, 3584528711L, 113926993, 338241895,
                666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, 2177026350L, 2456956037L,
                2730485921L, 2820302411L, 3259730800L, 3345764771L, 3516065817L, 3600352804L, 4094571909L, 275423344,
                430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779,
                1955562222, 2024104815, 2227730452L, 2361852424L, 2428436474L, 2756734187L, 3204031479L, 3329325298L
        };

        for(long x : K){
            tabByte.add(x);
        }

        return tabByte;
    }

    public byte[] MAJ(byte[] x , byte[] y , byte[] z){
        return XOR(XOR(ET(x,y),ET(x,z)), ET(y,z));
    }

    public byte[] CH(byte[] x, byte[] y, byte[] z) {
        return XOR(ET(x,y), ET(NON(x), z));
    }

    public byte[] ET(byte[] e, byte[] f) {
        byte[] etByte = new byte[e.length];
        for (int i = 0; i < e.length; i++) {
            etByte[i] = (byte) (e[i] & f[i]);
        }
        return etByte;
    }

    public byte[] NON(byte[] e) {
        byte[] non = new byte[e.length];
        for (int i = 0; i < e.length; i++) {
            non[i] = booleanNON(e[i]);
        }
        return non;
    }
    public byte booleanNON(int i) {
        return (byte) ((i == 0) ? 1 : 0);
    }

    //QUESTION 5-6

    public ArrayList<byte[]> hash256(ArrayList<byte[]> M, int size, int u) {

        ArrayList<byte[]> W = new ArrayList<>(M);

        byte[] sigma0;
        byte[] sigma1;

        for(byte[] b : W ){
            //System.out.println(Arrays.toString(b));
        }

        for (int i = 16; i < 64; i++) {

            sigma0 = XOR(XOR(ROTR(W.get(i - 15), (byte) 7), ROTR(W.get(i - 15), (byte) 18)), SHR(W.get(i - 15), 3));

            sigma1 = XOR(XOR(ROTR(W.get(i - 2), (byte) 17), ROTR(W.get(i - 2), (byte) 19)), SHR(W.get(i - 2), 10));

            int x = sumByteArray(W.get(i-16), sigma0);
            String xString = Integer.toBinaryString(x);

            int y = sumByteArray(W.get(i-7), sigma1);
            String yString = Integer.toBinaryString(y);

            int l = sumByteArray(convertByteArray(xString),convertByteArray(yString));

            String w16 = Integer.toBinaryString(l);
            byte[] w16ByteArray2 = convertByteArray(w16);

            W.add(w16ByteArray2);
        }

       /* System.out.println(Arrays.toString(W.get(1)));
        System.out.println("rotation  7 -----------------------------------------");
        System.out.println(Arrays.toString(ROTR(W.get(1), (byte) 7)));
        System.out.println("rotation 18 -----------------------------------------");
        System.out.println(Arrays.toString(ROTR(W.get(1), (byte) 18)));
        System.out.println("Shift 3 -----------------------------------------");
        System.out.println(Arrays.toString(SHR(W.get(1), 3)));

        System.out.println("XOR --------------------------------------------------");
        System.out.println((Arrays.toString(ROTR(W.get(1), (byte) 7))));
        System.out.println((Arrays.toString(ROTR(W.get(1), (byte) 18))));
        System.out.println("XOR -----------------------------------------");
        System.out.println(Arrays.toString(XOR(ROTR(W.get(1), (byte) 7), ROTR(W.get(1), (byte) 18))));
        System.out.println("XOR -----------------------------------------");
        System.out.println(Arrays.toString(XOR(ROTR(W.get(1), (byte) 15), SHR(W.get(1), 10))));

        System.out.println("XOR = O  -----------------------------------------");
        System.out.println(Arrays.toString(XOR(XOR(ROTR(W.get(1), (byte) 7), ROTR(W.get(1), (byte) 18)), SHR(W.get(1), 3))));

        System.out.println("XOR = 1  -----------------------------------------");
        System.out.println(Arrays.toString(XOR(XOR(ROTR(W.get(14), (byte) 17), ROTR(W.get(14), (byte) 19)), SHR(W.get(14), 10))));

        System.out.println(Arrays.toString(W.get(16)));
*/

        ArrayList<Long> testo = hashVal(20);
        ArrayList<Long> testo2 = hashVal2();

        ArrayList<byte[]> H = TransformationEnBytes(testo);
        ArrayList<byte[]> Constantes = TransformationEnBytes(testo2);

        byte[] a = H.get(0);
        byte[] b = H.get(1);
        byte[] c = H.get(2);
        byte[] d = H.get(3);
        byte[] e = H.get(4);
        byte[] f = H.get(5);
        byte[] g = H.get(6);
        byte[] h = H.get(7);



        for (int i = 0; i < 64; i++) {


            byte[] S1 = XOR(XOR(ROTR(e, (byte) 6), ROTR(e, (byte) 11)), ROTR(e, (byte) 25));


            byte[] S0 = XOR(XOR(ROTR(a, (byte) 2), ROTR(a, (byte) 13)), ROTR(a, (byte) 22));


            byte[] ch = CH(e, f, g);


            byte[] MAJ = MAJ(a, b, c);

            int y = sumByteArray(h, S1);
            String s = Integer.toBinaryString(y);
            int k = sumByteArray(convertByteArray(s), ch);
            String s2 = Integer.toBinaryString(k);
            int x = sumByteArray(convertByteArray(s2), Constantes.get(i));
            String s3 = Integer.toBinaryString(x);
            int r = sumByteArray(convertByteArray(s3), W.get(i));
            String s4 = Integer.toBinaryString(r);


            byte[] T1 = convertByteArray(s4);


            int z = sumByteArray(S0, MAJ);
            String zString = Integer.toBinaryString(z);
            byte[] T2 = convertByteArray(zString);


            int eSomme = sumByteArray(d, T1);
            String eSommeString = Integer.toBinaryString(eSomme);

            int aSomme = sumByteArray(T1, T2);
            String aSommeString = Integer.toBinaryString(aSomme);

            h = g;
            g = f;
            f = e;
            e = convertByteArray(eSommeString);
            d = c;
            c = b;
            b = a;
            a = convertByteArray(aSommeString);

        }

                word ="";
             //   System.out.println("a----------------------------------");
             //   System.out.println(Arrays.toString(a));
                if (size != 0){
                    String test = Arrays.toString(a);
                    for(int i =0, j = 0; i<size;) {
                        char q = test.charAt(j++);
                        if (q == '0' || q == '1') {
                            word += q;
                            i++;
                        }
                    }

                }

              /*  System.out.println("b----------------------------------");
                System.out.println(Arrays.toString(b));
                System.out.println("c----------------------------------");
                System.out.println(Arrays.toString(c));
                System.out.println("d----------------------------------");
                System.out.println(Arrays.toString(d));
                System.out.println("e----------------------------------");
                System.out.println(Arrays.toString(e));
                System.out.println("f----------------------------------");
                System.out.println(Arrays.toString(f));
                System.out.println("g----------------------------------");
                System.out.println(Arrays.toString(g));
                System.out.println("h----------------------------------");
                System.out.println(Arrays.toString(h));
*/
        ArrayList<byte[]> sha256 = new ArrayList<>();
        ArrayList<String[]> sha256String = new ArrayList<>();
        ArrayList<String> conversionEnString = new ArrayList<>();
        ArrayList<Long> sha256long = new ArrayList<>();

       int sh1Int =  sumByteArray(H.get(0),a);
       int sh2Int =  sumByteArray(H.get(1),b);
       int sh3Int =  sumByteArray(H.get(2),c);
       int sh4Int =  sumByteArray(H.get(3),d);
       int sh5Int =  sumByteArray(H.get(4),e);
       int sh6Int =  sumByteArray(H.get(5),f);
       int sh7Int =  sumByteArray(H.get(6),g);
       int sh8Int =  sumByteArray(H.get(7),h);



        sha256.add(intToString(sh1Int));
        sha256.add(intToString(sh2Int));
        sha256.add(intToString(sh3Int));
        sha256.add(intToString(sh4Int));
        sha256.add(intToString(sh5Int));
        sha256.add(intToString(sh6Int));
        sha256.add(intToString(sh7Int));
        sha256.add(intToString(sh8Int));



        for (byte[] bytes : sha256) {
           sha256String.add(byteToString(bytes));
        }

        for (String[] strings : sha256String) {
            String t = String.join("", strings);
            conversionEnString.add(t);
        }

        for (String s : conversionEnString) {
            sha256long.add(Long.parseLong(s,2));
        }

        affichageEnHexaConcatene(sha256long,u);
        return W;
    }

    static void exper(String string, String filename1, String filename2 ) throws IOException {

        try(FileWriter file = new FileWriter(filename1); FileWriter file1 = new FileWriter(filename2)){

           int  N = 50;
            String bit_8, bit_10, bit_12, bit_14, bit_16, newword;

            hachageFinal h1 = new hachageFinal();
            ArrayList<byte[]> MB1 = h1.utf8(string);
            ArrayList<Long> testo1 = h1.hashVal(20);
            ArrayList<Long> testo21 = h1.hashVal2();
            h1.affichageEnHexa(testo1);
            h1.affichageEnHexa(testo21);
            int counter = 0, counter1 = 0;
            ArrayList<byte[]> codeword1 = h1.hash256(MB1, 4, 0);
            System.out.println();
            System.out.println();
            bit_8 = h1.word;
            //  System.out.println(bit_8);
            System.out.println();
            System.out.println();
            int size = (int) (Math.random() * 9 + 1);
            String b = "";
            for (int i = 0; i < size; i++)
                b += (char) ((int) (Math.random() * 25 + 97));


            hachageFinal code18 = new hachageFinal();
            ArrayList<byte[]> MBc18 = code18.utf8(b);
            ArrayList<Long> testoc18 = code18.hashVal(20);
            ArrayList<Long> testoc218 = code18.hashVal2();
            code18.affichageEnHexa(testoc18);
            code18.affichageEnHexa(testoc218);
            ArrayList<byte[]> codew18 = code18.hash256(MBc18, 4, 0);
            b = code18.word;
            int flag = 0;
            for (int o = 0; o < 1000; o++) {
                ArrayList<String> experiment = new ArrayList<String>();
                for (int t = 0; t < N; t++) {
                    if (!Objects.equals(bit_8, b) && flag == 0) {
                        counter++;
                    }
                    else {flag = 1;}
                      //  System.out.println(b);

                  //  System.out.println(b);
                    experiment.add(b);
                    b = "";
                    size = (int) (Math.random() * 9 + 1);
                    for (int i = 0; i < size; i++)
                        b += (char) ((int) (Math.random() * 25 + 97));
                    MBc18 = code18.utf8(b);
                    testoc18 = code18.hashVal(20);
                    testoc218 = code18.hashVal2();
                    code18.affichageEnHexa(testoc18);
                    code18.affichageEnHexa(testoc218);
                    codew18 = code18.hash256(MBc18, 4, 0);
                    b = code18.word;
                }
                flag = 0;
               // if(o == 0)
                   // System.out.println(experiment);
                int flag1 = 0;
                for (int l = 0; l < N; l++)
                    for (int k = 1; k < N - 1; k++) {
                        if (l != k) {
                            if (!experiment.get(l).equals(experiment.get(k)) && flag1 == 0)
                                counter1++;
                            else{
                                flag1 = 1;
                                break;}
                        }

                    }
                flag1 = 0;
            }
            System.out.println("���������� ������� ���������(�������) ��� 4 ��� " + counter / 1000);
            System.out.println("���������� ��������(�������) ��� 4 ��� " + counter1 / 1000);

            counter = 0;
            counter1 = 0;

            ArrayList<byte[]> codeword2 = h1.hash256(MB1, 6, 0);
            System.out.println();
            System.out.println();
            bit_10 = h1.word;
            //   System.out.println(bit_10);
            System.out.println();
            System.out.println();
            size = (int) (Math.random() * 9 + 1);
            b = "";
            for (int i = 0; i < size; i++)
                b += (char) ((int) (Math.random() * 25 + 97));
            hachageFinal code110 = new hachageFinal();
            ArrayList<byte[]> MBc110 = code110.utf8(b);
            ArrayList<Long> testoc110 = code110.hashVal(20);
            ArrayList<Long> testoc2110 = code110.hashVal2();
            code110.affichageEnHexa(testoc110);
            code110.affichageEnHexa(testoc2110);
            ArrayList<byte[]> codew110 = code110.hash256(MBc110, 6, 0);
            b = code110.word;
             flag = 0;
            for (int o = 0; o < 1000; o++) {
                ArrayList<String> experiment = new ArrayList<String>();
                for (int t = 0; t < N; t++) {
                    if (!Objects.equals(bit_10, b) && flag == 0) {
                        counter++;

                    }
                    else{flag = 1;}
                    experiment.add(b);
                    b = "";
                    size = (int) (Math.random() * 9 + 1);
                    for (int i = 0; i < size; i++)
                        b += (char) ((int) (Math.random() * 25 + 97));

                    MBc110 = code110.utf8(b);
                    testoc110 = code110.hashVal(20);
                    testoc2110 = code110.hashVal2();
                    code110.affichageEnHexa(testoc110);
                    code110.affichageEnHexa(testoc2110);
                    codew110 = code110.hash256(MBc110, 6, 0);
                    b = code110.word;
                }
                flag = 0;

                int flag1 = 0;
                for (int l = 0; l < N; l++)
                    for (int k = 1; k < N - 1; k++) {
                        if (l != k) {
                            if (!experiment.get(l).equals(experiment.get(k)) && flag1 == 0)
                                counter1++;
                            else{
                                flag1 = 1;
                                break;}
                        }

                    }
                flag1 = 0;

            }
            System.out.println("���������� ������� ���������(�������) ��� 6 ��� " + counter / 1000);
            System.out.println("���������� ��������(�������) ��� 6 ���  " + counter1 / 1000);
            file.write(10 + " " + counter1 / 1000 + '\n');
            file1.write(10 + " " + counter / 1000 + '\n');
            counter = 0;
            counter1 = 0;


            ArrayList<byte[]> codeword3 = h1.hash256(MB1, 8, 0);
            System.out.println();
            System.out.println();
            bit_12 = h1.word;
            //   System.out.println(bit_10);
            System.out.println();
            System.out.println();
            size = (int) (Math.random() * 9 + 1);
            b = "";
            for (int i = 0; i < size; i++)
                b += (char) ((int) (Math.random() * 25 + 97));
            hachageFinal code112 = new hachageFinal();
            ArrayList<byte[]> MBc112 = code112.utf8(b);
            ArrayList<Long> testoc112 = code112.hashVal(20);
            ArrayList<Long> testoc2112 = code112.hashVal2();
            code112.affichageEnHexa(testoc112);
            code112.affichageEnHexa(testoc2112);
            ArrayList<byte[]> codew112 = code112.hash256(MBc112, 8, 0);
            b = code112.word;
            flag = 0;
            for (int o = 0; o < 1000; o++) {
                ArrayList<String> experiment = new ArrayList<String>();
                for (int t = 0; t < N; t++) {
                    if (!Objects.equals(bit_12, b) && flag == 0) {
                        counter++;

                    }
                    else{flag = 1;}
                    experiment.add(b);
                    b = "";
                    size = (int) (Math.random() * 9 + 1);
                    for (int i = 0; i < size; i++)
                        b += (char) ((int) (Math.random() * 25 + 97));
                    MBc112 = code112.utf8(b);
                    testoc112 = code112.hashVal(20);
                    testoc2112 = code112.hashVal2();
                    code112.affichageEnHexa(testoc112);
                    code112.affichageEnHexa(testoc2112);
                    codew112 = code112.hash256(MBc112, 8, 0);
                    b = code112.word;
                }
                flag = 0;
                int flag1 = 0;
                for (int l = 0; l < N; l++)
                    for (int k = 1; k < N - 1; k++) {
                        if (l != k) {
                            if (!experiment.get(l).equals(experiment.get(k)) && flag1 == 0)
                                counter1++;
                            else{
                                flag1 = 1;
                                break;}
                        }

                    }
                flag1 = 0;

            }
            System.out.println("���������� ������� ���������(�������) ��� 8 ��� " + counter / 1000);
            System.out.println("���������� ��������(�������) ��� 8 ���  " + counter1 / 1000);

            counter = 0;
            counter1 = 0;


            ArrayList<byte[]> codeword4 = h1.hash256(MB1, 10, 0);
            System.out.println();
            System.out.println();
            bit_14 = h1.word;
            //   System.out.println(bit_10);
            System.out.println();
            System.out.println();
            size = (int) (Math.random() * 9 + 1);
            b = "";
            for (int i = 0; i < size; i++)
                b += (char) ((int) (Math.random() * 25 + 97));
            hachageFinal code114 = new hachageFinal();
            ArrayList<byte[]> MBc114 = code114.utf8(b);
            ArrayList<Long> testoc114 = code114.hashVal(20);
            ArrayList<Long> testoc2114 = code114.hashVal2();
            code114.affichageEnHexa(testoc114);
            code114.affichageEnHexa(testoc2114);
            ArrayList<byte[]> codew114 = code114.hash256(MBc114, 10, 0);
            b = code114.word;
             flag = 0;
            for (int o = 0; o < 1000; o++) {
                ArrayList<String> experiment = new ArrayList<String>();
                for (int t = 0; t < N; t++) {
                    if (!Objects.equals(bit_14, b) && flag == 0) {
                        counter++;

                    }
                    else{flag = 1;}
                    experiment.add(b);
                    b = "";
                    size = (int) (Math.random() * 9 + 1);
                    for (int i = 0; i < size; i++)
                        b += (char) ((int) (Math.random() * 25 + 97));
                    MBc114 = code114.utf8(b);
                    testoc114 = code114.hashVal(20);
                    testoc2114 = code114.hashVal2();
                    code114.affichageEnHexa(testoc114);
                    code114.affichageEnHexa(testoc2114);
                    codew114 = code114.hash256(MBc114, 10, 0);
                    b = code114.word;
                }
                flag =0;
                int flag1 = 0;
                for (int l = 0; l < N; l++)
                    for (int k = 1; k < N - 1; k++) {
                        if (l != k) {
                            if (!experiment.get(l).equals(experiment.get(k)) && flag1 == 0)
                                counter1++;
                            else{
                                flag1 = 1;
                                break;}
                        }

                    }
                flag1 = 0;

            }
            System.out.println("���������� ������� ���������(�������) ��� 10 ���  " + counter / 1000);
            System.out.println("���������� ��������(�������) ��� 10 ���  " + counter1 / 1000);
            file.write(14 + " " + counter1 / 1000 + '\n');
            file1.write(14 + " " + counter / 1000 + '\n');
            counter = 0;
            counter1 = 0;
            b = "";


            ArrayList<byte[]> codeword5 = h1.hash256(MB1, 12, 0);
            System.out.println();
            System.out.println();
            bit_16 = h1.word;
            //   System.out.println(bit_10);
            System.out.println();
            System.out.println();
            size = (int) (Math.random() * 9 + 1);
            b = "";
            for (int i = 0; i < size; i++)
                b += (char) ((int) (Math.random() * 25 + 97));
            hachageFinal code116 = new hachageFinal();
            ArrayList<byte[]> MBc116 = code116.utf8(b);
            ArrayList<Long> testoc116 = code116.hashVal(20);
            ArrayList<Long> testoc2116 = code116.hashVal2();
            code116.affichageEnHexa(testoc116);
            code116.affichageEnHexa(testoc2116);
            ArrayList<byte[]> codew116 = code116.hash256(MBc116, 12, 0);
            b = code116.word;
            flag = 0;
            for (int o = 0; o < 1000; o++) {
                ArrayList<String> experiment = new ArrayList<String>();
                for (int t = 0; t < N; t++) {
                    if (!Objects.equals(bit_16, b) && flag == 0) {
                        counter++;

                    }
                    else{flag = 1;}
                    experiment.add(b);
                    b = "";
                    size = (int) (Math.random() * 9 + 1);
                    for (int i = 0; i < size; i++)
                        b += (char) ((int) (Math.random() * 25 + 97));
                    MBc116 = code116.utf8(b);
                    testoc116 = code116.hashVal(20);
                    testoc2116 = code116.hashVal2();
                    code116.affichageEnHexa(testoc116);
                    code116.affichageEnHexa(testoc2116);
                    codew116 = code116.hash256(MBc116, 12, 0);
                    b = code116.word;
                }
                flag = 0;
                int flag1 = 0;
                for (int l = 0; l < N; l++)
                    for (int k = 1; k < N - 1; k++) {
                        if (l != k) {
                            if (!experiment.get(l).equals(experiment.get(k)) && flag1 == 0)
                                counter1++;
                            else{
                                flag1 = 1;
                                break;}
                        }

                    }
                flag1 = 0;
            }
            System.out.println("���������� ������� ���������(�������) ��� 12 ���  " + counter / 1000);
            System.out.println("���������� ��������(�������) ��� 12 ���  " + counter1 / 1000);

            counter = 0;
            counter1 = 0;
            b = "";

            file.close();
            file1.close();
        }catch(IOException ex){  System.out.println("error");};
    }


    public static void main(String[] args) throws IOException {

        hachageFinal h = new hachageFinal();
        ArrayList<byte[]> MB = h.utf8("hello world");
        ArrayList<Long> testo = h.hashVal(20);
        ArrayList<Long> testo12 = h.hashVal2();
        h.affichageEnHexa(testo);
        h.affichageEnHexa(testo12);
        System.out.println("hello world");
        ArrayList<byte[]> sha256 = h.hash256(MB, 0,1);
        System.out.println();
        System.out.println();


        System.out.println("--------------------------------------------------------EXPERIMENT --------------------------------------------------------");
        System.out.println("����������� ��� ������� ���-�����:");
        System.out.println("mother");
        exper("mother","col1","proobraz1");
        System.out.println("--------------------------------------------------------EXPERIMENT --------------------------------------------------------");
        System.out.println("����������� ��� ������� ���-�����:");
        System.out.println("00000");
        exper("00000","col3","proobraz3");
        System.out.println("--------------------------------------------------------EXPERIMENT --------------------------------------------------------");
        System.out.println("����������� ��� �������� ���-�����:");
        System.out.println("elephant");
        exper("elephant","col3","proobraz3");
        System.out.println("--------------------------------------------------------EXPERIMENT --------------------------------------------------------");
    }


}

