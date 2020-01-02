package com.hyp.learn.r.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * 由于redis存储了大量上报的实时司机位置信息，也进行了大量的geo运算，所以线上的redis 不仅要考虑存储大小，还要考虑计算能力。
 *
 * 存储的的位置信息可以按照地区分开存储到不同的key，这样可以减少大量的数据运算，减少运算数据的颗粒。
 *
 * 如在网约车场景下，乘客下单时已经知道已经知道乘客的起点跟终点，这个时候进行派单，可以在不同地区下去搜索附近的司机，进行派单操作。从而减少运算成本。
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.r.utils
 * hyp create at 19-12-30
 **/

@Component
public class RedisGeoUtils {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 添加经纬度信息,时间复杂度为O(log(N))
     * redis 命令：geoadd cityGeo 116.405285 39.904989 "北京"
     *
     * @param k
     * @param point
     * @param m
     */
    @SuppressWarnings("all")
    public Long addGeoPoin(Object k, Point point, Object m) {
        Long addedNum = redisTemplate.opsForGeo().add(k, point, m);
        return addedNum;
    }

    /**
     * 查找指定key的经纬度信息，可以指定多个key，批量返回
     * redis命令：geopos cityGeo 北京
     *
     * @param k
     * @param m
     */
    public List<Point> geoGet(Object k, Object... m) {
        List<Point> points = redisTemplate.opsForGeo().position(k, m);
        return points;
    }

    /**
     * 返回两个地方的距离，可以指定单位，比如米m，千米km，英里mi，英尺ft
     * redis命令：geodist cityGeo 北京 上海
     *
     * @param k
     * @param mk1
     * @param mk2
     * @param metric
     * @return
     */
    public Distance geoDist(Object k, Object mk1, Object mk2, Metric metric) {
        Distance distance = redisTemplate.opsForGeo().distance(k, mk1, mk2, metric);
        return distance;
    }

    /**
     * 根据给定的经纬度，返回半径不超过指定距离的元素,时间复杂度为O(N+log(M))，N为指定半径范围内的元素个数，M为要返回的个数
     * redis命令：georadius cityGeo 116.405285 39.904989 100 km WITHDIST WITHCOORD ASC COUNT 5
     *
     * @param k
     * @param circle
     * @param args
     */
    public void nearByXY(Object k, Circle circle, RedisGeoCommands.GeoRadiusCommandArgs args) {
        //longitude,latitude
        //Circle circle = new Circle(116.405285, 39.904989, Metrics.KILOMETERS.getMultiplier());
        //RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(5);

        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo().radius(k, circle, args);
        System.out.println(results);
    }

    /**
     * 根据指定的地点查询半径在指定范围内的位置,时间复杂度为O(log(N)+M)，N为指定半径范围内的元素个数，M为要返回的个数
     * redis命令：georadiusbymember cityGeo 北京 100 km WITHDIST WITHCOORD ASC COUNT 5
     *
     * @param k
     * @param mk
     * @param distance
     * @param args
     * @return
     */
    public GeoResults<RedisGeoCommands.GeoLocation<String>> nearByPlace(Object k, Object mk, Distance distance, RedisGeoCommands.GeoRadiusCommandArgs args) {
//        Distance distance = new Distance(5, Metrics.KILOMETERS);
//        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<String>> results = redisTemplate.opsForGeo().radius(k, mk, distance, args);
        return results;
    }

    /**
     * 返回的是geohash值,查找一个位置的时间复杂度为O(log(N))
     * redis命令：geohash cityGeo 北京
     *
     * @param k
     * @param mks
     * @return
     */
    public List<String> geoHash(Object k, Object... mks) {
        List<String> results = redisTemplate.opsForGeo().hash(k, mks);
        return results;
    }

}
