package com.stu.luanvan.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.stu.luanvan.locales.FormatLocales;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {
    private Integer id;
    private String address;
    private String numberPhone;
    private Integer status;

    @JsonFormat(pattern= FormatLocales.DATE_FORMAT)
    private Date createDate;

    private String billCode;
    private String payment;
    private String username;
    private List<InvoiceDetailsResponse> invoiceDetails;
}