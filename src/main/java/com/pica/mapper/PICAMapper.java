package com.pica.mapper;

import java.util.ArrayList;
import java.util.List;

import com.pica.model.Application;

public class PICAMapper {

	public static List<Application> getAppliedForPayload(Application application, List<Application> list) {

		List<Application> applicationList = new ArrayList<>();

		if (list != null) {
			list.forEach((app) -> {
				if (app.getType().equalsIgnoreCase(application.getType())) {
					applicationList.add(new Application(application.getType(), application.getStatus(),
							application.getAppCode(), application.getCustId(), application.getBase29Code()));
				} else {
					applicationList.add(app);
				}
			});
			return applicationList;
		}

		applicationList.add(application);
		return applicationList;
	}

}
