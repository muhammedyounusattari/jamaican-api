package com.pica.mapper;

import java.util.ArrayList;
import java.util.List;

import com.pica.model.Agent;
import com.pica.model.Applicant;
import com.pica.model.DeskClerk;

public class AdminReviewFormMapper {

	public static List<Integer> getApplicantCodes(List<Applicant> appCodes) {

		List<Integer> appCodeList = new ArrayList<Integer>();
		for (int i = 0; i < appCodes.size(); i++) {
			Applicant applicant = appCodes.get(i);
			appCodeList.add(applicant.getApplicantId());
		}

		return appCodeList;
	}

	public static Agent syncDBAgent(Agent agent, List<Applicant> appCodeList) {
		if(agent == null)
			return null;
		List<Applicant> dbApplicant = agent.getApplications();
		if (dbApplicant == null) {
			dbApplicant = new ArrayList<Applicant>();
			agent.setApplications(new ArrayList<Applicant>(appCodeList));
		} else {
			
			ArrayList<Applicant> currentApplicants = agent.getApplications();
			appCodeList.forEach(applicant->{
				currentApplicants.add(applicant);
			});
			agent.setApplications(currentApplicants);
			//TODO- need filter applicants
//			dbApplicant = mergeTwoList(dbApplicant,appCodeList);
		}

		return agent;
	}

	// Merge two list without duplicate.
	private static List<Applicant> mergeTwoList(List<Applicant> dbApplicant, List<Applicant> appCodeList) {
		if (dbApplicant != null && appCodeList != null) {
			dbApplicant.remove(appCodeList);
			appCodeList.addAll(dbApplicant);
		}

		return appCodeList;
	}

	public static void main(String[] args) {
		List<String> list1 = new ArrayList<String>();
		list1.add("a");
		list1.add("b");
		list1.add("c");

		List<String> list2 = new ArrayList<String>();
		list2.add("b");
		list2.add("a");
		list2.add("d");

		list1.removeAll(list2);
		list2.addAll(list1);
		System.out.println(list2);

	}

	public static DeskClerk syncDBDeskClerk(DeskClerk desckClerk, List<Applicant> appCodeList) {
		if(desckClerk == null)
			return null;
		List<Applicant> dbApplicant = desckClerk.getApplications();
		if (dbApplicant == null) {
			dbApplicant = new ArrayList<Applicant>();
			desckClerk.setApplications(new ArrayList<Applicant>(appCodeList));
		} else {
			
			ArrayList<Applicant> currentApplicants = desckClerk.getApplications();
			appCodeList.forEach(applicant->{
				currentApplicants.add(applicant);
			});
			desckClerk.setApplications(currentApplicants);
			//TODO- need filter applicants
//			dbApplicant = mergeTwoList(dbApplicant,appCodeList);
		}

		return desckClerk;
	}

}
