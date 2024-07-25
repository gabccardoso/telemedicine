package com.hackaton.telemedicine.infrastructure.gateways.utils;

import com.hackaton.user.infrastructure.persistence.DoctorEntity;
import com.hackaton.user.infrastructure.persistence.PacientEntity;
import redis.clients.jedis.Jedis;

import java.util.UUID;

public class SessionUtil {

    private Jedis jedis;

    public SessionUtil(String host, int port) {
        this.jedis = new Jedis(host, port);
    }

    public SessionUtil() {
    }

    public String storeSessionPacient(PacientEntity pacient) {
        String sessionId = UUID.randomUUID().toString();
        String json;
        try {
            json = JsonUtils.serialize(pacient);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String key = "sessionId:" + sessionId;
        jedis.set(key, json);
        return key;
    }

    public String storeSessionDoctor(DoctorEntity doctorEntity) {
        String sessionId = UUID.randomUUID().toString();
        String json;
        try {
            json = JsonUtils.serialize(doctorEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String key = "sessionId:" + sessionId;
        jedis.set(key, json);
        return key;
    }
}
