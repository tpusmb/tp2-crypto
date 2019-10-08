import java.nio.ByteBuffer;

class Utils {

    static int byteToInt(byte[] bytes){
        return ByteBuffer.wrap(bytes).getInt();
    }

    static String byteToString(byte[] bytes) {
        StringBuilder buffer = new StringBuilder();
        for (byte b : bytes) {
            buffer.append(Integer.toHexString((b & 0xFF) | 0x100), 1, 3);
        }
        return buffer.toString();
    }
}
