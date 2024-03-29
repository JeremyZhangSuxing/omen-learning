package com.omen.learning.sample;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtil {

    /**
     * @param key (字符串 汉字)
     * @return 汉字转拼音 其它字符不变
     */
    public static String getPinyin(String key) throws BadHanyuPinyinOutputFormatCombination {
        HanyuPinyinOutputFormat letter = new HanyuPinyinOutputFormat();
        letter.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        letter.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        letter.setVCharType(HanyuPinyinVCharType.WITH_V);
        char[] arrays = key.trim().toCharArray();
        if (Character.toString(arrays[0]).matches("[\\u4e00-\\u9fa5]")) {
            String[] temp = PinyinHelper.toHanyuPinyinStringArray(arrays[0], letter);
            return temp[0].substring(0, 1);
        }
        return String.valueOf(arrays[0]);
    }

}