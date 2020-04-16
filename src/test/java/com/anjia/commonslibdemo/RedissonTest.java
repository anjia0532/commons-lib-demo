package com.anjia.commonslibdemo;

import com.anjia.commonslibdemo.base.Constants;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        classes = Application.class,
        properties = {
                "spring.redis.redisson.config=classpath:redisson.yaml",
                "spring.redis.timeout=10000"
        })
public class RedissonTest {

    // https://github.com/redisson/redisson/wiki/%E7%9B%AE%E5%BD%95

    @Autowired
    RedissonClient redisson;

    final String QUEUE_NAME = "queue";
    final String MAP_NAME = "map";

    @After
    public void shutdown() {
        getQueue(QUEUE_NAME).clear();
        getMap(MAP_NAME).clear();
    }

    @Test
    public void testQueue() {
        RQueue<Integer> queue = getQueue(QUEUE_NAME);
        queue.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));
        queue.poll(3);
        assertThat(queue).containsExactly(4, 5, 6);
    }

    @Test
    public void testGeo() {

        RGeo<String> geo = redisson.getGeo(RandomStringUtils.randomAlphanumeric(10));
        List<GeoEntry> entries = new ArrayList<>(10000);
        for (int i = 0; i < 10000; i++) {
            entries.add(new GeoEntry(RandomUtils.nextDouble(100, 150), RandomUtils.nextDouble(20, 50), RandomStringUtils.randomAlphanumeric(10)));
        }
        geo.add(entries.toArray(new GeoEntry[0]));
        geo.expireAsync(1,TimeUnit.MINUTES);
        Map<String,GeoPosition> geoPositionMap = geo.radiusWithPosition(RandomUtils.nextDouble(100, 120), RandomUtils.nextDouble(20, 40),100,GeoUnit.KILOMETERS,GeoOrder.ASC,10);
        System.out.println(geoPositionMap);
    }

    @Test
    public void testMap() {
        // from store
        RMap<Integer, Integer> map = getMap("map");
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        map.expire(1, TimeUnit.MINUTES);
        assertThat(map.size()).isEqualTo(3);
    }

    protected <K, V> RMap<K, V> getMap(String name) {
        return redisson.getMap(Constants.REDIS_PREFIX_NAMESPACE + name);
    }

    protected <T> RQueue<T> getQueue(String name) {
        return redisson.getQueue(Constants.REDIS_PREFIX_NAMESPACE + name);
    }

}
