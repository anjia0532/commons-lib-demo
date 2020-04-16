package com.anjia.commonslibdemo;

import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SpringUtilsTest {


    @Test
    public void testCollections() {
        List<String> list = Collections.emptyList();
        assertThat(CollectionUtils.isEmpty(list));
    }

    @Test
    public void testBeanUtils() {
        User a = new User().setName("a");
        User b = new User();
        BeanUtils.copyProperties(a, b);
        assertThat(b.getName()).isEqualTo("a");
    }

    public class User {
        private String name;

        public String getName() {
            return name;
        }

        public User setName(String name) {
            this.name = name;
            return this;
        }
    }
}
