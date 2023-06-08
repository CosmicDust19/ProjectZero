package me.projectzero.service.model.request.pagerequest;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.service.common.abstraction.model.Model;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestModel implements Model {

    @NotEmpty(message = "MsgCode.VALIDATION_ORDER_PROPERTY_NOT_BLANK")
    private String property;

    private Sort.Direction direction = Sort.Direction.ASC;

    private Sort.NullHandling nullHandling = Sort.NullHandling.NULLS_LAST;

    private boolean ignoreCase = false;

}
