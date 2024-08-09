package org.qxdn.birthdayreminder.model.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 分页请求
 */
@Data
public class PageRequest {

    /**
     * 当前页
     */
    @NotNull(message = "当前页不能为空")
    @Min(value = 1, message = "当前页最小值为1")
    protected Integer current = 1;

    /**
     * 每页大小
     */
    @NotNull(message = "每页大小不能为空")
    @Min(value = 1, message = "每页大小最小值为1")
    protected Integer pageSize = 20;

    /**
     * TODO: 验证限定只有两种
     * 排序方式
     */
    @NotNull(message = "排序方式不能为空")
    protected String direction = "ASC";

    /**
     * 排序字段
     */
    @NotNull
    protected String orderBy = "id";

    public Integer compute() {
        return current-1;
    }


}
