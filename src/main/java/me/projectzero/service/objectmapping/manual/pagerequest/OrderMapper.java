package me.projectzero.service.objectmapping.manual.pagerequest;

import lombok.NonNull;
import me.projectzero.service.common.abstraction.objectmapping.ManualMapper;
import me.projectzero.service.model.request.pagerequest.OrderRequestModel;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements ManualMapper<OrderRequestModel, Sort.Order> {

    @Override
    public Sort.Order map(@NonNull OrderRequestModel orderModel) {
        return orderModel.isIgnoreCase() ?
                new Sort.Order(orderModel.getDirection(), orderModel.getProperty(), orderModel.getNullHandling()).ignoreCase() :
                new Sort.Order(orderModel.getDirection(), orderModel.getProperty(), orderModel.getNullHandling());
    }

}
