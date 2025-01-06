package com.sd.stockmanagementsystem.domain.service;


import com.sd.stockmanagementsystem.application.port.input.AddAttributeValueUseCase;
import com.sd.stockmanagementsystem.application.port.input.FindAttributeValueByAttributeValueKeyUseCase;
import com.sd.stockmanagementsystem.application.port.input.FindOrCreateAttributeValueUseCase;

public interface IAttributeValueService extends
        AddAttributeValueUseCase,
        FindOrCreateAttributeValueUseCase,
        FindAttributeValueByAttributeValueKeyUseCase {

}
