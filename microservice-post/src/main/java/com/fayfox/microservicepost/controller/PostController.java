package com.fayfox.microservicepost.controller;

import brave.Tracer;
import com.fayfox.annotation.SysLogger;
import com.fayfox.dto.RespDTO;
import com.fayfox.microservicepost.dto.PostDTO;
import com.fayfox.microservicepost.pojo.Post;
import com.fayfox.microservicepost.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("post")
@Api(tags = {"博文管理"})
public class PostController {
    private final PostService postService;

    private Tracer tracer;

    public PostController(PostService postService, Tracer tracer) {
        this.postService = postService;
        this.tracer = tracer;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "获取博文", notes = "通过ID获取博文信息", response = PostDTO.class, responseContainer = "Array")
    @SysLogger("获取博文")
    public RespDTO item(@PathVariable(name = "id") int id) {
        tracer.currentSpan().tag("name", "fayfox");
        return RespDTO.success(postService.get(id));
    }

    @PostMapping("")
    @ApiOperation(value = "创建博文")
    public RespDTO create(@RequestBody @Valid Post post) {
        postService.create(post);

        HashMap<String, Integer> resp = new HashMap<>();
        resp.put("id", post.getId());
        return RespDTO.success(resp);
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除博文", notes = "软删除，可恢复")
    public RespDTO delete(@PathVariable(name = "id") int id) {
        postService.softDelete(id);

        return RespDTO.msg("操作成功");
    }
}
