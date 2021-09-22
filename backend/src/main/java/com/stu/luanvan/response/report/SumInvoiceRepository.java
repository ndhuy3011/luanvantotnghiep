package com.stu.luanvan.response.report;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SumInvoiceRepository {
    long sum;
    String dateStart;
    String dateEnd;
}
