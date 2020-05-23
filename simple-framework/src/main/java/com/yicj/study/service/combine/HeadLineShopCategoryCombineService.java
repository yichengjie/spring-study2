package com.yicj.study.service.combine;

import com.yicj.study.entity.dto.MainPageInfoDTO;
import com.yicj.study.entity.dto.Result;

public interface HeadLineShopCategoryCombineService {
    Result<MainPageInfoDTO> getMainPageInfo();
}
