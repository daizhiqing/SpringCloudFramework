package com.dzq.base.redis;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: yaorongfang
 * @Description: Redis缓存服务
 * @date: 2017/12/2
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class RedisServer {
    private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    private RedisTemplate redisTemplate;

    /**
     * 写入缓存
     * @param key
     *            缓存key
     * @param value
     *            缓存value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 写入缓存设置失效时间
     *
     * @param key
     *            缓存key
     * @param value
     *            缓存value
     * @param expireTime
     *            缓存失效时间
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return result;
    }

    /**
     * 设置已有缓存的缓存时间
     *
     * @param key
     *            缓存key
     * @param expireTime
     *            缓存失效时间
     * @return
     */
    public boolean expire(final String key, Long expireTime) {
        return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 批量删除缓存-keys
     *
     * @param keys
     *            缓存key的List集合
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除缓存-给定模式
     *
     * @param pattern
     *            给定模式 pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            redisTemplate.delete(keys);
        }
    }

    /**
     * 删除缓存
     * @param key
     *            缓存key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断一个缓存是否存在
     * @param key
     *            缓存key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     * @param key
     *            缓存key
     * @return
     */
    public String get(final String key) {
        if (StringUtils.isEmpty(key)) {
            return null;
        }
        Object result = null;
        if (exists(key)) {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            result = operations.get(key);
        }
        return null == result ? null : result.toString();
    }

    /**
     * 哈希添加缓存
     * @param key
     *            缓存key
     * @param hashKey
     *            Hash-key
     * @param value
     *            缓存value
     */
    public void hashSet(String key, String hashKey, String value) {
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    /**
     * 哈希获取缓存
     * @param key
     *            缓存key
     * @param hashKey
     *            Hash-key
     * @return
     */
    public String hashGet(String key, String hashKey) {
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }


    /**
     * 删除一个hash  中的 key
     * @param key
     * @param hashKey
     * @return
     */
    public Long hashDel(String key, Object hashKey) {
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        return hash.delete(key, hashKey);
    }

    /**
     * 获取对应一个hash map
     * @param key
     * @return
     */
    public Map<String, String> hashEntries(String key) {
        HashOperations<String, String, String> hash = redisTemplate.opsForHash();
        return hash.entries(key);
    }



    /**
     * 无序set集合添加缓存
     * @param key
     *            缓存key
     * @param value
     *            缓存value
     */
    public void sadd(String key, Object value) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        set.add(key, value);
    }

    /**
     * 无序set集合获取缓存
     *
     * @param key
     *            缓存key
     * @return
     */
    public Set<Object> smembers(String key) {
        SetOperations<String, Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * 有序集合添加缓存
     *
     * @param key
     *            缓存key
     * @param value
     *            缓存value
     * @param scoure
     */
    public void zAdd(String key, Object value, double scoure) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }

    /**
     * 有序集合获取缓存
     *
     * @param key
     *            缓存key
     * @param scoure
     *            score最小值，闭区间
     * @param scoure1
     *            score最大值，闭区间
     * @return
     */
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

    /**
     * 获取一个缓存剩余有效时间 <br>
     * 1、不存在的 key // 指令：TTL key 返回(integer) -2 <br>
     * 2、存在，但没有设置剩余生存时间 // 指令 TTL key 返回 (integer) -1 <br>
     * 3、有剩余生存时间的 key // 指令 TTL key 返回秒(integer)
     *
     * @param key
     *            缓存key
     * @return
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 获取一个缓存剩余有效时间 <br>
     * 1、不存在的 key // 指令：TTL key 返回(integer) -2 <br>
     * 2、存在，但没有设置剩余生存时间 // 指令 TTL key 返回 (integer) -1 <br>
     * 3、有剩余生存时间的 key // 指令 TTL key 返回秒(integer)
     *
     * @param key
     *            缓存key
     * @return
     */
    public Long getExpireByTime(String key) {
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /************************************************
     * 有序list 集合操作
     **********************************************/
    /**
     * 列表添加（leftPush依次由左边添加;返回添加成功后list size ）
     *
     * @param key
     *            缓存key
     * @param val
     *            缓存value
     * @return
     */
    public Long leftPush(String key, Object val) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.leftPush(key, val);
    }

    /**
     * 列表添加（rightPush依次有右边添加;返回添加后 list size ）
     *
     * @param key
     *            缓存key
     * @param val
     *            缓存value
     * @return
     */
    public Long rightPush(String key, Object val) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.rightPush(key, val);
    }

    /**
     * 删除最左边的那个项 （rightPush 添加 删除最早一条 ；leftPush 添加 删除最晚一条 ）
     *
     * @param key
     *            缓存key
     * @return
     */
    public Object leftPop(String key) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.leftPop(key);
    }

    /**
     * 删除最右边的那个项 （rightPush 添加 删除最晚一条 ；leftPush 添加 删除最早一条 ）
     *
     * @param key
     *            缓存key
     * @return
     */
    public Object rightPop(String key) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.rightPop(key);
    }

    /**
     * 列表获取（获得list集合 【 0 ，-1 代表所有值】）
     *
     * @param key
     *            缓存key
     * @return
     */
    public List<Object> getList(String key) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.range(key, 0, -1);
    }

    /**
     * 传入list 集合 key 和需要 删除的下标，返回移除数量
     *
     * @param key
     *            缓存key
     * @param index
     *            需要删除的下标
     * @return
     */
    public Long remove(String key, int index) {
        ListOperations<String, Object> list = redisTemplate.opsForList();
        return list.remove(key, 0, list.index(key, index));
    }

}
