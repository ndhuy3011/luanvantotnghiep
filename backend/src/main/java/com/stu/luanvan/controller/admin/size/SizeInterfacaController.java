package com.stu.luanvan.controller.admin.size;

import com.stu.luanvan.request.SizeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface SizeInterfacaController {
    ResponseEntity<?> postSave(@Valid @RequestBody SizeRequest sizeRequest) throws Exception;
}
