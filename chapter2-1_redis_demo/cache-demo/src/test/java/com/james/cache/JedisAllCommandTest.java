package com.james.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.james.cache.utils.JedisUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/*
 * AUTHOR james
 */
public class JedisAllCommandTest {
    // 基础指令封装后测试
    @Test
    public void testCommond() {
        // 工具类初始化
        JedisUtils jedis = new JedisUtils("192.168.1.111", 6379, "12345678");

        for (int i = 0; i < 100; i++) {
            // 设值
            jedis.set("n" + i, String.valueOf(i));
        }
        System.out.println("keys from redis return =======" + jedis.keys("*"));

    }

    // 使用pipeline批量删除
    // @Test
    public void testPipelineMdel() {
        // 工具类初始化
        JedisUtils jedis = new JedisUtils("192.168.1.111", 6379, "12345678");
        List<String> keys = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            keys.add("n" + i);
        }
        jedis.mdel(keys);
        System.out.println("after mdel the redis return ---------" + jedis.keys("*"));
    }

    // 使用pipeline提交所有操作
    @Test
    public void testPipelineSyncAll() {
        // 工具类初始化
        Jedis jedis = new Jedis("192.168.1.111", 6379);
        jedis.auth("12345678");
        // 获取pipeline对象
        Pipeline pipe = jedis.pipelined();
        pipe.multi();
        pipe.set("name", "james"); // 调值
        pipe.incr("age");// 自增
        pipe.get("name");
        pipe.discard();
        // 将不同类型的操作命令合并提交，并将操作操作以list返回
        List<Object> list = pipe.syncAndReturnAll();

        for (Object obj : list) {
            // 将操作结果打印出来
            System.out.println(obj);
        }
        // 断开连接，释放资源
        jedis.disconnect();
    }

    //@Test
    public void pipeCompare() {
        Jedis redis = new Jedis("192.168.1.111", 6379);
        redis.auth("12345678");//授权密码 对应redis.conf的requirepass密码
        Map<String, String> data = new HashMap<String, String>();
        redis.select(8);//使用第8个库
        redis.flushDB();//清空第8个库所有数据
        // hmset
        long start = System.currentTimeMillis();
        // 直接hmset
        for (int i = 0; i < 10000; i++) {
            data.clear();  //清空map
            data.put("k_" + i, "v_" + i);
            redis.hmset("key_" + i, data); //循环执行10000条数据插入redis
        }
        long end = System.currentTimeMillis();
        System.out.println("    共插入:[" + redis.dbSize() + "]条 .. ");
        System.out.println("1,未使用PIPE批量设值耗时" + (end - start) / 1000 + "秒..");
        redis.select(8);
        redis.flushDB();
        // 使用pipeline hmset
        Pipeline pipe = redis.pipelined();
        start = System.currentTimeMillis();
        //
        for (int i = 0; i < 10000; i++) {
            data.clear();
            data.put("k_" + i, "v_" + i);
            pipe.hmset("key_" + i, data); //将值封装到PIPE对象，此时并未执行，还停留在客户端
        }
        pipe.sync(); //将封装后的PIPE一次性发给redis
        end = System.currentTimeMillis();
        System.out.println("    PIPE共插入:[" + redis.dbSize() + "]条 .. ");
        System.out.println("2,使用PIPE批量设值耗时" + (end - start) / 1000 + "秒 ..");
//--------------------------------------------------------------------------------------------------
        // hmget
        Set<String> keys = redis.keys("key_*"); //将上面设值所有结果键查询出来
        // 直接使用Jedis hgetall
        start = System.currentTimeMillis();
        Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();
        for (String key : keys) {
            //此处keys根据以上的设值结果，共有10000个，循环10000次
            result.put(key, redis.hgetAll(key)); //使用redis对象根据键值去取值，将结果放入result对象
        }
        end = System.currentTimeMillis();
        System.out.println("    共取值:[" + redis.dbSize() + "]条 .. ");
        System.out.println("3,未使用PIPE批量取值耗时 " + (end - start) / 1000 + "秒 ..");

        // 使用pipeline hgetall
        result.clear();
        start = System.currentTimeMillis();
        for (String key : keys) {
            pipe.hgetAll(key); //使用PIPE封装需要取值的key,此时还停留在客户端，并未真正执行查询请求
        }
        pipe.sync();  //提交到redis进行查询

        end = System.currentTimeMillis();
        System.out.println("    PIPE共取值:[" + redis.dbSize() + "]条 .. ");
        System.out.println("4,使用PIPE批量取值耗时" + (end - start) / 1000 + "秒 ..");

        redis.disconnect();
    }

    // 使用Lua操作 eval "return redis.call('get',KEYS[1])" 1 name
    @Test
    public void testLuaScript() {
        // 工具类初始化
        Jedis jedis = new Jedis("192.168.1.111", 6379);
        jedis.auth("12345678");
        jedis.set("name", "james");

        String script = "return redis.call('get',KEYS[1])";
        Object result = jedis.eval(script, 1, "name");
        // 将操作结果打印出来
        System.out.println(result);
        // 断开连接，释放资源
        jedis.disconnect();
    }

    //使用Lua脚本加载到redis中，然后计算出sha值，
    //这里同样可以用redis客户端来load脚本，计算出sha值
    @Test
    public void testLuaFile() {
        // 工具类初始化
        Jedis jedis = new Jedis("192.168.1.111", 6379);
        jedis.auth("12345678");

        StringBuffer scriptBuffer = new StringBuffer("");
        scriptBuffer.append("\n");
        // 拼装LUA串
        scriptBuffer.append("local key = KEYS[1]\n");
        scriptBuffer.append("local limit = tonumber(ARGV[1])\n");
        scriptBuffer.append("local expire_time = ARGV[2]\n");

        scriptBuffer.append("local is_exists = redis.call('EXISTS', key)\n");
        scriptBuffer.append("if is_exists == 1 then\n");
        scriptBuffer.append("    if redis.call('INCR', key) > limit then\n");
        scriptBuffer.append("        return 0\n");
        scriptBuffer.append("    else\n");
        scriptBuffer.append("        return 1\n");
        scriptBuffer.append("    end\n");
        scriptBuffer.append("else\n");
        scriptBuffer.append("    redis.call('SET', key, 1)\n");
        scriptBuffer.append("    redis.call('EXPIRE', key, expire_time)\n");
        scriptBuffer.append("    return 1\n");
        scriptBuffer.append("end\n");
        // 计算sha值
        String scriptsha = jedis.scriptLoad(scriptBuffer.toString());
        System.out.println("===scriptsha==" + scriptsha);
        Object result = jedis.evalsha(scriptsha, 1, "rate.limit:127.0.0.1", "10", "20");
        // 将操作结果打印出来
        System.out.println(result);
        // 断开连接，释放资源
        jedis.disconnect();

    }

    //根据LUA的sha1值，来执行LUA逻辑，效果和上面一样，前提是LUA脚本要加载到redis
    @Test
    public void testLuaSha() {
        // 工具类初始化
        Jedis jedis = new Jedis("127.0.0.1", 6379);
//		jedis.auth("12345678");
        // 计算sha值
        String scriptsha = "9b0fb3acef8d5d5ca0f2600a2e6de4376727818e";
        Object result = jedis.evalsha(scriptsha, 1, "rate.limit:127.0.0.1", "10", "20");
        // 将操作结果打印出来
        System.out.println(result);
        // 断开连接，释放资源
        jedis.disconnect();
    }

    // @Test
    public void ttt() {
        Jedis jedis = new Jedis("192.168.1.111", 6379, 2000, 2000, false, null, null, null);
        int i = 0;
        i = 9;
        jedis.connect();
        jedis.auth("12345678");
        System.out.println("=====" + jedis.get("name"));
    }
}
