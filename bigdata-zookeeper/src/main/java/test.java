import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class test {

    public static void main(String[] args) {
        double i = (float)0/(float)9;
        if(!Double.isFinite(i) && !Double.isInfinite(i)){
            System.out.println(i);
        }else {
            System.out.println("分子或者分母有为0");
        }
    }
}
