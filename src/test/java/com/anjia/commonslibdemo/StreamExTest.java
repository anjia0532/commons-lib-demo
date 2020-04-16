package com.anjia.commonslibdemo;

import one.util.streamex.StreamEx;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamExTest {

    // Java 8 Stream之实战篇 https://segmentfault.com/a/1190000015775709
    @Test
    public void mapToList(){
        Map<String,String> maps = new HashMap<>();
        maps.put("a","a_a");
        maps.put("bb","bb_bb");
        maps.put("ccc","ccc_ccc");
        maps.put("ccc","dddd_dddd");

        List<String> lists = StreamEx.of(maps.entrySet()).map(Map.Entry::getValue).toList();
        assertThat(lists.size()).isEqualTo(3);
        assertThat(lists).contains("bb_bb");
    }
}
