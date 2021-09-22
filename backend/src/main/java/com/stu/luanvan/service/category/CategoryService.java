package com.stu.luanvan.service.category;

import com.stu.luanvan.exception.DuplicateEx;
import com.stu.luanvan.exception.NotFoundEx;
import com.stu.luanvan.locales.ExceptionLocales;
import com.stu.luanvan.model.category.CategoryModel;
import com.stu.luanvan.repository.CategoryRepository;
import com.stu.luanvan.request.CategoryRequest;
import com.stu.luanvan.service.ObjectMapDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;
@Service
public class CategoryService implements CategoryServiceInterface{
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Map<String, Object> findByAll(Integer page, Integer size, String nameSort) {
        Pageable pageable;
        if(nameSort != null){
            pageable = PageRequest.of(page,size, Sort.by(nameSort).descending());
        }else{
            pageable = PageRequest.of(page,size);
        }
        return ObjectMapDto.objectMap(categoryRepository.findAll(pageable));
    }

    @Override
    public Collection<CategoryModel> findByAll() {
        return categoryRepository.findAll(Sort.by("lastModifiedDate").descending());
    }

    @Override
    public CategoryModel findById(Integer id)
    {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public CategoryModel saveNew(CategoryRequest categoryRequest) throws Exception {

            if(categoryRepository.findByName(categoryRequest.getName())!=null){
                throw new DuplicateEx(ExceptionLocales.NAME_SAKE);
            }
        try{
            CategoryModel category ;
            CategoryModel categoryFind;
            if(categoryRequest.getCategory()!=null){
                categoryFind = categoryRepository.findByName(categoryRequest.getCategory());
                category = new CategoryModel(categoryRequest,categoryFind);
            }else{
                category = new CategoryModel(categoryRequest,null);
            }
            return categoryRepository.save(category);
        }catch(Exception ex){
            logger.error("Save Category: ",ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public CategoryModel saveEdit(CategoryRequest categoryRequest,int id) throws Exception {
            var category = categoryRepository.
                    findById(id).
                    orElse(null);
            if(category == null){
                throw new NotFoundEx(ExceptionLocales.NOT_FOUND_PRODUCT);
            }
            if(!categoryRequest.getName().equals(category.getName())){
                if(categoryRepository.findByName(categoryRequest.getName())!=null){
                    throw new DuplicateEx(ExceptionLocales.NAME_SAKE);
                }
            }
        try{
            category.edit(categoryRequest);
            if(categoryRequest.getCategory()!=null){
                var categoryFind = categoryRepository.findByName(categoryRequest.getCategory());
                category.setCategory(categoryFind);
            }
            else{
                category.setCategory(null);
            }
            return categoryRepository.save(category);
        }catch (Exception ex){
            logger.error("Edit Category: ",ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(Integer id) throws Exception {

            var category = categoryRepository.
                    findById(id).
                    orElse(null);
            if(category ==null){
                throw new NotFoundEx(ExceptionLocales.NOT_FOUND_PRODUCT);
            }
            if(!category.getProduct().isEmpty()){
                throw new DuplicateEx(ExceptionLocales.CATEGORY_DUPLICATE_PRODUCT);
            }
        try{
            categoryRepository.delete(category);
        }catch (Exception ex){
            logger.error("Delete Category: ",ex);
            throw new Exception(ExceptionLocales.INTERNAL_SERVER_ERROR);
        }


    }

    @Override
    public Collection<CategoryModel> findByCategoryNotNull() {
        return categoryRepository.findByCategoryNotNull();
    }
    @Override
    public Collection<CategoryModel> findByCategoryNull() {
        return categoryRepository.findByCategoryNull();
    }
}
