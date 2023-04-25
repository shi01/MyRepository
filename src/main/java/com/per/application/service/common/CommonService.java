package com.per.application.service.common;

import java.util.List;
import java.util.Map;

/**
 * @author MYL
 * @date 2021-11-18 14:13:30
 */
public interface CommonService {
	List<Map<String, String>> find2Select(String c1, String c2, String c3, String t);

	List<Map<String, String>> find2SelectBySearch(String c1, String c2, String c3, String t, String w);

	Long count(String sql);
}
