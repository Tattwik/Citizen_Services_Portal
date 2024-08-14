package tech.csm.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tech.csm.domain.Practice;
import tech.csm.repository.PracticeRepo;

@Service
public class PracticeServiceImpl implements PracticeService {
	
	@Autowired
	private PracticeRepo practiceRepo;

	@Override
	public String getData(String stateName, Integer pageSize, Integer pageNumber, Pageable pageable) {
		JSONArray jArr = new JSONArray();
		JSONObject jObj = new JSONObject();
		
		Page<Practice> getData = null;
		if (stateName == "--select--") {
			getData = practiceRepo.findAll(pageable);
		} else {
			getData = practiceRepo.getDataByName(pageable, stateName);
		}
		try {
			if (getData != null && getData.getSize() > 0) {
				int current = getData.getNumber();
				int begin = Math.max(1, current - 5);
				int end =  Math.min(begin + pageSize, getData.getTotalPages());
				jObj.put("start", begin);
				jObj.put("end", end);
				jObj.put("current", current);
				jObj.put("pgno", getData.getNumber());
				jObj.put("showrowno", getData.getSize());
				jObj.put("totalrowno", getData.getTotalElements());
				
				JSONObject jsonObject = null;
				for (Practice getData1 : getData) {
					jsonObject  = new JSONObject();
					if (getData1 != null ) {
						if (getData1.getId() != null) {
							jsonObject.put("id", getData1.getId());
						}
						if (getData1.getName() != null) {
							jsonObject.put("name", getData1.getName());
						}
						if (getData1.getEmail() != null) {
							jsonObject.put("email", getData1.getEmail());
						}
						if (getData1.getState() != null) {
							jsonObject.put("state", getData1.getState());
						}
					}
					jArr.put(jsonObject);
				}
				jObj.put("list", jArr);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(jObj.toString());
		return jObj.toString();
	}

}
