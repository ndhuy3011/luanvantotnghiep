package com.stu.luanvan.request.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateRequest {
   private Date dateStart;
   private Date dateEnd;
}
