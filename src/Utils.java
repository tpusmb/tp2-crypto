import java.nio.ByteBuffer;

class Utils {

    static int byteToInt(byte[] bytes){
        return ByteBuffer.wrap(bytes).getInt();
    }
}
