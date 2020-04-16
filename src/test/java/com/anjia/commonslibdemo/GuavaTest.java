package com.anjia.commonslibdemo;

import com.google.common.base.Splitter;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GuavaTest {
    // Google guava工具类的介绍和使用 https://www.cnblogs.com/leeego-123/p/11393394.html
    @Test
    public void testStringToMap(){
        String str = "xiaoming=11,xiaohong=23";
        Map<String,String> map = Splitter.on(",").withKeyValueSeparator("=").split(str);
        assertThat(map.containsKey("xiaoming"));
        assertThat(map.get("xiaoming")).isEqualTo("11");
    }
}
