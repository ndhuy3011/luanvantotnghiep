package com.stu.luanvan.service.supplier;

import com.stu.luanvan.exception.NotFoundEx;
import com.stu.luanvan.locales.ExceptionLocales;
import com.stu.luanvan.model.supplier.SupplierModel;
import com.stu.luanvan.repository.SupplierRepository;
import com.stu.luanvan.request.SupplierRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
@Service
public class SupplierService implements SupplierServiceInterfaces{

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SupplierRepository supplierRepository;
    @Override
    public Map<String, Object> findByAll(Integer page, Integer size, String nameSort) {
        return null;
    }

    @Override
    public Collection<SupplierModel> findByAll() {
        return supplierRepository.findAll();
    }

    @Override
    public SupplierModel findById(Integer id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public SupplierModel saveNew(SupplierRequest supplierRequest) throws Exception {
        try{
            var supplier = new SupplierModel(supplierRequest);
            return supplierRepository.save(supplier);
        }catch(Exception ex) {
            logger.error("Save Supplier: ", ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public SupplierModel saveEdit(SupplierRequest supplierRequest, int id) throws Exception {
        try{
            var supplier = findById(id);
            if(supplier==null){
                throw new NotFoundEx(ExceptionLocales.NOT_FOUND);
            }
            supplier.edit(supplierRequest);
            return supplierRepository.save(supplier);
        }catch(Exception ex) {
            logger.error("edit Supplier: ", ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try{
            var supplier = findById(id);
            if(supplier==null){
                throw new NotFoundEx(ExceptionLocales.NOT_FOUND);
            }
             supplierRepository.delete(supplier);
        }catch(Exception ex) {
            logger.error("delete Supplier: ", ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }
    }
}
