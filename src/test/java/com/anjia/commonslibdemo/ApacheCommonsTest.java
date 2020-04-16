package com.anjia.commonslibdemo;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;


public class ApacheCommonsTest {
    // Apache Commons Lang 3 学习（一） https://blog.csdn.net/wangyasheng/article/details/81943571

    @Test
    public void testStringUtilsIsBlank() {
        assertThat(StringUtils.isBlank(null));
        assertThat(StringUtils.isBlank(" "));
        assertThat(StringUtils.isAllBlank(null, "", "  "));
    }

    @Test
    public void testStringUtilsJoin() {
        assertThat(StringUtils.join("a", "b", "c")).isEqualTo("abc");
        assertThat(StringUtils.joinWith("|", "a", "b", "c")).isEqualTo("a|b|c");
        assertThat(Objects.isNull(StringUtils.join(null)));
    }

    @Test
    public void testStringUtilsSplit() {
        String str = ":a:b:c:::::";
        assertThat(StringUtils.splitByWholeSeparatorPreserveAllTokens(str, ":").length != str.split(":").length);
    }

    @Test
    public void testRandomUtils() {
        for (int i = 0; i < 1000; i++) {
            int randomInt = RandomUtils.nextInt(10, 20);
            assertThat(randomInt).isGreaterThanOrEqualTo(10);
            assertThat(randomInt).isLessThanOrEqualTo(20);
            System.out.println(randomInt);
        }
    }

    @Test
    public void testNumberUtils() {
        assertThat(NumberUtils.isCreatable("7.5"));
    }

    // Apache Commons IO入门教程(转) https://www.cnblogs.com/softidea/p/4279576.html
    @Test
    public void testFileIO() throws IOException {
        File test = new File("test.txt");
        FileUtils.writeStringToFile(test, "test", "utf-8");
        assertThat(FileUtils.readFileToString(test, "utf-8")).isEqualTo("test");
        FileUtils.forceDelete(test);
    }

    // Apache commons codec |MD5 SHA BASE64 简单方便操作 https://blog.csdn.net/u012881904/article/details/52767853
    @Test
    public void testMd5() {
        assertThat(DigestUtils.md5Hex("a")).isEqualTo("0cc175b9c0f1b6a831c399e269772661");
    }

}
