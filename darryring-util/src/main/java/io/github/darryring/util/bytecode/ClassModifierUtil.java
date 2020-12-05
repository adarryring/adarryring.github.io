package io.github.darryring.util.bytecode;

import io.github.darryring.util.type.ByteUtil;

/**
 * 字节码符号引用篡改类
 */
public class ClassModifierUtil {

    // Class 文件中常董池的起始偏移
    private static final int CONSTANT_POOL_COUNT_INDEX = 8;
    // CONSTANT_Utf8_info 常量的 tag 标志
    private static final int CONSTANT_Utf8_info = 1;
    // V 常量池中 11 种常量所占的长度， C0NSTANT^ Jtf8_info 型常量除外， 因为它不是定长的
    private static final int[] CONSTANT_ITEM_LENGTH = {-1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5};
    private static final int ul = 1;
    private static final int u2 = 2;
    private byte[] classByte;

    public ClassModifierUtil(byte[] classByte) {
        this.classByte = classByte;
    }

    /**
     * 修 改 常 量 池 中 CONSTANT_Utf8_info 常量的内容
     *
     * @param oldStr 修改前的字符串
     * @param newStr 修改后的字符串
     * @return 修改结果
     */
    public byte[] modifyUTF8Constant(String oldStr, String newStr) {
        int cpc = getConstantPoolCount();
        int offset = CONSTANT_POOL_COUNT_INDEX + u2;
        for (int i = 0; i < cpc; i++) {
            int tag = ByteUtil.bytes2Int(classByte, offset, ul);
            if (tag == CONSTANT_Utf8_info) {
                int len = ByteUtil.bytes2Int(classByte, offset + ul, u2);
                offset += (ul + u2);
                String str = ByteUtil.bytes2String(classByte, offset, len);
                if (str.equalsIgnoreCase(oldStr)) {
                    byte[] strBytes = ByteUtil.string2Bytes(newStr);
                    byte[] strLen = ByteUtil.int2Bytes(newStr.length(), u2);
                    classByte = ByteUtil.bytesReplace(classByte, offset - u2, u2, strLen);
                    classByte = ByteUtil.bytesReplace(classByte, offset, len, strBytes);
                    return classByte;
                } else {
                    offset += len;
                }
            } else {
                offset += CONSTANT_ITEM_LENGTH[tag];
            }
        }
        return classByte;
    }

    /**
     * 获取常量池中的常量
     *
     * @return 常量池数量
     */
    public int getConstantPoolCount() {
        return ByteUtil.bytes2Int(classByte, CONSTANT_POOL_COUNT_INDEX, u2);
    }
}
