package com.example.demo1.mapper;

import com.example.demo1.pojo.ViewRecordVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ViewRecordMapper {

    @Select("SELECT u.USER_NAME userName,vr.VIEW_TIME viewTime,f.FILM_NAME fileName "+
            "FROM user u JOIN view_record vr ON(u.USER_ID=vr.USER_ID )"+
            "JOIN film f ON(vr.FILM_ID=f.FILM_ID) WHERE vr.USER_ID=#{value}")
    List<ViewRecordVO>findRecordByUserId(int userId);
}
