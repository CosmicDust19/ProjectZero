package me.projectzero.service.common.objectmapping.manual.pagerequest;

import lombok.RequiredArgsConstructor;
import me.projectzero.service.common.model.request.pagerequest.PageRequestModel;
import me.projectzero.service.common.model.request.pagerequest.SortRequestModel;
import me.projectzero.service.common.objectmapping.interfaces.ManualMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PageRequestMapper implements ManualMapper<PageRequestModel, PageRequest> {

    private final ManualMapper<SortRequestModel, Sort> sortMapper;

    @Override
    public PageRequest map(PageRequestModel pageRequestModel) {
        if (pageRequestModel == null) return null;
        return PageRequest.of(pageRequestModel.getPageNo(), pageRequestModel.getPageSize(), sortMapper.map(pageRequestModel.getSort()));
    }

}
