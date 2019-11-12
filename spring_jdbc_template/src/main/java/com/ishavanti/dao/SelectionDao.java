package com.ishavanti.dao;

import com.ishavanti.entity.Selection;

import java.util.List;
import java.util.Map;

public interface SelectionDao {
    void insert(List<Selection> selections);

    void delete(int sid, int cid);

    List<Map<String, Object>> selectByStudent(int sid);

    List<Map<String, Object>> selectByCourse(int cid);
}
