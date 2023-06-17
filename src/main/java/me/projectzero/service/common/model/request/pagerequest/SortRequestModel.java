package me.projectzero.service.common.model.request.pagerequest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.projectzero.service.common.model.interfaces.Model;
import me.projectzero.service.common.validation.annotations.MaxCollectionSize;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SortRequestModel implements Model {

    @MaxCollectionSize(10)
    private List<@Valid OrderRequestModel> orders;

}
