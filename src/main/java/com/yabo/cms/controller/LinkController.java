package com.yabo.cms.controller;

import com.yabo.cms.entity.Link;
import com.yabo.cms.entity.response.R;
import com.yabo.cms.service.LinkService;
import jakarta.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Resource
    private LinkService linkService;

    @GetMapping
    public Object getLink() {
        return linkService.list();
    }

    @Transactional
    @PostMapping("/add")
    public Object addLink(@RequestBody Link link) {
        return linkService.save(link) ? R.success("添加成功！") : R.error(500, "添加失败！");
    }

    @Transactional
    @PutMapping("/update")
    public Object updateLink(@RequestBody Link link) {
        return linkService.updateById(link) ? R.success("修改成功！") : R.error(500, "修改失败！");
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public Object deleteLink(@PathVariable String id) {
        Integer idInt = Integer.valueOf(id);
        return linkService.removeById(idInt) ? R.success("删除成功！") : R.error(500, "删除失败！");
    }
}
