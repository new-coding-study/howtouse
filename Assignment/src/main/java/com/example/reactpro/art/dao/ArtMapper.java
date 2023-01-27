package com.example.reactpro.art.dao;

import com.example.reactpro.art.dto.ArtDTO;
import com.example.reactpro.common.paging.SelectCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ArtMapper {

    ArtDTO selectArt(int artNo);

    int insertArt(ArtDTO art);

    int updateArt(ArtDTO artDTO);

//    Integer selectArtTotal(String artAge);
    int selectArtTotal(String artAge);

    List<ArtDTO> selectArtListWithPaging(SelectCriteria selectCriteria);

    int deleteArt(int artNo);

}
