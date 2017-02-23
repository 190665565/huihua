package com.partner.huihua.service.product.impl;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.partner.huihua.bean.ProductInfo;
import com.partner.huihua.mapper.ProductMapper;
import com.partner.huihua.service.product.ProductService;
import com.partner.huihua.utils.security.Base64;

@Service
public class ProductInfoImpl implements ProductService{
	@Autowired
	ProductMapper productmapper;

	@Override
	public JSONArray getProductInfo(ProductInfo entity) {
		// TODO Auto-generated method stub
			JSONArray out = new JSONArray();
			List<ProductInfo> lstP = productmapper.getProductByPage(entity);
			// 重组json
			for (ProductInfo pi : lstP) {
				JSONObject ret = new JSONObject();
				ret.put("id", pi.getId()); // id
				ret.put("name", pi.getName()); // 名称
				ret.put("salt", pi.getSalt()); // 售价
				ret.put("primecost", pi.getPrimecost());//商品原价
				ret.put("desc", pi.getDesc()); // 描述
				ret.put("instructions", pi.getInstructions()); // 描述
				ret.put("picture_url", new String(pi.getPictureUrl())); // 大图片url
				ret.put("mp_url", pi.getMpUrl()); // 小图片url
				ret.put("exchanged_num", pi.getExchange()); // 已兑换
				out.add(ret);
			}
		System.out.println(out);
		return out;
	}
	
	@Override
	public JSONObject getProductDetail(ProductInfo entity) {
		// TODO Auto-generated method stub
		ProductInfo pi = productmapper.getProductDetail(entity);
		// 重组json
			JSONObject ret = new JSONObject();
			ret.put("id", pi.getId()); // id
			ret.put("name", pi.getName()); // 名称
			ret.put("picture_url", pi.getPictureUrl()); // 大图片url
			ret.put("salt", pi.getSalt()); // 售价
			ret.put("primecost", pi.getPrimecost());//商品原价
			ret.put("desc", pi.getDesc()); // 描述
			ret.put("instructions", pi.getInstructions()); // 使用说明
			ret.put("status", pi.getStatus().getValue()); // 状态
			ret.put("remain_num", pi.getRemain()); // 剩余
			ret.put("exchanged_num", pi.getExchange()); // 已兑换
			return ret;
	}

	@Override
	public ProductInfo getProductObject(ProductInfo entity) {
		// TODO Auto-generated method stub
		ProductInfo pi = productmapper.getProductDetail(entity);
		return pi;
	}

}
