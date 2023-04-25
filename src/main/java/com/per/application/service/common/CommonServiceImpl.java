package com.per.application.service.common;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.per.application.dao.common.CommonMapper;

/**
 * @author MYL
 * @date 2021-11-18 14:13:30
 */
@Service
public class CommonServiceImpl implements CommonService {
	@Autowired
	private CommonMapper cm;
	private String sql;

	@Override
	public List<Map<String, String>> find2Select(String c1, String c2, String c3, String t) {
		sql = "SELECT " + c1 + " val ," + c2 + " label," + c3 + " `describe` FROM " + t;
		return cm.execute(sql);
	}

	@Override
	public Long count(String sql) {
		return cm.executeCount(sql);
	}

	@Override
	public List<Map<String, String>> find2SelectBySearch(String c1, String c2, String c3, String t, String w) {
		sql = "SELECT " + c1 + " val ," + c2 + " label," + c3 + " `describe` FROM " + t + " WHERE "+w;
		return cm.execute(sql);
	}
}
