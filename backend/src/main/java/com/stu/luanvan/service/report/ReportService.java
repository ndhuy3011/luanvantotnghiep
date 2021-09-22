package com.stu.luanvan.service.report;

import com.stu.luanvan.model.category.CategoryModel;
import com.stu.luanvan.model.invoice.InvoiceModel;
import com.stu.luanvan.model.invoicedetails.InvoiceDetailsModel;
import com.stu.luanvan.repository.InvoiceRepository;
import com.stu.luanvan.request.report.DateRequest;
import com.stu.luanvan.response.report.CategoryReport;
import com.stu.luanvan.response.report.SumInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public Map<String, Object> getInvoiceDate(DateRequest dateRequest){
        var invoice = new LinkedHashMap<String,Object>();
        invoice.put("not",invoiceRepository.findByStatusAndLastModifiedDateAfterAndLastModifiedDateBefore(InvoiceStatus.NOT_INVOICED,dateRequest.getDateStart(),dateRequest.getDateEnd()).size());
        invoice.put("accuracy",invoiceRepository.findByStatusAndLastModifiedDateAfterAndLastModifiedDateBefore(InvoiceStatus.ACCURACY_INVOICED,dateRequest.getDateStart(),dateRequest.getDateEnd()).size());
        invoice.put("transaction",invoiceRepository.findByStatusAndLastModifiedDateAfterAndLastModifiedDateBefore(InvoiceStatus.TRANSACTION_INVOICED,dateRequest.getDateStart(),dateRequest.getDateEnd()).size());
        invoice.put("complete",invoiceRepository.findByStatusAndLastModifiedDateAfterAndLastModifiedDateBefore(InvoiceStatus.COMPLETE_INVOICED,dateRequest.getDateStart(),dateRequest.getDateEnd()).size());
        invoice.put("cancel",invoiceRepository.findByStatusAndLastModifiedDateAfterAndLastModifiedDateBefore(InvoiceStatus.CANCEL_INVOICED,dateRequest.getDateStart(),dateRequest.getDateEnd()).size());
        invoice.put("dateStart",dateRequest.getDateStart());
        invoice.put("dateEnd",dateRequest.getDateEnd());
        return invoice;
    }

    public Map<String, Object> getInvoice(){
        var invoice = new LinkedHashMap<String,Object>();
        invoice.put("not",invoiceRepository.findByStatus(InvoiceStatus.NOT_INVOICED).size());
        invoice.put("accuracy",invoiceRepository.findByStatus(InvoiceStatus.ACCURACY_INVOICED).size());
        invoice.put("transaction",invoiceRepository.findByStatus(InvoiceStatus.TRANSACTION_INVOICED).size());
        invoice.put("complete",invoiceRepository.findByStatus(InvoiceStatus.COMPLETE_INVOICED).size());
        invoice.put("cancel",invoiceRepository.findByStatus(InvoiceStatus.CANCEL_INVOICED).size());
        return invoice;
    }

    public Collection<Object> getCategory(){
        var listCategory = new LinkedHashMap<String,Object>();
        var invoice = invoiceRepository.findAll();
        invoice.forEach(i->
            i.getInvoicedetals().forEach(id->{
                CategoryModel category = (CategoryModel) id.getProduct().getCategory().toArray()[0];
                if(listCategory.containsKey(category.getId()+"")){
                    var value= (CategoryReport) listCategory.get(category.getId()+"");
                    value.increasing();
                }else{
                    var listCategoryProduct =id.getProduct().getCategory();
                    StringBuilder name = new StringBuilder();
                    for(CategoryModel item :listCategoryProduct){
                        if(item.getCategory()==null){
                            name.append(item.getName());
                            break;
                        }
                        name.append(item.getName());
                        name.append(" ");
                    }
                    var categoryReport = new CategoryReport(name.toString(),1);
                    listCategory.put(category.getId()+"",categoryReport);
                }
            })
        );

        return listCategory.values();
    }
      public SumInvoiceRepository getInvoiceSum(String start){

        long sum = 0;
        var end = "";
        LocalDate date;
        try{
            date = LocalDate.parse(start).plusDays(1);
        }catch (Exception ex){
            start = LocalDate.now().toString();
            date  = LocalDate.parse(start).plusDays(1);
        }
          end = LocalDate.of(date.getYear(), date.getMonth(),date.getDayOfMonth()).toString();
          var invoice =invoiceRepository.findByLastModifiedDateAfter(start,end);
        for(InvoiceModel e : invoice){
            for (InvoiceDetailsModel invoicedetal : e.getInvoicedetals()) {
                sum += invoicedetal.getAmount() * invoicedetal.getPrice();
            }
        }
        return new SumInvoiceRepository(sum,start,end);
      }
    public Collection<SumInvoiceRepository> getInvoiceSumWeek(String start){
        long sum;
        var end = "";
        var listSumInvoice = new ArrayList<SumInvoiceRepository>();
        LocalDate date;
        try{
            date = LocalDate.parse(start).minusDays(7);
        }catch (Exception ex){
            date  = LocalDate.now().minusDays(7);

        }
        for (int i = 0; i<8;i++){
            sum = 0;
            start = date.toString();
            date = date.plusDays(1);
            end = date.toString();

            var invoice =invoiceRepository.findByLastModifiedDateAfter(start,end);
            for(InvoiceModel e : invoice){
                for (InvoiceDetailsModel invoicedetal : e.getInvoicedetals()) {
                    sum += invoicedetal.getAmount() * invoicedetal.getPrice();
                }
            }
            listSumInvoice.add(new SumInvoiceRepository(sum,start,end));


        }
        return listSumInvoice;
    }
}
