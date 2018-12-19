package com.zhannicholas.cpwms.service;

import java.util.Map;

public interface StorageService {
    Map<String, Object> findNumberById(int partsId, int repoId);
    boolean increaseStorage(int partsId, int repoId, int number);
    boolean decreaseStorage(int partsId, int repoId, int number);
}
