package com.bihu.async;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wanganyu on 2017/12/18.
 */
public class EventModel {
    private EventType type;
    private int actorId;
    private int entityType;
    private int entityId;
    private int entityOwnerId;

    private Map<String,String> exts=new HashMap<String,String>();
    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public EventModel() {

    }
    public EventModel(EventType type, int actorId, int entityType, int entityId, int entityOwnerId, Map<String, String> exts) {
        this.type = type;
        this.actorId = actorId;
        this.entityType = entityType;
        this.entityId = entityId;
        this.entityOwnerId = entityOwnerId;
        this.exts = exts;
    }


    public int getActorId() {
        return actorId;
    }

    public void setActorId(int actorId) {
        this.actorId = actorId;
    }

    public int getEntityType() {
        return entityType;
    }

    public void setEntityType(int entityType) {
        this.entityType = entityType;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public int getEntityOwnerId() {
        return entityOwnerId;
    }

    public void setEntityOwnerId(int entityOwnerId) {
        this.entityOwnerId = entityOwnerId;
    }

    public Map<String, String> getExts() {
        return exts;
    }

    public void setExts(Map<String, String> exts) {
        this.exts = exts;
    }





}
