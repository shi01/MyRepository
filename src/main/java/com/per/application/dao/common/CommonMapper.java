package com.per.application.dao.common;

import java.util.List;
import java.util.Map;

/**
 * @author MYL
 * @date 2021-11-24 17:02:24
 */
public interface CommonMapper {
	public List<Map<String, String>> execute(String sql);

	public Long executeCount(String sql);
}
