package com.brijframework.crm.filters;

import static com.brijframework.crm.contants.Constants.APP_ID_KEY;
import static com.brijframework.crm.contants.Constants.BUSINESS_ID_KEY;
import static com.brijframework.crm.contants.Constants.CUST_APP_ID;
import static com.brijframework.crm.contants.Constants.OWNER_ID_KEY;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.brijframework.crm.repository.CustBusinessAppRepository;

@Component
@Order(0)
public class TransactionFilter implements Filter {
	
	@Autowired
	private CustBusinessAppRepository custBusinessAppRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String ownerId = req.getHeader(OWNER_ID_KEY);
        String appId = req.getHeader(APP_ID_KEY);
        String businessId = req.getHeader(BUSINESS_ID_KEY);
        MutableHttpServletRequest requestWrapper = new MutableHttpServletRequest(req);
        if(Objects.nonNull(ownerId) && Objects.nonNull(businessId) && Objects.nonNull(appId)) {
        	custBusinessAppRepository.findByCustIdAndAppIdAndBusinessId(Long.valueOf(ownerId), Long.valueOf(appId),Long.valueOf(businessId)).ifPresent((custBusinessApp)->{
        		requestWrapper.putHeader(CUST_APP_ID, ""+custBusinessApp.getId());
        		req.setAttribute(CUST_APP_ID, ""+custBusinessApp.getId());
        	});
        } else  if(Objects.nonNull(ownerId) && Objects.nonNull(businessId)) {
         	custBusinessAppRepository.findByCustIdAndAppIdAndBusinessId(Long.valueOf(ownerId), Long.valueOf(1l),Long.valueOf(businessId)).ifPresent((custBusinessApp)->{
         		requestWrapper.putHeader(CUST_APP_ID, ""+custBusinessApp.getId());
         		req.setAttribute(CUST_APP_ID, ""+custBusinessApp.getId());
         	});
         } else  if(Objects.nonNull(ownerId)) {
         	custBusinessAppRepository.findByCustIdAndAppId(Long.valueOf(ownerId), Long.valueOf(1l)).ifPresent((custBusinessApp)->{
         		requestWrapper.putHeader(CUST_APP_ID, ""+custBusinessApp.getId());
         		req.setAttribute(CUST_APP_ID, ""+custBusinessApp.getId());
         	});
         }
        chain.doFilter(requestWrapper, response);
    }
}