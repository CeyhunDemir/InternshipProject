package com.sd.stockmanagementsystem.domain.service;


import com.sd.stockmanagementsystem.application.port.input.AddAttributeValueUseCase;
import com.sd.stockmanagementsystem.application.port.input.FindAttributeValueByAttributeValueKeyUseCase;

public interface IAttributeValueService extends
        AddAttributeValueUseCase,
        FindAttributeValueByAttributeValueKeyUseCase {

}
