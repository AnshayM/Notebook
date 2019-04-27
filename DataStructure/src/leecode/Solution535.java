package leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * TinyURL 的加密与解密
 * <p>
 * TinyURL是一种URL简化服务， 比如：当你输入一个URL https://leetcode.com/problems/design-tinyurl 时，
 * 它将返回一个简化的URL http://tinyurl.com/4e9iAk.
 * <p>
 * 要求：设计一个 TinyURL 的加密 encode 和解密 decode 的方法。
 * 你的加密和解密算法如何设计和运作是没有限制的，你只需要保证一个URL可以被加密成一个TinyURL，
 * 并且这个TinyURL可以用解密方法恢复成原本的URL。
 * <p>
 * 思路：用HashMap来存密钥和密码，每次加密时，创造一个6位随机字符作为key，将目标字符串作为value
 * 然后解码的时候去map里查询即可
 *
 * @author: Anshay
 * @date: 2019/4/27
 */
public class Solution535 {
    Map<String, String> map = new HashMap<>();
    static final String INDEX = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String TINYURL_PREFIX = "http://tinyurl.com/";

    public String encode(String longUrl) {
        char[] chars = new char[6];
        while (true) {
            for (int i = 0; i < 6; i++) {
                chars[i] = INDEX.charAt((int) Math.random() * 62);
            }
            String shortUrl = TINYURL_PREFIX + new String(chars);
            if (!map.containsKey(shortUrl)) {
                map.put(shortUrl, longUrl);
                return shortUrl;
            }
        }
    }

    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }

}
