package me.projectzero.service.model.request.pagerequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.service.common.abstraction.model.Model;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestModel implements Model {

    @Min(value = 0, message = "MsgCode.VALIDATION_PAGE_REQUEST_PAGE_NO_MIN")
    private Integer pageNo = 0;

    @Min(value = 0, message = "MsgCode.VALIDATION_PAGE_REQUEST_PAGE_SIZE_MIN")
    @Max(value = 0, message = "MsgCode.VALIDATION_PAGE_REQUEST_PAGE_SIZE_MAX")
    private Integer pageSize = 10;

    @Valid
    private SortRequestModel sort;

}
