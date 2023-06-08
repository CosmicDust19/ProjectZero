package me.projectzero.service.model.request.pagerequest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.service.common.abstraction.model.Model;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SortRequestModel implements Model {

    //@MaxCollectionSize(max = Validation.PageRequest.MAX_SIZE_ORDER_COUNT, message = MsgCode.VALIDATION_SORT_ORDERS_MAX_COLLECTION_SIZE)
    private List<@Valid OrderRequestModel> orders;

}
