package me.projectzero.service.common.model.request.pagerequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.service.common.model.interfaces.Model;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestModel implements Model {

    @Min(0)
    private Integer pageNo = 0;

    @Min(0)
    @Max(0)
    private Integer pageSize = 10;

    @Valid
    private SortRequestModel sort;

}
