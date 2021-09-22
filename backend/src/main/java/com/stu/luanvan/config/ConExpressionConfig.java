package com.stu.luanvan.config;

import com.stu.luanvan.model.hot.HotModel;
import com.stu.luanvan.model.invoice.InvoiceModel;
import com.stu.luanvan.model.invoicedetails.InvoiceDetailsModel;
import com.stu.luanvan.repository.HotRepository;
import com.stu.luanvan.repository.InvoiceRepository;
import com.stu.luanvan.repository.ProductRepository;
import com.stu.luanvan.repository.ReviewRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
public class ConExpressionConfig {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private HotRepository hotRepository;
    @Autowired
    private ProductRepository productRepository;
    private final Logger logger = LogManager.getLogger(getClass());
    @Bean
    public TaskScheduler taskScheduler() {
        final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }

    @Scheduled(fixedDelay = 1000 * 60 * 100)
    public void scheduleReviewCommentFixedDelayTask() throws InterruptedException{
        try{
            var review = reviewRepository.findByStatusFalse();
            review.forEach(e->{
                if(e.getRate()>2){
                    e.setStatus(true);
                }
            });
            reviewRepository.saveAll(review);
        }catch (Exception ex){
            logger.error(ex);
        }
    }
    @Scheduled(cron="0 1 1 * * ?")
    public void scheduledHotProductCron() {
        try{
            var start = LocalDate.now().minusDays(1).toString();
            var end = LocalDate.now().toString();
            var invoice =invoiceRepository.findByLastModifiedDateAfter(start,end);
            var listHot = new LinkedHashMap<Integer,HotModel>();
            for(InvoiceModel e : invoice){
                for (InvoiceDetailsModel invoicedetal : e.getInvoicedetals()) {
                    if(listHot.containsKey(invoicedetal.getProduct().getId())){
                        var value= (HotModel) listHot.get(invoicedetal.getProduct().getId());
                        value.increasing();
                    }else{
                        var itemHot = new HotModel(invoicedetal.getProduct());
                        listHot.put(invoicedetal.getProduct().getId(),itemHot);
                    }
                }
            }
            List<HotModel> listHotModel = new ArrayList<HotModel>(listHot.values());
            listHotModel.sort(new Comparator<>() {
                @Override
                public int compare(HotModel o1, HotModel o2) {
                    return o1.getAmount().compareTo(o2.getAmount());
                }
            });
            var listAddHotModel = listHotModel.stream().limit(20).collect(Collectors.toList());
            hotRepository.saveAll(listAddHotModel);
        }catch(Exception ex){
            logger.error(ex);
        }
    }
    @Scheduled(cron="0 1 1 * * ?") //23h ngay cuoi cung cua thang
    public void scheduledFreshProductCron() {
        try{
            var product = productRepository.findByFreshTrue();
            product.forEach(e->{
                e.setFresh(false);
            });
            productRepository.saveAll(product);
        }catch(Exception ex){
            logger.error(ex);
        }
    }
}
