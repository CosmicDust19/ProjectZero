package me.projectzero.service.common.objectmapping.manual.pagerequest;

import me.projectzero.service.common.model.request.pagerequest.OrderRequestModel;
import me.projectzero.service.common.objectmapping.interfaces.ManualMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements ManualMapper<OrderRequestModel, Sort.Order> {

    @Override
    public Sort.Order map(OrderRequestModel orderModel) {
        if (orderModel == null) return null;
        return orderModel.isIgnoreCase() ?
                new Sort.Order(orderModel.getDirection(), orderModel.getProperty(), orderModel.getNullHandling()).ignoreCase() :
                new Sort.Order(orderModel.getDirection(), orderModel.getProperty(), orderModel.getNullHandling());
    }

}
