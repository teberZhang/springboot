package com.example.springboot.common.form;

import com.example.springboot.common.Constants;
import lombok.Data;

@Data
public class BaseForm {

    private Integer page = Constants.DEFAULT_PAGE;

    private Integer pageSize = Constants.DEFAULT_PAGE_SIZE;
}
