package com.yabo.cms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yabo.cms.entity.Link;
import com.yabo.cms.mapper.LinkMapper;
import com.yabo.cms.service.LinkService;
import org.springframework.stereotype.Service;

/**
 * (Link)表服务实现类
 *
 * @author makejava
 * @since 2024-01-07 19:37:20
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

}

