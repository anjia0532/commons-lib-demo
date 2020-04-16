package com.anjia.commonslibdemo;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.IdcardUtil;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HutoolTest {
    // https://www.hutool.cn/docs/#/

    @Test
    public void testRandomId(){
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        assertThat(snowflake.nextIdStr().length()).isEqualTo(19);
    }

    @Test
    public void testValidator(){
        System.out.println(Validator.isEmail("a@163.com"));
    }

    @Test
    public void testIdCard(){
        assertThat(IdcardUtil.isValidCard("111111111111111111")).isFalse();
        assertThat(IdcardUtil.isValidCard("370283199109172217"));
    }
}
