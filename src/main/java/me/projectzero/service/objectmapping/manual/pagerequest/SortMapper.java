package me.projectzero.service.objectmapping.manual.pagerequest;

import lombok.RequiredArgsConstructor;
import me.projectzero.service.common.abstraction.objectmapping.ManualMapper;
import me.projectzero.service.model.request.pagerequest.OrderRequestModel;
import me.projectzero.service.model.request.pagerequest.SortRequestModel;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SortMapper implements ManualMapper<SortRequestModel, Sort> {

    private final ManualMapper<OrderRequestModel, Sort.Order> orderMapper;

    @Override
    public Sort map(SortRequestModel sortModel) {
        return sortModel == null || sortModel.getOrders().isEmpty() ?
                Sort.unsorted() :
                Sort.by(orderMapper.map(sortModel.getOrders()));
    }

}
