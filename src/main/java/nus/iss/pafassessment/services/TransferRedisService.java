package nus.iss.pafassessment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransferRedisService {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;  

    public void insertTransactionRecord(String tid, String value){
        redisTemplate.opsForValue().set(tid,value);
        return;
    }
}
