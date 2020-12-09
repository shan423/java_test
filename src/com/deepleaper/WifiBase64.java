package com.deepleaper;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * This WifiBase64 class provides the functionality to encode/decode a byte array to/from a Base64 String.<br>
 * This WifiBase64 class does NOT follow the RFC 3548 or RFC 4648 specification of Base64 as the used charset can be specified
 * and no padding characters are used.<br>
 * So only the characters provided by the user by calling the {@link #setBase64Charset(String)} will appear in the encoded String and no other characters
 * like equal signs '=' (sometimes used for padding in Base64) will appear.
 *
 * @author Tobias Brandt
 */
public class WifiBase64 {

    static final byte base64ToInt[] =
            {
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 32, -1, -1,
                    30, 6, 27, 48, 36, 38, 40, 59, 35, 37, -1, -1, -1, -1, -1, -1,
                    -1, 39, 14, 44, 31, 9, 24, 50, 58, 19, 28, 17, 61, 1, 23, 15,
                    62, 49, 57, 47, 33, 51, 55, 46, 12, 25, 63, -1, -1, -1, -1, 3,
                    -1, 52, 10, 5, 26, 43, 7, 13, 42, 41, 56, 16, 18, 53, 60, 0,
                    45, 2, 4, 29, 11, 8, 34, 54, 20, 21, 22, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1
            };

    public byte[] decode(String s) throws Exception {
        //字符总长必须是4的倍数
        int sLen = s.length();
        int numGroups = sLen / 4;
        if (4 * numGroups != sLen)
            throw new IllegalArgumentException(
                    "字串长度必须是4的倍数");
        //余1个byte则算漏了两个byte，余2个byte则算漏掉了1个byte
        int missingBytesInLastGroup = 0;
        int numFullGroups = numGroups;
        if (sLen != 0) {
            //余2个byte的情况
            if (s.charAt(sLen - 1) == '=') {
                missingBytesInLastGroup++;
                //如果有余数发生，则完整3个byte组数少一个。
                numFullGroups--;
            }
            //余1个byte的情况
            if (s.charAt(sLen - 2) == '=')
                missingBytesInLastGroup++;
        }
        //总字节长度
        byte[] result = new byte[3 * numGroups - missingBytesInLastGroup];

        try {
            int inCursor = 0, outCursor = 0;
            for (int i = 0; i < numFullGroups; i++) {
                int ch0 = base64toInt(s.charAt(inCursor++), base64ToInt);
                int ch1 = base64toInt(s.charAt(inCursor++), base64ToInt);
                int ch2 = base64toInt(s.charAt(inCursor++), base64ToInt);
                int ch3 = base64toInt(s.charAt(inCursor++), base64ToInt);
                result[outCursor++] = (byte) ((ch0 << 2) | (ch1 >> 4));
                result[outCursor++] = (byte) ((ch1 << 4) | (ch2 >> 2));
                result[outCursor++] = (byte) ((ch2 << 6) | ch3);
            }
            if (missingBytesInLastGroup != 0) {
                int ch0 = base64toInt(s.charAt(inCursor++), base64ToInt);
                int ch1 = base64toInt(s.charAt(inCursor++), base64ToInt);
                //不管余1还是余2个byte，肯定要解码一个byte。
                result[outCursor++] = (byte) ((ch0 << 2) | (ch1 >> 4));

                //如果余2个，即差一个才构成3byte，那么还要解码第二个byte。
                if (missingBytesInLastGroup == 1) {
                    int ch2 = base64toInt(s.charAt(inCursor++), base64ToInt);
                    result[outCursor++] = (byte) ((ch1 << 4) | (ch2 >> 2));
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    private int base64toInt(char c, byte[] alphaToInt) throws Exception {
        int result = alphaToInt[c];
        if (result < 0)
            throw new Exception("非法索引值");
        return result;
    }
}