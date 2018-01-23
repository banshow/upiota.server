package io.github.upiota.server.sys.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.upiota.server.sys.entity.Menu;
import io.github.upiota.server.sys.mapper.MenuMapper;

@Service
public class MenuService {

	@Autowired
	private MenuMapper menuMapper;
	
	
	private void recursive(List<Menu> list,Map<Long,List<Menu>> map) {
		for(Menu m:list) {
			Long id = m.getId();
			List<Menu> l = map.get(id);
			if(l == null) {
				continue;
			}
			m.setChildren(l);
			
			recursive(l,map);
			
		}
	}

	public List<Menu> tree4User() {

		List<Menu> list = menuMapper.selectAll();

		Map<Long, List<Menu>> map = new LinkedHashMap<Long, List<Menu>>();

		list.forEach(s -> {
			Long pid = s.getParentId();
			List<Menu> l = map.get(pid);
			if (l == null) {
				l = new ArrayList<Menu>();
				map.put(pid, l);
			}
			l.add(s);
		});

		List<Menu> root = map.get(0l);

		recursive(root, map);
		return root;
	}

}