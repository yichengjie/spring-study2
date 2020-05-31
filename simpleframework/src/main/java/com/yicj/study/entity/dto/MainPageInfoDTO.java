package com.yicj.study.entity.dto;

import com.yicj.study.entity.bo.HeadLine;
import com.yicj.study.entity.bo.ShopCategory;
import lombok.Data;

import java.util.List;

@Data
public class MainPageInfoDTO {
    private List<HeadLine> headLineList;
    private List<ShopCategory> shopCategoryList;
}